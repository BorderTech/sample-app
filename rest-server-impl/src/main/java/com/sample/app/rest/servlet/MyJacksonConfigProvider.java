/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample.app.rest.servlet;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import javax.ws.rs.Produces;
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
public class MyJacksonConfigProvider implements ContextResolver<ObjectMapper> {

	private final ObjectMapper defaultObjectMapper;

	/**
	 * Default constructor.
	 */
	public MyJacksonConfigProvider() {
		defaultObjectMapper = new ObjectMapper();
		// Enable Jackson java8 Support (Local date and time)
		defaultObjectMapper.registerModule(new JavaTimeModule());
		// Disable writing as Timestamp so dates are written in the correct date format.
		defaultObjectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		// Prevent exception when encountering unknown property:
		defaultObjectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

	}

	@Override
	public ObjectMapper getContext(final Class<?> type) {
		return defaultObjectMapper;
	}

	/**
	 * @return the default mapper
	 */
	public static ObjectMapper createDefaultMapper() {
		final ObjectMapper jackson = new ObjectMapper();
		// Use JODA for Timestamp Formatting
		jackson.registerModule(new JodaModule());
		jackson.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		// Prevent exception when encountering unknown property:
		jackson.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		return jackson;
	}
}
