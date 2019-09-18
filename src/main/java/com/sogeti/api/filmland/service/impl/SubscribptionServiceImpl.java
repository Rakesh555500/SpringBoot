/**
 * 
 */
package com.sogeti.api.filmland.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sogeti.api.filmland.model.Category;
import com.sogeti.api.filmland.model.SubsciptionRequest;
import com.sogeti.api.filmland.model.SubscribeCategory;
import com.sogeti.api.filmland.model.SubscriptionShareRequest;
import com.sogeti.api.filmland.model.UserInfo;
import com.sogeti.api.filmland.repository.SubscriptionRepository;
import com.sogeti.api.filmland.service.CategoryService;
import com.sogeti.api.filmland.service.FilmLandUserService;
import com.sogeti.api.filmland.service.SubscribptionService;

/**
 * Service class for subscription
 * 
 * @author Rakesh
 *
 */
@Service
public class SubscribptionServiceImpl implements SubscribptionService {
	@Autowired
	private FilmLandUserService filmLandUserService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private SubscriptionRepository subscriptionRepository;

	@Override
	public SubscribeCategory subscribeCategory(SubsciptionRequest subsciptionRequest) {
		UserInfo userinfo = filmLandUserService.findUserByUsername(subsciptionRequest.getEmail());
		Category category = categoryService.findCategoryByName(subsciptionRequest.getAvailableCategory());

		SubscribeCategory subscribeCategory = new SubscribeCategory();
		subscribeCategory.setCategory(category);
		subscribeCategory.setUserinfo(userinfo);
		subscribeCategory.setRemainingContent(category.getAvailableContent());
		subscribeCategory.setStartDate(new Date());

		return subscriptionRepository.save(subscribeCategory);
	}

	@Override
	public List<SubscribeCategory> findSubscribptionsByUsername(String username) {
		UserInfo user = filmLandUserService.findUserByUsername(username);
		return subscriptionRepository.findSubscribptionsByUserId(user.getId());
	}

	@Override
	public List<SubscribeCategory> findAllSubscribptions() {
		Iterable<SubscribeCategory> itr = subscriptionRepository.findAll();
		List<SubscribeCategory> subscriptions = new ArrayList<>();
		itr.forEach(item -> subscriptions.add(item));
		return subscriptions;
	}

	@Override
	public SubscribeCategory shareSubscription(SubscriptionShareRequest subscriptionShareRequest) {
		SubscribeCategory subscribedCategory = findSubscribption(subscriptionShareRequest.getEmail(),
				subscriptionShareRequest.getSubscribedCategory());
		Category category = categoryService.findCategoryByName(subscriptionShareRequest.getSubscribedCategory());
		UserInfo customer = filmLandUserService.findUserByUsername(subscriptionShareRequest.getCustomer());
		
		int remainingContent = subscribedCategory.getRemainingContent() / 2;
		subscribedCategory.setRemainingContent(remainingContent);
		subscribedCategory.setSharedWith(1);
		
		SubscribeCategory updatedSubscription = updateSubscription(subscribedCategory);

		if (updatedSubscription != null) {
			SubscribeCategory sharedSubscribeCategory = new SubscribeCategory();
			sharedSubscribeCategory.setCategory(category);
			sharedSubscribeCategory.setUserinfo(customer);
			sharedSubscribeCategory.setRemainingContent(remainingContent);
			sharedSubscribeCategory.setStartDate(new Date());
			sharedSubscribeCategory.setSharedWith(1);
			return subscriptionRepository.save(sharedSubscribeCategory);
		} else {
			// rollback subscription update
			return null;
		}
	}

	@Override
	public SubscribeCategory findSubscribption(String username, String subscribedCategory) {
		UserInfo userInfo = filmLandUserService.findUserByUsername(username);
		Category category = categoryService.findCategoryByName(subscribedCategory);
		return subscriptionRepository.findSubscribption(userInfo.getId(), category.getId());
	}

	@Override
	public SubscribeCategory updateSubscription(SubscribeCategory subscribeCategory) {
		return subscriptionRepository.save(subscribeCategory);
	}

	@Override
	public List<Category> findAvailableCategoriesForSubscribption(String username) {
		UserInfo userInfo = filmLandUserService.findUserByUsername(username);
		return subscriptionRepository.findAvailableCategoriesForSubscribption(userInfo.getId());
	}
}