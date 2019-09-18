package com.sogeti.api.filmland.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sogeti.api.filmland.model.UserInfo;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserDetailsRepositoryTest {

	@Autowired
	private UserDetailsRepository userDetailsRepository;

	@Test
	public void whenFindByName_thenReturnCategory() {
		UserInfo found = userDetailsRepository.findByUsername("rakesh.gowda@gmail.com");
		assertNotNull("User searched is not present", found);
		assertThat(found.getUsername()).isEqualTo("rakesh.gowda@gmail.com");
	}

	@Test
	public void whenFindByName_thenReturnNull() {
		UserInfo found = userDetailsRepository.findByUsername("rahul.jha@gmail.com");
		assertNull("User searched is present", found);
	}
}