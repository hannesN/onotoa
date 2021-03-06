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
import de.topicmapslab.tmcledit.model.Node;



public class RemoveNodeCommand extends AbstractCommand {

	private final Node node;
	private final Diagram diagram;

	private List<Edge> edgeList = Collections.emptyList();
	private List<Edge> oldList = Collections.emptyList();
	
	private int nodeIndex;

	public RemoveNodeCommand(Diagram diagram, Node node) {
		super();
		this.diagram = diagram;
		this.node = node;
		this.nodeIndex = diagram.getNodes().indexOf(node);
	}
	
	public void execute() {
		prepare();
		diagram.getEdges().removeAll(edgeList);
		diagram.getNodes().remove(node);
	}
	
	public void redo() {
		diagram.getEdges().removeAll(edgeList);
		diagram.getNodes().remove(node);
	}
	
	@Override
	public void undo() {
		diagram.getNodes().add(nodeIndex, node);
		diagram.getEdges().clear();
		diagram.getEdges().addAll(oldList);
	}

	@Override
	protected boolean prepare() {
		for (Edge e : diagram.getEdges()) {
			if ( (e.getSource().equals(node)) || (e.getTarget().equals(node)) ) {
				if (edgeList==Collections.EMPTY_LIST)
					edgeList = new ArrayList<Edge>();
				edgeList.add(e);
			}
		}
		oldList = new ArrayList<Edge>(diagram.getEdges());
		return true;
	}
	
}

