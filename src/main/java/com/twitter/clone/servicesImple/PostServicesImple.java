package com.twitter.clone.servicesImple;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.twitter.clone.entity.Post;
import com.twitter.clone.repository.PostRepo;
import com.twitter.clone.services.PostServices;

@Component
public class PostServicesImple implements PostServices{
	
	@Autowired
    private PostRepo postRepo;
    
		
	//post validation
	@Override
	public List<String> postValidation(Post post) {

		List<String> error = new ArrayList<String>();
		if(post.getTitle().isEmpty()) {
			error.add("Title Can not be null");
		}else if(post.getTitle().length() > 60) {
			error.add("Title should not exceed 60 character");
		}
		
		if(post.getContent().isEmpty()) {
			error.add("Content Can not be null");
		}else if(post.getContent().length() > 120) {
			error.add("Content should not exceed 120 character");
		}
			
		return error;
	}
	
	
	//add Post
	public int addPost(Post post, int userId) {
		int addPost = this.postRepo.addPost(post, userId);
		return addPost;
	}
	
	public int addPostMultimedia(Post post, int userId, String fileType, String filePath) {
		int addPostMultimedia = this.postRepo.addPostMultimedia(post, userId, fileType, filePath);
		return addPostMultimedia;
	}



	//get all posts
	public List<Post> getAllPosts(){
		List<Post> allPosts = this.postRepo.getAllPosts();
		return allPosts;
	}

	
	//get all potst by user id
	public List<Post> getAllPostsByUserId(int userId){
		List<Post> allPosts = this.postRepo.getAllPostsByUserId(userId);
		return allPosts;
	}



	
}
