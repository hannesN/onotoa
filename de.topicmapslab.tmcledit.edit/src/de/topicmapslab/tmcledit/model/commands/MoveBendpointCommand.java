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
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.NotificationImpl;

import de.topicmapslab.tmcledit.model.Bendpoints;
import de.topicmapslab.tmcledit.model.Edge;

/**
 * @author Hannes Niederhausen
 *
 */
public class MoveBendpointCommand extends AbstractCommand {

	private final Edge edge;
	private final int  index;
	private final int  posX;
	private final int  posY;
	
	private Bendpoints bendpoint;
	private int  oldX;
	private int  oldY;
	
	public MoveBendpointCommand(Edge edge, int index, int posX, int posY) {
	    super();
	    this.edge = edge;
	    this.index = index;
	    this.posX = posX;
	    this.posY = posY;
    }

	public void execute() {
		bendpoint.setPosX(posX);
		bendpoint.setPosY(posY);
		
		
		NotificationImpl notification = new NotificationImpl(Notification.SET, 0, 0);
		
		edge.eNotify(notification);
    }

	public void redo() {
		execute();	    
    }
	
	@Override
	public void undo() {
		bendpoint.setPosY(oldX);
		bendpoint.setPosY(oldY);
	}
	
	
	@Override
	protected boolean prepare() {
		bendpoint = edge.getBendpoints().get(index);
		oldX = bendpoint.getPosX();
		oldY = bendpoint.getPosY();
	    
		return true;
	}
	
}
