package com.sogeti.api.filmland.constant;

import java.text.SimpleDateFormat;

/**
 * 
 * @author Rakesh
 *
 */
public class Constants {
	private Constants() {
	}

	public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
	public static final String CRON_EXPRESSION = "0 0/1 * 1/1 * ?";
	public static final String TOKEN_NOT_AVAILABLE = "Unable to get JWT Token";
	public static final String ACCESS_TOKEN_HAS_EXPIRED = "JWT Token has expired";
	public static final String TOKEN_REFERENCE = "Bearer ";

	public static final String SUBSCRIPTION_UNSUCCESSFULL = "Subscription unsuccessfull";

	public static final String SUBSCRIPTION_SUCCESSFULL = "Subscription successfull";

}
