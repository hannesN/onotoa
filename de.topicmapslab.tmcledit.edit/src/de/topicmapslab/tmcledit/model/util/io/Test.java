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

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import de.topicmapslab.tmcledit.model.AssociationType;
import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.Edge;
import de.topicmapslab.tmcledit.model.EdgeType;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.MappingElement;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.NameTypeConstraint;
import de.topicmapslab.tmcledit.model.Node;
import de.topicmapslab.tmcledit.model.OccurrenceTypeConstraint;
import de.topicmapslab.tmcledit.model.ReifierConstraint;
import de.topicmapslab.tmcledit.model.RoleConstraint;
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;
import de.topicmapslab.tmcledit.model.SubjectIdentifierConstraint;
import de.topicmapslab.tmcledit.model.SubjectLocatorConstraint;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.TypeNode;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;
import de.topicmapslab.tmcledit.model.index.TopicIndexer;

/**
 * @author mai00ckx
 * 
 */
class Test {

	/**
	 * @param args
	 */
	@SuppressWarnings("unchecked")
    public static void main(String[] args) {
		try {
			ModelFactory fac = ModelFactory.eINSTANCE;
			File file = fac.createFile();
			file.setTopicMapSchema(fac.createTopicMapSchema());
			file.setFilename("/home/mai00ckx/test.ono");
			createTestData(file);

			ModelSerializeOno1 s = new ModelSerializeOno1();

			String serialize = s.serialize(file);
			System.out.println(serialize);

			ModelDeserializer ds = new ModelDeserializeOno1();

			File file2 = ds.deserialize(new ByteArrayInputStream(serialize.getBytes("UTF-8")));

			TopicMapSchema s1 = file.getTopicMapSchema();
			TopicMapSchema s2 = file2.getTopicMapSchema();
			// TODO vgl file vs file2

			
			if (new ArrayList<TopicType>(s1.getTopicTypes()).equals(s2.getTopicTypes()))
				System.out.println("Topics are equal");
			else
				System.out.println("Topics are not equal");
				
			if (new ArrayList(s1.getMappings()).equals(s2.getMappings()))
				System.out.println("Mappings are equal");
			else {
				System.out.println("Mappings are not equal");
				System.out.println("1: "+s1.getMappings());
				System.out.println("2: "+s2.getMappings());
			}
			
			if (s1.equals(s2))
				System.out.println("Schema is equal");
			else
				System.out.println("Schema not equal");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
 
	}

	protected static final void createTestData(File file) {
		ModelFactory fac = ModelFactory.eINSTANCE;
		ModelIndexer.createInstance(file);
		TopicIndexer idx = ModelIndexer.getTopicIndexer();

		TopicMapSchema schema = file.getTopicMapSchema();
		
		schema.setComment("Das ist ein Kommentar zum, Schema");

		Diagram diagram = fac.createDiagram();
		diagram.setName("diagram 1");
		file.getDiagrams().add(diagram);

		MappingElement me = fac.createMappingElement();
		me.setKey("foo");
		me.setValue("http://psi.foo.com");
		schema.getMappings().add(me);

		me = fac.createMappingElement();
		me.setKey("tmcl");
		me.setValue("http://psi.topicmaps.org/tmcl/");
		schema.getMappings().add(me);

		TopicType tt = idx.createTopicType(KindOfTopicType.TOPIC_TYPE);
		tt.setName("Person");
		tt.getIdentifiers().add("foo:Person");
		addType(schema, tt);
		tt.getExtension().put("Hannes", "Rulz");

		SubjectIdentifierConstraint sic = fac.createSubjectIdentifierConstraint();
		sic.setCardMin("1");
		sic.setCardMax("1");
		sic.setRegexp("Hannes .*");
		tt.getSubjectIdentifierConstraints().add(sic);

		SubjectLocatorConstraint slc = fac.createSubjectLocatorConstraint();
		slc.setCardMin("1");
		slc.setCardMax("1");
		slc.setRegexp("ort:.*");
		tt.getSubjectLocatorConstraint().add(slc);

		TopicType tt2 = idx.createTopicType(KindOfTopicType.TOPIC_TYPE);
		tt2.setName("Boss");
		tt2.getIdentifiers().add("foo:Boss");
		tt2.getIsa().add(tt);
		addType(schema, tt2);

		TypeNode tn = fac.createTypeNode();
		tn.setPosX(50);
		tn.setPosY(50);
		tn.setTopicType(tt);
		diagram.getNodes().add((Node) tn);

		TypeNode tn2 = fac.createTypeNode();
		tn2.setPosX(450);
		tn2.setPosY(50);
		tn2.setTopicType(tt2);
		diagram.getNodes().add((Node) tn2);

		Edge e = fac.createEdge();
		e.setSource(tn2);
		e.setTarget(tn);
		e.setType(EdgeType.IS_ATYPE);
		diagram.getEdges().add(e);

		TopicType ot = idx.createTopicType(KindOfTopicType.OCCURRENCE_TYPE);
		ot.setName("Addresse");
		ot.getIdentifiers().add("foo:Address");
		addType(schema, ot);

		OccurrenceTypeConstraint otc = fac.createOccurrenceTypeConstraint();
		otc.setCardMax("*");
		otc.setCardMin("0");
		otc.setType(ot);
		tt.getOccurrenceConstraints().add(otc);

		TopicType nt = idx.createTopicType(KindOfTopicType.NAME_TYPE);
		nt.setName("Firstname");
		nt.getIdentifiers().add("foo:Firstname");
		addType(schema, nt);

		NameTypeConstraint ntc = fac.createNameTypeConstraint();
		ntc.setType(nt);
		tt.getNameContraints().add(ntc);

		TopicType rt1 = idx.createTopicType(KindOfTopicType.ROLE_TYPE);
		rt1.setName("Employee");
		rt1.getIdentifiers().add("foo:Employee");
		addType(schema, rt1);

		TopicType rt2 = idx.createTopicType(KindOfTopicType.ROLE_TYPE);
		rt2.setName("Employer");
		rt2.getIdentifiers().add("foo:Employer");
		addType(schema, rt2);

		TopicType ws = idx.createTopicType(KindOfTopicType.TOPIC_TYPE);
		ws.getLocators().add("http://wikipedia.de/website");
		ws.setName("Website");
		ws.setAbstract(true);
		addType(schema, ws);

		// try a association
		AssociationType at = (AssociationType) idx.createTopicType(KindOfTopicType.ASSOCIATION_TYPE);
		at.setName("works-for");
		addType(schema, at);

		TopicType date = idx.createTopicType(KindOfTopicType.TOPIC_TYPE);
		date.setName("date");
		addType(schema, date);

		ReifierConstraint rc = fac.createReifierConstraint();
		rc.setCardMin("0");
		rc.setCardMax("1");
		rc.setComment("Refies date of employment");
		rc.setType(date);

		at.setReifierConstraint(rc);

		AssociationTypeConstraint atc = fac.createAssociationTypeConstraint();
		atc.setType(at);
		schema.getAssociationTypeConstraints().add(atc);
		
		RoleConstraint employee = fac.createRoleConstraint();
		employee.setCardMin("1");
		employee.setCardMax("1");
		employee.setType(rt1);

		RoleConstraint employer = fac.createRoleConstraint();
		employer.setCardMin("1");
		employer.setCardMax("1");
		employer.setType(rt2);

		at.getRoles().add(employee);
		at.getRoles().add(employer);

		
		RolePlayerConstraint rpc = fac.createRolePlayerConstraint();
		rpc.setCardMin("1");
		rpc.setCardMax("1");
		rpc.setRole(employer);
		rpc.setPlayer(tt2);
		atc.getPlayerConstraints().add(rpc);
		
		rpc = fac.createRolePlayerConstraint();
		rpc.setCardMin("1");
		rpc.setCardMax("*");
		rpc.setRole(employee);
		rpc.setPlayer(tt);
		atc.getPlayerConstraints().add(rpc);
	}

	private static void addType(TopicMapSchema schema, TopicType type) {
		schema.getTopicTypes().add(type);
	}
}
