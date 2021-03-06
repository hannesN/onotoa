package de.topicmapslab.onotoa.aranuka.codegen;

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
	 * The plug-in ID
	 */
	public static final String PLUGIN_ID = "de.topicmapslab.onotoa.aranuke.codegen";

	// The shared instance
	private static Activator plugin;

	// selection service instance
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
	 * logs the given exception as error
	 * 
	 * @param e
	 *            the exception to log
	 */
	public static void logException(Throwable e) {
		getDefault().getLog().log(new Status(IStatus.ERROR, PLUGIN_ID, "An error occurred:", e));
	}

	/**
	 * @return
	 */
	public IOnotoaSelectionService getSelectionService() {
		if (selectionService == null) {
			BundleContext bc = getBundle().getBundleContext();
			ServiceReference servRef = bc.getServiceReference(IOnotoaSelectionService.class.getName());
			selectionService = (IOnotoaSelectionService) bc.getService(servRef);
		}
		return selectionService;
	}

}
