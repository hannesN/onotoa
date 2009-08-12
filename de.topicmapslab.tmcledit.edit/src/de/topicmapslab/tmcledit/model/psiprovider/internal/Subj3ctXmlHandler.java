package de.topicmapslab.tmcledit.model.psiprovider.internal;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * 
 */

public class Subj3ctXmlHandler extends DefaultHandler {
	
	private enum State {
		None,
		Subject,
		Name,
		Identifier,
		Description		
	};
	
	private final String SUBJECT ="Subject";
	private final String IDENTIFIER = "Identifier";
	private final String NAME ="NAME_CONSTRAINT";
	private final String DESCRIPTION = "Description";
	
	private Subje3ctResult currResult;
	
	private List<Subje3ctResult> resultList = new ArrayList<Subje3ctResult>();
	private State state = State.None;
	
	
	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
	}
	
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

		if (SUBJECT.equals(qName)) {
			currResult = new Subje3ctResult();
			state = State.Subject;
		} else if (NAME.equals(qName)) {
			state = State.Name;
		} else if (IDENTIFIER.equals(qName)) {
			state = State.Identifier;
		} else if (DESCRIPTION.equals(qName)) {
			state = State.Description;
		}
		
		
		
	}
	
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (SUBJECT.equals(qName)) {
			resultList.add(currResult);
			currResult = null;
		} else if ( (NAME.equals(qName)) || (IDENTIFIER.equals(qName))|| (DESCRIPTION.equals(qName)) ){
			state = State.Subject;
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if (currResult==null)
			return;
		
		String tmp = new String(ch, start, length);
		
		switch (state) {
		case Name:
			currResult.name = tmp;
			return;
		case Description:
			currResult.description = tmp;
			return;
		case Identifier:
			currResult.identifier = tmp;
			return;
		}
	}
	
	public List<Subje3ctResult> getResultList() {
		return resultList;
	}
}