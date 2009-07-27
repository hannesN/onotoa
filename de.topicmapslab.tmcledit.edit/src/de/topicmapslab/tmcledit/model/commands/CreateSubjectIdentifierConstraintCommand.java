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
import de.topicmapslab.tmcledit.model.SubjectIdentifierConstraint;
import de.topicmapslab.tmcledit.model.TopicType;

public class CreateSubjectIdentifierConstraintCommand extends AbstractCommand {


	private final TopicType topicType;
	private final SubjectIdentifierConstraint subjectIdentifierConstraint;
	
	
	public CreateSubjectIdentifierConstraintCommand(TopicType topicType) {
		this(topicType, ModelFactory.eINSTANCE.createSubjectIdentifierConstraint());
	}
	
	public CreateSubjectIdentifierConstraintCommand(TopicType topicType,
			SubjectIdentifierConstraint subjectIdentifierConstraint) {
		super("Create Subject Idnetifier Constraint");
		this.topicType = topicType;
		this.subjectIdentifierConstraint = subjectIdentifierConstraint;
	}
	
	public void execute() {
		topicType.getSubjectIdentifierConstraints().add(subjectIdentifierConstraint);
	}

	public void redo() {
		execute();
	}
	
	@Override
	public void undo() {
		topicType.getSubjectIdentifierConstraints().remove(subjectIdentifierConstraint);
	}
	
	@Override
	protected boolean prepare() {
		return true;
	}

	

}
