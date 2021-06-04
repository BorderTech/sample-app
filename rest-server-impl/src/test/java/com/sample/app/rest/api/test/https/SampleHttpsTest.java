package com.sample.app.rest.api.test.https;

import com.github.bordertech.config.Config;
import com.github.bordertech.lde.tomcat.TomcatLauncherProvider;
import com.sample.app.model.impl.MockDataUtil;
import com.sample.app.test.Unit;
import io.restassured.RestAssured;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.servlet.ServletException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.apache.commons.configuration.Configuration;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
 * Sample RestAssured unit test that shows how to use HTTPS.
 */
@Category(Unit.class)
public class SampleHttpsTest {

	private static final String OK_ID = "ORG1";
	private static final int OK_CODE = 200;

	/**
	 * Custom TOMCAT provider that setups HTTPS.
	 */
	private static TomcatLauncherProvider PROVIDER = new TomcatLauncherProvider() {
		@Override
		protected void configTomcat(final Tomcat tom) throws IOException, ServletException {
			super.configTomcat(tom);
			setupSSL(tom);
		}

		private void setupSSL(final Tomcat tom) {
			// Setup HTTPS and keystore details
			Connector httpsConnector = new Connector();
			Configuration params = Config.getInstance();
			httpsConnector.setPort(params.getInteger("tomcat.ssl.port", 8443));
			httpsConnector.setSecure(true);
			httpsConnector.setScheme("https");
			httpsConnector.setAttribute("keystoreFile", getKeyStorePath());
			httpsConnector.setAttribute("keystorePass", getKeyStorePassword());
			httpsConnector.setAttribute("clientAuth", params.getString("tomcat.ssl.clientAuth", "false"));
			httpsConnector.setAttribute("sslProtocol", params.getString("tomcat.ssl.sslProtocol", "TLS"));
			httpsConnector.setAttribute("SSLEnabled", params.getBoolean("tomcat.ssl.SSLEnabled", true));
			tom.getService().addConnector(httpsConnector);
		}

	};

	@BeforeClass
	public static void setup() {
		PROVIDER.launchServer(false);
		RestAssured.basePath = "api";
		RestAssured.baseURI = PROVIDER.getBaseUrl();
		setupRestAssurredSSL();
	}

	public static void setupRestAssurredSSL() {
		// Rest Assured can also be configured to ignore HTTPS errors
		// RestAssured.useRelaxedHTTPSValidation();
		// Set key store for RstAssured
		RestAssured.trustStore(getKeyStorePath(), getKeyStorePassword());
	}

	public static String getKeyStorePath() {
		String path = Config.getInstance().getString("tomcat.ssl.keystoreFile", "test-https-rest.keystore");

		File file = new File(path);
		if (file.exists()) {
			return path;
		}

		// Try classpath
		URL url = SampleHttpsTest.class.getClassLoader().getResource(path);
		if (url == null) {
			throw new IllegalStateException("Could not find keystore file in classpath [" + path + "]");
		}
		return url.getFile();
	}

	public static String getKeyStorePassword() {
		return Config.getInstance().getString("tomcat.ssl.keystorePass", "password");
	}

	@AfterClass
	public static void teardown() {
		PROVIDER.stopServer();
	}

	@Before
	@After
	public void cleanData() {
		MockDataUtil.resetData();
	}

	@Test
	public void retrieveClientSuccessful() {
		RestAssured
				.given().pathParam("ID", OK_ID)
				.get("v1/clients/{ID}")
				.then()
				.statusCode(OK_CODE)
				.body("data.clientId", Matchers.equalTo(OK_ID));
	}

}
