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
package de.topicmapslab.onotoa.search.searchImpl;

import de.topicmapslab.onotoa.search.views.Container;
import de.topicmapslab.onotoa.search.wrapper.TopicTypeWrapper;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;

/**
 * @author sip
 * 
 */
public class NonSubjectIdentifierTopicSearcher {

	private TopicMapSchema schema;
	private Container con;

	public NonSubjectIdentifierTopicSearcher(TopicMapSchema schema) {

		this.schema = schema;
		con = new Container();
		createContainer();

	}

	private void createContainer() {

		for (TopicType type : schema.getTopicTypes()) {

			if (type.getIdentifiers().size() == 0)
				con.getList().add(new TopicTypeWrapper(type));

		}

	}

	public Container getContainer() {
		return this.con;
	}

}
