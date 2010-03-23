/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.tests;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import de.topicmapslab.tmcledit.model.Annotation;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.TMCLConstruct;
import de.topicmapslab.tmcledit.model.compare.TMCLConstructComperator;

/**
 * <!-- begin-user-doc --> A test case for the model object '
 * <em><b>TMCL Construct</b></em>'. <!-- end-user-doc -->
 * 
 * @generated
 */

public class TMCLConstructTest {

	private TMCLConstruct testObject1;
	private TMCLConstruct testObject2;
	private TMCLConstructComperator comp;

	@Test
	public void checkId() {
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		testObject1 = ModelFactory.eINSTANCE.createTMCLConstruct();
		testObject2 = ModelFactory.eINSTANCE.createTMCLConstruct();

		Assert.assertFalse(comp.equals(testObject1, testObject2));
	}

	@Test
	public void seeAlsoTest() {
		testObject1.setSee_also("TMCL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		testObject2.setSee_also("TMCL");
		Assert.assertTrue(comp.equals(testObject1, testObject2));
	}

	@Test
	public void descriptionTest() {
		testObject1.setDescription("TMCL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		testObject2.setDescription("TMCL");
		Assert.assertTrue(comp.equals(testObject1, testObject2));
	}

	@Test
	public void commentTest() {
		testObject1.setComment("TMCL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		testObject2.setComment("TMCL");
		Assert.assertTrue(comp.equals(testObject1, testObject2));
	}

	@Test
	public void annotationTest() {

		Annotation annotation1 = ModelFactory.eINSTANCE.createAnnotation();
		Annotation annotation2 = ModelFactory.eINSTANCE.createAnnotation();

		// add annotation to testObject1
		annotation1.setKey("TMCL");
		annotation1.setValue("TMCL");
		testObject1.getAnnotations().add(annotation1);
		Assert.assertFalse(comp.equals(testObject1, testObject2));

		// add a different annotation to testObject2
		annotation2.setId(annotation1.getId());
		annotation2.setKey("TMC");
		annotation2.setValue("TMC");
		testObject2.getAnnotations().add(annotation2);
		Assert.assertFalse(comp.equals(testObject1, testObject2));

		// make both annotations the same
		annotation2.setKey("TMCL");
		annotation2.setValue("TMCL");
		Assert.assertTrue(comp.equals(testObject1, testObject2));
	}
} // TMCLConstructTest
