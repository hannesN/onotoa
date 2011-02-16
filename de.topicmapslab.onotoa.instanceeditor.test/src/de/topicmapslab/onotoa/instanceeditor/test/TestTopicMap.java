package de.topicmapslab.onotoa.instanceeditor.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import de.topicmapslab.onotoa.instanceeditor.model.Topic;

public class TestTopicMap extends AbstractTest {

	@Before
	public void before() throws Exception{
		getEmptyTopicMap();
	}
	
	@Test
	public void TestCreateTopic(){
		Topic topic = this.map.createTopic();
		assertNotNull(topic);
	}
	
	@Test
	public void TestGetTopics(){
		
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
	
}
