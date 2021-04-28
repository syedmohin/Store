package com.afc.exception;

public class UserNotFoundException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6705631034932648006L;

	public UserNotFoundException(String msg) {
        super(msg);
    }
}
