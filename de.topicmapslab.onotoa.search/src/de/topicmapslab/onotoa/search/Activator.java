package de.topicmapslab.onotoa.search;

import java.io.IOException;

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

	// The plug-in ID
	/**
	 * Final plugin ID String
	 */
	public static final String PLUGIN_ID = "de.topicmapslab.onotoa.search";

	// The shared instance
	private static Activator plugin;

	private IOnotoaSelectionService selectionService;

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
		ServiceReference servRef = context.getServiceReference(IOnotoaSelectionService.class.getName());
		selectionService = (IOnotoaSelectionService) context.getService(servRef);
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
	 * @return the selectionService
	 */
	public IOnotoaSelectionService getSelectionService() {
		return selectionService;
	}

	/**
	 * @param e
	 */
	public static void logException(IOException e) {
		getDefault().getLog().log(new Status(IStatus.ERROR, PLUGIN_ID, "", e));
	}

}
