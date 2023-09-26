package com.twitter.clone.entity;

public class PostMultimedia {

	private int id;
	private int post_id;
	private String media_type;
	private String path;
	public PostMultimedia(int id, int post_id, String media_type, String path) {
		super();
		this.id = id;
		this.post_id = post_id;
		this.media_type = media_type;
		this.path = path;
	}
	public PostMultimedia() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPost_id() {
		return post_id;
	}
	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}
	public String getMedia_type() {
		return media_type;
	}
	public void setMedia_type(String media_type) {
		this.media_type = media_type;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	@Override
	public String toString() {
		return "PostMultimedia [id=" + id + ", post_id=" + post_id + ", media_type=" + media_type + ", path=" + path
				+ "]";
	}
	
	
	
	
}
