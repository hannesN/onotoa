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

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.IProgressMonitor;

import de.topicmapslab.onotoa.search.util.SearchDataObject;
import de.topicmapslab.onotoa.search.views.Container;
import de.topicmapslab.onotoa.search.wrapper.AssociationTypeWrapper;
import de.topicmapslab.onotoa.search.wrapper.NameTypeWrapper;
import de.topicmapslab.onotoa.search.wrapper.OccurrenceTypeWrapper;
import de.topicmapslab.onotoa.search.wrapper.RoleTypeWrapper;
import de.topicmapslab.onotoa.search.wrapper.TopicTypeWrapper;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;

/**
 * Basic search class for TopicTypes.
 * 
 * Compares names of all TopicTypes at the schema with the users searchString
 * and put the valid ones into a container. It is possible to precise the result
 * by declaring that the search is caseSensitive and/or only valid for exact
 * matches or that the searchString represents a regular expressions.
 * 
 * @author Sebastian Lippert
 */

public class BasicTopicTypeSearcher implements ISearchImpl {

	private final String searchString;
	private final boolean isCaseSensitive;
	private final boolean isExactMatch;
	private final boolean isRegExp;
	private final TopicMapSchema schema;

	private final IProgressMonitor progressMonitor;

	private Container con;
	private int validateType;

	private List<String> typeList = Arrays.asList(new String[] { "Any", "Topic Type", "Occurrence Type", "Name Type",
	        "Role Type", "Association Type" });

	/**
	 * Constructor
	 * 
	 * @param searchObj
	 *            Object that includes all inputs from the search mask
	 * @param schema
	 *            the search based on
	 */

	public BasicTopicTypeSearcher(SearchDataObject searchObj, TopicMapSchema schema, IProgressMonitor progressMonitor) {

		this.searchString = searchObj.getSearchString();
		this.isCaseSensitive = searchObj.getIsCaseSensitive();
		this.isExactMatch = searchObj.getIsExactMatch();
		this.isRegExp = searchObj.getIsRegExp();
		this.schema = schema;
		this.progressMonitor = progressMonitor;

		con = new Container();
		validateType = typeList.indexOf(searchObj.getType());

	}

	/**
	 * Search TopicTypes with the given name
	 */

	public void fetchResult() {

		// init progressmonitor
		progressMonitor.setTaskName("Searching Topic Types");
		progressMonitor.beginTask("Searching", schema.getTopicTypes().size());

		// worked tts
		int worked = 0;

		/*
		 * search TopicType by name according through the three possible Boolean
		 * parameters (CaseSensitive, ExactMatch, RegExp) which specify the
		 * searchString. The comment over each search represents the binary code
		 * of this these.
		 * 
		 * For example 000 means: CaseSensitive = false ExactMatch = false
		 * RegExp = false
		 */

		// 000
		if (!isCaseSensitive && !isExactMatch) {
			for (TopicType tt : schema.getTopicTypes()) {
				if (tt.getName().toLowerCase().contains(searchString.toLowerCase()) && validateType(tt, validateType))
					con.getList().add(getTypeWrapper(tt, validateType));
				worked++;
				progressMonitor.worked(worked);
			}
		}
		// 100
		if (isCaseSensitive && !isExactMatch) {
			for (TopicType tt : schema.getTopicTypes()) {
				if (tt.getName().contains(searchString) && validateType(tt, validateType))
					con.getList().add(getTypeWrapper(tt, validateType));
				worked++;
				progressMonitor.worked(worked);
			}
		}
		// 010
		if (!isCaseSensitive && isExactMatch) {
			for (TopicType tt : schema.getTopicTypes()) {
				if (tt.getName().equalsIgnoreCase(searchString) && validateType(tt, validateType))
					con.getList().add(getTypeWrapper(tt, validateType));
				worked++;
				progressMonitor.worked(worked);
			}
		}
		// 110
		if (isCaseSensitive && isExactMatch) {
			for (TopicType tt : schema.getTopicTypes()) {
				if (tt.getName().equals(searchString) && validateType(tt, validateType))
					con.getList().add(getTypeWrapper(tt, validateType));
				worked++;
				progressMonitor.worked(worked);
			}
		}
		// 001
		if (isRegExp) {
			Pattern p = Pattern.compile(searchString);
			for (TopicType tt : schema.getTopicTypes()) {
				Matcher m = p.matcher(tt.getName());
				if (m.matches() && validateType(tt, validateType))
					con.getList().add(getTypeWrapper(tt, validateType));
				worked++;
				progressMonitor.worked(worked);
			}
		}
		progressMonitor.done();
	}

	/**
	 * Validates if the TopicType matches the expected type or not.
	 * 
	 * @param topicType
	 *            which should be proofed
	 * @return boolean result
	 */

	private boolean validateType(TopicType topicType, int validateType) {
		return (validateType == 0) || (topicType.getKind().getValue() == (validateType - 1));
	}

	/**
	 * Getter for the wrapper.
	 * 
	 * Returns the TopicType class own wrapper
	 * 
	 * @param topicType
	 *            that should be wrapped
	 * @return special wrapper
	 */

	private Object getTypeWrapper(TopicType topicType, int validateType) {

		switch (validateType) {

		case 0:
			return getTypeWrapper(topicType, topicType.getKind().getValue() + 1);
		case 1:
			return new TopicTypeWrapper(topicType);
		case 2:
			return new OccurrenceTypeWrapper(topicType);
		case 3:
			return new NameTypeWrapper(topicType);
		case 4:
			return new RoleTypeWrapper(topicType);
		case 5:
			return new AssociationTypeWrapper(topicType);

		default:
			return null;

		}

	}

	/**
	 * Getter for the search result
	 * 
	 * @return Result container
	 */

	public Container getResult() {
		return con;
	}

}
