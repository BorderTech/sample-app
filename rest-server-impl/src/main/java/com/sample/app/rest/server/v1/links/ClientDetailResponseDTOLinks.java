package com.sample.app.rest.server.v1.links;

import com.sample.app.rest.v1.model.ClientDetailResponseDTO;
import java.util.List;
import javax.ws.rs.core.Link;
import org.glassfish.jersey.linking.Binding;
import org.glassfish.jersey.linking.InjectLink;
import org.glassfish.jersey.linking.InjectLinks;

/**
 * ClientDetailResponse with jersey declarative links.
 * <p>
 * As the swagger Open API 2.0 version does not support links we are manually adding the Links to the response classes
 * for jersey to inject.
 * </p>
 */
public class ClientDetailResponseDTOLinks extends ClientDetailResponseDTO {

	@InjectLinks({
		@InjectLink(value = "clients/{id}", rel = "self", bindings = {
			@Binding(name = "id", value = "${instance.data.clientId}")})
	})
	private List<Link> links;

	/**
	 * @return the declarative links for this response
	 */
	public List<Link> getLinks() {
		return links;
	}

	/**
	 * @param links the declarative links
	 */
	public void setLinks(final List<Link> links) {
		this.links = links;
	}

}
