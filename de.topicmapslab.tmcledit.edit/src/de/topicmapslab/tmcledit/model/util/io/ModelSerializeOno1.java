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
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import de.topicmapslab.tmcledit.model.AbstractCardinalityContraint;
import de.topicmapslab.tmcledit.model.AbstractRegExpConstraint;
import de.topicmapslab.tmcledit.model.AbstractRegExpTopicType;
import de.topicmapslab.tmcledit.model.Annotation;
import de.topicmapslab.tmcledit.model.AssociationNode;
import de.topicmapslab.tmcledit.model.AssociationType;
import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.Bendpoint;
import de.topicmapslab.tmcledit.model.Comment;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.DomainDiagram;
import de.topicmapslab.tmcledit.model.Edge;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.LabelPos;
import de.topicmapslab.tmcledit.model.MappingElement;
import de.topicmapslab.tmcledit.model.NameTypeConstraint;
import de.topicmapslab.tmcledit.model.OccurrenceType;
import de.topicmapslab.tmcledit.model.OccurrenceTypeConstraint;
import de.topicmapslab.tmcledit.model.ReifiableTopicType;
import de.topicmapslab.tmcledit.model.ReifierConstraint;
import de.topicmapslab.tmcledit.model.RoleCombinationConstraint;
import de.topicmapslab.tmcledit.model.RoleConstraint;
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;
import de.topicmapslab.tmcledit.model.ScopeConstraint;
import de.topicmapslab.tmcledit.model.ScopedTopicType;
import de.topicmapslab.tmcledit.model.SubjectIdentifierConstraint;
import de.topicmapslab.tmcledit.model.SubjectLocatorConstraint;
import de.topicmapslab.tmcledit.model.TMCLConstruct;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicReifiesConstraint;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.TypeNode;

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
			document = DocumentBuilderFactory.newInstance().newDocumentBuilder().getDOMImplementation().createDocument(
			        "onotoa.topicmapslab.de", E_FILE, null);
			this.file = file;

			createFileNode();

			DOMSource domSource = new DOMSource(document);
			StringWriter writer = new StringWriter();
			StreamResult streamResult = new StreamResult(writer);

			TransformerFactory tf = TransformerFactory.newInstance();
			try {
				tf.setAttribute("indent-number", Integer.toString(4));
			} catch (IllegalArgumentException iae) {
				iae.printStackTrace(); // TODO remove me and log
			}
			Transformer serializer = tf.newTransformer();
			serializer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			serializer.setOutputProperty(OutputKeys.INDENT, "YES");

			serializer.transform(domSource, streamResult);

			return writer.getBuffer().toString();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private Element createFileNode() {
		Element fileNode = document.getDocumentElement();
		fileNode.setAttribute(A_VERSION, getVersionString());

		TopicMapSchema schema = file.getTopicMapSchema();

		Element schemaNode = document.createElement(E_SCHEMA);
		String tmp = schema.getBaseLocator();
		if ((tmp != null) && (tmp.length() > 0))
			schemaNode.setAttribute(A_BASE_LOCATOR, tmp);
		tmp = schema.getName();
		if ((tmp != null) && (tmp.length() > 0))
			schemaNode.setAttribute(A_NAME, tmp);
		addTMCLConstructElements(schema, schemaNode);
		fileNode.appendChild(schemaNode);

		for (MappingElement me : schema.getMappings()) {
			createMappingNode(me, schemaNode);
		}

		for (TopicType tt : schema.getTopicTypes()) {
			createTopicTypeNode(tt, schemaNode);
		}

		for (AssociationTypeConstraint atc : schema.getAssociationTypeConstraints()) {
			createAssociationConstraintNode(atc, schemaNode);
		}

		for (Diagram d : file.getDiagrams()) {
			createDiagramNode(d, fileNode);
		}

		return fileNode;
	}

	private void createMappingNode(MappingElement me, Element parent) {
		Element meNode = document.createElement(E_MAPPING_ELEMENT);
		meNode.setAttribute(A_KEY, me.getKey());
		meNode.setAttribute(A_VALUE, me.getValue());
		parent.appendChild(meNode);
	}

	private void createDiagramNode(Diagram diagram, Element parent) {
		Element dNode = document.createElement(E_DIAGRAM);
		dNode.setAttribute(A_NAME, diagram.getName());
		if (diagram instanceof DomainDiagram)
			dNode.setAttribute(A_TYPE, "domain");

		for (Comment c : diagram.getComments()) {
			Element cNode = document.createElement(E_COMMENT);
			addPositionElements(cNode, c);
			cNode.setAttribute(A_WIDTH, Integer.toString(c.getWidth()));
			cNode.setAttribute(A_HEIGHT, Integer.toString(c.getHeight()));
			cNode.setTextContent(c.getContent());
			dNode.appendChild(cNode);
		}

		for (de.topicmapslab.tmcledit.model.Node n : diagram.getNodes()) {
			addDiagramNode(dNode, n);
		}
		
		for (Edge e : diagram.getEdges()) {
			addEdgeNode(diagram, dNode, e);
		}
		parent.appendChild(dNode);
	}

	private void addEdgeNode(Diagram diagram, Element dNode, Edge e) {
	    Element edgeNode = document.createElement(E_EDGE);
	    edgeNode.setAttribute(A_TYPE, e.getType().getLiteral());
	    
	    String tmp = "node."+diagram.getNodes().indexOf(e.getSource());
	    edgeNode.setAttribute(A_SOURCE, tmp);
	    tmp = "node."+diagram.getNodes().indexOf(e.getTarget());
	    edgeNode.setAttribute(A_TARGET, tmp);
	    
	    
	    RolePlayerConstraint rpc = e.getRoleConstraint();
		if (rpc!=null) {
			AssociationTypeConstraint atc = (AssociationTypeConstraint) rpc.eContainer();
	    	int atcIdx = file.getTopicMapSchema().getAssociationTypeConstraints().indexOf(atc);
	    	
	    	StringBuilder builder = new StringBuilder(E_ASSOCIATION_CONSTRAINT);
	    	builder.append(".");
	    	builder.append(atcIdx);
	    	builder.append("_");
	    	builder.append(E_TOPIC_ROLE_CONSTRAINT);
	    	builder.append(".");
	    	builder.append(atc.getPlayerConstraints().indexOf(rpc));
	    	
	    	edgeNode.setAttribute(A_TOPIC_ROLE_REF, builder.toString());
		}
	    
	    for (Bendpoint bp : e.getBendpoints()) {
	    	Element bpNode = document.createElement(E_BENDPOINT);
	    	bpNode.setAttribute(A_POS_X, Integer.toString(bp.getPosX()));
	    	bpNode.setAttribute(A_POS_Y, Integer.toString(bp.getPosY()));
	    	edgeNode.appendChild(bpNode);
	    }
	    
	    for (LabelPos pos : e.getLabelPositions()) {
	    	Element lpNode = document.createElement(E_LABEL_POSITION);
	    	lpNode.setAttribute(A_POS_X, Integer.toString(pos.getPosX()));
	    	lpNode.setAttribute(A_POS_Y, Integer.toString(pos.getPosY()));
	    	edgeNode.appendChild(lpNode);
	    }
	    dNode.appendChild(edgeNode);
    }

	private void addDiagramNode(Element dNode, de.topicmapslab.tmcledit.model.Node n) {
	    Element nNode = document.createElement(E_NODE);
	    if (n instanceof TypeNode) {
	    	nNode.setAttribute(A_TYPE, "typeNode");
	    	if (((TypeNode) n).getImage()!=null) {
	    		nNode.setAttribute(A_IMAGE, ((TypeNode) n).getImage());
	    	}
	    	addTopicReference(nNode, ((TypeNode) n).getTopicType());
	    } else {
	    	nNode.setAttribute(A_TYPE, "associationNode");
	    	addAssociationConstraintReference(nNode, ((AssociationNode) n).getAssociationConstraint());
	    }
	    addPositionElements(nNode, n);
	    dNode.appendChild(nNode);
    }

	private void createAssociationConstraintNode(AssociationTypeConstraint atc, Element parent) {
		Element atcNode = document.createElement(E_ASSOCIATION_CONSTRAINT);

		addTMCLConstructElements(atc, atcNode);

		if (atc.getType() != null)
			addTopicReference(atcNode, atc.getType());

		for (RolePlayerConstraint rpc : atc.getPlayerConstraints()) {
			createRolePlayerConstraint(rpc, atcNode);
		}

		parent.appendChild(atcNode);
	}

	private void createRolePlayerConstraint(RolePlayerConstraint rpc, Element atcNode) {
		Element rpcNode = document.createElement(E_TOPIC_ROLE_CONSTRAINT);
		addCardinalityAttributes(rpcNode, rpc);
		addTMCLConstructElements(rpc, rpcNode);

		Element playerNode = document.createElement(E_PLAYER);
		addTopicReference(playerNode, rpc.getPlayer());
		rpcNode.appendChild(playerNode);

		if (rpc.getRole()!=null) {
			Element rcNode = document.createElement(E_ROLE_CONSTRAINT);
			addRoleConstraintReference(rcNode, rpc.getRole());
			rpcNode.appendChild(rcNode);
		}
		atcNode.appendChild(rpcNode);
	}

	private void addRoleConstraintReference(Element rcNode, RoleConstraint rc) {
		if (rc==null)
			return;
		Element e = document.createElement(E_ROLE_CONSTRAINT_REFERENCE);

		AssociationType at = (AssociationType) rc.eContainer();
		int ttIdx = file.getTopicMapSchema().getTopicTypes().indexOf(at);
		int rtIdx = at.getRoles().indexOf(rc);

		e.setAttribute(A_REF, "topictypes." + ttIdx + "_roleConstraints." + rtIdx);

		rcNode.appendChild(e);

	}

	private void addRegExpAttributes(Element node, AbstractRegExpConstraint rc) {
		if (rc.getRegexp() != null)
			node.setAttribute(A_REG_EXP, rc.getRegexp());
	}

	private void addCardinalityAttributes(Element node, AbstractCardinalityContraint acc) {
		if (acc.getCardMin() != null)
			node.setAttribute(A_CARD_MIN, acc.getCardMin());
		if (acc.getCardMax() != null)
			node.setAttribute(A_CARD_MAX, acc.getCardMax());
	}

	private void addPositionElements(Element node, de.topicmapslab.tmcledit.model.Node diagramNode) {
		node.setAttribute(A_POS_X, Integer.toString(diagramNode.getPosX()));
		node.setAttribute(A_POS_Y, Integer.toString(diagramNode.getPosY()));
	}

	private void createTopicTypeNode(TopicType tt, Element parent) {
		Element typeNode = document.createElement(E_TOPIC_TYPE);
		typeNode.setAttribute(A_KIND, tt.getKind().getLiteral());

		addTMCLConstructElements(tt, typeNode);

		Element nameNode = document.createElement(E_NAME);
		nameNode.setTextContent(tt.getName());
		typeNode.appendChild(nameNode);

		for (String s : tt.getIdentifiers()) {
			Element siNode = document.createElement(E_SUBJECT_IDENTIFIER);
			siNode.setTextContent(s);
			typeNode.appendChild(siNode);
		}

		for (String s : tt.getLocators()) {
			Element slNode = document.createElement(E_SUBJECT_LOCATOR);
			slNode.setTextContent(s);
			typeNode.appendChild(slNode);
		}

		if (tt.getNameContraints().size() > 0) {
			Element ncNode = document.createElement(E_NAME_CONSTRAINTS);
			for (NameTypeConstraint ntc : tt.getNameContraints()) {
				addNameConstraints(ntc, ncNode);
			}
			typeNode.appendChild(ncNode);
		}

		if (tt.getOccurrenceConstraints().size() > 0) {
			Element ocNode = document.createElement(E_OCCURRENCE_CONSTRAINTS);
			for (OccurrenceTypeConstraint otc : tt.getOccurrenceConstraints()) {
				addOccurrenceConstraint(otc, ocNode);
			}
			typeNode.appendChild(ocNode);
		}
		
		if (tt.getSubjectIdentifierConstraints().size()>0) {
			Element idNode = document.createElement(E_SUBJECT_IDENTIFIER_CONSTRAINTS);
			for (SubjectIdentifierConstraint sic : tt.getSubjectIdentifierConstraints()) {
				addSubjectIdentifierConstraint(sic, idNode);
			}
			typeNode.appendChild(idNode);
		}
		
		if (tt.getSubjectLocatorConstraints().size()>0) {
			Element idNode = document.createElement(E_SUBJECT_LOCATOR_CONSTRAINTS);
			for (SubjectLocatorConstraint slc : tt.getSubjectLocatorConstraints()) {
				addSubjectLocatorConstraint(slc, idNode);
			}
			typeNode.appendChild(idNode);
		}
		
		if (tt.getIsa().size() > 0) {
			Element isA = document.createElement(E_ISA);
			for (TopicType isaTT : tt.getIsa()) {
				addTopicReference(isA, isaTT);
			}
			typeNode.appendChild(isA);
		}
		if (tt.getAko().size() > 0) {
			Element ako = document.createElement(E_AKO);
			for (TopicType akoTT : tt.getAko()) {
				addTopicReference(ako, akoTT);
			}
			typeNode.appendChild(ako);
		}

		if (tt instanceof ScopedTopicType) {
			addScopeNodes((ScopedTopicType) tt, typeNode);
		}

		if (tt instanceof AbstractRegExpTopicType) {
			addRegExp((AbstractRegExpTopicType) tt, typeNode);
		}

		if (tt instanceof OccurrenceType) {
			OccurrenceType ot = (OccurrenceType) tt;
			if (ot.isUnique())
				typeNode.setAttribute(A_UNIQUE, "true");
			if (ot.getDataType() != null)
				typeNode.setAttribute(A_DATATYPE, ot.getDataType());

		}

		if (tt instanceof ReifiableTopicType) {
			ReifiableTopicType rtt = (ReifiableTopicType) tt;
			ReifierConstraint reifierConstraint = rtt.getReifierConstraint();
			if (reifierConstraint != null) {
				Element rNode = document.createElement(E_REIFIER_CONSTRAINT);
				addCardinalityAttributes(rNode, reifierConstraint);
				addTMCLConstructElements(reifierConstraint, rNode);
				if (reifierConstraint.getType() != null)
					addTopicReference(rNode, reifierConstraint.getType());
				typeNode.appendChild(rNode);
			}
		}

		
		for (TopicReifiesConstraint trc : tt.getTopicReifiesConstraints()) {
			Element rNode = document.createElement(E_TOPIC_REIFIES_CONSTRAINT);
			addCardinalityAttributes(rNode, trc);
			addTMCLConstructElements(trc, rNode);
			if (trc.getType() != null)
				addTopicReference(rNode, trc.getType());
			typeNode.appendChild(rNode);
		}
		
		if (tt instanceof AssociationType) {
			AssociationType at = (AssociationType) tt;
			if (at.getRoles().size() > 0) {
				Element rcNode = document.createElement(E_ROLE_CONSTRAINTS);
				for (RoleConstraint rc : at.getRoles()) {
					createRoleConstraintNode(rc, rcNode);
				}
				typeNode.appendChild(rcNode);
			}
			if (at.getRoleCombinations().size() > 0) {
				Element rccNode = document.createElement(E_ROLE_COMBINATION_CONSTRAINTS);
				for (RoleCombinationConstraint rcc : at.getRoleCombinations()) {
					createRoleCombinationConstraint(rcc, rccNode);
				}
				typeNode.appendChild(rccNode);
			}

		}

		if (tt.getOverlap().size()>0) {
			Element overlapNode = document.createElement(E_OVERLAP);
			for (TopicType tt2 : tt.getOverlap()) {
				addTopicReference(overlapNode, tt2);
			}
			typeNode.appendChild(overlapNode);
		}
		
		if (tt.isAbstract())
			typeNode.setAttribute(A_ABSTRACT, "true");

		parent.appendChild(typeNode);
	}

	private void addSubjectLocatorConstraint(SubjectLocatorConstraint slc, Element idNode) {
	    Element slNode = document.createElement(E_SUBJECT_LOCATOR_CONSTRAINT);
	    addRegExpAttributes(slNode, slc);
	    addCardinalityAttributes(slNode, slc);
	    addTMCLConstructElements(slc, slNode);
	    idNode.appendChild(slNode);
    }

	private void addSubjectIdentifierConstraint(SubjectIdentifierConstraint sic, Element idNode) {
		Element siNode = document.createElement(E_SUBJECT_IDENTIFIER_CONSTRAINT);
	    addRegExpAttributes(siNode, sic);
	    addCardinalityAttributes(siNode, sic);
	    addTMCLConstructElements(sic, siNode);
	    idNode.appendChild(siNode);
    }

	private void addOccurrenceConstraint(OccurrenceTypeConstraint otc, Element parent) {
	    Element ocNode = document.createElement(E_OCCURRENCE_CONSTRAINT);
	    addTMCLConstructElements(otc, ocNode);
	    addCardinalityAttributes(ocNode, otc);
	    if (otc.getType()!=null)
	    	addTopicReference(ocNode, otc.getType());
	    if (otc.isUnique())
	    	ocNode.setAttribute(A_UNIQUE, "true");
	    		
	    parent.appendChild(ocNode);
    }

	private void addNameConstraints(NameTypeConstraint ntc, Element parent) {
		Element ncNode = document.createElement(E_NAME_CONSTRAINT);
	    addTMCLConstructElements(ntc, ncNode);
	    addCardinalityAttributes(ncNode, ntc);	    
	    if (ntc.getType()!=null)
	    	addTopicReference(ncNode, ntc.getType());
	    parent.appendChild(ncNode);
	}

	private void createRoleCombinationConstraint(RoleCombinationConstraint rcc, Element parent) {
		Element rccNode = document.createElement(E_ROLE_COMBINATION_CONSTRAINT);
		addTMCLConstructElements(rcc, rccNode);

		Element player = document.createElement(E_PLAYER);
		addTopicReference(player, rcc.getPlayer());
		rccNode.appendChild(player);

		Element role = document.createElement(E_ROLE);
		addTopicReference(role, rcc.getRole());
		rccNode.appendChild(role);

		Element otherPlayer = document.createElement(E_OTHER_PLAYER);
		addTopicReference(otherPlayer, rcc.getOtherPlayer());
		rccNode.appendChild(otherPlayer);

		Element otherRole = document.createElement(E_OTHER_ROLE);
		addTopicReference(otherRole, rcc.getOtherRole());
		rccNode.appendChild(otherRole);

		parent.appendChild(rccNode);

	}

	private void createRoleConstraintNode(RoleConstraint rc, Element parent) {
		Element rNode = document.createElement(E_ROLE_CONSTRAINT);

		addTMCLConstructElements(rc, rNode);
		addCardinalityAttributes(rNode, rc);

		if (rc.getType() != null)
			addTopicReference(rNode, rc.getType());

		parent.appendChild(rNode);
	}

	private void addRegExp(AbstractRegExpTopicType tt, Element parent) {
		if (tt.getRegExp() != null)
			parent.setAttribute(A_REG_EXP, tt.getRegExp());

	}

	private void addScopeNodes(ScopedTopicType tt, Element parent) {
		for (ScopeConstraint sc : tt.getScope()) {
			Element scopeConstraintNode = document.createElement(E_SCOPE_CONSTRAINT);
			addTMCLConstructElements(sc, scopeConstraintNode);
			addCardinalityAttributes(scopeConstraintNode, sc);
			if (sc.getType() != null)
				addTopicReference(scopeConstraintNode, sc.getType());
			parent.appendChild(scopeConstraintNode);
		}

	}

	private void addTopicReference(Element element, TopicType type) {
		if (type==null)
			return;
		Element ref = document.createElement(E_TOPIC_TYPE_REF);
		ref.setAttribute(A_REF, "topictypes." + file.getTopicMapSchema().getTopicTypes().indexOf(type));
		element.appendChild(ref);
	}

	private void addAssociationConstraintReference(Element element, AssociationTypeConstraint atc) {
		Element ref = document.createElement(E_ASSOC_CONSTRAINT_REF);
		ref.setAttribute(A_REF, "assocConstraints."
		        + file.getTopicMapSchema().getAssociationTypeConstraints().indexOf(atc));
		element.appendChild(ref);
	}

	private void addTMCLConstructElements(TMCLConstruct construct, Element node) {
		String tmp = construct.getComment();
		if ((tmp != null) && (tmp.length() > 0)) {
			Element comm = document.createElement(E_COMMENT);
			comm.setTextContent(tmp);
			node.appendChild(comm);
		}

		tmp = construct.getDescription();
		if ((tmp != null) && (tmp.length() > 0)) {
			Element desc = document.createElement(E_DESCRIPTION);
			desc.setTextContent(tmp);
			node.appendChild(desc);
		}

		tmp = construct.getSee_also();
		if ((tmp != null) && (tmp.length() > 0)) {
			Element see = document.createElement(E_SEE_ALSO);
			see.setTextContent(tmp);
			node.appendChild(see);
		}

		for (Annotation a : construct.getAnnotations()) {
			Element extNode = document.createElement(E_ANNOTATION);
			extNode.setAttribute(A_KEY, a.getKey());
			extNode.setAttribute(A_VALUE, a.getValue());
			node.appendChild(extNode);
		}
	}

}
