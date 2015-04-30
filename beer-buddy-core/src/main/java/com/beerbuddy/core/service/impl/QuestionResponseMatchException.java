package com.beerbuddy.core.service.impl;


public class QuestionResponseMatchException extends Exception {

	private static final long serialVersionUID = 1L;

	public QuestionResponseMatchException(String username, String question, String response){
		super("Question and response don't match");
	}
	
	public QuestionResponseMatchException(String username, String question, String response, Throwable throwable) {
		super("Question and response don't match", throwable);
	}
	
}