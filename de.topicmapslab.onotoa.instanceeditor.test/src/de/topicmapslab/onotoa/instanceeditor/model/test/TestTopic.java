package de.topicmapslab.onotoa.instanceeditor.model.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import de.topicmapslab.onotoa.instanceeditor.model.Name;
import de.topicmapslab.onotoa.instanceeditor.model.Occurrence;
import de.topicmapslab.onotoa.instanceeditor.model.Topic;

/**
 * test class for {@link Topic}
 * @author Christian Haß
 *
 */
public class TestTopic extends AbstractModelTest {

	/**
	 * executed before each test
	 * @throws Exception
	 */
	@Before
	public void before() throws Exception{
		getEmptyTopicMap();
	}
	
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
	
	
}
