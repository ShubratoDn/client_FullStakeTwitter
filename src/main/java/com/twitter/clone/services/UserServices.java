package com.twitter.clone.services;

import java.util.List;

import com.twitter.clone.entity.User;

public interface UserServices {	
	
	List<String> userValidation(User user);
	
	int addUser(User user);
	User getUserByEmail(String email);	
	int updateUser();
	int removeUser();
	User getUserById(int userId);
	
}
