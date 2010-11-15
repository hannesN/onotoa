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
import org.eclipse.emf.common.util.EList;

import de.topicmapslab.tmcledit.model.AssociationNode;
import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.Edge;
import de.topicmapslab.tmcledit.model.EdgeType;
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;

/**
 * Command to delete a role player constraint. The {@link #execute()} method
 * will re-call {@link #prepare()} to make sure the model hasn't changed.
 * Changes can occure if this command is part of a {@link CompoundCommand}. If
 * the constraint was already removed by another command this command does
 * nothing.
 * 
 * @author Hannes Niederhausen
 * 
 */
public class DeleteRolePlayerConstraintCommand extends AbstractCommand {

	private final AssociationTypeConstraint associationTypeConstraint;
	private final RolePlayerConstraint rolePlayerConstraint;

	private Map<Diagram, Edge> edgeMap = null;
	private Map<Diagram, Integer> indexMap = null;

	private int contraintIndex;

	public DeleteRolePlayerConstraintCommand(RolePlayerConstraint rolePlayerConstraint) {
		super();

		assert (rolePlayerConstraint != null);
		assert (rolePlayerConstraint.eContainer() != null);

		this.associationTypeConstraint = (AssociationTypeConstraint) rolePlayerConstraint.eContainer();
		this.rolePlayerConstraint = rolePlayerConstraint;
	}

	public void execute() {

		prepare();
		if (!isPrepared)
			return;
		for (Diagram d : getEdgeMap().keySet()) {
			d.getEdges().remove(getEdgeMap().get(d));
		}
		associationTypeConstraint.getPlayerConstraints().remove(rolePlayerConstraint);
	}

	public void redo() {
		if (!isPrepared)
			return;

		for (Diagram d : getEdgeMap().keySet()) {
			d.getEdges().remove(getEdgeMap().get(d));
		}
		associationTypeConstraint.getPlayerConstraints().remove(rolePlayerConstraint);
	}

	@Override
	public void undo() {
		if (!isPrepared)
			return;

		EList<RolePlayerConstraint> tmp = associationTypeConstraint.getPlayerConstraints();
		tmp.add(contraintIndex, rolePlayerConstraint);
		for (Diagram d : getEdgeMap().keySet()) {
			int idx = getIndexMap().get(d);
			d.getEdges().add(idx, getEdgeMap().get(d));
		}
	}

	@Override
	protected boolean prepare() {
		contraintIndex = associationTypeConstraint.getPlayerConstraints().indexOf(rolePlayerConstraint);
		if (contraintIndex == -1) {
			this.isPrepared = false;
			return false;
		}
		edgeMap = null;
		indexMap = null;

		for (Diagram d : ModelIndexer.getInstance().getDiagrams()) {
			AssociationNode node = (AssociationNode) ModelIndexer.getNodeIndexer().getNodeFor(
			        associationTypeConstraint, d);

			if (node != null) {
				for (Edge e : ModelIndexer.getNodeIndexer().getEdges(d, EdgeType.ROLE_CONSTRAINT_TYPE)) {
					if (e.getRoleConstraint().equals(rolePlayerConstraint)) {
						addEdge(d, e);
					}
				}
			}
		}

		return true;
	}

	private void addEdge(Diagram d, Edge e) {
		if (edgeMap == null) {
			edgeMap = new HashMap<Diagram, Edge>();
		}
		edgeMap.put(d, e);
		addIndex(d, d.getEdges().indexOf(e));
	}

	private void addIndex(Diagram d, int idx) {
		if (indexMap == null) {
			indexMap = new HashMap<Diagram, Integer>();
		}
		indexMap.put(d, idx);
	}

	private Map<Diagram, Integer> getIndexMap() {
		if (indexMap == null)
			return Collections.emptyMap();

		return indexMap;
	}

	private Map<Diagram, Edge> getEdgeMap() {
		if (edgeMap == null)
			return Collections.emptyMap();

		return edgeMap;
	}
}
