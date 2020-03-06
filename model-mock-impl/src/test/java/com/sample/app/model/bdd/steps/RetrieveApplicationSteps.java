package com.sample.app.model.bdd.steps;

import com.sample.app.model.client.ClientDetail;
import com.sample.app.model.exception.ClientNotFoundException;
import com.sample.app.model.exception.ServiceException;
import com.sample.app.model.impl.ClientServicesMockImpl;
import com.sample.app.model.services.ClientServices;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

/**
 * Retrieve client steps.
 */
public class RetrieveApplicationSteps {

	private final ClientServices backing = new ClientServicesMockImpl();

	private ClientDetail client;
	private Exception error;

	@Given("A client retrieve service is available")
	public void wantToRetrieveApplication() {
		this.error = null;
		this.client = null;
	}

	@When("User retrieves client {string}")
	public void retrieveApplication(final String id) {
		client = null;
		error = null;
		try {
			client = backing.retrieveClient(id);
		} catch (Exception e) {
			error = e;
		}
	}

	@Then("User gets {string} client")
	public void shouldHaveApplication(final String id) {
		Assert.assertNotNull("Client should have been retrieved", client);
		Assert.assertEquals("Incorrect client id retrieved", id, client.getClientId());
	}

	@Then("User gets client exception for {string}")
	public void shouldHaveApplicationException(final String id) {
		Assert.assertTrue("Service should have created not found exception", error instanceof ClientNotFoundException);
	}

	@Then("User gets service exception for {string}")
	public void shouldHaveServiceException(final String id) {
		Assert.assertTrue("Service should have created service exception", error instanceof ServiceException);
	}

}
