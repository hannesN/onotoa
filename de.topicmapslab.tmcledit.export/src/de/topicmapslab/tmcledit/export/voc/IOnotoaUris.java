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
package de.topicmapslab.tmcledit.export.voc;

/**
 * This interface contains the identifier for the exported diagram information.
 * 
 * @author Hannes Niederhausen
 * @version 1.1.2
 */
public interface IOnotoaUris {

	/**
	 * General prefix for the types.
	 */
	public final static String PREFIX = "http://onotoa.topicmapslab.de/export/";
	
	////////////////// Topic Types ///////////////////////////////
	public final static String DIAGRAM = PREFIX + "diagram";
	
	public final static String NODE = PREFIX + "node";
	
	public final static String EDGE = PREFIX + "edge";
	
	public final static String COMMENT = PREFIX + "comment";
	
	public final static String BENDPOINT = PREFIX + "bendpoint";
	
	///////////////// Occurrence Types /////////////////////////////////
	
	public final static String ID = PREFIX + "id";
	
	public final static String TYPE = PREFIX + "type";
	
	public final static String POSX = PREFIX + "posx";
	
	public final static String POSY = PREFIX + "posy";

	public final static String WIDTH = PREFIX + "width";
	
	public final static String HEIGHT = PREFIX + "height";
	
	public final static String CONTENT = PREFIX + "content";
	
	////////////////// Role Types ///////////////////////////////
	
	public final static String SOURCE = PREFIX + "source";
	
	public final static String TARGET = PREFIX + "target";
	
	public final static String CONNECTOR = PREFIX + "connector";
	
	public final static String CONTAINER = PREFIX + "container";
	
	public final static String CONTAINEE = PREFIX + "containee";
	
	public final static String REFERER = PREFIX + "referer";
	
	public final static String REFEREE = PREFIX + "referee";
	
////////////////// Role Types ///////////////////////////////
	
	public final static String CONTAINS = PREFIX + "contains";
	
	public final static String REFERS = PREFIX + "refers";
	
	public final static String CONNECTS = PREFIX + "connects";
}
