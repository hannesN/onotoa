/**
 * 
 */
package de.topicmapslab.de.tmcledit.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.tmapi.core.TMAPIException;

import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.TopicReifiesConstraint;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;

/**
 * tests reifier
 * @author Hannes Niederhausen
 *
 */
public class ReifierTest extends AbstractImportTest {

	/**
	 * loading topic map
	 */
	@BeforeClass
	public static void init() throws IOException, TMAPIException {
		loadTopicMap("resources/reifier.xtm");

	}

	/**
	 * checks number of types
	 */
	@Test
	public void countType() {
		assertEquals(4, getFile().getTopicMapSchema().getTopicTypes().size());
	}

	/**
	 * checks topic types
	 */
	@Test
	public void checkTopicType() {
		List<TopicType> ttList = ModelIndexer.getTopicIndexer().getTypesByKind(
				KindOfTopicType.TOPIC_TYPE);
		assertEquals(1, ttList.size());

		TopicType tt = ttList.get(0);
		assertEquals("TestReifier", tt.getName());
		assertEquals(1, tt.getIdentifiers().size());
		assertEquals("http://testmap.de/testreifier", tt.getIdentifiers()
				.get(0));
		assertEquals(3, tt.getTopicReifiesConstraints().size());
	}

	/**
	 * checks association reifier
	 */
	@Test
	public void checkAssocReifierConstraint() {
		List<TopicType> ttList = ModelIndexer.getTopicIndexer().getTypesByKind(
				KindOfTopicType.TOPIC_TYPE);
		assertEquals(1, ttList.size());

		TopicType tt = ttList.get(0);
		assertEquals("TestReifier", tt.getName());
		assertEquals(3, tt.getTopicReifiesConstraints().size());
		for (TopicReifiesConstraint trc : tt.getTopicReifiesConstraints()) {
			assertNotNull(trc.getType());
			if (trc.getType().getKind() == KindOfTopicType.ASSOCIATION_TYPE)
				checkType(trc, "NotReifiedAssoc", 0, 0);
		}

	}

	/**
	 * checks name reifier
	 */
	@Test
	public void checkNameReifierConstraint() {
		List<TopicType> ttList = ModelIndexer.getTopicIndexer().getTypesByKind(
				KindOfTopicType.TOPIC_TYPE);
		assertEquals(1, ttList.size());

		TopicType tt = ttList.get(0);
		assertEquals("TestReifier", tt.getName());
		assertEquals(3, tt.getTopicReifiesConstraints().size());
		for (TopicReifiesConstraint trc : tt.getTopicReifiesConstraints()) {
			assertNotNull(trc.getType());
			if (trc.getType().getKind() == KindOfTopicType.NAME_TYPE)
				checkType(trc, "ReifiedName", 0, 1);
		}

	}

	/**
	 * checks occurrence reifier
	 */
	@Test
	public void checkOccurrenceReifierConstraint() {
		List<TopicType> ttList = ModelIndexer.getTopicIndexer().getTypesByKind(
				KindOfTopicType.TOPIC_TYPE);
		assertEquals(1, ttList.size());

		TopicType tt = ttList.get(0);
		assertEquals("TestReifier", tt.getName());
		assertEquals(3, tt.getTopicReifiesConstraints().size());
		for (TopicReifiesConstraint trc : tt.getTopicReifiesConstraints()) {
			assertNotNull(trc.getType());
			if (trc.getType().getKind() == KindOfTopicType.OCCURRENCE_TYPE)
				checkType(trc, "ReifiedOccurrence", 1, 1);
		}

	}

	private void checkType(TopicReifiesConstraint trc, String name,
			int cardMin, int cardMax) {
		TopicType tt = trc.getType();
		assertEquals(name, tt.getName());
		assertEquals(1, tt.getIdentifiers().size());
		assertEquals("http://testmap.de/" + name.toLowerCase(), tt
				.getIdentifiers().get(0));

		assertEquals(Integer.toString(cardMin), trc.getCardMin());
		assertEquals(Integer.toString(cardMax), trc.getCardMax());

	}
}
