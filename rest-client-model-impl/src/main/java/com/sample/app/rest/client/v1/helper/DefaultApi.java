package com.sample.app.rest.client.v1.helper;

import com.sample.app.rest.client.jersey.v1.api.ClientServicesApi;

/**
 * Default API invoker.
 */
public class DefaultApi extends ClientServicesApi {

	/**
	 * Default constructor.
	 */
	public DefaultApi() {
		super(new DefaultApiClient());
	}
}
