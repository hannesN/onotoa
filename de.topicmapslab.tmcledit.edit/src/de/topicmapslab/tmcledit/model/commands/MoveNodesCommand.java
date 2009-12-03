package de.topicmapslab.tmcledit.model.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

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


public class MoveNodesCommand extends AbstractNodeListCommand {

	private final Diagram oldDiagram;
	
	private List<Comment> commentList;
	
	private List<Edge> oldEdgeList;
	private List<Edge> newEdgeList;
	
	
	
	
	public MoveNodesCommand(List<Node> nodeList, Diagram newDiagram) {
	    super(nodeList, newDiagram);
	    this.oldDiagram = (Diagram) nodeList.get(0).eContainer();
    }

	public void execute() {
		oldDiagram.getEdges().removeAll(oldEdgeList);
		oldDiagram.getNodes().removeAll(nodeList);
		oldDiagram.getComments().removeAll(commentList);
		
		newDiagram.getNodes().addAll(nodeList);
		newDiagram.getEdges().addAll(newEdgeList);
		newDiagram.getComments().addAll(commentList);
		
    }

	public void redo() {
		execute();	    
    }
	
	@Override
	public void undo() {
		newDiagram.getEdges().removeAll(newEdgeList);
		newDiagram.getNodes().removeAll(nodeList);
		newDiagram.getComments().removeAll(commentList);

		oldDiagram.getNodes().addAll(nodeList);
		oldDiagram.getEdges().addAll(oldEdgeList);
		oldDiagram.getComments().addAll(commentList);
		
	}
	
	@Override
	protected boolean prepare() {
		// removing nodes from nodelist which are already in the newDiagram
		removeNodes();
		findCommentNodes();
		findRemoveEdges();
		findNewEdges();
		
		return true;
	}
	
	

	private void findCommentNodes() {
	    Iterator<Node> it = nodeList.iterator();
	    commentList = new ArrayList<Comment>();
	    while (it.hasNext()) {
	    	Node n = it.next();
	    	if (n instanceof Comment) {
	    		commentList.add((Comment) n);
	    		it.remove();
	    	}
	    }
	    
	    if (commentList.size()==0) {
	    	commentList=Collections.emptyList();
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
	    		Edge edge = createEdge(node, node2, EdgeType.AKO_TYPE);
	    		addNewEdge(edge);
	    	}
	    }
	    for(TopicType tt : ModelIndexer.getTopicIndexer().getUsedAsAko(topicType)) {
	    	Node node2 = findNode(tt);
	    	if (node2!=null) {
	    		Edge edge = createEdge(node2, node, EdgeType.AKO_TYPE);
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
						Edge e = createEdge(assocNode, n, EdgeType.ROLE_CONSTRAINT_TYPE);
						e.setRoleConstraint(rpc);
						newEdgeList.add(e);
					}
				}
			}
		} else {
			TypeNode tn = (TypeNode) node;
			AssociationIndexer associationIndexer = ModelIndexer.getAssociationIndexer();
			for (RolePlayerConstraint rpc : associationIndexer.getRolePlayerConstraintsFor(tn.getTopicType())) {
				AssociationTypeConstraint atc = (AssociationTypeConstraint) rpc.eContainer();
				Node node2 = nodeIndexer.getNodeFor(atc, newDiagram);
				if (node2!=null) {
					Edge e = createEdge(tn, node2, EdgeType.ROLE_CONSTRAINT_TYPE);
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

	private Node findNode(TopicType tt) {
		TopicTypeNodeIndexer nodeIndexer = ModelIndexer.getNodeIndexer();
	    Node node = nodeIndexer.getNodeFor(tt, newDiagram);
	    if (node==null) {
	    	for (Node n : nodeList) {
	    		if ( (n instanceof TypeNode)
	    			&& (((TypeNode)n).getTopicType().equals(tt))) {
	    			return n;
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
	    		Edge edge = createEdge(node, node2, EdgeType.IS_ATYPE);
	    		addNewEdge(edge);
	    	}
	    }
	    for(TopicType tt : ModelIndexer.getTopicIndexer().getUsedAsIsa(topicType) ) {
	    	Node node2 = findNode(tt);
	    	if (node2!=null) {
	    		Edge edge = createEdge(node2, node, EdgeType.IS_ATYPE);
	    		addNewEdge(edge);
	    	}
	    }
    }

	private void findRemoveEdges() {
		oldEdgeList = new ArrayList<Edge>();
		for (Edge e : oldDiagram.getEdges()) {
			if (nodeList.contains(e.getSource()) || nodeList.contains(e.getTarget())) {
				oldEdgeList.add(e);
			}
		}
		if (oldEdgeList.size()==0)
			oldEdgeList = Collections.emptyList();
    }
	
	private Edge createEdge(Node node1, Node node2, EdgeType type) {
		Edge edge = ModelFactory.eINSTANCE.createEdge();
		edge.setSource(node1);
		edge.setTarget(node2);
		edge.setType(type);
		return edge;
	}
}
