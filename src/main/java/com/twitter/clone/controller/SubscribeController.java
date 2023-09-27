package com.twitter.clone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.twitter.clone.entity.User;
import com.twitter.clone.entity.UserSubscribed;
import com.twitter.clone.servicesImple.UserSubscribeServiceImple;

import jakarta.servlet.http.HttpSession;

@Controller
public class SubscribeController {

	@Autowired
	private UserSubscribeServiceImple subscribeServiceImple;

	
	@GetMapping("/my-followers")
	public String myFollowers(Model model, HttpSession session) {

		User user = (User) session.getAttribute("user");

		List<User> myFollower = subscribeServiceImple.myFollower(user.getId());

		model.addAttribute("myFollowerList", myFollower);

		return "followers";
	}
	
	
	
	@GetMapping("/following")
	public String following(Model model, HttpSession session) {

		User user = (User) session.getAttribute("user");

		List<User> myFollower = subscribeServiceImple.following(user.getId());

		model.addAttribute("following", myFollower);

		return "following";
	}
	
	
	
	
	@PostMapping("/follow")
	public ResponseEntity<?> followSomeone(@RequestParam("userId") int creator_id, HttpSession session) {		
		User user = (User) session.getAttribute("user");
		int follower_id = user.getId();
		
		UserSubscribed checkSubscription = subscribeServiceImple.checkSubscription(user.getId(), creator_id);
		if(checkSubscription == null) {
			subscribeServiceImple.subscribe(follower_id, creator_id);			
			return ResponseEntity.ok("FOLLOWED");
		}else {
			subscribeServiceImple.deleteSubscribe(follower_id, creator_id);
			return ResponseEntity.ok("UNFOLLOWED");
		}
	}
	
	
	
	
	@PostMapping("/isFollowing")
	public ResponseEntity<?> isFollowing(@RequestParam("userId") int creator_id, HttpSession session) {		
		User user = (User) session.getAttribute("user");
				
		UserSubscribed checkSubscription = subscribeServiceImple.checkSubscription(user.getId(), creator_id);
		if(checkSubscription == null) {						
			return ResponseEntity.ok(false);
		}else {			
			return ResponseEntity.ok(true);
		}
	}
	
	
	
}
