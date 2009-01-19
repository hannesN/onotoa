package de.topicmapslab.tmcledit.model.validation;

import java.util.Collections;
import java.util.List;
import java.util.Vector;

import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.MappingElement;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.NameTypeConstraint;
import de.topicmapslab.tmcledit.model.OccurenceTypeConstraint;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.util.ModelIndexer;
import de.topicmapslab.tmcledit.model.validation.actions.AssociationCreateTypeAction;
import de.topicmapslab.tmcledit.model.validation.actions.AssociationSelectTypeAction;
import de.topicmapslab.tmcledit.model.validation.actions.DeactivateFlagAction;
import de.topicmapslab.tmcledit.model.validation.actions.NameConstraintCreateTypeAction;
import de.topicmapslab.tmcledit.model.validation.actions.NameConstraintSelectTypeAction;
import de.topicmapslab.tmcledit.model.validation.actions.NewPrefixAction;
import de.topicmapslab.tmcledit.model.validation.actions.OccurenceConstraintCreateTypeAction;
import de.topicmapslab.tmcledit.model.validation.actions.OccurenceConstraintSelectTypeAction;
import de.topicmapslab.tmcledit.model.validation.actions.RemoveIsAAction;

/**
 * @author Hannes Niederhausen
 *
 */
public class ModelValidator {
	private final TopicMapSchema schema;
	private List<ValidationResult> resultList = Collections.emptyList();
	
	public ModelValidator(File file) {
		this.schema = file.getTopicMapSchema();
	}
	
	private void addValidationResult(ValidationResult result) {
		if (resultList==Collections.EMPTY_LIST)
			resultList = new Vector<ValidationResult>();
		
		resultList.add(result);
	}
	
	public List<ValidationResult> validate() {
		validateAssociationConstraints();
		validateTopicTypes();
		return resultList;
	}
	
	private void validateTopicTypes() {
		for (TopicType tt : schema.getTopicTypes()) {
			validateOccurenceConstraint(tt);
			validateNameConstraint(tt);
			checkPrefixes(tt);
			if (ModelIndexer.getInstance().isFilterActivated(KindOfTopicType.TOPIC_TYPE)) {
				for (TopicType isA_tt : tt.getIsa()) {
					if (isA_tt.getKind()==KindOfTopicType.NO_TYPE) {
						ValidationResult vr = new ValidationResult("The topic "
								+ isA_tt.getName() + " may not be type for "
								+ tt.getName(), isA_tt);
						vr.addValidationAction(new RemoveIsAAction(tt, isA_tt));
						vr.addValidationAction(new DeactivateFlagAction(schema, ModelPackage.TOPIC_MAP_SCHEMA__ACTIVE_TOPIC_TYPE_CONSTRAINT, false));
						addValidationResult(vr);
					}
				}
			}
		}
	}
	
	private void validateAssociationConstraints() {
		for (AssociationTypeConstraint atc : schema.getAssociationTypeConstraints()) {
			if (atc.getAssociationType()==null) {
				ValidationResult vr = new ValidationResult("No type set for association constraint", atc);
				vr.addValidationAction(new AssociationCreateTypeAction());
				vr.addValidationAction(new AssociationSelectTypeAction());
				addValidationResult(vr);
			} else if ((schema.isActiveAssociationTypeConstraint())
					&& (atc.getAssociationType().getKind() == KindOfTopicType.NO_TYPE)) {
				ValidationResult vr = new ValidationResult("The topic "
						+ atc.getAssociationType().getName() + " may not be type for an association constraint", atc.getAssociationType());
				vr.addValidationAction(new DeactivateFlagAction(schema, ModelPackage.TOPIC_MAP_SCHEMA__ACTIVE_ASSOCIATION_TYPE_CONSTRAINT, false));
				vr.addValidationAction(new AssociationCreateTypeAction());
				vr.addValidationAction(new AssociationSelectTypeAction());
				addValidationResult(vr);
			}
			if (atc.getRoleTypeConstraints().size()==0) {
				ValidationResult vr = new ValidationResult("No roles set for association constraint", atc);
				addValidationResult(vr);
				
			}
		}
	}
	
	private void checkPrefixes(TopicType type) {
		for (String id : type.getIdentifiers()) {
			checkPrefix(type, id);
		}
		for (String loc : type.getLocators()) {
			checkPrefix(type, loc);
		}
	}

	private void checkPrefix(TopicType tt, String c) {
		String prefix = getPrefix(c);
		if (prefix!=null) {
			boolean found = false;
			for (MappingElement me : schema.getMappings()) {
				if (me.getKey().equals(prefix)) {
					found = true;
					break;
				}
			}
			if (!found) {
				ValidationResult vr = new ValidationResult("Unknown prefix used", tt);
				vr.addValidationAction(new NewPrefixAction(schema, prefix));
				addValidationResult(vr);
			}
		}
	}
	
	
	private String getPrefix(String name) {
		if (name.startsWith("http://"))
			return null;
		int index = name.indexOf(':');
		if (index!=-1)
			return name.substring(0, index);
		
		return null;
	}
	/*
	private void validateRoleConstraints(RoleTypeConstraints rtc) {
		// check if check for type is needed
	}
	*/
	
	private void validateOccurenceConstraint(TopicType topicType) {
		for (OccurenceTypeConstraint otc : topicType.getOccurenceConstraints()) {
			if (otc.getType()==null) {
				ValidationResult vr = new ValidationResult("No type for occurence type set", otc);
				vr.addValidationAction(new OccurenceConstraintSelectTypeAction());
				vr.addValidationAction(new OccurenceConstraintCreateTypeAction());
				addValidationResult(vr);
			} else if ((schema.isActiveOccurenceTypeConstraint())
					&& (otc.getType().getKind() == KindOfTopicType.NO_TYPE)) {
				ValidationResult vr = new ValidationResult("The topic "
						+ otc.getType().getName() + " may not be type for an occurence constraint", otc.getType());
				vr.addValidationAction(new DeactivateFlagAction(schema, ModelPackage.TOPIC_MAP_SCHEMA__ACTIVE_NAME_TYPE_CONSTRAINT, false));
				vr.addValidationAction(new OccurenceConstraintSelectTypeAction());
				vr.addValidationAction(new OccurenceConstraintCreateTypeAction());
				addValidationResult(vr);
			}
		}
	}
	
	private void validateNameConstraint(TopicType topicType) {
		for (NameTypeConstraint ntc : topicType.getNameContraints()) {
			if (ntc.getType()==null) {
				ValidationResult vr = new ValidationResult("No type for name type constraint set", ntc);
				vr.addValidationAction(new NameConstraintSelectTypeAction());
				vr.addValidationAction(new NameConstraintCreateTypeAction());
				addValidationResult(vr);
			} else if ((schema.isActiveAssociationTypeConstraint())
					&& (ntc.getType().getKind() == KindOfTopicType.NO_TYPE)) {
				ValidationResult vr = new ValidationResult("The topic "
						+ ntc.getType().getName() + " may not be type for an name constraint", ntc.getType());
				vr.addValidationAction(new DeactivateFlagAction(schema, ModelPackage.TOPIC_MAP_SCHEMA__ACTIVE_NAME_TYPE_CONSTRAINT, false));
				vr.addValidationAction(new NameConstraintSelectTypeAction());
				vr.addValidationAction(new NameConstraintCreateTypeAction());
				addValidationResult(vr);
			}
		}
	}
	
}
