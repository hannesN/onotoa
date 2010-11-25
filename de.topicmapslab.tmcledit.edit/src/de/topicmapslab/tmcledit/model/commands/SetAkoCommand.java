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

import java.util.List;

import de.topicmapslab.tmcledit.model.EdgeType;
import de.topicmapslab.tmcledit.model.TopicType;

/**
 * Sets the ako list of a topic type to a new list
 * 
 * @author Hannes Niederhausen
 * 
 */
public class SetAkoCommand extends AbstractConnectionCommand {

	/**
	 * 
	 * Constructor
	 * 
	 * @param newList the new list containg all super types
	 * @param topic the sub type
	 */
	public SetAkoCommand(List<TopicType> newList, TopicType topic) {
		super("Set a kind of relationship", newList, topic);
	}

	@Override
	protected EdgeType getEdgeType() {
		return EdgeType.AKO_TYPE;
	}
	
	@Override
	protected List<TopicType> getTypeList() {
	    return topic.getAko();
	}
	
	@Override
	protected boolean prepare() {
		if (topic.getAko().equals(newList))
			return false;
		
	    return super.prepare();
	}
}
