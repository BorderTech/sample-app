package com.sample.app.model.impl;

import com.sample.app.model.client.CodeOption;
import com.sample.app.model.client.DocumentContent;
import com.sample.app.model.client.DocumentDetail;
import com.sample.app.model.client.ClientDetail;
import com.sample.app.model.exception.ClientNotFoundException;
import com.sample.app.model.exception.DocumentNotFoundException;
import com.sample.app.model.exception.ServiceException;
import java.util.Collections;
import java.util.List;
import com.sample.app.model.services.ClientServices;

/**
 * Mock Client Services.
 */
@SuppressWarnings({"BED_HIERARCHICAL_EXCEPTION_DECLARATION", "PMB_POSSIBLE_MEMORY_BLOAT"})
public class ClientServicesMockImpl implements ClientServices {

	@Override
	public List<String> retrieveTables() throws ServiceException {
		return MockDataUtil.retrieveTableNames();
	}

	@Override
	public List<CodeOption> retrieveCodes(final String table) throws ServiceException {
		List<CodeOption> options = MockDataUtil.retrieveTableCodes(table);
		if (options == null) {
			throw new ServiceException("Table not found [" + table + "]");
		}
		return options;
	}

	@Override
	public List<ClientDetail> searchClients(final String search) throws ServiceException {

		if ("error".equals(search)) {
			throw new ServiceException("Mock error");
		}

		if ("none".equals(search)) {
			return Collections.EMPTY_LIST;
		}

		return MockDataUtil.searchClients(search);
	}

	@Override
	public ClientDetail retrieveClient(final String clientId) throws ServiceException, ClientNotFoundException {
		ClientDetail detail = MockDataUtil.retrieveClient(clientId);
		if (detail == null) {
			throw new ClientNotFoundException();
		}
		return detail;
	}

	@Override
	public ClientDetail createClient(final ClientDetail detail) throws ServiceException {
		if ("error".equals(detail.getName())) {
			throw new ServiceException("Mock error");
		}
		return MockDataUtil.createClient(detail);
	}

	@Override
	public ClientDetail updateClient(final ClientDetail detail) throws ServiceException {
		if ("error".equals(detail.getName())) {
			throw new ServiceException("Mock error");
		}

		String key = detail.getClientId();
		if (MockDataUtil.retrieveClient(key) == null) {
			throw new ServiceException("Organisation does not exist [" + key + "].");
		}
		// Update
		MockDataUtil.updateClient(detail);
		return detail;
	}

	@Override
	public void deleteClient(final String clientId) throws ServiceException {
		// Check exists
		if (MockDataUtil.retrieveClient(clientId) == null) {
			throw new ServiceException("Organisation does not exist [" + clientId + "].");
		}
		// Remove
		MockDataUtil.deleteClient(clientId);
	}

	@Override
	public List<DocumentDetail> retrieveClientDocuments(final String clientId) throws ServiceException, ClientNotFoundException {
		return MockDataUtil.getOrCreateClientDocuments(clientId);
	}

	@Override
	public DocumentContent retrieveDocument(final String documentId) throws ServiceException, DocumentNotFoundException {

		DocumentDetail doc = MockDataUtil.retrieveDocument(documentId);
		if (doc == null) {
			throw new DocumentNotFoundException();
		}

		// Sleep for 3 seconds
		try {
			Thread.currentThread().sleep(3000);
		} catch (InterruptedException e) {
			throw new ServiceException("Could not process thread. " + e.getMessage(), e);
		}
		return MockDataUtil.retrieveContent(doc);
	}

}
