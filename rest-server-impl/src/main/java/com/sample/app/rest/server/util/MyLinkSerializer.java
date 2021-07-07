package com.sample.app.rest.server.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import javax.ws.rs.core.Link;
import org.glassfish.jersey.message.internal.JerseyLink;

/**
 * Custom serializer for Declarative Links.
 * <p>
 * This can be used if all the default fields included with the Link object are not needed.
 * </p>
 *
 * @see JerseyLink
 * @see Link
 */
public class MyLinkSerializer extends JsonSerializer<Link> {

	@Override
	public void serialize(final Link link, final JsonGenerator jg, final SerializerProvider sp) throws IOException {
		jg.writeStartObject();
		jg.writeStringField("rel", link.getRel());
		jg.writeStringField("uri", link.getUri().toString());
		jg.writeEndObject();
	}
}
