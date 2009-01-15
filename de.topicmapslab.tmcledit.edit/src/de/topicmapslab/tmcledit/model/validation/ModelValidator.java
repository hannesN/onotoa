package de.topicmapslab.tmcledit.model.validation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.TopicMapSchema;

/**
 * @author Hannes Niederhausen
 *
 */
public class ModelValidator {
	private final TopicMapSchema schema;
	private final File file;
	
	private List<ValidationResult> resultList = Collections.emptyList();
	
	public ModelValidator(File file) {
		this.file = file;
		this.schema = file.getTopicMapSchema();
	}
	
	private void addValidationResult(ValidationResult result) {
		if (resultList==Collections.EMPTY_LIST)
			resultList = new Vector<ValidationResult>();
		
		resultList.add(result);
	}
	
	public List<ValidationResult> validate() {
		System.out.println("Number of CPUs"+Runtime.getRuntime().availableProcessors());
		
		return resultList;
	}
	
	private void validateAssociationRoles() {
		
	}
	
}
