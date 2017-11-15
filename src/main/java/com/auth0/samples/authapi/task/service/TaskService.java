package com.auth0.samples.authapi.task.service;

import com.auth0.samples.authapi.task.model.Task;
import com.auth0.samples.authapi.task.repository.TaskRepository;
import com.auth0.samples.authapi.task.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static com.auth0.samples.authapi.task.util.SecurityConstant.*;

/**
 * Created by Thomas Leruth on 11/9/17
 */

/**
 * Main services powering the task endpoint
 */
@Service
public class TaskService {

	@Autowired
	TaskRepository taskRepository;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	UserRepository userRepository;


	/**
	 * Method to read from the JWT the task giver and set it in the task POJO
	 * @param task task given
	 * @param request info from http
	 */
	public void taskSetTaskGiver(Task task, HttpServletRequest request) {
		task
				.setTaskGiver(Jwts.parser()
						.setSigningKey(SECRET.getBytes())
						.parseClaimsJws(request.getHeader(HEADER_STRING).replace(TOKEN_PREFIX, ""))
						.getBody()
						.getSubject());
	}

	/**
	 * Get all tasks
	 * @return
	 */
	public List<Task> getTasks() {
		return taskRepository.findAll();
	}

	public Task editTask(long id, Task task) {
		Task taskModified = taskRepository.findOne(id);
		taskModified.setDescription(task.getDescription());
		return taskRepository.save(taskModified);
	}

	/**
	 * delete selected task
	 * @param id
	 * @return
	 */
	public Task deleteTask(long id) {
		Task deletedTask = taskRepository.findOne(id);
		taskRepository.delete(id);
		return deletedTask;
	}

	/**
	 * Sanity check to see if given ID exist
	 * @param id
	 * @return
	 */
	public boolean sanityCheckTaskExist(long id) {
		if (taskRepository.findOne(id) != null) {
			return true;
		}
		return false;
	}

	/**
	 * Sanity check for valid description
	 * @param task
	 * @return
	 */
	public boolean sanityCheckValidTaskDescription(Task task) {
		if (task.getDescription() == null || task.getDescription().equals("")) {
			return false;
		}
		return true;
	}


}
