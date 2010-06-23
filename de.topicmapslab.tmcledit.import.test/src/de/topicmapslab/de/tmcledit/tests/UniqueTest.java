package de.topicmapslab.de.tmcledit.tests;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.tmapi.core.TMAPIException;

import de.topicmapslab.tmcledit.model.AbstractUniqueValueTopicType;
import de.topicmapslab.tmcledit.model.TopicType;

public class UniqueTest extends AbstractImportTest {

	@BeforeClass
	public static void init() throws IOException, TMAPIException {
		loadTopicMap("resources/unique.xtm");
		
	}
	
	@Test
	public void countType() {
		assertEquals(4, getFile().getTopicMapSchema().getTopicTypes().size());
	}
	
	@Test
	public void testUnique() {
		for (TopicType tt : getFile().getTopicMapSchema().getTopicTypes()) {
			assertTrue("InstanceCheck", tt instanceof AbstractUniqueValueTopicType);
			assertNotNull("Name", tt.getName());
			
			if (tt.getName().startsWith("Non")) {
				assertFalse(tt.getName()+":",((AbstractUniqueValueTopicType) tt).isUnique());
			} else {
				assertTrue(tt.getName()+":",((AbstractUniqueValueTopicType) tt).isUnique());
			}
			
		}
	}
}
