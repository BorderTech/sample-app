package com.sample.app.model.client;

import com.sample.app.test.Unit;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
 * Example test.
 */
@Category(Unit.class)
public class AddressDetailTest {

	@Test
	public void testCountryCodeAccessors() {
		AddressDetail detail = new AddressDetail();
		Assert.assertNull("Should be null by default", detail.getCountryCode());
		detail.setCountryCode("X");
		Assert.assertEquals("Should be X", "X", detail.getCountryCode());
	}

}
