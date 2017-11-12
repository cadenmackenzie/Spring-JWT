package com.auth0.samples.authapi.task.controller;

import jdk.nashorn.internal.objects.annotations.Getter;

/**
 * Created by Thomas Leruth on 11/11/17
 */
// to pass the object
public class InvalidInputException extends RuntimeException {

	private Object givenObject;

	public InvalidInputException(Object givenObject) {
		this.givenObject = givenObject;
	}

	public Object getGivenObject() {
		return givenObject;
	}

	public void setGivenObject(Object givenObject) {
		this.givenObject = givenObject;
	}
}
