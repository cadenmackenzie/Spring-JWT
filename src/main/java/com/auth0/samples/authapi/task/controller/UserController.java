package com.auth0.samples.authapi.task.controller;


import com.auth0.samples.authapi.task.exception.InvalidInputException;
import com.auth0.samples.authapi.task.exception.UsernameAlreadyTakenException;
import com.auth0.samples.authapi.task.model.Response;
import com.auth0.samples.authapi.task.service.ResponseService;
import com.auth0.samples.authapi.task.service.UserService;
import org.omg.CORBA.DynAnyPackage.Invalid;
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

/**
 * Controllers for the /users end point
 */
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	ResponseService responseService;


	/**
	 * Class to post a new user using custom exception being thrown by the services which have custom sanity checks
	 * @param appUser Pojo class
	 * @return ResponseEntity with a custom Response
	 * @throws InvalidInputException custom exception caught by the GlobalControllerAdvise (notice that it is not thrown
	 * at the method level because it is a RunTimeException
	 * @throws UsernameAlreadyTakenException similar as the other Exception
	 */
	@RequestMapping(value = {"/signup"}, method = RequestMethod.POST)
	public ResponseEntity signUp (@RequestBody AppUser appUser) throws InvalidInputException, UsernameAlreadyTakenException {
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
