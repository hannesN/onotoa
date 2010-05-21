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

import de.topicmapslab.tmcledit.model.AbstractTypedCardinalityConstraint;
import de.topicmapslab.tmcledit.model.AssociationType;
import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.Edge;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.NameTypeConstraint;
import de.topicmapslab.tmcledit.model.OccurrenceTypeConstraint;
import de.topicmapslab.tmcledit.model.RoleConstraint;
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;
import de.topicmapslab.tmcledit.model.ScopeConstraint;
import de.topicmapslab.tmcledit.model.ScopedTopicType;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.TypeNode;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;

public class DeleteTopicTypeCommand extends AbstractCommand {

	private final TopicType topicType;

	private final boolean ignoreConstraints;
	
	private List<TopicType> isAList = Collections.emptyList();
	private List<TopicType> akoList = Collections.emptyList();;
	private List<ContainmentPair<Diagram, TypeNode>> typeNodeList = Collections.emptyList();

	private List<DeleteTopicTypeConstraintItemCommand> constraintCommands = Collections.emptyList();

	private Map<Diagram, List<Edge>> edgeMap = Collections.emptyMap();

	private List<DeleteAssociationConstraintCommand> associationCommands = Collections.emptyList();

	private List<RemoveScopeConstraintsCommand> scopeCommands = Collections.emptyList();

	private List<RemoveRoleConstraintCommand> roleCommands = Collections.emptyList();

	private List<SetRoleConstraintCommand> roleconstCommands = Collections.emptyList();

	private List<DeleteRolePlayerConstraintCommand> deleteRolePlayerCommands = Collections.emptyList();

	public DeleteTopicTypeCommand(TopicType topicType) {
		this(topicType, false);
	}
	
	public DeleteTopicTypeCommand(TopicType topicType, boolean ignoreConstraints) {
		this.topicType = topicType;
		this.ignoreConstraints = ignoreConstraints;
	}

	public void execute() {
		for (ContainmentPair<Diagram, TypeNode> cp : typeNodeList) {
			if (edgeMap.get(cp.getContainer()) != null) {
				cp.container.getEdges().removeAll(edgeMap.get(cp.getContainer()));
			}
			cp.getContainer().getNodes().remove(cp.getElement());
		}
		for (TopicType tt : isAList) {
			tt.getIsa().remove(topicType);
		}
		for (TopicType tt : akoList) {
			tt.getAko().remove(topicType);
		}

		for (DeleteTopicTypeConstraintItemCommand cmd : constraintCommands) {
			cmd.execute();
		}

		for (DeleteAssociationConstraintCommand cmd : associationCommands) {
			cmd.execute();
		}

		for (RemoveScopeConstraintsCommand cmd : scopeCommands) {
			cmd.execute();
		}

		for (RemoveRoleConstraintCommand cmd : roleCommands) {
			cmd.execute();
		}
		
		for (DeleteRolePlayerConstraintCommand cmd : deleteRolePlayerCommands) {
			cmd.execute();
		}

		for (SetRoleConstraintCommand cmd : roleconstCommands) {
			cmd.execute();
		}

		ModelIndexer.getInstance().getTopicMapSchema().getTopicTypes().remove(topicType);

	}

	@Override
	public void undo() {
		ModelIndexer.getInstance().getTopicMapSchema().getTopicTypes().add(topicType);

		for (TopicType tt : isAList) {
			tt.getIsa().add(topicType);
		}
		for (TopicType tt : akoList) {
			tt.getAko().add(topicType);
		}
		for (ContainmentPair<Diagram, TypeNode> cp : typeNodeList) {
			cp.getContainer().getNodes().add(cp.getElement());
			if (edgeMap.get(cp.getContainer()) != null) {
				cp.container.getEdges().addAll(edgeMap.get(cp.getContainer()));
			}
		}

		for (DeleteTopicTypeConstraintItemCommand cmd : constraintCommands) {
			cmd.undo();
		}
		
		for (DeleteRolePlayerConstraintCommand cmd : deleteRolePlayerCommands) {
			cmd.undo();
		}

		for (DeleteAssociationConstraintCommand cmd : associationCommands) {
			cmd.undo();
		}

		for (RemoveScopeConstraintsCommand cmd : scopeCommands) {
			cmd.undo();
		}
		for (RemoveRoleConstraintCommand cmd : roleCommands) {
			cmd.undo();
		}
		for (SetRoleConstraintCommand cmd : roleconstCommands) {
			cmd.undo();
		}
	}

	public void redo() {
		execute();
	}

	@Override
	protected boolean prepare() {
		if (!isPrepared) {
			// first call of prepare
			extractTopicNodes();
			isAList = ModelIndexer.getTopicIndexer().getInstanceTypes(topicType);
			akoList = ModelIndexer.getTopicIndexer().getSubTypes(topicType);

			prepareConstraintCommandList();

			prepareAssociationCommandsList();

			prepareScopeConstraintCommandList();

			isPrepared = true;
		}

		return true;
	}

	private void prepareAssociationCommandsList() {
		if (ignoreConstraints)
			return;
		
		TopicMapSchema topicMapSchema = ModelIndexer.getInstance()
				.getTopicMapSchema();
		for (AssociationTypeConstraint atc : topicMapSchema
				.getAssociationTypeConstraints()) {
			if (topicType.equals(atc.getType())) {
				DeleteAssociationConstraintCommand cmd = new DeleteAssociationConstraintCommand(
						atc);
				if (cmd.canExecute()) {
					addAssociationConstraintCommand(cmd);
				}
			} else {
				if ( (atc.getType()!=null) && (atc.getType() instanceof AssociationType) ) {
					AssociationType at = (AssociationType) atc.getType();
				
					for (RoleConstraint rc : at.getRoles()) {
						if (topicType.equals(rc.getType())) {
							RemoveRoleConstraintCommand cmd = new RemoveRoleConstraintCommand(at, rc);
							if (cmd.canExecute())
								addRoleConstraintCommand(cmd);
							// check if theres a roleplayconstraint using this role
							for (RolePlayerConstraint rpc : atc.getPlayerConstraints()) {
								if ( rc.equals(rpc.getRole()) ) {
									SetRoleConstraintCommand cmd2 = new SetRoleConstraintCommand(rpc, null);
									if (cmd2.canExecute())
										addRoleConstraintCommand(cmd2);
								}
							}
						}
					}
				}
				for (RolePlayerConstraint rpc : atc.getPlayerConstraints()) {
					if (topicType.equals(rpc.getPlayer())) {
						DeleteRolePlayerConstraintCommand cmd2 = new DeleteRolePlayerConstraintCommand(atc, rpc);
						if (cmd2.canExecute())
							addDeleteRolePlayerConstraintCommand(cmd2);
					} 
				}
				
			}
		}

	}

	private void addDeleteRolePlayerConstraintCommand(DeleteRolePlayerConstraintCommand cmd) {
		if (deleteRolePlayerCommands == Collections.EMPTY_LIST) {
			deleteRolePlayerCommands = new ArrayList<DeleteRolePlayerConstraintCommand>();
		}
		deleteRolePlayerCommands.add(cmd);
	}

	/**
	 * Searches usage of the topic type in constraints of other topics
	 */
	private void prepareConstraintCommandList() {
		if (ignoreConstraints)
			return;
		for (TopicType tt : ModelIndexer.getInstance().getTopicMapSchema().getTopicTypes()) {
			if (tt.equals(topicType))
				continue;

			for (NameTypeConstraint ntc : tt.getNameConstraints()) {
				if (topicType.equals(ntc.getType())) {
					addConstraintCommand(tt, ntc, ModelPackage.TOPIC_TYPE__NAME_CONSTRAINTS);
				}
			}

			for (OccurrenceTypeConstraint otc : tt.getOccurrenceConstraints()) {
				if (topicType.equals(otc.getType())) {
					addConstraintCommand(tt, otc, ModelPackage.TOPIC_TYPE__OCCURRENCE_CONSTRAINTS);
				}
			}

		}
	}

	private void prepareScopeConstraintCommandList() {
		for (ScopedTopicType stt : ModelIndexer.getTopicIndexer().getScopedTopicTypes()) {
			if (stt.equals(topicType))
				continue;

			for (ScopeConstraint sc : stt.getScope()) {
				if (topicType.equals(sc.getType()))
					addScopeConstraintCommand(new RemoveScopeConstraintsCommand(stt, sc));
			}
		}
	}

	private void addConstraintCommand(TopicType tt, AbstractTypedCardinalityConstraint constraint, int featureID) {
		if (constraintCommands == Collections.EMPTY_LIST) {
			constraintCommands = new ArrayList<DeleteTopicTypeConstraintItemCommand>();
		}
		DeleteTopicTypeConstraintItemCommand cmd = new DeleteTopicTypeConstraintItemCommand(tt, constraint, featureID);
		if (cmd.canExecute()) {
			constraintCommands.add(cmd);
		}
	}

	private void addAssociationConstraintCommand(DeleteAssociationConstraintCommand cmd) {
		if (associationCommands == Collections.EMPTY_LIST) {
			associationCommands = new ArrayList<DeleteAssociationConstraintCommand>();
		}

		associationCommands.add(cmd);
	}

	private void addScopeConstraintCommand(RemoveScopeConstraintsCommand cmd) {
		if (scopeCommands == Collections.EMPTY_LIST) {
			scopeCommands = new ArrayList<RemoveScopeConstraintsCommand>();
		}
		scopeCommands.add(cmd);
	}

	private void addRoleConstraintCommand(RemoveRoleConstraintCommand cmd) {
		if (roleCommands == Collections.EMPTY_LIST) {
			roleCommands = new ArrayList<RemoveRoleConstraintCommand>();
		}
		roleCommands.add(cmd);
	}

	private void addRoleConstraintCommand(SetRoleConstraintCommand cmd) {
		if (roleconstCommands == Collections.EMPTY_LIST) {
			roleconstCommands = new ArrayList<SetRoleConstraintCommand>();
		}
		roleconstCommands.add(cmd);
	}

	private void addToEdgeList(Diagram d, Edge e) {
		if (edgeMap == Collections.EMPTY_MAP) {
			edgeMap = new HashMap<Diagram, List<Edge>>(2);
		}

		List<Edge> edgeList = edgeMap.get(d);
		if (edgeList == null) {
			edgeList = new ArrayList<Edge>();
			edgeMap.put(d, edgeList);
		}
		edgeList.add(e);
	}

	/**
	 * Retrieves all topic nodes and related edges and prepares them for removal
	 */
	private void extractTopicNodes() {
		for (Diagram d : ModelIndexer.getInstance().getDiagrams()) {
			TypeNode node = (TypeNode) ModelIndexer.getNodeIndexer().getNodeFor(topicType, d);
			if (node != null) {

				if (typeNodeList == Collections.EMPTY_LIST)
					typeNodeList = new ArrayList<ContainmentPair<Diagram, TypeNode>>();
				typeNodeList.add(new ContainmentPair<Diagram, TypeNode>(d, node));

				for (Edge e : ModelIndexer.getNodeIndexer().getEdgesUsingTopicType(topicType)) {
					if (e.eContainer().equals(d))
						addToEdgeList(d, e);
				}
			}
		}
	}

	private class ContainmentPair<C, E> {
		private final C container;
		private final E element;

		public ContainmentPair(C container, E element) {
			super();
			this.container = container;
			this.element = element;
		}

		public C getContainer() {
			return container;
		}

		public E getElement() {
			return element;
		}
	}
}
