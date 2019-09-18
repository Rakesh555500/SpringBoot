package com.sogeti.api.filmland.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sogeti.api.filmland.model.Category;
import com.sogeti.api.filmland.model.SubscribeCategory;

public interface SubscriptionRepository extends CrudRepository<SubscribeCategory, Long> {
	@Query("SELECT s FROM SubscribeCategory s WHERE s.userinfo.id = :userId")
	public List<SubscribeCategory> findSubscribptionsByUserId(Long userId);

	@Query("SELECT s FROM SubscribeCategory s WHERE s.userinfo.id = :userId AND s.category.id=:categoryId")
	public SubscribeCategory findSubscribption(Long userId, Long categoryId);

	@Query("SELECT c FROM Category c WHERE c.id NOT IN (SELECT s.category.id FROM SubscribeCategory s WHERE s.userinfo.id=:userId)")
	public List<Category> findAvailableCategoriesForSubscribption(Long userId);

	@Query("SELECT s FROM SubscribeCategory s")
	public List<SubscribeCategory> findAllSubscribptions();
}
