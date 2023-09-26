package com.twitter.clone.servicesImple;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.twitter.clone.entity.User;
import com.twitter.clone.repository.UserRepo;
import com.twitter.clone.services.UserServices;

@Component
public class UserServicesImple implements UserServices {	

	
	@Autowired
    private UserRepo userRepo;
	
	
	
	//adding user
	public int addUser(User user) {
		int addUser = this.userRepo.addUser(user);
		return addUser;
	}

	
	public int updateUser() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int removeUser() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public User getUserById(int userId) {
		User user = userRepo.getUserById(userId);
		return user;
	}


	
	public User getUserByEmail(String email) {
		User user = userRepo.getUserByEmail(email);
		return user;
	}

	
	//user Validation
	public List<String> userValidation(User user) {
		
		List<String> error = new ArrayList<String>();
		
		//name validation
		if(user.getName().isBlank() || user.getName() == null) {
			error.add("Please Enter the name");			
		}else {
			if(user.getName().length() < 3) {
				error.add("Name should be more than 3 char");	
			}
		}
		
		//email validation
		if(user.getEmail().isBlank()) {
			error.add("Please Enter the Email");		
		}
		
		
		if(user.getPassword().isBlank()) {
			error.add("Enter Password");
		}else {
			if(user.getPassword().length() <= 3) {
				error.add("Password need minimum 4 char");	
			}
		}
		
		return error;
	}









}
