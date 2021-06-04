package com.sample.app.server.lde;

import com.github.bordertech.lde.tomcat.CustomJarScanner;
import java.net.URL;
import java.util.Deque;
import java.util.Set;
import org.apache.tomcat.JarScanType;
import org.apache.tomcat.JarScannerCallback;
import org.apache.tomcat.util.scan.StandardJarScanner;

/**
 * Custom jar scanner to handle JAVA 11 class loader changes.
 * <p>
 * Required until tomcat lde provider updated.
 * </p>
 */
public class TomcatStandardJarScannerFix extends StandardJarScanner implements CustomJarScanner {

	@Override
	protected void processURLs(final JarScanType scanType, final JarScannerCallback callback, final Set<URL> processedURLs, final boolean isWebapp, final Deque<URL> classPathUrlsToProcess) {
		// Make webApp always true so all classes are scanned for servlet 3 annotations
		super.processURLs(scanType, callback, processedURLs, true, classPathUrlsToProcess);
	}

	@Override
	public boolean isScanManifest() {
		return false;
	}

}
