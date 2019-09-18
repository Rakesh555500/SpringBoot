package com.sogeti.api.filmland.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.sogeti.api.filmland.exception.UserExistsException;
import com.sogeti.api.filmland.model.UserInfo;

/**
 * 
 * @author C28278
 *
 */
public interface FilmLandUserService extends UserDetailsService{
	UserInfo registerNewUser(UserInfo registerUser) throws UserExistsException;

	UserInfo findUserByUsername(String username);

	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}