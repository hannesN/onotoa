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

import org.eclipse.core.runtime.IProgressMonitor;

import de.topicmapslab.onotoa.search.util.SearchData;
import de.topicmapslab.onotoa.search.views.Container;
import de.topicmapslab.tmcledit.model.TopicMapSchema;

/**
 * Class detects the modus of the search (basic, advanced) and the searched type
 * (TopicType, NameType ...) After this it initiates the correct search
 * 
 * @author Sebastian Lippert
 * 
 */

public class MainSearchHandler {

	private Container con;

	/**
	 * Constructor
	 * 
	 * @param searchData
	 *            Object that includes all inputs from the search mask
	 * @param schema
	 *            the search based on
	 */

	public MainSearchHandler(SearchData searchData, TopicMapSchema schema, IProgressMonitor progressMonitor) {

		//con = new Container("lala");

		// detect the modus of the search by the according Boolean

		BasicTopicTypeSearcher searcher = new BasicTopicTypeSearcher(searchData, schema, progressMonitor);
		searcher.fetchResult();
		con = searcher.getResult();

	}

	/**
	 * Getter for the search result
	 * 
	 * @return Result container
	 */

	public Container getResultContainer() {
		return con;
	}

}
