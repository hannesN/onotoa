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

import org.eclipse.emf.common.command.AbstractCommand;

import de.topicmapslab.tmcledit.model.Bendpoints;
import de.topicmapslab.tmcledit.model.Edge;

/**
 * @author Hannes Niederhausen
 *
 */
public class RemoveBendpointCommand extends AbstractCommand {

	private final Edge edge;
	private final int  index;
	
	private Bendpoints bendpoint;

	public RemoveBendpointCommand(Edge edge, int index) {
	    super();
	    this.edge = edge;
	    this.index = index;
    }

	public void execute() {
		bendpoint = edge.getBendpoints().get(index);
	    edge.getBendpoints().remove(index);	    
    }

	public void redo() {
		execute();	    
    }
	
	@Override
	public void undo() {
	    edge.getBendpoints().add(index, bendpoint);
	}
	
	
	@Override
	protected boolean prepare() {
		return true;
	}
	
}
