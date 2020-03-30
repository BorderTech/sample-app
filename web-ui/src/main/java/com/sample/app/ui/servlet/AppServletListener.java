package com.sample.app.ui.servlet;

import com.github.bordertech.taskmaster.cache.servlet.CachingProviderListener;
import com.github.bordertech.taskmaster.servlet.TaskContextListener;
import com.github.bordertech.taskmaster.servlet.combo.AbstractComboServletListener;
import javax.servlet.annotation.WebListener;

/**
 * App servlet listeners.
 */
@WebListener
public class AppServletListener extends AbstractComboServletListener {

	/**
	 * Default constructor.
	 */
	public AppServletListener() {
		super(new TaskContextListener(), new CachingProviderListener());
	}

}
