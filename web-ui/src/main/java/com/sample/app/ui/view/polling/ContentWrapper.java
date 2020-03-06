package com.sample.app.ui.view.polling;

import com.github.bordertech.taskmaster.service.ResultHolder;
import com.github.bordertech.wcomponents.ContentAccess;
import com.sample.app.model.client.DocumentContent;

/**
 * Wrap the document content.
 */
public abstract class ContentWrapper implements ContentAccess {

	private final String fileName;
	private final String mimeType;

	/**
	 * @param fileName the file name
	 * @param mimeType the mime type
	 */
	public ContentWrapper(final String fileName, final String mimeType) {
		this.fileName = fileName;
		this.mimeType = mimeType;
	}

	@Override
	public byte[] getBytes() {
		return getDocument().getResult().getBytes();
	}

	@Override
	public String getDescription() {
		return fileName;
	}

	@Override
	public String getMimeType() {
		return mimeType;
	}

	/**
	 *
	 * @return the service result (preferably from a cache)
	 */
	protected abstract ResultHolder<String, DocumentContent> getDocument();

}
