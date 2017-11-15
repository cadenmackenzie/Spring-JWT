package com.auth0.samples.authapi.task.service;

/**
 * Created by Thomas Leruth on 11/9/17
 */

import com.auth0.samples.authapi.task.exception.InvalidInputException;
import com.auth0.samples.authapi.task.exception.UsernameAlreadyTakenException;
import com.auth0.samples.authapi.task.model.AppUser;
import com.auth0.samples.authapi.task.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

/**
 * Logic for the /user controller
 */
@Service
public class UserService implements UserDetailsService {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	//check if user exists and return its details if so

	/**
	 * Check if the user exists and return its details if so
	 * @param username the username given
	 * @return The use details
	 * @throws UsernameNotFoundException
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser appUser = userRepository.findByUsername(username);
		if (appUser == null) {
			throw new UsernameNotFoundException(username);
		}
		return new User(appUser.getUsername(), appUser.getPassword(), emptyList());
	}

	/**
	 * Sign up service with the custom exceptions
	 * @param appUser
	 */
	public void signUp(AppUser appUser) {
		if (!sanityCheckGivenData(appUser)) {
			throw new InvalidInputException(appUser);
		}
		if (!SanityCheckUserExist(appUser.getUsername())) {
			throw new UsernameAlreadyTakenException(appUser);
		}
		appUser.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));
		userRepository.save(appUser);
	}

	/**
	 * Sanity check to see if the username already exist
	 * @param username
	 * @return
	 */
	private boolean SanityCheckUserExist(String username)  {
		if (userRepository.findByUsername(username) == null) {
			return true;
		}
		return false;
	}

	/**
	 * Sanity check to see if the data given are good
	 * @param appuser
	 * @return
	 */
	private boolean sanityCheckGivenData(AppUser appuser) {
		if (appuser.getUsername() == null || appuser.getUsername().equals("") ||
		appuser.getPassword().equals("") || appuser.getPassword() == null) {
			return false;
		}
		return true;
	}
}
