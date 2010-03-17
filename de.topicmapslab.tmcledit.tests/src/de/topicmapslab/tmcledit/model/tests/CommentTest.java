/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.tests;

import de.topicmapslab.tmcledit.model.Comment;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.compare.CommentComparator;

import junit.framework.Assert;
import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc --> A test case for the model object '
 * <em><b>Comment</b></em>'. <!-- end-user-doc -->
 * 
 * @generated
 */
public class CommentTest extends NodeTest {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(CommentTest.class);
	}

	/**
	 * Constructs a new Comment test case with the given name. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public CommentTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Comment test case. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected Comment getFixture() {
		return (Comment) fixture;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(ModelFactory.eINSTANCE.createComment());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 * @generated
	 */
	@Override
	protected void tearDown() throws Exception {
		setFixture(null);
	}

	public void testComent() {

		Comment testObject1 = ModelFactory.eINSTANCE.createComment();
		Comment testObject2 = ModelFactory.eINSTANCE.createComment();

		CommentComparator comp = new CommentComparator();

		// NULL test
		super.nullTest(testObject1, comp);

		// set id
		super.idTest(testObject1, testObject2, comp);

		// set posX and posY
		super.positionTest(testObject1, testObject2, comp);
		
		// set content
		testObject1.setContent("TMCL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		testObject2.setContent("TMCL");
		Assert.assertTrue(comp.equals(testObject1, testObject2));
		
		// set height
		testObject1.setHeight(1);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		testObject2.setHeight(1);
		Assert.assertTrue(comp.equals(testObject1, testObject2));
		
		// set width
		testObject1.setWidth(1);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		testObject2.setWidth(1);
		Assert.assertTrue(comp.equals(testObject1, testObject2));
		
		
	}

} // CommentTest
