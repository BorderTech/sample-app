package com.sample.app.model.exception;

/**
 * Client not found.
 */
public class ClientNotFoundException extends Exception {

	/**
	 * Construct exception.
	 */
	public ClientNotFoundException() {
		super("Client not found");
	}

}
