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

import static de.topicmapslab.tmcledit.diagram.preferences.SchemesXMLConstants.A_B;
import static de.topicmapslab.tmcledit.diagram.preferences.SchemesXMLConstants.A_G;
import static de.topicmapslab.tmcledit.diagram.preferences.SchemesXMLConstants.A_NAME;
import static de.topicmapslab.tmcledit.diagram.preferences.SchemesXMLConstants.A_R;
import static de.topicmapslab.tmcledit.diagram.preferences.SchemesXMLConstants.E_COLOR;
import static de.topicmapslab.tmcledit.diagram.preferences.SchemesXMLConstants.E_COMMENT_COLOR;
import static de.topicmapslab.tmcledit.diagram.preferences.SchemesXMLConstants.E_COMMENT_COLOR_SEC;
import static de.topicmapslab.tmcledit.diagram.preferences.SchemesXMLConstants.E_SCHEME;
import static de.topicmapslab.tmcledit.diagram.preferences.SchemesXMLConstants.E_SCHEMES;
import static de.topicmapslab.tmcledit.diagram.preferences.SchemesXMLConstants.E_TOPIC_COLOR;
import static de.topicmapslab.tmcledit.diagram.preferences.SchemesXMLConstants.E_TOPIC_COLOR_SEC;
import static de.topicmapslab.tmcledit.diagram.preferences.SchemesXMLConstants.E_TOPIC_FONT_COLOR;
import static de.topicmapslab.tmcledit.diagram.preferences.SchemesXMLConstants.E_COMMENT_FONT_COLOR;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.eclipse.core.runtime.Status;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.ext.DefaultHandler2;

import de.topicmapslab.tmcledit.diagram.DiagramActivator;
import de.topicmapslab.tmcledit.diagram.preferences.ColorScheme.ColorDefinition;

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
		TOPIC_FONT_COLOR,
		TOPIC_COLOR_SEC,
		COMMENT_COLOR,
		COMMENT_COLOR_SEC,
		COMMENT_FONT_COLOR
	}
	
	private List<ColorScheme> schemaList;
	private ColorScheme currColorSchema;
	private State state;
	private boolean docStart;
	
	/**
	 * 
	 * @return the read schema list
	 */
	public List<ColorScheme> getSchemaList() {
		return schemaList;
	}

	/**
	 * Gets an input stream which should be a serialized color scheme list
	 * @param is the input stream to parse
	 * @return a list of color schemes
	 */
	public static List<ColorScheme> parseSchemeList(InputStream is) {
		try {
			SchemesXMLHandler handler = new SchemesXMLHandler();
			
			SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
			saxParser.parse(is, handler);
			
			return handler.getSchemaList();
		} catch (SAXException saxE) { 
			return null;		
		} catch (Exception e) {
			DiagramActivator.getDefault().getLog().log(new Status(Status.ERROR, DiagramActivator.PLUGIN_ID, "Coudln't parse scheme properties", e));
			return Collections.emptyList();
		}
	}
	
	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public void startDocument() throws SAXException {
		docStart = true;
	}
	
	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

		if ( (docStart) && (!E_SCHEMES.equals(qName)) ) {
			throw new SAXException("Invalid xml file!");
		} 
		
		if (E_SCHEMES.equals(qName)) {
			schemaList = new ArrayList<ColorScheme>();
			docStart = false;
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
		
		if (E_TOPIC_FONT_COLOR.equals(qName))
			state = State.TOPIC_FONT_COLOR;
		
		if (E_COMMENT_FONT_COLOR.equals(qName))
			state = State.COMMENT_FONT_COLOR;
		
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
			case TOPIC_FONT_COLOR:
				currColorSchema.setTopicFontColor(def);				
				break;
			case COMMENT_FONT_COLOR:
				currColorSchema.setCommentFontColor(def);				
				break;
			}
		}
	}
	
	/**
	 * 
	 * {@inheritDoc}
	 */
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
			||(E_TOPIC_COLOR_SEC.equals(qName)) 
			||(E_TOPIC_FONT_COLOR.equals(qName))
			||(E_COMMENT_FONT_COLOR.equals(qName)) ) {
				state = State.NONE;
		}
	}
	
	
}
