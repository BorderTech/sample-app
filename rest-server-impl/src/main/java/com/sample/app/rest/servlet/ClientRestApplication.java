package com.sample.app.rest.servlet;

import com.github.bordertech.swagger.application.SwaggerRestApplication;
import com.github.bordertech.swagger.jackson.JacksonObjectMapperProvider;
import com.sample.app.rest.server.v1.ClientServicesResourceImpl;

/**
 * ClientREST Swagger/Jersey Application.
 */
public class ClientRestApplication extends SwaggerRestApplication {

	/**
	 * Default constructor.
	 */
	public ClientRestApplication() {
		super(ClientServicesResourceImpl.class, JacksonObjectMapperProvider.class);
	}
}
