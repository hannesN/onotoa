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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @author Hannes Niederhausen
 *
 */
public class ModelDeserializerFactory {

	ModelDeserializer createDeserializer(String filename) {
		File file = new File(filename);
		if (!file.exists())
			throw new IllegalArgumentException();
		try {
	        return createDeserializer(new FileInputStream(file));
        } catch (FileNotFoundException e) {
	        throw new RuntimeException(e);
        }
	}
	
	
	ModelDeserializer createDeserializer(InputStream is) {
		Handler dh = new Handler();
		try {
	        SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
	        
	        
			parser.parse(is, dh);
        	throw new IllegalArgumentException("No Onotoa file format!");

		} catch (FoundException e) {
			return dh.getModelDeserializer();
		} catch (Exception e) {
        	throw new RuntimeException(e);
        }
	}
	
	private class Handler extends DefaultHandler {
		private static final String TMCELEDIT_FILE = "tmceledit:File";
		private static final String FILE = "file";

		private ModelDeserializer modelDeserializer;
		
		@Override
		public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
			if (TMCELEDIT_FILE.equals(qName)) {
				modelDeserializer = new EMFModelDeserialzer();
				throw new FoundException();
			}
			if (FILE.equals(qName)) {
				String version = attributes.getValue("version");
				if ("1.0.0".equals(version)) {
					modelDeserializer = new ModelDeserializeOno1();
					throw new FoundException();
				}
			}
		}
		
		public ModelDeserializer getModelDeserializer() {
	        return modelDeserializer;
        }
	}
	
	private class FoundException extends RuntimeException {
        private static final long serialVersionUID = -5384023154199014612L;
	}
}
