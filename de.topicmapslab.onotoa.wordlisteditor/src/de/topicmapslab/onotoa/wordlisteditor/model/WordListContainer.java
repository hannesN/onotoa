/*******************************************************************************
 * Copyright (c) 2008-2011 Topic Maps Lab and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *      Hannes Niederhausen - initial API and implementation
 ******************************************************************************/
package de.topicmapslab.onotoa.wordlisteditor.model;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import de.topicmapslab.onotoa.wordlisteditor.Activator;
import de.topicmapslab.tmcledit.model.KindOfTopicType;

/**
 * Container for the words
 * 
 * @author Hannes Niederhausen
 *
 */
public class WordListContainer implements Collection<Word>{

	private List<Word> wordList;
	
	/**
     * 
     */
    public WordListContainer() {
    	wordList = new ArrayList<Word>();
    }

	/**
     * @return
     * @see java.util.List#size()
     */
    public int size() {
	    return wordList.size();
    }

	/**
     * @return
     * @see java.util.List#isEmpty()
     */
    public boolean isEmpty() {
	    return wordList.isEmpty();
    }

	/**
     * @param o
     * @return
     * @see java.util.List#contains(java.lang.Object)
     */
    public boolean contains(Object o) {
	    return wordList.contains(o);
    }

	/**
     * @return
     * @see java.util.List#iterator()
     */
    public Iterator<Word> iterator() {
	    return wordList.iterator();
    }

	/**
     * @return
     * @see java.util.List#toArray()
     */
    public Object[] toArray() {
	    return wordList.toArray();
    }

	/**
     * @param <T>
     * @param a
     * @return
     * @see java.util.List#toArray(T[])
     */
    public <T> T[] toArray(T[] a) {
	    return wordList.toArray(a);
    }

	/**
     * @param e
     * @return
     * @see java.util.List#add(java.lang.Object)
     */
    public boolean add(Word e) {
	    return wordList.add(e);
    }

	/**
     * @param o
     * @return
     * @see java.util.List#remove(java.lang.Object)
     */
    public boolean remove(Object o) {
	    return wordList.remove(o);
    }

	/**
     * @param c
     * @return
     * @see java.util.List#containsAll(java.util.Collection)
     */
    public boolean containsAll(Collection<?> c) {
	    return wordList.containsAll(c);
    }

	/**
     * @param c
     * @return
     * @see java.util.List#addAll(java.util.Collection)
     */
    public boolean addAll(Collection<? extends Word> c) {
	    return wordList.addAll(c);
    }

	/**
     * @param index
     * @param c
     * @return
     * @see java.util.List#addAll(int, java.util.Collection)
     */
    public boolean addAll(int index, Collection<? extends Word> c) {
	    return wordList.addAll(index, c);
    }

	/**
     * @param c
     * @return
     * @see java.util.List#removeAll(java.util.Collection)
     */
    public boolean removeAll(Collection<?> c) {
	    return wordList.removeAll(c);
    }

	/**
     * @param c
     * @return
     * @see java.util.List#retainAll(java.util.Collection)
     */
    public boolean retainAll(Collection<?> c) {
	    return wordList.retainAll(c);
    }

	/**
     * 
     * @see java.util.List#clear()
     */
    public void clear() {
	    wordList.clear();
    }

	/**
     * @param index
     * @return
     * @see java.util.List#get(int)
     */
    public Word get(int index) {
	    return wordList.get(index);
    }

	/**
     * @param index
     * @param element
     * @return
     * @see java.util.List#set(int, java.lang.Object)
     */
    public Word set(int index, Word element) {
	    return wordList.set(index, element);
    }

	/**
     * @param index
     * @param element
     * @see java.util.List#add(int, java.lang.Object)
     */
    public void add(int index, Word element) {
	    wordList.add(index, element);
    }

	/**
     * @param index
     * @return
     * @see java.util.List#remove(int)
     */
    public Word remove(int index) {
	    return wordList.remove(index);
    }

	/**
     * @param o
     * @return
     * @see java.util.List#indexOf(java.lang.Object)
     */
    public int indexOf(Object o) {
	    return wordList.indexOf(o);
    }

	/**
     * @param o
     * @return
     * @see java.util.List#lastIndexOf(java.lang.Object)
     */
    public int lastIndexOf(Object o) {
	    return wordList.lastIndexOf(o);
    }

	/**
     * @return
     * @see java.util.List#listIterator()
     */
    public ListIterator<Word> listIterator() {
	    return wordList.listIterator();
    }

	/**
     * @param index
     * @return
     * @see java.util.List#listIterator(int)
     */
    public ListIterator<Word> listIterator(int index) {
	    return wordList.listIterator(index);
    }

	/**
     * @param fromIndex
     * @param toIndex
     * @return
     * @see java.util.List#subList(int, int)
     */
    public List<Word> subList(int fromIndex, int toIndex) {
	    return wordList.subList(fromIndex, toIndex);
    }
	
    /**
     * Serializes the {@link WordListContainer} to xml.
     * @return the XML serialization of the container
     */
	public String toXML() {
		try {
	        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().getDOMImplementation().createDocument(
	                "onotoa.topicmapslab.de", "words", null);

	        for (Word w : wordList) {
	        	Element e = doc.createElement("word");
	        	e.setAttribute("word", w.getWord());
	        	e.setAttribute("type", w.getType().getName());
	        	doc.getDocumentElement().appendChild(e);
	        }
	        
	        TransformerFactory tf = TransformerFactory.newInstance();
        	tf.setAttribute("indent-number", Integer.toString(4));
	        
	        
	        DOMSource domSource = new DOMSource(doc);
	        StringWriter writer = new StringWriter();
	        StreamResult streamResult = new StreamResult(writer);
	        
	        Transformer serializer = tf.newTransformer();
	        serializer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	        
	        serializer.transform(domSource, streamResult);
	        
	        return writer.getBuffer().toString();
        } catch (Exception e) {
	        Activator.logError(e);
	        throw new RuntimeException(e);
        }
	}
	
	/**
     * @return the wordList
     */
    public List<Word> getWordList() {
	    return wordList;
    }
	
	/**
	 * Parses the given XML and creates a new word list contianer
	 * 
	 * @param xml
	 * @return
	 */
	public static WordListContainer parseXML(String xml) {
		try {
	        final WordListContainer wordListContainer = new WordListContainer();
	        SAXParserFactory fac = SAXParserFactory.newInstance();
	        SAXParser parser;

	        parser = fac.newSAXParser();
	        parser.parse(new ByteArrayInputStream(xml.getBytes()),
	        		new DefaultHandler() {
	        	@Override
	        	public void startElement(String uri, String localName, String qName, Attributes attributes)
	        	        throws SAXException {
	        		if ("word".equals(qName)) {
	        			Word w = new Word();
	        			String tmp = attributes.getValue("word");
	        			w.setWord(tmp);
	        			tmp = attributes.getValue("type");
	        			w.setType(KindOfTopicType.getByName(tmp));
	        			
	        			wordListContainer.add(w);
	        		}
	        	}
	        });
	        return wordListContainer;
		} catch (Exception e) {
	        Activator.logError(e);
	        throw new RuntimeException(e);
        }
		
	}
	
}
