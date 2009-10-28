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
package de.topicmapslab.onotoa.net.sync.common.util;

import static de.topicmapslab.onotoa.net.sync.common.util.IServerDataElements.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import de.topicmapslab.onotoa.net.sync.common.ServerData;

/**
 * @author Hannes Niederhausen
 * 
 */
public class ServerDataHandler extends DefaultHandler {

	private  List<ServerData> data;
	
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		
		if (E_SERVERS.equals(qName)) {
			data = new ArrayList<ServerData>();
			return;
		}
		
		if (E_SERVER.equals(qName)) {
			if (data==null)
				throw new SAXException("Invalid element. Expected servers, got server");
			
			ServerData sd = new ServerData();
			String name = attributes.getValue(A_NAME);
			if (name!=null)
				sd.setName(name);
			String port = attributes.getValue(A_PORT);
			if (port!=null)
				sd.setPort(port);
			String host = attributes.getValue(A_HOST);
			if (host!=null)
				sd.setHost(host);
			
			String active = attributes.getValue(A_HOST);
			if (host!=null) {
				sd.setActive(Boolean.parseBoolean(active));
			} else {
				sd.setActive(true);
			}
			
			
			data.add(sd);
		}
		
	}
	
	public List<ServerData> getData() {
		if (data==null)
			return Collections.emptyList();
		return data;
	}
	
	public void parseXML(String xml) throws SAXException, IOException {
			SAXParser parser;
			try {
				parser = SAXParserFactory.newInstance().newSAXParser();
			} catch (ParserConfigurationException e) {
				throw new RuntimeException(e);
			}
			parser.parse(new ByteArrayInputStream(xml.getBytes()), this);
	}
	
}
