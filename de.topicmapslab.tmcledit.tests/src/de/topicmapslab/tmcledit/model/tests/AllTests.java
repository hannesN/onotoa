package de.topicmapslab.tmcledit.model.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for de.topicmapslab.tmcledit.model.tests");
		//$JUnit-BEGIN$
		suite.addTestSuite(MappingElementTest.class);
		suite.addTestSuite(TMCLConstructTest.class);
		suite.addTestSuite(AnnotationTest.class);
		suite.addTestSuite(NodeTest.class);
		suite.addTestSuite(BendpointTest.class);
		suite.addTestSuite(LabelPosTest.class);
		suite.addTestSuite(CommentTest.class);
		//$JUnit-END$
		return suite;
	}

}
