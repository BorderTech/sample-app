package com.sample.app.model.client;

import com.openpojo.reflection.filters.FilterPackageInfo;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.rule.impl.GetterMustExistRule;
import com.openpojo.validation.rule.impl.SetterMustExistRule;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SerializableTester;
import com.openpojo.validation.test.impl.SetterTester;
import com.sample.app.test.Unit;
import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
 * Unit tests for model POJOs.
 */
@Category(Unit.class)
public class ModelPojoTest {

	// The package to test
	private static final String POJO_PACKAGE = "com.sample.app.model.client";

	@Test
	public void testPojoStructureAndBehavior() {
		Validator validator = ValidatorBuilder.create()
				// Add Rules to validate structure for POJO_PACKAGE
				// See com.openpojo.validation.rule.impl for more ...
				.with(new GetterMustExistRule())
				.with(new SetterMustExistRule())
				// Add Testers to validate behaviour for POJO_PACKAGE
				// See com.openpojo.validation.test.impl for more ...
				.with(new SetterTester())
				.with(new GetterTester())
				.with(new SerializableTester())
				.build();

		validator.validate(POJO_PACKAGE, new FilterPackageInfo());
	}
}
