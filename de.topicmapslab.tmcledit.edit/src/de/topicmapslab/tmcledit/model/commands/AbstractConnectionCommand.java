package de.topicmapslab.tmcledit.model.commands;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.command.AbstractCommand;

import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.Edge;
import de.topicmapslab.tmcledit.model.EdgeType;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.Node;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.TypeNode;

public abstract class AbstractConnectionCommand extends AbstractCommand {

	private List<TopicType> removeList;
	private List<TopicType> addList;
	private List<EdgeWrapper> removeEdgeList;



	private List<EdgeWrapper> addEdgeList;
	protected List<TopicType> newList;
	protected final TopicType topic;


	public AbstractConnectionCommand(String label, List<TopicType> newList,
			TopicType topic) {
		super(label);
		this.newList = newList;
		this.topic = topic;
	}

	protected abstract EdgeType getEdgeType();
	
	@Override
	public void execute() {
		topic.getIsa().removeAll(removeList);
		topic.getIsa().addAll(addList);
		for (EdgeWrapper ew : addEdgeList) {
			ew.diagram.getEdges().add(ew.edge);
		}
		for (EdgeWrapper ew : removeEdgeList) {
			ew.diagram.getEdges().remove(ew.edge);
		}
		
	}

	@Override
	public void redo() {
		execute();
	}

	@Override
	public void undo() {
		topic.getIsa().addAll(removeList);
		topic.getIsa().removeAll(addList);
		for (EdgeWrapper ew : addEdgeList) {
			ew.diagram.getEdges().remove(ew.edge);
		}
		for (EdgeWrapper ew : removeEdgeList) {
			ew.diagram.getEdges().add(ew.edge);
		}
	}

	@Override
	protected boolean prepare() {
		createTypeLists();
		
		createEdgeLists();
		
		
		// sort of clean up ;)
		newList = null;
		return true;
	}

	private void createEdgeLists() {
		addEdgeList = new ArrayList<EdgeWrapper>();
		removeEdgeList = new ArrayList<EdgeWrapper>();
		
		// now the more costly part.. finding edges which represent the current state
		TopicMapSchema schema = (TopicMapSchema) topic.eContainer();
		File file = (File) schema.eContainer();
		
		for (Diagram d : file.getDiagrams()) {
			// check if we have a topicnode containing representing the topic
			TypeNode currentNode = null;
			for (Node node : d.getNodes()) {
				if (node instanceof TypeNode) {
					if (((TypeNode) node).getTopicType().equals(topic)) {
						findRemoveEdges(d);
						currentNode = (TypeNode) node;
						break; 
					}
				}
			}
			// now we have the type node and all edges we will remove
			// its time to create the new edges for the new types
			for (Node node : d.getNodes()) {
				if (node instanceof TypeNode) {
					TypeNode tmpNode = (TypeNode) node;
					if (addList.contains(tmpNode.getTopicType()) ) {
						Edge e = ModelFactory.eINSTANCE.createEdge();
						e.setType(getEdgeType());
						e.setSource(currentNode);
						e.setTarget(tmpNode);
						addEdgeList.add(new EdgeWrapper(d, e));
					}
				}
			}
			
		}
	}

	private void findRemoveEdges(Diagram d) {
		for (Edge edge : d.getEdges()) {
			if (edge.getType()==getEdgeType()) {
				TypeNode target = (TypeNode) edge.getTarget();
				if (removeList.contains(target.getTopicType()) )
					removeEdgeList.add(new EdgeWrapper(d, edge));
			}
		}
	}

	private void createTypeLists() {
		addList = new ArrayList<TopicType>();
		for (TopicType tt : newList) {
			if (!topic.getIsa().contains(tt)) {
				addList.add(tt);
			}
		}
		removeList = new ArrayList<TopicType>();
		for (TopicType tt : topic.getIsa()) {
			if (!newList.contains(tt)) {
				removeList.add(tt);
			}
		}
	}

	public static class EdgeWrapper {
		public final Diagram diagram;
		public final Edge edge;
		
		public EdgeWrapper(Diagram diagram, Edge edge) {
			super();
			this.diagram = diagram;
			this.edge = edge;
		}
		
	}
}