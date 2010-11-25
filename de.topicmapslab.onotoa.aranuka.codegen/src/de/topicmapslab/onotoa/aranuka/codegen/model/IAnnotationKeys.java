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
package de.topicmapslab.onotoa.aranuka.codegen.model;

/**
 * Interface containing the annotation keys
 * 
 * @author Hannes Niederhausen
 * 
 */
public interface IAnnotationKeys {
	// aranuka
	/** key for generate attirbute*/
	public static final String GENERATE_ATTRIBUTE = "de.topicmapslab.aranuka.generateattribute";
	/** key for the aranuka name annotation */
	public static final String NAME = "de.topicmapslab.aranuka.name";
	/** key for the identifier auto generation */
	public static final String AUTO_GENERATE = "de.topicmapslab.aranuka.autogenerate";

	// genny
	/** annotation key for the category*/
	public static final String CATEGORY = "de.topicmapslab.genny.category";
	
	// kuria
	/** annotation for the hidden flag */
	public static final String HIDDEN = "de.topicmapslab.kuria.hidden";
	/** key for the description annotation*/
	public static final String DESCRIPTION = "de.topicmapslab.kuria.description";
	/** annotation for the read-only flag */
	public static final String READONLY = "de.topicmapslab.kuria.read-only";
	/** key for the label annotation*/
	public static final String LABEL = "de.topicmapslab.kuria.label";
	/** annotation for the hidden flag */
	public static final String LIST_STYLE = "de.topicmapslab.kuria.liststyle";
	/** annotaiton key for the rows values */
	public static final String ROWS = "de.topicmapslab.kuria.rows";
	/** annotation for the type label flag */
	public static final String TYPELABEL = "de.topicmapslab.kuria.typelabel";
	/** annotation for the create new flag */
	public static final String CREATE_NEW = "de.topicmapslab.kuria.createnew";
	/** annotation for the weight of a field*/
	public static final String WEIGHT = "de.topicmapslab.kuria.weight";
}
