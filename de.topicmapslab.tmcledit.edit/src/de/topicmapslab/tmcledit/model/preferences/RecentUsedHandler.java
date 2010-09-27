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
package de.topicmapslab.tmcledit.model.preferences;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


/**
 * @author Hannes Niederhausen
 *
 */
public class RecentUsedHandler extends DefaultHandler {

	private List<String> list;

	@Override
	public void startDocument() throws SAXException {
		list = new ArrayList<String>();
	}
	
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if (qName.equals("file")) {
			list.add(attributes.getValue("path"));
		}
	}
	
	public List<String> getList() {
		if (list==null)
			return new ArrayList<String>();
		return list;
	}
}
