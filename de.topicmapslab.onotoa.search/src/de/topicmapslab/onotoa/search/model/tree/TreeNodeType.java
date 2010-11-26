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
package de.topicmapslab.onotoa.search.model.tree;

/**
 * Enum class for TreeNode types
 * 
 * @author Hannes Niederhausen
 * 
 */
public enum TreeNodeType {
	/**
	 * No type
	 */
	None,
	/**
	 * User
	 */
	User,
	/**
	 * Type
	 */
	Type,
	/**
	 * SuperType
	 */
	Supertype,
	/**
	 * NameType
	 */
	Nametype,
	/**
	 * OccurrenceT
	 */
	OccurrenceType,
	/**
	 * Association
	 */
	Association,
	/**
	 * Role
	 */
	Role,
	/**
	 * Player
	 */
	Player
}
