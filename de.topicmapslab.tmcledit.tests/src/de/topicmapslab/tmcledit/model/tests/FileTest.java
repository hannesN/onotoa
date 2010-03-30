/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.tests;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.topicmapslab.tmcledit.model.Comment;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.compare.FileComparator;

public class FileTest extends OnoObjectTest {

	private File testObject1;
	private File testObject2;
	private FileComparator comp;

	@Before
	public void prepare() {

		if (testObject1 == null)
			testObject1 = ModelFactory.eINSTANCE.createFile();
		if (testObject2 == null)
			testObject2 = ModelFactory.eINSTANCE.createFile();

		comp = new FileComparator();
	}

	@After
	public void shutdown() {
		testObject1 = null;
		testObject2 = null;
	}

	/**
	 * Test compares Object with NULL and proves the equality of two NULL
	 * Objects
	 */

	@Test
	public void nullTest() {

		Assert.assertFalse(comp.equals(testObject1, null));
		Assert.assertFalse(comp.equals(null, testObject1));
		Assert.assertTrue(comp.equals(null, null));
	}

	/**
	 * Test compares two objects with different and same IDs.
	 */

	@Test
	public void idTest() {

		Assert.assertFalse(comp.equals(testObject1, testObject2));
		testObject1.setId(testObject2.getId());
		Assert.assertTrue(comp.equals(testObject1, testObject2));
	}

	/**
	 * The Aguilera test
	 */

	@Test
	public void dirtyTest() {

		testObject1.setId(testObject2.getId());

		testObject1.setDirty(false);
		testObject2.setDirty(true);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		testObject2.setDirty(false);
		Assert.assertTrue(comp.equals(testObject1, testObject2));

	}

	/**
	 * Test compares Objects with different and same filenames
	 */

	@Test
	public void filenameTest() {

		testObject1.setId(testObject2.getId());

		testObject1.setFilename("TMCL");
		testObject2.setFilename("TMQL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		testObject2.setFilename("TMCL");
		Assert.assertTrue(comp.equals(testObject1, testObject2));

	}

	/**
	 * Test compares Objects with different and same nodes
	 */

	@Test
	public void nodesTest() {

		testObject1.setId(testObject2.getId());

		testObject1.setNotes("TMCL");
		testObject2.setNotes("TMQL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		testObject2.setNotes("TMCL");
		Assert.assertTrue(comp.equals(testObject1, testObject2));

	}
	
	/**
	 * Test compares Objects with different and same topic map schemas
	 */
	
	@Test
	public void topicMapSchemaTest(){
		
		testObject1.setId(testObject2.getId());

		TopicMapSchema mapSchema1 = ModelFactory.eINSTANCE.createTopicMapSchema();
		TopicMapSchema mapSchema2 = ModelFactory.eINSTANCE.createTopicMapSchema();
		
		// set the same map schema in both lists, what is not possible
		Assert.assertTrue(comp.equals(testObject1, testObject2));
		testObject1.setTopicMapSchema(mapSchema1);
		testObject2.setTopicMapSchema(mapSchema1);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		
		// set different map schemas
		testObject1.setTopicMapSchema(mapSchema2);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		
		// make the map schemas equal
		mapSchema1.setId(mapSchema2.getId());
		Assert.assertTrue(comp.equals(testObject1, testObject2));
		
		// edit some attributes
		mapSchema1.setBaseLocator("TMCL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		mapSchema2.setBaseLocator("TMQL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		mapSchema2.setBaseLocator("TMCL");
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		mapSchema1.setName("TMCL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		mapSchema2.setName("TMQL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		mapSchema2.setName("TMCL");
		Assert.assertTrue(comp.equals(testObject1, testObject2));

	}

	@Test
	public void diagramTest(){
		
		testObject1.setId(testObject2.getId());

		Diagram dia1 = ModelFactory.eINSTANCE.createDiagram();
		Diagram dia2 = ModelFactory.eINSTANCE.createDiagram();
		Diagram dia3 = ModelFactory.eINSTANCE.createDiagram();
		Diagram dia4 = ModelFactory.eINSTANCE.createDiagram();
		
		// add the same diagram in both lists, what is impossible
		Assert.assertTrue(comp.equals(testObject1, testObject2));
		testObject1.getDiagrams().add(dia1);
		testObject2.getDiagrams().add(dia1);
		Assert.assertFalse(comp.equals(testObject1, testObject2));

		
		// add different diagrams in both lists
		testObject1.getDiagrams().add(dia2);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		
		// make both entries the same
		dia1.setId(dia2.getId());
		Assert.assertTrue(comp.equals(testObject1, testObject2));
		
		// check different list lengths
		testObject1.getDiagrams().add(dia3);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		testObject2.getDiagrams().add(dia4);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		dia3.setId(dia4.getId());
		Assert.assertTrue(comp.equals(testObject1, testObject2));
		
		// change some attributes from entry #2
		dia3.setName("TMCL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		dia4.setName("TMQL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		dia4.setName("TMCL");
		Assert.assertTrue(comp.equals(testObject1, testObject2));
		
		Comment com1 = ModelFactory.eINSTANCE.createComment();
		Comment com2 = ModelFactory.eINSTANCE.createComment();
		
		Assert.assertTrue(comp.equals(testObject1, testObject2));
		dia3.getComments().add(com1);
		dia4.getComments().add(com2);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		
		com1.setId(com2.getId());
		Assert.assertTrue(comp.equals(testObject1, testObject2));
		com1.setPosX(1);
		com2.setPosX(2);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		com2.setPosX(1);
		Assert.assertTrue(comp.equals(testObject1, testObject2));
		


		
		
	}
	
} // FileTest
