package com.sogeti.api.filmland.model;

/**
 * 
 * @author Rakesh
 *
 */
public class SubsciptionRequest {
	private String email;
	private String availableCategory;

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the availableCategory
	 */
	public String getAvailableCategory() {
		return availableCategory;
	}

	/**
	 * @param availableCategory the availableCategory to set
	 */
	public void setAvailableCategory(String availableCategory) {
		this.availableCategory = availableCategory;
	}
}