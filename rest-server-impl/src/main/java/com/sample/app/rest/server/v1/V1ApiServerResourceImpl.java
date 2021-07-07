package com.sample.app.rest.server.v1;

import com.github.bordertech.didums.Didums;
import com.sample.app.model.client.ClientDetail;
import com.sample.app.model.client.CodeOption;
import com.sample.app.model.client.DocumentContent;
import com.sample.app.model.client.DocumentDetail;
import com.sample.app.model.exception.ClientNotFoundException;
import com.sample.app.model.exception.DocumentNotFoundException;
import com.sample.app.model.exception.ServiceException;
import com.sample.app.model.services.AppServices;
import com.sample.app.rest.mapping.v1.ModelMappingUtil;
import com.sample.app.rest.server.util.ClientErrorException;
import com.sample.app.rest.server.v1.api.V1Api;
import com.sample.app.rest.server.v1.links.ClientDetailResponseDTOLinks;
import com.sample.app.rest.v1.model.ClientDetailDTO;
import com.sample.app.rest.v1.model.CodeOptionDTO;
import com.sample.app.rest.v1.model.DocumentContentDTO;
import com.sample.app.rest.v1.model.DocumentContentResponseDTO;
import com.sample.app.rest.v1.model.DocumentDetailDTO;
import com.sample.app.rest.v1.model.RetrieveClientDocumentsResponseDTO;
import com.sample.app.rest.v1.model.RetrieveCodesResponseDTO;
import com.sample.app.rest.v1.model.RetrieveTablesResponseDTO;
import com.sample.app.rest.v1.model.SearchClientsResponseDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.ws.rs.core.Response;

/**
 * Sample REST Resource call a backing service model implementation.
 */
public class V1ApiServerResourceImpl implements V1Api {

	private final AppServices backing = Didums.getService(AppServices.class);

	@Override
	public Response retrieveTables() {
		try {
			List<String> data = backing.retrieveTables();
			RetrieveTablesResponseDTO resp = new RetrieveTablesResponseDTO();
			resp.setData(data);
			return Response.ok(resp).build();
		} catch (ServiceException e) {
			throw new ClientErrorException(e.getMessage(), e);
		}
	}

	@Override
	public Response retrieveCodes(final String table) {
		try {
			List<CodeOption> data = backing.retrieveCodes(table);
			List<CodeOptionDTO> dto = new ArrayList<>();
			data.forEach(opt -> {
				dto.add(ModelMappingUtil.getCodeOptionDTOFromModel(opt));
			});
			RetrieveCodesResponseDTO resp = new RetrieveCodesResponseDTO();
			resp.setData(dto);
			return Response.ok(resp).build();
		} catch (ServiceException e) {
			throw new ClientErrorException(e.getMessage(), e);
		}
	}

	@Override
	public Response searchClients(final String search) {
		try {
			List<ClientDetail> data = backing.searchClients(search);
			List<ClientDetailDTO> dto = new ArrayList<>();
			data.forEach(opt -> {
				dto.add(ModelMappingUtil.getClientDetailDTOFromModel(opt));
			});
			SearchClientsResponseDTO resp = new SearchClientsResponseDTO();
			resp.setData(dto);
			return Response.ok(resp).build();
		} catch (ServiceException e) {
			throw new ClientErrorException(e.getMessage(), e);
		}
	}

	@Override
	public Response retrieveClient(final String clientId) {
		try {
			ClientDetail data = backing.retrieveClient(clientId);
			ClientDetailDTO dto = ModelMappingUtil.getClientDetailDTOFromModel(data);
			ClientDetailResponseDTOLinks resp = new ClientDetailResponseDTOLinks();
			resp.setData(dto);
			return Response.ok(resp).build();
		} catch (ServiceException | ClientNotFoundException e) {
			throw new ClientErrorException(e.getMessage(), e);
		}
	}

	@Override
	public Response createClient(final ClientDetailDTO detail) {
		try {
			ClientDetail model = ModelMappingUtil.getClientDetailFromDTO(detail);
			ClientDetail data = backing.createClient(model);
			ClientDetailDTO dto = ModelMappingUtil.getClientDetailDTOFromModel(data);
			ClientDetailResponseDTOLinks resp = new ClientDetailResponseDTOLinks();
			resp.setData(dto);
			return Response.status(Response.Status.CREATED).entity(resp).build();
		} catch (ServiceException e) {
			throw new ClientErrorException(e.getMessage(), e);
		}
	}

	@Override
	public Response updateClient(final String clientId, final ClientDetailDTO detail) {
		// Check IDs
		if (!Objects.equals(clientId, detail.getClientId())) {
			throw new ClientErrorException("Client ID does not match ID in details.");
		}
		try {
			ClientDetail model = ModelMappingUtil.getClientDetailFromDTO(detail);
			ClientDetail data = backing.updateClient(model);
			ClientDetailDTO dto = ModelMappingUtil.getClientDetailDTOFromModel(data);
			ClientDetailResponseDTOLinks resp = new ClientDetailResponseDTOLinks();
			resp.setData(dto);
			return Response.ok(resp).build();
		} catch (ServiceException e) {
			throw new ClientErrorException(e.getMessage(), e);
		}
	}

	@Override
	public Response deleteClient(final String clientId) {
		try {
			backing.deleteClient(clientId);
			return Response.noContent().build();
		} catch (ServiceException e) {
			throw new ClientErrorException(e.getMessage(), e);
		}
	}

	@Override
	public Response retrieveClientDocuments(final String clientId) {
		try {
			List<DocumentDetail> data = backing.retrieveClientDocuments(clientId);
			List<DocumentDetailDTO> dto = new ArrayList<>();
			data.forEach(opt -> {
				dto.add(ModelMappingUtil.getDocumentDetailDTOFromModel(opt));
			});
			RetrieveClientDocumentsResponseDTO resp = new RetrieveClientDocumentsResponseDTO();
			resp.setData(dto);
			return Response.ok(resp).build();
		} catch (ServiceException | ClientNotFoundException e) {
			throw new ClientErrorException(e.getMessage(), e);
		}
	}

	@Override
	public Response retrieveDocument(final String documentId) {
		try {
			DocumentContent data = backing.retrieveDocument(documentId);
			DocumentContentDTO dto = ModelMappingUtil.getDocumentContentDTOFromModel(data);
			DocumentContentResponseDTO resp = new DocumentContentResponseDTO();
			resp.setData(dto);
			return Response.ok(resp).build();
		} catch (ServiceException | DocumentNotFoundException e) {
			throw new ClientErrorException(e.getMessage(), e);
		}
	}

}
