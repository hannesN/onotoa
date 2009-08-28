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

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.ext.DefaultHandler2;

import de.topicmapslab.tmcledit.diagram.preferences.ColorScheme.ColorDefinition;

import static de.topicmapslab.tmcledit.diagram.preferences.SchemesXMLConstants.*;

/**
 * SAX Handler to parse Colorscheme xml properties
 * 
 * @author Hannes Niederhausen
 *
 */
public class SchemesXMLHandler extends DefaultHandler2 {

	enum State {
		NONE,
		TOPIC_COLOR,
		TOPIC_COLOR_SEC,
		COMMENT_COLOR,
		COMMENT_COLOR_SEC
	}
	
	private List<ColorScheme> schemaList;
	private ColorScheme currColorSchema;
	private State state;
	
	public List<ColorScheme> getSchemaList() {
		return schemaList;
	}
	
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if (E_SCHEMES.equals(qName)) {
			schemaList = new ArrayList<ColorScheme>();
			return;
		}
		
		if (E_SCHEME.equals(qName)) {
			currColorSchema = new ColorScheme();
			String name = attributes.getValue(A_NAME);
			currColorSchema.setName(name);
		}
		
		if (E_COMMENT_COLOR.equals(qName))
			state = State.COMMENT_COLOR;
		
		if (E_COMMENT_COLOR_SEC.equals(qName))
			state = State.COMMENT_COLOR_SEC;
		
		if (E_TOPIC_COLOR.equals(qName))
			state = State.TOPIC_COLOR;
		
		if (E_TOPIC_COLOR_SEC.equals(qName))
			state = State.TOPIC_COLOR_SEC;
		
		if (E_COLOR.equals(qName)) {
			int r = Integer.parseInt(attributes.getValue(A_R));
			int g = Integer.parseInt(attributes.getValue(A_G));
			int b = Integer.parseInt(attributes.getValue(A_B));
			
			ColorDefinition def = new ColorDefinition(r, g, b);
			
			switch (state) {
			case COMMENT_COLOR:
				currColorSchema.setCommentColor(def);				
				break;
			case COMMENT_COLOR_SEC:
				currColorSchema.setCommentSecondaryColor(def);				
				break;
			case TOPIC_COLOR:
				currColorSchema.setTopicColor(def);				
				break;
			case TOPIC_COLOR_SEC:
				currColorSchema.setTopicSecondaryColor(def);				
				break;
			}
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (E_SCHEME.equals(qName)) {
			schemaList.add(currColorSchema);
			currColorSchema=null;
			return;
		}
		
		if ((E_COMMENT_COLOR.equals(qName))
			||(E_COMMENT_COLOR_SEC.equals(qName))
			||(E_TOPIC_COLOR.equals(qName))
			||(E_TOPIC_COLOR_SEC.equals(qName)) ) {
			state = State.NONE;
		}
	}
	
	
}
