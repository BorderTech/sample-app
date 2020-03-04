package com.sample.app.rest.client.v1;

import com.sample.app.rest.client.v1.helper.DefaultApi;
import com.sample.app.model.exception.ServiceException;
import com.sample.app.rest.client.jersey.v1.api.ClientServicesApi;
import com.sample.app.rest.client.jersey.v1.invoker.ApiException;

/**
 * Provide the default Swagger API implementation.
 */
public abstract class AbstractRestClient {

	/**
	 * @return the Swagger API
	 */
	protected ClientServicesApi getApi() {
		return new DefaultApi();
	}

	/**
	 * Handle the API exception.
	 *
	 * @param e the API exception
	 * @return the system exception
	 */
	protected ServiceException handleException(final ApiException e) {
		return new ServiceException(e.getMessage(), e);
	}

}
