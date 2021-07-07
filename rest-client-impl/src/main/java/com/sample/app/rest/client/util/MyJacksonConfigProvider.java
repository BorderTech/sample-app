package com.sample.app.rest.client.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

/**
 * Jackson object mapper.
 * <p>
 * This provider can be added to the Application classes to provide different defaults to Jackson.
 * </p>
 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MyJacksonConfigProvider implements ContextResolver<ObjectMapper> {

	private final ObjectMapper defaultObjectMapper;

	/**
	 * Default constructor.
	 */
	public MyJacksonConfigProvider() {
		defaultObjectMapper = new ObjectMapper();

		// Enable Jackson java8 Support (LocalDate and LocalDateTime)
		defaultObjectMapper.registerModule(new JavaTimeModule());
		// Disable writing as Timestamp so dates are written in the correct date format.
		defaultObjectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

		// Prevent exception when encountering unknown property (such as the Hypermedia links)
		defaultObjectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

		// Register custom declarative link serializer (only used if the jaxrs links are in the model)
		SimpleModule simpleModule = new SimpleModule();
		simpleModule.addDeserializer(Link.class, new MyLinkDeserializer());
		defaultObjectMapper.registerModule(simpleModule);
	}

	@Override
	public ObjectMapper getContext(final Class<?> type) {
		return defaultObjectMapper;
	}

}
