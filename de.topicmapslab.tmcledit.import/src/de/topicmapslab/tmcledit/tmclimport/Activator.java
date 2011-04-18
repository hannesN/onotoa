package de.topicmapslab.tmcledit.tmclimport;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	/**
	 *  The plug-in ID
	 */
	public static final String PLUGIN_ID = "de.topicmapslab.tmcledit.tmclimport";

	// The shared instance
	private static Activator plugin;
	
	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/**
	 * {inheritDoc}
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Logs the message as info
	 * @param message the message to log
	 */
	public void logInfo(String message) {
		Status s = new Status(Status.INFO, PLUGIN_ID, message);
		getLog().log(s);
	}
	
	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}
	
	/**
	 * logs the given exception as error
	 * 
	 * @param e
	 *            the exception to log
	 */
	public static void logException(Throwable e) {
		getDefault().getLog().log(new Status(IStatus.ERROR, PLUGIN_ID, "An error occurred:", e));
	}

	/**
	 * Logs the given exception as error.
	 * 
	 * @param e the exception to log
	 */
	public void logError(Exception e) {
		Status s = new Status(Status.ERROR, PLUGIN_ID, "An error occurred!", e);
		getLog().log(s);
    }

}
