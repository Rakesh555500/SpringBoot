package com.sogeti.api.filmland.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.doReturn;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.sogeti.api.filmland.model.Category;
import com.sogeti.api.filmland.repository.CategoryRepository;
import com.sogeti.api.filmland.service.impl.CategoryServiceImpl;

/**
 * 
 * @author Rakesh
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTest {
	@InjectMocks
	private CategoryServiceImpl categoryService;

	@Mock
	private CategoryRepository categoryRepository;

	@Test
	public void whenFindByName_thenReturnCategory() {
		Category category = new Category();
		category.setId(1L);
		category.setName("Dutch Films");
		category.setPrice(10.0);
		category.setAvailableContent(10);

		doReturn(category).when(categoryRepository).findCategoryByName("Dutch Films");

		Category found = categoryService.findCategoryByName("Dutch Films");
		assertNotNull("Category searched is not present", found);
		assertThat(found.getName()).isEqualTo("Dutch Films");
	}

	@Test
	public void whenFindByName_thenReturnNull() {
		Category found = categoryService.findCategoryByName("Indian Films");
		assertNull("Category searched is present", found);
	}
}
