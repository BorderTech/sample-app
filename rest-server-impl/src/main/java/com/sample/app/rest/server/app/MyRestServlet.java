package com.sample.app.rest.server.app;

import com.github.bordertech.swagger.servlet.SwaggerJersey2Servlet;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * Client swagger jersey servlet.
 *
 * @see MyRestApplication
 */
@WebServlet(urlPatterns = "/api/*",
		initParams
		= {
			// Tell Jersey to use JSON
			@WebInitParam(name = "com.sun.jersey.api.json.POJOMappingFeature", value = "true"),
			// Tell Jersey the Application
			@WebInitParam(name = "javax.ws.rs.Application", value = "com.sample.app.rest.server.app.MyRestApplication")
		},
		loadOnStartup = 1
)
@SuppressWarnings("NoWhitespaceBefore")
public class MyRestServlet extends SwaggerJersey2Servlet {
}
