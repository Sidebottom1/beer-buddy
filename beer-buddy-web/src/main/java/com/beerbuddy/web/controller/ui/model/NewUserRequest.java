package com.beerbuddy.web.controller.ui.model;


public class NewUserRequest {

    protected String username;
    protected String password;
    protected String name;
    protected String email;
    protected String question;
    protected String response;

    public String getEmail() {
		return email;
	}
    public void setEmail(String email) {
		this.email = email;
	}
    public String getName() {
		return name;
	}
    public void setName(String name) {
		this.name = name;
	}
    public String getPassword() {
		return password;
	}
    public void setPassword(String password) {
		this.password = password;
	}
    public String getUsername() {
		return username;
	}
    public void setUsername(String username) {
		this.username = username;
	}
    public void setQuestion(String question) {
		this.question = question;
	}
    public void setResponse(String response) {
		this.response = response;
	}
}
