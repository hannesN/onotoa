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
package de.topicmapslab.tmcledit.diagram.preferences;

/**
 * Constants containing the element and attribute names of the scheme definition xml file.
 * 
 * @author Hannes Niederhausen
 *
 */
public interface SchemesXMLConstants {
	/** root node for schemes */
	public static final String E_SCHEMES = "schemes";
	/** node for a scheme*/
	public static final String E_SCHEME = "scheme";
	/** color node */
	public static final String E_COLOR = "color";
	/** node for the topic font color*/
	public static final String E_TOPIC_FONT_COLOR = "topic_font_color";
	/** node for the topic color */
	public static final String E_TOPIC_COLOR = "topic_color";
	/** node for the secondary topic color */
	public static final String E_TOPIC_COLOR_SEC = "secondary_topic_color";
	
	/** node for the comment font color */
	public static final String E_COMMENT_FONT_COLOR = "comment_font_color";
	/** node for the comment color */
	public static final String E_COMMENT_COLOR = "comment_color";
	/** node for the comment secondary color */
	public static final String E_COMMENT_COLOR_SEC = "secondary_comment_color";
	
	/** name attribute */
	public static final String A_NAME = "name";
	
	/** Color red attribute*/
	public static final String A_R = "r";
	/** Color green attribute*/
	public static final String A_G = "g";
	/** Color blue attribute*/
	public static final String A_B = "b";
	
}
