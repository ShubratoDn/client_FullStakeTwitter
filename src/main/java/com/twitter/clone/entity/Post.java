package com.twitter.clone.entity;

import java.sql.Timestamp;
public class Post {
	
	private int id;
	private String title;
	private String content;
	private Timestamp upload_date;
	private PostMultimedia postMultimedia;
	private User user;
	
	public Post(int id, String title, String content, Timestamp upload_date, PostMultimedia postMultimedia, User user) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.upload_date = upload_date;
		this.postMultimedia = postMultimedia;
		this.user = user;
	}


	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}


	
	
	public PostMultimedia getPostMultimedia() {
		return postMultimedia;
	}


	public void setPostMultimedia(PostMultimedia postMultimedia) {
		this.postMultimedia = postMultimedia;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getUpload_date() {
		return upload_date;
	}
	public void setUpload_date(Timestamp upload_date) {
		this.upload_date = upload_date;
	}


	@Override
	public String toString() {
		return "Post [id=" + id + ", title=" + title + ", content=" + content + ", upload_date=" + upload_date + "]";
	}

	
	
	
	
}
