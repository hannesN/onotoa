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

import de.topicmapslab.tmcledit.model.AssociationNode;
import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.Edge;
import de.topicmapslab.tmcledit.model.EdgeType;
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;

public class DeleteRolePlayerConstraintCommand extends AbstractCommand {

	private final AssociationTypeConstraint associationTypeConstraint;
	private final RolePlayerConstraint rolePlayerConstraint;
	
	private Map<Diagram, Edge>  edgeMap = Collections.emptyMap();
	private Map<Diagram, Integer> indexMap  = Collections.emptyMap();
	
	private int contraintIndex;
	
	public DeleteRolePlayerConstraintCommand(
			AssociationTypeConstraint associationTypeConstraint,
			RolePlayerConstraint RolePlayerConstraint) {
		super();
		this.associationTypeConstraint = associationTypeConstraint;
		this.rolePlayerConstraint = RolePlayerConstraint;
	}

	public void execute() {
		for (Diagram d : edgeMap.keySet()) {
			d.getEdges().remove(edgeMap.get(d));
		}
		associationTypeConstraint.getPlayerConstraints().remove(rolePlayerConstraint);
	}

	public void redo() {
		execute();
	}

	@Override
	public void undo() {
		associationTypeConstraint.getPlayerConstraints().add(contraintIndex, rolePlayerConstraint);
		for (Diagram d : edgeMap.keySet()) {
			int idx = indexMap.get(d);
			d.getEdges().add(idx, edgeMap.get(d));
		}
	}
	
	@Override
	protected boolean prepare() {
		for (Diagram d : ModelIndexer.getInstance().getDiagrams()) {
			AssociationNode node = (AssociationNode) ModelIndexer.getNodeIndexer()
					.getNodeFor(associationTypeConstraint, d);
			
			if (node!=null) {
				for (Edge e : ModelIndexer.getNodeIndexer().getEdges(d, EdgeType.ROLE_CONSTRAINT_TYPE)) {
					if (e.getRoleConstraint().equals(rolePlayerConstraint)) {
						addEdge(d, e);
					}
				}
			}
		}
		
		contraintIndex = associationTypeConstraint.getPlayerConstraints().indexOf(rolePlayerConstraint);
		return true;
	}

	private void addEdge(Diagram d, Edge e) {
		if (edgeMap==Collections.EMPTY_MAP) {
			edgeMap = new HashMap<Diagram, Edge>();
		}
		edgeMap.put(d, e);
		addIndex(d, d.getEdges().indexOf(e));
	}
	
	private void addIndex(Diagram d, int idx) {
		if (indexMap==Collections.EMPTY_MAP) {
			indexMap = new HashMap<Diagram, Integer>();
		}
		indexMap.put(d, idx);
	}
}
