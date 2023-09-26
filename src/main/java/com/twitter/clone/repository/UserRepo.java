package com.twitter.clone.repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.twitter.clone.entity.User;

@Repository
public class UserRepo {		

	@Autowired
	private  JdbcTemplate jdbcTemplate;
	
	//add User
	public int addUser(User user) {		
		String sql = "INSERT INTO user (name, email, password, image) VALUES (?, ?, ?,?)";		
		int addeduser = jdbcTemplate.update(sql, user.getName(), user.getEmail(), user.getPassword(), user.getImage());
		return addeduser;
	}
	
	
	//get user by email
	public User getUserByEmail(String email) {		
		String sql = "select * from user where email=?";		
		RowMapper<User> rowMapper = new UserRowMapperImple();
		
		try {
			User user = this.jdbcTemplate.queryForObject(sql,rowMapper,email);
			return user;
		} catch (Exception e) {
			return null;
		}
		
	}
	
	
	public User getUserById(int id) {		
		String sql = "select * from user where id=?";		
		RowMapper<User> rowMapper = new UserRowMapperImple();
		
		try {
			User user = this.jdbcTemplate.queryForObject(sql,rowMapper,id);
			return user;
		} catch (Exception e) {
			return null;
		}
		
	}
	
	
	
}
