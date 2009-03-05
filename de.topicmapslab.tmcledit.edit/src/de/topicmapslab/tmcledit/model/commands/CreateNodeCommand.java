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
package de.topicmapslab.tmcledit.model.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.swt.graphics.Point;

import de.topicmapslab.tmcledit.model.AssociationNode;
import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.Edge;
import de.topicmapslab.tmcledit.model.EdgeType;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.Node;
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.TypeNode;
import de.topicmapslab.tmcledit.model.util.ModelIndexer;

/**
 * @author Hannes Niederhausen
 * 
 */
public class CreateNodeCommand extends AbstractCommand {

	public enum Type {
		ASSOCIATION, TYPE
	}

	private final Type type;

	private final Node node;

	private final Diagram diagram;
	
	private List<CreateEdgeCommand> edgeCommands = Collections.emptyList();

	private boolean createdNewType = false;

	public CreateNodeCommand(Diagram diagram, Point location, Node node) {
		if (node instanceof TypeNode) {
			this.type = Type.TYPE;
		} else {
			this.type = Type.ASSOCIATION;
		}

		this.diagram = diagram;

		this.node = node;

		node.setPosX(location.x);
		node.setPosY(location.y);
	}

	@Override
	public boolean canExecute() {
		prepare();
		if ((node instanceof TypeNode) && (!createdNewType)) {
			TopicType tt = ((TypeNode) node).getTopicType();
			for (Node n : diagram.getNodes()) {
				if (n instanceof TypeNode) {
					if (((TypeNode) n).getTopicType().equals(tt))
						return false;
				}
			}
		}
		return true;
	}

	public void execute() {
		switch (type) {
		case TYPE:
			if (createdNewType) {
				TopicType tt = ((TypeNode) node).getTopicType();
				File file = (File) diagram.eContainer();
				file.getTopicMapSchema().getTopicTypes().add(tt);
			}
			break;
		case ASSOCIATION:
			if (createdNewType) {
				AssociationTypeConstraint atc = ((AssociationNode)node).getAssociationConstraint();
				File file = (File) diagram.eContainer();
				file.getTopicMapSchema().getAssociationTypeConstraints().add(atc);
			}
		}
		diagram.getNodes().add(node);
		for (CreateEdgeCommand cmd : edgeCommands) {
			if (cmd.canExecute())
				cmd.execute();
		}
	}

	@Override
	protected boolean prepare() {
		if (isPrepared)
			return true;
		switch (type) {
		case TYPE:
			TopicType topicType = ((TypeNode) node).getTopicType();
				if (topicType.eContainer() == null) {
				createdNewType = true;
			} else {
				ModelIndexer modelIndexer = ModelIndexer.getInstance();
				for(TopicType tt : topicType.getIsa()) {
					Node node2 = modelIndexer.getNodeFor(tt, diagram);
					if (node2!=null) {
						Edge edge = createEdge(node, node2, EdgeType.IS_ATYPE);
						CreateEdgeCommand cmd = new CreateEdgeCommand(edge, diagram);
						addEdgeCommand(cmd);
					}
				}
				for(TopicType tt : topicType.getAko()) {
					Node node2 = modelIndexer.getNodeFor(tt, diagram);
					if (node2!=null) {
						Edge edge = createEdge(node, node2, EdgeType.AKO_TYPE);
						CreateEdgeCommand cmd = new CreateEdgeCommand(edge, diagram);
						addEdgeCommand(cmd);
					}
				}
				for (RolePlayerConstraint rpc : modelIndexer.getRolePlayerConstraintsFor(topicType)) {
					AssociationTypeConstraint atc = (AssociationTypeConstraint) rpc.eContainer();
					Node node2 = modelIndexer.getNodeFor(atc, diagram);
					if (node2!=null) {
						Edge edge = createEdge(node2, node, EdgeType.ROLE_CONSTRAINT_TYPE);
						edge.setRoleConstraint(rpc);
						CreateEdgeCommand cmd = new CreateEdgeCommand(edge, diagram);
						addEdgeCommand(cmd);
					}
				}
			}
			break;
		case ASSOCIATION:
			if (((AssociationNode) node).getAssociationConstraint()
					.eContainer() == null) {
				createdNewType = true;
			}
		}
		isPrepared = true;
		return true;
	}

	private Edge createEdge(Node node1, Node node2, EdgeType type) {
		Edge edge = ModelFactory.eINSTANCE.createEdge();
		edge.setSource(node1);
		edge.setTarget(node2);
		edge.setType(type);
		return edge;
	}

	public void redo() {
		switch (type) {
		case TYPE:
			if (createdNewType) {
				TopicType tt = ((TypeNode) node).getTopicType();
				File file = (File) diagram.eContainer();
				file.getTopicMapSchema().getTopicTypes().add(tt);
			}
			break;
		case ASSOCIATION:
			if (createdNewType) {
				AssociationTypeConstraint atc = ((AssociationNode)node).getAssociationConstraint();
				File file = (File) diagram.eContainer();
				file.getTopicMapSchema().getAssociationTypeConstraints().add(atc);
			}
		}
		diagram.getNodes().add(node);
		for (CreateEdgeCommand cmd : edgeCommands) {
			cmd.redo();
		}
	}

	@Override
	public void undo() {
		for (CreateEdgeCommand cmd : edgeCommands) {
			cmd.undo();
		}
		diagram.getNodes().remove(node);
		switch (type) {
		case TYPE:
			if (createdNewType) {
				TopicType tt = ((TypeNode) node).getTopicType();
				File file = (File) diagram.eContainer();
				file.getTopicMapSchema().getTopicTypes().remove(tt);
			}
			
			break;
		case ASSOCIATION:
			if (createdNewType) {
				AssociationTypeConstraint atc = ((AssociationNode)node).getAssociationConstraint();
				File file = (File) diagram.eContainer();
				file.getTopicMapSchema().getAssociationTypeConstraints().remove(atc);
			}
		}
		
	}

	@Override
	public String getLabel() {
		return (type==Type.TYPE) ? "Create Type Node" : "Create Association Node";
	}
	
	private void addEdgeCommand(CreateEdgeCommand cmd) {
		if (edgeCommands==Collections.EMPTY_LIST)
			edgeCommands = new ArrayList<CreateEdgeCommand>();
		
		edgeCommands.add(cmd);
	}
}
