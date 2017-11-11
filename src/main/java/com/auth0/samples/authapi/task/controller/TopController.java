package com.auth0.samples.authapi.task.controller;

import com.auth0.samples.authapi.task.model.Response;
import com.auth0.samples.authapi.task.service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Thomas Leruth on 11/9/17
 */

// useless for now but Ryan question in there
@RestController
public class TopController {

	@Autowired
	ResponseService responseService;

	// RYAAAAAANNNNNNNNN :D
	// Please disregard the body, at the moment this end point is handled by something else (Could trace it up to
	// doFilterInternal. I do not like this end point as it returns nothing (just updating the header)
	// wanted to make it nice and take the header and put it into a response but I can't control this endpoint sadly
	@RequestMapping(value = {"/login"}, method = RequestMethod.POST)
	public Response login() {
		return responseService.responseFailure(400);
	}
}
