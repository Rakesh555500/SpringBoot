/**
 * 
 */
package com.sogeti.api.filmland.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sogeti.api.filmland.model.Category;
import com.sogeti.api.filmland.repository.CategoryRepository;
import com.sogeti.api.filmland.service.CategoryService;

/**
 * Service class for category
 * 
 * @author Rakesh
 *
 */
@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Category findCategoryByName(String name) {
		return categoryRepository.findCategoryByName(name);
	}
}
