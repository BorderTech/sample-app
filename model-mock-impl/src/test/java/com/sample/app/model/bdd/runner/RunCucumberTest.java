package com.sample.app.model.bdd.runner;

import com.sample.app.model.impl.MockDataUtil;
import com.sample.app.test.Unit;
import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

/**
 * Run cucumber tests.
 */
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
		strict = false,
		features = "classpath:api/bdd/features",
		glue = {"com.sample.app.model.bdd.steps"}
)
@Category(Unit.class)
public class RunCucumberTest {

	@BeforeClass
	public static void startTomcat() {
		MockDataUtil.resetData();
	}

	@AfterClass
	public static void closeTomcat() {
		MockDataUtil.resetData();
	}
}
