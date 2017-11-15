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

	/**
	 * The service powering responses object
	 * @param object Very broad as I want to be able to take any object as body
	 * @param statusCode the code to set the page on
	 * @return Return the modified response with more info
	 */
	public Response responseSuccess(Object object, int statusCode){
		response.setStatus(statusCode);
		response.setMessage(HttpStatus.valueOf(statusCode).getReasonPhrase());
		response.setObject(object);
		return response;
	}

	/**
	 * Similar as above but withotu object
	 * @param statusCode
	 * @return
	 */
	public Response responseFailure(int statusCode) {
		response.setStatus(statusCode);
		response.setMessage(HttpStatus.valueOf(statusCode).getReasonPhrase());
		response.setObject(null);
		return response;
	}

	/**
	 * Overloaded constructor in case I want to return a specific message
	 * @param message
	 * @param statusCode
	 * @return
	 */
	public Response responseFailure(String message, int statusCode) {
		response.setStatus(statusCode);
		response.setMessage(message);
		response.setObject(null);
		return response;
	}
}
