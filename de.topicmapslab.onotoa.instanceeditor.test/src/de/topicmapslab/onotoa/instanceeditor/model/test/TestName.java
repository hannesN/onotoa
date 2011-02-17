package de.topicmapslab.onotoa.instanceeditor.model.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import de.topicmapslab.onotoa.instanceeditor.model.Name;
import de.topicmapslab.onotoa.instanceeditor.model.Topic;

/**
 * test class for {@link Name}
 * @author Christian Haß
 *
 */
public class TestName extends AbstractModelTest {

	/**
	 * executed before each test
	 * @throws Exception
	 */
	@Before
	public void before() throws Exception{
		getEmptyTopicMap();
	}
	
	@Test
	public void testGetType(){
		
		Topic topic = this.map.createTopic();
		assertNotNull(topic);
		
		Name name = topic.createName();
		assertNotNull(name);
		
		Topic type = this.map.createTopic();
		assertNotNull(type);
		
		name.setType(type);
		
		assertEquals(type, name.getType());
		
	}
	
	@Test
	public void testSetType(){
		
		Topic topic = this.map.createTopic();
		assertNotNull(topic);
		
		Name name = topic.createName();
		assertNotNull(name);
		
		Topic type = this.map.createTopic();
		assertNotNull(type);
		
		assertNotSame(type, name.getType());
				
		name.setType(type);
		
		assertEquals(type, name.getType());
		
	}
	
	@Test
	public void testAddItemIdentifier(){
		
		Topic topic = this.map.createTopic();
		assertNotNull(topic);
		
		Name name = topic.createName();
		assertNotNull(name);
		
		String iri = "http://test/iri";
		
		name.addItemIdentifier(iri);
		
		Set<String>  iis = name.getItemIdentifier();
		assertNotNull(iis);
		assertEquals(1, iis.size());
		assertEquals(iri, iis.iterator().next());
		
	}
	
	@Test
	public void testGetItemIdentifier(){
		
		Topic topic = this.map.createTopic();
		assertNotNull(topic);
		
		Name name = topic.createName();
		assertNotNull(name);
		
		String iri = "http://test/iri";
		
		Set<String>  iis = name.getItemIdentifier();
		assertNotNull(iis);
		assertEquals(0, iis.size());
		
		name.addItemIdentifier(iri);
		
		iis = name.getItemIdentifier();
		assertNotNull(iis);
		assertEquals(1, iis.size());
		assertEquals(iri, iis.iterator().next());
		
	}
	
	@Test
	public void testAddTheme(){
		
		Topic topic = this.map.createTopic();
		assertNotNull(topic);
		
		Name name = topic.createName();
		assertNotNull(name);
		
		Set<Topic> themes = name.getThemes();
		assertNotNull(themes);
		assertEquals(0, themes.size());
		
		Topic theme = this.map.createTopic();
		assertNotNull(theme);
		
		name.addTheme(theme);
		
		themes = name.getThemes();
		assertNotNull(themes);
		assertEquals(1, themes.size());
		assertEquals(theme, themes.iterator().next());
		
	}
	
	public void testGetThemes(){
		
	}
	
	public void testRemoveTheme(){
		
	}
	
	public void testSetValue(){
		
	}
	
	public void testGetValue(){
		
	}
	
	public void testSetReifier(){
		
	}
	
	public void testGetReifier(){
		
	}
	
	
}
