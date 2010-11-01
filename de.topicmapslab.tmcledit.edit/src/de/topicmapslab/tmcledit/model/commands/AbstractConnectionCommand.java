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
import java.util.Map.Entry;

import org.eclipse.emf.common.command.AbstractCommand;

import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.DomainDiagram;
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

	private List<TopicType> oldList = Collections.emptyList();
	protected List<TopicType> newList;
	
	private Map<Diagram, List<Edge>> newEdgesMaps;
	private Map<Diagram, List<Edge>> oldEdgesMaps;
	
	protected final TopicType topic;


	public AbstractConnectionCommand(String label, List<TopicType> newList,
			TopicType topic) {
		super(label);
		this.newList = newList;
		this.topic = topic;
	}

	protected abstract EdgeType getEdgeType();
	
	protected abstract List<TopicType> getTypeList();
	
	public void execute() {
		topic.eSetDeliver(newList.size()==0);
		getTypeList().clear();
		topic.eSetDeliver(true);
		getTypeList().addAll(newList);
			
		for (Entry<Diagram, List<Edge>> e : getNewEdgesMaps().entrySet()) {
			Diagram d = e.getKey();
			List<Edge> edges = e.getValue();
			
			d.eSetDeliver(edges.size()>0);
			d.getEdges().clear();
			d.eSetDeliver(true);
			d.getEdges().addAll(edges);
		}
		
	}
	
	public void redo() {
		execute();
	}

	@Override
	public void undo() {
		topic.eSetDeliver(oldList.size()!=0);
		getTypeList().clear();
		topic.eSetDeliver(true);
		getTypeList().addAll(oldList);
		
		for (Entry<Diagram, List<Edge>> e : getOldEdgesMaps().entrySet()) {
			Diagram d = e.getKey();
			List<Edge> edges = e.getValue();
			
			d.eSetDeliver(edges.size()>0);
			d.getEdges().clear();
			d.eSetDeliver(true);
			d.getEdges().addAll(edges);
		}
	}

	@Override
	protected boolean prepare() {
		if (isPrepared)
			return false;
		
		createTypeLists();
		
		createEdgeLists();
		
		
		return true;
	}
	
	public Map<Diagram, List<Edge>> getNewEdgesMaps() {
		if (newEdgesMaps==null)
			return Collections.emptyMap();
		return newEdgesMaps;
    }
	
	public Map<Diagram, List<Edge>> getOldEdgesMaps() {
		if (oldEdgesMaps==null)
			return Collections.emptyMap();
	    return oldEdgesMaps;
    }

	private void createEdgeLists() {
		// now the more costly part.. finding edges which represent the current state
		TopicMapSchema schema = (TopicMapSchema) topic.eContainer();
		File file = (File) schema.eContainer();
		
		for (Diagram d : file.getDiagrams()) {
			if ( (d instanceof DomainDiagram) && (getEdgeType()==EdgeType.IS_ATYPE) )
				continue;
			// check if we have a topicnode representing the topic
			TypeNode currentNode = (TypeNode) ModelIndexer.getNodeIndexer().getNodeFor(topic, d);
			if (currentNode!=null) {
				
				List<Edge> newEdges = new ArrayList<Edge>(d.getEdges());
				
				// remove the edges which aren't needed anymore
				findRemoveEdges(d, newEdges);
			
			
				// now we have the type node and all edges we will remove
				// its time to create the new edges for the new types
				for (Node node : d.getNodes()) {
					if (node instanceof TypeNode) {
						TypeNode tmpNode = (TypeNode) node;
						if ( (newList.contains(tmpNode.getTopicType())) 
								&& (!oldList.contains(tmpNode.getTopicType())) ) {
							
							Edge e = ModelFactory.eINSTANCE.createEdge();
							e.setType(getEdgeType());
							e.setSource(currentNode);
							e.setTarget(tmpNode);
							newEdges.add(e);
						}
					}
				}
				putNewEdgesEntry(d, newEdges);
			}
			
		}
	}

	private void findRemoveEdges(Diagram d, List<Edge> edges) {
		for (Edge edge : ModelIndexer.getNodeIndexer().getEdges(d, getEdgeType())) {
			if (((TypeNode)edge.getSource()).getTopicType().equals(topic)) {
				TypeNode target = (TypeNode) edge.getTarget();
				if (oldList.contains(target.getTopicType()) && (!newList.contains(target.getTopicType())) )
					edges.remove(edge);
			}
		}
	}

	private void createTypeLists() {
		List<TopicType> tmpList = (getEdgeType()==EdgeType.AKO_TYPE) ? topic.getAko() : topic.getIsa();
		oldList = new ArrayList<TopicType>(tmpList);
	}
	
	/**
	 * Puts an entry to the newEntriesMap and oldEntriesMap
	 * @param d
	 * @param list
	 */
	private void putNewEdgesEntry(Diagram d, List<Edge> list) {
		if (newEdgesMaps==null) {
			newEdgesMaps = new HashMap<Diagram, List<Edge>>();
			oldEdgesMaps = new HashMap<Diagram, List<Edge>>();
		}
		
		newEdgesMaps.put(d, list);
		oldEdgesMaps.put(d, new ArrayList<Edge>(d.getEdges()));
	}
}