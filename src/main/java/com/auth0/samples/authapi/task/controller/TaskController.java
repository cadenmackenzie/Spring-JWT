package com.auth0.samples.authapi.task.controller;

import com.auth0.samples.authapi.task.model.Response;
import com.auth0.samples.authapi.task.model.Task;
import com.auth0.samples.authapi.task.repository.TaskRepository;
import com.auth0.samples.authapi.task.service.ResponseService;
import com.auth0.samples.authapi.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Thomas Leruth on 11/9/17
 */

//Class with the controllers for tasks, using sanity check and response giver, logic is in the services
@RestController
@RequestMapping("/tasks")
public class TaskController {

	@Autowired
	ResponseService responseService;

	@Autowired
	TaskService taskService;

	@Autowired
	TaskRepository taskRepository;

	@RequestMapping(method = RequestMethod.POST)
	public Response addTask(@RequestBody Task task, HttpServletRequest request) {
		if (taskService.sanityCheckValidTaskDescription(task)) {
			taskService.taskSetTaskGiver(task, request);
			return responseService.responseSuccess(taskRepository.save(task));
		}
		return responseService.responseFailure("Invalid task description");
	}

	@RequestMapping(method = RequestMethod.GET)
	public Response getTasks() {
		return responseService.responseSuccess(taskService.getTasks());
	}

	@RequestMapping(value = {"/{id}"}, method = RequestMethod.PUT)
	public Response editTask(@PathVariable long id, @RequestBody Task task) {
		if (!taskService.sanityCheckTaskExist(id)) {
			return responseService.responseFailure("No task with the given id");
		}
		if (!taskService.sanityCheckValidTaskDescription(task)) {
			return responseService.responseFailure("Invalid task description");
		}
		return responseService.responseSuccess(taskService.editTask(id, task));
	}

	// Ryan please show me with ResponseEntity<> how it would look like and how it differs in term of result
	// is that where you want to go with try/catch?
	@RequestMapping(value = {"/{id}"}, method = RequestMethod.DELETE)
	public Response deleteTask(@PathVariable long id) {
		try {
			if (!taskService.sanityCheckTaskExist(id)) {
				return responseService.responseFailure("No task with the given id");
			}
			return responseService.responseSuccess(taskService.deleteTask(id));
		}
		catch (Exception x) {
			return responseService.responseFailure(x.toString());
		}

	}
}

