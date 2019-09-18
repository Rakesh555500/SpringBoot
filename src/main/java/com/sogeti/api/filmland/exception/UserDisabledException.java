/**
 * 
 */
package com.sogeti.api.filmland.exception;

/**
 * @author C28278
 *
 */
public class UserDisabledException extends Exception {

	private static final long serialVersionUID = 1L;

	public UserDisabledException(String message) {
		super(message);
	}
}
