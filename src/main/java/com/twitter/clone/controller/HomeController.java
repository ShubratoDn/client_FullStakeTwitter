package com.twitter.clone.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.twitter.clone.entity.Post;
import com.twitter.clone.entity.User;
import com.twitter.clone.helpers.ServerMessage;
import com.twitter.clone.services.PostServices;
import com.twitter.clone.services.UserServices;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@Autowired    
    private  UserServices userServices;
	
	@Autowired
    private PostServices postServices;
    	
	//home page
	@GetMapping(value = {"/home","/"})
	public String home(Model model, HttpSession session) {
		
		ServerMessage sm = null;
		model.addAttribute("postMsg", sm);		
		model.addAttribute("title", "");
		model.addAttribute("content","");
		
		List<Post> allPosts = new ArrayList<Post>();
		allPosts = postServices.getAllPosts();
		
		model.addAttribute("allPosts",allPosts);		
		session.setAttribute("allPosts", allPosts);
		
		return "home";
	}	
	

	
	//profile Controllers
	@GetMapping("/profile/{userId}")
	public String profile(@PathVariable("userId") int userId, HttpSession session) {
		
		User loggedUser =  (User)session.getAttribute("user");		
		if(loggedUser == null) {
			return "redirect:/login";
		}
		
		User user = userServices.getUserById(userId);
		if(user == null) {
			return "redirect:/home";
		}
		session.setAttribute("visitedUser", user);
		return "redirect:/profile";
	}
	
	
	@GetMapping("/profile")
	public String uprofile(Model model, HttpSession session) {		
		
		User user = null;
		
		if(session.getAttribute("visitedUser") != null) {
			 user = (User) session.getAttribute("visitedUser");
			model.addAttribute("visitedUser", user);
		}else if(session.getAttribute("user")!= null) {			
			 user  = (User) session.getAttribute("user");
			model.addAttribute("visitedUser", user);
		}else {			
			return "redirect:/home";
		}
		
		List<Post> allPosts = postServices.getAllPostsByUserId(user.getId());		
		model.addAttribute("allPosts",allPosts);		
		
		
		
		User x = (User) session.getAttribute("visitedUser");
		User y =(User) session.getAttribute("user");
		
		if( x!= null && x.getId() == y.getId()) {
			model.addAttribute("ownerVisiting", true);
		}else if(x == null) {
			model.addAttribute("ownerVisiting", true);
		}
		
		
		return "profile";
	}
	
	
	
	
}
