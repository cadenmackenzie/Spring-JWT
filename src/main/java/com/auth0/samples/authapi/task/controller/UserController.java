package com.auth0.samples.authapi.task.controller;


import com.auth0.samples.authapi.task.model.Response;
import com.auth0.samples.authapi.task.repository.UserRepository;
import com.auth0.samples.authapi.task.service.ResponseService;
import com.auth0.samples.authapi.task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	@RequestMapping(value = {"/signup"}, method = RequestMethod.POST)
	public Response signUp (@RequestBody AppUser appUser) {
		if (!userService.sanityCheckGivenData(appUser)) {
			return responseService.responseFailure("Data provided are invalid");
		}
		if (!userService.SanityCheckUserExist(appUser.getUsername())) {
			return responseService.responseFailure("Username already exist");
		}
		userService.signUp(appUser);
		return responseService.responseSuccess(appUser);
	}
}

