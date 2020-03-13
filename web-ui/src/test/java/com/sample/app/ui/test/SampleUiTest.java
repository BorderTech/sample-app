package com.sample.app.ui.test;

import com.github.bordertech.webfriends.selenium.common.tag.SeleniumTags;
import com.github.bordertech.webfriends.selenium.element.form.SButton;
import com.github.bordertech.webfriends.selenium.element.sections.SHeading1;
import com.github.bordertech.webfriends.selenium.element.table.STable;
import com.github.bordertech.webfriends.selenium.smart.junit.SmartDriverTestCase;
import com.sample.app.test.Smoke;
import com.sample.app.test.Unit;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
 * Sample UI Unit tests.
 */
@Category({Unit.class, Smoke.class})
public class SampleUiTest extends SmartDriverTestCase {
// ## SMOKE Properties
// ##bordertech.webfriends.selenium.launchServer=false
// ##bordertech.webfriends.selenium.serverUrl=http://localhost:8081/lde
//	mvn test -Psmoke -pl web-ui -Dbordertech.webfriends.selenium.launchServer=false -Dbordertech.webfriends.selenium.serverUrl=http://localhost:8081/lde

	@Test
	public void pingSearch() {
		navigateToPath("/app");
		getDriver().waitForPageReady();
		SHeading1 heading = getDriver().findWebFriend(SeleniumTags.H1);
		Assert.assertNotNull("Heading not found", heading);
		Assert.assertEquals("Heading not correct", "Client App", heading.getHeadingText());
		SButton button = getDriver().findButton("Search");
		Assert.assertNotNull("Search button not found", button);
		button.click();
		STable tbl = getDriver().findWebFriend(SeleniumTags.TABLE);
		Assert.assertNotNull("Should have table results", tbl);
	}

}
