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
import de.topicmapslab.tmcledit.model.AssociationNode;
import de.topicmapslab.tmcledit.model.AssociationType;
import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.Comment;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.MappingElement;
import de.topicmapslab.tmcledit.model.NameTypeConstraint;
import de.topicmapslab.tmcledit.model.OccurrenceType;
import de.topicmapslab.tmcledit.model.OccurrenceTypeConstraint;
import de.topicmapslab.tmcledit.model.RegExpTopicType;
import de.topicmapslab.tmcledit.model.ReifiableTopicType;
import de.topicmapslab.tmcledit.model.ReifierConstraint;
import de.topicmapslab.tmcledit.model.RoleCombinationConstraint;
import de.topicmapslab.tmcledit.model.RoleConstraint;
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;
import de.topicmapslab.tmcledit.model.ScopeConstraint;
import de.topicmapslab.tmcledit.model.ScopedTopicType;
import de.topicmapslab.tmcledit.model.TMCLConstruct;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
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
			        "onotoa.topicmapslab.de", "file", null);
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
		fileNode.setAttribute("version", getVersionString());

		TopicMapSchema schema = file.getTopicMapSchema();

		Element schemaNode = document.createElement("schema");
		String tmp = schema.getBaseLocator();
		if ((tmp != null) && (tmp.length() > 0))
			schemaNode.setAttribute("baseLocator", tmp);
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
		Element meNode = document.createElement("mappingelement");
		meNode.setAttribute("key", me.getKey());
		meNode.setAttribute("value", me.getValue());
		parent.appendChild(meNode);
	}

	private void createDiagramNode(Diagram diagram, Element parent) {
		Element dNode = document.createElement("diagram");
		dNode.setAttribute("name", diagram.getName());

		for (Comment c : diagram.getComments()) {
			Element cNode = document.getDocumentElement();
			addPositionElements(cNode, c);
			cNode.setAttribute("w", Integer.toString(c.getWidth()));
			cNode.setAttribute("h", Integer.toString(c.getHeight()));
			dNode.appendChild(cNode);
		}

		for (de.topicmapslab.tmcledit.model.Node n : diagram.getNodes()) {
			Element nNode = document.createElement("node");
			if (n instanceof TypeNode) {
				nNode.setAttribute("type", "typeNode");
				addTopicReference(nNode, ((TypeNode) n).getTopicType());
			} else {
				nNode.setAttribute("type", "associationNode");
				addAssociationConstraintReference(nNode, ((AssociationNode) n).getAssociationConstraint());
			}
			dNode.appendChild(nNode);
		}
		parent.appendChild(dNode);
	}

	private void createAssociationConstraintNode(AssociationTypeConstraint atc, Element parent) {
		Element atcNode = document.createElement("assocConstr");

		addCardinalityAttributes(atcNode, atc);
		addTMCLConstructElements(atc, atcNode);
		addRegExpAttributes(atcNode, atc);

		if (atc.getType() != null)
			addTopicReference(atcNode, atc.getType());

		for (RolePlayerConstraint rpc : atc.getPlayerConstraints()) {
			createRolePlayerConstraint(rpc, atcNode);
		}

		parent.appendChild(atcNode);
	}

	private void createRolePlayerConstraint(RolePlayerConstraint rpc, Element atcNode) {
		Element rpcNode = document.createElement("topicRoleConstr");
		addCardinalityAttributes(rpcNode, rpc);
		addTMCLConstructElements(rpc, rpcNode);

		Element playerNode = document.createElement("player");
		addTopicReference(playerNode, rpc.getPlayer());
		rpcNode.appendChild(playerNode);

		Element rcNode = document.createElement("roleConstraint");
		addRoleConstraintReference(rcNode, rpc.getRole());
		rpcNode.appendChild(rcNode);

		atcNode.appendChild(rpcNode);
	}

	private void addRoleConstraintReference(Element rcNode, RoleConstraint rc) {
		Element e = document.createElement("roleConstrRef");

		AssociationType at = (AssociationType) rc.eContainer();
		int ttIdx = file.getTopicMapSchema().getTopicTypes().indexOf(at);
		int rtIdx = at.getRoles().indexOf(rc);

		e.setAttribute("ref", "topictypes." + ttIdx + ".roleConstraints." + rtIdx);

		rcNode.appendChild(e);

	}

	private void addRegExpAttributes(Element node, AbstractRegExpConstraint rc) {
		if (rc.getRegexp() != null)
			node.setAttribute("regExp", rc.getRegexp());
	}

	private void addCardinalityAttributes(Element node, AbstractCardinalityContraint acc) {
		if (acc.getCardMin() != null)
			node.setAttribute("cardMin", acc.getCardMin());
		if (acc.getCardMax() != null)
			node.setAttribute("cardMax", acc.getCardMax());
	}

	private void addPositionElements(Element node, de.topicmapslab.tmcledit.model.Node diagramNode) {
		node.setAttribute("posX", Integer.toString(diagramNode.getPosX()));
		node.setAttribute("posY", Integer.toString(diagramNode.getPosY()));
	}

	private void createTopicTypeNode(TopicType tt, Element parent) {
		Element typeNode = document.createElement("topicType");
		typeNode.setAttribute("kind", tt.getKind().getLiteral());

		addTMCLConstructElements(tt, typeNode);

		Element nameNode = document.createElement("name");
		nameNode.setTextContent(tt.getName());
		typeNode.appendChild(nameNode);

		for (String s : tt.getIdentifiers()) {
			Element siNode = document.createElement("subjectIdentifier");
			siNode.setTextContent(s);
			typeNode.appendChild(siNode);
		}

		for (String s : tt.getLocators()) {
			Element slNode = document.createElement("subjectLocator");
			slNode.setTextContent(s);
			typeNode.appendChild(slNode);
		}

		if (tt.getNameContraints().size() > 0) {
			Element ncNode = document.createElement("nameConstraints");
			for (NameTypeConstraint ntc : tt.getNameContraints()) {
				addNameConstraints(ntc, ncNode);
			}
			typeNode.appendChild(ncNode);
		}

		if (tt.getOccurrenceConstraints().size() > 0) {
			Element ocNode = document.createElement("occurrenceConstraints");
			for (OccurrenceTypeConstraint otc : tt.getOccurrenceConstraints()) {
				addOccurrenceConstraint(otc, ocNode);
			}
			typeNode.appendChild(ocNode);
		}
		
		if (tt.getIsa().size() > 0) {
			Element isA = document.createElement("isa");
			for (TopicType isaTT : tt.getIsa()) {
				addTopicReference(isA, isaTT);
			}
			typeNode.appendChild(isA);
		}
		if (tt.getAko().size() > 0) {
			Element ako = document.createElement("ako");
			for (TopicType akoTT : tt.getAko()) {
				addTopicReference(ako, akoTT);
			}
			typeNode.appendChild(ako);
		}

		if (tt instanceof ScopedTopicType) {
			addScopeNodes((ScopedTopicType) tt, typeNode);
		}

		if (tt instanceof RegExpTopicType) {
			addRegExp((AbstractRegExpTopicType) tt, typeNode);
		}

		if (tt instanceof OccurrenceType) {
			OccurrenceType ot = (OccurrenceType) tt;
			if (ot.isUnique())
				typeNode.setAttribute("unique", "true");
			if (ot.getDataType() != null)
				typeNode.setAttribute("datatype", ot.getDataType());

		}

		if (tt instanceof ReifiableTopicType) {
			ReifiableTopicType rtt = (ReifiableTopicType) tt;
			ReifierConstraint reifierConstraint = rtt.getReifierConstraint();
			if (reifierConstraint != null) {
				Element rNode = document.createElement("reifierConstraint");
				addCardinalityAttributes(rNode, reifierConstraint);
				addTMCLConstructElements(reifierConstraint, rNode);
				if (reifierConstraint.getType() != null)
					addTopicReference(rNode, reifierConstraint.getType());
				typeNode.appendChild(rNode);
			}
		}

		if (tt instanceof AssociationType) {
			AssociationType at = (AssociationType) tt;
			if (at.getRoles().size() > 0) {
				Element rcNode = document.createElement("roleConstraints");
				for (RoleConstraint rc : at.getRoles()) {
					createRoleConstraintNode(rc, rcNode);
				}
			}
			if (at.getRoleCombinations().size() > 0) {
				Element rccNode = document.createElement("roleCombinationConstraints");
				for (RoleCombinationConstraint rcc : at.getRoleCombinations()) {
					createRoleCombinationConstraint(rcc, rccNode);
				}
			}

		}

		if (tt.isAbstract())
			typeNode.setAttribute("abstract", "true");

		parent.appendChild(typeNode);
	}

	private void addOccurrenceConstraint(OccurrenceTypeConstraint otc, Element parent) {
	    Element ocNode = document.createElement("occurrenceConstraint");
	    addTMCLConstructElements(otc, ocNode);
	    addCardinalityAttributes(ocNode, otc);
	    if (otc.getType()!=null)
	    	addTopicReference(ocNode, otc.getType());
	    if (otc.isUnique())
	    	ocNode.setAttribute("unique", "true");
	    		
	    parent.appendChild(ocNode);
    }

	private void addNameConstraints(NameTypeConstraint ntc, Element parent) {
		Element ncNode = document.createElement("nameConstraint");
	    addTMCLConstructElements(ntc, ncNode);
	    addCardinalityAttributes(ncNode, ntc);	    
	    if (ntc.getType()!=null)
	    	addTopicReference(ncNode, ntc.getType());
	    parent.appendChild(ncNode);
	}

	private void createRoleCombinationConstraint(RoleCombinationConstraint rcc, Element parent) {
		Element rccNode = document.createElement("roleCombinationConstraint");
		addTMCLConstructElements(rcc, rccNode);

		Element player = document.createElement("player");
		addTopicReference(player, rcc.getPlayer());
		rccNode.appendChild(player);

		Element role = document.createElement("role");
		addTopicReference(role, rcc.getRole());
		rccNode.appendChild(role);

		Element otherPlayer = document.createElement("otherPlayer");
		addTopicReference(otherPlayer, rcc.getPlayer());
		rccNode.appendChild(otherPlayer);

		Element otherRole = document.createElement("otherRole");
		addTopicReference(otherRole, rcc.getOtherRole());
		rccNode.appendChild(otherRole);

		parent.appendChild(rccNode);

	}

	private void createRoleConstraintNode(RoleConstraint rc, Element parent) {
		Element rNode = document.createElement("roleConstraint");

		addTMCLConstructElements(rc, rNode);
		addCardinalityAttributes(rNode, rc);

		if (rc.getType() != null)
			addTopicReference(rNode, rc.getType());

		parent.appendChild(rNode);
	}

	private void addRegExp(AbstractRegExpTopicType tt, Element parent) {
		if (tt.getRegExp() != null)
			parent.setAttribute("regExp", tt.getRegExp());

	}

	private void addScopeNodes(ScopedTopicType tt, Element parent) {
		for (ScopeConstraint sc : tt.getScope()) {
			Element scopeConstraintNode = document.createElement("scopeConstraint");
			addTMCLConstructElements(sc, scopeConstraintNode);
			addCardinalityAttributes(scopeConstraintNode, sc);
			if (sc.getType() != null)
				addTopicReference(scopeConstraintNode, sc.getType());
			parent.appendChild(scopeConstraintNode);
		}

	}

	private void addTopicReference(Element element, TopicType type) {
		Element ref = document.createElement("topictypeRef");
		ref.setAttribute("ref", "topictypes." + file.getTopicMapSchema().getTopicTypes().indexOf(type));
		element.appendChild(ref);
	}

	private void addAssociationConstraintReference(Element element, AssociationTypeConstraint atc) {
		Element ref = document.createElement("assocConstrRef");
		ref.setAttribute("ref", "assocConstraints."
		        + file.getTopicMapSchema().getAssociationTypeConstraints().indexOf(atc));
		element.appendChild(ref);
	}

	private void addTMCLConstructElements(TMCLConstruct construct, Element node) {
		String tmp = construct.getComment();
		if ((tmp != null) && (tmp.length() > 0)) {
			Element comm = document.createElement("comment");
			comm.setTextContent(tmp);
			node.appendChild(comm);
		}

		tmp = construct.getDescription();
		if ((tmp != null) && (tmp.length() > 0)) {
			Element desc = document.createElement("description");
			desc.setTextContent(tmp);
			node.appendChild(desc);
		}

		tmp = construct.getSee_also();
		if ((tmp != null) && (tmp.length() > 0)) {
			Element see = document.createElement("see-also");
			see.setTextContent(tmp);
			node.appendChild(see);
		}

		for (String key : construct.getExtension().keySet()) {
			Element extNode = document.createElement("annotation");
			extNode.setAttribute("key", key);
			extNode.setAttribute("value", construct.getExtension().get(key));
			node.appendChild(extNode);
		}
	}

}
