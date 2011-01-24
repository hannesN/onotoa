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
package de.topicmapslab.tmcledit.diagram.policies;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.jface.viewers.CellEditor;

import de.topicmapslab.tmcledit.diagram.command.CommandAdapter;
import de.topicmapslab.tmcledit.diagram.editor.IOnotoaEditDomain;
import de.topicmapslab.tmcledit.diagram.editparts.IDirectEditable;

/**
 * Abstract Edit Policy for direct editing elements.
 * 
 * @author Hannes Niederhausen
 *
 */
public abstract class AbstractDirectEditPolicy extends DirectEditPolicy {

	@Override
	protected Command getDirectEditCommand(DirectEditRequest request) {
		if (getHost() instanceof IDirectEditable) {
			IOnotoaEditDomain ed = (IOnotoaEditDomain) getHost().getViewer().getEditDomain();
			org.eclipse.emf.common.command.Command cmd = getCommand(getHost().getModel(), request);
			if (cmd!=null)
				return new CommandAdapter(ed.getEditingDomain().getCommandStack(), cmd);
		
		}
		return null;
	}
	
	protected String getNewString(DirectEditRequest request) {
		CellEditor cellEditor = request.getCellEditor();
		return (String) cellEditor.getValue();
	}
	
	/**
	 * Returns the command for changes of the model.
	 * @param model the model element
	 * @param request the {@link DirectEditRequest} of the {@link EditPart}
	 * @return
	 */
	public abstract org.eclipse.emf.common.command.Command getCommand(Object model, DirectEditRequest request);
		
	@Override
	protected void revertOldEditValue(DirectEditRequest request) {
		if (getHost() instanceof IDirectEditable) {
			IDirectEditable otcep = ((IDirectEditable)getHost());
			
			otcep.revertNameChange();
		}
	}

	@Override
	protected void showCurrentEditValue(DirectEditRequest request) {
		String value = (String) request.getCellEditor().getValue();
		IDirectEditable ep = (IDirectEditable) getHost();
		((IDirectEditable)ep).handleNameChange(value);
	}

}