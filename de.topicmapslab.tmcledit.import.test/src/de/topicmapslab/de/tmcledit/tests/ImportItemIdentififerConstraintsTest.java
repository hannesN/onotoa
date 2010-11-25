package de.topicmapslab.de.tmcledit.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.tmapi.core.TMAPIException;

import de.topicmapslab.tmcledit.model.ItemIdentifierConstraint;
import de.topicmapslab.tmcledit.model.TopicType;

public class ImportItemIdentififerConstraintsTest extends AbstractImportTest {
	
	@BeforeClass
	public static void init() throws IOException, TMAPIException {
		loadTopicMap("resources/itemidcontstr.xtm");
	}
	
	@Test
	public void countType() {
		assertEquals(2, getFile().getTopicMapSchema().getTopicTypes().size());
	}
	
	@Test
	public void testDefaultCardConstraint() {
		for (TopicType tt : getFile().getTopicMapSchema().getTopicTypes()) {
			assertNotNull(tt.getName());
			if (tt.getName().equals("Test1")) {
				assertEquals(1, tt.getItemIdentifierConstraints().size());
				ItemIdentifierConstraint iic = tt.getItemIdentifierConstraints().get(0);
				assertEquals("0", iic.getCardMin());
				assertEquals("*", iic.getCardMax());
				assertEquals(".*", iic.getRegexp());
			}
		}
	}

	/*
	 * Tests the iic of test2 with regexp and card [1,4]
	 */
	@Test
	public void testValuesConstraint() {
		for (TopicType tt : getFile().getTopicMapSchema().getTopicTypes()) {
			assertNotNull(tt.getName());
			if (tt.getName().equals("Test2")) {
				assertEquals(1, tt.getItemIdentifierConstraints().size());
				ItemIdentifierConstraint iic = tt
						.getItemIdentifierConstraints().get(0);
				assertEquals("1", iic.getCardMin());
				assertEquals("4", iic.getCardMax());
				assertEquals("urn:.*", iic.getRegexp());
			}
		}
	}
}
