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

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.common.command.AbstractCommand;

import de.topicmapslab.tmcledit.model.TopicReifiesConstraint;
import de.topicmapslab.tmcledit.model.TopicType;

/**
 * @author Hannes Niederhausen
 *
 */
public class AddTopicReifiesConstraintsCommand extends AbstractCommand {

	private final TopicType topicType;
	private final List<TopicReifiesConstraint> reifies;
	
	
	public AddTopicReifiesConstraintsCommand(TopicType topicType,
			TopicReifiesConstraint... reifiesConstraints) {
		this(topicType, Arrays.asList(reifiesConstraints));
	}
	
	public AddTopicReifiesConstraintsCommand(TopicType topicType,
			List<TopicReifiesConstraint> reifiesConstraints) {
		super("Add Topic Reifies Constraints");
		this.topicType = topicType;
		this.reifies = reifiesConstraints;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.command.Command#execute()
	 */
	public void execute() {
		topicType.getTopicReifiesConstraints().addAll(reifies);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.command.Command#redo()
	 */
	public void redo() {
		execute();
	}

	@Override
	public void undo() {
		topicType.getTopicReifiesConstraints().removeAll(reifies);
	}
	
	@Override
	protected boolean prepare() {
		if (reifies.isEmpty())
			return false;
		return true;
	}
}
