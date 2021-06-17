package com.sample.app.rest.servlet;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.github.bordertech.restfriends.envelope.ErrorDetail;
import com.github.bordertech.restfriends.envelope.ErrorEnvelope;
import com.github.bordertech.restfriends.envelope.impl.ErrorResponse;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Handle the JSON mapping exceptions (eg parsing date and time).
 */
@Provider
public class MyHandleJsonMappingExceptions implements ExceptionMapper<JsonMappingException> {

	@Override
	public Response toResponse(final JsonMappingException exception) {
		ErrorDetail errorDetail = new ErrorDetail(400, exception.getMessage());
		ErrorEnvelope resp = new ErrorResponse(errorDetail);
		return Response.status(errorDetail.getStatus()).
				entity(resp).
				type("application/json").
				build();
	}
}
