package com.sample.app.ui.view;

import com.github.bordertech.taskmaster.service.exception.ServiceException;
import com.github.bordertech.wcomponents.HeadingLevel;
import com.github.bordertech.wcomponents.WEmailField;
import com.github.bordertech.wcomponents.WFieldLayout;
import com.github.bordertech.wcomponents.WHeading;
import com.github.bordertech.wcomponents.WPanel;
import com.github.bordertech.wcomponents.WTextField;
import com.sample.app.ui.util.V1ApiClientHelperFactory;
import com.sample.app.rest.v1.api.V1Api;
import com.sample.app.rest.v1.model.ClientDetailDTO;
import com.sample.app.ui.application.ClientApp;
import com.sample.app.ui.common.AddressPanel;

/**
 * Client view.
 */
public class ClientView extends AbstractClientView<ClientDetailDTO> {

	private static final V1Api CLIENT_SERVICES = V1ApiClientHelperFactory.getInstance();

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
	protected void doCreateServiceCall(final ClientDetailDTO bean) throws ServiceException {
		try {
			String id = CLIENT_SERVICES.createClient(bean).getData().getClientId();
			getApp().getMessages().success("Client [" + id + "] created.");
		} catch (Exception e) {
			throw new ServiceException("Error creating client. " + e.getMessage(), e);
		}
	}

	@Override
	protected void doUpdateServiceCall(final ClientDetailDTO bean) throws ServiceException {
		CLIENT_SERVICES.updateClient(bean.getClientId(), bean);
		getApp().getMessages().success("Client updated.");
	}

	@Override
	protected ClientDetailDTO getSummary(final ClientDetailDTO bean) {
		return bean;
	}

}
