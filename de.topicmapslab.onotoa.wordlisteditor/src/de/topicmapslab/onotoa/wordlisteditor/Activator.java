/*******************************************************************************
 * Copyright (c) 2008-2011 Topic Maps Lab and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *      Hannes Niederhausen - initial API and implementation
 ******************************************************************************/
package de.topicmapslab.onotoa.wordlisteditor;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import de.topicmapslab.onotoa.selection.service.IOnotoaSelectionService;
import de.topicmapslab.onotoa.wordlisteditor.editor.WordListEditor;
import de.topicmapslab.onotoa.wordlisteditor.editor.input.WordListEditorInput;
import de.topicmapslab.tmcledit.model.views.ModelView;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	/**
	 *  The plug-in ID
	 */
	public static final String PLUGIN_ID = "de.topicmapslab.onotoa.wordlisteditor"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;
	
	/**
	 * The constructor
	 */
	public Activator() {
	}

	
	/**
	 * {@inheritDoc}
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	
	/**
	 * {@inheritDoc}
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
	 * Logs an exception as error
	 * @param e the exception to log
	 */
	public static void logError(Exception e) {
		getDefault().getLog().log(new Status(IStatus.ERROR, PLUGIN_ID, e.getMessage()));
	}
	
	/**
	 *  Logs an exception as info
	 * @param e the exception to log
	 */
	public static void logInfo(Exception e) {
		getDefault().getLog().log(new Status(IStatus.INFO, PLUGIN_ID, e.getMessage()));
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


	/**
     * 
     */
    public void openEditor() {
	    try {
	        getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(new WordListEditorInput(ModelView.getInstance()), WordListEditor.ID);
        } catch (PartInitException e) {
	        throw new RuntimeException(e);
        }
	    
    }
}
