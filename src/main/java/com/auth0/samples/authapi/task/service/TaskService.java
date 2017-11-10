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
// all the logics for the controllers
@Service
public class TaskService {

	@Autowired
	TaskRepository taskRepository;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	UserRepository userRepository;

	// get the username from the JWT and set it as the username in the task model
	public void taskSetTaskGiver(Task task, HttpServletRequest request) {
		task
				.setTaskGiver(Jwts.parser()
						.setSigningKey(SECRET.getBytes())
						.parseClaimsJws(request.getHeader(HEADER_STRING).replace(TOKEN_PREFIX, ""))
						.getBody()
						.getSubject());
	}

	public List<Task> getTasks() {
		return taskRepository.findAll();
	}

	public Task editTask(long id, Task task) {
		Task taskModified = taskRepository.findOne(id);
		taskModified.setDescription(task.getDescription());
		return taskRepository.save(taskModified);
	}

	public Task deleteTask(long id) {
		Task deletedTask = taskRepository.findOne(id);
		taskRepository.delete(id);
		return deletedTask;
	}

	public boolean sanityCheckTaskExist(long id) {
		if (taskRepository.findOne(id) != null) {
			return true;
		}
		return false;
	}

	public boolean sanityCheckValidTaskDescription(Task task) {
		if (task.getDescription() == null || task.getDescription().equals("")) {
			return false;
		}
		return true;
	}


}
