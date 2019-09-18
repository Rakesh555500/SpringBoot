package com.sogeti.api.filmland.service.impl;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sogeti.api.filmland.exception.UserExistsException;
import com.sogeti.api.filmland.model.UserInfo;
import com.sogeti.api.filmland.repository.UserDetailsRepository;
import com.sogeti.api.filmland.service.FilmLandUserService;

/**
 * Service class for users
 * 
 * @author Rakesh
 *
 */
@Service
public class FilmLandUserServiceImpl implements FilmLandUserService {
	@Autowired
	private UserDetailsRepository repository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserInfo user = findUserByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				Collections.emptyList());
	}

	@Override
	public UserInfo registerNewUser(UserInfo registerUser) throws UserExistsException {
		if (isUserExists(registerUser.getUsername())) {
			throw new UserExistsException("User with email " + registerUser.getUsername() + " already exist");
		}
		UserInfo userToBeRegistered = new UserInfo();
		userToBeRegistered.setUsername(registerUser.getUsername());
		userToBeRegistered.setPassword(passwordEncoder.encode(registerUser.getPassword()));
		return repository.save(userToBeRegistered);
	}

	boolean isUserExists(String username) {
		return repository.findByUsername(username) != null;
	}

	@Override
	public UserInfo findUserByUsername(String username) {
		return repository.findByUsername(username);
	}
}