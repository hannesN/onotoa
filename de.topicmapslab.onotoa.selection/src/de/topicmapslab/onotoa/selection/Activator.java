package de.topicmapslab.onotoa.selection;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import de.topicmapslab.onotoa.selection.service.IOnotoaSelectionService;
import de.topicmapslab.onotoa.selection.service.OnotoaSelectionService;

public class Activator implements BundleActivator {

	private static BundleContext context;
	private OnotoaSelectionService service;

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		service = new OnotoaSelectionService();
		context.registerService(IOnotoaSelectionService.class.getName(), service, null);
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

}
