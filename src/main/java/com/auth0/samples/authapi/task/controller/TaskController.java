package com.auth0.samples.authapi.task.controller;

import com.auth0.samples.authapi.task.model.Response;
import com.auth0.samples.authapi.task.model.Task;
import com.auth0.samples.authapi.task.repository.TaskRepository;
import com.auth0.samples.authapi.task.service.ResponseService;
import com.auth0.samples.authapi.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Thomas Leruth on 11/9/17
 */

/**
 *  Controllers for the /task end points
 */
@RestController
@RequestMapping("/tasks")
public class TaskController {

	@Autowired
	ResponseService responseService;

	@Autowired
	TaskService taskService;

	@Autowired
	TaskRepository taskRepository;

	Response response;// = new Response();

	/**
	 * Post Controller to make a new task using a sanity check directly
	 * @param task POJO class
	 * @param request to get the JWT token (to use for setting the task giver)
	 * @return ResponseEntity which allows the set HTTP response code correctly and returning the posted object
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity addTask(@RequestBody Task task, HttpServletRequest request) {
		response = new Response();
		if (taskService.sanityCheckValidTaskDescription(task)) {
			taskService.taskSetTaskGiver(task, request);
			response = responseService.responseSuccess(taskRepository.save(task), 201);
		}
		else {
			response = responseService.responseFailure("Invalid task description", 400);
		}
		return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
	}

	/**
	 * Similar as above return the list of tasks
	 * @return ResponseEntity
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity getTasks() {
		response = responseService.responseSuccess(taskService.getTasks(),200);
		return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
	}

	/**
	 * Put Controller to mofify a specific task using sanity check
	 * @param id id of the task to change
	 * @param task new task to use as modifier
	 * @return ResponseEntity
	 */
	@RequestMapping(value = {"/{id}"}, method = RequestMethod.PUT)
	public ResponseEntity editTask(@PathVariable long id, @RequestBody Task task) {
		if (!taskService.sanityCheckTaskExist(id)) {
			response = responseService.responseFailure("No task with the given id",400);
		}
		if (!taskService.sanityCheckValidTaskDescription(task)) {
			response =  responseService.responseFailure("Invalid task description",400);
		}
		else {
			response =  responseService.responseSuccess(taskService.editTask(id, task),200);
		}
		return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
	}

	/**
	 * Delete Controller to delete a specific task
	 * @param id id of the task to delete
	 * @return ResponseEntity
	 */
	@RequestMapping(value = {"/{id}"}, method = RequestMethod.DELETE)
	public ResponseEntity deleteTask(@PathVariable long id) {
			if (!taskService.sanityCheckTaskExist(id)) {
				response = responseService.responseFailure("No task with the given id", 418);
			}
			else {
				response = responseService.responseSuccess(taskService.deleteTask(id), 200);
			}
		return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
	}
}
