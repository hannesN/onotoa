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
import de.topicmapslab.onotoa.search.wrapper.IdentifierWrapper;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;

/**
 * @author sip
 * 
 */
public class SubjectLocatorSearcher {

	private TopicMapSchema schema;
	private Container con;
	private List<String> locatorList;

	public SubjectLocatorSearcher(TopicMapSchema schema) {

		this.schema = schema;
		con = new Container("All Subject Locators");
		locatorList = new ArrayList<String>();
		createContainer();

	}

	private void createContainer() {

		for (TopicType type : schema.getTopicTypes()) {
			for (String locator : type.getLocators()) {
				locatorList.add(locator);
				con.getList().add(new IdentifierWrapper(type, locator, IdentifierWrapper.SUBJECTLOCATOR));
			}
		}
	}

	public List<String> getLocatorList(){
		return locatorList;
	}
	
	public Container getContainer() {
		return this.con;
	}

}
