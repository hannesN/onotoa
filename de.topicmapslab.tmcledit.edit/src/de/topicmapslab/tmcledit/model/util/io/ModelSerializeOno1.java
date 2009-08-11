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
package de.topicmapslab.tmcledit.model.util.io;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.TMCLConstruct;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;

/**
 * @author Hannes Niederhausen
 *
 */
public class ModelSerializeOno1 implements ModelSerializer {

	private Document document;
	private File file;

	public String getVersionString() {
		return "1.0.0";
	}

	public String serialize(File file) {
		
		try {
	        document = DocumentBuilderFactory.newInstance().newDocumentBuilder().getDOMImplementation().createDocument("onotoa.topicmapslab.de", "ono", null);
	        this.file = file;
	        
	        Element node = createFileNode();
		
	        document.appendChild(node);
	        return document.toString();
		
		
		} catch (Exception e) {
        	throw new RuntimeException(e);
        }
	}

	private Element createFileNode() {
		Element node = document.createElement("file");
		node.setAttribute("version", getVersionString());
		
		TopicMapSchema schema = file.getTopicMapSchema();
		
		Element schemaNode = document.createElement("schema");
		schemaNode.setAttribute("baseLocator", schema.getBaseLocator());
		addTMCLConstructElements(schema, schemaNode);
		
		
	    return node;
    }
	
	private void addTMCLConstructElements(TMCLConstruct construct, Element node) {
		String tmp = construct.getComment();
		if ( (tmp!=null) && (tmp.length()>0)) {
			Element comm = document.createElement("comment");
			comm.setTextContent(tmp);
			node.appendChild(comm);
		}
		
		tmp = construct.getDescription();
		if ( (tmp!=null) && (tmp.length()>0)) {
			Element desc = document.createElement("description");
			desc.setTextContent(tmp);
			node.appendChild(desc);
		}
		
		tmp = construct.getSee_also();
		if ( (tmp!=null) && (tmp.length()>0)) {
			Element see = document.createElement("see-also");
			see.setTextContent(tmp);
			node.appendChild(see);
		}
		
	}

}
