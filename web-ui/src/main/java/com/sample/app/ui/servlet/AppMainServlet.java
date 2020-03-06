package com.sample.app.ui.servlet;

import com.github.bordertech.wcomponents.WComponent;
import com.github.bordertech.wcomponents.registry.UIRegistry;
import com.github.bordertech.wcomponents.servlet.WServlet;
import com.sample.app.ui.application.ClientApp;
import javax.servlet.annotation.WebServlet;

/**
 * App main WEB UI servlet.
 */
@WebServlet(urlPatterns = "/app")
public class AppMainServlet extends WServlet {

	@Override
	public WComponent getUI(final Object httpServletRequest) {
		return UIRegistry.getInstance().getUI(ClientApp.class.getName());
	}

}
