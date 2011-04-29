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
/**
 * 
 */
package de.topicmapslab.tmcledit.diagram.policies;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;

import de.topicmapslab.tmcledit.diagram.command.CommandAdapter;
import de.topicmapslab.tmcledit.diagram.editor.TMCLEditDomain;
import de.topicmapslab.tmcledit.diagram.editparts.MoveableLabelEditPart;
import de.topicmapslab.tmcledit.model.LabelPos;
import de.topicmapslab.tmcledit.model.commands.SetLabelPosCommand;

/**
 * Layout policy for the moveable labels (like cardinalities of roleplayerconstraints)
 * 
 * @author Hannes Niederhausen
 */
public class LabelXYLayoutEditPolicy extends XYLayoutEditPolicy {
	@Override
	protected EditPolicy createChildEditPolicy(EditPart child) {
		return new NonResizableEditPolicy();
	}

	@Override
	protected Command getCreateCommand(CreateRequest request) {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Command getMoveChildrenCommand(Request request) {
		if (request.getType()==RequestConstants.REQ_MOVE_CHILDREN) {
			TMCLEditDomain ed = (TMCLEditDomain) getHost().getViewer().getEditDomain();
			ChangeBoundsRequest req = (ChangeBoundsRequest) request;
			List<EditPart> epList = req.getEditParts();
			
			MoveableLabelEditPart ep = (MoveableLabelEditPart) epList.get(0); 
			
			LabelPos pos = (LabelPos) ep.getModel();
			
			int x = pos.getPosX()+req.getMoveDelta().x;
			int y = pos.getPosY()+req.getMoveDelta().y;
			
								
			return new CommandAdapter(ed.getEditingDomain()
					.getCommandStack(), 
					new SetLabelPosCommand((LabelPos) ep.getModel(), x, y));
			
		}
		
		return null;
	}

	@Override
	protected Command createChangeConstraintCommand(EditPart child,
			Object constraint) {
		return null;
	}
}