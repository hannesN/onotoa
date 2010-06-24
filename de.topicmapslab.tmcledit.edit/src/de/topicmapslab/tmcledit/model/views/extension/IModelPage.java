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

	public abstract void updateUI();

	public abstract Object getModel();

}