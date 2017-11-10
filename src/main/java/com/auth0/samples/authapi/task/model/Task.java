package com.auth0.samples.authapi.task.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Thomas Leruth on 11/9/17
 */

// Same as Appuser in the logic
@Entity
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String description;
	private String taskGiver;

	protected Task() { }

	public Task(String description) {
		this.description = description;
	}

	public long getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTaskGiver() {
		return taskGiver;
	}

	public void setTaskGiver(String taskGiver) {
		this.taskGiver = taskGiver;
	}
}
