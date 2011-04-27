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

import de.topicmapslab.onotoa.search.container.SubjectIdentifierContainer;
import de.topicmapslab.onotoa.search.wrapper.IdentifierWrapper;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;

/**
 * 
 * Class that implements the Subject Identifier search
 * 
 * @author Sebastian Lippert
 * 
 */

public class SubjectIdentifierSearcher implements ISearcher {

	private TopicMapSchema schema;
	private SubjectIdentifierContainer con;
	private List<String> identifierList;

	/**
	 * Constructor
	 * 
	 * @param schema
	 *            Schema that should used
	 */

	public SubjectIdentifierSearcher(TopicMapSchema schema) {

		this.schema = schema;
		con = new SubjectIdentifierContainer("All Subject Identifiers", this);
		identifierList = new ArrayList<String>();

	}

	/**
	 * {@inheritDoc}
	 */

	public void fetchResult() {

		for (TopicType type : schema.getTopicTypes()) {
			for (String identifier : type.getIdentifiers()) {
				identifierList.add(identifier);
				con.addListElement(new IdentifierWrapper(type, identifier, IdentifierWrapper.SUBJECTIDENTIFIER));
			}
		}

	}

	/**
	 * {@inheritDoc}
	 */

	public SubjectIdentifierContainer getResult() {
		Collections.sort((List<? extends Comparable>) con.getContentList());
		return con;
	}

	/**
	 * Getter for all found Subject Identifier
	 * 
	 * @return Subject Identifier list
	 */
	public List<String> getIdentifierList() {
		return identifierList;
	}

	/**
	 * {@inheritDoc}
	 */

	public void refresh() {

		con.removeAllElements();
		fetchResult();

	}
}
