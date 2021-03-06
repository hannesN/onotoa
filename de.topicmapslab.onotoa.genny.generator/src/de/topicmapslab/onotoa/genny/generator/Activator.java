package de.topicmapslab.onotoa.genny.generator;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import de.topicmapslab.onotoa.selection.service.IOnotoaSelectionService;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	/**
	 * plug-in ID
	 */
	public static final String PLUGIN_ID = "de.topicmapslab.onotoa.genny.generator"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;

	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext
	 * )
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
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
	 * Logs an exception
	 * @param e the exception to log
	 */
	public static void logException(Throwable e) {
		getDefault().getLog().log(new Status(IStatus.ERROR, PLUGIN_ID, "An error occurred:", e));
	}
	
	/**
	 * Returns the selection service found via OSGi services
	 * @return the {@link IOnotoaSelectionService} of the running instance
	 */
	public IOnotoaSelectionService getOnotoaSelectionService() {
		BundleContext context = getBundle().getBundleContext();
		ServiceReference servRef = context.getServiceReference(IOnotoaSelectionService.class.getName());
		return (IOnotoaSelectionService) context.getService(servRef);
	}

}
