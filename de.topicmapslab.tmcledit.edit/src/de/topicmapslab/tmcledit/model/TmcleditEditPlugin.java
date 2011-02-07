/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.ui.EclipseUIPlugin;
import org.eclipse.emf.common.util.ResourceLocator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import de.topicmapslab.onotoa.selection.service.IOnotoaSelectionService;
import de.topicmapslab.tmcledit.model.util.extension.AnnotationProviderInfo;
import de.topicmapslab.tmcledit.model.util.extension.ExtensionManager;
import de.topicmapslab.tmcledit.model.util.extension.PSIProviderInfo;

/**
 * This is the central singleton for the Tmcledit edit plugin.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 */
public final class TmcleditEditPlugin extends EMFPlugin {
	/**
	 * ID of the domain diagram editor copied from the plugin.xml
	 */
	public static final String DOMAIN_DIAGRAMEDITOR_ID = "de.topicmapslab.tmcledit.domaindiagram.editor.DomainDiagramEditor";
	
	/**
	 * ID of the diagram editor copied from the plugin.xml
	 */
	public final static String DIAGRAMEDITOR_ID = "de.topicmapslab.tmcledit.diagram.editor.TMCLDiagramEditor";
	
	/**
	 * The plugin id of this bundle
	 */
	public final static String PLUGIN_ID = "de.topicmapslab.tmcledit.edit";
	
	/**
	 * Keep track of the singleton.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public static final TmcleditEditPlugin INSTANCE = new TmcleditEditPlugin();

	/**
	 * Keep track of the singleton.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	private static Implementation plugin;

	/**
	 * Create the instance.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public TmcleditEditPlugin() {
		super
		  (new ResourceLocator [] {
		   });
	}

	/**
	 * Returns the singleton instance of the Eclipse plugin.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the singleton instance.
	 * @generated
	 */
	@Override
	public ResourceLocator getPluginResourceLocator() {
		return plugin;
	}

	/**
	 * Returns the singleton instance of the Eclipse plugin.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the singleton instance.
	 * @generated
	 */
	public static Implementation getPlugin() {
		return plugin;
	}
	
	/**
	 * 
	 * @return the {@link ExtensionManager} instance
	 */
	public static ExtensionManager getExtensionManager() {
		return Implementation.getExtensionManager();
	}
	
	/**
	 * Logs an exception as error
	 * @param e the exception to log
	 */
	public static void logError(Exception e) {
		getPlugin().getLog().log(new Status(IStatus.ERROR, PLUGIN_ID, e.getMessage()));
	}
	
	/**
	 *  Logs an exception as info
	 * @param e the exception to log
	 */
	public static void logInfo(Exception e) {
		getPlugin().getLog().log(new Status(IStatus.INFO, PLUGIN_ID, e.getMessage()));
	}
	
	/**
	 * The actual implementation of the Eclipse <b>Plugin</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public static class Implementation extends EclipseUIPlugin {
		private static ExtensionManager extensionManager = new ExtensionManager();

		/**
		 * Creates an instance.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public Implementation() {
			super();

			// Remember the static instance.
			//
			plugin = this;
		}
		
		/**
		 * 
		 * @return the {@link ExtensionManager} instance
		 */
		public static ExtensionManager getExtensionManager() {
	        return extensionManager;
        }

		/**
		 * 
		 * @return the {@link PSIProviderInfo} instance
		 */
		public List<PSIProviderInfo> getPsiProviderInfos() {
	        return extensionManager.getPsiProviderInfos();
        }

		/**
		 * 
		 * @return the {@link AnnotationProviderInfo} instance
		 */
		public List<AnnotationProviderInfo> getAnnotationProviderInfos() {
	        return extensionManager.getAnnotationProviderInfos();
        }
		
		/**
		 * Returns all annotation keys which are not registered as internal.
		 * @return set of Strings which are annotation keys
		 */
		public Set<String> getNoneInternalAnnotionKeys() {
			Set<String> names = new HashSet<String>();
			
			for (AnnotationProviderInfo a : getAnnotationProviderInfos()) {
				if (!a.isInternal())
					names.add(a.getName());
			}
			
			if (names.size()==0)
				return Collections.emptySet();
			
			return names;
		}
		
		/**
		 * Returns an {@link AnnotationProviderInfo} with the given name
		 * @param name the name of the info; must no be <code>null</code>
		 * @return <code>null</code> or the {@link AnnotationProviderInfo} with the given name 
		 */
		public AnnotationProviderInfo getAnnotionProviderInfo(String name) {
			if (name==null)
				throw new IllegalArgumentException();
			for (AnnotationProviderInfo a : getAnnotationProviderInfos()) {
				if (name.equals(a.getName())) {
					return a;
				}
			}
			return null;
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

}
