package com.sogeti.api.filmland.controller;

import static com.sogeti.api.filmland.constant.Constants.SUBSCRIPTION_SUCCESSFULL;
import static com.sogeti.api.filmland.constant.Constants.SUBSCRIPTION_UNSUCCESSFULL;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sogeti.api.filmland.exception.SubscriptionAlreadyExistsException;
import com.sogeti.api.filmland.model.ApiResponse;
import com.sogeti.api.filmland.model.Category;
import com.sogeti.api.filmland.model.SubsciptionRequest;
import com.sogeti.api.filmland.model.SubscribeCategory;
import com.sogeti.api.filmland.model.SubscribedCategoriesDto;
import com.sogeti.api.filmland.model.SubscribedCategoryDto;
import com.sogeti.api.filmland.model.SubscriptionShareRequest;
import com.sogeti.api.filmland.service.SubscribptionService;

/**
 * 
 * @author Rakesh
 *
 */
@RestController
@CrossOrigin()
public class FilmLandController {

	@Autowired
	private SubscribptionService subscriptionService;

	@GetMapping(value = "/categories")
	public ResponseEntity<SubscribedCategoriesDto> getSubscribedCategories(@RequestParam("username") String username) {
		SubscribedCategoriesDto subscribedCategories = new SubscribedCategoriesDto();
		List<Category> categories = subscriptionService.findAvailableCategoriesForSubscribption(username);
		List<SubscribeCategory> subsCategories = subscriptionService.findSubscribptionsByUsername(username);
		subscribedCategories.setAvailableCategories(categories);
		subscribedCategories.setSubscribedCategories(SubscribedCategoryDto.fromEntityList(subsCategories));
		return ResponseEntity.ok(subscribedCategories);
	}

	@PostMapping(value = "/subscribe")
	public ResponseEntity<ApiResponse> subscribeCategory(@RequestBody SubsciptionRequest subsciptionRequest) throws SubscriptionAlreadyExistsException {
		SubscribeCategory subscribedCategory = subscriptionService.subscribeCategory(subsciptionRequest);
		ApiResponse apiResponse = new ApiResponse();
		if (subscribedCategory != null) {
			apiResponse.setStatus(SUBSCRIPTION_SUCCESSFULL);
			apiResponse.setMessage(subsciptionRequest.getEmail() + " subscribed to category "
					+ subsciptionRequest.getAvailableCategory());
		} else {
			apiResponse.setStatus(SUBSCRIPTION_UNSUCCESSFULL);
			apiResponse.setMessage(subsciptionRequest.getEmail() + " failed to subscribe to category "
					+ subsciptionRequest.getAvailableCategory());
		}
		return ResponseEntity.ok(apiResponse);
	}

	@PostMapping(value = "/shareSubscription")
	public ResponseEntity<ApiResponse> shareSubscription(
			@RequestBody SubscriptionShareRequest subscriptionShareRequest) {
		SubscribeCategory subscribedCategory = subscriptionService.shareSubscription(subscriptionShareRequest);
		ApiResponse apiResponse = new ApiResponse();
		if (subscribedCategory != null) {
			apiResponse.setStatus(SUBSCRIPTION_SUCCESSFULL);
			apiResponse.setMessage(subscriptionShareRequest.getSubscribedCategory()
					+ " shared successfully with customer  " + subscriptionShareRequest.getCustomer());
		} else {
			apiResponse.setStatus(SUBSCRIPTION_UNSUCCESSFULL);
			apiResponse.setMessage("Failed to share " + subscriptionShareRequest.getSubscribedCategory()
					+ " with customer  " + subscriptionShareRequest.getCustomer());
		}
		return ResponseEntity.ok(apiResponse);
	}
}