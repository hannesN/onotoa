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

import de.topicmapslab.onotoa.search.views.Container;

/**
 * Interface for all searcher classes. It provides a method that get the results
 * and a method that returns them as a container
 * 
 * @author Sebastian Lippert
 * 
 */

public interface ISearchImpl {

	/**
	 * fetches the result of the specific search and creates a container based on
	 * these
	 */

	public void fetchResult();

	/**
	 * get created result container
	 * 
	 * @return container with results
	 */

	public Container getResult();

}
