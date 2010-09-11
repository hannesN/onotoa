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
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.IProgressMonitor;

import de.topicmapslab.onotoa.search.util.SearchData;
import de.topicmapslab.onotoa.search.views.Container;
import de.topicmapslab.onotoa.search.wrapper.TopicTypeWrapper;
import de.topicmapslab.tmcledit.model.AssociationType;
import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.NameType;
import de.topicmapslab.tmcledit.model.NameTypeConstraint;
import de.topicmapslab.tmcledit.model.OccurrenceType;
import de.topicmapslab.tmcledit.model.OccurrenceTypeConstraint;
import de.topicmapslab.tmcledit.model.RoleConstraint;
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;
import de.topicmapslab.tmcledit.model.RoleType;
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

	private String lowerCaseSearchString, containerLabel;
	private final String searchString;
	private final boolean isCaseSensitive;
	private final boolean isExactMatch;
	private final boolean isRegExp;
	private final boolean checkSubjectIdentifier;
	private final boolean checkSubjectLocator;
	private final boolean checkName;
	private final TopicMapSchema schema;
	private List<TopicTypeWrapper> resultList;
	private final List<TopicType> topicList;
	private final IProgressMonitor progressMonitor;

	private Container con;
	private int validateType;
	private boolean isValidated;

	private List<String> typeList = Arrays.asList(new String[] { "Any", "Topic Type", "Occurrence Type", "Name Type",
	        "Role Type", "Association Type" });

	/**
	 * Constructor
	 * 
	 * @param searchData
	 *            Object that includes all inputs from the search mask
	 * @param schema
	 *            the search based on
	 */

	public BasicTopicTypeSearcher(SearchData searchData, TopicMapSchema schema, IProgressMonitor progressMonitor) {

		this.searchString = searchData.getSearchString();
		this.isCaseSensitive = searchData.isCaseSensitive();
		this.isExactMatch = searchData.isExactMatch();
		this.isRegExp = searchData.isRegExp();
		this.checkSubjectIdentifier = searchData.isCheckSubjectidentifier();
		this.checkSubjectLocator = searchData.isCheckSubjectLocator();
		this.checkName = searchData.isCheckName();
		this.topicList = searchData.getTopicList();
		this.schema = schema;
		this.progressMonitor = progressMonitor;

		resultList = new ArrayList<TopicTypeWrapper>();
		validateType = typeList.indexOf(searchData.getType());
		lowerCaseSearchString = searchString.toLowerCase();

		containerLabel = "Search for \"" + searchData.getSearchString() + "\" " + " (Type: " + searchData.getType()
		        + ")";
		
		con = new Container(containerLabel);

	}

	/**
	 * Search TopicTypes
	 */

	public void fetchResult() {

		// find topics by name, identifier and locator
		findTopics();

		/*
		 * check topicList argument and remove Topics that don't match the
		 * expected constraints
		 */
		if (topicList != null && topicList.size() != 0)
			if (validateType == 5)
				// check constraints of searched AssociationTypes
				cleanAssociationTypeSearch();
			else
				// check constraints of searched TopicTypes
				cleanTopicTypeSearch();
	}

	/**
     * 
     */
	private void findTopics() {

		// init progressmonitor
		progressMonitor.setTaskName("Searching Topic Types");
		progressMonitor.beginTask("Searching", schema.getTopicTypes().size());

		// worked tts
		int worked = 0;

		/*
		 * This search consists of two nested steps.
		 * 
		 * The first, outer one respects only the three Boolean parameters
		 * isCaseSensitive, isExactMatch and isRegExp. They all specify the way
		 * of interpreting the searchString.The comment over each great part
		 * represents the binary code of those.
		 * 
		 * For example 000 means: CaseSensitive = false ExactMatch = false
		 * RegExp = false
		 * 
		 * The second, middle step respects the Booleans checkSubjectIdentifier,
		 * checkSubjectLocator and checkName. They indicates that the search
		 * should check the Identifiers and/or Locators and/or names of each
		 * TopicType or not. The searchSting will be interpreted in the same way
		 * as specified at the first step.
		 * 
		 * All positive matches will be added to a HashSet.
		 */

		/*
		 * 000
		 */
		if (!isCaseSensitive && !isExactMatch && !isRegExp) {
			for (TopicType tt : schema.getTopicTypes()) {

				boolean done = false;
				isValidated = validateType(tt, validateType);
				if (checkName) {
					if (isValidated && notCaseNotExactTest(tt.getName())) {
						resultList.add(new TopicTypeWrapper(tt));
						worked++;
						progressMonitor.worked(worked);
						continue;
					}
				}
				if (checkSubjectIdentifier) {
					for (String s : tt.getIdentifiers()) {
						if (isValidated && notCaseNotExactTest(s)) {
							resultList.add(new TopicTypeWrapper(tt));
							done = true;
							break;
						}
					}
				}
				if (checkSubjectLocator && !done) {
					for (String s : tt.getLocators()) {
						if (isValidated && notCaseNotExactTest(s)) {
							resultList.add(new TopicTypeWrapper(tt));
							break;
						}
					}
				}
			}
		}

		/*
		 * 100
		 */
		else if (isCaseSensitive && !isExactMatch) {
			for (TopicType tt : schema.getTopicTypes()) {

				boolean done = false;
				isValidated = validateType(tt, validateType);
				if (checkName) {
					if (isValidated && isCaseNotExactTest(tt.getName())) {
						resultList.add(new TopicTypeWrapper(tt));
						worked++;
						progressMonitor.worked(worked);
						continue;
					}
				}
				if (checkSubjectIdentifier) {
					for (String s : tt.getIdentifiers()) {
						if (isValidated && isCaseNotExactTest(s)) {
							resultList.add(new TopicTypeWrapper(tt));
							done = true;
							break;
						}
					}
				}
				if (checkSubjectLocator && !done) {
					for (String s : tt.getLocators()) {
						if (isValidated && isCaseNotExactTest(s)) {
							resultList.add(new TopicTypeWrapper(tt));
							break;
						}
					}
				}
			}
		}

		/*
		 * 010
		 */
		else if (!isCaseSensitive && isExactMatch) {
			for (TopicType tt : schema.getTopicTypes()) {

				boolean done = false;
				isValidated = validateType(tt, validateType);
				if (checkName) {
					if (isValidated && notCaseIsExactTest(tt.getName())) {
						resultList.add(new TopicTypeWrapper(tt));
						worked++;
						progressMonitor.worked(worked);
						continue;
					}
				}
				if (checkSubjectIdentifier) {
					for (String s : tt.getIdentifiers()) {
						if (isValidated && notCaseIsExactTest(s)) {
							resultList.add(new TopicTypeWrapper(tt));
							done = true;
							break;
						}
					}
				}
				if (checkSubjectLocator && !done) {
					for (String s : tt.getLocators()) {
						if (isValidated && notCaseIsExactTest(s)) {
							resultList.add(new TopicTypeWrapper(tt));
							break;
						}
					}
				}
			}
		}

		/*
		 * 110
		 */
		else if (isCaseSensitive && isExactMatch) {
			for (TopicType tt : schema.getTopicTypes()) {

				boolean done = false;
				isValidated = validateType(tt, validateType);
				if (checkName) {
					if (isValidated && isCaseIsExactTest(tt.getName())) {
						resultList.add(new TopicTypeWrapper(tt));
						worked++;
						progressMonitor.worked(worked);
						continue;
					}
				}
				if (checkSubjectIdentifier) {
					for (String s : tt.getIdentifiers()) {
						if (isValidated && isCaseIsExactTest(s)) {
							resultList.add(new TopicTypeWrapper(tt));
							done = true;
							break;
						}
					}
				}
				if (checkSubjectLocator && !done) {
					for (String s : tt.getLocators()) {
						if (isValidated && isCaseIsExactTest(s)) {
							resultList.add(new TopicTypeWrapper(tt));
							break;
						}
					}
				}
			}
		}

		/*
		 * 001 (isRegExp = true)
		 */
		else {
			Matcher matcher;
			Pattern pattern = Pattern.compile(searchString);
			boolean done;
			for (TopicType tt : schema.getTopicTypes()) {

				done = false;
				isValidated = validateType(tt, validateType);
				if (checkName) {
					matcher = pattern.matcher(tt.getName());
					if (isValidated && matcher.matches()) {
						resultList.add(new TopicTypeWrapper(tt));
						worked++;
						progressMonitor.worked(worked);
						continue;
					}
				}
				if (checkSubjectIdentifier) {
					for (String s : tt.getIdentifiers()) {
						matcher = pattern.matcher(s);
						if (matcher.matches() && isValidated) {
							resultList.add(new TopicTypeWrapper(tt));
							done = true;
							break;
						}
					}
				}
				if (checkSubjectLocator && !done) {
					for (String s : tt.getLocators()) {
						matcher = pattern.matcher(s);
						if (matcher.matches() && isValidated) {
							resultList.add(new TopicTypeWrapper(tt));
							break;
						}
					}
				}
			}
		}
		progressMonitor.done();
	}

	/**
	 * Compares all constraints from all previously found TopicTypes with the
	 * constraints the user specified in the advanced dialog part. If an
	 * TopicType doesn't contains all specified constraints he will be removed
	 * from the ResultList.
	 */

	private void cleanTopicTypeSearch() {

		List<OccurrenceType> occurrenceList;
		List<NameType> nameList;
		List<RoleType> roleList;
		List<AssociationType> associationList;
		List<TopicTypeWrapper> removeList = new ArrayList<TopicTypeWrapper>();
		TopicType resultType;

		// iterate over all wrapper from previous search
		for (int i = 0; i < resultList.size(); i++) {

			// TopicType to take a look at
			resultType = resultList.get(i).getTopicType();

			// lists for types of the TopicTypes constraints
			occurrenceList = new ArrayList<OccurrenceType>();
			nameList = new ArrayList<NameType>();
			roleList = new ArrayList<RoleType>();
			associationList = new ArrayList<AssociationType>();

			// detect all OccurrrenceTypes the TopicType uses as constraint
			if (resultType.getOccurrenceConstraints().size() > 0)
				for (OccurrenceTypeConstraint occConstraint : resultType.getOccurrenceConstraints()) {
					occurrenceList.add((OccurrenceType) occConstraint.getType());
				}

			// detect all NameTypes the TopicType uses as constraint
			if (resultType.getNameConstraints().size() > 0)
				for (NameTypeConstraint nameConstraint : resultType.getNameConstraints()) {
					nameList.add((NameType) nameConstraint.getType());
				}

			/*
			 * detect all AssociationTypes where the TopicType plays a role and
			 * detect all roles the TopicType plays
			 */
			if (schema.getAssociationTypeConstraints().size() > 0)
				for (AssociationTypeConstraint assoConstraint : schema.getAssociationTypeConstraints()) {
					for (RolePlayerConstraint rpc : assoConstraint.getPlayerConstraints()) {
						if (rpc.getPlayer().equals(resultType)) {
							roleList.add((RoleType) rpc.getRole().getType());
							associationList.add((AssociationType) assoConstraint.getType());
						}
					}
				}

			/*
			 * iterate over all types (the searched TopicType should use) and
			 * check if they are used by the TopicType of the iteration
			 */
			for (TopicType type : topicList) {

				switch (type.getKind().getValue()) {
				case 1:
					if (!occurrenceList.contains(type))
						removeList.add(resultList.get(i));
					break;
				case 2:
					if (!nameList.contains(type))
						removeList.add(resultList.get(i));
					break;
				case 3:
					if (!roleList.contains(type))
						removeList.add(resultList.get(i));
					break;
				case 4:
					if (!associationList.contains(type))
						removeList.add(resultList.get(i));
					break;
				default:
					break;
				}
			}
		}
		// remove non matching wrapper from the resultList
		resultList.removeAll(removeList);
	}

	/**
	 * Compares all roles from every previously found AssociationType with the
	 * RoleTypes the user specified in the advanced dialog part. If an
	 * AssociationType doesn't contains all specified RoleTypes he will be
	 * removed from the ResultList.
	 */

	private void cleanAssociationTypeSearch() {

		List<RoleConstraint> rolesList;
		List<TopicType> rolesTypeList;
		List<TopicTypeWrapper> removeList = new ArrayList<TopicTypeWrapper>();

		// iterate over all wrapper from the previous search
		for (TopicTypeWrapper wrapper : resultList) {

			// get all roles of the wrapper
			rolesList = new ArrayList<RoleConstraint>(((AssociationType) wrapper.getTopicType()).getRoles());
			rolesTypeList = new ArrayList<TopicType>();

			// put all TopicTypes of the founded roles into rolesTypeList
			for (RoleConstraint rt : rolesList)
				rolesTypeList.add(rt.getType());

			// compare rolesTypeList and topicList
			if (!rolesTypeList.containsAll(topicList))
				removeList.add(wrapper);
		}

		// remove non matching wrapper from the resultList
		resultList.removeAll(removeList);
	}

	private boolean notCaseNotExactTest(String s) {
		return (s.toLowerCase().contains(lowerCaseSearchString));
	}

	private boolean isCaseNotExactTest(String s) {
		return s.contains(searchString);
	}

	private boolean notCaseIsExactTest(String s) {
		return s.equalsIgnoreCase(searchString);
	}

	private boolean isCaseIsExactTest(String s) {
		return s.equals(searchString);
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
	 * Getter for the search result
	 * 
	 * @return Result container
	 */

	public Container getResult() {

		Iterator it = resultList.iterator();
		while (it.hasNext()) {
			con.getList().add(it.next());
		}
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
