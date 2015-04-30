package com.beerbuddy.web.controller.ui.model;

public class PasswordResetRequest {
	
    protected String username;
    protected String email;
    protected String question;
    protected String response;
    protected String password;

    public String getEmail() {
		return email;
	}
    public String getUsername() {
		return username;
	}
    public void setUsername(String username) {
		this.username = username;
	}
    
    public String getQuestion() {
		return question;
	}
    public String getResponse() {
		return response;
	}
    public String getPassword() {
		return password;
	}
    
    public void setPassword(String password) {
		this.password = password;
	}
    
    

}
