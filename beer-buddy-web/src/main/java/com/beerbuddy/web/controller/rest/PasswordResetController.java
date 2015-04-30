package com.beerbuddy.web.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.beerbuddy.core.model.User;
import com.beerbuddy.core.service.UserService;
import com.beerbuddy.core.service.impl.QuestionResponseMatchException;
import com.beerbuddy.web.controller.ui.model.BeerMapper;
import com.beerbuddy.web.controller.ui.model.PasswordResetRequest;

@RestController
@RequestMapping("/users")
public class PasswordResetController implements BeerMapper {

	@Autowired
	UserService userService;
	
	@Description("This will update a user. If a username not found, then a bad request response will be thrown")
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<?> update(@RequestBody PasswordResetRequest request) {
		try {
			User user = userService.updateUser(request.getUsername(), request.getQuestion(), request.getResponse(), request.getPassword());
			//UserProfile profile = new UserProfile();
			//profile.setPassword(request.getPassword());
			user.setPassword(request.getPassword());
			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch(QuestionResponseMatchException e) {/*
			Map<String, String> error = ImmutableMap.
				of("error", "username_dont_exits", 
					"message", "Username is invalid: " + request.getUsername());
			return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		*/
			return null;
			}
		//TODO: add an exception for bad passwords...
	}

	

}