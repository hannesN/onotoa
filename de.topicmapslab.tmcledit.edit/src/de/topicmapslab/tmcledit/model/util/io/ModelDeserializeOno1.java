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

import static de.topicmapslab.tmcledit.model.util.io.ModelXMLConstantsOno1.A_ABSTRACT;
import static de.topicmapslab.tmcledit.model.util.io.ModelXMLConstantsOno1.A_DATATYPE;
import static de.topicmapslab.tmcledit.model.util.io.ModelXMLConstantsOno1.A_KIND;
import static de.topicmapslab.tmcledit.model.util.io.ModelXMLConstantsOno1.A_UNIQUE;
import static de.topicmapslab.tmcledit.model.util.io.ModelXMLConstantsOno1.E_NAME;
import static de.topicmapslab.tmcledit.model.util.io.ModelXMLConstantsOno1.E_SUBJECT_IDENTIFIER;
import static de.topicmapslab.tmcledit.model.util.io.ModelXMLConstantsOno1.E_SUBJECT_LOCATOR;
import static de.topicmapslab.tmcledit.model.util.io.ModelXMLConstantsOno1.E_TOPIC_TYPE;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

	
	public File deserialize(String filename) {
		java.io.File ioFile = new java.io.File(filename);
		if (ioFile.exists()) {
			try {
	            return deserialize(new FileInputStream(ioFile));
            } catch (FileNotFoundException e) {
	            throw new RuntimeException(e);
            }
		}
	    return null;
	}
	
	public File deserialize(InputStream is) {
		try {
			fac = ModelFactory.eINSTANCE;
			file = fac.createFile();
			file.setTopicMapSchema(fac.createTopicMapSchema());

			ModelIndexer.createInstance(file);
			idx = ModelIndexer.getTopicIndexer();

			byte buffer[] = new byte[500];
			int counter=0;
			
			int tmp; 
			while ((tmp = is.read())!=-1) {
				buffer[counter]=(byte) tmp;
				counter++;
				if (counter==buffer.length) {
					byte tmpBuffer[] = new byte[buffer.length*2];
					System.arraycopy(buffer, 0, tmpBuffer, 0, buffer.length);
					buffer= tmpBuffer;
				}
			}
			
			InputStream is2 = new ByteArrayInputStream(buffer, 0, counter);
			
			
			SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();

			saxParser.parse(is2, new TopicTypeHandler(idx, ttList));
			is2.reset();
			saxParser.parse(is2, new ModelHandler(ttList, file));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return file;
	}

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
