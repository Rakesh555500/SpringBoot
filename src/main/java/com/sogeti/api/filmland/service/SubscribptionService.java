package com.sogeti.api.filmland.service;

import java.util.List;

import com.sogeti.api.filmland.exception.SubscriptionAlreadyExistsException;
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
	public SubscribeCategory subscribeCategory(SubsciptionRequest subsciptionRequest) throws SubscriptionAlreadyExistsException;

	public SubscribeCategory shareSubscription(SubscriptionShareRequest subscriptionShareRequest);

	public SubscribeCategory updateSubscription(SubscribeCategory subscribeCategory);

	public List<SubscribeCategory> findSubscriptionsByUsername(String username);

	public List<SubscribeCategory> findAllSubscriptions();

	public SubscribeCategory findSubscription(String username, String subscribedCategory);
	
	public List<Category> findAvailableCategoriesForSubscription(String username);

}