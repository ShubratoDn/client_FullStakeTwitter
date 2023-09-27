package com.twitter.clone.repository;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.twitter.clone.entity.User;
import com.twitter.clone.entity.UserSubscribed;
import com.twitter.clone.services.UserServices;

@Component
public class UserSubscribedRepo {

	@Autowired
	private  JdbcTemplate jdbcTemplate;
	
	@Autowired
	private UserServices userServices;
	
	//check for subscription
	public UserSubscribed checkSubscription(int follower_id, int creator_id) {
		String sql = "SELECT * FROM `user_subscribed` WHERE `follower_id`=? AND creator_id=?";
		RowMapper<UserSubscribed> rowMapper = new UserSubscribedRowMapperImple(userServices);
		
		try {
			UserSubscribed queryForObject = this.jdbcTemplate.queryForObject(sql, rowMapper, follower_id, creator_id);
			return queryForObject;
		}catch (Exception e) {
			return null;
		}		
	}
	
	
	//subscribe 
	public int addSubscribe(int follower_id, int creator_id) {		
		String sql = "INSERT INTO `user_subscribed`(`follower_id`, `creator_id`) VALUES (?,?)";		
		int addedSubscriber = jdbcTemplate.update(sql, follower_id, creator_id);
		return addedSubscriber;
	}
	
	//delete subscriber
	public int deleteSubscribe(int follower_id, int creator_id) {
	    String sql = "DELETE FROM `user_subscribed` WHERE `follower_id` = ? AND `creator_id` = ?";
	    int deletedSubscriber = jdbcTemplate.update(sql, follower_id, creator_id);
	    return deletedSubscriber;
	}

	
	public List<User> myFollower(int creator_id){
		String sql = "SELECT * from `user_subscribed` where `creator_id`=?";
		RowMapper<UserSubscribed> rowMapper = new UserSubscribedRowMapperImple(userServices);
		
		try {
			List<UserSubscribed> queryForObject = this.jdbcTemplate.query(sql, rowMapper,creator_id);
			List<User> list = new ArrayList<>();
			
			for(UserSubscribed userSubscribed: queryForObject) {
				list.add(userSubscribed.getFollower());
			}
			
			return list;
		}catch (Exception e) {
			System.out.println("WORK 2" + e);
			return null;
		}
	}
	
	
	
	public List<User> following(int follower_id){
		String sql = "SELECT * from `user_subscribed` where `follower_id`=?";
		RowMapper<UserSubscribed> rowMapper = new UserSubscribedRowMapperImple(userServices);
		
		try {
			List<UserSubscribed> queryForObject = this.jdbcTemplate.query(sql, rowMapper,follower_id);
			List<User> list = new ArrayList<>();
			
			for(UserSubscribed userSubscribed: queryForObject) {
				list.add(userSubscribed.getCreator());
			}
			
			return list;
		}catch (Exception e) {
			System.out.println("WORK 2" + e);
			return null;
		}
	}
	
	
}
