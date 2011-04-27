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
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.TMCLConstruct;

/**
 * @author Hannes Niederhausen
 * 
 */
public class CreateAnnotationCommand extends AbstractCommand {

	private final TMCLConstruct construct;

	private final Annotation annotation;
	private final String key;
	private final String value;

	/**
	 * Constructor
	 * @param construct the annotated construct
	 * @param key the key of the annotation
	 * @param value the value of the annotation
	 */
	public CreateAnnotationCommand(TMCLConstruct construct, String key, String value) {
		super();
		this.construct = construct;
		annotation = ModelFactory.eINSTANCE.createAnnotation();
		this.key = key;
		this.value = value;
	}

	public void execute() {
		construct.getAnnotations().add(annotation);
	}

	@Override
	public void undo() {
		construct.getAnnotations().remove(annotation);
	}

	public void redo() {
		execute();
	}

	@Override
	protected boolean prepare() {
		if ((key==null) || (key.length()==0))
			return false;
		
		annotation.setKey(key);
		annotation.setValue(value);
		
		return true;
	}

}
