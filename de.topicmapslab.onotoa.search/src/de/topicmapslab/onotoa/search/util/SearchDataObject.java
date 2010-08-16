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

/**
 * Object that includes all inputs from the search mask. For details look at the
 * constructor.
 * 
 * @author Sebastian Lippert
 * 
 */

public class SearchDataObject {

	private boolean isAdvancedSearch;
	private String searchString;
	private String type;
	private boolean isCaseSensitive;
	private boolean isExactMatch;
	private boolean isRegExp;

	/**
	 * Standart constructor
	 * 
	 * @param isAdvancedSearch
	 *            Boolean if the search is basic or advanced
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
	 */

	public SearchDataObject(boolean isAdvancedSearch, String searchString, String type, boolean isCaseSensitive,
	        boolean isExactMatch, boolean isRegExp) {

		this.isAdvancedSearch = isAdvancedSearch;
		this.searchString = searchString;
		this.type = type;
		this.isCaseSensitive = isCaseSensitive;
		this.isExactMatch = isExactMatch;
		this.isRegExp = isRegExp;

	}

	/**
	 * @return the isAdvancedSearch
	 */

	public Boolean getIsAdvancedSearch() {
		return isAdvancedSearch;
	}

	/**
	 * @param isAdvancedSearch
	 *            the isAdvancedSearch to set
	 */
	public void setIsAdvancedSearch(Boolean isAdvancedSearch) {
		this.isAdvancedSearch = isAdvancedSearch;
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
	public boolean getIsCaseSensitive() {
		return isCaseSensitive;
	}

	/**
	 * @param isCaseSensitive
	 *            the isCaseSensitive to set
	 */

	public void setIsCaseSensitive(Boolean isCaseSensitive) {
		this.isCaseSensitive = isCaseSensitive;
	}

	/**
	 * @return the isExactMatch
	 */
	public boolean getIsExactMatch() {
		return isExactMatch;
	}

	/**
	 * @param isExactMatch
	 *            the isExactMatch to set
	 */
	public void setIsExactMatch(Boolean isExactMatch) {
		this.isExactMatch = isExactMatch;
	}

	/**
	 * @return the isRegExp
	 */

	public boolean getIsRegExp() {
		return isRegExp;
	}

	/**
	 * @param isRegExp
	 *            the isRegExp to set
	 */

	public void setIsRegExp(Boolean isRegExp) {
		this.isRegExp = isRegExp;
	}

}
