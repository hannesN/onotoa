/**
 * 
 */
package de.topicmapslab.de.tmcledit.tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.junit.BeforeClass;
import org.junit.Test;
import org.tmapi.core.TMAPIException;

import de.topicmapslab.tmcledit.model.AssociationType;
import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.RoleCombinationConstraint;
import de.topicmapslab.tmcledit.model.RoleConstraint;
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;
import de.topicmapslab.tmcledit.model.RoleType;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;

/**
 * @author Hannes Niederhausen
 *
 */
public class RolesTest extends AbstractImportTest {

	@BeforeClass
	public static void init() throws IOException, TMAPIException {
		loadTopicMap("resources/roles.xtm");
		
	}
	@Test
	public void countType() {
		assertEquals(5, getFile().getTopicMapSchema().getTopicTypes().size());
	}
	
	@Test
	public void checkRoles() {
		ModelIndexer.createInstance(getFile());
		List<AssociationType> associationTypes = ModelIndexer.getTopicIndexer().getAssociationTypes();
		
		assertEquals(1, associationTypes.size());
		
		AssociationType at = associationTypes.get(0);
		
		assertEquals("testAssoc", at.getName());
		assertEquals(1, at.getIdentifiers().size());
		assertEquals("http://testmap.de/testassoc", at.getIdentifiers().get(0));
		
		assertEquals(2, at.getRoles().size());
		
		for (RoleConstraint rc : at.getRoles()) {
			assertNotNull("Role Type of constraint", rc.getType());
			RoleType rt = (RoleType) rc.getType();
			assertNotNull("RoleTypeName", rt.getName());
			
			if (rt.getName().equals("testRole1")) {
				assertEquals("1", rc.getCardMin());
				assertEquals("1", rc.getCardMax());
				
			} else {
				assertEquals("testRole2", rt.getName());
				assertEquals("0", rc.getCardMin());
				assertEquals("*", rc.getCardMax());
			}
		}
	}

	@Test
	public void testPlayer() {
		ModelIndexer.createInstance(getFile());
		
		EList<AssociationTypeConstraint> atcList = getFile().getTopicMapSchema().getAssociationTypeConstraints();
		assertEquals(1, atcList.size());
		
		List<AssociationType> associationTypes = ModelIndexer.getTopicIndexer().getAssociationTypes();
		
		assertEquals(1, associationTypes.size());
		
		AssociationType at = associationTypes.get(0);
		
		AssociationTypeConstraint atc = atcList.get(0);
		assertEquals(at, atc.getType());
		
		assertEquals(2, atc.getPlayerConstraints().size());
		for (RolePlayerConstraint rpc : atc.getPlayerConstraints()) {
			TopicType tt  = rpc.getPlayer();
			
			assertNotNull("Player Name", tt.getName());
			
			if (tt.getName().equals("Test1")) {
				assertEquals("0", rpc.getCardMin());
				assertEquals("*", rpc.getCardMax());
			} else {
				assertEquals("1", rpc.getCardMin());	
				assertEquals("1", rpc.getCardMax());
			}
		}
	}
	@Test
	public void testRoleCombination() {
		ModelIndexer.createInstance(getFile());
		
		List<AssociationType> associationTypes = ModelIndexer.getTopicIndexer().getAssociationTypes();
		
		assertEquals(1, associationTypes.size());
		
		AssociationType at = associationTypes.get(0);
		
		assertEquals(1, at.getRoleCombinations().size());
		
		RoleCombinationConstraint rcc = at.getRoleCombinations().get(0);
		
		assertNotNull(rcc.getRole());
		assertEquals("testRole1", rcc.getRole().getName());
		
		assertNotNull(rcc.getPlayer());
		assertEquals("Test1", rcc.getPlayer().getName());
		
		assertNotNull(rcc.getOtherRole());
		assertEquals("testRole2", rcc.getOtherRole().getName());
		
		assertNotNull(rcc.getOtherPlayer());
		assertEquals("Test2", rcc.getOtherPlayer().getName());
		
	}
}
