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

import static de.topicmapslab.onotoa.aranuka.codegen.model.IAnnotationKeys.*;

import java.util.ArrayList;

import de.topicmapslab.kuria.annotation.widgets.Editable;
import de.topicmapslab.kuria.annotation.widgets.Hidden;
import de.topicmapslab.kuria.annotation.widgets.List;
import de.topicmapslab.kuria.annotation.widgets.TextField;
import de.topicmapslab.tmcledit.model.Annotation;
import de.topicmapslab.tmcledit.model.TMCLConstruct;
import de.topicmapslab.tmcledit.model.views.extension.IModelExtension;

/**
 * @author Hannes Niederhausen
 *
 */
@Editable
public class CodeGenerator implements IModelExtension {

	@Hidden
	private TMCLConstruct construct;
	
	
	@TextField
	private String getCategory() {
		java.util.List<String> tmp = getValueOf(CATEGORY);
		if (tmp.isEmpty())
			return null;
		return tmp.get(0);
	}
	
	
	
	
	private java.util.List<String> getValueOf(String annotationKey) {
		
		java.util.List<String> result = new ArrayList<String>();
		
		for (Annotation a : construct.getAnnotations()) {
			if (a.getKey().equals(annotationKey))
				result.add(a.getValue());
		}
		
		return result;
	}
}
