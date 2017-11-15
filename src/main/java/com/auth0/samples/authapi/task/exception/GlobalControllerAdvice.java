package com.auth0.samples.authapi.task.exception;

import com.auth0.samples.authapi.task.exception.InvalidInputException;
import com.auth0.samples.authapi.task.exception.UsernameAlreadyTakenException;
import com.auth0.samples.authapi.task.model.ApiErrorResponse;
import com.auth0.samples.authapi.task.model.AppUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Thomas Leruth on 11/11/17
 */

/**
 * Class to globally catch the error thrown by the controller
 */
@ControllerAdvice
public class GlobalControllerAdvice {

	/**
	 * Advice for handling custom InvalidInputException and setting up the body of that error thanks to "@ResponseBody"
	 *
	 * @param iE The exception received from the service
	 * @return ResponseEntity showing an custom ApiErrorObject + correct response code
	 */
	@ResponseBody
	@ExceptionHandler(InvalidInputException.class)
	public ResponseEntity InvalidInputHandler(InvalidInputException iE) {
		HttpStatus hS = HttpStatus.valueOf(400);
		String message = "this request could not processed be done due to invalid inputs";
		ApiErrorResponse aE = new ApiErrorResponse(hS, iE.getExceptionName(), message, iE.getGivenObject());
		return new ResponseEntity<>(aE, hS);
	}

	/**
	 * Similar for UsernameAlreadyTakenException
	 * @param uE The exception received from the service
	 * @return ReponseEntity
	 */
	@ResponseBody
	@ExceptionHandler(UsernameAlreadyTakenException.class)
	public ResponseEntity UsernameAlreadyTakenHandler(UsernameAlreadyTakenException uE) {
		HttpStatus hS = HttpStatus.valueOf(400);
		// Well I did not expect that crazy casting to work ;D
		String message = "the username: '" + ((AppUser) uE.getGivenObject()).getUsername() + "' is already taken";
		ApiErrorResponse aE = new ApiErrorResponse(hS, uE.getExceptionName(), message, uE.getGivenObject());
		return new ResponseEntity<>(aE, hS);
	}
}
