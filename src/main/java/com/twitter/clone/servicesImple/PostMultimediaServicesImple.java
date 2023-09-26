package com.twitter.clone.servicesImple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.twitter.clone.entity.PostMultimedia;
import com.twitter.clone.repository.PostMultimediaRepo;
import com.twitter.clone.services.PostMultimediaServices;

@Component
public class PostMultimediaServicesImple implements PostMultimediaServices{
	
	@Autowired
    private PostMultimediaRepo postMultimediaRepo;
    	
    
	@Override
	public PostMultimedia getPostMultimediaByPostId(int postId) {
		PostMultimedia postMm = this.postMultimediaRepo.getPostMultimediaByPostId(postId);
		return postMm;
	}

	
	
}
