package com.sogeti.api.filmland.service;

import java.util.List;

import com.sogeti.api.filmland.exception.SubscriptionAlreadyExistsException;
import com.sogeti.api.filmland.exception.UserNotExistsException;
import com.sogeti.api.filmland.model.Category;
import com.sogeti.api.filmland.model.SubsciptionRequest;
import com.sogeti.api.filmland.model.SubscribeCategory;
import com.sogeti.api.filmland.model.SubscriptionShareRequest;

/**
 * 
 * @author Rakesh
 *
 */
public interface SubscribptionService {
	public SubscribeCategory subscribeCategory(SubsciptionRequest subsciptionRequest) throws SubscriptionAlreadyExistsException, UserNotExistsException;

	public SubscribeCategory shareSubscription(SubscriptionShareRequest subscriptionShareRequest) throws UserNotExistsException;

	public SubscribeCategory updateSubscription(SubscribeCategory subscribeCategory);

	public List<SubscribeCategory> findSubscriptionsByUsername(String username) throws UserNotExistsException;

	public List<SubscribeCategory> findAllSubscriptions();

	public SubscribeCategory findSubscription(String username, String subscribedCategory) throws UserNotExistsException;
	
	public List<Category> findAvailableCategoriesForSubscription(String username);

}