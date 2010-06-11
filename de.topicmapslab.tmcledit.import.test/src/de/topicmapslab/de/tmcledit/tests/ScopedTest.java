/**
 * 
 */
package de.topicmapslab.de.tmcledit.tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.tmapi.core.TMAPIException;

import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.NameType;
import de.topicmapslab.tmcledit.model.OccurrenceType;
import de.topicmapslab.tmcledit.model.ScopeConstraint;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;

/**
 * tests import of scope constraints
 * 
 * @author Hannes Niederhausen
 * 
 */
public class ScopedTest extends AbstractImportTest{

	@BeforeClass
	public static void init() throws IOException, TMAPIException {
		loadTopicMap("resources/scoped.xtm");
		
	}
	
	@Test
	public void countType() {
		assertEquals(4, getFile().getTopicMapSchema().getTopicTypes().size());
	}
	
	@Test
	public void nametypeTest() {
		ModelIndexer.createInstance(getFile());
		List<TopicType> nameTypes = ModelIndexer.getTopicIndexer().getTypesByKind(KindOfTopicType.NAME_TYPE);
		
		assertEquals(1, nameTypes.size());
		
		NameType nt = (NameType) nameTypes.get(0);
		
		assertEquals("scopedName", nt.getName());
		assertEquals(1, nt.getIdentifiers().size());
		assertEquals("http://testmap.de/scopedname", nt.getIdentifiers().get(0));
		assertEquals(1, nt.getScope().size());
		
		ScopeConstraint sc = nt.getScope().get(0);
		
		assertEquals("1", sc.getCardMin());
		assertEquals("1", sc.getCardMax());
		
		assertNotNull(sc.getType());
		assertEquals("language", sc.getType().getName());
		assertEquals(1, sc.getType().getIdentifiers().size());
		assertEquals("http://testmap.de/language", sc.getType().getIdentifiers().get(0));
	}
	
	public void occtypeTest() {
		ModelIndexer.createInstance(getFile());
		List<TopicType> occTypes = ModelIndexer.getTopicIndexer().getTypesByKind(KindOfTopicType.OCCURRENCE_TYPE);
		
		assertEquals(1, occTypes.size());
		
		OccurrenceType ot = (OccurrenceType) occTypes.get(0);
		
		assertEquals("description", ot.getName());
		assertEquals(1, ot.getIdentifiers().size());
		assertEquals("http://testmap.de/description", ot.getIdentifiers().get(0));
		assertEquals(2, ot.getScope().size());
		
		for (ScopeConstraint sc : ot.getScope()) {

			assertNotNull(sc.getType());
			assertEquals(0, sc.getType().getIdentifiers().size());
			
			if (sc.getType().getName().equals("language")) {
				assertEquals("http://testmap.de/language", sc.getType()
						.getIdentifiers().get(0));
				assertEquals("1", sc.getCardMin());
				assertEquals("1", sc.getCardMax());
			} else {
				assertEquals("context", sc.getType().getName());
				assertEquals("http://testmap.de/context", sc.getType().getIdentifiers().get(0));
				assertEquals("0", sc.getCardMin());
				assertEquals("1", sc.getCardMax());
			}
		}
	}
}
