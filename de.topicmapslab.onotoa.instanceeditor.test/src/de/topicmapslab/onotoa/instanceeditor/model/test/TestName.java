package de.topicmapslab.onotoa.instanceeditor.model.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Set;

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
	 * test for getType()
	 */
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
	
	/**
	 * test for setType()
	 */
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
	
	/**
	 * test for addItemIdentifier() 
	 */
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
	
	/**
	 * test for getItemIdentifier() 
	 */
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
	
	/**
	 * test for addTheme() 
	 */
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
	
	/**
	 * test for getThemes() 
	 */
	@Test
	public void testGetThemes(){
		
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
	
	/**
	 * test for removeThemes() 
	 */
	@Test
	public void testRemoveTheme(){
		
		Topic topic = this.map.createTopic();
		assertNotNull(topic);
		
		Name name = topic.createName();
		assertNotNull(name);
		
		Topic theme1 = this.map.createTopic();
		assertNotNull(theme1);
		
		Topic theme2 = this.map.createTopic();
		assertNotNull(theme2);
		
		Topic theme3 = this.map.createTopic();
		assertNotNull(theme3);
		
		name.addTheme(theme1);
		name.addTheme(theme2);
		name.addTheme(theme3);
		
		Set<Topic> themes = name.getThemes();
		assertNotNull(themes);
		assertEquals(3, themes.size());
		assertTrue(themes.contains(theme1));
		assertTrue(themes.contains(theme2));
		assertTrue(themes.contains(theme3));
		
		name.removeTheme(theme2);
		
		themes = name.getThemes();
		assertNotNull(themes);
		assertEquals(2, themes.size());
		assertTrue(themes.contains(theme1));
		assertTrue(themes.contains(theme3));
		
	}
	
	/**
	 * test for setValue() 
	 */
	@Test
	public void testSetValue(){
		
		Topic topic = this.map.createTopic();
		assertNotNull(topic);
		
		Name name = topic.createName();
		assertNotNull(name);
		
		String value = "lala";
		
		assertNotSame(value, name.getValue());
		
		name.setValue(value);
		
		assertEquals(value, name.getValue());
		
	}
	
	/**
	 * test for getValue() 
	 */
	@Test
	public void testGetValue(){
		
		Topic topic = this.map.createTopic();
		assertNotNull(topic);
		
		Name name = topic.createName();
		assertNotNull(name);

		String input = "lala";
		name.setValue(input);
		
		String value = name.getValue();
		assertNotNull(value);
		
		assertEquals(value, input);
		
	}
	
	/**
	 * test for setReifier() 
	 */
	@Test
	public void testSetReifier(){
		
		Topic topic = this.map.createTopic();
		assertNotNull(topic);
		
		Name name = topic.createName();
		assertNotNull(name);
		
		assertNull(name.getReifier());
		
		Topic reifier = this.map.createTopic();
		assertNotNull(reifier);
		
		name.setReifier(reifier);
		
		assertNotNull(name.getReifier());
		assertEquals(reifier, name.getReifier());
	}
	
	/**
	 * test for getReifier() 
	 */
	@Test
	public void testGetReifier(){
		
		Topic topic = this.map.createTopic();
		assertNotNull(topic);
		
		Name name = topic.createName();
		assertNotNull(name);
		
		Topic reifier = this.map.createTopic();
		assertNotNull(reifier);
		
		name.setReifier(reifier);
		
		assertNotNull(name.getReifier());
		assertEquals(reifier, name.getReifier());
	}
	
	
}
