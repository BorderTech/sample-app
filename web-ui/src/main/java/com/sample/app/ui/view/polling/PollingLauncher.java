package com.sample.app.ui.view.polling;

import com.github.bordertech.taskmaster.service.ResultHolder;
import com.github.bordertech.wcomponents.Request;
import com.github.bordertech.wcomponents.WContent;
import com.github.bordertech.wcomponents.WLink;
import com.github.bordertech.wcomponents.WPopup;
import com.github.bordertech.wcomponents.addons.polling.PollingServicePanel;
import com.github.bordertech.wcomponents.addons.polling.PollingStartType;
import com.sample.app.rest.v1.model.DocumentContentDTO;
import com.sample.app.rest.v1.model.DocumentDetailDTO;
import com.sample.app.ui.view.DocumentView;
import javax.cache.Cache;

/**
 * Polling panel that provides a link to the document content (once retrieved).
 */
public class PollingLauncher extends PollingServicePanel<DocumentDetailDTO, DocumentContentDTO> {

	private final WContent content = new WContent();
	private final WPopup popup = new WPopup() {
		@Override
		public String getUrl() {
			return content.getUrl();
		}
	};
	// Link to content (after retrieved result)
	private final WLink link = new WLink() {
		@Override
		public String getUrl() {
			return content.getUrl();
		}
	};

	/**
	 * Construct polling launcher.
	 */
	public PollingLauncher() {
		this(null);
	}

	/**
	 * @param document the polling launcher document details
	 */
	public PollingLauncher(final DocumentDetailDTO document) {
		setServiceCriteria(document);
		getContentResultHolder().add(content);
		getContentResultHolder().add(popup);
		getContentResultHolder().add(link);
		// Service action
		setServiceAction(DocumentView.RETREIVE_DOC_ACTION);
		// Start with Button
		setStartType(PollingStartType.BUTTON);
		getStartButton().setRenderAsLink(true);
	}

	/**
	 * Configure for pop up only.
	 */
	public void popupOnly() {
		link.setVisible(false);
		setStartType(PollingStartType.AUTOMATIC);
		getStartButton().setVisible(false);
	}

	@Override
	protected void handleInitPollingPanel(final Request request) {
		super.handleInitPollingPanel(request);
		DocumentDetailDTO doc = getServiceCriteria();
		getStartButton().setText(doc.getResourcePath());
	}

	@Override
	protected void handleInitResultContent(final Request request) {
		super.handleInitResultContent(request);
		// Setup the document details on the Link
		DocumentContentDTO doc = getServiceResult().getResult();
		String key = doc.getDocumentId() + "-" + doc.getFilename();
		content.setContentAccess(new CachedContentWrapper(doc.getFilename(), doc.getMimeType(), doc.getDocumentId()));
		content.setCacheKey(key);
		popup.setTargetWindow(key);
		popup.setVisible(true);
		link.setText(getStartButton().getText());
	}

	@Override
	public String getServiceCacheKey() {
		return getServiceCriteria().getDocumentId();
	}

	@Override
	protected Cache<String, ResultHolder> getServiceCache() {
		return DocumentView.CACHE;
	}

}
