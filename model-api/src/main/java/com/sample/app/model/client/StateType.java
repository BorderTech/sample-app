package com.sample.app.model.client;

/**
 * State types.
 */
public enum StateType {
	/**
	 * ACT State.
	 */
	ACT,
	/**
	 * NSW State.
	 */
	NSW,
	/**
	 * NT State.
	 */
	NT,
	/**
	 * QLD State.
	 */
	QLD,
	/**
	 * TAS State.
	 */
	TAS,
	/**
	 * VIC State.
	 */
	VIC,
	/**
	 * WA State.
	 */
	WA;

	@Override
	public String toString() {
		return name();
	}
}
