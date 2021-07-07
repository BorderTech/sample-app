package com.sample.app.rest.server.util;

import com.github.bordertech.restfriends.exception.RestStatusCodable;
import javax.ws.rs.core.Response;

/**
 * Server error exception.
 */
public class ServerErrorException extends RuntimeException implements RestStatusCodable {

	/**
	 * @param message the exception message
	 * @param ex the original exception
	 */
	public ServerErrorException(final String message, final Exception ex) {
		super(message, ex);
	}

	/**
	 * @param message the exception message
	 */
	public ServerErrorException(final String message) {
		super(message);
	}

	@Override
	public int getStatusCode() {
		return Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
	}

}
