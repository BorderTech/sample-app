package com.sample.app.rest.server.v1;

import com.github.bordertech.didums.Didums;
import com.github.bordertech.restfriends.exception.RestBusinessException;
import com.sample.app.model.client.ClientDetail;
import com.sample.app.model.client.CodeOption;
import com.sample.app.model.client.DocumentContent;
import com.sample.app.model.client.DocumentDetail;
import com.sample.app.model.exception.ClientNotFoundException;
import com.sample.app.model.exception.DocumentNotFoundException;
import com.sample.app.model.exception.ServiceException;
import com.sample.app.model.services.ClientServices;
import com.sample.app.rest.api.v1.ClientServicesResource;
import com.sample.app.rest.api.v1.model.ClientDetailResponse;
import com.sample.app.rest.api.v1.model.DocumentContentResponse;
import com.sample.app.rest.api.v1.model.RetrieveClientDocumentsResponse;
import com.sample.app.rest.api.v1.model.RetrieveCodesResponse;
import com.sample.app.rest.api.v1.model.RetrieveTablesResponse;
import com.sample.app.rest.api.v1.model.SearchClientsResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.ws.rs.core.Response;

/**
 * Sample REST Resource call a backing service implementation.
 */
@SuppressWarnings("NoWhitespaceBefore")
public class ClientServicesResourceImpl implements ClientServicesResource {

	private final ClientServices backing = Didums.getService(ClientServices.class);

	@Override
	public RetrieveTablesResponse retrieveTables()
			throws RestBusinessException {
		try {
			List<String> resp = backing.retrieveTables();
			return new RetrieveTablesResponse(new ArrayList<>(resp));
		} catch (ServiceException e) {
			throw new RestBusinessException(e.getMessage(), e);
		}
	}

	@Override
	public RetrieveCodesResponse retrieveCodes(final String table)
			throws RestBusinessException {
		try {
			List<CodeOption> resp = backing.retrieveCodes(table);
			return new RetrieveCodesResponse(new ArrayList<>(resp));
		} catch (ServiceException e) {
			throw new RestBusinessException(e.getMessage(), e);
		}
	}

	@Override
	public SearchClientsResponse searchClients(final String search)
			throws RestBusinessException {
		try {
			List<ClientDetail> resp = backing.searchClients(search);
			return new SearchClientsResponse(new ArrayList<>(resp));
		} catch (ServiceException e) {
			throw new RestBusinessException(e.getMessage(), e);
		}
	}

	@Override
	public ClientDetailResponse retrieveClient(final String clientId)
			throws RestBusinessException {
		try {
			ClientDetail resp = backing.retrieveClient(clientId);
			return new ClientDetailResponse(resp);
		} catch (ServiceException | ClientNotFoundException e) {
			throw new RestBusinessException(e.getMessage(), e);
		}
	}

	@Override
	public ClientDetailResponse createClient(final ClientDetail detail)
			throws RestBusinessException {
		try {
			ClientDetail resp = backing.createClient(detail);
			return new ClientDetailResponse(resp);
		} catch (ServiceException e) {
			throw new RestBusinessException(e.getMessage(), e);
		}
	}

	@Override
	public ClientDetailResponse updateClient(final String clientId, final ClientDetail detail)
			throws RestBusinessException {
		// Check IDs
		if (!Objects.equals(clientId, detail.getClientId())) {
			throw new RestBusinessException("Client ID does not match ID in details.");
		}
		try {
			ClientDetail resp = backing.updateClient(detail);
			return new ClientDetailResponse(resp);
		} catch (ServiceException e) {
			throw new RestBusinessException(e.getMessage(), e);
		}
	}

	@Override
	public Response deleteClient(final String clientId)
			throws RestBusinessException {
		try {
			backing.deleteClient(clientId);
			return Response.ok().build();
		} catch (ServiceException e) {
			throw new RestBusinessException(e.getMessage(), e);
		}
	}

	@Override
	public RetrieveClientDocumentsResponse retrieveClientDocuments(final String clientId)
			throws RestBusinessException {
		try {
			List<DocumentDetail> resp = backing.retrieveClientDocuments(clientId);
			return new RetrieveClientDocumentsResponse(new ArrayList<>(resp));
		} catch (ServiceException | ClientNotFoundException e) {
			throw new RestBusinessException(e.getMessage(), e);
		}
	}

	@Override
	public DocumentContentResponse retrieveDocument(final String documentId)
			throws RestBusinessException {
		try {
			DocumentContent resp = backing.retrieveDocument(documentId);
			return new DocumentContentResponse(resp);
		} catch (ServiceException | DocumentNotFoundException e) {
			throw new RestBusinessException(e.getMessage(), e);
		}
	}

}
