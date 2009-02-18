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
package de.topicmapslab.tmcledit.model.validation.actions;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.command.CommandStack;

import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.commands.SetIsACommand;

/**
 * @author Hannes Niederhausen
 * 
 */
public class RemoveIsAAction extends ValidationAction {

	private TopicType tt;
	private TopicType isA;

	public RemoveIsAAction(CommandStack cmdStack, TopicType tt, TopicType isA) {
		super(cmdStack);
		this.tt = tt;
		this.isA = isA;
		setText("Remove isa relation");
	}
	
	@Override
	public void run() {
		List<TopicType> type = new ArrayList<TopicType>(tt.getIsa());
		type.remove(isA);
		
		SetIsACommand cmd = new SetIsACommand(type, tt);
		getCommandStack().execute(cmd);
	}

}
