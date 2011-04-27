package de.topicmapslab.tmcledit.domaindiagram;

import org.eclipse.core.runtime.Status;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import de.topicmapslab.onotoa.selection.service.IOnotoaSelectionService;
import de.topicmapslab.onotoa.selection.service.OnotoaSelectionService;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	/**
	 *  The plug-in ID
	 */
	public static final String PLUGIN_ID = "de.topicmapslab.tmcledit.domaindiagram";

	// The shared instance
	private static Activator plugin;

	private IOnotoaSelectionService onotoaSelectionService;
	
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
		ServiceReference servRef = context.getServiceReference(IOnotoaSelectionService.class.getName());
		onotoaSelectionService = (IOnotoaSelectionService) context.getService(servRef);
	}

	/**
	 * 
	 * @return the {@link OnotoaSelectionService}
	 */
	public IOnotoaSelectionService getOnotoaSelectionService() {
		return onotoaSelectionService;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
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
	 * 
	 * @return the active shell of the workbench window
	 */
	public static Shell getCurrentShell() {
		return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
	}

	/**
	 * Logs an exception as error
	 * @param e exception to log
	 */
	public void log(Exception e) {
		getLog().log(new Status(Status.ERROR, PLUGIN_ID, "An error occured", e));
	}

}
