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
import de.topicmapslab.tmcledit.model.index.ModelIndexer;

public abstract class AbstractConnectionCommand extends AbstractCommand {

	private List<TopicType> removeList = Collections.emptyList();
	private List<TopicType> addList = Collections.emptyList();
	private List<EdgeWrapper> removeEdgeList = Collections.emptyList();



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
	
	public void execute() {
		if (getEdgeType()==EdgeType.AKO_TYPE) {
			topic.getAko().removeAll(removeList);
			topic.getAko().addAll(addList);
		} else if (getEdgeType()==EdgeType.IS_ATYPE) {
			topic.getIsa().removeAll(removeList);
			topic.getIsa().addAll(addList);
		}
		for (EdgeWrapper ew : addEdgeList) {
			ew.diagram.getEdges().add(ew.edge);
		}
		for (EdgeWrapper ew : removeEdgeList) {
			ew.diagram.getEdges().remove(ew.edge);
		}
		
	}

	public void redo() {
		execute();
	}

	@Override
	public void undo() {
		if (getEdgeType()==EdgeType.AKO_TYPE) {
			topic.getAko().addAll(removeList);
			topic.getAko().removeAll(addList);
		} else if (getEdgeType()==EdgeType.IS_ATYPE) {
			topic.getIsa().addAll(removeList);
			topic.getIsa().removeAll(addList);
		}
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
			TypeNode currentNode = (TypeNode) ModelIndexer.getNodeIndexer().getNodeFor(topic, d);
			if (currentNode!=null) {
				findRemoveEdges(d);
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
		for (Edge edge : ModelIndexer.getNodeIndexer().getEdges(d, getEdgeType())) {
			if (((TypeNode)edge.getSource()).getTopicType().equals(topic)) {
				TypeNode target = (TypeNode) edge.getTarget();
				if (removeList.contains(target.getTopicType()) )
					removeEdgeList.add(new EdgeWrapper(d, edge));
			}
		}
	}

	private void createTypeLists() {
		List<TopicType> tmpList = (getEdgeType()==EdgeType.AKO_TYPE) ? topic.getAko() : topic.getIsa();
		addList = new ArrayList<TopicType>();
		for (TopicType tt : newList) {
			if (!tmpList.contains(tt)) {
				addList.add(tt);
			}
		}
		removeList = new ArrayList<TopicType>();
		
		for (TopicType tt : tmpList) {
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