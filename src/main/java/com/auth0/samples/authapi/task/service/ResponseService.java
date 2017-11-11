package com.auth0.samples.authapi.task.service;

import com.auth0.samples.authapi.task.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * Created by Thomas Leruth on 11/9/17
 */

// Nice response handler logics
@Service
public class ResponseService {

	Response response = new Response();

	public Response responseSuccess(Object object, int statusCode){
		response.setStatus(statusCode);
		response.setMessage(HttpStatus.valueOf(statusCode).getReasonPhrase());
		response.setObject(object);
		return response;
	}

	public Response responseFailure(int statusCode) {
		response.setStatus(statusCode);
		response.setMessage(HttpStatus.valueOf(statusCode).getReasonPhrase());
		response.setObject(null);
		return response;
	}

	public Response responseFailure(String message, int statusCode) {
		response.setStatus(statusCode);
		response.setMessage(message);
		response.setObject(null);
		return response;
	}
}
