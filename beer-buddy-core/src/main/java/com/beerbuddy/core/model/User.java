package com.beerbuddy.core.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface User {

	public String getUsername();
	
	public Date getLastLogin();
	
	public String getName();
	
	public String getEmail();

	public String getQuestion();

	public String getResponse();

	public void setPassword(String password);
	@JsonIgnore
	public Long getProfileId();
	
	public void setProfile(UserProfile profile);

}