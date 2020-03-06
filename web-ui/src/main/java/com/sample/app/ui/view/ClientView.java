package com.sample.app.ui.view;

import com.github.bordertech.wcomponents.HeadingLevel;
import com.github.bordertech.wcomponents.WEmailField;
import com.github.bordertech.wcomponents.WFieldLayout;
import com.github.bordertech.wcomponents.WHeading;
import com.github.bordertech.wcomponents.WPanel;
import com.github.bordertech.wcomponents.WTextField;
import com.sample.app.model.client.ClientDetail;
import com.sample.app.model.exception.ServiceException;
import com.sample.app.model.services.ClientServices;
import com.sample.app.ui.application.ClientApp;
import com.sample.app.ui.common.AddressPanel;
import com.sample.app.ui.util.ClientServicesHelperFactory;

/**
 * Client view.
 */
public class ClientView extends AbstractClientView<ClientDetail> {

	private static final ClientServices CLIENT_SERVICES = ClientServicesHelperFactory.getInstance();

	/**
	 * @param app the client app.
	 */
	public ClientView(final ClientApp app) {
		super("Client", app);

		// Ids
		setIdName("cl");
		setNamingContext(true);

		setupContent();
		setupButtons();
	}

	private void setupContent() {

		WPanel content = getContent();

		content.add(new WHeading(HeadingLevel.H2, "Name"));

		WFieldLayout layout = new WFieldLayout(WFieldLayout.LAYOUT_FLAT);
		layout.setLabelWidth(20);
		content.add(layout);

		// Name
		WTextField txtName = new WTextField();
		txtName.setMandatory(true);
		txtName.setBeanProperty("name");
		layout.addField("Name", txtName);

		// ABN
		WTextField txtABN = new WTextField();
		txtABN.setMandatory(true);
		txtABN.setBeanProperty("abn");
		layout.addField("ABN", txtABN);

		// Email
		WEmailField email = new WEmailField();
		email.setBeanProperty("email");
		layout.addField("Email", email);

		// Address
		content.add(new WHeading(HeadingLevel.H2, "Address"));

		AddressPanel address = new AddressPanel();
		address.setBeanProperty("address");
		content.add(address);
	}

	@Override
	protected void doCreateServiceCall(final ClientDetail bean) throws ServiceException {
		String id = CLIENT_SERVICES.createClient(bean).getClientId();
		getApp().getMessages().success("Client [" + id + "] created.");
	}

	@Override
	protected void doUpdateServiceCall(final ClientDetail bean) throws ServiceException {
		CLIENT_SERVICES.updateClient(bean);
		getApp().getMessages().success("Client updated.");
	}

	@Override
	protected ClientDetail getSummary(final ClientDetail bean) {
		return bean;
	}

}
