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
package de.topicmapslab.onotoa.aranuka.codegen.model;

import java.util.ArrayList;

import de.topicmapslab.kuria.annotation.widgets.Editable;
import de.topicmapslab.kuria.annotation.widgets.Hidden;
import de.topicmapslab.tmcledit.model.Annotation;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.TMCLConstruct;
import de.topicmapslab.tmcledit.model.views.extension.IModelExtension;

/**
 * @author Hannes Niederhausen
 * 
 */
@Editable
public abstract class GeneratorData implements IModelExtension {

	@Hidden
	private TMCLConstruct parent;

	/**
	 * Default constructor
	 */
	public GeneratorData() {
	}

	/**
	 * @param parent
	 *            the construct which annotations will be edited
	 */
	public GeneratorData(TMCLConstruct parent) {
		super();
		this.parent = parent;
	}
	
	protected String getValueOf(String annotationKey) {

		for (Annotation a : parent.getAnnotations()) {
			if (a.getKey().equals(annotationKey))
				return a.getValue();
		}
		return null;
	}

	protected boolean getBooleanValueOf(String annotationKey) {

		return getBooleanValueOf(annotationKey, false);
	}
	
	protected boolean getBooleanValueOf(String annotationKey, boolean defaultVal) {

		String val = getValueOf(annotationKey);
		if (val == null)
			return defaultVal;

		return Boolean.parseBoolean(val);
	}

	protected int getIntValueOf(String annotationKey) {

		String val = getValueOf(annotationKey);
		if (val == null)
			return -1;

		try {
			return Integer.parseInt(val);
		} catch (NumberFormatException e) {
			return -1;
		}
	}

	protected java.util.List<Annotation> getAnnotationsOf(String annotationKey) {
		java.util.List<Annotation> result = new ArrayList<Annotation>();

		for (Annotation a : parent.getAnnotations()) {
			if (a.getKey().equals(annotationKey))
				result.add(a);
		}

		return result;
	}

	protected Annotation getAnnotationOf(String annotationKey) {
		for (Annotation a : parent.getAnnotations()) {
			if (a.getKey().equals(annotationKey))
				return a;
		}
		return null;
	}

	protected void setValue(String annoKey, int value) {
		setValue(annoKey, Integer.toString(value));
	}
	
	protected void setValue(String annoKey, String value) {
		Annotation a = getAnnotationOf(annoKey);
		if (a == null) {
			a = ModelFactory.eINSTANCE.createAnnotation();
			a.setKey(annoKey);
			a.setValue(value);
			// TODO undo/redo add annotation
			parent.getAnnotations().add(a);
		} else {
			// TODO undo/redo with command
			a.setValue(value);
		}

	}
}
