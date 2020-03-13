package com.sample.app.rest.api.smoke;

import com.sample.app.test.Smoke;
import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
 * Sample API Smoke tests.
 */
@Category(Smoke.class)
public class SampleApiSmokeTest {

	@BeforeClass
	public static void setupRest() {

		// Port
		String port = System.getProperty("server.smoke.port");
		RestAssured.port = port == null ? 8082 : Integer.valueOf(port);

		// Base
		String basePath = System.getProperty("server.smoke.base");
		RestAssured.basePath = basePath == null ? "/lde/api/v1/" : basePath;

		// Host
		String baseHost = System.getProperty("server.smoke.host");
		RestAssured.baseURI = baseHost == null ? "http://localhost" : baseHost;

		// Logging
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
	}

	@Test
	public void basicPingTest() {
		RestAssured.given().when().get("/clients").then().statusCode(200);
	}

	@Test
	public void basicSearchSystemError() {
		RestAssured.given().queryParam("search", "error").when().get("/clients").then().statusCode(400);
	}

	@Test
	public void basicRetrieveClient() {
		RestAssured.given().pathParam("id", "ORG1").when().get("/clients/{id}").then().statusCode(200);
	}

	@Test
	public void basicRetrieveClientError() {
		RestAssured.given().pathParam("id", "error").when().get("/clients/{id}").then().statusCode(400);
	}

	@Test
	public void basicRetrieveClientNotFound() {
		RestAssured.given().pathParam("id", "notfound").when().get("/clients/{id}").then().statusCode(400);
	}

}
