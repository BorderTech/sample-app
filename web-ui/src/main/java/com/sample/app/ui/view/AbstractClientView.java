package com.sample.app.ui.view;

import com.github.bordertech.wcomponents.Action;
import com.github.bordertech.wcomponents.ActionEvent;
import com.github.bordertech.wcomponents.Container;
import com.github.bordertech.wcomponents.Input;
import com.github.bordertech.wcomponents.MessageContainer;
import com.github.bordertech.wcomponents.WButton;
import com.github.bordertech.wcomponents.WComponent;
import com.github.bordertech.wcomponents.WContainer;
import com.github.bordertech.wcomponents.WMenu;
import com.github.bordertech.wcomponents.WMenuItem;
import com.github.bordertech.wcomponents.WMessages;
import com.github.bordertech.wcomponents.WPanel;
import com.github.bordertech.wcomponents.WSection;
import com.github.bordertech.wcomponents.WebUtilities;
import com.github.bordertech.wcomponents.layout.ColumnLayout;
import com.github.bordertech.wcomponents.validation.ValidatingAction;
import com.sample.app.model.client.ClientDetail;
import com.sample.app.model.exception.ServiceException;
import com.sample.app.ui.application.ClientApp;
import com.sample.app.ui.common.ClientWMessages;
import com.sample.app.ui.common.Constants;
import com.sample.app.ui.common.ViewMode;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Abstract client view.
 *
 * @param <T> the bound bean type
 */
public abstract class AbstractClientView<T> extends WSection implements MessageContainer {

	/**
	 * The logger instance for this class.
	 */
	private static final Log LOG = LogFactory.getLog(AbstractClientView.class);

	/**
	 * Main controller.
	 */
	private final ClientApp app;

	/**
	 * Messages for this view.
	 */
	private final WMessages messages = new ClientWMessages();

	/**
	 * @param title the view title
	 * @param app the client app
	 */
	public AbstractClientView(final String title, final ClientApp app) {
		super(title);
		this.app = app;

		WPanel content = getContent();

		setupMenu();

		// Messages
		content.add(messages);
	}

	/**
	 * @param detail the bean to be displayed
	 */
	public void setDetail(final T detail) {
		getContent().setBean(detail);
	}

	/**
	 * @return the bean being displayed.
	 */
	public T getDetail() {
		return (T) getContent().getBean();
	}

	/**
	 * @param viewMode the mode of the view
	 */
	public void setViewMode(final ViewMode viewMode) {
		getOrCreateComponentModel().viewMode = viewMode;
		if (viewMode == ViewMode.READONLY) {
			doMakeReadOnly(getContent());
		}
	}

	/**
	 * @return the mode of the view
	 */
	public ViewMode getViewMode() {
		return getComponentModel().viewMode;
	}

	private void doMakeReadOnly(final WComponent component) {
		if (component instanceof Input) {
			((Input) component).setReadOnly(true);
		}

		if (component instanceof Container) {
			for (WComponent child : ((Container) component).getChildren()) {
				doMakeReadOnly(child);
			}
		}
	}

	private void setupMenu() {
		WPanel content = getContent();

		// Menu
		WMenu menu = new WMenu();
		content.add(menu);

		WMenuItem item = new WMenuItem("Back");
		item.setAction(new Action() {
			@Override
			public void execute(final ActionEvent event) {
				getApp().showSearch();
			}
		});
		menu.add(item);

		item = new WMenuItem("Reset") {
			@Override
			public boolean isVisible() {
				return getViewMode() != ViewMode.READONLY;
			}
		};
		item.setAction(new Action() {
			@Override
			public void execute(final ActionEvent event) {
				doReset();
			}
		});
		menu.add(item);
	}

	/**
	 * Setup the action buttons.
	 */
	protected final void setupButtons() {

		WPanel content = getContent();
		WPanel panel = new WPanel(WPanel.Type.FEATURE);
		panel.setMargin(Constants.NORTH_MARGIN_LARGE);
		panel.setLayout(new ColumnLayout(new int[]{50, 50}, new ColumnLayout.Alignment[]{ColumnLayout.Alignment.LEFT, ColumnLayout.Alignment.RIGHT}));

		content.add(panel);

		WContainer left = new WContainer();
		WContainer right = new WContainer();
		panel.add(left);
		panel.add(right);

		// Cancel
		WButton cancelButton = new WButton("Cancel") {
			@Override
			public boolean isVisible() {
				return getViewMode() == ViewMode.CREATE || getViewMode() == ViewMode.UPDATE;
			}
		};
		cancelButton.setCancel(true);
		cancelButton.setAction(new Action() {
			@Override
			public void execute(final ActionEvent event) {
				getApp().showSearch();
			}
		});
		left.add(cancelButton);

		// Back
		WButton backButton = new WButton("Back") {
			@Override
			public boolean isVisible() {
				return getViewMode() == ViewMode.READONLY;
			}
		};
		backButton.setAction(new Action() {
			@Override
			public void execute(final ActionEvent event) {
				getApp().showSearch();
			}
		});
		left.add(backButton);

		// Save
		WButton saveButton = new WButton() {
			@Override
			public boolean isVisible() {
				return getViewMode() == ViewMode.CREATE || getViewMode() == ViewMode.UPDATE;
			}

			@Override
			public String getText() {
				return getViewMode() == ViewMode.CREATE ? "Create" : "Update";
			}
		};
		saveButton.setAction(new ValidatingAction(getMessages().getValidationErrors(), content) {
			@Override
			public void executeOnValid(final ActionEvent event) {
				doSaveAction();
			}
		});
		right.add(saveButton);

		content.setDefaultSubmitButton(saveButton);

	}

	/**
	 * Handle reset details.
	 */
	protected void doReset() {
		T bean = getDetail();
		getContent().reset();
		setDetail(bean);
	}

	/**
	 * Handle save action.
	 */
	protected void doSaveAction() {
		doUpdateDetailBean();
		T bean = getDetail();
		try {
			if (getViewMode() == ViewMode.CREATE) {
				doCreateServiceCall(bean);
				getApp().showSearch();
			} else {
				doUpdateServiceCall(bean);
				ClientDetail summary = getSummary(bean);
				getApp().showSearchWithUpdate(summary);
			}
		} catch (Exception e) {
			String msg = "Error calling service. " + e.getMessage();
			LOG.error(msg, e);
			getMessages().error(msg);
		}
	}

	protected void doUpdateDetailBean() {
		WebUtilities.updateBeanValue(getContent());
	}

	protected abstract void doCreateServiceCall(final T bean) throws ServiceException;

	protected abstract void doUpdateServiceCall(final T bean) throws ServiceException;

	protected abstract ClientDetail getSummary(final T bean);

	@Override
	public WMessages getMessages() {
		return messages;
	}

	/**
	 *
	 * @return the main controller
	 */
	public ClientApp getApp() {
		return app;
	}

	@Override
	protected ViewModel newComponentModel() {
		return new ViewModel();
	}

	@Override
	protected ViewModel getComponentModel() {
		return (ViewModel) super.getComponentModel();
	}

	@Override
	protected ViewModel getOrCreateComponentModel() {
		return (ViewModel) super.getOrCreateComponentModel();
	}

	/**
	 * Holds the extrinsic state information of a WApplication.
	 */
	public static class ViewModel extends SectionModel {

		private ViewMode viewMode;
	}

}
