package com.sample.app.rest.server.app;

import com.github.bordertech.swagger.servlet.SwaggerUiLaunchServlet;
import javax.servlet.annotation.WebServlet;

/**
 * Client launch swagger servlet.
 */
@WebServlet(urlPatterns = "/launchswagger")
public class MyLaunchSwaggerServlet extends SwaggerUiLaunchServlet {
}
