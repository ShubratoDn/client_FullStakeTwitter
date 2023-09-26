package com.twitter.clone.servicesImple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.twitter.clone.entity.UserSubscribed;
import com.twitter.clone.repository.UserSubscribedRepo;
import com.twitter.clone.services.UserSubscribeServices;

@Component
public class UserSubscribeServiceImple implements UserSubscribeServices {

	@Autowired
	private UserSubscribedRepo userSubscribedRepo;
	
	public UserSubscribed checkSubscription(int follower_id, int creator_id) {
		UserSubscribed checkSubscription = userSubscribedRepo.checkSubscription(follower_id, creator_id);
		return checkSubscription;
	}
	
	
	public int subscribe(int follower_id, int creator_id) {		
		int addSubscribe = userSubscribedRepo.addSubscribe(follower_id, creator_id);
		return addSubscribe;
	}
	
	//delete subscriber
	public int deleteSubscribe(int follower_id, int creator_id) {
	  int deleteSubscribe = userSubscribedRepo.deleteSubscribe(follower_id, creator_id);
	  return deleteSubscribe;
	}
	
}
