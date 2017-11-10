package com.auth0.samples.authapi.task.service;

/**
 * Created by Thomas Leruth on 11/9/17
 */

import com.auth0.samples.authapi.task.model.AppUser;
import com.auth0.samples.authapi.task.repository.TaskRepository;
import com.auth0.samples.authapi.task.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

//logic for the controllers
@Service
public class UserService implements UserDetailsService {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	//check if user exists and return its details if so
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser appUser = userRepository.findByUsername(username);
		if (appUser == null) {
			throw new UsernameNotFoundException(username);
		}
		return new User(appUser.getUsername(), appUser.getPassword(), emptyList());
	}

	public void signUp(AppUser appUser) {
		appUser.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));
		userRepository.save(appUser);
	}

	public boolean SanityCheckUserExist(String username)  {
		if (userRepository.findByUsername(username) == null) {
			return true;
		}
		return false;
	}

	public boolean sanityCheckGivenData(AppUser appuser) {
		if (appuser.getUsername() == null || appuser.getUsername().equals("") ||
		appuser.getPassword().equals("") || appuser.getPassword() == null) {
			return false;
		}
		return true;
	}
}
