package com.sample.app.rest.api.v1.model;

import com.github.bordertech.restfriends.envelope.DataEnvelope;
import com.sample.app.model.client.ClientDetail;
import java.util.ArrayList;

/**
 * Search clients response.
 */
public class SearchClientsResponse implements DataEnvelope<ArrayList<ClientDetail>> {

	private ArrayList<ClientDetail> data;

	/**
	 * Default constructor.
	 */
	public SearchClientsResponse() {
	}

	/**
	 * @param data the client details
	 */
	public SearchClientsResponse(final ArrayList<ClientDetail> data) {
		this.data = data;
	}

	@Override
	public ArrayList<ClientDetail> getData() {
		if (data == null) {
			data = new ArrayList<>();
		}
		return data;
	}

	@Override
	public void setData(final ArrayList<ClientDetail> data) {
		this.data = data;
	}

}
