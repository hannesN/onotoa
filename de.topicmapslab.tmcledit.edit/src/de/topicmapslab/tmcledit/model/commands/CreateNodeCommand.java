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

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.CompoundCommand;
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
import de.topicmapslab.tmcledit.model.index.ModelIndexer;
import de.topicmapslab.tmcledit.model.index.TopicIndexer;
import de.topicmapslab.tmcledit.model.index.TopicTypeNodeIndexer;

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
	
	private CompoundCommand edgeCommands;
	
//	private List<CreateEdgeCommand> edgeCommands = Collections.emptyList();

	private boolean createdNewType = false;

	private boolean addType;
	
	
	public CreateNodeCommand(Diagram diagram, Node node) {
		if (node instanceof TypeNode) {
			this.type = Type.TYPE;
		} else {
			this.type = Type.ASSOCIATION;
		}

		this.diagram = diagram;

		this.node = node;
	}

	public CreateNodeCommand(Diagram diagram, Point location, Node node) {
		this(diagram, node);

		node.setPosX(location.x);
		node.setPosY(location.y);
	}

	@Override
	public boolean canExecute() {
		if (prepare()) {
			if ((node instanceof TypeNode) && (!createdNewType)) {
				TopicType tt = ((TypeNode) node).getTopicType();
				for (Node n : diagram.getNodes()) {
					if (n instanceof TypeNode) {
						if (((TypeNode) n).getTopicType().equals(tt))
							return false;
					}
				}
			} else if (node instanceof AssociationNode) {
				AssociationTypeConstraint atc = ((AssociationNode) node).getAssociationConstraint();
				if (atc != null) {
					if (ModelIndexer.getNodeIndexer().getNodeFor(atc, diagram)!=null)
						return false;
					TopicType at = atc.getType();
					if ((at!=null) && (at.eContainer()==null))
						addType = true;
				}
			}
			return (edgeCommands.isEmpty() || edgeCommands.canExecute());
		}
		return false;
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
				
				if (addType)
					file.getTopicMapSchema().getTopicTypes().add(atc.getType());
				
				file.getTopicMapSchema().getAssociationTypeConstraints().add(atc);
			}
		}
		diagram.getNodes().add(node);
		edgeCommands.execute();
	}

	@Override
	protected boolean prepare() {
		if (isPrepared)
			return true;
		
		edgeCommands = new CompoundCommand();
		TopicTypeNodeIndexer nodeIndexer = ModelIndexer.getNodeIndexer();
		
		switch (type) {
		case TYPE:
			TopicType topicType = ((TypeNode) node).getTopicType();
			if (topicType.eContainer() == null) {
				createdNewType = true;
			} else {
				
				TopicIndexer topicIndexer = ModelIndexer.getTopicIndexer();
				createIsAEdges(topicType, nodeIndexer, topicIndexer);
				createAkOEdges(topicType, nodeIndexer, topicIndexer);
				
				for (RolePlayerConstraint rpc : ModelIndexer.getAssociationIndexer().getRolePlayerConstraintsFor(topicType)) {
					AssociationTypeConstraint atc = (AssociationTypeConstraint) rpc.eContainer();
					Node node2 = nodeIndexer.getNodeFor(atc, diagram);
					if (node2!=null) {
						createRPEdge(node2, node, rpc);
					}
				}
			}
			break;
		case ASSOCIATION:
			AssociationNode associationNode = (AssociationNode) node;
			if (associationNode.getAssociationConstraint()
					.eContainer() == null) {
				createdNewType = true;
			} else {
				AssociationTypeConstraint atc = associationNode.getAssociationConstraint();
				if (atc!=null) {
					for (RolePlayerConstraint rpc : atc.getPlayerConstraints()) {
						TopicType player = rpc.getPlayer();
						if (player==null)
							continue;
						
						Node pnode = nodeIndexer.getNodeFor(player, diagram);
						if (pnode==null)
							continue;
						createRPEdge(node, pnode, rpc);
					}
					
				}
					
				
			}
		}
		isPrepared = true;
		return true;
	}

	private void createRPEdge(Node node1, Node node2, RolePlayerConstraint rpc) {
		Edge edge = createEdge(node1, node2, EdgeType.ROLE_CONSTRAINT_TYPE);
		edge.setRoleConstraint(rpc);
		CreateEdgeCommand cmd = new CreateEdgeCommand(edge, diagram);
		edgeCommands.append(cmd);
	}
	
	private void createAkOEdges(TopicType topicType, TopicTypeNodeIndexer nodeIndexer, TopicIndexer topicIndexer) {
	    for(TopicType tt : topicType.getAko()) {
	    	Node node2 = nodeIndexer.getNodeFor(tt, diagram);
	    	if (node2!=null) {
	    		Edge edge = createEdge(node, node2, EdgeType.AKO_TYPE);
	    		CreateEdgeCommand cmd = new CreateEdgeCommand(edge, diagram);
	    		edgeCommands.append(cmd);
	    	}
	    }
	    for(TopicType tt : topicIndexer.getUsedAsAko(topicType)) {
	    	Node node2 = nodeIndexer.getNodeFor(tt, diagram);
	    	if (node2!=null) {
	    		Edge edge = createEdge(node2, node, EdgeType.AKO_TYPE);
	    		CreateEdgeCommand cmd = new CreateEdgeCommand(edge, diagram);
	    		edgeCommands.append(cmd);
	    	}
	    }
    }

	private void createIsAEdges(TopicType topicType, TopicTypeNodeIndexer nodeIndexer, TopicIndexer topicIndexer) {
	    for(TopicType tt : topicType.getIsa()) {
	    	Node node2 = nodeIndexer.getNodeFor(tt, diagram);
	    	if (node2!=null) {
	    		Edge edge = createEdge(node, node2, EdgeType.IS_ATYPE);
	    		CreateEdgeCommand cmd = new CreateEdgeCommand(edge, diagram);
	    		edgeCommands.append(cmd);
	    	}
	    }
	    for(TopicType tt : topicIndexer.getUsedAsIsa(topicType) ) {
	    	Node node2 = nodeIndexer.getNodeFor(tt, diagram);
	    	if (node2!=null) {
	    		Edge edge = createEdge(node2, node, EdgeType.IS_ATYPE);
	    		CreateEdgeCommand cmd = new CreateEdgeCommand(edge, diagram);
	    		edgeCommands.append(cmd);
	    	}
	    }
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
				if (addType) {
					file.getTopicMapSchema().getTopicTypes().add(atc.getType());
				}
				file.getTopicMapSchema().getAssociationTypeConstraints().add(atc);
			}
		}
		diagram.getNodes().add(node);
		edgeCommands.redo();
	}

	@Override
	public void undo() {
		edgeCommands.undo();
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
				if (addType)
					file.getTopicMapSchema().getTopicTypes().add(atc.getType());
				file.getTopicMapSchema().getAssociationTypeConstraints().remove(atc);
			}
		}
		
	}

	@Override
	public String getLabel() {
		return (type==Type.TYPE) ? "Create Type Node" : "Create Association Node";
	}
}
