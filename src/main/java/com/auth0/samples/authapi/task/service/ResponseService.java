package com.auth0.samples.authapi.task.service;

import com.auth0.samples.authapi.task.model.Response;
import org.springframework.stereotype.Service;

/**
 * Created by Thomas Leruth on 11/9/17
 */

// Nice response handler logics
@Service
public class ResponseService {

	Response response = new Response();

	public Response responseSuccess(Object object){
		response.setStatus(200);
		response.setMessage("Success");
		response.setObject(object);
		return response;
	}

	public Response responseFailure() {
		response.setStatus(400);
		response.setMessage("Failure");
		response.setObject(null);
		return response;
	}

	public Response responseFailure(String message) {
		response.setStatus(400);
		response.setMessage(message);
		response.setObject(null);
		return response;
	}
}
