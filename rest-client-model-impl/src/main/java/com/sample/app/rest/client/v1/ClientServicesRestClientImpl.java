package com.sample.app.rest.client.v1;

import com.sample.app.model.client.ClientDetail;
import com.sample.app.model.client.CodeOption;
import com.sample.app.model.client.DocumentContent;
import com.sample.app.model.client.DocumentDetail;
import com.sample.app.model.exception.ClientNotFoundException;
import com.sample.app.model.exception.DocumentNotFoundException;
import com.sample.app.model.exception.ServiceException;
import com.sample.app.model.services.ClientServices;
import com.sample.app.rest.api.v1.model.ClientDetailResponse;
import com.sample.app.rest.api.v1.model.DocumentContentResponse;
import com.sample.app.rest.api.v1.model.RetrieveClientDocumentsResponse;
import com.sample.app.rest.api.v1.model.RetrieveCodesResponse;
import com.sample.app.rest.api.v1.model.RetrieveTablesResponse;
import com.sample.app.rest.api.v1.model.SearchClientsResponse;
import com.sample.app.rest.client.jersey.v1.invoker.ApiException;
import java.util.List;

/**
 * Service implementation that calls Swagger generated REST Client.
 */
public class ClientServicesRestClientImpl extends AbstractRestClient implements ClientServices {

	@Override
	public List<String> retrieveTables() throws ServiceException {
		try {
			RetrieveTablesResponse resp = getApi().retrieveTables();
			return resp.getData();
		} catch (ApiException e) {
			throw handleException(e);
		}
	}

	@Override
	public List<CodeOption> retrieveCodes(final String table) throws ServiceException {
		try {
			RetrieveCodesResponse resp = getApi().retrieveCodes(table);
			return resp.getData();
		} catch (ApiException e) {
			throw handleException(e);
		}
	}

	@Override
	public List<ClientDetail> searchClients(final String search) throws ServiceException {
		try {
			SearchClientsResponse resp = getApi().searchClients(search);
			return resp.getData();
		} catch (ApiException e) {
			throw handleException(e);
		}
	}

	@Override
	public ClientDetail retrieveClient(final String clientId) throws ServiceException, ClientNotFoundException {
		try {
			ClientDetailResponse resp = getApi().retrieveClient(clientId);
			return resp.getData();
		} catch (ApiException e) {
			throw handleException(e);
		}
	}

	@Override
	public ClientDetail createClient(final ClientDetail detail) throws ServiceException {
		try {
			ClientDetailResponse resp = getApi().createClient(detail);
			return resp.getData();
		} catch (ApiException e) {
			throw handleException(e);
		}
	}

	@Override
	public ClientDetail updateClient(final ClientDetail detail) throws ServiceException {
		try {
			ClientDetailResponse resp = getApi().updateClient(detail.getClientId(), detail);
			return resp.getData();
		} catch (ApiException e) {
			throw handleException(e);
		}
	}

	@Override
	public void deleteClient(final String clientId) throws ServiceException {
		try {
			getApi().deleteClient(clientId);
		} catch (ApiException e) {
			throw handleException(e);
		}
	}

	@Override
	public List<DocumentDetail> retrieveClientDocuments(final String clientId) throws ServiceException, ClientNotFoundException {
		try {
			RetrieveClientDocumentsResponse resp = getApi().retrieveClientDocuments(clientId);
			return resp.getData();
		} catch (ApiException e) {
			throw handleException(e);
		}
	}

	@Override
	public DocumentContent retrieveDocument(final String documentId) throws ServiceException, DocumentNotFoundException {
		try {
			DocumentContentResponse resp = getApi().retrieveDocument(documentId);
			return resp.getData();
		} catch (ApiException e) {
			throw handleException(e);
		}
	}

}
