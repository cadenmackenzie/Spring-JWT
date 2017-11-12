package com.auth0.samples.authapi.task.model;


/**
 * Created by Thomas Leruth on 11/9/17
 */

// Used for proper response from controllers
public class Response {

	String message;
	int status;
	Object object;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}
}
