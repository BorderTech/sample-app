package com.sample.app.model.exception;

/**
 * Document not found.
 */
public class DocumentNotFoundException extends Exception {

	/**
	 * Construct exception.
	 */
	public DocumentNotFoundException() {
		super("Document not found");
	}

}
