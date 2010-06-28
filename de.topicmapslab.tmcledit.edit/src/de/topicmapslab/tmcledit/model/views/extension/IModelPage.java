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

/**
 * Interface for a property details page.
 * 
 * @author Hannes Niederhausen
 * @version 1.1.2
 */
public interface IModelPage extends IPage {

	
	public abstract void setModel(Object model);

	public abstract CommandStack getCommandStack();

	public abstract void setCommandStack(CommandStack commandStack);

	public abstract void aboutToHide();

	/**
	 * Calling this method triggers an update of
	 * every widget in the page. This may be needed when 
	 * model changes outside the page occur.  
	 * 
	 */
	public abstract void updateUI();

	public abstract Object getModel();

	public abstract IPageSite getSite();

	public abstract void init(IPageSite pageSite);

	public abstract String getId();

}