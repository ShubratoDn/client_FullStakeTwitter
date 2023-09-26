package com.twitter.clone.services;

import java.util.List;

import com.twitter.clone.entity.Post;

public interface PostServices {

	List<String> postValidation(Post post);
	
	int addPost(Post post, int userId);
	int addPostMultimedia(Post post, int userId, String fileType, String filePath);
	
	List<Post> getAllPosts();
	List<Post> getAllPostsByUserId(int userId);
}
