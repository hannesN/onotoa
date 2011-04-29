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

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.BendpointEditPolicy;
import org.eclipse.gef.requests.BendpointRequest;

import de.topicmapslab.tmcledit.diagram.command.CommandAdapter;
import de.topicmapslab.tmcledit.diagram.editor.TMCLEditDomain;
import de.topicmapslab.tmcledit.model.Edge;
import de.topicmapslab.tmcledit.model.commands.AddBendpointCommand;
import de.topicmapslab.tmcledit.model.commands.MoveBendpointCommand;
import de.topicmapslab.tmcledit.model.commands.RemoveBendpointCommand;

/**
 * Policy to create and remove bendpoints
 * 
 * @author Hannes Niederhausen
 *
 */
public class OnotoaBendpointEditPolicy extends BendpointEditPolicy {
	@Override
	protected Command getMoveBendpointCommand(BendpointRequest request) {
		Point location = getLocation(request);

		Edge edge = (Edge) request.getSource().getModel();
		return new CommandAdapter(getCommandStack(),
				new MoveBendpointCommand(edge, request.getIndex(),
										location.x, location.y));
	}

	@Override
	protected Command getDeleteBendpointCommand(BendpointRequest request) {
		return new CommandAdapter(getCommandStack(), 
				new RemoveBendpointCommand((Edge) request.getSource().getModel(), 
											request.getIndex()));
	}

	@Override
	protected Command getCreateBendpointCommand(BendpointRequest request) {
		Point location = getLocation(request);

		Edge edge = (Edge) request.getSource().getModel();

		return new CommandAdapter(getCommandStack(), new AddBendpointCommand(
				edge, request.getIndex(), location.x, location.y));
	}

	private CommandStack getCommandStack() {
		TMCLEditDomain ed = (TMCLEditDomain) getHost().getViewer()
				.getEditDomain();
		return ed.getEditingDomain().getCommandStack();
	}
	
	private Point getLocation(BendpointRequest req) {
		Point p = req.getLocation().getCopy();
		req.getSource().getFigure().translateToRelative(p);
		return p;
	}
}