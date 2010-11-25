/**
 * 
 */
package de.topicmapslab.de.tmcledit.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.tmapi.core.TMAPIException;

import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.NameType;
import de.topicmapslab.tmcledit.model.OccurrenceType;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;


/**
 * tests import of characteristics
 * 
 * @author Hannes Niederhausen
 *
 */
public class CharacteristicsTest extends AbstractImportTest{

	/**
	 * Loads topic map
	 * @throws IOException
	 * @throws TMAPIException
	 */
	@BeforeClass
	public static void init() throws IOException, TMAPIException {
		loadTopicMap("resources/characteristicstest.xtm");
		
	}
	
	/**
	 * Counts types
	 */
	@Test
	public void countType() {
		assertEquals(4, getFile().getTopicMapSchema().getTopicTypes().size());
	}
	
	/**
	 * Checks topic types
	 */
	@Test
	public void checkTopicType() {
		List<TopicType> ttList = ModelIndexer.getTopicIndexer().getTypesByKind(KindOfTopicType.TOPIC_TYPE);
		assertEquals(2, ttList.size());
		
		for (TopicType tt : ttList) {
			assertNotNull(tt.getName());
			if ("test".equals(tt.getName())) {
				
				assertFalse(tt.isAbstract());
				
				assertEquals(1, tt.getIdentifiers().size());
				assertEquals("http://lala.de/test", tt.getIdentifiers().get(0));
				
				assertEquals(tt.getName()+":",1, tt.getNameConstraints().size());
				
				assertEquals(tt.getName()+":", 1, tt.getOccurrenceConstraints().size());
				
				assertEquals(tt.getName()+":", 1, tt.getSubjectIdentifierConstraints().size());
				
				assertEquals(tt.getName()+":", 1, tt.getSubjectLocatorConstraints().size());
				
			} else {
				assertTrue(tt.isAbstract());
				assertEquals("Test2", tt.getName());
				assertEquals(1, tt.getIdentifiers().size());
				assertEquals("http://lala.de/test2", tt.getIdentifiers().get(0));
				
				assertEquals(tt.getName()+":", 1, tt.getNameConstraints().size());
				
				assertEquals(tt.getName()+":", 1, tt.getOccurrenceConstraints().size());
			}
		}
		
	}
	
	/**
	 * chacks names
	 */
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
	
	/**
	 * checks occurrences
	 */
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
