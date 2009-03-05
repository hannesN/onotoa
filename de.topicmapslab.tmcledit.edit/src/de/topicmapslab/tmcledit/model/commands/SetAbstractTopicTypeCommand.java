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
/**
 * 
 */
package de.topicmapslab.tmcledit.model.commands;


import org.eclipse.emf.common.command.AbstractCommand;

import de.topicmapslab.tmcledit.model.TopicType;

/**
 * @author Hannes Niederhausen
 *
 */
public class SetAbstractTopicTypeCommand extends AbstractCommand {

	private final TopicType tt;
	private final boolean isAbstract;
	
	
	public SetAbstractTopicTypeCommand(TopicType topicType, boolean isAbstract) {
		super();
		this.isAbstract = isAbstract;
		tt = topicType;
	}

	public void execute() {
		tt.setAbstract(isAbstract);
	}
	
	@Override
	public void undo() {
		tt.setAbstract(!isAbstract);
	}

	public void redo() {
		execute();
	}
	
	@Override
	public String getLabel() {
		return "Update Set Abstract";
	}
	
	@Override
	protected boolean prepare() {
		return true;
	}
}
