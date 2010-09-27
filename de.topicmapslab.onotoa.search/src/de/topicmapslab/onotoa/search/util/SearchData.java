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
package de.topicmapslab.onotoa.search.util;

import java.util.List;

import de.topicmapslab.tmcledit.model.TopicType;

/**
 * Object that includes all inputs from the search mask. For details look at the
 * constructor.
 * 
 * @author Sebastian Lippert
 * 
 */

public class SearchData {

	// private boolean isAdvancedSearch;
	private String searchString;
	private String type;
	private boolean isCaseSensitive;
	private boolean isExactMatch;
	private boolean isRegExp;
	private boolean checkSubjectidentifier;
	private boolean checkSubjectLocator;
	private boolean checkName;
	private List<TopicType> topicList;

	/**
	 * Standart constructor
	 * 
	 * @param searchString
	 *            String that will be compared with objects name
	 * @param type
	 *            String that represents type of the searched object (TopicType,
	 *            NameType ...)
	 * @param isCaseSensitive
	 *            Boolean that can specify the searchString
	 * @param isExactMatch
	 *            Boolean that can specify the searchString
	 * @param isRegExp
	 *            Boolean that can specify the searchString
	 * @param checkSubjectIdentifier
	 *            Boolean that can specify the searchString
	 * @param checkSubjectLocator
	 *            Boolean that can specify the searchString
	 * @param topicList
	 *            List of Topic Types that specifies the searched object
	 */

	public SearchData(String searchString, String type, boolean isCaseSensitive, boolean isExactMatch,
	        boolean isRegExp, boolean checkSubjectidentifier, boolean checkSubjectLocator, boolean checkName,
	        List<TopicType> topicList) {

		// this.isAdvancedSearch = isAdvancedSearch;
		this.searchString = searchString;
		this.type = type;
		this.isCaseSensitive = isCaseSensitive;
		this.isExactMatch = isExactMatch;
		this.isRegExp = isRegExp;
		this.checkSubjectidentifier = checkSubjectidentifier;
		this.checkSubjectLocator = checkSubjectLocator;
		this.checkName = checkName;
		this.topicList = topicList;

	}

	/**
	 * @return the searchString
	 */

	public String getSearchString() {
		return searchString;
	}

	/**
	 * @param searchString
	 *            the searchString to set
	 */
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	/**
	 * @return the type
	 */

	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the isCaseSensitive
	 */
	public boolean isCaseSensitive() {
		return isCaseSensitive;
	}

	/**
	 * @param isCaseSensitive
	 *            the isCaseSensitive to set
	 */

	public void setCaseSensitive(Boolean isCaseSensitive) {
		this.isCaseSensitive = isCaseSensitive;
	}

	/**
	 * @return the isExactMatch
	 */
	public boolean isExactMatch() {
		return isExactMatch;
	}

	/**
	 * @param isExactMatch
	 *            the isExactMatch to set
	 */
	public void setExactMatch(Boolean isExactMatch) {
		this.isExactMatch = isExactMatch;
	}

	/**
	 * @return the isRegExp
	 */

	public boolean isRegExp() {
		return isRegExp;
	}

	/**
	 * @param isRegExp
	 *            the isRegExp to set
	 */

	public void setRegExp(Boolean isRegExp) {
		this.isRegExp = isRegExp;
	}

	/**
	 * @return the checkSubjectidentifier
	 */
	public boolean isCheckSubjectidentifier() {
		return checkSubjectidentifier;
	}

	/**
	 * @param checkSubjectidentifier
	 *            the checkSubjectidentifier to set
	 */
	public void setCheckSubjectidentifier(boolean checkSubjectidentifier) {
		this.checkSubjectidentifier = checkSubjectidentifier;
	}

	/**
	 * @return the checkSubjectLocator
	 */
	public boolean isCheckSubjectLocator() {
		return checkSubjectLocator;
	}

	/**
	 * @param checkSubjectLocator
	 *            the checkSubjectLocator to set
	 */
	public void setCheckSubjectLocator(boolean checkSubjectLocator) {
		this.checkSubjectLocator = checkSubjectLocator;
	}

	/**
	 * @return the checkName
	 */
	public boolean isCheckName() {
		return checkName;
	}

	/**
	 * @param checkName
	 *            the checkName to set
	 */
	public void setCheckName(boolean checkName) {
		this.checkName = checkName;
	}

	/**
	 * @return the topicList
	 */
	public List<TopicType> getTopicList() {
		return topicList;
	}

	/**
	 * @param topicList
	 *            the topicList to set
	 */
	public void setTopicList(List<TopicType> topicList) {
		this.topicList = topicList;
	}

	@Override
	public String toString() {

		String s = "Input: " + searchString + "\n" + "Type: " + type + "\n" + "Exact Match: " + isExactMatch + "\n"
		        + "Case sensitive: " + isCaseSensitive + "\n" + "Reg Exp: " + isRegExp + "\n"
		        + "Check SubjectIdentifier: " + checkSubjectidentifier + "\n" + "Check SubjectLocator: "
		        + checkSubjectLocator + "\n";

		if (topicList == null)
			s += "Topic List: null";
		else
			for (TopicType topic : topicList)
				s += "Topic List: " + topic.getName() + " - " + topic.getKind().toString() + "\n";

		return s;
	}

}
