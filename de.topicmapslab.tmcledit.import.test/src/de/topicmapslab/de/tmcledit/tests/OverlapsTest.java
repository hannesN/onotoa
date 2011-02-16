package de.topicmapslab.de.tmcledit.tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.tmapi.core.TMAPIException;

import de.topicmapslab.tmcledit.model.TopicType;

/**
 * tests overlaps import
 * 
 * @author Hannes Niederhausen
 *
 */
public class OverlapsTest extends AbstractImportTest {

	/**
	 * loads topic map
	 * @throws IOException
	 * @throws TMAPIException
	 */
	@BeforeClass
	public static void init() throws IOException, TMAPIException {
		loadTopicMap("resources/overlaps.xtm");
		
	}
	
	/**
	 * checks number of types
	 */
	@Test
	public void countType() {
		assertEquals(2, getFile().getTopicMapSchema().getTopicTypes().size());
	}
	
	/**
	 * checks overlaps
	 */
	@Test
	public void testOverlaps() {
		for (TopicType tt : getFile().getTopicMapSchema().getTopicTypes()) {
			assertEquals(1, tt.getOverlap().size());
		}
	}
}
