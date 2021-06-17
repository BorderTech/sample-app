package com.sample.app.rest.servlet;

import javax.ws.rs.core.Application;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

/**
 * Create a Jersey ResourceConfig to enable access to more config.
 */
public class MyApplicationWrapper extends ResourceConfig {

	/**
	 * Construct the resource with the REST application.
	 */
	public MyApplicationWrapper() {
		Application appl = new ClientRestApplication();
		registerClasses(appl.getClasses());
		// Handle the validation exceptions
		registerClasses(MyHandleValidationExceptions.class);
		// Now you can expect validation errors to be sent to the client.
		property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
	}

}
