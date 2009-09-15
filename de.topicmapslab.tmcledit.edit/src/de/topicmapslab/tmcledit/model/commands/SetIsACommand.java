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
 * @author Hannes Niederhausen
 * 
 */
public class SetIsACommand extends AbstractConnectionCommand {

	public SetIsACommand(List<TopicType> newList, TopicType topic) {
		super("Set is a relationship", newList, topic);
	}

	@Override
	protected EdgeType getEdgeType() {
		return EdgeType.IS_ATYPE;
	}
	
	@Override
	protected boolean prepare() {
		if (newList.equals(topic.getIsa()))
			return false;
	    return super.prepare();
	}

}
