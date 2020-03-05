package com.sample.app.rest.api.v1;

import com.github.bordertech.restfriends.envelope.impl.ErrorResponse;
import com.github.bordertech.restfriends.exception.RestBusinessException;
import com.sample.app.model.client.ClientDetail;
import com.sample.app.rest.api.v1.model.ClientDetailResponse;
import com.sample.app.rest.api.v1.model.DocumentContentResponse;
import com.sample.app.rest.api.v1.model.RetrieveClientDocumentsResponse;
import com.sample.app.rest.api.v1.model.RetrieveCodesResponse;
import com.sample.app.rest.api.v1.model.RetrieveTablesResponse;
import com.sample.app.rest.api.v1.model.SearchClientsResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Sample REST Resource call a backing service implementation.
 */
@Api(value = "ClientServices")
@Path(value = "v1")
@ApiResponses(value = {
	@ApiResponse(code = 400, message = "Invalid request", response = ErrorResponse.class)
	,
	@ApiResponse(code = 500, message = "Internal error", response = ErrorResponse.class)
})
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@SuppressWarnings("NoWhitespaceBefore")
public interface ClientServicesResource {

	/**
	 * Retrieve table names.
	 *
	 * @return the table names
	 * @throws RestBusinessException a business exception
	 */
	@GET
	@Path("tables")
	@ApiOperation(value = "Retrieve table names")
	RetrieveTablesResponse retrieveTables() throws RestBusinessException;

	/**
	 * Retrieve table codes.
	 *
	 * @param table the table name
	 * @return the table codes
	 * @throws RestBusinessException a business exception
	 */
	@GET
	@Path("tables/{table}")
	@ApiOperation(value = "Retrieve codes for a table")
	public RetrieveCodesResponse retrieveCodes(
			@ApiParam(value = "Table name") @PathParam("table") String table)
			throws RestBusinessException;

	/**
	 * Search clients.
	 *
	 * @param search the search criteria
	 * @return the matching clients
	 * @throws RestBusinessException a business exception
	 */
	@GET
	@Path("clients")
	@ApiOperation(value = "Search clients")
	public SearchClientsResponse searchClients(
			@ApiParam(value = "Search criteria") @QueryParam("search") String search)
			throws RestBusinessException;

	/**
	 * Retrieve client.
	 *
	 * @param clientId the client id
	 * @return the client details
	 * @throws RestBusinessException a business exception
	 */
	@GET
	@Path("clients/{id}")
	@ApiOperation(value = "Retrieve client")
	public ClientDetailResponse retrieveClient(
			@ApiParam(value = "Client id") @PathParam("id") String clientId)
			throws RestBusinessException;

	/**
	 * Create client.
	 *
	 * @param detail the client details
	 * @return the client details
	 * @throws RestBusinessException a business exception
	 */
	@POST
	@Path("clients")
	@ApiOperation(value = "Create client")
	public ClientDetailResponse createClient(
			@ApiParam(value = "Client details") ClientDetail detail)
			throws RestBusinessException;

	/**
	 * Update client.
	 *
	 * @param clientId the client id
	 * @param detail the client details
	 * @return the client details
	 * @throws RestBusinessException a business exception
	 */
	@PUT
	@Path("clients/{id}")
	@ApiOperation(value = "Update client")
	public ClientDetailResponse updateClient(
			@ApiParam(value = "Client id") @PathParam("id") String clientId,
			@ApiParam(value = "Client details") ClientDetail detail)
			throws RestBusinessException;

	/**
	 * Delete client.
	 *
	 * @param clientId the client id
	 * @return the OK response
	 * @throws RestBusinessException a business exception
	 */
	@DELETE
	@Path("clients/{id}")
	@ApiOperation(value = "Delete client")
	public Response deleteClient(
			@ApiParam(value = "Client id") @PathParam("id") String clientId)
			throws RestBusinessException;

	/**
	 * Retrieve client documents.
	 *
	 * @param clientId the client id
	 * @return the client documents
	 * @throws RestBusinessException a business exception
	 */
	@GET
	@Path("clients/{id}/documents")
	@ApiOperation(value = "Retrieve client documents")
	public RetrieveClientDocumentsResponse retrieveClientDocuments(
			@ApiParam(value = "Client id") @PathParam("id") String clientId)
			throws RestBusinessException;

	/**
	 * Retrieve client document content.
	 *
	 * @param documentId the document id
	 * @return the client documents
	 * @throws RestBusinessException a business exception
	 */
	@GET
	@Path("documents/{docId}")
	@ApiOperation(value = "Retrieve client documents")
	public DocumentContentResponse retrieveDocument(
			@ApiParam(value = "Document id") @PathParam("docId") String documentId)
			throws RestBusinessException;

}
