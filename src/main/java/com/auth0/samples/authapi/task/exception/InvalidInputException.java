package com.auth0.samples.authapi.task.exception;

import jdk.nashorn.internal.objects.annotations.Getter;

/**
 * Created by Thomas Leruth on 11/11/17
 */
// to pass the object
public class InvalidInputException extends RuntimeException {

	private Object givenObject;
	private String ExceptionName;

	public InvalidInputException(Object givenObject) {
		this.givenObject = givenObject;
		this.ExceptionName = getClass().getSimpleName();
	}

	public Object getGivenObject() {
		return givenObject;
	}

	public void setGivenObject(Object givenObject) {
		this.givenObject = givenObject;
	}

	public String getExceptionName() {
		return ExceptionName;
	}

	public void setExceptionName(String exceptionName) {
		ExceptionName = exceptionName;
	}
}
