package com.twitter.clone.helpers;

import java.util.List;

public class ServerMessage {

	public ServerMessage() {
		
	}
		

	public ServerMessage(List<String> message, String status, String css) {
		super();
		this.message = message;
		this.status = status;
		this.css = css;
	}


	private List<String> message;
	private String status;
	private String css;
	public List<String> getMessage() {
		return message;
	}


	public void setMessage(List<String> message) {
		this.message = message;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getCss() {
		return css;
	}


	public void setCss(String css) {
		this.css = css;
	}
	
	
	
	
}
