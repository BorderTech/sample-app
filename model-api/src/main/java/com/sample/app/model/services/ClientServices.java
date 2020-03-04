package com.sample.app.model.services;

import com.sample.app.model.client.ClientDetail;
import com.sample.app.model.client.CodeOption;
import com.sample.app.model.client.DocumentContent;
import com.sample.app.model.client.DocumentDetail;
import com.sample.app.model.exception.ClientNotFoundException;
import com.sample.app.model.exception.DocumentNotFoundException;
import com.sample.app.model.exception.ServiceException;
import java.util.List;

/**
 * Client services interface.
 */
public interface ClientServices {

	/**
	 * Retrieve table names.
	 *
	 * @return the list of table names
	 * @throws ServiceException a service exception
	 */
	List<String> retrieveTables() throws ServiceException;

	/**
	 * Retrieve the codes for a table.
	 *
	 * @param table the table name to retrieve
	 * @return the list of code options
	 * @throws ServiceException a service exception
	 */
	List<CodeOption> retrieveCodes(String table) throws ServiceException;

	/**
	 * Search clients.
	 *
	 * @param search the search criteria
	 * @return the list of clients
	 * @throws ServiceException a service exception
	 */
	List<ClientDetail> searchClients(String search) throws ServiceException;

	/**
	 * Retrieve a client.
	 *
	 * @param clientId the client id to retrieve
	 * @return the details
	 * @throws ServiceException a service exception
	 * @throws ClientNotFoundException client not found
	 */
	ClientDetail retrieveClient(String clientId) throws ServiceException, ClientNotFoundException;

	/**
	 * Create a client.
	 *
	 * @param detail the details to create
	 * @return the created client
	 * @throws ServiceException a service exception
	 */
	ClientDetail createClient(ClientDetail detail) throws ServiceException;

	/**
	 * Update a client.
	 *
	 * @param detail the updated details
	 * @return the updated client
	 * @throws ServiceException a service exception
	 */
	ClientDetail updateClient(ClientDetail detail) throws ServiceException;

	/**
	 * Delete a client.
	 *
	 * @param clientId the client Id to delete
	 * @throws ServiceException a service exception
	 */
	void deleteClient(String clientId) throws ServiceException;

	/**
	 * Retrieve client documents.
	 *
	 * @param clientId the client id to retrieve documents for
	 * @return the client documents
	 * @throws ServiceException a service exception
	 * @throws ClientNotFoundException client not found
	 */
	List<DocumentDetail> retrieveClientDocuments(String clientId) throws ServiceException, ClientNotFoundException;

	/**
	 * Retrieve document contents.
	 *
	 * @param documentId the document id
	 * @return the document content
	 * @throws ServiceException a service exception
	 * @throws DocumentNotFoundException document not found exception
	 */
	DocumentContent retrieveDocument(String documentId) throws ServiceException, DocumentNotFoundException;

}
