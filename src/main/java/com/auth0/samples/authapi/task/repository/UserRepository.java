package com.auth0.samples.authapi.task.repository;

import com.auth0.samples.authapi.task.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Thomas Leruth on 11/9/17
 */

// Repo extendings JPA to give more ability (saving, etc...)
public interface UserRepository extends JpaRepository<AppUser, Long> {

	AppUser findByUsername(String username);
}
