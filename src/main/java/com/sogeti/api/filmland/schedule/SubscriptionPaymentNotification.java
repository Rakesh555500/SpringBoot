package com.sogeti.api.filmland.schedule;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sogeti.api.filmland.constant.Constants;
import com.sogeti.api.filmland.model.SubscribeCategory;
import com.sogeti.api.filmland.service.SubscribptionService;

/**
 * 
 * @author Rakesh
 *
 */
@Component
public class SubscriptionPaymentNotification {
	private static final Logger LOGGER = LoggerFactory.getLogger(SubscriptionPaymentNotification.class);

	@Autowired
	private SubscribptionService subscribeService;

	/**
	 * This method will run the scheduler in each 10 Sec interval to fetch the over
	 * due subscription user
	 * 
	 */
	@Scheduled(cron = Constants.CRON_EXPRESSION)
	public void notifyUserForPayments() {
		List<SubscribeCategory> subscribedCategories = subscribeService.findAllSubscriptions();
		subscribedCategories.forEach(subscribedCategory -> {
			if (isBeforeMonths(-1, subscribedCategory.getStartDate())) {
				String username = subscribedCategory.getUserinfo().getUsername();
				double paymentAmount;
				if (subscribedCategory.getSharedWith() == 0) {
					paymentAmount = subscribedCategory.getCategory().getPrice();
				} else {
					paymentAmount = subscribedCategory.getCategory().getPrice() / subscribedCategory.getSharedWith();
				}
				LOGGER.info("PaymentNotification: Mail has been sent to subscriber {} for monthly payment of {}",
						username, paymentAmount);
			}
		});

	}

	/**
	 * This method will compare the start date with current date and return response
	 * in 0/1(boolean)
	 * 
	 * @param months Contain the start date of subscription month
	 * @param aDate  Contain the start date of subscription date
	 * @return return true or false after date calculation
	 */
	private boolean isBeforeMonths(int months, Date aDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, months);
		return aDate.compareTo(calendar.getTime()) < 0;
	}
}