package com.sample.app.rest.api.v1.model;

import com.github.bordertech.restfriends.envelope.DataEnvelope;
import com.sample.app.model.client.CodeOption;
import java.util.ArrayList;

/**
 * Retrieve codes response.
 */
public class RetrieveCodesResponse implements DataEnvelope<ArrayList<CodeOption>> {

	private ArrayList<CodeOption> data;

	/**
	 * Default constructor.
	 */
	public RetrieveCodesResponse() {
	}

	/**
	 * @param data the code options
	 */
	public RetrieveCodesResponse(final ArrayList<CodeOption> data) {
		this.data = data;
	}

	@Override
	public ArrayList<CodeOption> getData() {
		if (data == null) {
			data = new ArrayList<>();
		}
		return data;
	}

	@Override
	public void setData(final ArrayList<CodeOption> data) {
		this.data = data;
	}

}
