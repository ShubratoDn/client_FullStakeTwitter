package com.twitter.clone.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.twitter.clone.entity.User;
import com.twitter.clone.repository.UserRepo;

@Controller
public class UserController {	
	
	@Autowired
	private UserRepo userRepo;
	
	@GetMapping("/search_user")
	public String followpage() {
		return "search_user";
	}
	
	
	@GetMapping("/user/{username}")
	public ResponseEntity<?> userSearchList(@PathVariable String username){
		List<User> userByNameContaining = new ArrayList<>();		
		
		userByNameContaining = userRepo.getUserByNameContaining(username);
			
		return ResponseEntity.ok(userByNameContaining);
	}
	
}
