/*******************************************************************************
 * Copyright (c) 2008, 2009 Topic Maps Lab and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Hannes Niederhausen - initial API and implementation
 *******************************************************************************/
package de.topicmapslab.tmcledit.model.validation;

import java.util.Collections;
import java.util.List;
import java.util.Vector;

import org.eclipse.emf.common.command.CommandStack;

import de.topicmapslab.tmcledit.model.AssociationType;
import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.MappingElement;
import de.topicmapslab.tmcledit.model.NameTypeConstraint;
import de.topicmapslab.tmcledit.model.OccurrenceTypeConstraint;
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.validation.actions.AssociationCreateTypeAction;
import de.topicmapslab.tmcledit.model.validation.actions.AssociationSelectTypeAction;
import de.topicmapslab.tmcledit.model.validation.actions.NameConstraintCreateTypeAction;
import de.topicmapslab.tmcledit.model.validation.actions.NameConstraintSelectTypeAction;
import de.topicmapslab.tmcledit.model.validation.actions.NewPrefixAction;
import de.topicmapslab.tmcledit.model.validation.actions.OccurrenceConstraintCreateTypeAction;
import de.topicmapslab.tmcledit.model.validation.actions.OccurrenceConstraintSelectTypeAction;
import de.topicmapslab.tmcledit.model.validation.actions.RemoveIsAAction;

/**
 * @author Hannes Niederhausen
 * 
 */
public class ModelValidator {
	private final TopicMapSchema schema;
	private List<ValidationResult> resultList = Collections.emptyList();

	private CommandStack commandStack;

	public ModelValidator(File file) {
		this.schema = file.getTopicMapSchema();
	}

	private void addValidationResult(ValidationResult result) {
		if (resultList == Collections.EMPTY_LIST)
			resultList = new Vector<ValidationResult>();

		resultList.add(result);
	}

	public List<ValidationResult> validate(CommandStack cmdStack) {
		this.commandStack = cmdStack;
		validateAssociationConstraints();
		validateTopicTypes();
		return resultList;
	}

	private void validateTopicTypes() {
		for (TopicType tt : schema.getTopicTypes()) {
			validateOccurrenceConstraint(tt);
			validateNameConstraint(tt);
			checkPrefixes(tt);

			for (TopicType isA_tt : tt.getIsa()) {
				if (isA_tt.getKind() == KindOfTopicType.NO_TYPE) {
					ValidationResult vr = new ValidationResult("The topic " + isA_tt.getName()
					        + " may not be type for " + tt.getName(), isA_tt);
					vr.addValidationAction(new RemoveIsAAction(commandStack, tt, isA_tt));
					addValidationResult(vr);
				}

			}
		}
	}

	private void validateAssociationConstraints() {
		for (AssociationTypeConstraint atc : schema.getAssociationTypeConstraints()) {
			if (atc.getType() == null) {
				ValidationResult vr = new ValidationResult("No type set for association constraint", atc);
				vr.addValidationAction(new AssociationCreateTypeAction(commandStack));
				vr.addValidationAction(new AssociationSelectTypeAction(commandStack));
				addValidationResult(vr);
				return;
			}
			if (atc.getType().getKind() != KindOfTopicType.ASSOCIATION_TYPE) {
				ValidationResult vr = new ValidationResult("The topic " + atc.getType().getName()
				        + " may not be type for an association constraint", atc.getType());
				vr.addValidationAction(new AssociationCreateTypeAction(commandStack));
				vr.addValidationAction(new AssociationSelectTypeAction(commandStack));
				addValidationResult(vr);
			}

			if (((AssociationType) atc.getType()).getRoles().size() == 0) {
				ValidationResult vr = new ValidationResult(
				        "The topic " + atc.getType().getName() + " has no roles set", atc.getType());
				addValidationResult(vr);
			}

			for (RolePlayerConstraint rpc : atc.getPlayerConstraints()) {
				if (rpc.getRole() == null) {
					ValidationResult vr = new ValidationResult("No role set for role player constraint", rpc);
					addValidationResult(vr);
				}

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
		if (prefix != null) {
			if (prefix.equals("mailto"))
				return;

			boolean found = false;
			for (MappingElement me : schema.getMappings()) {
				if (me.getKey().equals(prefix)) {
					found = true;
					break;
				}
			}
			if (!found) {
				ValidationResult vr = new ValidationResult("Unknown prefix used", tt);
				vr.addValidationAction(new NewPrefixAction(commandStack, schema, prefix));
				addValidationResult(vr);
			}
		}
	}

	private String getPrefix(String name) {
		if (name.startsWith("http://"))
			return null;
		int index = name.indexOf(':');
		if (index != -1)
			return name.substring(0, index);

		return null;
	}

	/*
	 * private void validateRoleConstraints(RoleTypeConstraints rtc) { // check
	 * if check for type is needed }
	 */

	private void validateOccurrenceConstraint(TopicType topicType) {
		for (OccurrenceTypeConstraint otc : topicType.getOccurrenceConstraints()) {
			
			if ((otc.getType() != null) && (otc.getType().getKind() != KindOfTopicType.OCCURRENCE_TYPE)) {
				ValidationResult vr = new ValidationResult("The topic " + otc.getType().getName()
				        + " may not be type for an occurrence constraint", otc.getType());
				vr.addValidationAction(new OccurrenceConstraintSelectTypeAction(commandStack));
				vr.addValidationAction(new OccurrenceConstraintCreateTypeAction(commandStack));
				addValidationResult(vr);
			}
		}
	}

	private void validateNameConstraint(TopicType topicType) {
		for (NameTypeConstraint ntc : topicType.getNameContraints()) {
			if (ntc.getType() == null) {
				/*
				 * ValidationResult vr = new ValidationResult(
				 * "No type for name type constraint set", ntc);
				 * vr.addValidationAction(new NameConstraintSelectTypeAction(
				 * commandStack)); vr.addValidationAction(new
				 * NameConstraintCreateTypeAction( commandStack));
				 * addValidationResult(vr);
				 */

			} else if (ntc.getType().getKind() != KindOfTopicType.NAME_TYPE) {
				ValidationResult vr = new ValidationResult("The topic " + ntc.getType().getName()
				        + " may not be type for an name constraint", ntc.getType());

				vr.addValidationAction(new NameConstraintSelectTypeAction(commandStack));
				vr.addValidationAction(new NameConstraintCreateTypeAction(commandStack));
				addValidationResult(vr);
			}
		}
	}

}
