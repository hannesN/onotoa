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

import de.topicmapslab.tmcledit.model.SubjectLocatorConstraint;
import de.topicmapslab.tmcledit.model.TopicType;

public class CreateSubjectLocatorConstraintCommand extends AbstractCommand {

	
	private final TopicType topicType;
	private final SubjectLocatorConstraint subjectLocatorConstraint;
	

	public CreateSubjectLocatorConstraintCommand(TopicType topicType,
			SubjectLocatorConstraint subjectLocatorConstraint) {
		super("Create Subject Locator");
		this.topicType = topicType;
		this.subjectLocatorConstraint = subjectLocatorConstraint;
	}
	
	@Override
	public void execute() {
		topicType.getSubjectLocatorConstraint().add(subjectLocatorConstraint);
	}

	@Override
	public void redo() {
		execute();
	}
	
	@Override
	public void undo() {
		topicType.getSubjectLocatorConstraint().remove(subjectLocatorConstraint);
	}
	
	@Override
	protected boolean prepare() {
		return true;
	}

}
