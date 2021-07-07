package com.sample.app.rest.mock.v1;

import com.sample.app.model.client.ClientDetail;
import com.sample.app.model.client.CodeOption;
import com.sample.app.model.client.DocumentContent;
import com.sample.app.model.client.DocumentDetail;
import com.sample.app.model.exception.ClientNotFoundException;
import com.sample.app.model.exception.DocumentNotFoundException;
import com.sample.app.model.exception.ServiceException;
import com.sample.app.model.impl.AppServicesMockImpl;
import com.sample.app.model.services.AppServices;
import com.sample.app.rest.exception.ApiException;
import com.sample.app.rest.mapping.v1.ModelMappingUtil;
import com.sample.app.rest.v1.api.V1Api;
import com.sample.app.rest.v1.model.ClientDetailDTO;
import com.sample.app.rest.v1.model.ClientDetailResponseDTO;
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

/**
 * API MOCK Implementation that uses a mocked backing model.
 */
public class V1ApiMockImpl implements V1Api {

	private final AppServices mocking = new AppServicesMockImpl();

	@Override
	public RetrieveTablesResponseDTO retrieveTables() {
		try {
			List<String> data = mocking.retrieveTables();
			RetrieveTablesResponseDTO resp = new RetrieveTablesResponseDTO();
			resp.setData(data);
			return resp;
		} catch (ServiceException e) {
			throw new ApiException(e.getMessage(), e);
		}
	}

	@Override
	public RetrieveCodesResponseDTO retrieveCodes(final String table) {
		try {
			List<CodeOption> data = mocking.retrieveCodes(table);
			List<CodeOptionDTO> dto = new ArrayList<>();
			data.forEach(opt -> {
				dto.add(ModelMappingUtil.getCodeOptionDTOFromModel(opt));
			});
			RetrieveCodesResponseDTO resp = new RetrieveCodesResponseDTO();
			resp.setData(dto);
			return resp;
		} catch (ServiceException e) {
			throw new ApiException(e.getMessage(), e);
		}
	}

	@Override
	public SearchClientsResponseDTO searchClients(final String search) {
		try {
			List<ClientDetail> data = mocking.searchClients(search);
			List<ClientDetailDTO> dto = new ArrayList<>();
			data.forEach(opt -> {
				dto.add(ModelMappingUtil.getClientDetailDTOFromModel(opt));
			});
			SearchClientsResponseDTO resp = new SearchClientsResponseDTO();
			resp.setData(dto);
			return resp;
		} catch (ServiceException e) {
			throw new ApiException(e.getMessage(), e);
		}
	}

	@Override
	public ClientDetailResponseDTO retrieveClient(final String clientId) {
		try {
			ClientDetail data = mocking.retrieveClient(clientId);
			ClientDetailDTO dto = ModelMappingUtil.getClientDetailDTOFromModel(data);
			ClientDetailResponseDTO resp = new ClientDetailResponseDTO();
			resp.setData(dto);
			return resp;
		} catch (ServiceException | ClientNotFoundException e) {
			throw new ApiException(e.getMessage(), e);
		}
	}

	@Override
	public ClientDetailResponseDTO createClient(final ClientDetailDTO detail) {
		try {
			ClientDetail model = ModelMappingUtil.getClientDetailFromDTO(detail);
			ClientDetail data = mocking.createClient(model);
			ClientDetailDTO dto = ModelMappingUtil.getClientDetailDTOFromModel(data);
			ClientDetailResponseDTO resp = new ClientDetailResponseDTO();
			resp.setData(dto);
			return resp;
		} catch (ServiceException e) {
			throw new ApiException(e.getMessage(), e);
		}
	}

	@Override
	public ClientDetailResponseDTO updateClient(final String clientId, final ClientDetailDTO detail) {
		// Check IDs
		if (!Objects.equals(clientId, detail.getClientId())) {
			throw new ApiException("Client ID does not match ID in details.");
		}
		try {
			ClientDetail model = ModelMappingUtil.getClientDetailFromDTO(detail);
			ClientDetail data = mocking.updateClient(model);
			ClientDetailDTO dto = ModelMappingUtil.getClientDetailDTOFromModel(data);
			ClientDetailResponseDTO resp = new ClientDetailResponseDTO();
			resp.setData(dto);
			return resp;
		} catch (ServiceException e) {
			throw new ApiException(e.getMessage(), e);
		}
	}

	@Override
	public void deleteClient(final String clientId) {
		try {
			mocking.deleteClient(clientId);
		} catch (ServiceException e) {
			throw new ApiException(e.getMessage(), e);
		}
	}

	@Override
	public RetrieveClientDocumentsResponseDTO retrieveClientDocuments(final String clientId) {
		try {
			List<DocumentDetail> data = mocking.retrieveClientDocuments(clientId);
			List<DocumentDetailDTO> dto = new ArrayList<>();
			data.forEach(opt -> {
				dto.add(ModelMappingUtil.getDocumentDetailDTOFromModel(opt));
			});
			RetrieveClientDocumentsResponseDTO resp = new RetrieveClientDocumentsResponseDTO();
			resp.setData(dto);
			return resp;
		} catch (ServiceException | ClientNotFoundException e) {
			throw new ApiException(e.getMessage(), e);
		}
	}

	@Override
	public DocumentContentResponseDTO retrieveDocument(final String documentId) {
		try {
			DocumentContent data = mocking.retrieveDocument(documentId);
			DocumentContentDTO dto = ModelMappingUtil.getDocumentContentDTOFromModel(data);
			DocumentContentResponseDTO resp = new DocumentContentResponseDTO();
			resp.setData(dto);
			return resp;
		} catch (ServiceException | DocumentNotFoundException e) {
			throw new ApiException(e.getMessage(), e);
		}
	}

}
