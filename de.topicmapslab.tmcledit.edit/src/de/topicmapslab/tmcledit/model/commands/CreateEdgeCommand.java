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
package de.topicmapslab.tmcledit.model.commands;

import org.eclipse.emf.common.command.AbstractCommand;

import de.topicmapslab.tmcledit.model.AssociationNode;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.Edge;
import de.topicmapslab.tmcledit.model.Node;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.TypeNode;

public class CreateEdgeCommand extends AbstractCommand {

	private Diagram diagram;
	private Edge edge;
	private final boolean editType;

	public CreateEdgeCommand(Edge newEdge) {
		this(newEdge, null, true);
	}

	public CreateEdgeCommand(Edge newEdge, Diagram diagram) {
		this(newEdge, diagram, true);
	}

	public CreateEdgeCommand(Edge newEdge, Diagram diagram, boolean editType) {
		edge = newEdge;
		this.diagram = diagram;
		this.editType = editType;
	}

	public void setSource(Node source) {
		edge.setSource(source);
	}

	public void setTarget(Node target) {
		edge.setTarget(target);
	}

	public void setDiagram(Diagram diagram) {
		this.diagram = diagram;
	}

	@Override
	public boolean canExecute() {
		return prepare();
	}

	@Override
	protected boolean prepare() {
		if ((edge.getTarget() != null) && (edge.getSource() != null)
				&& (diagram != null)) {
			if (edge.getRoleConstraint() != null) {
				edge.getRoleConstraint().setPlayer(
						((TypeNode) edge.getTarget()).getTopicType());
			}
		} else {
			return false;
		}
		return true;
	}

	public void execute() {
		redo();
	}

	public Edge getEdge() {
		return edge;
	}

	@Override
	public void undo() {
		if (editType) {
			switch (edge.getType()) {
			case AKO_TYPE:
				TopicType source = ((TypeNode) edge.getSource()).getTopicType();
				TopicType target = ((TypeNode) edge.getTarget()).getTopicType();
				source.getAko().remove(target);
				break;
			case IS_ATYPE:
				source = ((TypeNode) edge.getSource()).getTopicType();
				target = ((TypeNode) edge.getTarget()).getTopicType();
				source.getIsa().remove(target);
				break;
			case ROLE_CONSTRAINT_TYPE:
				((AssociationNode) edge.getSource()).getAssociationConstraint()
						.getPlayerConstraints()
						.remove(edge.getRoleConstraint());
				diagram.getEdges().remove(edge);
				break;

			}
		}
		diagram.getEdges().remove(edge);
	}

	public void redo() {
		if (editType) {
			switch (edge.getType()) {
			case AKO_TYPE:
				TopicType source = ((TypeNode) edge.getSource()).getTopicType();
				TopicType target = ((TypeNode) edge.getTarget()).getTopicType();
				source.getAko().add(target);
				break;
			case IS_ATYPE:
				source = ((TypeNode) edge.getSource()).getTopicType();
				target = ((TypeNode) edge.getTarget()).getTopicType();
				source.getIsa().add(target);
				break;
			case ROLE_CONSTRAINT_TYPE:
				((AssociationNode) edge.getSource()).getAssociationConstraint()
						.getPlayerConstraints().add(edge.getRoleConstraint());
				diagram.getEdges().add(edge);
				break;

			}
		}
		diagram.getEdges().add(edge);

	}

}
