package com.sample.app.client.lde;

import com.github.bordertech.lde.api.LdeLauncher;

/**
 * Start Tomcat Server.
 */
@SuppressWarnings("HideUtilityClassConstructor")
public final class DemoLauncherProxy {

	public static void main(final String[] args) {
		LdeLauncher.launchServer();
	}
}
