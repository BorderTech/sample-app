package com.sample.app.rest.api.v1.model;

import com.github.bordertech.restfriends.envelope.DataEnvelope;
import com.sample.app.model.client.DocumentDetail;
import java.util.ArrayList;

/**
 * Retrieve documents response.
 */
public class RetrieveClientDocumentsResponse implements DataEnvelope<ArrayList<DocumentDetail>> {

	private ArrayList<DocumentDetail> data;

	/**
	 * Default constructor.
	 */
	public RetrieveClientDocumentsResponse() {
	}

	/**
	 * @param data the code options
	 */
	public RetrieveClientDocumentsResponse(final ArrayList<DocumentDetail> data) {
		this.data = data;
	}

	@Override
	public ArrayList<DocumentDetail> getData() {
		if (data == null) {
			data = new ArrayList<>();
		}
		return data;
	}

	@Override
	public void setData(final ArrayList<DocumentDetail> data) {
		this.data = data;
	}

}
