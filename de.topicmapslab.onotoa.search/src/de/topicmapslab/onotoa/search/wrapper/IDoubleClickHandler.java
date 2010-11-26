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
package de.topicmapslab.onotoa.search.wrapper;

import org.eclipse.jface.viewers.ISelectionProvider;

/**
 * Interface for classes that should hanlde a double click
 * 
 * @author Sebastian Lippert
 * 
 */

public interface IDoubleClickHandler extends ISelectionProvider {

	/**
	 * handling for double click
	 */
	public void doubleClickHappend();

}
