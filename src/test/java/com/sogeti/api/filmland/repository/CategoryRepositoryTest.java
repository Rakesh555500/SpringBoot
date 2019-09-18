package com.sogeti.api.filmland.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sogeti.api.filmland.model.Category;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryRepositoryTest {

	@Autowired
	private CategoryRepository categoryRepository;

	@Test
	public void whenFindByName_thenReturnCategory() {
		Category found = categoryRepository.findCategoryByName("Dutch Films");
		assertNotNull("Category searched is not present", found);
		assertThat(found.getName()).isEqualTo("Dutch Films");
	}
	
	@Test
	public void whenFindByName_thenReturnNull() {
		Category found = categoryRepository.findCategoryByName("Indian Films");
		assertNull("Category searched is present", found);
	}
}
