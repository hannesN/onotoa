package de.topicmapslab.onotoa.update;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.equinox.p2.core.IProvisioningAgent;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "de.topicmapslab.onotoa.update"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;

	private ServiceRegistration policyRegistration;

	private IProvisioningAgent agent;
	
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
		
		initProvisionAgent(context);
		registerPolicy(context);
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
	 * Returns the provision agent
	 * @return
	 */
	public static IProvisioningAgent getAgent() {
		return getDefault().agent;
	}

	private void initProvisionAgent(BundleContext context) {
		
		ServiceReference ref = context.getServiceReference(IProvisioningAgent.class.getName());
		agent = (IProvisioningAgent) context.getService(ref);
	}

	/**
		 * @param context
		 */
		protected void registerPolicy(BundleContext context) {
	//		Policy p = new Policy();
	//		p.setRepositoriesVisible(false);
	//		p.setShowLatestVersionsOnly(true);
	//		p.setGroupByCategory(false);
	//		
	//		policyRegistration = context.registerService(Policy.class.getName(), p, null);
		}

	public static void logException(Exception e) {
		
		getDefault().getLog().log(new Status(IStatus.ERROR, PLUGIN_ID, "An Error occurred", e));
	}

}
