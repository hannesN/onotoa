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
package de.topicmapslab.tmcledit.model.actions;

import org.eclipse.jface.action.IAction;

/**
 * Action which state update can be triggered.
 * 
 * @author Hannes Niederhausen
 * 
 */
public interface UpdateAction extends IAction {

	/**
	 * Triggers an update. Mostly the update checks if the action should be
	 * enabled or disabled and sets the state.
	 */
	public void update();

}
