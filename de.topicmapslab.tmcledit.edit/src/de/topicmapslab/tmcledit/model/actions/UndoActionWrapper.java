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

import org.eclipse.emf.edit.ui.action.UndoAction;
import org.eclipse.ui.actions.ActionFactory;


/**
 * This class is just here to have the UpdateAction implemented
 * @author Hannes Niederhausen
 *
 */
public class UndoActionWrapper extends UndoAction implements UpdateAction {

	/**
	 * Constructor which sets the id to {@link ActionFactory.UNDO.getId()}
	 */
	public UndoActionWrapper() {
		setId(ActionFactory.UNDO.getId());
	}
	
}
