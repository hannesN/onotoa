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
package de.topicmapslab.tmcledit.diagram.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.jface.action.Action;

import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.Node;
import de.topicmapslab.tmcledit.model.commands.AbstractNodeListCommand;
import de.topicmapslab.tmcledit.model.commands.CopyNodesCommand;

/**
 * @author Hannes Niederhausen
 *
 */
public class CopyToDiagramAction extends Action {

	private final Diagram diagram;
	private final EditPartViewer viewer;
	private final CommandStack commandStack;

	public CopyToDiagramAction(CommandStack commandStack, Diagram diagram, EditPartViewer viewer) {
		this.diagram = diagram;
		this.viewer = viewer;
		this.commandStack = commandStack;
	}


	@Override
	public void run() {
		List<Node> nodeList = collectMoveableNodes();
		AbstractNodeListCommand cmd = new CopyNodesCommand(nodeList, diagram);
		if (cmd.canExecute())
			commandStack.execute(cmd);
	}		
	
	@SuppressWarnings("unchecked")
	private List<Node> collectMoveableNodes() {
		Iterator it = viewer.getSelectedEditParts().iterator();
		
		ArrayList<Node> nodes = new ArrayList<Node>();
		while (it.hasNext()) {
			EditPart ep = (EditPart) it.next();
			if (ep.getModel() instanceof Node) {
				nodes.add((Node) ep.getModel());
			}
		}
		return nodes;
		
	}

	@Override
	public String getText() {
		return diagram.getName();
	}
	
	
	public void update() {
		
	}

}
