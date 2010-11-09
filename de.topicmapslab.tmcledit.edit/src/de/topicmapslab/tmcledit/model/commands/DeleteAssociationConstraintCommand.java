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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.CompoundCommand;

import de.topicmapslab.tmcledit.model.AssociationNode;
import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.Node;
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;

public class DeleteAssociationConstraintCommand extends AbstractCommand {
	
	private final AssociationTypeConstraint constraint;
	private final TopicMapSchema tms;
	private CompoundCommand cmd;
	
	private Map<Diagram, Node> nodeMap = null;
	private Map<Diagram, Integer> indexMap = null;
	
	private int atcIndex;

	
	
	public DeleteAssociationConstraintCommand(
			AssociationTypeConstraint constraint) {
		super();
		this.constraint = constraint;
		tms = (TopicMapSchema) constraint.eContainer();
	}
	
	
	public void execute() {
		// prepare again because model could have changed
		prepare();
		
		cmd.execute();
		

		for (Diagram d : getNodeMap().keySet()) {
			d.getNodes().remove(getNodeMap().get(d));
		}
		
		tms.getAssociationTypeConstraints().remove(constraint);
		
	}
	
	public void redo() {
		cmd.redo();
		
		for (Diagram d : getNodeMap().keySet()) {
			d.getNodes().remove(getNodeMap().get(d));
		}
		
		tms.getAssociationTypeConstraints().remove(constraint);
	}
	
	@Override
	public void undo() {
		
		tms.getAssociationTypeConstraints().add(atcIndex, constraint);
		
		
		for (Diagram d : getNodeMap().keySet()) {
			int idx = getIndexMap().get(d);
			d.getNodes().add(idx, getNodeMap().get(d));
		}
		cmd.undo();
	}
	
	@Override
	protected boolean prepare() {
		atcIndex = tms.getAssociationTypeConstraints().indexOf(constraint);
		
		// reset state
		cmd = new CompoundCommand();
		indexMap = null;
		nodeMap = null;
		
		
		for (RolePlayerConstraint rpc : constraint.getPlayerConstraints()) {
			DeleteRolePlayerConstraintCommand cmd = new DeleteRolePlayerConstraintCommand(constraint, rpc);
			this.cmd.appendIfCanExecute(cmd);
		}
		
		File file = (File) tms.eContainer();
		
		for (Diagram d : file.getDiagrams()) {
			AssociationNode node = (AssociationNode) ModelIndexer.getNodeIndexer().getNodeFor(constraint, d);
			if (node!=null) {
				addNode(d, node);
			}
		}
		
		return true;
	}


	private void addNode(Diagram d, AssociationNode node) {
		if (nodeMap==null) {
			nodeMap = new HashMap<Diagram, Node>();
		}
		nodeMap.put(d, node);
		addIndex(d, d.getNodes().indexOf(node));
	}	

	private void addIndex(Diagram d, int idx) {
		if (indexMap==null) {
			indexMap = new HashMap<Diagram, Integer>();
		}
		indexMap.put(d, idx);
	}	


	private Map<Diagram, Node> getNodeMap() {
		if (nodeMap==null)
			return Collections.emptyMap();
		
	    return nodeMap;
    }
	
	private Map<Diagram, Integer> getIndexMap() {
		if (indexMap==null)
			return Collections.emptyMap();
		
	    return indexMap;
    }
}
