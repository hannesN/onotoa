/**
 * 
 */
package de.topicmapslab.de.tmcledit.tests;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.tmapi.core.TMAPIException;

import de.topicmapslab.tmcledit.model.AssociationType;
import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.NameType;
import de.topicmapslab.tmcledit.model.OccurrenceType;
import de.topicmapslab.tmcledit.model.RoleType;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;


/**
 * @author Hannes Niederhausen
 *
 */
public class TypesTest extends AbstractImportTest{

	@BeforeClass
	public static void init() throws IOException, TMAPIException {
		loadTopicMap("resources/typestest.ctm");
		
	}
	
	@Test
	public void countType() {
		assertEquals(5, getFile().getTopicMapSchema().getTopicTypes().size());
	}
	
	@Test
	public void checkTopicType() {
		List<TopicType> ttList = ModelIndexer.getTopicIndexer().getTypesByKind(KindOfTopicType.TOPIC_TYPE);
		assertEquals(1, ttList.size());
		
		TopicType tt = ttList.get(0);
		
		assertEquals("test", tt.getName());
		assertEquals(1, tt.getIdentifiers().size());
		assertEquals("http://lala.de/test", tt.getIdentifiers().get(0));
		
	}
	
	@Test
	public void checkNameType() {
		List<TopicType> ttList = ModelIndexer.getTopicIndexer().getTypesByKind(KindOfTopicType.NAME_TYPE);
		assertEquals(1, ttList.size());
		
		assertTrue(ttList.get(0) instanceof NameType);
		NameType tt = (NameType) ttList.get(0);
		
		assertEquals("Name", tt.getName());
		assertEquals(1, tt.getIdentifiers().size());
		assertEquals("http://lala.de/name", tt.getIdentifiers().get(0));
		
	}
	
	@Test
	public void checkRoleType() {
		List<TopicType> ttList = ModelIndexer.getTopicIndexer().getTypesByKind(KindOfTopicType.ROLE_TYPE);
		assertEquals(1, ttList.size());
		
		assertTrue(ttList.get(0) instanceof RoleType);
		RoleType tt = (RoleType) ttList.get(0);
		
		assertEquals("testRole", tt.getName());
		assertEquals(1, tt.getIdentifiers().size());
		assertEquals("http://lala.de/testrole", tt.getIdentifiers().get(0));
		
	}
	
	@Test
	public void checkAssociationType() {
		List<TopicType> ttList = ModelIndexer.getTopicIndexer().getTypesByKind(KindOfTopicType.ASSOCIATION_TYPE);
		assertEquals(1, ttList.size());
		
		assertTrue(ttList.get(0) instanceof AssociationType);
		AssociationType tt = (AssociationType) ttList.get(0);
		
		assertEquals("testAssoc", tt.getName());
		assertEquals(1, tt.getIdentifiers().size());
		assertEquals("http://lala.de/testassoc", tt.getIdentifiers().get(0));
		
	}
	
	@Test
	public void checkOccurrenceType() {
		List<TopicType> ttList = ModelIndexer.getTopicIndexer().getTypesByKind(KindOfTopicType.OCCURRENCE_TYPE);
		assertEquals(1, ttList.size());
		
		assertTrue(ttList.get(0) instanceof OccurrenceType);
		OccurrenceType tt = (OccurrenceType) ttList.get(0);
		
		assertEquals("testOcc", tt.getName());
		assertEquals(1, tt.getIdentifiers().size());
		assertEquals("http://lala.de/testocc", tt.getIdentifiers().get(0));
		
	}
}
