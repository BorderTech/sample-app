package com.sample.app.ui.view.polling;

import com.github.bordertech.taskmaster.service.ResultHolder;
import com.sample.app.model.client.DocumentContent;
import com.sample.app.ui.view.DocumentView;

/**
 * Wrap the document content.
 */
public class CachedContentWrapper extends ContentWrapper {

	private final String documentId;

	/**
	 * @param fileName the file name
	 * @param mimeType the mime type
	 * @param documentId the document id
	 */
	public CachedContentWrapper(final String fileName, final String mimeType, final String documentId) {
		super(fileName, mimeType);
		this.documentId = documentId;
	}

	/**
	 * @return the service result from the CACHE
	 */
	@Override
	protected ResultHolder<String, DocumentContent> getDocument() {
		return DocumentView.CACHE.get(documentId);
	}

}
