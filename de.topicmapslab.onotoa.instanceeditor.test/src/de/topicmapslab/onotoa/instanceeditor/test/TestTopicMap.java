package de.topicmapslab.onotoa.instanceeditor.test;

import static org.junit.Assert.assertNotNull;
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
	
}
