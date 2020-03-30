package com.sample.app.server.lde;

import com.github.bordertech.lde.api.LdeLauncher;

/**
 * Start Tomcat Server.
 */
@SuppressWarnings("HideUtilityClassConstructor")
public final class DemoLauncherProxy {

	/**
	 * Launch LDE Server.
	 *
	 * @param args the main arguments
	 */
	public static void main(final String[] args) {
		LdeLauncher.launchServer();
	}
}
