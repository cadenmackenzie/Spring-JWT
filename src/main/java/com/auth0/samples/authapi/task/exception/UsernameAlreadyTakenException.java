package com.auth0.samples.authapi.task.exception;

/**
 * Created by Thomas Leruth on 11/11/17
 */

/**
 * Custom Exception when the Username is already Taken (RunTimeException)
 * Improvement: Use @Data or @Getter & @Setter from project Lombok
 */
public class UsernameAlreadyTakenException extends RuntimeException {

	private Object givenObject;
	private String ExceptionName;

	public UsernameAlreadyTakenException(Object givenObject) {
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
