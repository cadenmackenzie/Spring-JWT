package com.auth0.samples.authapi.task.repository;

import com.auth0.samples.authapi.task.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Thomas Leruth on 11/9/17
 */

// Repo extending JPA to give more ability (saving, etc...)
public interface TaskRepository extends JpaRepository<Task, Long> {
}
