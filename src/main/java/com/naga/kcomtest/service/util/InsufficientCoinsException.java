package com.naga.kcomtest.service.util;

/**
 * Thrown to indicate that a method has been passed an illegal or unavailable
 * Coins
 *
 * @author Naga
 * 
 */
public class InsufficientCoinsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6871697280032897909L;

	/**
	 * 
	 */

	public InsufficientCoinsException() {
		super();
	}

	public InsufficientCoinsException(String s) {
		super(s);
	}

}
