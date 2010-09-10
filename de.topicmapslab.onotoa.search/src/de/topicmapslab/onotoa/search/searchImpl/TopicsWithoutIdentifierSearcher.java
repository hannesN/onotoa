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

import java.util.ArrayList;
import java.util.List;

import de.topicmapslab.onotoa.search.views.Container;
import de.topicmapslab.onotoa.search.wrapper.TopicTypeWrapper;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;

/**
 * @author sip
 * 
 */
public class TopicsWithoutIdentifierSearcher implements ISearchImpl {

	private final TopicMapSchema schema;
	private Container con;

	public TopicsWithoutIdentifierSearcher(TopicMapSchema schema) {

		this.schema = schema;
		con = new Container();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.topicmapslab.onotoa.search.searchImpl.ISearchImpl#fetchResult()
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
				con.getList().add(new TopicTypeWrapper(tt));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.topicmapslab.onotoa.search.searchImpl.ISearchImpl#getResult()
	 */
	public Container getResult() {
		return con;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.topicmapslab.onotoa.search.searchImpl.ISearchImpl#getReslutList()
	 */
	public List<TopicType> getReslutList() {

		List<TopicType> resultList = new ArrayList<TopicType>();
		for (Object wrapper : con.getList())
			resultList.add(((TopicTypeWrapper) wrapper).getTopicType());

		return resultList;
	}

}
