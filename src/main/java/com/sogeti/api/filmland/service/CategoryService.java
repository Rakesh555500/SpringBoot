package com.sogeti.api.filmland.service;

import com.sogeti.api.filmland.model.Category;

/**
 * 
 * @author C28278
 *
 */
public interface CategoryService {

	public Category findCategoryByName(String name);
}