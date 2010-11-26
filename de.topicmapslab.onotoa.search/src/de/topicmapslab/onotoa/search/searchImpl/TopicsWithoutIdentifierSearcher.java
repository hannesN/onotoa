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

import java.util.Collections;
import java.util.List;

import de.topicmapslab.onotoa.search.container.TopicsWithoutIdentifierContainer;
import de.topicmapslab.onotoa.search.wrapper.TopicTypeWrapper;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;

/**
 * Class that implements the search for unused Topic Types
 * 
 * @author Sebastian Lippert
 */

public class TopicsWithoutIdentifierSearcher implements ISearcher {

	private final TopicMapSchema schema;
	private TopicsWithoutIdentifierContainer con;

	/**
	 * Constructor
	 * 
	 * @param schema
	 *            Schema that should used
	 */

	public TopicsWithoutIdentifierSearcher(TopicMapSchema schema) {

		this.schema = schema;
		con = new TopicsWithoutIdentifierContainer("Topics without Identifier", this);

	}

	/**
	 * {@inheritDoc}
	 */

	public void fetchResult() {

		List<TopicType> topicList = schema.getTopicTypes();

		// iterate over all TopicTypes of the schema
		for (TopicType tt : topicList) {
			/*
			 * check if topic has no SubjectIdentifier AND no SubjectLocator AND
			 * no ItemIdentifier
			 */
			if (tt.getIdentifiers().size() == 0 && tt.getLocators().size() == 0
			        && tt.getItemIdentifierConstraints().size() == 0)
				con.addListElement(new TopicTypeWrapper(tt));
		}
	}

	/**
	 * {@inheritDoc}
	 */

	public TopicsWithoutIdentifierContainer getResult() {
		Collections.sort((List<? extends Comparable>) con.getContentList());
		return con;
	}

	/**
	 * {@inheritDoc}
	 */

	public void refresh() {

		con.removeAllElements();
		fetchResult();

	}

}
