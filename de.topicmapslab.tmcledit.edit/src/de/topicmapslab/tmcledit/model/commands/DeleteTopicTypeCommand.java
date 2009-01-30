package de.topicmapslab.tmcledit.model.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.command.AbstractCommand;

import de.topicmapslab.tmcledit.model.AbstractConstraint;
import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.Edge;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.NameTypeConstraint;
import de.topicmapslab.tmcledit.model.OccurenceTypeConstraint;
import de.topicmapslab.tmcledit.model.ScopeConstraint;
import de.topicmapslab.tmcledit.model.ScopedTopicType;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.TypeNode;
import de.topicmapslab.tmcledit.model.util.ModelIndexer;

public class DeleteTopicTypeCommand extends AbstractCommand {

	private final TopicType topicType;

	private List<TopicType> isAList = Collections.emptyList();
	private List<TopicType> akoList = Collections.emptyList();;
	private List<ContainmentPair<Diagram, TypeNode>> typeNodeList = Collections
			.emptyList();

	private List<DeleteTopicTypeConstraintItemCommand> constraintCommands = Collections
			.emptyList();

	private Map<Diagram, List<Edge>> edgeMap = Collections.emptyMap();

	private List<DeleteAssociationConstraintCommand> associationCommands = Collections
			.emptyList();

	private List<RemoveScopeConstraintCommand> scopeCommands = Collections
			.emptyList();

	public DeleteTopicTypeCommand(TopicType topicType) {
		this.topicType = topicType;
	}

	@Override
	public void execute() {
		for (ContainmentPair<Diagram, TypeNode> cp : typeNodeList) {
			if (edgeMap.get(cp.getContainer()) != null) {
				cp.container.getEdges().removeAll(
						edgeMap.get(cp.getContainer()));
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
		
		for (RemoveScopeConstraintCommand cmd : scopeCommands) {
			cmd.execute();
		}

		ModelIndexer.getInstance().getTopicMapSchema().getTopicTypes().remove(
				topicType);

	}

	@Override
	public void undo() {
		ModelIndexer.getInstance().getTopicMapSchema().getTopicTypes().add(
				topicType);

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

		for (DeleteAssociationConstraintCommand cmd : associationCommands) {
			cmd.undo();
		}
		
		for (RemoveScopeConstraintCommand cmd : scopeCommands) {
			cmd.undo();
		}

	}

	@Override
	public void redo() {
		execute();
	}

	@Override
	protected boolean prepare() {
		if (!isPrepared) {
			// first call of prepare
			extractTopicNodes();
			isAList = ModelIndexer.getInstance().getInstanceTypes(topicType);
			akoList = ModelIndexer.getInstance().getSubTypes(topicType);

			prepareConstraintCommandList();

			prepareAssociationCommandsList();

			prepareScopeConstraintCommandList();

			isPrepared = true;
		}

		return true;
	}

	private void prepareAssociationCommandsList() {
		TopicMapSchema topicMapSchema = ModelIndexer.getInstance()
				.getTopicMapSchema();
		for (AssociationTypeConstraint asc : topicMapSchema
				.getAssociationTypeConstraints()) {
			if (asc.getType().equals(topicType)) {
				DeleteAssociationConstraintCommand cmd = new DeleteAssociationConstraintCommand(
						asc);
				if (cmd.canExecute()) {
					addAssociationConstraintCommand(cmd);
				}
			}
		}

	}

	/**
	 * Searches usage of the topic type in constraints of other topics
	 */
	private void prepareConstraintCommandList() {
		for (TopicType tt : ModelIndexer.getInstance().getTopicMapSchema()
				.getTopicTypes()) {
			if (tt.equals(topicType))
				continue;

			for (NameTypeConstraint ntc : tt.getNameContraints()) {
				if (ntc.getType().equals(topicType)) {
					addConstraintCommand(tt, ntc,
							ModelPackage.TOPIC_TYPE__NAME_CONTRAINTS);
				}
			}

			for (OccurenceTypeConstraint otc : tt.getOccurenceConstraints()) {
				if (otc.getType().equals(topicType)) {
					addConstraintCommand(tt, otc,
							ModelPackage.TOPIC_TYPE__OCCURENCE_CONSTRAINTS);
				}
			}

		}
	}

	private void prepareScopeConstraintCommandList() {
		for (ScopedTopicType stt : ModelIndexer.getInstance().getScopedTopicTypes()) {
			if (stt.equals(topicType))
				continue;
			
			for (ScopeConstraint sc : stt.getScope()) {
				if (topicType.equals(sc.getType()))
					addScopeContraintCommand(new RemoveScopeConstraintCommand(stt, sc));
			}
		}
	}

	private void addConstraintCommand(TopicType tt,
			AbstractConstraint constraint, int featureID) {
		if (constraintCommands == Collections.EMPTY_LIST) {
			constraintCommands = new ArrayList<DeleteTopicTypeConstraintItemCommand>();
		}
		DeleteTopicTypeConstraintItemCommand cmd = new DeleteTopicTypeConstraintItemCommand(
				tt, constraint, featureID);
		if (cmd.canExecute()) {
			constraintCommands.add(cmd);
		}
	}

	private void addAssociationConstraintCommand(
			DeleteAssociationConstraintCommand cmd) {
		if (associationCommands == Collections.EMPTY_LIST) {
			associationCommands = new ArrayList<DeleteAssociationConstraintCommand>();
		}

		associationCommands.add(cmd);
	}

	private void addScopeContraintCommand(RemoveScopeConstraintCommand cmd) {
		if (scopeCommands == Collections.EMPTY_LIST) {
			scopeCommands = new ArrayList<RemoveScopeConstraintCommand>();
		}
		scopeCommands.add(cmd);
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
			TypeNode node = (TypeNode) ModelIndexer.getInstance().getNodeFor(
					topicType, d);
			if (node != null) {

				if (typeNodeList == Collections.EMPTY_LIST)
					typeNodeList = new ArrayList<ContainmentPair<Diagram, TypeNode>>();
				typeNodeList
						.add(new ContainmentPair<Diagram, TypeNode>(d, node));

				for (Edge e : ModelIndexer.getInstance()
						.getEdgesUsingTopicType(topicType)) {
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
