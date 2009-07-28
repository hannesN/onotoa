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
import de.topicmapslab.tmcledit.model.ModelFactory;

/**
 * @author Hannes Niederhausen
 *
 */
public class AddBendpointCommand extends AbstractCommand {

	private final Edge edge;
	private final int  index;
	private final int  posX;
	private final int  posY;
	
	private Bendpoints bendpoint;

	public AddBendpointCommand(Edge edge, int index, int posX, int posY) {
	    super();
	    this.edge = edge;
	    this.index = index;
	    this.posX = posX;
	    this.posY = posY;
    }

	public void execute() {
	    edge.getBendpoints().add(index, bendpoint);	    
    }

	public void redo() {
		execute();	    
    }
	
	@Override
	public void undo() {
	    edge.getBendpoints().remove(index);
	}
	
	
	@Override
	protected boolean prepare() {
		bendpoint = ModelFactory.eINSTANCE.createBendpoints();
		bendpoint.setPosX(posX);
		bendpoint.setPosY(posY);
	    
		return true;
	}
	
}
