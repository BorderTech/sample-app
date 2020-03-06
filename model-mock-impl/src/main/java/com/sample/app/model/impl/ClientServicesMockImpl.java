package com.sample.app.model.impl;

import com.sample.app.model.client.ClientDetail;
import com.sample.app.model.client.CodeOption;
import com.sample.app.model.client.DocumentContent;
import com.sample.app.model.client.DocumentDetail;
import com.sample.app.model.exception.ClientNotFoundException;
import com.sample.app.model.exception.DocumentNotFoundException;
import com.sample.app.model.exception.ServiceException;
import com.sample.app.model.services.ClientServices;
import java.util.Collections;
import java.util.List;

/**
 * Mock Client Services.
 */
@SuppressWarnings({"BED_HIERARCHICAL_EXCEPTION_DECLARATION", "PMB_POSSIBLE_MEMORY_BLOAT"})
public class ClientServicesMockImpl implements ClientServices {

	private static final String ERROR_REQ = "error";
	private static final String NONE_REQ = "none";

	@Override
	public List<String> retrieveTables() throws ServiceException {
		return MockDataUtil.retrieveTableNames();
	}

	@Override
	public List<CodeOption> retrieveCodes(final String table) throws ServiceException {
		// Mock error
		if (ERROR_REQ.equals(table)) {
			throw new ServiceException("Mock table error");
		}

		List<CodeOption> options = MockDataUtil.retrieveTableCodes(table);
		if (options == null) {
			throw new ServiceException("Table not found [" + table + "]");
		}
		return options;
	}

	@Override
	public List<ClientDetail> searchClients(final String search) throws ServiceException {

		// Mock error
		if (ERROR_REQ.equals(search)) {
			throw new ServiceException("Mock search error");
		}

		// None found
		if (NONE_REQ.equals(search)) {
			return Collections.EMPTY_LIST;
		}

		return MockDataUtil.searchClients(search);
	}

	@Override
	public ClientDetail retrieveClient(final String clientId) throws ServiceException, ClientNotFoundException {
		// Mock error
		if (ERROR_REQ.equals(clientId)) {
			throw new ServiceException("Mock retrieve client error");
		}
		ClientDetail detail = MockDataUtil.retrieveClient(clientId);
		if (detail == null) {
			throw new ClientNotFoundException();
		}
		return detail;
	}

	@Override
	public ClientDetail createClient(final ClientDetail detail) throws ServiceException {
		// Mock error
		if (ERROR_REQ.equals(detail.getName())) {
			throw new ServiceException("Mock create client error");
		}
		return MockDataUtil.createClient(detail);
	}

	@Override
	public ClientDetail updateClient(final ClientDetail detail) throws ServiceException {
		// Mock error
		if (ERROR_REQ.equals(detail.getName())) {
			throw new ServiceException("Mock update client error");
		}

		String key = detail.getClientId();
		if (MockDataUtil.retrieveClient(key) == null) {
			throw new ServiceException("Client does not exist [" + key + "].");
		}
		// Update
		MockDataUtil.updateClient(detail);
		return detail;
	}

	@Override
	public void deleteClient(final String clientId) throws ServiceException {
		// Mock error
		if (ERROR_REQ.equals(clientId)) {
			throw new ServiceException("Mock delete client error");
		}

		// Check exists
		if (MockDataUtil.retrieveClient(clientId) == null) {
			throw new ServiceException("Client does not exist [" + clientId + "].");
		}
		// Remove
		MockDataUtil.deleteClient(clientId);
	}

	@Override
	public List<DocumentDetail> retrieveClientDocuments(final String clientId) throws ServiceException, ClientNotFoundException {
		// Mock error
		if (ERROR_REQ.equals(clientId)) {
			throw new ServiceException("Mock retrieve client documents");
		}
		return MockDataUtil.getOrCreateClientDocuments(clientId);
	}

	@Override
	public DocumentContent retrieveDocument(final String documentId) throws ServiceException, DocumentNotFoundException {
		// Mock error
		if (ERROR_REQ.equals(documentId)) {
			throw new ServiceException("Mock retrieve document content");
		}
		DocumentDetail doc = MockDataUtil.retrieveDocument(documentId);
		if (doc == null) {
			throw new DocumentNotFoundException();
		}

		// Sleep for 3 seconds
		try {
			Thread.currentThread().sleep(3000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			throw new ServiceException("Could not process thread. " + e.getMessage(), e);
		}
		return MockDataUtil.retrieveContent(doc);
	}

}
