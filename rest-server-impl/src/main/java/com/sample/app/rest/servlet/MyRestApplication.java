package com.sample.app.rest.servlet;

import com.github.bordertech.swagger.application.SwaggerRestApplication;
import com.sample.app.rest.server.v1.ClientServicesResourceImpl;
import javax.ws.rs.core.Application;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

/**
 * Create a Jersey ResourceConfig to enable access to more config.
 */
public class MyRestApplication extends ResourceConfig {

	/**
	 * Construct the resource with the REST application.
	 */
	public MyRestApplication() {

		// Register the classes needed for swagger (take it from RestFriends Application)
		Application appl = new SwaggerRestApplication();
		registerClasses(appl.getClasses());

		// Register the Client Services
		registerClasses(ClientServicesResourceImpl.class);

		// Register the jackson config
		registerClasses(MyJacksonConfigProvider.class);
		// Handle the date time exceptions
		registerClasses(MyHandleJsonMappingExceptions.class);

		// Handle the bean validation exceptions
		registerClasses(MyHandleValidationExceptions.class);
		// Now you can expect validation errors to be sent to the client.
		property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
	}

}
