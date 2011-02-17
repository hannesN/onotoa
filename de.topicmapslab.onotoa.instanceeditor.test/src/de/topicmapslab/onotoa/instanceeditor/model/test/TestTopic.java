package de.topicmapslab.onotoa.instanceeditor.model.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;

import de.topicmapslab.onotoa.instanceeditor.model.Association;
import de.topicmapslab.onotoa.instanceeditor.model.Name;
import de.topicmapslab.onotoa.instanceeditor.model.Occurrence;
import de.topicmapslab.onotoa.instanceeditor.model.Role;
import de.topicmapslab.onotoa.instanceeditor.model.Topic;

/**
 * test class for {@link Topic}
 * @author Christian Haß
 *
 */
public class TestTopic extends AbstractModelTest {

	
	/**
	 * test for getBestLabel()
	 */
	@Test
	public void testGetBestLabel(){
		
		Topic topic = this.map.createTopic();
		assertNotNull(topic);
		Topic nametype = this.map.createTopic();
		assertNotNull(nametype);
		
		Name typedname = topic.createName();
		assertNotNull(typedname);
		typedname.setType(nametype);
		typedname.setValue("a");
		
		Name defaultName = topic.createName();
		assertNotNull(defaultName);
		defaultName.setValue("b");
		
		
		String bestLabel = topic.getBestLabel();
		assertNotNull(bestLabel);
		assertEquals(bestLabel, "b");
		
	}
	
	/**
	 * test for getTypes()
	 */
	@Test
	public void testGetTypes(){
		
		Topic topic = this.map.createTopic();
		assertNotNull(topic);
		
		Topic type1 = this.map.createTopic();
		assertNotNull(type1);
		Topic type2 = this.map.createTopic();
		assertNotNull(type2);
		
		topic.addType(type1);
		topic.addType(type2);
		
		
		Set<Topic> types = topic.getTypes();
		assertNotNull(types);
		
		assertEquals(2, types.size());
		assertTrue(types.contains(type1));
		assertTrue(types.contains(type2));
		
		
	}
	
	/**
	 * test for addType()
	 */
	@Test
	public void testAddType(){
		
		Topic topic = this.map.createTopic();
		assertNotNull(topic);
		
		Set<Topic> types = topic.getTypes();
		assertNotNull(types);
		assertEquals(0, types.size());
		
		Topic type = this.map.createTopic();
		assertNotNull(type);
		
		topic.addType(type);
		
		types = topic.getTypes();
		assertNotNull(types);
		assertEquals(1, types.size());
		assertEquals(type, types.iterator().next());
		
	}
	
	/**
	 * test for getNames()
	 */
	@Test
	public void testGetNames(){
		
		Topic topic = this.map.createTopic();
		assertNotNull(topic);
		
		Set<Name> names = topic.getNames();
		assertNotNull(names);
		assertEquals(0, names.size());
		
		Name n = topic.createName();
		
		names = topic.getNames();
		assertNotNull(names);
		assertEquals(1, names.size());
		assertEquals(n, names.iterator().next());
	}
	
	/**
	 * test for createName()
	 */
	@Test
	public void testcreateName(){
		
		Topic topic = this.map.createTopic();
		assertNotNull(topic);
		
		Set<Name> names = topic.getNames();
		assertNotNull(names);
		assertEquals(0, names.size());
		
		Name n = topic.createName();
		assertNotNull(n);
		
		names = topic.getNames();
		assertNotNull(names);
		assertEquals(1, names.size());
		assertEquals(n, names.iterator().next());
	}
	
	/**
	 * test for getOccurrences()
	 */
	@Test
	public void testGetOccurrences(){
		
		Topic topic = this.map.createTopic();
		assertNotNull(topic);
		
		Set<Occurrence> occurrences = topic.getOccurrences();
		assertNotNull(occurrences);
		assertEquals(0, occurrences.size());
		
		Occurrence o = topic.createOccurrence();
		
		occurrences = topic.getOccurrences();
		assertNotNull(occurrences);
		assertEquals(1, occurrences.size());
		assertEquals(o, occurrences.iterator().next());
	}
	
	/**
	 * test for createOccurrence()
	 */
	@Test
	public void testCreateOccurrences(){
		
		Topic topic = this.map.createTopic();
		assertNotNull(topic);
		
		Set<Occurrence> occurrences = topic.getOccurrences();
		assertNotNull(occurrences);
		assertEquals(0, occurrences.size());
		
		Occurrence o = topic.createOccurrence();
		
		occurrences = topic.getOccurrences();
		assertNotNull(occurrences);
		assertEquals(1, occurrences.size());
		assertEquals(o, occurrences.iterator().next());
	}
	
	/**
	 * test for addSuperType()
	 */
	@Test
	public void testAddSuperType(){
	
		Topic topic = this.map.createTopic();
		assertNotNull(topic);
		
		Set<Topic> superTypes = topic.getSupertypes();
		assertNotNull(superTypes);
		assertEquals(0, superTypes.size());
		
		Topic supertype = this.map.createTopic();
		assertNotNull(supertype);
		
		topic.addSuperType(supertype);
		
		superTypes = topic.getSupertypes();
		assertNotNull(superTypes);
		assertEquals(1, superTypes.size());
		assertEquals(supertype, superTypes.iterator().next());
		
	}
	
	/**
	 * test for getSuperTypes()
	 */
	@Test
	public void testGetSuperTypes(){
		
		Topic topic = this.map.createTopic();
		assertNotNull(topic);
		
		Topic supertype1 = this.map.createTopic();
		assertNotNull(supertype1);
		
		Topic supertype2 = this.map.createTopic();
		assertNotNull(supertype2);
				
		topic.addSuperType(supertype1);
		supertype1.addSuperType(supertype2);
		
		Set<Topic> superTypes = topic.getSupertypes();
		assertNotNull(superTypes);
		assertEquals(2, superTypes.size());
		assertTrue(superTypes.contains(supertype1));
		assertTrue(superTypes.contains(supertype2));
	}
	
	/**
	 * test for removeType()
	 */
	@Test
	public void testRemoveType(){
		
		Topic topic = this.map.createTopic();
		assertNotNull(topic);

		Topic type = this.map.createTopic();
		assertNotNull(type);
		topic.addType(type);
		
		Set<Topic> types = topic.getTypes();
		assertNotNull(types);
		assertEquals(1, types.size());
		
		topic.removeType(type);
		
		types = topic.getTypes();
		assertNotNull(types);
		assertEquals(0, types.size());
	}
	
	/**
	 * test for removeName()
	 */
	@Test
	public void testRemoveName(){
	
		Topic topic = this.map.createTopic();
		assertNotNull(topic);
		
		Name name = topic.createName();
		
		Set<Name> names = topic.getNames();
		assertNotNull(names);
		assertEquals(1, names.size());
		
		topic.removeName(name);
		
		names = topic.getNames();
		assertNotNull(names);
		assertEquals(0, names.size());

	}
	
	/**
	 * test for removeOccurrence()
	 */
	@Test
	public void testRemoveOccurrence(){
	
		Topic topic = this.map.createTopic();
		assertNotNull(topic);
		
		Occurrence occ = topic.createOccurrence();
		
		Set<Occurrence> occs = topic.getOccurrences();
		assertNotNull(occs);
		assertEquals(1, occs.size());
		
		topic.removeOccurrence(occ);
		
		occs = topic.getOccurrences();
		assertNotNull(occs);
		assertEquals(0, occs.size());

	}
	
	/**
	 * test for getRoles()
	 */
	@Test
	public void testGetRoles(){
		
		Topic topic = this.map.createTopic();
		assertNotNull(topic);
		
		Set<Role> roles = topic.getRoles();
		assertNotNull(roles);
		assertEquals(0, roles.size());
		
		Association ass = this.map.createAssociation();
		assertNotNull(ass);
		
		Role role = ass.createRole(topic);
		assertNotNull(role);
		
		roles = topic.getRoles();
		assertNotNull(roles);
		assertEquals(1, roles.size());
		assertEquals(role, roles.iterator().next());
		
	}
	
	/**
	 * test for removeSuperType()
	 */
	@Test
	public void testRemoveSuperType(){
		
		
		Topic topic = this.map.createTopic();
		assertNotNull(topic);

		Topic supertype = this.map.createTopic();
		assertNotNull(supertype);
		topic.addSuperType(supertype);
		
		Set<Topic> supertypes = topic.getSupertypes();
		assertNotNull(supertypes);
		assertEquals(1, supertypes.size());
		
		topic.removeSuperType(supertype);
		
		supertypes = topic.getSupertypes();
		assertNotNull(supertypes);
		assertEquals(0, supertypes.size());
	}

	/**
	 * test for addSubjectIdentifier
	 */
	@Test
	public void testAddSubjectIdentifier(){
		
		Topic topic = this.map.createTopic();
		assertNotNull(topic);
		
		Set<String> sis = topic.getSubjectIdentifier();
		assertNotNull(sis);
		assertEquals(0, sis.size());
		
		String iri = "http://test/identifier";
		
		topic.addSubjectIdentifier(iri);
		
		sis = topic.getSubjectIdentifier();
		assertNotNull(sis);
		assertEquals(1, sis.size());
		assertEquals(iri, sis.iterator().next());
	}
	
	/**
	 * test for addSubjectLocator
	 */
	@Test
	public void testAddSubjectLocator(){
		
		Topic topic = this.map.createTopic();
		assertNotNull(topic);
		
		Set<String> sls = topic.getSubjectLocator();
		assertNotNull(sls);
		assertEquals(0, sls.size());
		
		String iri = "http://test/identifier";
		
		topic.addSubjectLocator(iri);
		
		sls = topic.getSubjectLocator();
		assertNotNull(sls);
		assertEquals(1, sls.size());
		assertEquals(iri, sls.iterator().next());
	}
	
	/**
	 * test for addItemIdentifier
	 */
	@Test
	public void testAddItemIdentifier(){
		
		Topic topic = this.map.createTopic();
		assertNotNull(topic);
		
		Set<String> iis = topic.getItemIdentifier();
		assertNotNull(iis);
		assertEquals(1, iis.size());
		
		String iri = "http://test/identifier";
		
		topic.addItemIdentifier(iri);
		
		iis = topic.getItemIdentifier();
		assertNotNull(iis);
		assertEquals(2, iis.size());
		
	}
		
	/**
	 * test for removeSubjectIdentifier
	 */
	@Test
	public void testRemoveSubjectIdentifier(){
		
		Topic topic = this.map.createTopic();
		assertNotNull(topic);
		
		String iri = "http://test/identifier";
		topic.addSubjectIdentifier(iri);
		
		Set<String> sis = topic.getSubjectIdentifier();
		assertNotNull(sis);
		assertEquals(1, sis.size());
		assertEquals(iri, sis.iterator().next());
		
		topic.removeSubjectIdentifier(iri);
		
		sis = topic.getSubjectIdentifier();
		assertNotNull(sis);
		assertEquals(0, sis.size());
		
	}
	
	/**
	 * test for removeSubjectLocator
	 */
	@Test
	public void testRemoveSubjectLocator(){
		
		Topic topic = this.map.createTopic();
		assertNotNull(topic);
		
		String iri = "http://test/identifier";
		topic.addSubjectLocator(iri);
		
		Set<String> sis = topic.getSubjectLocator();
		assertNotNull(sis);
		assertEquals(1, sis.size());
		assertEquals(iri, sis.iterator().next());
		
		topic.removeSubjectLocator(iri);
		
		sis = topic.getSubjectLocator();
		assertNotNull(sis);
		assertEquals(0, sis.size());
		
	}
	
	/**
	 * test for removeItemIdentifier
	 */
	@Test
	public void testRemoveItemIdentifier(){
		
		Topic topic = this.map.createTopic();
		assertNotNull(topic);
		
		String iri = "http://test/identifier";
		topic.addItemIdentifier(iri);
		
		Set<String> sis = topic.getItemIdentifier();
		assertNotNull(sis);
		assertEquals(2, sis.size());
				
		topic.removeItemIdentifier(iri);
		
		sis = topic.getItemIdentifier();
		assertNotNull(sis);
		assertEquals(1, sis.size());
		
	}
	
	
}
