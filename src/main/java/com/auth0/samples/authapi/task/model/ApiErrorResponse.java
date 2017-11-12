package com.auth0.samples.authapi.task.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * Created by Thomas Leruth on 11/11/17 modelled worked from brunocleite@Github
 */

public class ApiErrorResponse {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;
	private HttpStatus status;
	private String userMessage;
	private Object givenObject;

	public ApiErrorResponse(HttpStatus status, String userMessage, Object givenObject) {
		timestamp = LocalDateTime.now();
		this.status = status;
		this.userMessage = userMessage;
		this.givenObject = givenObject;
	}

	public ApiErrorResponse(HttpStatus status, String userMessage) {
		timestamp = LocalDateTime.now();
		this.status = status;
		this.userMessage = userMessage;
		this.givenObject = null;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getUserMessage() {
		return userMessage;
	}

	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}

	public Object getGivenObject() {
		return givenObject;
	}

	public void setGivenObject(Object givenObject) {
		this.givenObject = givenObject;
	}
}
