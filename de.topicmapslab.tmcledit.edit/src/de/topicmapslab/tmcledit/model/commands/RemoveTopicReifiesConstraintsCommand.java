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

import de.topicmapslab.tmcledit.model.TopicReifiesConstraint;
import de.topicmapslab.tmcledit.model.TopicType;

/**
 * @author Hannes Niederhausen
 *
 */
public class RemoveTopicReifiesConstraintsCommand extends AbstractCommand {

	private final TopicType topicType;
	private final List<TopicReifiesConstraint> reifies;
	private final List<TopicReifiesConstraint> oldReifies;
	
	public RemoveTopicReifiesConstraintsCommand(TopicType topicType,
			TopicReifiesConstraint refiesConstraint) {
		this(topicType, new ArrayList<TopicReifiesConstraint>());
		this.reifies.add(refiesConstraint);
	}
	
	public RemoveTopicReifiesConstraintsCommand(TopicType topicType,
			List<TopicReifiesConstraint> refiesConstraints) {
		super("Remove Topic Reifies Constraint");
		this.topicType = topicType;
		this.reifies = refiesConstraints;
		this.oldReifies = new ArrayList<TopicReifiesConstraint>(this.topicType.getTopicReifiesConstraints());
	}

	public void execute() {
		topicType.getTopicReifiesConstraints().removeAll(reifies);
	}

	public void redo() {
		execute();
	}
	
	@Override
	public void undo() {
		topicType.getTopicReifiesConstraints().clear();
		topicType.getTopicReifiesConstraints().addAll(oldReifies);
	}

	@Override
	protected boolean prepare() {
		if (reifies.isEmpty())
			return false;
		return true;
	}
}
