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
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.IProgressMonitor;

import de.topicmapslab.onotoa.search.util.SearchDataObject;
import de.topicmapslab.onotoa.search.views.Container;
import de.topicmapslab.onotoa.search.wrapper.AbstractTypeWrapper;
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

	private String lowerCaseSearchString;
	private final String searchString;
	private final boolean isCaseSensitive;
	private final boolean isExactMatch;
	private final boolean isRegExp;
	private final boolean checkSubjectIdentifier;
	private final boolean checkSubjectLocator;
	private final TopicMapSchema schema;
	private HashSet<AbstractTypeWrapper> set;

	private final IProgressMonitor progressMonitor;

	private Container con;
	private int validateType;
	private boolean isValidated;

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
		this.checkSubjectIdentifier = searchObj.getIsCheckSubjectidentifier();
		this.checkSubjectLocator = searchObj.getIsCheckSubjectLocator();
		this.schema = schema;
		this.progressMonitor = progressMonitor;
		this.lowerCaseSearchString = searchString.toLowerCase();
		set = new HashSet<AbstractTypeWrapper>();

		con = new Container();
		validateType = typeList.indexOf(searchObj.getType());

	}

	/**
	 * Search TopicTypes with the given name
	 */

	public void fetchResult() {

		findTopicsByName();
		// if (checkSubjectIdentifier)
		// findTopicsBySubjectIdentifier();
	}

	/**
     * 
     */
	private void findTopicsByName() {
		// init progressmonitor
		progressMonitor.setTaskName("Searching Topic Types by Name");
		progressMonitor.beginTask("Searching", schema.getTopicTypes().size());

		// worked tts
		int worked = 0;

		/*
		 * This search consists of three nested steps.
		 * 
		 * The first, outer one respects only the three Boolean parameters
		 * isCaseSensitive, isExactMatch and isRegExp. They all specify the way
		 * of interpreting the searchString that possibly match the name of a
		 * TopicType. The comment over each great part represents the binary
		 * code of those.
		 * 
		 * For example 000 means: CaseSensitive = false ExactMatch = false
		 * RegExp = false
		 * 
		 * The second, middle step respects the Booleans checkSubjectIdentifier
		 * and checkSubjectLocator. They indicates that the search should check
		 * the Identifiers and Locators of each TopicType or not. The
		 * searchSting will be interpreted in the same way as specified at the
		 * first step.
		 * 
		 * Now all five possible Booleans are checked and the exact search can
		 * executed. This is the third and last step.
		 * 
		 * All positive matches will be added to a HashSet.
		 */

		/*
		 * 000
		 */
		if (!isCaseSensitive && !isExactMatch) {

			if (!checkSubjectIdentifier && !checkSubjectLocator) {
				for (TopicType tt : schema.getTopicTypes()) {
					if (notCaseNotExactTest(tt.getName()) && validateType(tt, validateType))
						set.add(getTypeWrapper(tt, validateType));

					worked++;
					progressMonitor.worked(worked);
				}

			} else if (checkSubjectIdentifier && !checkSubjectLocator) {
				for (TopicType tt : schema.getTopicTypes()) {

					isValidated = validateType(tt, validateType);
					if (notCaseNotExactTest(tt.getName()) && isValidated) {
						set.add(getTypeWrapper(tt, validateType));
						worked++;
						progressMonitor.worked(worked);
						continue;
					}
					for (String s : tt.getIdentifiers()) {
						if (notCaseNotExactTest(s) && isValidated) {
							set.add(getTypeWrapper(tt, validateType));
							break;
						}
					}
					worked++;
					progressMonitor.worked(worked);
				}
			} else if (checkSubjectLocator && !checkSubjectIdentifier) {

				for (TopicType tt : schema.getTopicTypes()) {

					isValidated = validateType(tt, validateType);
					if (notCaseNotExactTest(tt.getName()) && isValidated) {
						set.add(getTypeWrapper(tt, validateType));
						worked++;
						progressMonitor.worked(worked);
						continue;
					}
					for (String s : tt.getLocators()) {
						if (notCaseNotExactTest(s) && isValidated) {
							set.add(getTypeWrapper(tt, validateType));
							break;
						}
					}
					worked++;
					progressMonitor.worked(worked);
				}
			} else {
				boolean done;
				for (TopicType tt : schema.getTopicTypes()) {

					done = false;
					isValidated = validateType(tt, validateType);
					if (notCaseNotExactTest(tt.getName()) && isValidated) {
						set.add(getTypeWrapper(tt, validateType));
						worked++;
						progressMonitor.worked(worked);
						continue;
					}

					for (String s : tt.getIdentifiers()) {
						if (notCaseNotExactTest(s) && isValidated) {
							set.add(getTypeWrapper(tt, validateType));
							done = true;
							break;
						}
					}

					if (!done) {
						for (String s : tt.getLocators()) {
							if (notCaseNotExactTest(s) && isValidated) {
								set.add(getTypeWrapper(tt, validateType));
								break;
							}
						}
					}
					worked++;
					progressMonitor.worked(worked);
				}

			}
		}

		/*
		 * 100
		 */
		else if (isCaseSensitive && !isExactMatch) {
			if (!checkSubjectIdentifier && !checkSubjectLocator) {

				for (TopicType tt : schema.getTopicTypes()) {
					if (isCaseNotExactTest(tt.getName()) && validateType(tt, validateType))
						set.add(getTypeWrapper(tt, validateType));

					worked++;
					progressMonitor.worked(worked);
				}
			} else if (checkSubjectIdentifier && !checkSubjectLocator) {

				for (TopicType tt : schema.getTopicTypes()) {

					isValidated = validateType(tt, validateType);
					if (isCaseNotExactTest(tt.getName()) && isValidated) {
						set.add(getTypeWrapper(tt, validateType));
						worked++;
						progressMonitor.worked(worked);
						continue;
					}
					for (String s : tt.getIdentifiers()) {
						if (isCaseNotExactTest(s) && isValidated) {
							set.add(getTypeWrapper(tt, validateType));
							break;
						}
					}
					worked++;
					progressMonitor.worked(worked);
				}
			} else if (checkSubjectLocator && !checkSubjectIdentifier) {

				for (TopicType tt : schema.getTopicTypes()) {

					isValidated = validateType(tt, validateType);
					if (isCaseNotExactTest(tt.getName()) && isValidated) {
						set.add(getTypeWrapper(tt, validateType));
						worked++;
						progressMonitor.worked(worked);
						continue;
					}
					for (String s : tt.getLocators()) {
						if (isCaseNotExactTest(s) && isValidated) {
							set.add(getTypeWrapper(tt, validateType));
							break;
						}
					}
					worked++;
					progressMonitor.worked(worked);
				}
			} else {

				boolean done;
				for (TopicType tt : schema.getTopicTypes()) {

					done = false;
					isValidated = validateType(tt, validateType);
					if (isCaseNotExactTest(tt.getName()) && isValidated) {
						set.add(getTypeWrapper(tt, validateType));
						worked++;
						progressMonitor.worked(worked);
						continue;
					}

					for (String s : tt.getIdentifiers()) {
						if (isCaseNotExactTest(s) && isValidated) {
							set.add(getTypeWrapper(tt, validateType));
							done = true;
							break;
						}
					}

					if (!done) {
						for (String s : tt.getLocators()) {
							if (isCaseNotExactTest(s) && isValidated) {
								set.add(getTypeWrapper(tt, validateType));
								break;
							}
						}
					}
					worked++;
					progressMonitor.worked(worked);
				}

			}
		}

		/*
		 * 010
		 */
		else if (!isCaseSensitive && isExactMatch) {
			if (!checkSubjectIdentifier && !checkSubjectLocator) {

				for (TopicType tt : schema.getTopicTypes()) {
					if (notCaseIsExactTest(tt.getName()) && validateType(tt, validateType))
						set.add(getTypeWrapper(tt, validateType));

					worked++;
					progressMonitor.worked(worked);
				}
			} else if (checkSubjectIdentifier && !checkSubjectLocator) {

				for (TopicType tt : schema.getTopicTypes()) {

					isValidated = validateType(tt, validateType);
					if (notCaseIsExactTest(tt.getName()) && isValidated) {
						set.add(getTypeWrapper(tt, validateType));
						worked++;
						progressMonitor.worked(worked);
						continue;
					}
					for (String s : tt.getIdentifiers()) {
						if (notCaseIsExactTest(s) && isValidated) {
							set.add(getTypeWrapper(tt, validateType));
							break;
						}
					}
					worked++;
					progressMonitor.worked(worked);
				}
			} else if (checkSubjectLocator && !checkSubjectIdentifier) {

				for (TopicType tt : schema.getTopicTypes()) {

					isValidated = validateType(tt, validateType);
					if (notCaseIsExactTest(tt.getName()) && isValidated) {
						set.add(getTypeWrapper(tt, validateType));
						worked++;
						progressMonitor.worked(worked);
						continue;
					}
					for (String s : tt.getLocators()) {
						if (notCaseIsExactTest(s) && isValidated) {
							set.add(getTypeWrapper(tt, validateType));
							break;
						}
					}
					worked++;
					progressMonitor.worked(worked);
				}
			} else {
				boolean done;
				for (TopicType tt : schema.getTopicTypes()) {

					done = false;
					isValidated = validateType(tt, validateType);
					if (notCaseIsExactTest(tt.getName()) && isValidated) {
						set.add(getTypeWrapper(tt, validateType));
						worked++;
						progressMonitor.worked(worked);
						continue;
					}

					for (String s : tt.getIdentifiers()) {
						if (notCaseIsExactTest(s) && isValidated) {
							set.add(getTypeWrapper(tt, validateType));
							done = true;
							break;
						}
					}

					if (!done) {
						for (String s : tt.getLocators()) {
							if (notCaseIsExactTest(s) && isValidated) {
								set.add(getTypeWrapper(tt, validateType));
								break;
							}
						}
					}
					worked++;
					progressMonitor.worked(worked);
				}

			}
		}

		/*
		 * 110
		 */
		else if (isCaseSensitive && isExactMatch) {
			if (!checkSubjectIdentifier && !checkSubjectLocator) {

				for (TopicType tt : schema.getTopicTypes()) {
					if (isCaseIsExactTest(tt.getName()) && validateType(tt, validateType))
						set.add(getTypeWrapper(tt, validateType));

					worked++;
					progressMonitor.worked(worked);
				}
			} else if (checkSubjectIdentifier && !checkSubjectLocator) {

				for (TopicType tt : schema.getTopicTypes()) {

					isValidated = validateType(tt, validateType);
					if (isCaseIsExactTest(tt.getName()) && isValidated) {
						set.add(getTypeWrapper(tt, validateType));
						worked++;
						progressMonitor.worked(worked);
						continue;
					}
					for (String s : tt.getIdentifiers()) {
						if (isCaseIsExactTest(s) && isValidated) {
							set.add(getTypeWrapper(tt, validateType));
							break;
						}
					}
					worked++;
					progressMonitor.worked(worked);
				}
			} else if (checkSubjectLocator && !checkSubjectIdentifier) {

				for (TopicType tt : schema.getTopicTypes()) {

					isValidated = validateType(tt, validateType);
					if (isCaseIsExactTest(tt.getName()) && isValidated) {
						set.add(getTypeWrapper(tt, validateType));
						worked++;
						progressMonitor.worked(worked);
						continue;
					}
					for (String s : tt.getLocators()) {
						if (isCaseIsExactTest(s) && isValidated) {
							set.add(getTypeWrapper(tt, validateType));
							break;
						}
					}
					worked++;
					progressMonitor.worked(worked);
				}
			} else {
				boolean done;
				for (TopicType tt : schema.getTopicTypes()) {

					done = false;
					isValidated = validateType(tt, validateType);
					if (isCaseIsExactTest(tt.getName()) && isValidated) {
						set.add(getTypeWrapper(tt, validateType));
						worked++;
						progressMonitor.worked(worked);
						continue;
					}

					for (String s : tt.getIdentifiers()) {
						if (isCaseIsExactTest(s) && isValidated) {
							set.add(getTypeWrapper(tt, validateType));
							done = true;
							break;
						}
					}

					if (!done) {
						for (String s : tt.getLocators()) {
							if (isCaseIsExactTest(s) && isValidated) {
								set.add(getTypeWrapper(tt, validateType));
								break;
							}
						}
					}
					worked++;
					progressMonitor.worked(worked);
				}

			}
		}

		/*
		 * 001 (isRegExp = true)
		 */
		else {
			Matcher matcher;
			Pattern pattern = Pattern.compile(searchString);

			if (!checkSubjectIdentifier && !checkSubjectLocator) {

				for (TopicType tt : schema.getTopicTypes()) {
				 Matcher	matcher1 = pattern.matcher(tt.getName());
					if (matcher1.matches() && validateType(tt, validateType))
						set.add(getTypeWrapper(tt, validateType));

					worked++;
					progressMonitor.worked(worked);
				}
			} else if (checkSubjectIdentifier && !checkSubjectLocator) {
				for (TopicType tt : schema.getTopicTypes()) {

					isValidated = validateType(tt, validateType);
					matcher = pattern.matcher(tt.getName());
					if (matcher.matches() && isValidated) {
						set.add(getTypeWrapper(tt, validateType));
						worked++;
						progressMonitor.worked(worked);
						continue;
					}
					for (String s : tt.getIdentifiers()) {
						matcher = pattern.matcher(s);
						if (matcher.matches() && isValidated) {
							set.add(getTypeWrapper(tt, validateType));
							break;
						}
					}
					worked++;
					progressMonitor.worked(worked);
				}
			} else if (!checkSubjectIdentifier && checkSubjectLocator) {
				for (TopicType tt : schema.getTopicTypes()) {

					isValidated = validateType(tt, validateType);
					matcher = pattern.matcher(tt.getName());
					if (matcher.matches() && isValidated) {
						set.add(getTypeWrapper(tt, validateType));
						worked++;
						progressMonitor.worked(worked);
						continue;
					}

					for (String s : tt.getLocators()) {
						matcher = pattern.matcher(s);
						if (matcher.matches() && isValidated) {
							set.add(getTypeWrapper(tt, validateType));
							break;
						}
					}
					worked++;
					progressMonitor.worked(worked);
				}
			} else {

				boolean done;
				for (TopicType tt : schema.getTopicTypes()) {

					done = false;
					isValidated = validateType(tt, validateType);
					matcher = pattern.matcher(tt.getName());
					if (matcher.matches() && isValidated) {
						set.add(getTypeWrapper(tt, validateType));
						worked++;
						progressMonitor.worked(worked);
						continue;
					}

					for (String s : tt.getIdentifiers()) {
						matcher = pattern.matcher(s);
						if (matcher.matches() && isValidated) {
							set.add(getTypeWrapper(tt, validateType));
							done = true;
							break;
						}
					}

					if (!done) {
						for (String s : tt.getLocators()) {
							matcher = pattern.matcher(s);
							if (matcher.matches() && isValidated) {
								set.add(getTypeWrapper(tt, validateType));
								break;
							}
						}
					}
					worked++;
					progressMonitor.worked(worked);
				}
			}
		}
		progressMonitor.done();
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
		return s.contains(searchString);
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

	private AbstractTypeWrapper getTypeWrapper(TopicType topicType, int validateType) {

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

		Iterator it = set.iterator();
		while (it.hasNext()) {
			con.getList().add(it.next());
		}
		return con;
	}

}
