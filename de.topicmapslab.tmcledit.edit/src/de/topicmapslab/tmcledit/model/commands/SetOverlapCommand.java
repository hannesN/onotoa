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
public class SetOverlapCommand extends AbstractCommand {

	private TopicType topicType;

	private List<TopicType> newList;
	private List<TopicType> oldList;
	
	public SetOverlapCommand(List<TopicType> newList, TopicType topic) {
		super("Set overlap types");
		this.topicType = topic;
		this.newList = newList;
	}

	public void execute() {
		topicType.eSetDeliver(true);
		topicType.getOverlap().clear();
		topicType.eSetDeliver(true);
		topicType.getOverlap().addAll(newList);
	}

	@Override
	protected boolean prepare() {
		
		boolean sameObjects = true;
		
		// check if it is the same list of objects (ignoring order)
		if (newList.size()==topicType.getOverlap().size()) {
			for (TopicType tt : newList) {
				if (!topicType.getOverlap().contains(tt)) {
					sameObjects = true;
					break;
				}
			}
			if (sameObjects)
				return false;
		}
		oldList = new ArrayList<TopicType>(topicType.getOverlap());

		return true;
	}

	@Override
	public void undo() {
		topicType.eSetDeliver(false);
		topicType.getOverlap().clear();
		topicType.eSetDeliver(true);
		topicType.getOverlap().addAll(oldList);
	}

	public void redo() {
		execute();
	}
}
