/*******************************************************************************
 * Copyright (c) 2008, 2009 Topic Maps Lab and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Hannes Niederhausen - initial API and implementation
 *******************************************************************************/
package de.topicmapslab.tmcledit.model.views.extension;

import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.ui.part.IPage;
import org.eclipse.ui.part.IPageSite;

import de.topicmapslab.tmcledit.model.views.PropertyDetailView;

/**
 * Interface for a property details page.
 * 
 * @author Hannes Niederhausen
 * @version 1.1.2
 */
public interface IModelPage extends IPage {

	/**
	 * 
	 * @return the {@link CommandStack} used to execute Undoable Operations in the page
	 */
	public abstract CommandStack getCommandStack();

	/**
	 * Sets the {@link CommandStack} used to execute Undoable Operations in the page
	 * @param commandStack the new {@link CommandStack}
	 */
	public abstract void setCommandStack(CommandStack commandStack);

	/**
	 * Initializes the page using the {@link IPageSite} of the {@link PropertyDetailView} 
	 * @param pageSite the {@link IPageSite} of the view
	 */
	public abstract void init(IPageSite pageSite);

	/**
	 * Indicates that the page will be hidden. Implementations should clean up some chaches or clear input masks
	 */
	public abstract void aboutToHide();

	/**
	 * Calling this method triggers an update of
	 * every widget in the page. This may be needed when 
	 * model changes outside the page occur.  
	 * 
	 */
	public abstract void updateUI();

	/**
	 * 
	 * @return the model which was modified by the page
	 */
	public abstract Object getModel();

	/**
	 * Sets the model instance which will be modified or shown
	 * @param model the model to show/edit
	 */
	public abstract void setModel(Object model);

	/**
	 * 
	 * @return the used pageSite
	 */
	public abstract IPageSite getSite();

	/**
	 * The id of the page. The id should be unique so it is advised to use a scheme like
	 * the ones for views or editors.
	 * @return the id of the page
	 */
	public abstract String getId();

}