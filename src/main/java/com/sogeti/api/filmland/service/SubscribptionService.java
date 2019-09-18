package com.sogeti.api.filmland.service;

import java.util.List;

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
	public SubscribeCategory subscribeCategory(SubsciptionRequest subsciptionRequest);

	public SubscribeCategory shareSubscription(SubscriptionShareRequest subscriptionShareRequest);

	public SubscribeCategory updateSubscription(SubscribeCategory subscribeCategory);

	public List<SubscribeCategory> findSubscribptionsByUsername(String username);

	public List<SubscribeCategory> findAllSubscribptions();

	public SubscribeCategory findSubscribption(String username, String subscribedCategory);
	
	public List<Category> findAvailableCategoriesForSubscribption(String username);

}