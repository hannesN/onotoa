package de.topicmapslab.tmcledit.model.commands;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.command.AbstractCommand;

import de.topicmapslab.tmcledit.model.AssociationNode;
import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.Comment;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.Node;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.TypeNode;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;

/**
 * Abstract command for the move and copy nodes commands.
 * 
 * @author Hannes Niederhausen
 *
 */
public abstract class AbstractNodeListCommand extends AbstractCommand {

	protected final Diagram newDiagram;
	protected final List<Node> nodeList;

	/**
	 * 
	 * @param nodeList list of nodes to work with
	 * @param newDiagram target 
	 */
	public AbstractNodeListCommand(List<Node> nodeList, Diagram newDiagram) {
		super();
		this.newDiagram=newDiagram;
		this.nodeList = nodeList;
	}

	/**
	 * Removing nodes which are already in the target diagram
	 */
	protected void removeNodes() {
    	Iterator<Node> it = nodeList.iterator();
    	while (it.hasNext()) {
    		Node tmp = it.next();
    		if (tmp instanceof TypeNode) {
    			TopicType tt = ((TypeNode) tmp).getTopicType();
    			if (ModelIndexer.getNodeIndexer().getNodeFor(tt, newDiagram)!=null)
    				it.remove();
    		} else if (tmp instanceof AssociationNode) {
    			AssociationTypeConstraint assoConstr = ((AssociationNode) tmp).getAssociationConstraint();
    			if (ModelIndexer.getNodeIndexer().getNodeFor(assoConstr, newDiagram)!=null)
    				it.remove();
    		} else if (tmp instanceof Comment) {
    			String content = ((Comment) tmp).getContent();
    			for (Comment c : newDiagram.getComments()) {
        			if (c.getContent().equals(content))
        				it.remove();
        		}
    		}
    	}
    	
        
    }

}