package com.sample.app.rest.api.v1.model;

import com.github.bordertech.restfriends.envelope.DataEnvelope;
import com.sample.app.model.client.ClientDetail;

/**
 * Retrieve client response.
 */
public class ClientDetailResponse implements DataEnvelope<ClientDetail> {

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

}
