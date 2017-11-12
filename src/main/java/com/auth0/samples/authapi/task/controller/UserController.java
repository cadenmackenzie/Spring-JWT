package com.auth0.samples.authapi.task.controller;


import com.auth0.samples.authapi.task.exception.InvalidInputException;
import com.auth0.samples.authapi.task.exception.UsernameAlreadyTakenException;
import com.auth0.samples.authapi.task.model.Response;
import com.auth0.samples.authapi.task.service.ResponseService;
import com.auth0.samples.authapi.task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.auth0.samples.authapi.task.model.AppUser;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Thomas Leruth on 11/9/17
 */

// Controllers for users end point with sanity check and response maker
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	ResponseService responseService;

	// two sanity check, one for input, second to check it the username is already taken with proper message displaying
	// both using try/catch with Exception handler using ResponseEntity
	// Ryan why is exception better? Because I can controll pre-made ones already + the controller has less logic?
	@RequestMapping(value = {"/signup"}, method = RequestMethod.POST)
	public ResponseEntity signUp (@RequestBody AppUser appUser) {
		try {
			userService.signUp(appUser);
		}
		catch (InvalidInputException | UsernameAlreadyTakenException eE) {
			throw eE;
		}
		Response response = responseService.responseSuccess(appUser,201);
		return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
	}
}
