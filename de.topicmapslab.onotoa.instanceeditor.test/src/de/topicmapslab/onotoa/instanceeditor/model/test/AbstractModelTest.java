package de.topicmapslab.onotoa.instanceeditor.model.test;

import org.junit.Before;
import org.tmapi.core.TopicMap;
import org.tmapi.core.TopicMapSystem;
import org.tmapi.core.TopicMapSystemFactory;

import de.topicmapslab.majortom.core.TopicMapSystemFactoryImpl;
import de.topicmapslab.majortom.inmemory.store.InMemoryTopicMapStore;
import de.topicmapslab.majortom.store.TopicMapStoreProperty;
import de.topicmapslab.majortom.util.FeatureStrings;
import de.topicmapslab.onotoa.instanceeditor.service.ITopicMapProvider;
import de.topicmapslab.onotoa.instanceeditor.test.provider.MyTopicMapProvider;

/**
 * abstract model test class
 * @author Christian Haß
 *
 */
public abstract class AbstractModelTest {

	/**
	 * the topic map
	 */
	protected de.topicmapslab.onotoa.instanceeditor.model.TopicMap map;
	
	private ITopicMapProvider provider;
	
	/**
	 * creates an empty topic map
	 * @throws Exception
	 */
	void getEmptyTopicMap() throws Exception{

		TopicMapSystemFactory tm_factory = new TopicMapSystemFactoryImpl();
		tm_factory.setProperty(TopicMapStoreProperty.TOPICMAPSTORE_CLASS, InMemoryTopicMapStore.class.getCanonicalName());
		tm_factory.setFeature(FeatureStrings.SUPPORT_HISTORY, false);
		
		TopicMapSystem tm_system = tm_factory.newTopicMapSystem();
		TopicMap topicMap = tm_system.createTopicMap("http://test/");
				
		this.provider = new MyTopicMapProvider(topicMap, "http://test/");
		this.map = new de.topicmapslab.onotoa.instanceeditor.model.TopicMap(this.provider);
		
	}
	
	
	/**
	 * executed before each test
	 * @throws Exception
	 */
	@Before
	public void before() throws Exception{
		getEmptyTopicMap();
	}
	

	
}
