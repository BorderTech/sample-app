package com.sample.app.ui.application;

import com.github.bordertech.wcomponents.HeadingLevel;
import com.github.bordertech.wcomponents.Margin;
import com.github.bordertech.wcomponents.MessageContainer;
import com.github.bordertech.wcomponents.Request;
import com.github.bordertech.wcomponents.WApplication;
import com.github.bordertech.wcomponents.WCardManager;
import com.github.bordertech.wcomponents.WComponent;
import com.github.bordertech.wcomponents.WHeading;
import com.github.bordertech.wcomponents.WMessages;
import com.github.bordertech.wcomponents.WPanel;
import com.github.bordertech.wcomponents.WSection;
import com.github.bordertech.wcomponents.WText;
import com.github.bordertech.wcomponents.WTimeoutWarning;
import com.github.bordertech.wcomponents.WebUtilities;
import com.sample.app.model.client.AddressDetail;
import com.sample.app.model.client.ClientDetail;
import com.sample.app.ui.common.ClientWMessages;
import com.sample.app.ui.common.Constants;
import com.sample.app.ui.common.ViewMode;
import com.sample.app.ui.view.ClientView;
import com.sample.app.ui.view.DocumentView;
import com.sample.app.ui.view.SearchView;
import java.util.Date;

/**
 * The root WComponent application for the Sample web UI.
 */
public class ClientApp extends WApplication implements MessageContainer {

	/**
	 * Messages.
	 */
	private final WMessages messages = new ClientWMessages();

	/**
	 * Card manager.
	 */
	private final WCardManager mgr = new WCardManager();
	private final SearchView vwSearch;
	private final ClientView vwClient;
	private final DocumentView vwDocuments;

	/**
	 * Creates a new Sample App.
	 */
	public ClientApp() {

		// Custom css
		addCssFile("/css/app.css");

		// Header
		final WPanel header = new WPanel(WPanel.Type.HEADER);
		add(header);
		header.add(new WHeading(HeadingLevel.H1, "Client App"));

		// Detail
		WPanel detail = new WPanel();
		detail.setMargin(new Margin(Constants.GAP_LARGE));
		add(detail);

		// Messages
		detail.add(messages);

		// Card manager
		detail.add(mgr);

		// Cards
		vwSearch = new SearchView(this);
		vwClient = new ClientView(this);
		vwDocuments = new DocumentView(this);
		mgr.add(vwSearch);
		mgr.add(vwClient);
		mgr.add(vwDocuments);

		// Footer
		final WPanel footer = new WPanel(WPanel.Type.FOOTER);
		add(footer);

		footer.add(new WText(new Date().toString()));

		add(new WTimeoutWarning());

		// IDs
		header.setIdName("hdr");
		messages.setIdName("msgs");
	}

	@Override
	protected void preparePaintComponent(final Request request) {
		// Just for a PMD error
		super.preparePaintComponent(request);
	}

	/**
	 * Show documents view.
	 */
	public void showDocuments() {
		vwDocuments.reset();
		mgr.makeVisible(vwDocuments);
	}

	/**
	 * Show search view.
	 */
	public void showSearch() {
		mgr.makeVisible(vwSearch);
	}

	/**
	 * Show search view and apply updates.
	 *
	 * @param summary the updated client details
	 */
	public void showSearchWithUpdate(final ClientDetail summary) {
		vwSearch.refreshClientSummary(summary);
		showSearch();
	}

	/**
	 * Show client view.
	 *
	 * @param detail the client details
	 * @param update true if allow update
	 */
	public void viewClient(final ClientDetail detail, final boolean update) {
		vwClient.reset();
		vwClient.setViewMode(update ? ViewMode.UPDATE : ViewMode.READONLY);
		vwClient.setDetail(detail);
		mgr.makeVisible(vwClient);
	}

	/**
	 * Show create client view.
	 */
	public void createClient() {
		ClientDetail detail = new ClientDetail();
		detail.setAddress(new AddressDetail());
		vwClient.reset();
		vwClient.setViewMode(ViewMode.CREATE);
		vwClient.setDetail(detail);
		mgr.makeVisible(vwClient);
	}

	@Override
	public String getTitle() {
		return "Client App - " + getCurrentTitle();
	}

	@Override
	public WMessages getMessages() {
		return messages;
	}

	@Override
	public void handleStepError() {
		super.handleStepError();
		getMessages()
				.warn("A request was made that is not in the expected sequence and the application has been refreshed to its current state.");
	}

	/**
	 * @return the title of the current card
	 */
	private String getCurrentTitle() {
		return ((WSection) mgr.getVisible()).getDecoratedLabel().getText();
	}

	/**
	 * Retrieves the BrisApp ancestor of the given component.
	 *
	 * @param descendant the component to find the BrisApp ancestor of.
	 * @return the BrisApp ancestor for the given component, or null if not found.
	 */
	public static ClientApp getInstance(final WComponent descendant) {
		return WebUtilities.getAncestorOfClass(ClientApp.class, descendant);
	}

}
