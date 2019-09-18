/**
 * 
 */
package com.sogeti.api.filmland.service.impl;

import static com.sogeti.api.filmland.constant.Constants.NOT_EXIST;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sogeti.api.filmland.exception.SubscriptionAlreadyExistsException;
import com.sogeti.api.filmland.exception.UserNotExistsException;
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
	public SubscribeCategory subscribeCategory(SubsciptionRequest subsciptionRequest)
			throws SubscriptionAlreadyExistsException, UserNotExistsException {
		UserInfo userInfo = filmLandUserService.findUserByUsername(subsciptionRequest.getEmail());
		Category category = categoryService.findCategoryByName(subsciptionRequest.getAvailableCategory());

		if (userInfo == null) {
			throw new UserNotExistsException(subsciptionRequest.getEmail() + NOT_EXIST);
		}

		if (subscriptionRepository.findSubscribption(userInfo.getId(), category.getId()) != null) {
			throw new SubscriptionAlreadyExistsException(subsciptionRequest.getEmail() + " already subscribed to "
					+ subsciptionRequest.getAvailableCategory());
		}

		SubscribeCategory subscribeCategory = new SubscribeCategory();
		subscribeCategory.setCategory(category);
		subscribeCategory.setUserinfo(userInfo);
		subscribeCategory.setRemainingContent(category.getAvailableContent());
		subscribeCategory.setStartDate(new Date());

		return subscriptionRepository.save(subscribeCategory);
	}

	@Override
	public List<SubscribeCategory> findSubscriptionsByUsername(String username) throws UserNotExistsException {
		UserInfo userInfo = filmLandUserService.findUserByUsername(username);
		if (userInfo == null) {
			throw new UserNotExistsException(username + NOT_EXIST);
		}
		return subscriptionRepository.findSubscribptionsByUserId(userInfo.getId());
	}

	@Override
	public List<SubscribeCategory> findAllSubscriptions() {
		Iterable<SubscribeCategory> itr = subscriptionRepository.findAllSubscribptions();
		List<SubscribeCategory> subscriptions = new ArrayList<>();
		itr.forEach(item -> subscriptions.add(item));
		return subscriptions;
	}

	@Override
	public SubscribeCategory shareSubscription(SubscriptionShareRequest subscriptionShareRequest)
			throws UserNotExistsException {
		SubscribeCategory subscribedCategory = findSubscription(subscriptionShareRequest.getEmail(),
				subscriptionShareRequest.getSubscribedCategory());
		Category category = categoryService.findCategoryByName(subscriptionShareRequest.getSubscribedCategory());
		UserInfo customer = filmLandUserService.findUserByUsername(subscriptionShareRequest.getCustomer());

		if (customer == null) {
			throw new UserNotExistsException(subscriptionShareRequest.getCustomer() + NOT_EXIST);
		}

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
	public SubscribeCategory findSubscription(String username, String subscribedCategory) {
		UserInfo userInfo = filmLandUserService.findUserByUsername(username);
		Category category = categoryService.findCategoryByName(subscribedCategory);
		return subscriptionRepository.findSubscribption(userInfo.getId(), category.getId());
	}

	@Override
	public SubscribeCategory updateSubscription(SubscribeCategory subscribeCategory) {
		return subscriptionRepository.save(subscribeCategory);
	}

	@Override
	public List<Category> findAvailableCategoriesForSubscription(String username) {
		UserInfo userInfo = filmLandUserService.findUserByUsername(username);
		return subscriptionRepository.findAvailableCategoriesForSubscribption(userInfo.getId());
	}
}