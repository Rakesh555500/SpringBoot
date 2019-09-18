package com.sogeti.api.filmland.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sogeti.api.filmland.model.Category;
import com.sogeti.api.filmland.model.SubscribeCategory;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SubscriptionRepositoryTest {
	@Autowired
	private SubscriptionRepository subscriptionRepository;

	@Test
	public void whenFindByUserId_thenReturnSubscribedCategory() {
		List<SubscribeCategory> found = subscriptionRepository.findSubscribptionsByUserId(1L);
		assertNotNull("Subscriptions not present", found);
		assertThat(found.size()).isEqualTo(1);
		assertThat(found.get(0).getUserinfo().getUsername()).isEqualTo("rakesh.gowda@gmail.com");
		assertThat(found.get(0).getCategory().getName()).isEqualTo("International Films");
	}

	@Test
	public void whenFindByUserId_thenReturnNull() {
		List<SubscribeCategory> found = subscriptionRepository.findSubscribptionsByUserId(2L);
		assertThat(found.size()).isEqualTo(0);
	}

	@Test
	public void whenFindByUserIdAndCategoryId_thenReturnSubscribedCategory() {
		SubscribeCategory found = subscriptionRepository.findSubscribption(1L, 3L);
		assertNotNull("Subscription not present", found);
		assertThat(found.getUserinfo().getUsername()).isEqualTo("rakesh.gowda@gmail.com");
		assertThat(found.getCategory().getName()).isEqualTo("International Films");
	}

	@Test
	public void whenFindByUserIdAndCategoryId_thenReturnNull() {
		SubscribeCategory found = subscriptionRepository.findSubscribption(1L, 2L);
		assertNull("Subscription is present", found);
	}

	@Test
	public void whenFindAvailableCategoriesForUser_thenReturnCategory() {
		List<Category> found = subscriptionRepository.findAvailableCategoriesForSubscribption(1L);
		assertNotNull("Categories not present", found);
		assertThat(found.size()).isEqualTo(2);
	}

	@Test
	public void whenFindAvailableCategoriesForUser_thenReturnAllCategories() {
		List<Category> found = subscriptionRepository.findAvailableCategoriesForSubscribption(2L);
		assertNotNull("Categories not present", found);
		assertThat(found.size()).isEqualTo(3);
	}

	@Test
	public void whenFindAllSubscriptions_thenReturnAllSubscriptions() {
		List<SubscribeCategory> found = subscriptionRepository.findAllSubscribptions();
		assertNotNull("Subscriptions not present", found);
		assertThat(found.size()).isEqualTo(1);
	}
}