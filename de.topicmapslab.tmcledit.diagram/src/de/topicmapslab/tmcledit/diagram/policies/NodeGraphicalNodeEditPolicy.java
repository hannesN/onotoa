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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.command.CommandStack;
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
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.TypeNode;
import de.topicmapslab.tmcledit.model.commands.AddTopicRoleCommand;
import de.topicmapslab.tmcledit.model.commands.CreateEdgeCommand;
import de.topicmapslab.tmcledit.model.commands.SetAkoCommand;
import de.topicmapslab.tmcledit.model.commands.SetIsACommand;

public class NodeGraphicalNodeEditPolicy extends GraphicalNodeEditPolicy {

	@Override
	protected Command getConnectionCompleteCommand(
			CreateConnectionRequest request) {

		CreateEdgeCommand cmd = (CreateEdgeCommand) ((CommandAdapter) request
				.getStartCommand()).getEmfCommand();

		if (request.getTargetEditPart() == request.getSourceEditPart())
			return null;

		if ((request.getSourceEditPart() instanceof AssociationNodeEditPart)
				&& (request.getTargetEditPart() instanceof AssociationNodeEditPart))
			return null;

		Node node = (Node) request.getTargetEditPart().getModel();

		if (cmd.getEdge().getType() == EdgeType.ROLE_CONSTRAINT_TYPE) {
			if (node instanceof AssociationNode) {
				cmd.setSource(node);
			} else {
				cmd.setTarget(node);
			}
			AssociationTypeConstraint atc = ((AssociationNode) cmd.getEdge().getSource()).getAssociationConstraint();
			RolePlayerConstraint rpc = cmd.getEdge().getRoleConstraint();
			
			AddTopicRoleCommand trCmd = new AddTopicRoleCommand(rpc, atc, ((TypeNode)cmd.getEdge().getTarget()).getTopicType());
			return new CommandAdapter(getCommandStack(), trCmd);
		} 
		
		cmd.setTarget(node);
		if (cmd.getEdge().getType() == EdgeType.IS_ATYPE) {
			return getISACommand(cmd);
		} else if (cmd.getEdge().getType() == EdgeType.AKO_TYPE) {
			return getAKOCommand(cmd);
		}
		return request.getStartCommand();
	}

	private Command getISACommand(CreateEdgeCommand cmd) {
		TopicType target = ((TypeNode) cmd.getEdge().getTarget())
				.getTopicType();
		TopicType source = ((TypeNode) cmd.getEdge().getSource()).getTopicType();

		if (target.getKind() == KindOfTopicType.NO_TYPE) {
			return null;
		}
		
		List<TopicType> newList = new ArrayList<TopicType>(source.getIsa());
		newList.add(target);
		SetIsACommand isACmd = new SetIsACommand(newList, source);
		return new CommandAdapter(getCommandStack(), isACmd);
	}

	private Command getAKOCommand(CreateEdgeCommand cmd) {
		TopicType target = ((TypeNode) cmd.getEdge().getTarget()).getTopicType();
		TopicType source = ((TypeNode) cmd.getEdge().getSource()).getTopicType();

		if (target.getKind() == KindOfTopicType.NO_TYPE) {
			return null;
		}
		
		List<TopicType> newList = new ArrayList<TopicType>(source.getAko());
		newList.add(target);
		SetAkoCommand akoCmd = new SetAkoCommand(newList, source);
		return new CommandAdapter(getCommandStack(), akoCmd);
	}

	@Override
	protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
		CreateEdgeCommand cmd = new CreateEdgeCommand((Edge) request
				.getNewObject());
		Diagram d = (Diagram) getHost().getParent().getModel();
		cmd.setDiagram(d);

		Node node = (Node) getHost().getModel();
		if (cmd.getEdge().getType() == EdgeType.ROLE_CONSTRAINT_TYPE) {
			if (node instanceof AssociationNode)
				cmd.setSource(node);
			else
				cmd.setTarget(node);
		} else {
			cmd.setSource(node);
		}

		CommandAdapter cmdAdapter = new CommandAdapter(getCommandStack(), cmd);
		request.setStartCommand(cmdAdapter);

		return cmdAdapter;
	}

	private CommandStack getCommandStack() {
		return getEditDomain().getEditingDomain().getCommandStack();
	}
	
	private TMCLEditDomain getEditDomain() {
		TMCLEditDomain ed = (TMCLEditDomain) getHost().getViewer()
				.getEditDomain();
		return ed;
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
