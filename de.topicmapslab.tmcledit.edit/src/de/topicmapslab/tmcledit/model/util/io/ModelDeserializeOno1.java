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

import static de.topicmapslab.tmcledit.model.util.io.ModelXMLConstantsOno1.*;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.OccurrenceType;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;
import de.topicmapslab.tmcledit.model.index.TopicIndexer;

/**
 * @author Hannes Niederhausen
 * 
 */
public class ModelDeserializeOno1 implements ModelDeserializer {

	private ModelFactory fac;
	private File file;
	private TopicIndexer idx;
	private List<TopicType> ttList = new ArrayList<TopicType>(10);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.topicmapslab.tmcledit.model.util.io.ModelDeserializer#deserialize(
	 * java.io.Reader)
	 */
	public File deserialize(InputStream is) {
		try {
			fac = ModelFactory.eINSTANCE;
			file = fac.createFile();
			file.setTopicMapSchema(fac.createTopicMapSchema());

			ModelIndexer.createInstance(file);
			idx = ModelIndexer.getTopicIndexer();

			SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();

			saxParser.parse(is, new TopicTypeHandler(idx, ttList));
			is.reset();
			saxParser.parse(is, new ModelHandler(ttList, file));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return file;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.topicmapslab.tmcledit.model.util.io.ModelDeserializer#getVersionString
	 * ()
	 */
	public String getVersionString() {
		return "1.0.0";
	}

	/**
	 * Class which loads the topictype list, which is used for referencing
	 * 
	 * @author Hannes Niederhausen
	 * 
	 */
	static private class TopicTypeHandler extends DefaultHandler {

		private enum State {
			None, Name, SubjectIdentifier, SubjectLocator
		};

		private TopicType currTopicType = null;
		private State state = State.None;
		private TopicIndexer idx;
		private List<TopicType> ttList;

		public TopicTypeHandler(TopicIndexer idx, List<TopicType> ttList) {
			super();
			this.idx = idx;
			this.ttList = ttList;
		}

		@Override
		public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
			if (E_TOPIC_TYPE.equals(qName)) {
				String attrValue = attributes.getValue(A_KIND);
				KindOfTopicType kind = KindOfTopicType.get(attrValue);
				currTopicType = idx.createTopicType(kind);
				
				if (attributes.getValue(A_ABSTRACT)!=null)
					currTopicType.setAbstract(true);
				
				if (currTopicType.getKind()==KindOfTopicType.OCCURRENCE_TYPE) {
					String attr = attributes.getValue(A_UNIQUE);
					if (attr!=null)
						((OccurrenceType)currTopicType).setUnique(true);
					
					attr = attributes.getValue(A_DATATYPE);
					if (attr!=null)
						((OccurrenceType)currTopicType).setDataType(attr);
					
				}
			}

			if (E_NAME.equals(qName)) {
				if (currTopicType != null)
					state = State.Name;
			}
			if (E_SUBJECT_IDENTIFIER.equals(qName)) {
				if (currTopicType != null)
					state = State.SubjectIdentifier;
			}
			if (E_SUBJECT_LOCATOR.equals(qName)) {
				if (currTopicType != null)
					state = State.SubjectLocator;
			}

		}

		@Override
		public void characters(char[] ch, int start, int length) throws SAXException {
			if (state == State.None)
				return;

			String tmp = new String(ch, start, length);
			if (state == State.Name)
				currTopicType.setName(tmp);
			if (state == State.SubjectIdentifier)
				currTopicType.getIdentifiers().add(tmp);
			if (state == State.SubjectLocator)
				currTopicType.getLocators().add(tmp);

		}

		@Override
		public void endElement(String uri, String localName, String qName) throws SAXException {

			if (E_TOPIC_TYPE.equals(qName)) {
				ttList.add(currTopicType);
				currTopicType = null;
			}
			if (E_SUBJECT_IDENTIFIER.equals(qName)) {
				if (currTopicType != null)
					state = State.None;
			}
			if (E_SUBJECT_LOCATOR.equals(qName)) {
				if (currTopicType != null)
					state = State.None;
			}
			if (E_NAME.equals(qName)) {
				if (currTopicType != null)
					state = State.None;
			}
		}
	}
}
