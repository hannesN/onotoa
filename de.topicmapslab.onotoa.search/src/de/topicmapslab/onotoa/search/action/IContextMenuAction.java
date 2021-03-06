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
package de.topicmapslab.onotoa.search.action;

/**
 * 
 * Property list for fired PropertyChangeEvent by context menu actions
 * 
 * @author Sebastian Lippert
 * 
 */
public interface IContextMenuAction {

	/**
	 * Constant for adding an Subject Identifier
	 */
	public static String ADD_SUBJECTIDENTIFIER = "Add_SubjectIdentifier";
	/**
	 * Constant for adding an Subject Locator
	 */
	public static String ADD_SUBJECTLOCATOR = "Add_SubjectLocator";

}
