/**
 * 
 */
package com.sogeti.api.filmland.exception;

/**
 * @author C28278
 *
 */
public class UserExistsException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public UserExistsException(String message) {
		super(message);
	}
}
