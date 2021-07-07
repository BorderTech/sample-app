package com.sample.app.rest.server.app;

import com.github.bordertech.swagger.application.SwaggerRestApplication;
import com.sample.app.rest.server.util.MyJacksonConfigProvider;
import com.sample.app.rest.server.v1.V1ApiServerResourceImpl;
import com.sample.app.rest.server.v1.handler.MyBeanValidationExceptions;
import com.sample.app.rest.server.v1.handler.MyCatchAllExceptions;
import java.util.Collection;
import java.util.Collections;
import javax.ws.rs.core.Application;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.linking.DeclarativeLinkingFeature;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Shows how to create a Jersey ResourceConfig to enable access to more config.
 * <p>
 * This application is registered in the {@link MyRestServlet}.
 * </p>
 *
 * @see MyRestServlet
 */
public class MyRestApplication extends ResourceConfig {

	/**
	 * Construct the resource and add the needed providers.
	 */
	public MyRestApplication() {

		// Register the classes needed for swagger (take it from RestFriends Application)
		Application appl = new SwaggerRestApplication() {
			@Override
			public Collection<Class<?>> getExceptionMapperClasses() {
				// Ignore RestFriends handlers
				return Collections.EMPTY_LIST;
			}
		};
		registerClasses(appl.getClasses());

		// Register the V1Api Services
		register(V1ApiServerResourceImpl.class);

		// Register the jackson config
		register(MyJacksonConfigProvider.class);

		// Handle the bean validation exceptions
		register(MyBeanValidationExceptions.class);
		// Now you can expect validation errors to be sent to the client.
//		property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);

		// Handle all exceptions
		register(MyCatchAllExceptions.class);

		// Register Declarative Linking
		register(DeclarativeLinkingFeature.class);

		// Dont use default JSON Parsing excpetion mappings
		register(JacksonFeature.withoutExceptionMappers());
	}

}
