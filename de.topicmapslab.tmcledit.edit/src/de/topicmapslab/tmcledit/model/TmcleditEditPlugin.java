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

import de.topicmapslab.tmcledit.model.util.extension.AnnotationProviderInfo;
import de.topicmapslab.tmcledit.model.util.extension.ExtensionManager;
import de.topicmapslab.tmcledit.model.util.extension.PSIProviderInfo;

/**
 * This is the central singleton for the Tmcledit edit plugin.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 */
public final class TmcleditEditPlugin extends EMFPlugin {
	
	public static final String DOMAIN_DIAGRAMEDITOR_ID = "de.topicmapslab.tmcledit.domaindiagram.editor.DomainDiagramEditor";
	public final static String DIAGRAMEDITOR_ID = "de.topicmapslab.tmcledit.diagram.editor.TMCLDiagramEditor";
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
	
	public static ExtensionManager getExtensionManager() {
		return Implementation.getExtensionManager();
	}
	
	public static void logError(Exception e) {
		getPlugin().getLog().log(new Status(IStatus.ERROR, PLUGIN_ID, e.getMessage()));
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
		
		public static ExtensionManager getExtensionManager() {
	        return extensionManager;
        }

		public List<PSIProviderInfo> getPsiProviderInfos() {
	        return extensionManager.getPsiProviderInfos();
        }

		public List<AnnotationProviderInfo> getAnnotationProviderInfos() {
	        return extensionManager.getAnnotationProviderInfos();
        }
		
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
		
		
	}

}
