package com.sogeti.api.filmland.repository;

import org.springframework.data.repository.CrudRepository;

import com.sogeti.api.filmland.model.UserInfo;

/**
 * 
 * @author C28278
 *
 */
public interface UserDetailsRepository extends CrudRepository<UserInfo, Long> {
	UserInfo findByUsername(String username);
	
}