package com.sample.app.rest.mapping.v1;

import com.sample.app.model.client.ClientDetail;
import com.sample.app.model.client.CodeOption;
import com.sample.app.model.client.DocumentContent;
import com.sample.app.model.client.DocumentDetail;
import com.sample.app.rest.v1.model.ClientDetailDTO;
import com.sample.app.rest.v1.model.CodeOptionDTO;
import com.sample.app.rest.v1.model.DocumentContentDTO;
import com.sample.app.rest.v1.model.DocumentDetailDTO;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

/**
 * Helper class to map between the backing Model and DTO classes.
 */
public final class ModelMappingUtil {

	/**
	 * Prevent instantiation.
	 */
	private ModelMappingUtil() {
		// Do nothing
	}

	/**
	 * @param model the model code option to map
	 * @return the DTO code option
	 */
	public static CodeOptionDTO getCodeOptionDTOFromModel(final CodeOption model) {
		return getMapper().map(model, CodeOptionDTO.class);
	}

	/**
	 * @param dto the DTO client detail to map
	 * @return the model client detail
	 */
	public static ClientDetail getClientDetailFromDTO(final ClientDetailDTO dto) {
		TypeMap<ClientDetailDTO, ClientDetail> typeMap = getMapper().createTypeMap(ClientDetailDTO.class, ClientDetail.class);
		typeMap.addMappings(mapper -> mapper.using(LOCALDATE_TO_DATE_CONVERTER).map(ClientDetailDTO::getSampleOnlyDate, ClientDetail::setSampleOnlyDate));
		return typeMap.map(dto);
	}

	/**
	 * @param model the model client detail to map
	 * @return the DTO client detail
	 */
	public static ClientDetailDTO getClientDetailDTOFromModel(final ClientDetail model) {
		TypeMap<ClientDetail, ClientDetailDTO> typeMap = getMapper().createTypeMap(ClientDetail.class, ClientDetailDTO.class);
		typeMap.addMappings(mapper -> mapper.using(DATE_TO_LOCALDATE_CONVERTER).map(ClientDetail::getSampleOnlyDate, ClientDetailDTO::setSampleOnlyDate));
		return typeMap.map(model);
	}

	/**
	 * @param model the model document detail to map
	 * @return the DTO document detail
	 */
	public static DocumentDetailDTO getDocumentDetailDTOFromModel(final DocumentDetail model) {
		TypeMap<DocumentDetail, DocumentDetailDTO> typeMap = getMapper().createTypeMap(DocumentDetail.class, DocumentDetailDTO.class);
		typeMap.addMappings(mapper -> mapper.using(DATE_TO_LOCALDATE_CONVERTER).map(DocumentDetail::getSubmitDate, DocumentDetailDTO::setSubmitDate));
		return typeMap.map(model);
	}

	/**
	 * @param model the model document content to map
	 * @return the DTO document content
	 */
	public static DocumentContentDTO getDocumentContentDTOFromModel(final DocumentContent model) {
		return getMapper().map(model, DocumentContentDTO.class);
	}

	private static ModelMapper getMapper() {
		// Check if can use same instance
		return new ModelMapper();
	}

	/**
	 * Custom converter for Date to LocalDate as mapper only handles the Date type to LocalDateTime.
	 */
	private static final Converter<Date, LocalDate> DATE_TO_LOCALDATE_CONVERTER = (context) -> {
		Date source = context.getSource();
		return source == null ? null : source.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	};

	/**
	 * Custom converter for LocalDate to Date as mapper only handles the LocalDateTime type to Date.
	 */
	private static final Converter<LocalDate, Date> LOCALDATE_TO_DATE_CONVERTER = mappingContext -> {
		LocalDate source = mappingContext.getSource();
		return source == null ? null : Date.from(source.atStartOfDay(ZoneId.systemDefault()).toInstant());
	};

}
