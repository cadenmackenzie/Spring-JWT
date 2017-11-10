package com.auth0.samples.authapi.task.configuration;

import com.auth0.samples.authapi.task.repository.TaskRepository;
import com.auth0.samples.authapi.task.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Thomas Leruth on 11/9/17
 */
@Configuration
public class RepositoryConfig {

	TaskRepository taskRepository;

	UserRepository userRepository;

	@Bean
	public TaskRepository taskRepository() {
		return taskRepository;
	}

	@Bean
	public UserRepository userRepository() {
		return userRepository;
	}
}
