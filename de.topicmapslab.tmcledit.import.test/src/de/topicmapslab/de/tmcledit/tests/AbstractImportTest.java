/**
 * 
 */
package de.topicmapslab.de.tmcledit.tests;

import java.io.IOException;

import org.tmapi.core.TMAPIException;

import de.topicmapslab.tmcl.loader.util.NullWorkMonitor;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;
import de.topicmapslab.tmcledit.tmclimport.builder.OnotoaBuilder;

/**
 *  abstract test for import tests
 * 
 * @author Hannes Niederhausen
 * 
 */
public abstract class AbstractImportTest {

	// private TopicMap topicMap;
	private static File file;

	/** loads the topic map */

	protected static void loadTopicMap(String filename) throws IOException,
			TMAPIException {
		//
		// TopicMapSystemFactory tmsFactory = new TopicMapSystemFactoryImpl();
		// tmsFactory.setProperty(TopicMapStoreProperty.TOPICMAPSTORE_CLASS,
		// InMemoryTopicMapStore.class.getName());
		// TopicMapSystem tms = tmsFactory.newTopicMapSystem();
		//
		// topicMap = tms.createTopicMap("http://onotoa.topicmapslab.de/");
		// CTMTopicMapReader reader = new CTMTopicMapReader(topicMap, new
		// File(filename));
		// reader.read();
		//
		OnotoaBuilder builder = new OnotoaBuilder(filename);
		file = builder.getFile(new NullWorkMonitor());
		ModelIndexer.createInstance(file);
	}

	/**
	 * 
	 * @return the {@link File} with the loaded model 
	 */
	public File getFile() {
		return file;
	}
}
