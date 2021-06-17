package com.sample.app.rest.servlet;

import com.github.bordertech.restfriends.envelope.ErrorDetail;
import com.github.bordertech.restfriends.envelope.ErrorEnvelope;
import com.github.bordertech.restfriends.envelope.impl.ErrorResponse;
import javax.validation.ValidationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Handle the bean validation exceptions.
 */
@Provider
public class MyHandleValidationExceptions implements ExceptionMapper<ValidationException> {

	@Override
	public Response toResponse(final ValidationException exception) {
		ErrorDetail errorDetail = new ErrorDetail(400, exception.getMessage());
		ErrorEnvelope resp = new ErrorResponse(errorDetail);
		return Response.status(errorDetail.getStatus()).
				entity(resp).
				type("application/json").
				build();
	}
}
