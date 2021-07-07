package com.sample.app.ui.view;

import com.github.bordertech.wcomponents.Action;
import com.github.bordertech.wcomponents.ActionEvent;
import com.github.bordertech.wcomponents.AjaxTarget;
import com.github.bordertech.wcomponents.MessageContainer;
import com.github.bordertech.wcomponents.SimpleBeanBoundTableModel;
import com.github.bordertech.wcomponents.WAjaxControl;
import com.github.bordertech.wcomponents.WButton;
import com.github.bordertech.wcomponents.WContainer;
import com.github.bordertech.wcomponents.WField;
import com.github.bordertech.wcomponents.WFieldLayout;
import com.github.bordertech.wcomponents.WList;
import com.github.bordertech.wcomponents.WMenu;
import com.github.bordertech.wcomponents.WMenuItem;
import com.github.bordertech.wcomponents.WMessages;
import com.github.bordertech.wcomponents.WPanel;
import com.github.bordertech.wcomponents.WSection;
import com.github.bordertech.wcomponents.WSubMenu;
import com.github.bordertech.wcomponents.WTable;
import com.github.bordertech.wcomponents.WTableColumn;
import com.github.bordertech.wcomponents.WText;
import com.github.bordertech.wcomponents.WTextField;
import com.github.bordertech.wcomponents.validation.ValidatingAction;
import com.sample.app.rest.v1.api.V1Api;
import com.sample.app.rest.v1.model.AddressDetailDTO;
import com.sample.app.rest.v1.model.ClientDetailDTO;
import com.sample.app.ui.application.ClientApp;
import com.sample.app.ui.common.ClientWMessages;
import com.sample.app.ui.common.Constants;
import com.sample.app.ui.common.WTextCountryCodeDesc;
import com.sample.app.ui.util.V1ApiClientHelperFactory;
import java.util.List;
import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Search view.
 */
public class SearchView extends WSection implements MessageContainer {

	private static final Log LOG = LogFactory.getLog(SearchView.class);

	private static final V1Api CLIENT_SERVICES = V1ApiClientHelperFactory.getInstance();

	private final ClientApp app;

	private final WTextField txtSearch = new WTextField();

	private final WMessages messages = new ClientWMessages();

	private final WMenu menu = new WMenu();

	private final WPanel resultsPanel = new WPanel();

	private final WTable table = new WTable();

	/**
	 * @param app the client app.
	 */
	public SearchView(final ClientApp app) {
		super("Search");
		this.app = app;

		WPanel content = getContent();

		content.add(menu);
		setupMenu();

		// Messages
		content.add(messages);

		WFieldLayout layout = new WFieldLayout(WFieldLayout.LAYOUT_FLAT);
		layout.setLabelWidth(20);
		layout.setMargin(Constants.NORTH_MARGIN_LARGE);
		content.add(layout);

		WField btnField = layout.addField("Search name", txtSearch);
		btnField.setInputWidth(100);
//		txtSearch.setMandatory(true);

		WButton searchButton = new WButton("Search");
		searchButton.setAction(new ValidatingAction(messages.getValidationErrors(), layout) {
			@Override
			public void executeOnValid(final ActionEvent event) {
				doSearch(txtSearch.getValue());
			}
		});
		layout.addField(searchButton);

		WAjaxControl ajax = new WAjaxControl(searchButton, new AjaxTarget[]{btnField, messages, resultsPanel});
		content.add(ajax);

		// Table
		content.add(resultsPanel);
		resultsPanel.setMargin(Constants.NORTH_MARGIN_LARGE);
		resultsPanel.add(table);
		setupTable();

		// Ids
		setIdName("srch");
		setNamingContext(true);

		content.setDefaultSubmitButton(searchButton);

	}

	private void setupMenu() {

		WSubMenu subMenu = new WSubMenu("Create");
		menu.add(subMenu);

		WMenuItem item = new WMenuItem("Client");
		item.setAction(new Action() {
			@Override
			public void execute(final ActionEvent event) {
				app.createClient();
			}
		});
		item.setCancel(true);
		subMenu.add(item);

		// Reset
		item = new WMenuItem("Reset");
		item.setAction(new Action() {
			@Override
			public void execute(final ActionEvent event) {
				// Reset to force call service again
				doReset();
			}
		});
		item.setCancel(true);
		menu.add(item);

		// Documents
		item = new WMenuItem("Documents");
		item.setAction(new Action() {
			@Override
			public void execute(final ActionEvent event) {
				app.showDocuments();
			}
		});
		item.setCancel(true);
		menu.add(item);

	}

	/**
	 * Setup the results table.
	 */
	private void setupTable() {
		table.setIdName("rs");

		// View client
		final WButton viewClient = new WButton("Display");
		viewClient.setAction(new Action() {
			@Override
			public void execute(final ActionEvent event) {
				ClientDetailDTO client = (ClientDetailDTO) viewClient.getBeanValue();
				doHandleClient(client.getClientId(), false);
			}
		});
		viewClient.setImageUrl("icons/view.png");
		viewClient.setToolTip("view");

		// Update client
		final WButton updateClient = new WButton("Update");
		updateClient.setHtmlClass("updateButton");
		updateClient.setAction(new Action() {
			@Override
			public void execute(final ActionEvent event) {
				ClientDetailDTO client = (ClientDetailDTO) viewClient.getBeanValue();
				doHandleClient(client.getClientId(), true);
			}
		});
		updateClient.setImageUrl("icons/edit.png");
		updateClient.setToolTip("update");
		final WContainer actionsContainer = new WContainer();
		actionsContainer.add(viewClient);
		actionsContainer.add(updateClient);

		WList listCountry = new WList(WList.Type.STACKED);
		listCountry.setRepeatedComponent(new WTextCountryCodeDesc());

		// Foramt Addresss summary
		WText txtAddress = new WText() {
			@Override
			public String getText() {
				AddressDetailDTO det = (AddressDetailDTO) getBean();
				StringBuilder buf = new StringBuilder();
				buf.append(det.getStreet());
				buf.append(", ").append(det.getSuburb());
				buf.append(", ").append(det.getState());
				buf.append(", ").append(det.getPostcode());
				buf.append(", ").append(det.getCountryCode());
				return buf.toString();
			}
		};

		table.setMargin(Constants.SOUTH_MARGIN_LARGE);
		table.addColumn(new WTableColumn("ID", new WText()));
		table.addColumn(new WTableColumn("Name", new WText()));
		table.addColumn(new WTableColumn("Address", txtAddress));
		table.addColumn(new WTableColumn("Actions", actionsContainer));
		table.setStripingType(WTable.StripingType.ROWS);
		table.setNoDataMessage("No clients found.");

		SimpleBeanBoundTableModel model = new SimpleBeanBoundTableModel(new String[]{"clientId", "name", "address", "."}) {
			@Override
			public Object getRowKey(final List<Integer> row) {
				return ((ClientDetailDTO) super.getRowKey(row)).getClientId();
			}
		};
		table.setTableModel(model);

		table.setBeanProperty(".");

		table.setVisible(false);
	}

	/**
	 * Refresh the client summary details.
	 *
	 * @param summary the client details.
	 */
	public void refreshClientSummary(final ClientDetailDTO summary) {
		List<ClientDetailDTO> clients = (List<ClientDetailDTO>) table.getBean();
		// Find ID
		for (int idx = 0; idx < clients.size(); idx++) {
			if (Objects.equals(clients.get(idx).getClientId(), summary.getClientId())) {
				clients.set(idx, summary);
				break;
			}
		}
	}

	/**
	 * Refresh results. Remove results from the cache.
	 */
	public void doReset() {
		reset();
	}

	private void doSearch(final String criteria) {
		try {
			table.reset();
			List<ClientDetailDTO> clients = CLIENT_SERVICES.searchClients(criteria).getData();
			if (clients.isEmpty()) {
				messages.info("No clients found");
				return;
			}
			table.setBean(clients);
			table.setVisible(true);
		} catch (Exception e) {
			String msg = "Error searching for clients. " + e.getMessage();
			LOG.error(msg, e);
			messages.error(msg);
		}

	}

	private void doHandleClient(final String clientId, final boolean update) {
		try {
			ClientDetailDTO detail = CLIENT_SERVICES.retrieveClient(clientId).getData();
			app.viewClient(detail, update);
		} catch (Exception e) {
			String msg = "Error retrieving client [" + clientId + "]. " + e.getMessage();
			LOG.error(msg, e);
			messages.error(msg);
		}

	}

	@Override
	public WMessages getMessages() {
		return messages;
	}

}
