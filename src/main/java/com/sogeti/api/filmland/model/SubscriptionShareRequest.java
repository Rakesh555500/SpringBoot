package com.sogeti.api.filmland.model;

/**
 * 
 * @author Rakesh
 *
 */
public class SubscriptionShareRequest {
	private String email;
	private String customer;
	private String subscribedCategory;

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
	 * @return the customer
	 */
	public String getCustomer() {
		return customer;
	}
	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	/**
	 * @return the subscribedCategory
	 */
	public String getSubscribedCategory() {
		return subscribedCategory;
	}
	/**
	 * @param subscribedCategory the subscribedCategory to set
	 */
	public void setSubscribedCategory(String subscribedCategory) {
		this.subscribedCategory = subscribedCategory;
	}
}
