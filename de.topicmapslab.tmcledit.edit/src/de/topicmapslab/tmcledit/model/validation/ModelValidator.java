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

import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.eclipse.emf.common.command.CommandStack;

import de.topicmapslab.tmcledit.model.Annotation;
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
import de.topicmapslab.tmcledit.model.actions.IgnorePrefixAction;
import de.topicmapslab.tmcledit.model.validation.ValidationResult.Priority;
import de.topicmapslab.tmcledit.model.validation.actions.AssociationCreateTypeAction;
import de.topicmapslab.tmcledit.model.validation.actions.AssociationSelectTypeAction;
import de.topicmapslab.tmcledit.model.validation.actions.ModifyPrefix;
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

	private Set<String> ignoredPrefixes = null;
	
	private CommandStack commandStack;
	private List<String> foundNotFoundPrefixes;

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
		
		for (Annotation a : schema.getAnnotations()) {
			if ("validator.ignoreprefix".equals(a.getKey())) {
				if (ignoredPrefixes==null)
					ignoredPrefixes = new HashSet<String>();
				ignoredPrefixes.add(a.getValue());
			}
			
		}
		
		foundNotFoundPrefixes = new ArrayList<String>();
		validateAssociationConstraints();
		validateTopicTypes();
		validateDuplicateNames();
		
		foundNotFoundPrefixes = null;
		return resultList;
	}

	private Set<String> getIgnoredPrefixes() {
	    if (ignoredPrefixes==null)
	    	return Collections.emptySet();
		return ignoredPrefixes;
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

			MappingElement foundME = null;
			for (MappingElement me : schema.getMappings()) {
				if (me.getKey().equals(prefix)) {
					foundME = me;
					break;
				}
			}
			if (foundME==null) {
				if (getIgnoredPrefixes().contains(prefix))
					return;
				if (!(foundNotFoundPrefixes.contains(prefix))) {					
					ValidationResult vr = new ValidationResult("Unknown prefix used: '"+prefix+"'", tt);
					vr.addValidationAction(new NewPrefixAction(commandStack, schema, prefix));
					vr.addValidationAction(new IgnorePrefixAction(commandStack, schema, prefix));
					addValidationResult(vr);

					foundNotFoundPrefixes.add(prefix);
				}
			} else {
				// check if its a valid uri
				String uri = foundME.getValue();
				if (!( (uri.endsWith("#"))
				  || (uri.endsWith("/")))) {
					uri += "#";
				}
				String tmp = c;
				tmp = tmp.replace(prefix + ":", uri);
				try {
					new URL(tmp); 
				} catch (MalformedURLException e){
					String msg = MessageFormat.format("Identifier: {0} resolves to the invalid URL: {1} of topic: {2}", c, tmp, tt.getName());
					ValidationResult vr = new ValidationResult(msg, tt);
					vr.addValidationAction(new ModifyPrefix(commandStack, foundME));
					addValidationResult(vr);
				}
				
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
		for (NameTypeConstraint ntc : topicType.getNameConstraints()) {
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

	@SuppressWarnings("unchecked")
    private void validateDuplicateNames() {
		Map<String, Object> map = new HashMap<String, Object>(5);
		
		for (TopicType tt : schema.getTopicTypes()) {
			Object o = map.get(tt.getName());
			if (o==null)
				map.put(tt.getName(), tt);
			else if (o instanceof TopicType) {
				List<TopicType> tmp = new ArrayList<TopicType>();
				tmp.add(tt);
				map.put(tt.getName(), tmp);
			} else {
				((List<TopicType>)o).add(tt);
			}
		}
		
		for (Object o : map.values()) {
			if (o instanceof List<?>) {
				TopicType tt = ((List<TopicType>)o).get(0);
				ValidationResult vr = new ValidationResult("More than one topic has the name " + tt.getName(), o, Priority.WARNING);
				addValidationResult(vr);
			}
		}
		
	}
	
}
