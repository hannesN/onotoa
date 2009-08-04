/**
 * 
 */
package de.topicmapslab.tmcledit.tmclimport.builder;

import java.io.FileReader;
import java.io.IOException;

import org.tinytim.mio.CTMTopicMapReader;
import org.tinytim.mio.LTMTopicMapReader;
import org.tinytim.mio.TopicMapReader;
import org.tinytim.mio.XTMTopicMapReader;
import org.tinytim.voc.TMCL;
import org.tmapi.core.Locator;
import org.tmapi.core.Name;
import org.tmapi.core.Topic;
import org.tmapi.core.TopicMap;
import org.tmapi.core.TopicMapSystemFactory;
import org.tmapi.index.TypeInstanceIndex;

import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.TopicType;

/**
 * @author Hannes Niederhausen
 *
 */
public class OnotoaBuilder {

	private final String filename;
	// loaded topic map
	private TopicMap topicMap;
	
	private File file;

	private ModelFactory modelFactory;

	private int counter = 0;
	
	public OnotoaBuilder(String filename) {
		this.filename = filename;
	}
	
	public File getFile() {
		if (file==null)
			try {
				createFile();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		return file;
	}
	
	private void createFile() throws Exception {
		topicMap = TopicMapSystemFactory.newInstance().newTopicMapSystem().createTopicMap("file:/"+filename);
		modelFactory = ModelFactory.eINSTANCE;
		
		file = modelFactory.createFile();
		file.setTopicMapSchema(modelFactory.createTopicMapSchema());
		
		TopicMapReader reader = getReader();
		
		
		if (reader!=null)
			reader.read();
		
		TypeInstanceIndex tiIdx = topicMap.getIndex(TypeInstanceIndex.class);
		
		Topic type = topicMap.getTopicBySubjectIdentifier(TMCL.TOPIC_TYPE);
		if (type!=null) {
			for (Topic t : tiIdx.getTopics(type)) {
				addTopic(t, KindOfTopicType.TOPIC_TYPE);
			}
		}
		type = topicMap.getTopicBySubjectIdentifier(TMCL.NAME_TYPE);
		if (type!=null) {
			for (Topic t : tiIdx.getTopics(type)) {
				addTopic(t, KindOfTopicType.NAME_TYPE);
			}
		}
		type = topicMap.getTopicBySubjectIdentifier(TMCL.ROLE_TYPE);
		if (type!=null) {
			for (Topic t : tiIdx.getTopics(type)) {
				addTopic(t, KindOfTopicType.ROLE_TYPE);
			}
		}
		type = topicMap.getTopicBySubjectIdentifier(TMCL.OCCURRENCE_TYPE);
		if (type!=null) {
			for (Topic t : tiIdx.getTopics(type)) {
				addTopic(t, KindOfTopicType.OCCURRENCE_TYPE);
			}
		}
		type = topicMap.getTopicBySubjectIdentifier(TMCL.ASSOCIATION_TYPE);
		if (type!=null) {
			for (Topic t : tiIdx.getTopics(type)) {
				addTopic(t, KindOfTopicType.ASSOCIATION_TYPE);
			}
		}
		type = topicMap.getTopicBySubjectIdentifier(TMCL.SCOPE_TYPE);
		if (type!=null) {
			for (Topic t : tiIdx.getTopics(type)) {
				addTopic(t, KindOfTopicType.SCOPE_TYPE);
			}
		}
	}

	private TopicMapReader getReader() throws IOException {
		java.io.File file = new java.io.File(filename);
		TopicMapReader reader = null;
		String lowerCase = filename.toLowerCase();
		if (lowerCase.endsWith(".ctm")) {
			FileReader fr = new FileReader(file);
			
			
			
			reader = new CTMTopicMapReader(topicMap, file);
		} else if (lowerCase.endsWith(".xtm")) {
			reader = new XTMTopicMapReader(topicMap, file);
		} else if (lowerCase.endsWith(".ltm")) {
			reader = new LTMTopicMapReader(topicMap, file);
		}
		return reader;
	}

	private void addTopic(Topic t, KindOfTopicType kind) {
		
		TopicType tt = null; 
		
		
		tt = modelFactory.createTopicType();
		tt.setKind(kind);
		
		if (t.getNames().isEmpty()) {
			tt.setName("default"+counter);
			counter++;
		} else {
			Name first = t.getNames().iterator().next();
			tt.setName(first.getValue());
		}
		
		for (Locator l : t.getSubjectIdentifiers()) {
			tt.getIdentifiers().add(l.toExternalForm());
		}
		
		for (Locator l : t.getSubjectLocators()) {
			tt.getLocators().add(l.toExternalForm());
		}
		
		
		file.getTopicMapSchema().getTopicTypes().add(tt);
	}
	
}
