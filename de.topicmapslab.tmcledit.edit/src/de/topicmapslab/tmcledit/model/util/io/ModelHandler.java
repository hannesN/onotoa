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

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import de.topicmapslab.tmcledit.model.AbstractCardinalityContraint;
import de.topicmapslab.tmcledit.model.AbstractRegExpConstraint;
import de.topicmapslab.tmcledit.model.AbstractTypedConstraint;
import de.topicmapslab.tmcledit.model.AssociationType;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.MappingElement;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.NameTypeConstraint;
import de.topicmapslab.tmcledit.model.OccurrenceTypeConstraint;
import de.topicmapslab.tmcledit.model.ReifiableTopicType;
import de.topicmapslab.tmcledit.model.ReifierConstraint;
import de.topicmapslab.tmcledit.model.RoleConstraint;
import de.topicmapslab.tmcledit.model.SubjectIdentifierConstraint;
import de.topicmapslab.tmcledit.model.SubjectLocatorConstraint;
import de.topicmapslab.tmcledit.model.TMCLConstruct;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;

class ModelHandler extends DefaultHandler {

	private enum State {
		NONE, TOPIC_TYPE, NAME_CONSTRAINT, ISA, AKO, OCCURENCE_CONSTRAINT, REIFIER_CONSTRAINT, ROLE_CONSTRAINT, COMMENT, SEE_ALSO, DESCRIPTION
	};

	private TopicType currTopicType = null;
	private ModelHandler.State state = State.NONE;
	private List<TopicType> ttList;
	private final File file;
	private Iterator<TopicType> topicTypeIt;
	private Stack<TMCLConstruct> constructs;
	private ModelFactory fac = ModelFactory.eINSTANCE;

	public ModelHandler(List<TopicType> ttList, File file) {
		super();
		this.ttList = ttList;
		topicTypeIt = ttList.iterator();
		this.file = file;
		constructs = new Stack<TMCLConstruct>();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (E_SCHEMA.equals(qName)) {
			constructs.add(file.getTopicMapSchema());
		}
		if (E_MAPPING_ELEMENT.equals(qName)) {
			addMappingElement(attributes);
		}
		
		if (E_TOPIC_TYPE.equals(qName)) {
			currTopicType = (topicTypeIt.hasNext()) ? topicTypeIt.next() : null;
			constructs.add(currTopicType);
			state = State.TOPIC_TYPE;
		}
		if (E_SUBJECT_IDENTIFIER_CONSTRAINT.equals(qName)) {
			addSubjectIdentifierConstraint(attributes);
		}
		if (E_SUBJECT_LOCATOR_CONSTRAINT.equals(qName)) {
			addSubjectLocatorConstraint(attributes);
		}
		if (E_NAME_CONSTRAINT.equals(qName)) {
			addNameConstraint(attributes);
		}
		
		if (E_OCCURRENCE_CONSTRAINT.equals(qName)) {
			addOccurrenceConstraint(attributes);
		}
		
		if (E_ANNOTATION.equals(qName)) {
			addAnnoation(attributes);
		}
		
		if (E_REIFIER_CONSTRAINT.equals(qName)) {
			addReifierConstraint(attributes);
		}

		if (E_ROLE_CONSTRAINT.equals(qName)) {
			addRoleConstraint(attributes);
		}
		
		if (E_ISA.equals(qName))
			state = State.ISA;
		
		if (E_AKO.equals(qName))
			state = State.AKO;

		if (E_TOPIC_TYPE_REF.equals(qName)) {
			resolveTypeRef(attributes);
		}
	
		if (E_COMMENT.equals(qName)) {
			state = State.COMMENT;
		}
		
		if (E_SEE_ALSO.equals(qName)) {
			state = State.SEE_ALSO;
		}
		
		if (E_DESCRIPTION.equals(qName)) {
			state = State.DESCRIPTION;
		}
	}

	private void addReifierConstraint(Attributes attributes) {
	    if (currTopicType!=null) {
	    	ReifierConstraint rc = fac.createReifierConstraint();
	    	setCardinality(rc, attributes);
	    	constructs.add(rc);
	    	state = State.REIFIER_CONSTRAINT;
	    }
    }

	private void addRoleConstraint(Attributes attributes) {
	    if (currTopicType!=null) {
	    	RoleConstraint rc = fac.createRoleConstraint();
	    	setCardinality(rc, attributes);
	    	constructs.add(rc);
	    	state = State.ROLE_CONSTRAINT;
	    }
    }

	private void addAnnoation(Attributes attributes) {
	    if (constructs.isEmpty())
	    	return;
	    TMCLConstruct construct = constructs.lastElement();
	    construct.getExtension().put(attributes.getValue(A_KEY), attributes.getValue(A_VALUE));
    }

	private void addOccurrenceConstraint(Attributes attributes) {
	    if (currTopicType != null) {
	    	state = State.OCCURENCE_CONSTRAINT;
	    	OccurrenceTypeConstraint otc = fac.createOccurrenceTypeConstraint();
	    	setCardinality(otc, attributes);
	    	constructs.add(otc);
	    }
    }

	private void addNameConstraint(Attributes attributes) {
	    if (currTopicType != null) {
	    	state = State.NAME_CONSTRAINT;
	    	NameTypeConstraint ntc = fac.createNameTypeConstraint();
	    	setCardinality(ntc, attributes);
	    	constructs.add(ntc);
	    }
    }

	private void addSubjectLocatorConstraint(Attributes attributes) {
	    if (currTopicType != null) {
	    	SubjectLocatorConstraint slc = (SubjectLocatorConstraint) createIdentityConstraint(attributes,
	    	        ModelPackage.TOPIC_TYPE__SUBJECT_LOCATOR_CONSTRAINT);
	    	constructs.add(slc);
	    	currTopicType.getSubjectLocatorConstraint().add(slc);
	    }
    }

	private void addSubjectIdentifierConstraint(Attributes attributes) {
	    if (currTopicType != null) {
	    	SubjectIdentifierConstraint sic = (SubjectIdentifierConstraint) createIdentityConstraint(attributes,
	    	        ModelPackage.TOPIC_TYPE__SUBJECT_IDENTIFIER_CONSTRAINTS);
	    	constructs.add(currTopicType);
	    	currTopicType.getSubjectIdentifierConstraints().add(sic);
	    }
    }

	private void addMappingElement(Attributes attributes) {
	    String key = attributes.getValue(A_KEY);
	    String value = attributes.getValue(A_VALUE);
	    
	    if (constructs.isEmpty())
	    	return;
	    TMCLConstruct c = constructs.lastElement();
	    if (c instanceof TopicMapSchema) {
	    	MappingElement me = fac.createMappingElement();
	    	me.setKey(key);
	    	me.setValue(value);
	    	((TopicMapSchema) c).getMappings().add(me);
	    }
    }

	private void resolveTypeRef(Attributes attributes) {
	    String ref = attributes.getValue(A_REF);
	    if (ref != null) {
	    	int idx = ref.indexOf(".");
	    	int topicIdx = Integer.parseInt(ref.substring(idx + 1));
	    	TopicType tt = ttList.get(topicIdx);

	    	if (constructs.isEmpty())
	    		return;
	    	
	    	TMCLConstruct construct = constructs.lastElement();
	    	if (construct instanceof AbstractTypedConstraint) {
	    		((AbstractTypedConstraint) construct).setType(tt);
	    	} else if (construct instanceof TopicType) {
	    		if (state == State.ISA) {
	    			((TopicType) construct).getIsa().add(tt);
	    		} else if (state == State.AKO) {
	    			((TopicType) construct).getAko().add(tt);
	    		}
	    	}
	    }
    }

	private TMCLConstruct createIdentityConstraint(Attributes attributes, int featureID) {
		AbstractRegExpConstraint c = null;
		if (featureID == ModelPackage.TOPIC_TYPE__SUBJECT_IDENTIFIER_CONSTRAINTS) {
			c = fac.createSubjectIdentifierConstraint();
		} else {
			c = fac.createSubjectLocatorConstraint();
		}
		String regExp = attributes.getValue(A_REG_EXP);
		if (regExp != null)
			c.setRegexp(regExp);

		setCardinality((AbstractCardinalityContraint) c, attributes);

		return c;
	}

	private void setCardinality(AbstractCardinalityContraint acc, Attributes attributes) {
		String attr = attributes.getValue(A_CARD_MIN);
		if (attr != null)
			acc.setCardMin(attr);
		attr = attributes.getValue(A_CARD_MAX);
		if (attr != null)
			acc.setCardMax(attr);
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (state == State.NONE)
			return;
		
		if (constructs.isEmpty())
			return;
		
		String tmp = new String(ch, start, length);
		TMCLConstruct c = constructs.lastElement();
		switch (state) {
        case COMMENT:
        	c.setComment(tmp);
        	return;
        case SEE_ALSO:
        	c.setSee_also(tmp);
        	return;
        case DESCRIPTION:
        	c.setDescription(tmp);
        default:
	        return;
        }
		
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {

		if (E_TOPIC_TYPE.equals(qName)) {
			file.getTopicMapSchema().getTopicTypes().add(currTopicType);
			currTopicType = null;
			constructs.pop();
			state = State.NONE;
		}
		if (E_SCHEMA.equals(qName)) {
			constructs.pop();
		}
		if ((E_SUBJECT_IDENTIFIER_CONSTRAINT.equals(qName)) || (E_SUBJECT_LOCATOR_CONSTRAINT.equals(qName))) {
			if (currTopicType != null) {
				constructs.pop();
				state = State.TOPIC_TYPE;
			}
		}
		
		if ( (state==State.COMMENT)
		   || (state==State.SEE_ALSO)
		   || (state==State.DESCRIPTION)) {
			state = State.NONE;
		}
		
		if (E_ISA.equals(qName))
			state = State.TOPIC_TYPE;
		
		if (E_AKO.equals(qName))
			state = State.TOPIC_TYPE;

		if (E_NAME_CONSTRAINT.equals(qName)) {
			if (currTopicType != null) {
				currTopicType.getNameContraints().add((NameTypeConstraint) constructs.pop());
				state = State.TOPIC_TYPE;
			}
		}
		if (E_REIFIER_CONSTRAINT.equals(qName)) {
			((ReifiableTopicType) currTopicType).setReifierConstraint((ReifierConstraint) constructs.pop());
			state = State.TOPIC_TYPE;
		}
		
		if (E_ROLE_CONSTRAINT.equals(qName)) {
			((AssociationType) currTopicType).getRoles().add((RoleConstraint) constructs.pop());
			state = State.TOPIC_TYPE;
		}
		
		if (E_OCCURRENCE_CONSTRAINT.equals(qName)) {
			if (currTopicType != null) {
				currTopicType.getOccurrenceConstraints().add((OccurrenceTypeConstraint) constructs.pop());
				state = State.TOPIC_TYPE;
			}
		}
	}
}