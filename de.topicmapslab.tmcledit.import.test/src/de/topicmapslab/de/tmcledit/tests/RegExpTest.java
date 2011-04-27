package de.topicmapslab.de.tmcledit.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.tmapi.core.TMAPIException;

import de.topicmapslab.tmcledit.model.AbstractRegExpTopicType;
import de.topicmapslab.tmcledit.model.AbstractUniqueValueTopicType;
import de.topicmapslab.tmcledit.model.OccurrenceType;
import de.topicmapslab.tmcledit.model.TopicType;

/**
 * 
 * 
 * @author Hannes Niederhausen
 *
 */
public class RegExpTest extends AbstractImportTest {
	/**
	 * loads topic map
	 */
	@BeforeClass
	public static void init() throws IOException, TMAPIException {
		loadTopicMap("resources/regexp.xtm");
	}
	/**
	 * checks number of types
	 */
	@Test
	public void countType() {
		assertEquals(4, getFile().getTopicMapSchema().getTopicTypes().size());
	}
	
	/**
	 * checks unique
	 */
	@Test
	public void testUnique() {
		for (TopicType tt : getFile().getTopicMapSchema().getTopicTypes()) {
			assertTrue("InstanceCheck", tt instanceof AbstractUniqueValueTopicType);
			assertNotNull("Name", tt.getName());
			
			if (tt.getName().startsWith("regExp")) {
				if (tt instanceof OccurrenceType)
					assertEquals(tt.getName(), "\\d+\\.\\d\\d", ((AbstractRegExpTopicType) tt).getRegExp());
				else
					assertEquals(tt.getName(), "Hannes .* Niederhausen", ((AbstractRegExpTopicType) tt).getRegExp());
			} else {
				assertEquals(tt.getName(), ".*", ((AbstractRegExpTopicType) tt).getRegExp());
			}
			
		}
		
		
	}
}
