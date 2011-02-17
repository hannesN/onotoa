package de.topicmapslab.onotoa.instanceeditor.model.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;

import de.topicmapslab.onotoa.instanceeditor.model.Association;
import de.topicmapslab.onotoa.instanceeditor.model.Role;
import de.topicmapslab.onotoa.instanceeditor.model.Topic;

/**
 * test class for {@link Association}
 * @author Christian Haß
 *
 */
public class TestAssociation extends AbstractModelTest {

	/**
	 * test for createRole()
	 */
	@Test
	public void testCreateRole(){
		
		Association association = this.map.createAssociation();
		assertNotNull(association);
		
		Topic player = this.map.createTopic();
		assertNotNull(player);
		
		Role role = association.createRole(player);
		assertNotNull(role);
		
		Set<Role> roles = association.getRoles();
		assertNotNull(roles);
		assertEquals(1, roles.size());
		
		System.out.println(role.getId());
		System.out.println(roles.iterator().next().getId());
		
		assertTrue(roles.contains(role));
		
	}
	
	/**
	 * test for getRoles()
	 */
	@Test
	public void testGetRoles(){
		
		Association association = this.map.createAssociation();
		assertNotNull(association);
		
		Set<Role> roles = association.getRoles();
		assertNotNull(roles);
		assertEquals(0, roles.size());
		
		Topic player = this.map.createTopic();
		assertNotNull(player);
		
		Role role = association.createRole(player);
		assertNotNull(role);
		
		roles = association.getRoles();
		assertNotNull(roles);
		assertEquals(1, roles.size());
		assertTrue(roles.contains(role));
		
	}
	
	/**
	 * test for getRoles(Topic type)
	 */
	@Test
	public void testGetRolesByType(){
		
		Association association = this.map.createAssociation();
		assertNotNull(association);
		
		Topic type1 = this.map.createTopic();
		assertNotNull(type1);
		
		Topic type2 = this.map.createTopic();
		assertNotNull(type2);
		
		Topic player = this.map.createTopic();
		assertNotNull(player);
		
		Role role1 = association.createRole(player);
		assertNotNull(role1);
		role1.setType(type1);
		
		Role role2 = association.createRole(player);
		assertNotNull(role2);
		role2.setType(type2);
		
		Set<Role> roles = association.getRoles();
		assertNotNull(roles);
		assertEquals(2, roles.size());
		
		roles = association.getRoles(type1);
		assertNotNull(roles);
		assertEquals(1, roles.size());
		assertTrue(roles.contains(role1));
		
		roles = association.getRoles(type2);
		assertNotNull(roles);
		assertEquals(1, roles.size());
		assertTrue(roles.contains(role2));
		
	}
	
	/**
	 * test for getOtherRoles()
	 */
	@Test
	public void testGetOtherRoles(){
		
		Association association = this.map.createAssociation();
		assertNotNull(association);
		
		Topic type1 = this.map.createTopic();
		assertNotNull(type1);
		
		Topic type2 = this.map.createTopic();
		assertNotNull(type2);
		
		Topic player = this.map.createTopic();
		assertNotNull(player);
		
		Role role1 = association.createRole(player);
		assertNotNull(role1);
		role1.setType(type1);
		
		Role role2 = association.createRole(player);
		assertNotNull(role2);
		role2.setType(type2);
		
		Set<Role> roles = association.getRoles();
		assertNotNull(roles);
		assertEquals(2, roles.size());
		
		roles = association.getOtherRoles(role1);
		assertNotNull(roles);
		assertEquals(1, roles.size());
		assertTrue(roles.contains(role2));
		
		roles = association.getOtherRoles(role2);
		assertNotNull(roles);
		assertEquals(1, roles.size());
		assertTrue(roles.contains(role1));
		
	}
	
	/**
	 * test for getType()
	 */
	@Test
	public void testGetType(){

		Association association = this.map.createAssociation();
		assertNotNull(association);
		
		Topic type = this.map.createTopic();
		assertNotNull(type);
		
		association.setType(type);
		
		assertEquals(type, association.getType());
		
	}
	
	/**
	 * test for setType()
	 */
	@Test
	public void testSetType(){
		
		Association association = this.map.createAssociation();
		assertNotNull(association);
		
		Topic type = this.map.createTopic();
		assertNotNull(type);
		
		assertNotSame(type, association.getType());
				
		association.setType(type);
		
		assertEquals(type, association.getType());
		
	}
	
	/**
	 * test for addItemIdentifier() 
	 */
	@Test
	public void testAddItemIdentifier(){

		Association association = this.map.createAssociation();
		assertNotNull(association);
		
		String iri = "http://test/iri";
		
		association.addItemIdentifier(iri);
		
		Set<String>  iis = association.getItemIdentifier();
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
		
		String iri = "http://test/iri";
		
		Set<String>  iis = association.getItemIdentifier();
		assertNotNull(iis);
		assertEquals(0, iis.size());
		
		association.addItemIdentifier(iri);
		
		iis = association.getItemIdentifier();
		assertNotNull(iis);
		assertEquals(1, iis.size());
		assertEquals(iri, iis.iterator().next());
		
	}
	
	/**
	 * test for addTheme() 
	 */
	@Test
	public void testAddTheme(){

		Association association = this.map.createAssociation();
		assertNotNull(association);
		
		Set<Topic> themes = association.getThemes();
		assertNotNull(themes);
		assertEquals(0, themes.size());
		
		Topic theme = this.map.createTopic();
		assertNotNull(theme);
		
		association.addTheme(theme);
		
		themes = association.getThemes();
		assertNotNull(themes);
		assertEquals(1, themes.size());
		assertEquals(theme, themes.iterator().next());
		
	}
	
	/**
	 * test for getThemes() 
	 */
	@Test
	public void testGetThemes(){

		Association association = this.map.createAssociation();
		assertNotNull(association);
		
		Set<Topic> themes = association.getThemes();
		assertNotNull(themes);
		assertEquals(0, themes.size());
		
		Topic theme = this.map.createTopic();
		assertNotNull(theme);
		
		association.addTheme(theme);
		
		themes = association.getThemes();
		assertNotNull(themes);
		assertEquals(1, themes.size());
		assertEquals(theme, themes.iterator().next());
		
	}
	
	/**
	 * test for removeThemes() 
	 */
	@Test
	public void testRemoveTheme(){

		Association association = this.map.createAssociation();
		assertNotNull(association);
		
		Topic theme1 = this.map.createTopic();
		assertNotNull(theme1);
		
		Topic theme2 = this.map.createTopic();
		assertNotNull(theme2);
		
		Topic theme3 = this.map.createTopic();
		assertNotNull(theme3);
		
		association.addTheme(theme1);
		association.addTheme(theme2);
		association.addTheme(theme3);
		
		Set<Topic> themes = association.getThemes();
		assertNotNull(themes);
		assertEquals(3, themes.size());
		assertTrue(themes.contains(theme1));
		assertTrue(themes.contains(theme2));
		assertTrue(themes.contains(theme3));
		
		association.removeTheme(theme2);
		
		themes = association.getThemes();
		assertNotNull(themes);
		assertEquals(2, themes.size());
		assertTrue(themes.contains(theme1));
		assertTrue(themes.contains(theme3));
		
	}
	
	
	
	/**
	 * test for setReifier() 
	 */
	@Test
	public void testSetReifier(){

		Association association = this.map.createAssociation();
		assertNotNull(association);
		
		assertNull(association.getReifier());
		
		Topic reifier = this.map.createTopic();
		assertNotNull(reifier);
		
		association.setReifier(reifier);
		
		assertNotNull(association.getReifier());
		assertEquals(reifier, association.getReifier());
	}
	
	/**
	 * test for getReifier() 
	 */
	@Test
	public void testGetReifier(){

		Association association = this.map.createAssociation();
		assertNotNull(association);
		
		Topic reifier = this.map.createTopic();
		assertNotNull(reifier);
		
		association.setReifier(reifier);
		
		assertNotNull(association.getReifier());
		assertEquals(reifier, association.getReifier());
	}
	
	
	
}
