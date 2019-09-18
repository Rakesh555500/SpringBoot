package com.sogeti.api.filmland.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * @author Rakesh
 *
 */
@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class UserInfo {
	@Id
	@GeneratedValue
	@JsonIgnore
	private Long id;
	private String username;
	private String password;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userinfo")
	@JsonIgnore
	private List<SubscribeCategory> subscribedCategories;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the subscribedCategories
	 */
	public List<SubscribeCategory> getSubscribedCategories() {
		return subscribedCategories;
	}

	/**
	 * @param subscribedCategories the subscribedCategories to set
	 */
	public void setSubscribedCategories(List<SubscribeCategory> subscribedCategories) {
		this.subscribedCategories = subscribedCategories;
	}
}