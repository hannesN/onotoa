package de.topicmapslab.onotoa.instanceeditor.model.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;

import java.util.Set;

import org.junit.Test;

import de.topicmapslab.onotoa.instanceeditor.model.Association;
import de.topicmapslab.onotoa.instanceeditor.model.Role;
import de.topicmapslab.onotoa.instanceeditor.model.Topic;

/**
 * test class for {@link Role}
 * @author Christian Haß
 *
 */
public class TestRole extends AbstractModelTest {

	/**
	 * test for getAssociation()
	 */
	@Test
	public void testGetAssociation(){
		
		Association association = this.map.createAssociation();
		assertNotNull(association);
		
		Topic player = this.map.createTopic();
		assertNotNull(player);
		
		Role role = association.createRole(player);
		assertNotNull(role);
		
		Association a = role.getAssociation();
		assertNotNull(a);
		assertEquals(a, association);
		
	}
	
	/**
	 * test for getPlayer()
	 */
	@Test
	public void testGetPlayer(){
		
		Association association = this.map.createAssociation();
		assertNotNull(association);
		
		Topic player = this.map.createTopic();
		assertNotNull(player);
		
		Role role = association.createRole(player);
		assertNotNull(role);
		
		Topic p = role.getPlayer();
		assertNotNull(p);
		assertEquals(p, player);
	}
	
	/**
	 * test for getType()
	 */
	@Test
	public void testGetType(){

		Association association = this.map.createAssociation();
		assertNotNull(association);
		
		Topic player = this.map.createTopic();
		assertNotNull(player);
		
		Role role = association.createRole(player);
		assertNotNull(role);
		
		Topic type = this.map.createTopic();
		assertNotNull(type);
		
		role.setType(type);
		
		assertEquals(type, role.getType());
		
	}
	
	/**
	 * test for setType()
	 */
	@Test
	public void testSetType(){
		
		Association association = this.map.createAssociation();
		assertNotNull(association);
		
		Topic player = this.map.createTopic();
		assertNotNull(player);
		
		Role role = association.createRole(player);
		assertNotNull(role);
		
		Topic type = this.map.createTopic();
		assertNotNull(type);
		
		assertNotSame(type, role.getType());
				
		role.setType(type);
		
		assertEquals(type, role.getType());
		
	}
	
	/**
	 * test for addItemIdentifier() 
	 */
	@Test
	public void testAddItemIdentifier(){

		Association association = this.map.createAssociation();
		assertNotNull(association);
		
		Topic player = this.map.createTopic();
		assertNotNull(player);
		
		Role role = association.createRole(player);
		assertNotNull(role);
		
		String iri = "http://test/iri";
		
		role.addItemIdentifier(iri);
		
		Set<String>  iis = role.getItemIdentifier();
		assertNotNull(iis);
		assertEquals(1, iis.size());
		assertEquals(iri, iis.iterator().next());
		
	}
	
	/**
	 * test for getItemIdentifier() 
	 */
	@Test
	public void testGetItemIdentifier(){

		Association association = this.map.createAssociation();
		assertNotNull(association);
		
		Topic player = this.map.createTopic();
		assertNotNull(player);
		
		Role role = association.createRole(player);
		assertNotNull(role);
		
		String iri = "http://test/iri";
		
		Set<String>  iis = role.getItemIdentifier();
		assertNotNull(iis);
		assertEquals(0, iis.size());
		
		role.addItemIdentifier(iri);
		
		iis = role.getItemIdentifier();
		assertNotNull(iis);
		assertEquals(1, iis.size());
		assertEquals(iri, iis.iterator().next());
		
	}

	
	/**
	 * test for setReifier() 
	 */
	@Test
	public void testSetReifier(){

		Association association = this.map.createAssociation();
		assertNotNull(association);
		
		Topic player = this.map.createTopic();
		assertNotNull(player);
		
		Role role = association.createRole(player);
		assertNotNull(role);
		
		assertNull(role.getReifier());
		
		Topic reifier = this.map.createTopic();
		assertNotNull(reifier);
		
		role.setReifier(reifier);
		
		assertNotNull(role.getReifier());
		assertEquals(reifier, role.getReifier());
	}
	
	/**
	 * test for getReifier() 
	 */
	@Test
	public void testGetReifier(){

		Association association = this.map.createAssociation();
		assertNotNull(association);
		
		Topic player = this.map.createTopic();
		assertNotNull(player);
		
		Role role = association.createRole(player);
		assertNotNull(role);
		
		Topic reifier = this.map.createTopic();
		assertNotNull(reifier);
		
		role.setReifier(reifier);
		
		assertNotNull(role.getReifier());
		assertEquals(reifier, role.getReifier());
	}
	
}
