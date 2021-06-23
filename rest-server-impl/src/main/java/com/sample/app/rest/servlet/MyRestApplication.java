package com.sample.app.rest.servlet;

import com.github.bordertech.swagger.application.SwaggerRestApplication;
import com.sample.app.rest.server.v1.ClientServicesResourceImpl;
import javax.ws.rs.core.Application;
import org.glassfish.jersey.linking.DeclarativeLinkingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

/**
 * Shows how to create a Jersey ResourceConfig to enable access to more config.
 * <p>
 * This application class can be registered in the {@link ClientRestServlet}.
 * </p>
 *
 * @see ClientRestServlet
 * @see ClientRestApplication
 */
public class MyRestApplication extends ResourceConfig {

	/**
	 * Construct the resource and add the needed providers.
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

		// Register Declarative Linking
		register(DeclarativeLinkingFeature.class);
	}

}
