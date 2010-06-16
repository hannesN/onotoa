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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.command.AbstractCommand;

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
	private List<DeleteRolePlayerConstraintCommand> cmds = Collections.emptyList();
	
	private Map<Diagram, Node> nodeMap = Collections.emptyMap();
	private Map<Diagram, Integer> indexMap = Collections.emptyMap();
	
	private int atcIndex;

	
	
	public DeleteAssociationConstraintCommand(
			AssociationTypeConstraint constraint) {
		super();
		this.constraint = constraint;
		tms = (TopicMapSchema) constraint.eContainer();
	}
	
	
	public void execute() {
		
		for (DeleteRolePlayerConstraintCommand cmd : cmds) {
			cmd.execute();
		}
		

		for (Diagram d : nodeMap.keySet()) {
			d.getNodes().remove(nodeMap.get(d));
		}
		
		tms.getAssociationTypeConstraints().remove(constraint);
		
	}

	public void redo() {
		for (DeleteRolePlayerConstraintCommand cmd : cmds) {
			cmd.redo();
		}
		
		for (Diagram d : nodeMap.keySet()) {
			d.getNodes().remove(nodeMap.get(d));
		}
		
		tms.getAssociationTypeConstraints().remove(constraint);
	}
	
	@Override
	public void undo() {
		
		tms.getAssociationTypeConstraints().add(atcIndex, constraint);
		
		
		for (Diagram d : nodeMap.keySet()) {
			int idx = indexMap.get(d);
			d.getNodes().add(idx, nodeMap.get(d));
		}
		
		for (DeleteRolePlayerConstraintCommand cmd : cmds) {
			cmd.undo();
		}
	}
	
	@Override
	protected boolean prepare() {
		if (constraint.getPlayerConstraints().size()>0) {
			cmds = new ArrayList<DeleteRolePlayerConstraintCommand>(constraint.getPlayerConstraints().size());
		}
		
		atcIndex = tms.getAssociationTypeConstraints().indexOf(constraint);
		
		for (RolePlayerConstraint rpc : constraint.getPlayerConstraints()) {
			DeleteRolePlayerConstraintCommand cmd = new DeleteRolePlayerConstraintCommand(constraint, rpc);
			if (cmd.canExecute())
				cmds.add(cmd);
		}
		
		File file = (File) tms.eContainer();
		for (Diagram d : file.getDiagrams()) {
			AssociationNode node = (AssociationNode) ModelIndexer.getNodeIndexer()
					.getNodeFor(constraint, d);
			if (node!=null) {
				addNode(d, node);
			}
		}
		
		return true;
	}


	private void addNode(Diagram d, AssociationNode node) {
		if (nodeMap==Collections.EMPTY_MAP) {
			nodeMap = new HashMap<Diagram, Node>();
		}
		nodeMap.put(d, node);
		addIndex(d, d.getNodes().indexOf(node));
	}	

	private void addIndex(Diagram d, int idx) {
		if (indexMap==Collections.EMPTY_MAP) {
			indexMap = new HashMap<Diagram, Integer>();
		}
		indexMap.put(d, idx);
	}	

}
