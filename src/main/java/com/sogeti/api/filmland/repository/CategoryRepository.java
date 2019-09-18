package com.sogeti.api.filmland.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sogeti.api.filmland.model.Category;

/**
 * 
 * @author C28278
 *
 */
public interface CategoryRepository extends CrudRepository<Category, Long> {
	@Query("SELECT c FROM Category c WHERE c.name = :name")
	public Category findCategoryByName(String name);
}
