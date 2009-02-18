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

import de.topicmapslab.tmcledit.model.OccurenceTypeConstraint;
import de.topicmapslab.tmcledit.model.TopicType;

/**
 * 
 * @author Hannes Niederhausen
 *
 */
public class CreateOccurenceConstraintCommand extends AbstractCommand {

	private final TopicType topicType;
	
	private OccurenceTypeConstraint otc;
	
	
	public CreateOccurenceConstraintCommand(TopicType topicType, OccurenceTypeConstraint otc) {
		this.topicType = topicType;
		this.otc = otc;
	}
	
	@Override
	public void execute() {
		redo();
	}

	@Override
	protected boolean prepare() {
		otc.setCardMin("0");
		otc.setCardMin("1");
		otc.setUnique(false);
		
		return true;
	}
	
	@Override
	public void undo() {
		topicType.getOccurenceConstraints().remove(otc);		
	}
	
	@Override
	public void redo() {
		topicType.getOccurenceConstraints().add(otc);
	}
	
	@Override
	public String getLabel() {
		return "Create Occurence Constraint";
	}
}
