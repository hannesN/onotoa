package de.topicmapslab.tmcledit.model.validation;

import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.TopicMapSchema;

/**
 * @author Hannes Niederhausen
 *
 */
public class ModelValidator {
	private final TopicMapSchema schema;
	private final File file;
	
	public ModelValidator(File file) {
		this.file = file;
		this.schema = file.getTopicMapSchema();
	}
	
	
	
	
}
