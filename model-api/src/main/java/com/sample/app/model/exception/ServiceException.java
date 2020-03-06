package com.sample.app.model.exception;

/**
 * Exception processing services.
 */
public class ServiceException extends Exception {

	/**
	 * @param msg exception message
	 */
	public ServiceException(final String msg) {
		this(msg, null);
	}

	/**
	 * @param msg exception message
	 * @param throwable original exception
	 */
	public ServiceException(final String msg, final Throwable throwable) {
		super(msg, throwable);
	}

}
