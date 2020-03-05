package com.sample.app.rest.client.v1.helper;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.bordertech.config.Config;
import com.sample.app.rest.client.jersey.v1.invoker.ApiClient;
import com.sample.app.rest.client.jersey.v1.invoker.ApiException;
import com.sample.app.rest.client.jersey.v1.invoker.ApiResponse;
import com.sample.app.rest.client.jersey.v1.invoker.Pair;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.GenericType;

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
		ObjectMapper mapper = json.getContext(null);
		// Turn off the generated ENUM options
		mapper.disable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
		mapper.disable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);

	}

	@Override
	public <T> ApiResponse<T> invokeAPI(final String path, final String method, final List<Pair> queryParams,
			final Object body, final Map<String, String> headerParams, final Map<String, Object> formParams,
			final String accept, final String contentType, final String[] authNames, final GenericType<T> returnType)
			throws ApiException {
		Object fixBody = body;
		// Pass a Empty String instead of NULL Body
		if ("PUT".equals(method) && body == null) {
			fixBody = "";
		}
		return super.invokeAPI(path, method, queryParams, fixBody, headerParams, formParams, accept, contentType, authNames, returnType);
	}
}
