package com.sample.app.rest.client.v1.helper;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import javax.ws.rs.core.Link;

/**
 * Custom deserializer for Declarative Links.
 * <p>
 * The Deserializer provided by Jackson (in module jackson-datatype-jaxrs) does not seem to handle an array of links at
 * the moment, so using a custom serializer.
 * </p>
 *
 * @see Link
 */
public class MyLinkDeserializer extends JsonDeserializer<Link> {

	@Override
	public Link deserialize(final JsonParser p, final DeserializationContext ctxt) throws IOException {
		Link.Builder builder = Link.fromUri(p.getValueAsString("uri"));
		builder.rel(p.getValueAsString("rel"));
		return builder.build();
	}
}
