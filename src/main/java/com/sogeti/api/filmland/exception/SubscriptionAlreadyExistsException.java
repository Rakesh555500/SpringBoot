/**
 * 
 */
package com.sogeti.api.filmland.exception;

/**
 * @author Rakesh
 *
 */
public class SubscriptionAlreadyExistsException extends Exception {
	private static final long serialVersionUID = 1L;

	public SubscriptionAlreadyExistsException(String message) {
		super(message);
	}

}
