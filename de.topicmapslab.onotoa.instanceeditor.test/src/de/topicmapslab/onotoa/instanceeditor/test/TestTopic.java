package de.topicmapslab.onotoa.instanceeditor.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import de.topicmapslab.onotoa.instanceeditor.model.Name;
import de.topicmapslab.onotoa.instanceeditor.model.Topic;

public class TestTopic extends AbstractTest {

	@Before
	public void before() throws Exception{
		getEmptyTopicMap();
	}
	
	@Test
	public void TestGetBestLabel(){
		
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
}
