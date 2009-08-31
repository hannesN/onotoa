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


import java.io.StringWriter;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import de.topicmapslab.tmcledit.diagram.preferences.ColorScheme.ColorDefinition;

/**
 * @author Hannes Niederhausen
 * 
 */
public class SchemeXMLBuilder {

	private Document doc;

	public String buildSchemeXML(List<ColorScheme> list) {
		return buildSchemeXML(list, false);
	}
	
	public String buildSchemeXML(List<ColorScheme> list, boolean indent) {
		try {

			DocumentBuilder documentBuilder = DocumentBuilderFactory
					.newInstance().newDocumentBuilder();

			doc = documentBuilder.getDOMImplementation().createDocument("onotoa.topicmapslab.de", E_SCHEMES, null);

			Element schemas = doc.getDocumentElement();
			for (ColorScheme cs : list) {
				if ("default".equals(cs.getName()))
					continue;

				Element e = getSchemeElement(cs);
				schemas.appendChild(e);
			}

			DOMSource domSource = new DOMSource(doc);
			StringWriter writer = new StringWriter();
			StreamResult streamResult = new StreamResult(writer);

			TransformerFactory tf = TransformerFactory.newInstance();
			tf.setAttribute("indent-number", Integer.toString(4));

			Transformer serializer = tf.newTransformer();
			serializer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			if (indent)
				serializer.setOutputProperty(OutputKeys.INDENT, "YES");
			else
				serializer.setOutputProperty(OutputKeys.INDENT, "NO");

			serializer.transform(domSource, streamResult);
			
			return writer.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private Element getSchemeElement(ColorScheme cs) {
		Element e = doc.createElement(E_SCHEME);

		e.setAttribute(A_NAME, cs.getName());

		createColorDefinition(cs.getCommentColor(), E_COMMENT_COLOR, e);
		createColorDefinition(cs.getTopicFontColor(), E_TOPIC_FONT_COLOR, e);
		if (cs.getCommentSecondaryColor() != null)
			createColorDefinition(cs.getCommentSecondaryColor(),
					E_COMMENT_COLOR_SEC, e);

		createColorDefinition(cs.getTopicColor(), E_TOPIC_COLOR, e);
		createColorDefinition(cs.getCommentFontColor(), E_COMMENT_FONT_COLOR, e);
		if (cs.getTopicSecondaryColor() != null)
			createColorDefinition(cs.getTopicSecondaryColor(),
					E_TOPIC_COLOR_SEC, e);

		return e;
	}

	private void createColorDefinition(ColorDefinition cd, String elementName,
			Element parent) {
		Element e = doc.createElement(elementName);

		Element color = doc.createElement(E_COLOR);
		color.setAttribute(A_R, Integer.toString(cd.r));
		color.setAttribute(A_G, Integer.toString(cd.g));
		color.setAttribute(A_B, Integer.toString(cd.b));
		e.appendChild(color);

		parent.appendChild(e);
	}

}
