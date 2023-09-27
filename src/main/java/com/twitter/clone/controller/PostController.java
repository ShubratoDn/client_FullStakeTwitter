package com.twitter.clone.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.twitter.clone.entity.Post;
import com.twitter.clone.entity.User;
import com.twitter.clone.helpers.FileServices;
import com.twitter.clone.helpers.ServerMessage;
import com.twitter.clone.services.PostServices;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class PostController {

	@Autowired
	private PostServices postServices;

	
	// inserting post
	@PostMapping("/write-post")
	public String writeBlog(
			@ModelAttribute Post post,
			@RequestParam("file") MultipartFile file,
			HttpSession session,
			Model model,
			HttpServletRequest req,
			RedirectAttributes redirectAttributes) throws IOException {

		ServerMessage sm = new ServerMessage();
		model.addAttribute("title", post.getTitle());
		model.addAttribute("content", post.getContent());
		
		//jokhn error asbe tokhon allPost set kortesi
		
		List<Post> allPosts = new ArrayList<Post>();
		allPosts = (List<Post>) req.getAttribute("allPosts");		

		model.addAttribute("allPosts", allPosts);
			
		User user = (User) session.getAttribute("user");
		if (user == null) {
			System.out.println("User Not Logged In for upload Post");
			return "home";
		}

		// input validation
		List<String> postValidation = postServices.postValidation(post);
		if (!postValidation.isEmpty()) {
			sm = new ServerMessage(postValidation, "error", "alert-danger");
			model.addAttribute("postMsg", sm);
			return "home";
		}

		// file validation
		FileServices fileServices = new FileServices();

		// getting file type
		String postFileType = "empty";
		if (!file.isEmpty()) {
			postFileType = fileServices.postFileType(file);
		}

		// image validation
		if (postFileType.equals("image")) {
			List<String> postImageValidation = fileServices.postImageValidation(file);
			if (!postImageValidation.isEmpty()) {
				sm = new ServerMessage(postImageValidation, "error", "alert-danger");
				model.addAttribute("postMsg", sm);
				return "home";
			}
		} else if (postFileType.equals("video")) {
			// Video validation
			List<String> postVideoValidation = fileServices.postVideoValidation(file);
			if (!postVideoValidation.isEmpty()) {
				sm = new ServerMessage(postVideoValidation, "error", "alert-danger");
				model.addAttribute("postMsg", sm);
				return "home";
			}
		} else if (postFileType.equals("empty")) {

		} else {
			sm = new ServerMessage(Arrays.asList("Invalid file type"), "error", "alert-danger");
			model.addAttribute("postMsg", sm);
			return "home";
		}

		// upload the file
		String filePath = "";
		if (postFileType.equals("image") || postFileType.equals("video")) {
			filePath = fileServices.uploadPostFile(file, session);
		}

		// Inserting data to Database
		post.setUser(user);
		postServices.addPost(post, user.getId());
		// uploading multimedia data if available
		if (postFileType.equals("image") || postFileType.equals("video")) {
			postServices.addPostMultimedia(post, user.getId(), postFileType, filePath);
		}

		System.out.println(post);
		model.addAttribute("title", "");
		model.addAttribute("content", "");

		
		session.setAttribute("postAdded", new Object());
		
		redirectAttributes.addFlashAttribute("title", post.getTitle());
		redirectAttributes.addFlashAttribute("content", post.getContent());
		return "redirect:/profile";
	}
	
	
	
	@GetMapping("/post/search/{query}")
	public ResponseEntity<?> searchQuery(@PathVariable String query){
		List<Post> postContaining = this.postServices.getPostContaining(query);
		return ResponseEntity.ok(postContaining);
	}
	
	

	
	@GetMapping("/post/{id}")
	public String post(@PathVariable int id, Model model){
		
		Post postById = this.postServices.getPostById(id);
		
		model.addAttribute("post", postById);
		
		return "postDetail";
	}
	

}
