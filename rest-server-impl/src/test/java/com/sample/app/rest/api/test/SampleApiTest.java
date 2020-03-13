package com.sample.app.rest.api.test;

import com.github.bordertech.lde.api.LdeLauncher;
import com.sample.app.model.impl.MockDataUtil;
import com.sample.app.test.Unit;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
 * Sample API Unit tests.
 */
@Category(Unit.class)
public class SampleApiTest {

	@BeforeClass
	public static void startTomcat() {
		LdeLauncher.launchServer(false);
	}

	@AfterClass
	public static void closeTomcat() {
		LdeLauncher.stopServer();
	}

	@BeforeClass
	public static void setupRest() {
		// Port
		String port = System.getProperty("server.port");
		RestAssured.port = port == null ? 8082 : Integer.valueOf(port);

		// Base
		String basePath = System.getProperty("server.base");
		RestAssured.basePath = basePath == null ? "/lde/api/v1/" : basePath;

		// Host
		String baseHost = System.getProperty("server.host");
		RestAssured.baseURI = baseHost == null ? "http://localhost" : baseHost;

		// Logging
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
	}

	@Before
	@After
	public void cleanData() {
		MockDataUtil.resetData();
	}

	@Test
	public void basicPingTest() {
		ValidatableResponse resp = RestAssured.given().when().get("/clients").then();
		resp.statusCode(200).body("data.size()", Matchers.is(9));
	}

	@Test
	public void basicSearchSystemError() {
		ValidatableResponse resp = RestAssured.given().queryParam("search", "error").when().get("/clients").then();
		resp.statusCode(400).body("error.status", Matchers.is(400));
	}

	@Test
	public void basicRetrieveClient() {
		ValidatableResponse resp = RestAssured.given().pathParam("id", "ORG1").when().get("/clients/{id}").then();
		resp.statusCode(200).body("data.clientId", Matchers.is("ORG1"));
	}

	@Test
	public void basicRetrieveClientError() {
		ValidatableResponse resp = RestAssured.given().pathParam("id", "error").when().get("/clients/{id}").then();
		resp.statusCode(400).body("error.status", Matchers.is(400));
	}

	@Test
	public void basicRetrieveClientNotFound() {
		ValidatableResponse resp = RestAssured.given().pathParam("id", "notfound").when().get("/clients/{id}").then();
		resp.statusCode(400).body("error.status", Matchers.is(400));
	}

}
