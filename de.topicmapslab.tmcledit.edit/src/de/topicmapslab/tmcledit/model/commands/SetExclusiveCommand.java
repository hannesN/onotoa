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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.command.AbstractCommand;

import de.topicmapslab.tmcledit.model.TopicType;

/**
 * @author Hannes Niederhausen
 * 
 */
public class SetExclusiveCommand extends AbstractCommand {

	private TopicType topicType;

	private List<TopicType> removeList;
	private List<TopicType> addList;

	public SetExclusiveCommand(List<TopicType> newList, TopicType topic) {
		super("Set exclusive types");
		this.topicType = topic;
		this.removeList = new ArrayList<TopicType>();
		this.addList = new ArrayList<TopicType>(newList);
	}

	public void execute() {

		for (TopicType tt : removeList) {
			tt.getExclusive().remove(topicType);
		}
		for (TopicType tt : addList) {
			tt.getExclusive().add(topicType);
		}

		topicType.eSetDeliver(false);
		topicType.getExclusive().removeAll(removeList);
		topicType.eSetDeliver(true);
		topicType.getExclusive().addAll(addList);
	}

	@Override
	protected boolean prepare() {
		removeList = new ArrayList<TopicType>();
		for (TopicType tt : topicType.getExclusive()) {
			if (addList.contains(tt)) {
				addList.remove(tt);
			} else {
				removeList.add(tt);
			}
		}

		return true;
	}

	@Override
	public void undo() {
		for (TopicType tt : removeList) {
			tt.getExclusive().add(topicType);
		}
		for (TopicType tt : addList) {
			tt.getExclusive().remove(topicType);
		}

		topicType.eSetDeliver(false);
		topicType.getExclusive().addAll(removeList);
		topicType.eSetDeliver(true);
		topicType.getExclusive().removeAll(addList);
	}

	public void redo() {
		execute();
	}
}
