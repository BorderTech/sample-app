package com.sample.app.rest.server.util;

import com.github.bordertech.restfriends.exception.RestStatusCodable;
import javax.ws.rs.core.Response;

/**
 * Client error exception.
 */
public class ClientErrorException extends RuntimeException implements RestStatusCodable {

	/**
	 * @param message the exception message
	 * @param ex the original exception
	 */
	public ClientErrorException(final String message, final Exception ex) {
		super(message, ex);
	}

	/**
	 * @param message the exception message
	 */
	public ClientErrorException(final String message) {
		super(message);
	}

	@Override
	public int getStatusCode() {
		return Response.Status.BAD_REQUEST.getStatusCode();
	}

}
