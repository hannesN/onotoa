/**
 * 
 */
package de.topicmapslab.de.tmcledit.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.tmapi.core.TMAPIException;

import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.NameType;
import de.topicmapslab.tmcledit.model.NameTypeConstraint;
import de.topicmapslab.tmcledit.model.OccurrenceType;
import de.topicmapslab.tmcledit.model.OccurrenceTypeConstraint;
import de.topicmapslab.tmcledit.model.ReifierConstraint;
import de.topicmapslab.tmcledit.model.SubjectIdentifierConstraint;
import de.topicmapslab.tmcledit.model.SubjectLocatorConstraint;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;


/**
 * @author Hannes Niederhausen
 *
 */
public class ReifiedTest extends AbstractImportTest{

	@BeforeClass
	public static void init() throws IOException, TMAPIException {
		loadTopicMap("resources/reified.xtm");
		
	}
	
	@Test
	public void countType() {
		assertEquals(7, getFile().getTopicMapSchema().getTopicTypes().size());
	}
	
	@Test
	public void checkTopicType() {
		List<TopicType> ttList = ModelIndexer.getTopicIndexer().getTypesByKind(KindOfTopicType.TOPIC_TYPE);
		assertEquals(3, ttList.size());
		
		for (TopicType tt : ttList) {
			assertNotNull(tt.getName());
			if ("TestReifier".equals(tt.getName())) {
				
				assertFalse(tt.isAbstract());
				
				assertEquals(1, tt.getIdentifiers().size());
				assertEquals("http://testmap.de/testreifier", tt.getIdentifiers().get(0));
				
			} else if ("Test1".equals(tt.getName())) {
				assertFalse(tt.isAbstract());
				assertEquals(1, tt.getIdentifiers().size());
				assertEquals("http://testmap.de/test1", tt.getIdentifiers().get(0));
				
				assertEquals(1, tt.getNameConstraints().size());
				
				NameTypeConstraint ntc = tt.getNameConstraints().get(0);
				assertEquals("1", ntc.getCardMin());
				assertEquals("1", ntc.getCardMax());
				
				NameType nt = (NameType) ntc.getType();
				assertEquals("testNameMustReify", nt.getName());
				assertEquals("http://testmap.de/testnamemustreify", nt.getIdentifiers().get(0));
				assertEquals(".*", nt.getRegExp());
				
				assertNotNull("Check if reifier constraint exists", nt.getReifierConstraint());
				
				ReifierConstraint rf = nt.getReifierConstraint();
				assertEquals("1", rf.getCardMin());
				assertEquals("1", rf.getCardMax());
				assertEquals("TestReifier", rf.getType().getName());
				
				assertEquals(1, tt.getOccurrenceConstraints().size());
				OccurrenceTypeConstraint otc = tt.getOccurrenceConstraints().get(0);
				assertEquals("0", otc.getCardMin());
				assertEquals("*", otc.getCardMax());
				
				OccurrenceType ot = (OccurrenceType) otc.getType();
				assertEquals("xsd:negativeInteger", ot.getDataType());
				assertNotNull(ot.getReifierConstraint());
				rf = ot.getReifierConstraint();
				assertEquals("0", rf.getCardMin());
				assertEquals("1", rf.getCardMax());
				assertEquals("TestReifier", rf.getType().getName());
				
				assertEquals(1, tt.getSubjectIdentifierConstraints().size());
				SubjectIdentifierConstraint sic = tt.getSubjectIdentifierConstraints().get(0);
				assertEquals("1", sic.getCardMin());
				assertEquals("7", sic.getCardMax());
				assertEquals("http://psi\\..*", sic.getRegexp());
				
			} else if ("Test2".equals(tt.getName())) {
				assertFalse(tt.isAbstract());
				assertEquals(1, tt.getIdentifiers().size());
				assertEquals("http://testmap.de/test2", tt.getIdentifiers().get(0));
				
				assertEquals(1, tt.getNameConstraints().size());
				
				NameTypeConstraint ntc = tt.getNameConstraints().get(0);
				assertEquals("1", ntc.getCardMin());
				assertEquals("5", ntc.getCardMax());
				
				// name type with reifier constraint with card [0,0] 
				NameType nt = (NameType) ntc.getType();
				assertEquals("TestnameCannotReify", nt.getName());
				assertEquals("http://testmap.de/testnamecannotreify", nt.getIdentifiers().get(0));
				assertEquals(".*", nt.getRegExp());
				
				assertNotNull("Check if reifier constraint exists", nt.getReifierConstraint());
				
				ReifierConstraint rf = nt.getReifierConstraint();
				assertEquals("0", rf.getCardMin());
				assertEquals("0", rf.getCardMax());
				assertNull(rf.getType());
				
				assertEquals(1, tt.getOccurrenceConstraints().size());
				OccurrenceTypeConstraint otc = tt.getOccurrenceConstraints().get(0);
				assertEquals("0", otc.getCardMin());
				assertEquals("*", otc.getCardMax());
				
				OccurrenceType ot = (OccurrenceType) otc.getType();
				assertEquals("xsd:negativeInteger", ot.getDataType());
				assertNotNull(ot.getReifierConstraint());
				rf = ot.getReifierConstraint();
				assertEquals("0", rf.getCardMin());
				assertEquals("0", rf.getCardMax());
				assertNull(rf.getType());
				
				assertEquals(1, tt.getSubjectLocatorConstraints().size());
				SubjectLocatorConstraint sic = tt.getSubjectLocatorConstraints().get(0);
				assertEquals("0", sic.getCardMin());
				assertEquals("*", sic.getCardMax());
				assertEquals(".*", sic.getRegexp());
				
			} 
		}
		
	}
}
