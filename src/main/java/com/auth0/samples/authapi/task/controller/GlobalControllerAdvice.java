package com.auth0.samples.authapi.task.controller;

import com.auth0.samples.authapi.task.model.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Thomas Leruth on 11/11/17
 */

//can set the targeting basepackage, annotation, etc
@ControllerAdvice
public class GlobalControllerAdvice {

	@ResponseBody
	@ExceptionHandler(InvalidInputException.class)
	public ResponseEntity InvalidInputHandler(InvalidInputException iE) {
		HttpStatus hS = HttpStatus.valueOf(400);
		String message = "this request could not processed be done due to invalid inputs";
		ApiErrorResponse aE = new ApiErrorResponse(hS,message, iE.getGivenObject());
		return new ResponseEntity<>(aE, hS);
	}
}
