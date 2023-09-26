package com.twitter.clone.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.twitter.clone.entity.Post;
import com.twitter.clone.entity.User;

public class PostRowMapperImple implements RowMapper<Post>{

	@Override
	public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Post post = new Post();
		User user = new User();
		
		post.setId(rs.getInt("id"));
		
		user.setId(rs.getInt("user_id"));
		post.setUser(user);
		
		post.setTitle(rs.getString("title"));
		post.setContent(rs.getString("content"));
		post.setUpload_date(rs.getTimestamp("upload_date"));
		
		return post;
	}

	
}
