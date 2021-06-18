package com.sample.app.rest.servlet;

import com.github.bordertech.swagger.application.SwaggerRestApplication;
import com.sample.app.rest.server.v1.ClientServicesResourceImpl;

/**
 * ClientREST Swagger/Jersey Application.
 * <p>
 * This application class is registered in the {@link ClientRestServlet}.
 * </p>
 *
 * @see ClientRestServlet
 */
public class ClientRestApplication extends SwaggerRestApplication {

	/**
	 * Default constructor to register the providers.
	 */
	public ClientRestApplication() {
		super(
				// Client Service
				ClientServicesResourceImpl.class,
				// The jackson config
				MyJacksonConfigProvider.class,
				// Handle the date time exceptions
				MyHandleJsonMappingExceptions.class,
				// Handle the bean validation exceptions
				MyHandleValidationExceptions.class
		);
	}

}
