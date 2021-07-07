package com.sample.app.ui.util;

import com.github.bordertech.didums.Didums;
import com.sample.app.rest.v1.api.V1Api;
import java.io.Serializable;

/**
 * Provide an instance of the REST API client services.
 */
public final class V1ApiClientHelperFactory implements Serializable {

	/**
	 * Singleton instance.
	 */
	private static final V1Api INSTANCE = Didums.getService(V1Api.class);

	/**
	 * Don't allow external instantiation of this class.
	 */
	private V1ApiClientHelperFactory() {
		// Do nothing
	}

	/**
	 * @return the singleton instance of the CRM Services.
	 */
	public static V1Api getInstance() {
		return INSTANCE;
	}
}
