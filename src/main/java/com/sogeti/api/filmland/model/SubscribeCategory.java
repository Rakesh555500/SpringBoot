package com.sogeti.api.filmland.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * @author Rakesh
 *
 */
@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class SubscribeCategory {
	@Id
	@GeneratedValue
	@JsonIgnore
	private Long id;

	@OneToOne
	@JsonIgnoreProperties({ "id", "availableContent" })
	private Category category;
	private int remainingContent;
	private Date startDate;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private UserInfo userinfo;

	@JsonIgnore
	private int sharedWith = 0;

	/**
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

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
	 * @return the userinfo
	 */
	public UserInfo getUserinfo() {
		return userinfo;
	}

	/**
	 * @param userinfo the userinfo to set
	 */
	public void setUserinfo(UserInfo userinfo) {
		this.userinfo = userinfo;
	}

	/**
	 * @return the remainingContent
	 */
	public int getRemainingContent() {
		return remainingContent;
	}

	/**
	 * @param remainingContent the remainingContent to set
	 */
	public void setRemainingContent(int remainingContent) {
		this.remainingContent = remainingContent;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the sharedWith
	 */
	public int getSharedWith() {
		return sharedWith;
	}

	/**
	 * @param sharedWith the sharedWith to set
	 */
	public void setSharedWith(int sharedWith) {
		this.sharedWith = sharedWith;
	}
}