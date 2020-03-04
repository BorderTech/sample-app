package com.sample.app.server.lde;

import com.github.bordertech.lde.api.LdeLauncher;

/**
 * Start Tomcat Server.
 */
public final class LdeAppLauncherProxy {

	public static void main(final String[] args) {
		LdeLauncher.launchServer();
	}
}
