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
import static de.topicmapslab.tmcledit.model.util.io.ModelXMLConstantsOno1.A_CARD_MIN;
import static de.topicmapslab.tmcledit.model.util.io.ModelXMLConstantsOno1.A_KEY;
import static de.topicmapslab.tmcledit.model.util.io.ModelXMLConstantsOno1.A_NAME;
import static de.topicmapslab.tmcledit.model.util.io.ModelXMLConstantsOno1.A_POS_X;
import static de.topicmapslab.tmcledit.model.util.io.ModelXMLConstantsOno1.A_POS_Y;
import static de.topicmapslab.tmcledit.model.util.io.ModelXMLConstantsOno1.A_REF;
import static de.topicmapslab.tmcledit.model.util.io.ModelXMLConstantsOno1.A_REG_EXP;
import static de.topicmapslab.tmcledit.model.util.io.ModelXMLConstantsOno1.A_SOURCE;
import static de.topicmapslab.tmcledit.model.util.io.ModelXMLConstantsOno1.A_TARGET;
import static de.topicmapslab.tmcledit.model.util.io.ModelXMLConstantsOno1.A_TOPIC_ROLE_REF;
import static de.topicmapslab.tmcledit.model.util.io.ModelXMLConstantsOno1.A_TYPE;
import static de.topicmapslab.tmcledit.model.util.io.ModelXMLConstantsOno1.A_VALUE;
import static de.topicmapslab.tmcledit.model.util.io.ModelXMLConstantsOno1.E_AKO;
import static de.topicmapslab.tmcledit.model.util.io.ModelXMLConstantsOno1.E_ANNOTATION;
import static de.topicmapslab.tmcledit.model.util.io.ModelXMLConstantsOno1.E_ASSOCIATION_CONSTRAINT;
import static de.topicmapslab.tmcledit.model.util.io.ModelXMLConstantsOno1.E_ASSOC_CONSTRAINT_REF;
import static de.topicmapslab.tmcledit.model.util.io.ModelXMLConstantsOno1.E_BENDPOINT;
import static de.topicmapslab.tmcledit.model.util.io.ModelXMLConstantsOno1.E_COMMENT;
import static de.topicmapslab.tmcledit.model.util.io.ModelXMLConstantsOno1.E_DESCRIPTION;
import static de.topicmapslab.tmcledit.model.util.io.ModelXMLConstantsOno1.E_DIAGRAM;
import static de.topicmapslab.tmcledit.model.util.io.ModelXMLConstantsOno1.E_EDGE;
import static de.topicmapslab.tmcledit.model.util.io.ModelXMLConstantsOno1.E_ISA;
import static de.topicmapslab.tmcledit.model.util.io.ModelXMLConstantsOno1.E_LABEL_POSITION;
import static de.topicmapslab.tmcledit.model.util.io.ModelXMLConstantsOno1.E_MAPPING_ELEMENT;
import static de.topicmapslab.tmcledit.model.util.io.ModelXMLConstantsOno1.E_NAME_CONSTRAINT;
import static de.topicmapslab.tmcledit.model.util.io.ModelXMLConstantsOno1.E_NODE;
import static de.topicmapslab.tmcledit.model.util.io.ModelXMLConstantsOno1.E_OCCURRENCE_CONSTRAINT;
import static de.topicmapslab.tmcledit.model.util.io.ModelXMLConstantsOno1.E_OTHER_PLAYER;
import static de.topicmapslab.tmcledit.model.util.io.ModelXMLConstantsOno1.E_OTHER_ROLE;
import static de.topicmapslab.tmcledit.model.util.io.ModelXMLConstantsOno1.E_PLAYER;
import static de.topicmapslab.tmcledit.model.util.io.ModelXMLConstantsOno1.E_REIFIER_CONSTRAINT;
import static de.topicmapslab.tmcledit.model.util.io.ModelXMLConstantsOno1.E_ROLE;
import static de.topicmapslab.tmcledit.model.util.io.ModelXMLConstantsOno1.E_ROLE_COMBINATION_CONSTRAINT;
import static de.topicmapslab.tmcledit.model.util.io.ModelXMLConstantsOno1.E_ROLE_CONSTRAINT;
import static de.topicmapslab.tmcledit.model.util.io.ModelXMLConstantsOno1.E_ROLE_CONSTRAINT_REFERENCE;
import static de.topicmapslab.tmcledit.model.util.io.ModelXMLConstantsOno1.E_SCHEMA;
import static de.topicmapslab.tmcledit.model.util.io.ModelXMLConstantsOno1.E_SCOPE_CONSTRAINT;
import static de.topicmapslab.tmcledit.model.util.io.ModelXMLConstantsOno1.E_SEE_ALSO;
import static de.topicmapslab.tmcledit.model.util.io.ModelXMLConstantsOno1.E_SUBJECT_IDENTIFIER_CONSTRAINT;
import static de.topicmapslab.tmcledit.model.util.io.ModelXMLConstantsOno1.E_SUBJECT_LOCATOR_CONSTRAINT;
import static de.topicmapslab.tmcledit.model.util.io.ModelXMLConstantsOno1.E_TOPIC_ROLE_CONSTRAINT;
import static de.topicmapslab.tmcledit.model.util.io.ModelXMLConstantsOno1.E_TOPIC_TYPE;
import static de.topicmapslab.tmcledit.model.util.io.ModelXMLConstantsOno1.E_TOPIC_TYPE_REF;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import org.eclipse.emf.ecore.EObject;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import de.topicmapslab.tmcledit.model.AbstractCardinalityContraint;
import de.topicmapslab.tmcledit.model.AbstractRegExpConstraint;
import de.topicmapslab.tmcledit.model.AbstractRegExpTopicType;
import de.topicmapslab.tmcledit.model.AbstractTypedConstraint;
import de.topicmapslab.tmcledit.model.Annotation;
import de.topicmapslab.tmcledit.model.AssociationNode;
import de.topicmapslab.tmcledit.model.AssociationType;
import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.Bendpoint;
import de.topicmapslab.tmcledit.model.Comment;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.Edge;
import de.topicmapslab.tmcledit.model.EdgeType;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.LabelPos;
import de.topicmapslab.tmcledit.model.MappingElement;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.NameTypeConstraint;
import de.topicmapslab.tmcledit.model.Node;
import de.topicmapslab.tmcledit.model.OccurrenceTypeConstraint;
import de.topicmapslab.tmcledit.model.ReifiableTopicType;
import de.topicmapslab.tmcledit.model.ReifierConstraint;
import de.topicmapslab.tmcledit.model.RoleCombinationConstraint;
import de.topicmapslab.tmcledit.model.RoleConstraint;
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;
import de.topicmapslab.tmcledit.model.RoleType;
import de.topicmapslab.tmcledit.model.ScopeConstraint;
import de.topicmapslab.tmcledit.model.ScopedTopicType;
import de.topicmapslab.tmcledit.model.SubjectIdentifierConstraint;
import de.topicmapslab.tmcledit.model.SubjectLocatorConstraint;
import de.topicmapslab.tmcledit.model.TMCLConstruct;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicReifiesConstraint;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.TypeNode;

class ModelHandler extends DefaultHandler {

	private enum State {
		NONE, TOPIC_TYPE, NAME_CONSTRAINT, 
		ISA, AKO, OCCURENCE_CONSTRAINT, REIFIER_CONSTRAINT, TOPIC_REIFIES_CONSTRAINT, 
		ROLE_CONSTRAINT, COMMENT, SEE_ALSO, DESCRIPTION, ASSOCIATION_CONSTRAINT, 
		PLAYER, TOPIC_ROLE_CONSTRAINT, TYPE_NODE, ASSOCIATION_NODE, 
		ROLE_COMBINATION, OTHER_PLAYER, OTHER_ROLE, ROLE, DIAGRAM_COMMENT, OVERLAP
	};

	private TopicType currTopicType = null;
	private ModelHandler.State state = State.NONE;
	private List<TopicType> ttList;
	private final File file;
	private Iterator<TopicType> topicTypeIt;
	private Stack<EObject> constructs;
	private ModelFactory fac = ModelFactory.eINSTANCE;

	public ModelHandler(List<TopicType> ttList, File file) {
		super();
		this.ttList = ttList;
		topicTypeIt = ttList.iterator();
		this.file = file;
		constructs = new Stack<EObject>();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (E_SCHEMA.equals(qName)) {
			constructs.push(file.getTopicMapSchema());
			String tmp = attributes.getValue(A_NAME);
			if (tmp!=null)
				file.getTopicMapSchema().setName(tmp);
			
			tmp = attributes.getValue(A_BASE_LOCATOR);
			if (tmp!=null)
				file.getTopicMapSchema().setBaseLocator(tmp);
			
		}
		if (E_MAPPING_ELEMENT.equals(qName)) {
			addMappingElement(attributes);
		}
		
		if (E_TOPIC_TYPE.equals(qName)) {
			currTopicType = (topicTypeIt.hasNext()) ? topicTypeIt.next() : null;
			constructs.push(currTopicType);
			if (currTopicType instanceof AbstractRegExpTopicType) {
				String regExp = attributes.getValue(A_REG_EXP);
				if (regExp!=null)
					((AbstractRegExpTopicType) currTopicType).setRegExp(regExp);
			}
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
		
		if (E_TOPIC_REIFIES_CONSTRAINT.equals(qName)) {
			addTopicReifiesConstraint(attributes);
		}

		if (E_ROLE_CONSTRAINT.equals(qName)) {
			addRoleConstraint(attributes);
		}
		
		if (E_ISA.equals(qName))
			state = State.ISA;
		
		if (E_AKO.equals(qName))
			state = State.AKO;

		if (E_TOPIC_TYPE_REF.equals(qName)) {
			addTopicTypeReference(attributes);
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
		
		if (E_ROLE_COMBINATION_CONSTRAINT.equals(qName)) {
			RoleCombinationConstraint rcc = fac.createRoleCombinationConstraint();
			constructs.push(rcc);
		}
		
		if (E_ASSOCIATION_CONSTRAINT.equals(qName)) {
			state = State.ASSOCIATION_CONSTRAINT;
			AssociationTypeConstraint atc = fac.createAssociationTypeConstraint();
			constructs.push(atc);
		}
		
		if (E_TOPIC_ROLE_CONSTRAINT.equals(qName)) {
			state = State.TOPIC_ROLE_CONSTRAINT;
			RolePlayerConstraint rpc = fac.createRolePlayerConstraint();
			setCardinality(rpc, attributes);
			constructs.push(rpc);			
		}
		
		if (E_ROLE.equals(qName)) {
			state = State.ROLE;
		}
		
		if (E_PLAYER.equals(qName)) {
			state = State.PLAYER;
		}
		
		if (E_OTHER_ROLE.equals(qName)) {
			state = State.OTHER_ROLE;
		}
		
		if (E_OTHER_PLAYER.equals(qName)) {
			state = State.OTHER_PLAYER;
		}
		
		if (E_OVERLAP.equals(qName)) {
			state = State.OVERLAP;
		}
		

		if (E_ROLE_CONSTRAINT_REFERENCE.equals(qName)) {
			addRoleConstrainReference(attributes);
		}
		
		if (E_DIAGRAM.equals(qName)) {
			Diagram d = fac.createDiagram();
			String name = attributes.getValue(A_NAME);
			if (name==null)
				return;
			d.setName(name);
				
			constructs.push(d);
		}
		
		if (E_NODE.equals(qName)) {
			addNode(attributes);
		}
		
		if (E_EDGE.equals(qName)) {
			addEdge(attributes);
		}
		
		if (E_BENDPOINT.equals(qName)) {
			addBendPoint(attributes);
		}

		if (E_LABEL_POSITION.equals(qName)) {
			addLabelPosition(attributes);
		}
		
		if (E_COMMENT.equals(qName)) {
			if (constructs.isEmpty())
				return;
			if (!(constructs.lastElement() instanceof Diagram) )
				return;
			Comment c = fac.createComment();
			setPosition(c, attributes);
			String tmp = attributes.getValue(A_WIDTH);
			if (tmp!=null)
				c.setWidth(Integer.parseInt(tmp));
			tmp = attributes.getValue(A_HEIGHT);
			if (tmp!=null)
				c.setHeight(Integer.parseInt(tmp));
			constructs.push(c);
			state = State.DIAGRAM_COMMENT;
		}
		
		if (E_ASSOC_CONSTRAINT_REF.equals(qName)) {
			if (constructs.isEmpty())
				return;
			
			String tmp = attributes.getValue(A_REF);
			if (tmp==null)
				return;
			AssociationTypeConstraint atc = resolveAssociationConstraint(tmp);
			
			AssociationNode an = (AssociationNode) constructs.lastElement();
			an.setAssociationConstraint(atc);
		}
		
		if (E_SCOPE_CONSTRAINT.equals(qName)) {
			ScopeConstraint sc = fac.createScopeConstraint();
			setCardinality(sc, attributes);
			constructs.push(sc);	
		}
	}

	private AssociationTypeConstraint resolveAssociationConstraint(String tmp) {
	    int idx = Integer.parseInt(tmp.substring(tmp.lastIndexOf('.')+1));
	    return file.getTopicMapSchema().getAssociationTypeConstraints().get(idx);
    }

	private void addBendPoint(Attributes attributes) {
	    if (constructs.isEmpty())
	    	return;
	    Bendpoint bp = fac.createBendpoint();
	    setPosition(bp, attributes);
	    Edge e = (Edge) constructs.lastElement();
	    e.getBendpoints().add(bp);
    }

	private void addLabelPosition(Attributes attributes) {
	    if (constructs.isEmpty())
	    	return;
	    LabelPos lp = fac.createLabelPos();			
	    setPosition(lp, attributes);
	    Edge e = (Edge) constructs.lastElement();
	    e.getLabelPositions().add(lp);
    }

	private void addNode(Attributes attributes) {
	    String type = attributes.getValue(A_TYPE);
	    Node node = null;
	    if (type.equals("typeNode")) {
	    	state = State.TYPE_NODE;
	    	node = fac.createTypeNode();
	    } else {
	    	state = State.ASSOCIATION_NODE;
	    	node = fac.createAssociationNode();
	    }
	    setPosition(node, attributes);
	    constructs.push(node);
    }

	private void addEdge(Attributes attributes) {
	    if (constructs.isEmpty())
	    	return;
	    Diagram d = (Diagram) constructs.lastElement();
	    
	    Edge e = fac.createEdge();
	    String tmp = attributes.getValue(A_TYPE);
	    if (tmp!=null) {
	    	e.setType(EdgeType.valueOf(tmp));
	    }
	    tmp = attributes.getValue(A_SOURCE);
	    if (tmp==null)
	    	return;

	    e.setSource(resolveNodeReference(d, tmp));
	    tmp = attributes.getValue(A_TARGET);
	    if (tmp==null)
	    	return;
	    e.setTarget(resolveNodeReference(d, tmp));
	    
	    tmp = attributes.getValue(A_TOPIC_ROLE_REF);
	    if (tmp!=null) {
	    	RolePlayerConstraint rpc = resolveTopicRoleReference(tmp);
	    	if (rpc==null)
	    		return;
	    	e.setRoleConstraint(rpc);
	    }
	    
	    constructs.push(e);
    }

	private RolePlayerConstraint resolveTopicRoleReference(String tmp) {
		String assocRef = tmp.substring(0, tmp.indexOf('_'));
		int assocIdx = Integer.parseInt(assocRef.substring(assocRef.indexOf('.')+1));
		
		AssociationTypeConstraint atc = file.getTopicMapSchema().getAssociationTypeConstraints().get(assocIdx);
		
		int rpcIndex = Integer.parseInt(tmp.substring(tmp.lastIndexOf('.')+1));
		
	    return atc.getPlayerConstraints().get(rpcIndex);
    }

	private Node resolveNodeReference(Diagram d, String tmp) {
	    tmp = tmp.substring(tmp.indexOf(".")+1);
	    int idx = Integer.parseInt(tmp);
	    if ( (idx>=d.getNodes().size()) || (idx==-1) )
	    	throw new RuntimeException("invalid node index: "+idx);
	    	
	    Node src = d.getNodes().get(idx);
	    return src;
    }

	

	private void addRoleConstrainReference(Attributes attributes) {
	    if (state == State.TOPIC_ROLE_CONSTRAINT) {
	    	if (constructs.isEmpty())
	    		return;
	    	RolePlayerConstraint rpc = (RolePlayerConstraint) constructs.lastElement();
	    	RoleConstraint rc = resolveRoleConstraintReference(attributes);
	    	if (rc!=null)
	    		rpc.setRole(rc);
	    		
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
	
	private void addTopicReifiesConstraint(Attributes attributes) {
	    if (currTopicType!=null) {
	    	TopicReifiesConstraint trc = fac.createTopicReifiesConstraint();
	    	setCardinality(trc, attributes);
	    	constructs.add(trc);
	    	state = State.TOPIC_REIFIES_CONSTRAINT;
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
	    TMCLConstruct construct = (TMCLConstruct) constructs.lastElement();
	    Annotation annotation = ModelFactory.eINSTANCE.createAnnotation();
	    annotation.setKey(attributes.getValue(A_KEY));
	    annotation.setValue(attributes.getValue(A_VALUE));
	    construct.getAnnotations().add(annotation);
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
	    	constructs.add(sic);
	    	currTopicType.getSubjectIdentifierConstraints().add(sic);
	    }
    }

	private void addMappingElement(Attributes attributes) {
	    String key = attributes.getValue(A_KEY);
	    String value = attributes.getValue(A_VALUE);
	    
	    if (constructs.isEmpty())
	    	return;
	    TMCLConstruct c = (TMCLConstruct) constructs.lastElement();
	    if (c instanceof TopicMapSchema) {
	    	MappingElement me = fac.createMappingElement();
	    	me.setKey(key);
	    	me.setValue(value);
	    	((TopicMapSchema) c).getMappings().add(me);
	    }
    }

	private void addTopicTypeReference(Attributes attributes) {
	    String ref = attributes.getValue(A_REF);
	    if (ref != null) {
	    	TopicType tt = resolveTopicTypeReference(ref);

	    	if (constructs.isEmpty())
	    		return;
	    	
	    	EObject construct = constructs.lastElement();
			if (construct instanceof RolePlayerConstraint) {
				((RolePlayerConstraint) construct).setPlayer(tt);
			} else if (construct instanceof AbstractTypedConstraint) {
					((AbstractTypedConstraint) construct).setType(tt);
			} else if (construct instanceof TopicType) {
				if (state == State.ISA) {
					((TopicType) construct).getIsa().add(tt);
				} else if (state == State.AKO) {
					((TopicType) construct).getAko().add(tt);
				} else if (state == State.OVERLAP) {
					((TopicType) construct).getOverlap().add(tt);
				}
			} else if (construct instanceof TypeNode) {
				((TypeNode) construct).setTopicType(tt);
			} else if (construct instanceof RoleCombinationConstraint) {
				RoleCombinationConstraint rcc = (RoleCombinationConstraint) construct;
				switch (state) {
				case PLAYER:
					rcc.setPlayer(tt);
					return;
				case ROLE:
					rcc.setRole((RoleType) tt);
					return;
				case OTHER_PLAYER:
					rcc.setOtherPlayer(tt);
					return;
				case OTHER_ROLE:
					rcc.setOtherRole((RoleType) tt);
					return;
				}
			}
			
	    }
    }

	private TopicType resolveTopicTypeReference(String ref) {
	    int idx = ref.indexOf(".");
	    int topicIdx = Integer.parseInt(ref.substring(idx + 1));
	    TopicType tt = ttList.get(topicIdx);
	    return tt;
    }
	
	private RoleConstraint resolveRoleConstraintReference(Attributes attributes) {
		String ref = attributes.getValue(A_REF);
		if (ref != null) {
			String typeRef = ref.substring(0, ref.lastIndexOf("_"));
			TopicType tt = resolveTopicTypeReference(typeRef);
			
			int idx = ref.lastIndexOf('.');
			int rcIdx = Integer.parseInt(ref.substring(idx + 1));
			
			RoleConstraint rc = ((AssociationType) tt).getRoles().get(rcIdx);
			return rc;
		}
		return null;
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
	
	private void setPosition(Object obj, Attributes attributes) {
		String tmp = attributes.getValue(A_POS_X);
		int x = Integer.parseInt(tmp);
		tmp = attributes.getValue(A_POS_Y);
		int y = Integer.parseInt(tmp);
		
		if (obj instanceof Node) {
			((Node) obj).setPosX(x);
			((Node) obj).setPosY(y);
		} else if (obj instanceof LabelPos) {
			((LabelPos) obj).setPosX(x);
			((LabelPos) obj).setPosY(y);
		} else if (obj instanceof Bendpoint) {
			((Bendpoint) obj).setPosX(x);
			((Bendpoint) obj).setPosY(y);
		}
		
    }

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (state == State.NONE)
			return;
		
		if (constructs.isEmpty())
			return;
		
		String tmp = new String(ch, start, length);
		 
		EObject c =  constructs.lastElement();
		if (c instanceof TMCLConstruct) {
			switch (state) {
			case COMMENT:
				((TMCLConstruct) c).setComment(tmp);
				return;
			case SEE_ALSO:
				((TMCLConstruct) c).setSee_also(tmp);
				return;
			case DESCRIPTION:
				((TMCLConstruct) c).setDescription(tmp);
			default:
				return;
			}
		} else if (c instanceof Comment) {
			((Comment) c).setContent(tmp);
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
		
		if (E_TOPIC_REIFIES_CONSTRAINT.equals(qName)) {
			currTopicType.setTopicReifiesConstraint( (TopicReifiesConstraint) constructs.pop());
			state = State.TOPIC_TYPE;
		}
		
		if (E_ROLE_CONSTRAINT.equals(qName)) {
			if ( (state != State.ASSOCIATION_CONSTRAINT) 
			  && (state != State.TOPIC_ROLE_CONSTRAINT) ) {
				state = State.TOPIC_TYPE;
				if ( (constructs.isEmpty()) || (currTopicType==null))
					return;
				((AssociationType) currTopicType).getRoles().add((RoleConstraint) constructs.pop());
				
			}
		}
		
		if (E_OVERLAP.equals(qName)) {
			state = State.NONE;
		}
		
		if (E_ROLE_COMBINATION_CONSTRAINT.equals(qName)) {
			state = State.NONE;
			if (constructs.isEmpty())
				return;
			RoleCombinationConstraint rcc = (RoleCombinationConstraint) constructs.pop();
			AssociationType at = (AssociationType) constructs.lastElement();
			at.getRoleCombinations().add(rcc);
		}
		
		if (E_OCCURRENCE_CONSTRAINT.equals(qName)) {
			if (currTopicType != null) {
				currTopicType.getOccurrenceConstraints().add((OccurrenceTypeConstraint) constructs.pop());
				state = State.TOPIC_TYPE;
			}
		}
		
		if (E_ASSOCIATION_CONSTRAINT.equals(qName)) {
			state = State.NONE;
			AssociationTypeConstraint atc = (AssociationTypeConstraint) constructs.pop();
			file.getTopicMapSchema().getAssociationTypeConstraints().add(atc);
		}
		
		if (E_TOPIC_ROLE_CONSTRAINT.equals(qName)) {
			state = State.ASSOCIATION_CONSTRAINT;
			if (constructs.isEmpty())
				return;
			RolePlayerConstraint rpc = (RolePlayerConstraint) constructs.pop();
			AssociationTypeConstraint atc = (AssociationTypeConstraint) constructs.lastElement();
			atc.getPlayerConstraints().add(rpc);
		}
		
		if (E_PLAYER.equals(qName)) {
			state = State.TOPIC_ROLE_CONSTRAINT;
		}
		
		if (E_ROLE.equals(qName)) {
			state = State.NONE;
		}
		
		if (E_OTHER_PLAYER.equals(qName)) {
			state = State.NONE;
		}
		
		if (E_OTHER_ROLE.equals(qName)) {
			state = State.NONE;
		}
		
		if (E_SCOPE_CONSTRAINT.equals(qName)) {
			if(constructs.isEmpty())
				return;
			ScopeConstraint sc = (ScopeConstraint) constructs.pop();
			ScopedTopicType st = (ScopedTopicType) constructs.lastElement();
			
			st.getScope().add(sc);
		}
		
		checkDiagramElements(qName);
		
	}

	private void checkDiagramElements(String qName) {
	    if (E_DIAGRAM.equals(qName)) {
			Diagram d = (Diagram) constructs.pop();
			file.getDiagrams().add(d);
		}
	    
	    if (E_COMMENT.equals(qName)) {
	    	if (state!=State.DIAGRAM_COMMENT)
	    		return;
	    	state = State.NONE;
	    	if (constructs.isEmpty())
	    		return;
	    	Comment c = (Comment) constructs.pop();
	    	Diagram d = (Diagram) constructs.lastElement();
	    	
	    	d.getComments().add(c);
	    }
		if (E_NODE.equals(qName)) {
			state = State.NONE;
			if (constructs.isEmpty())
				return;
			Node node = (Node) constructs.pop();
			Diagram d = (Diagram) constructs.lastElement();
			d.getNodes().add(node);
		}
		
		if (E_EDGE.equals(qName)) {
			if (constructs.isEmpty())
				return;
			Edge e = (Edge) constructs.pop();
			Diagram d = (Diagram) constructs.lastElement();
			d.getEdges().add(e);
		}
    }
}