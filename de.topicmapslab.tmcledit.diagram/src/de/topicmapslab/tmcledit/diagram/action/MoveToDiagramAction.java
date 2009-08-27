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

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.IStructuredSelection;

import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.Node;
import de.topicmapslab.tmcledit.model.commands.RemoveNodeCommand;

/**
 * @author Hannes Niederhausen
 *
 */
public class MoveToDiagramAction extends Action {

	private final Diagram diagram;
	private final EditPartViewer viewer;


	public MoveToDiagramAction(Diagram diagram, EditPartViewer viewer) {
		this.diagram = diagram;
		this.viewer = viewer;
	}


	@Override
	public void run() {
		CompoundCommand cmd = new CompoundCommand();
		RemoveNodeCommand
		
	}
	
	@SuppressWarnings("unchecked")
	private List<Node> collectMoveableNodes() {
		Iterator it = viewer.getSelectedEditParts().iterator();
		
		ArrayList<Node> nodes = new ArrayList<Node>();
		while (it.hasNext()) {
			EditPart ep = (EditPart) it.next();
			if (ep.getModel() instanceof Node) {
				
			}
		}
		
		
	}

	@Override
	public String getText() {
		return diagram.getName();
	}
	
	
	public void update() {
		
	}

}
