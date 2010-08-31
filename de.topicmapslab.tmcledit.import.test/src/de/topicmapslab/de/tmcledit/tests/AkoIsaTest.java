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
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;


/**
 * @author Hannes Niederhausen
 *
 */
public class AkoIsaTest extends AbstractImportTest{

	@BeforeClass
	public static void init() throws IOException, TMAPIException {
		loadTopicMap("resources/isa_ako.xtm");
		
	}
	
	@Test
	public void countType() {
		assertEquals(3, getFile().getTopicMapSchema().getTopicTypes().size());
	}
	
	@Test
	public void checkTopicType() {
		List<TopicType> ttList = ModelIndexer.getTopicIndexer().getTypesByKind(KindOfTopicType.TOPIC_TYPE);
		assertEquals(3, ttList.size());
		
		for (TopicType tt : ttList) {
			assertNotNull(tt.getName());
			if ("AbstractTest1".equals(tt.getName())) {
				
				assertTrue(tt.isAbstract());
				
				assertEquals(1, tt.getIdentifiers().size());
				assertEquals(0, tt.getIsa().size());
				assertEquals("http://testmap.de/abstracttest1", tt.getIdentifiers().get(0));
				
			} else if ("Test1".equals(tt.getName())) {
				assertFalse(tt.isAbstract());
				assertEquals(1, tt.getIdentifiers().size());
				assertEquals("http://testmap.de/test1", tt.getIdentifiers().get(0));
				
				assertEquals(1, tt.getAko().size());
				assertEquals(0, tt.getIsa().size());
				assertEquals("AbstractTest1", tt.getAko().get(0).getName());
			} else if ("Instance".equals(tt.getName())) {
				assertFalse(tt.isAbstract());
				assertEquals(1, tt.getIdentifiers().size());
				assertEquals("http://testmap.de/instance", tt.getIdentifiers().get(0));
				
				assertEquals(1, tt.getIsa().size());
				assertEquals("Test1", tt.getIsa().get(0).getName());
			}
		}
		
	}
}
