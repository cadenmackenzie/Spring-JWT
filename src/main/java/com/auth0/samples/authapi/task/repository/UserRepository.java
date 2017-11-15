package com.auth0.samples.authapi.task.repository;

import com.auth0.samples.authapi.task.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Thomas Leruth on 11/9/17
 */

/**
 * Repo using for the Tasks creating a bean and extending JpaRepository to have some functions (like save...)
 */
@Repository
// Repo extendings JPA to give more ability (saving, etc...)
public interface UserRepository extends JpaRepository<AppUser, Long> {

	AppUser findByUsername(String username);
}
