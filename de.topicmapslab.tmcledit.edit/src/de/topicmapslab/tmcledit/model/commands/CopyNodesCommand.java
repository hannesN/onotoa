package de.topicmapslab.tmcledit.model.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import de.topicmapslab.tmcledit.model.AssociationNode;
import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.Comment;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.DomainDiagram;
import de.topicmapslab.tmcledit.model.Edge;
import de.topicmapslab.tmcledit.model.EdgeType;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.Node;
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.TypeNode;
import de.topicmapslab.tmcledit.model.index.AssociationIndexer;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;
import de.topicmapslab.tmcledit.model.index.TopicTypeNodeIndexer;

/**
 * Command which copies a list of nodes to another diagram
 * 
 * @author Hannes Niederhausen
 *
 */
public class CopyNodesCommand extends AbstractNodeListCommand {

	private List<Edge> newEdgeList;
	
	private Map<Node, Node> newNodesMap;
	private Map<Comment, Comment> newCommentMap;
	
	
	/**
	 * Constructor 
	 * 
	 * @param nodeList list of nodes to copy
	 * @param newDiagram target diagram
	 */
	public CopyNodesCommand(List<Node> nodeList, Diagram newDiagram) {
	    super(nodeList, newDiagram);
	    newNodesMap = new HashMap<Node, Node>(nodeList.size());
	    newCommentMap = new HashMap<Comment, Comment>();
    }

	public void execute() {
		newDiagram.getNodes().addAll(newNodesMap.values());
		newDiagram.getEdges().addAll(newEdgeList);
		newDiagram.getComments().addAll(newCommentMap.values());
		
    }

	public void redo() {
		execute();	    
    }
	
	@Override
	public void undo() {
		newDiagram.getEdges().removeAll(newEdgeList);
		newDiagram.getNodes().removeAll(newNodesMap.values());
		newDiagram.getComments().removeAll(newCommentMap.values());
	}
	
	@Override
	protected boolean prepare() {
		removeNodes();
		createNodes();
		findNewEdges();
		return true;
	}

	private void createNodes() {
		for (Node n : nodeList) {
			Node newNode = null;
			if (n instanceof TypeNode) {
				if (ModelIndexer.getNodeIndexer().getNodeFor(((TypeNode) n).getTopicType(), newDiagram)!=null) {
					return;
				}
				newNode = ModelFactory.eINSTANCE.createTypeNode();
				((TypeNode) newNode).setImage(((TypeNode) n).getImage());
				((TypeNode) newNode).setTopicType(((TypeNode) n).getTopicType());
			} else if (n instanceof AssociationNode) {
				if (ModelIndexer.getNodeIndexer().getNodeFor(((AssociationNode) n).getAssociationConstraint(), newDiagram)!=null) {
					return;
				}
				newNode = ModelFactory.eINSTANCE.createAssociationNode();
				((AssociationNode)newNode).setAssociationConstraint(((AssociationNode) n).getAssociationConstraint());
			} else {
				newNode = ModelFactory.eINSTANCE.createComment();
				((Comment)newNode).setWidth(((Comment) n).getWidth());
				((Comment)newNode).setHeight(((Comment) n).getHeight());
				((Comment)newNode).setContent(((Comment) n).getContent());
			}
			
			newNode.setPosX(n.getPosX());
			newNode.setPosY(n.getPosY());
			if (n instanceof Comment) {
				newCommentMap.put((Comment) n, (Comment) newNode);
			} else {
				newNodesMap.put(n, newNode);
			}
		}
	    
    }

	private void findNewEdges() {
		newEdgeList = new ArrayList<Edge>();

		for (Node n : nodeList) {
			if (n instanceof TypeNode) {
				TypeNode tn = (TypeNode) n;
				createAkOEdges(tn, tn.getTopicType());
				createIsAEdges(tn, tn.getTopicType());
			}
			createRolePlayerEdges(n);
		}
		
		if (newEdgeList.size()==0)
			newEdgeList = Collections.emptyList();
    }
	
	private void createAkOEdges(TypeNode node, TopicType topicType) {
	    for(TopicType tt : topicType.getAko()) {
	    	Node node2 = findNode(tt);
	    	if (node2!=null) {
	    		Edge edge = createEdge(newNodesMap.get(node), node2, EdgeType.AKO_TYPE);
	    		addNewEdge(edge);
	    	}
	    }
	    for(TopicType tt : ModelIndexer.getTopicIndexer().getUsedAsAko(topicType)) {
	    	Node node2 = findNode(tt);
	    	if (node2!=null) {
	    		Edge edge = createEdge(node2, newNodesMap.get(node), EdgeType.AKO_TYPE);
	    		addNewEdge(edge);
	    	}
	    }
    }
	
	private void createRolePlayerEdges(Node node) {
		TopicTypeNodeIndexer nodeIndexer = ModelIndexer.getNodeIndexer();
		if (node instanceof AssociationNode) {
			AssociationNode assocNode = (AssociationNode) node;
			
			for (RolePlayerConstraint rpc : assocNode.getAssociationConstraint().getPlayerConstraints()) {
				if (rpc.getPlayer()==null)
					continue;
				else {
					Node n = findNode(rpc.getPlayer());
					if (n!=null) {
						Edge e = createEdge(newNodesMap.get(assocNode), n, EdgeType.ROLE_CONSTRAINT_TYPE);
						e.setRoleConstraint(rpc);
						newEdgeList.add(e);
					}
				}
			}
		} else if (node instanceof TypeNode) {
			TypeNode tn = (TypeNode) node;
			AssociationIndexer associationIndexer = ModelIndexer.getAssociationIndexer();
			for (RolePlayerConstraint rpc : associationIndexer.getRolePlayerConstraintsFor(tn.getTopicType())) {
				AssociationTypeConstraint atc = (AssociationTypeConstraint) rpc.eContainer();
				Node node2 = nodeIndexer.getNodeFor(atc, newDiagram);
				if (node2!=null) {
					Edge e = createEdge(newNodesMap.get(tn), node2, EdgeType.ROLE_CONSTRAINT_TYPE);
					e.setRoleConstraint(rpc);
					newEdgeList.add(e);
				}
			}
		}
	}
	
	private void addNewEdge(Edge edge) {
		for (Edge e :newEdgeList) {
			if ( (e.getSource()==edge.getSource()) && (e.getTarget()==edge.getTarget()) )
				return;
		}
		newEdgeList.add(edge);
	}

	/*
	 * Finds a node in the new diagram or move list which represents the given topic type
	 */
	private Node findNode(TopicType tt) {
		TopicTypeNodeIndexer nodeIndexer = ModelIndexer.getNodeIndexer();
	    Node node = nodeIndexer.getNodeFor(tt, newDiagram);
	    if (node==null) {
	    	for (Node n : nodeList) {
	    		if ( (n instanceof TypeNode)
	    			&& (((TypeNode)n).getTopicType().equals(tt))) {
	    			return newNodesMap.get(n);
	    		}
	    	}
	    }
	    return node;
    }

	private void createIsAEdges(TypeNode node, TopicType topicType) {
	    if (newDiagram instanceof DomainDiagram)
	    	return;
		for(TopicType tt : topicType.getIsa()) {
	    	Node node2 = findNode(tt);
	    	if (node2!=null) {
	    		Edge edge = createEdge(newNodesMap.get(node), node2, EdgeType.IS_ATYPE);
	    		addNewEdge(edge);
	    	}
	    }
	    for(TopicType tt : ModelIndexer.getTopicIndexer().getUsedAsIsa(topicType) ) {
	    	Node node2 = findNode(tt);
	    	if (node2!=null) {
	    		Edge edge = createEdge(node2, newNodesMap.get(node), EdgeType.IS_ATYPE);
	    		addNewEdge(edge);
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
}
