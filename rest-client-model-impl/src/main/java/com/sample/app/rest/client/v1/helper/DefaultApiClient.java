package com.sample.app.rest.client.v1.helper;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.bordertech.config.Config;
import com.sample.app.rest.client.jersey.v1.invoker.ApiClient;
import com.sample.app.rest.client.jersey.v1.invoker.ApiException;
import com.sample.app.rest.client.jersey.v1.invoker.ApiResponse;
import com.sample.app.rest.client.jersey.v1.invoker.Pair;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Link;

/**
 * API client defaults.
 */
public class DefaultApiClient extends ApiClient {

	private static final String AV_REST_URI = "sample.rest.uri";

	/**
	 * @return the REST URI endpoint
	 */
	public static String getRestUri() {
		return Config.getInstance().getString(AV_REST_URI, "http://localhost:8082/lde/api");
	}

	/**
	 * Default constructor.
	 */
	public DefaultApiClient() {
		setBasePath(getRestUri());
		// Force API client to use base URL by clearing the default server index
		setServerIndex(null);
		ObjectMapper mapper = json.getContext(null);

		// Turn off the generated ENUM options
		mapper.disable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
		mapper.disable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);

		// Enable Jackson java8 Support (LocalDate and LocalDateTime)
		mapper.registerModule(new JavaTimeModule());
		// Disable writing as Timestamp so dates are written in the correct date format.
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

		// Prevent exception when encountering unknown property:
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

		// Handle declarative links
		SimpleModule simpleModule = new SimpleModule();
		simpleModule.addDeserializer(Link.class, new MyLinkDeserializer());
		mapper.registerModule(simpleModule);

	}

	@Override
	public <T> ApiResponse<T> invokeAPI(final String path, final String method, final List<Pair> queryParams, final Object body, final Map<String, String> headerParams, final Map<String, String> cookieParams, final Map<String, Object> formParams, final String accept, final String contentType, final String[] authNames, final GenericType<T> returnType, final boolean isBodyNullable) throws ApiException {
		Object fixBody = body;
		// Pass a Empty String instead of NULL Body
		if ("PUT".equals(method) && body == null) {
			fixBody = "";
		}
		return super.invokeAPI(path, method, queryParams, fixBody, headerParams, cookieParams, formParams, accept, contentType, authNames, returnType, isBodyNullable); //To change body of generated methods, choose Tools | Templates.
	}

}
