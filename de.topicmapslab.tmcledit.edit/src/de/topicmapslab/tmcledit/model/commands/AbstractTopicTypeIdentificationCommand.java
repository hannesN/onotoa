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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.command.AbstractCommand;

import de.topicmapslab.tmcledit.model.TopicType;

public abstract class AbstractTopicTypeIdentificationCommand extends
		AbstractCommand {

	protected List<String> newList;
	protected List<String> oldList;
	protected TopicType type;


	public AbstractTopicTypeIdentificationCommand(String label, List<String> newList, TopicType type) {
		super(label);
		this.newList = newList;
		this.type = type;
	}

	protected abstract List<String> getStringList();

	protected final void setList(List<String> list) {
		if (list.size()>0)
			type.eSetDeliver(false);
		getStringList().clear();
		type.eSetDeliver(true);
		getStringList().addAll(list);
	}
	
	@Override
	protected boolean prepare() {
		oldList = new ArrayList<String>();
		oldList.addAll(getStringList());
		return true;
	}
	
	public void execute() {
		setList(newList);
	}

	public void redo() {
		setList(newList);
	}

	@Override
	public void undo() {
		setList(oldList);
	}

}