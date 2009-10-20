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
package de.topicmapslab.tmcledit.domaindiagram.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.jface.viewers.CellEditor;

import de.topicmapslab.tmcledit.domaindiagram.command.CommandAdapter;
import de.topicmapslab.tmcledit.domaindiagram.editor.DomainEditDomain;
import de.topicmapslab.tmcledit.domaindiagram.editparts.IDirectEditable;

public abstract class AbstractDirectEditPolicy extends DirectEditPolicy {

	public AbstractDirectEditPolicy() {
		super();
	}

	@Override
	protected Command getDirectEditCommand(DirectEditRequest request) {
		if (getHost() instanceof IDirectEditable) {
			DomainEditDomain ed = (DomainEditDomain) getHost().getViewer().getEditDomain();
			org.eclipse.emf.common.command.Command cmd = getRenameCommand(getHost().getModel(), request);
			if (cmd!=null)
				return new CommandAdapter(ed.getEditingDomain().getCommandStack(), cmd);
		
		}
		return null;
	}
	
	protected String getNewString(DirectEditRequest request) {
		CellEditor cellEditor = request.getCellEditor();
		return (String) cellEditor.getValue();
	}
	
	public abstract org.eclipse.emf.common.command.Command getRenameCommand(Object model, DirectEditRequest request);
		
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