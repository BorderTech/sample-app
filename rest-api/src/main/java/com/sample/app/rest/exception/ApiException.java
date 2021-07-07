package com.sample.app.rest.exception;

/**
 * API exception thrown by implementations.
 */
public class ApiException extends RuntimeException {

	/**
	 * @param message the exception message
	 * @param excp the original exception
	 */
	public ApiException(final String message, final Exception excp) {
		super(message, excp);
	}

	/**
	 * @param message the exception message
	 */
	public ApiException(final String message) {
		super(message);
	}

}
