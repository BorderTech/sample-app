package com.sample.app.ui.common;

import com.github.bordertech.wcomponents.WDateField;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * WDateField that handles LocalDate format.
 */
public class WDateFieldLocal extends WDateField {

	@Override
	public Object getData() {
		Object obj = super.getData();
		if (obj instanceof LocalDate) {
			LocalDate loc = (LocalDate) obj;
			return Date.from(loc.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		}
		return obj;
	}

	@Override
	protected void doUpdateBeanValue(final Object value) {
		if (value instanceof Date) {
			LocalDate loc = ((Date) value).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			super.doUpdateBeanValue(loc);
		} else {
			super.doUpdateBeanValue(value);
		}
	}

}
