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

import de.topicmapslab.tmcledit.model.Annotation;
import de.topicmapslab.tmcledit.model.TMCLConstruct;

/**
 * @author Hannes Niederhausen
 * 
 */
public class RemoveAnnotationCommand extends AbstractCommand {

	private final TMCLConstruct construct;
	private final int index;
	private final Annotation annotation;

	public RemoveAnnotationCommand(TMCLConstruct construct, Annotation annotation) {
		super();
		this.construct = construct;
		this.annotation = annotation;
		index = this.construct.getAnnotations().indexOf(annotation);
	}

	public void execute() {
		construct.getAnnotations().remove(annotation);
	}

	@Override
	public void undo() {
		construct.getAnnotations().add(index, annotation);
	}

	public void redo() {
		execute();
	}

	@Override
	protected boolean prepare() {
		if (construct.getAnnotations().contains(annotation))
			return true;

		return false;
	}

}
