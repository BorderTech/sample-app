package com.sample.app.rest.server.v1.handler;

import com.github.bordertech.restfriends.exception.RestStatusCodable;
import com.sample.app.rest.v1.model.ErrorDetailDTO;
import com.sample.app.rest.v1.model.ErrorResponseDTO;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Handle all exceptions (includes JSON mapping and parser exceptions) and format to DTO error response.
 */
@Provider
public class MyCatchAllExceptions implements ExceptionMapper<Throwable> {

	@Override
	public Response toResponse(final Throwable exception) {
		int status = 400;
		if (exception instanceof RestStatusCodable) {
			status = ((RestStatusCodable) exception).getStatusCode();
		}
		ErrorDetailDTO errorDetail = new ErrorDetailDTO();
		errorDetail.setStatus(status);
		errorDetail.setMessage(exception.getMessage());
		ErrorResponseDTO resp = new ErrorResponseDTO();
		resp.setError(errorDetail);
		return Response.status(errorDetail.getStatus()).
				entity(resp).
				type("application/json").
				build();
	}
}