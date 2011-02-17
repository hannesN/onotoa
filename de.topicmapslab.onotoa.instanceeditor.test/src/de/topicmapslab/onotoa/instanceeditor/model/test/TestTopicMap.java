package de.topicmapslab.onotoa.instanceeditor.model.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import de.topicmapslab.onotoa.instanceeditor.model.Association;
import de.topicmapslab.onotoa.instanceeditor.model.Topic;
import de.topicmapslab.onotoa.instanceeditor.model.TopicMap;

/**
 * test class for {@link TopicMap}
 * @author Christian Haß
 *
 */
public class TestTopicMap extends AbstractModelTest {

	/**
	 * executed before each test
	 * @throws Exception
	 */
	@Before
	public void before() throws Exception{
		getEmptyTopicMap();
	}
	
	/**
	 * test for createTopic()
	 */
	@Test
	public void testCreateTopic(){
		Topic topic = this.map.createTopic();
		assertNotNull(topic);
	}
	
	/**
	 * test for getTopics()
	 */
	@Test
	public void testGetTopics(){
		
		Topic topic1 = this.map.createTopic();
		assertNotNull(topic1);
		Topic topic2 = this.map.createTopic();
		assertNotNull(topic2);
		
		Set<Topic> topics = this.map.getTopics();
		assertNotNull(topics);
		assertEquals(2, topics.size());
		
		assertTrue(topics.contains(topic1));
		assertTrue(topics.contains(topic2));
	}
	
	/**
	 * test for removeTopic()
	 */
	@Test
	public void testRemoveTopic(){
		
		Topic topic1 = this.map.createTopic();
		assertNotNull(topic1);
		Topic topic2 = this.map.createTopic();
		assertNotNull(topic2);
		
		Set<Topic> topics = this.map.getTopics();
		assertNotNull(topics);
		assertEquals(2, topics.size());
		
		this.map.removeTopic(topic1);
		topics = this.map.getTopics();
		assertNotNull(topics);
		assertEquals(1, topics.size());
		
		assertEquals(topic2, topics.iterator().next());
	}
	
	/**
	 * test for getTopicBySubjectIdentifier()
	 */
	@Test
	public void testGetTopicBySubjectIdentifier(){
		
		Topic topic1 = this.map.createTopic();
		assertNotNull(topic1);
		topic1.addSubjectIdentifier("http://test/topic1");
		
		Topic topic2 = this.map.createTopic();
		assertNotNull(topic2);
		topic1.addSubjectIdentifier("http://test/topic2");
		topic2.addSubjectIdentifier("http://test/topic3");
		
		Topic testTopic = this.map.getTopicBySubjectIdentifier("http://test/topic1");
		assertEquals(testTopic, topic1);
		
		testTopic = this.map.getTopicBySubjectIdentifier("http://test/topic3");
		assertEquals(testTopic, topic2);
		
	}
	
	/**
	 * test for getTopicBySubjectLocator()
	 */
	@Test
	public void testGetTopicBySubjectLocator(){
		
		Topic topic1 = this.map.createTopic();
		assertNotNull(topic1);
		topic1.addSubjectLocator("http://test/topic1");
		
		Topic topic2 = this.map.createTopic();
		assertNotNull(topic2);
		topic1.addSubjectLocator("http://test/topic2");
		topic2.addSubjectLocator("http://test/topic3");
		
		Topic testTopic = this.map.getTopicBySubjectLocator("http://test/topic1");
		assertEquals(testTopic, topic1);
		
		testTopic = this.map.getTopicBySubjectLocator("http://test/topic3");
		assertEquals(testTopic, topic2);
		
	}
	
	/**
	 * test for getTopicByItemIdentifier()
	 */
	@Test
	public void testGetTopicByItemIdentifier(){
		
		Topic topic1 = this.map.createTopic();
		assertNotNull(topic1);
		topic1.addItemIdentifier("http://test/topic1");
		
		Topic topic2 = this.map.createTopic();
		assertNotNull(topic2);
		topic1.addItemIdentifier("http://test/topic2");
		topic2.addItemIdentifier("http://test/topic3");
		
		Topic testTopic = this.map.getTopicByItemIdentifier("http://test/topic1");
		assertEquals(testTopic, topic1);
		
		testTopic = this.map.getTopicByItemIdentifier("http://test/topic3");
		assertEquals(testTopic, topic2);
		
	}
	
	/**
	 * test for createAssociation()
	 */
	@Test
	public void testCreateAssociation(){
		
		Association ass = this.map.createAssociation();
		assertNotNull(ass);
		
	}
	
	
	
}
