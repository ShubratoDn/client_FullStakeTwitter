package com.twitter.clone.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import com.twitter.clone.entity.User;
import com.twitter.clone.entity.UserSubscribed;
import com.twitter.clone.services.UserServices;
import com.twitter.clone.servicesImple.UserServicesImple;

public class UserSubscribedRowMapperImple implements RowMapper<UserSubscribed> {

	private final UserServices userServices;

    @Autowired
    public UserSubscribedRowMapperImple(UserServices userServices) {
        this.userServices = userServices;
    }
    
	@Override
	public UserSubscribed mapRow(ResultSet rs, int rowNum) throws SQLException {	
		
		UserSubscribed userSubscribed = new UserSubscribed();
		
		User follower = userServices.getUserById(rs.getInt("follower_id"));
		User creator = userServices.getUserById(rs.getInt("creator_id"));
		
		userSubscribed.setId(rs.getInt("id"));
		userSubscribed.setFollower(follower);
		userSubscribed.setCreator(creator);
		
		return userSubscribed;
	}

}
