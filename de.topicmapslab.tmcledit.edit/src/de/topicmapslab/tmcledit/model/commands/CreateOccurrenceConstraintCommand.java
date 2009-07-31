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

import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.OccurrenceTypeConstraint;
import de.topicmapslab.tmcledit.model.TopicType;

/**
 * 
 * @author Hannes Niederhausen
 *
 */
public class CreateOccurrenceConstraintCommand extends AbstractCommand {

	private final TopicType topicType;
	
	private OccurrenceTypeConstraint otc;
	
	public CreateOccurrenceConstraintCommand(TopicType topicType) {
		this(topicType, ModelFactory.eINSTANCE.createOccurrenceTypeConstraint());
	}
	
	public CreateOccurrenceConstraintCommand(TopicType topicType, OccurrenceTypeConstraint otc) {
		this.topicType = topicType;
		this.otc = otc;
	}
	
	public void execute() {
		redo();
	}

	@Override
	protected boolean prepare() {
		otc.setUnique(false);
		
		return true;
	}
	
	@Override
	public void undo() {
		topicType.getOccurrenceConstraints().remove(otc);		
	}
	
	public void redo() {
		topicType.getOccurrenceConstraints().add(otc);
	}
	
	@Override
	public String getLabel() {
		return "Create Occurrence Constraint";
	}
}
