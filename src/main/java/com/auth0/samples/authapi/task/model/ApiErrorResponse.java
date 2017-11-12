package com.auth0.samples.authapi.task.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * Created by Thomas Leruth on 11/11/17 modelled worked from brunocleite@Github
 */
@JsonPropertyOrder({"timestamp", "status", "exceptionName", "userMessage", "givenObject"})
public class ApiErrorResponse {


	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;
	private HttpStatus status;
	private String exceptionName;
	private String userMessage;
	private Object givenObject;

	public ApiErrorResponse(HttpStatus status, String exceptionName, String userMessage, Object givenObject) {
		timestamp = LocalDateTime.now();
		this.status = status;
		this.exceptionName = exceptionName;
		this.userMessage = userMessage;
		this.givenObject = givenObject;
	}

	public ApiErrorResponse(HttpStatus status, String exceptionName, String userMessage) {
		timestamp = LocalDateTime.now();
		this.status = status;
		this.exceptionName = exceptionName;
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

	public String getExceptionName() {
		return exceptionName;
	}

	public void setExceptionName(String exceptionName) {
		this.exceptionName = exceptionName;
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
