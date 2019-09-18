package com.sogeti.api.filmland.model;

import java.util.List;

/**
 * 
 * @author Rakesh
 *
 */
public class SubscribedCategoriesDto {
	private List<Category> availableCategories;
	private List<SubscribedCategoryDto> subscribedCategories;

	/**
	 * @return the availableCategories
	 */
	public List<Category> getAvailableCategories() {
		return availableCategories;
	}

	/**
	 * @param availableCategories the availableCategories to set
	 */
	public void setAvailableCategories(List<Category> availableCategories) {
		this.availableCategories = availableCategories;
	}

	/**
	 * @return the subscribedCategories
	 */
	public List<SubscribedCategoryDto> getSubscribedCategories() {
		return subscribedCategories;
	}

	/**
	 * @param subscribedCategories the subscribedCategories to set
	 */
	public void setSubscribedCategories(List<SubscribedCategoryDto> subscribedCategories) {
		this.subscribedCategories = subscribedCategories;
	}
}
