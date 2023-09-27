package com.twitter.clone.repository;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.twitter.clone.entity.Post;
import com.twitter.clone.entity.PostMultimedia;
import com.twitter.clone.entity.User;
import com.twitter.clone.services.PostMultimediaServices;
import com.twitter.clone.services.UserServices;

@Repository
public class PostRepo {

	private  JdbcTemplate jdbcTemplate;	
	
	private final UserServices userServices;
	
	private final PostMultimediaServices postMultimediaServices;
	
	@Autowired
    public PostRepo(JdbcTemplate jdbcTemplate, UserServices userServices, PostMultimediaServices postMultimediaServices) {
        this.jdbcTemplate = jdbcTemplate;
        this.userServices = userServices;
        this.postMultimediaServices = postMultimediaServices;
    }	
	
	
	public int addPost(Post post, int user_id) {
		String sql = "INSERT INTO post (user_id, title, content) VALUES (?, ?, ?)";		
		int affectedRow = jdbcTemplate.update(sql, user_id, post.getTitle(), post.getContent());
		
		System.out.println("Post added");
		return affectedRow;
	}	
	
	
	//multimedia data insert
	public int addPostMultimedia(Post post, int user_id, String fileType, String filePath) {		
		
		Post uploadedPost = null;		
		String sql = "SELECT * FROM post WHERE user_id=? AND title=? ";		
		RowMapper<Post> rowMapper = new PostRowMapperImple();		
		try {			
			uploadedPost = this.jdbcTemplate.queryForObject(sql, rowMapper, user_id, post.getTitle());
		} catch (Exception e) {
			System.out.println(e);
			uploadedPost = null;
		}
		
		
		String sql2 = "INSERT INTO post_multimedia (post_id, media_type, path) VALUES (?, ?, ?)";	
		int affectedRow = jdbcTemplate.update(sql2, uploadedPost.getId(), fileType, filePath);		
		
		System.out.println("Post Multimedia Inserted");
		return affectedRow;
	}
	
	
	
	//get All Posts
	public List<Post> getAllPosts(){		
		String sql = "SELECT * FROM post  ORDER BY id  DESC";
		List<Post> allPosts = this.jdbcTemplate.query(sql, new PostRowMapperImple());
		
		for(Post post : allPosts) {
			User user = userServices.getUserById(post.getUser().getId());
			PostMultimedia postMultimedia = postMultimediaServices.getPostMultimediaByPostId(post.getId());
			post.setUser(user);
			post.setPostMultimedia(postMultimedia);
		}
		
		return allPosts;
	}
	
	public List<Post> getYourFollowingPosts(int follower_id) {
		String sql = "SELECT p.* FROM post p JOIN user_subscribed us ON p.user_id = us.creator_id WHERE us.follower_id = "+follower_id+" OR p.user_id ="+follower_id+" ORDER BY id  DESC";
		List<Post> allPosts = this.jdbcTemplate.query(sql, new PostRowMapperImple());
		
		for(Post post : allPosts) {
			User user = userServices.getUserById(post.getUser().getId());
			PostMultimedia postMultimedia = postMultimediaServices.getPostMultimediaByPostId(post.getId());
			post.setUser(user);
			post.setPostMultimedia(postMultimedia);
		}
		
		return allPosts;
	}
	
	//get All Posts By User id
	public List<Post> getAllPostsByUserId(int userId){		
		String sql = "SELECT * FROM post  where user_id=? ORDER BY id  DESC";
		List<Post> allPosts = this.jdbcTemplate.query(sql, new PostRowMapperImple(), userId);
		
		for(Post post : allPosts) {
			User user = userServices.getUserById(post.getUser().getId());
			PostMultimedia postMultimedia = postMultimediaServices.getPostMultimediaByPostId(post.getId());
			post.setUser(user);
			post.setPostMultimedia(postMultimedia);
		}
		
		return allPosts;
	}
	
	
	
	public List<Post> getPostContaining(String text){
		String sql = "SELECT * from post WHERE title LIKE '%"+text+"%' or content LIKE '%"+text+"%' ORDER BY id  DESC";
		List<Post> allPosts = this.jdbcTemplate.query(sql, new PostRowMapperImple());
		
		for(Post post : allPosts) {
			User user = userServices.getUserById(post.getUser().getId());
			PostMultimedia postMultimedia = postMultimediaServices.getPostMultimediaByPostId(post.getId());
			post.setUser(user);
			post.setPostMultimedia(postMultimedia);
		}
		
		return allPosts;
	}
	
	
	
	public Post getPostById(int id) {
		
		String sql = "select * from post where id=?";
		
		RowMapper<Post> rowMapper = new PostRowMapperImple();
		Post post = null;		
		try {
			post = this.jdbcTemplate.queryForObject(sql,rowMapper,id);
		} catch (Exception e) {
			return null;
		}
		
		User user = userServices.getUserById(post.getUser().getId());
		PostMultimedia postMultimedia = postMultimediaServices.getPostMultimediaByPostId(post.getId());
		post.setUser(user);
		post.setPostMultimedia(postMultimedia);
		
		return post;
	}
	
	
	
}
