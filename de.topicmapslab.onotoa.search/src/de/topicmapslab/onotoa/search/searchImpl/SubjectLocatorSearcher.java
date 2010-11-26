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
import java.util.Collections;
import java.util.List;

import de.topicmapslab.onotoa.search.container.SubjectLocatorContainer;
import de.topicmapslab.onotoa.search.wrapper.IdentifierWrapper;
import de.topicmapslab.onotoa.search.wrapper.TopicTypeWrapper;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;

/**
 * 
 * Class that implements the Subject Identifier search
 * 
 * @author Sebastian Lippert
 * 
 */

public class SubjectLocatorSearcher implements ISearcher {

	private TopicMapSchema schema;
	private SubjectLocatorContainer con;
	private List<String> locatorList;

	/**
	 * Constructor
	 * 
	 * @param schema
	 *            Schema that should used
	 */

	public SubjectLocatorSearcher(TopicMapSchema schema) {

		this.schema = schema;
		con = new SubjectLocatorContainer("All Subject Locators", this);
		locatorList = new ArrayList<String>();

	}

	/**
	 * {@inheritDoc}
	 */

	public void fetchResult() {

		for (TopicType type : schema.getTopicTypes()) {
			for (String locator : type.getLocators()) {
				locatorList.add(locator);
				con.addListElement(new IdentifierWrapper(type, locator, IdentifierWrapper.SUBJECTLOCATOR));
			}
		}

	}

	/**
	 * {@inheritDoc}
	 */

	public SubjectLocatorContainer getResult() {
		Collections.sort((List<? extends Comparable>) con.getContentList());
		return this.con;
	}

	/**
	 * 
	 * Returns the result list 
	 * 
	 * @return
	 */

	public List<TopicType> getResultList() {

		List<TopicType> resultList = new ArrayList<TopicType>();
		for (Object wrapper : con.getContentList())
			resultList.add(((TopicTypeWrapper) wrapper).getTopicType());

		return resultList;
	}

	/**
	 * Getter for all found Subject Locator
	 * 
	 * @return Subject Locator list
	 */

	public List<String> getLocatorList() {
		return locatorList;
	}

	/**
	 * {@inheritDoc}
	 */

	public void refresh() {

		con.removeAllElements();
		fetchResult();

	}

}
