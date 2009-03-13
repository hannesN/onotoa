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

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;

import de.topicmapslab.tmcledit.diagram.command.CommandAdapter;
import de.topicmapslab.tmcledit.diagram.editor.TMCLEditDomain;
import de.topicmapslab.tmcledit.diagram.editparts.AssociationNodeEditPart;
import de.topicmapslab.tmcledit.model.AssociationNode;
import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.Edge;
import de.topicmapslab.tmcledit.model.EdgeType;
import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.Node;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.TypeNode;
import de.topicmapslab.tmcledit.model.commands.CreateEdgeCommand;
import de.topicmapslab.tmcledit.model.util.ModelIndexer;

public class NodeGraphicalNodeEditPolicy extends GraphicalNodeEditPolicy {

	@Override
	protected Command getConnectionCompleteCommand(
			CreateConnectionRequest request) {
		
		CreateEdgeCommand cmd = (CreateEdgeCommand) ((CommandAdapter)request
				.getStartCommand()).getEmfCommand();
		
		if (request.getTargetEditPart()==request.getSourceEditPart())
			return null;
		
		if ( (request.getSourceEditPart() instanceof AssociationNodeEditPart) &&
			 (request.getTargetEditPart() instanceof AssociationNodeEditPart) )
			return null;
		
		Node node = (Node) request.getTargetEditPart().getModel();
		if (node instanceof AssociationNode)
			cmd.setSource(node);
		else
			cmd.setTarget(node);

		
		
		if (cmd.getEdge().getType()==EdgeType.IS_ATYPE) {
			TopicType target = ((TypeNode)cmd.getEdge().getTarget()).getTopicType();
		
			if ( (target.getKind()==KindOfTopicType.NO_TYPE) && 
				 (ModelIndexer.getInstance().getTopicMapSchema().isActiveTopicTypeConstraint()) ) {
				
				return null;
			}
			
		}
		return request.getStartCommand();
	}

	@Override
	protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
		CreateEdgeCommand cmd = new CreateEdgeCommand((Edge) request.getNewObject());
		TMCLEditDomain ed = (TMCLEditDomain) getHost().getViewer().getEditDomain();
		Diagram d = (Diagram) getHost().getParent().getModel();
		cmd.setDiagram(d);
		
		Node node = (Node) getHost().getModel();
		if (node instanceof AssociationNode)
			cmd.setSource(node);
		else
			cmd.setTarget(node);

		CommandAdapter cmdAdapter = new CommandAdapter(ed.getEditingDomain().getCommandStack(), cmd);
		request.setStartCommand(cmdAdapter);

		return cmdAdapter;
	}

	@Override
	protected Command getReconnectSourceCommand(ReconnectRequest request) {
		return null;
	}

	@Override
	protected Command getReconnectTargetCommand(ReconnectRequest request) {
		return null;
	}

}
