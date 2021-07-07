package com.sample.app.rest.client.v1;

import com.github.bordertech.config.Config;
import com.sample.app.rest.client.util.MyJacksonConfigProvider;
import com.sample.app.rest.exception.ApiException;
import com.sample.app.rest.v1.api.V1Api;
import com.sample.app.rest.v1.model.ClientDetailDTO;
import com.sample.app.rest.v1.model.ClientDetailResponseDTO;
import com.sample.app.rest.v1.model.DocumentContentResponseDTO;
import com.sample.app.rest.v1.model.ErrorResponseDTO;
import com.sample.app.rest.v1.model.RetrieveClientDocumentsResponseDTO;
import com.sample.app.rest.v1.model.RetrieveCodesResponseDTO;
import com.sample.app.rest.v1.model.RetrieveTablesResponseDTO;
import com.sample.app.rest.v1.model.SearchClientsResponseDTO;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.ClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

/**
 * Client implementation that uses REST EASY proxy client.
 */
public class V1ApiClientResteasyImpl implements V1Api {

	private static final String AV_REST_URI = "sample.rest.uri";

	/**
	 * @return the REST URI endpoint
	 */
	private static String getRestUri() {
		return Config.getInstance().getString(AV_REST_URI, "http://localhost:8082/lde/api");
	}

	@Override
	public RetrieveTablesResponseDTO retrieveTables() {
		try {
			return getApi().retrieveTables();
		} catch (Exception e) {
			throw handleException(e);
		}
	}

	@Override
	public RetrieveCodesResponseDTO retrieveCodes(final String table) {
		try {
			return getApi().retrieveCodes(table);
		} catch (Exception e) {
			throw handleException(e);
		}
	}

	@Override
	public SearchClientsResponseDTO searchClients(final String search) {
		try {
			return getApi().searchClients(search);
		} catch (Exception e) {
			throw handleException(e);
		}
	}

	@Override
	public ClientDetailResponseDTO retrieveClient(final String clientId) {
		try {
			return getApi().retrieveClient(clientId);
		} catch (Exception e) {
			throw handleException(e);
		}
	}

	@Override
	public ClientDetailResponseDTO createClient(final ClientDetailDTO detail) {
		try {
			return getApi().createClient(detail);
		} catch (Exception e) {
			throw handleException(e);
		}
	}

	@Override
	public ClientDetailResponseDTO updateClient(final String id, final ClientDetailDTO detail) {
		try {
			return getApi().updateClient(detail.getClientId(), detail);
		} catch (Exception e) {
			throw handleException(e);
		}
	}

	@Override
	public void deleteClient(final String clientId) {
		try {
			getApi().deleteClient(clientId);
		} catch (Exception e) {
			throw handleException(e);
		}
	}

	@Override
	public RetrieveClientDocumentsResponseDTO retrieveClientDocuments(final String clientId) {
		try {
			return getApi().retrieveClientDocuments(clientId);
		} catch (Exception e) {
			throw handleException(e);
		}
	}

	@Override
	public DocumentContentResponseDTO retrieveDocument(final String documentId) {
		try {
			return getApi().retrieveDocument(documentId);
		} catch (Exception e) {
			throw handleException(e);
		}
	}

	/**
	 * Get the backing RestEasy Client proxy class.
	 *
	 * @return the backing API Rest client
	 */
	private V1Api getApi() {
		// Check if can reuse same client
		ResteasyClient client = (ResteasyClient) ClientBuilder.newClient();
		client.register(MyJacksonConfigProvider.class);
		ResteasyWebTarget target = client.target(getRestUri());
		V1Api api = target.proxy(V1Api.class);
		return api;
	}

	/**
	 * Handle the API exception.
	 *
	 * @param e the API exception
	 * @return the system exception
	 */
	private ApiException handleException(final Exception e) {
		if (e instanceof WebApplicationException) {
			String msg = extractErrorMessage((WebApplicationException) e);
			return new ApiException(msg, e);
		}
		return new ApiException(e.getMessage(), e);
	}

	private String extractErrorMessage(final WebApplicationException excp) {
		try {
			// Check if response body is the error details
			ErrorResponseDTO det = excp.getResponse().readEntity(ErrorResponseDTO.class);
			if (det != null && det.getError() != null && det.getError().getMessage() != null) {
				return det.getError().getMessage();
			}
		} catch (Exception e) {
			// Could not get details
		}
		return excp.getMessage();
	}

}
