package de.topicmapslab.de.tmcledit.tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.tmapi.core.TMAPIException;

import de.topicmapslab.tmcledit.model.TopicType;

public class OverlapsTest extends AbstractImportTest {

	@BeforeClass
	public static void init() throws IOException, TMAPIException {
		loadTopicMap("resources/overlaps.xtm");
		
	}
	
	@Test
	public void countType() {
		assertEquals(2, getFile().getTopicMapSchema().getTopicTypes().size());
	}
	
	@Test
	public void testOverlaps() {
		for (TopicType tt : getFile().getTopicMapSchema().getTopicTypes()) {
			assertEquals(1, tt.getOverlap().size());
		}
	}
}
