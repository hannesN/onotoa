package de.topicmapslab.onotoa.selection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.BundleContext;

import de.topicmapslab.onotoa.selection.service.IOnotoaSelectionService;
import de.topicmapslab.onotoa.selection.service.OnotoaSelectionService;

/**
 * Activator for the Selection bundle
 * 
 * @author Hannes Niederhausen
 *
 */
public class Activator extends Plugin {
	private static final String PLUGIN_ID = "de.topicmapslab.onotoa.selection";
	
	private static BundleContext context;
	private static Activator plugin;
	
	private OnotoaSelectionService service;

	static BundleContext getContext() {
		return context;
	}

	/**
	 * Returns the bundles singleton
	 * @return
	 */
	public static Activator getPlugin() {
	    return plugin;
    }
	
	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		service = new OnotoaSelectionService();
		context.registerService(IOnotoaSelectionService.class.getName(), service, null);
		Activator.plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
		Activator.plugin = null;
	}
	
	/**
	 * Logs a {@link Throwable} as info
	 * @param t the {@link Throwable} to log
	 */
	public static void logInfo(Throwable t) {
		getPlugin().getLog().log(new Status(IStatus.INFO, PLUGIN_ID, "Exception thrown:", t));
	}
}
