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

import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.TopicReifiesConstraint;
import de.topicmapslab.tmcledit.model.TopicType;

/**
 * @author Hannes Niederhausen
 *
 */
public class SetCannotReifyConstraint extends AbstractCommand {

	private final TopicType topicType;
	
	private List<TopicReifiesConstraint> oldList;
	private TopicReifiesConstraint cannotConstraint;
	
	public SetCannotReifyConstraint(TopicType tt) {
		this.topicType = tt;
    }
	
	public void execute() {
		topicType.eSetDeliver(false);
		topicType.getTopicReifiesConstraints().clear();
		topicType.eSetDeliver(true);
		topicType.getTopicReifiesConstraints().add(cannotConstraint);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.command.Command#redo()
	 */
	public void redo() {
		execute();
	}
	
	@Override
	public void undo() {
		topicType.eSetDeliver(oldList.size()==0);
		topicType.getTopicReifiesConstraints().clear();
		topicType.eSetDeliver(true);
		topicType.getTopicReifiesConstraints().addAll(oldList);
	}

	@Override
	protected boolean prepare() {
	    oldList=new ArrayList<TopicReifiesConstraint>(topicType.getTopicReifiesConstraints());
	    // already the cannot state
		if ((oldList.size() == 1) && (oldList.get(0).getType() == null))
			return false;
		
		cannotConstraint = ModelFactory.eINSTANCE.createTopicReifiesConstraint();
		cannotConstraint.setCardMin("0");
		cannotConstraint.setCardMax("0");
		
	    return true;
	}
}
