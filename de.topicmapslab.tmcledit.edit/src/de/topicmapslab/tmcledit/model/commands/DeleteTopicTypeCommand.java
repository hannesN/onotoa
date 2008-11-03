package de.topicmapslab.tmcledit.model.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.command.AbstractCommand;

import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.Edge;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.TypeNode;
import de.topicmapslab.tmcledit.model.util.ModelIndexer;

public class DeleteTopicTypeCommand extends AbstractCommand {

	private final TopicType topicType;

	private List<TopicType> isAList = Collections.emptyList();
	private List<TopicType> akoList = Collections.emptyList();
	private List<ContainmentPair<Diagram, TypeNode>> typeNodeList = Collections.emptyList();
	
	private Map<Diagram, List<Edge>> edgeMap = Collections.emptyMap();
		
	
	public DeleteTopicTypeCommand(TopicType topicType) {
		this.topicType = topicType;
	}
	
	@Override
	public void execute() {
		for (ContainmentPair<Diagram, TypeNode> cp : typeNodeList) {
			if (edgeMap.get(cp.getContainer())!=null) {
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
			if (edgeMap.get(cp.getContainer())!=null) {
				cp.container.getEdges().addAll(edgeMap.get(cp.getContainer()));
			}
		
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
			
			isPrepared = true;
		}
		
		return true;
	}



	private void addToEdgeList(Diagram d, Edge e) {
		if (edgeMap==Collections.EMPTY_MAP) {
			edgeMap = new HashMap<Diagram, List<Edge>>(2);
		}
		
		List<Edge> edgeList = edgeMap.get(d);
		if (edgeList==null) {
			edgeList = new ArrayList<Edge>();
			edgeMap.put(d, edgeList);
		}
		edgeList.add(e);		
	}

	/**
	 * Retrieves all topic nodes and related edges and prepares them for
	 * removal
	 */
	private void extractTopicNodes() {
		for(Diagram d : ModelIndexer.getInstance().getDiagrams()) {
			TypeNode node = ModelIndexer.getInstance().getNodeFor(topicType, d);
			if (node!=null) {
				
				if (typeNodeList==Collections.EMPTY_LIST)
					typeNodeList = new ArrayList<ContainmentPair<Diagram,TypeNode>>();
				typeNodeList.add(new ContainmentPair<Diagram, TypeNode>(d, node));
				
				for (Edge e : ModelIndexer.getInstance().getEdgesUsingTopicType(topicType)) {
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
