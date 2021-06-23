package com.sample.app.rest.api.v1.model;

import com.github.bordertech.restfriends.envelope.DataEnvelope;
import com.sample.app.model.client.ClientDetail;
import java.util.List;
import javax.ws.rs.core.Link;
import org.glassfish.jersey.linking.InjectLinks;

/**
 * Retrieve client response.
 */
public class ClientDetailResponse implements DataEnvelope<ClientDetail> {

	@InjectLinks()
	private List<Link> links;

	private ClientDetail data;

	/**
	 * Default constructor.
	 */
	public ClientDetailResponse() {
	}

	/**
	 * @param data the organisation details
	 */
	public ClientDetailResponse(final ClientDetail data) {
		this.data = data;
	}

	@Override
	public ClientDetail getData() {
		return data;
	}

	@Override
	public void setData(final ClientDetail data) {
		this.data = data;
	}

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
