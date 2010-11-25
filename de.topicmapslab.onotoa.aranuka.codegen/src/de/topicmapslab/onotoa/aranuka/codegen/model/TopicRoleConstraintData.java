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

import static de.topicmapslab.onotoa.aranuka.codegen.model.IAnnotationKeys.GENERATE_ATTRIBUTE;
import static de.topicmapslab.onotoa.aranuka.codegen.model.IAnnotationKeys.LABEL;
import static de.topicmapslab.onotoa.aranuka.codegen.model.IAnnotationKeys.NAME;
import de.topicmapslab.kuria.annotation.widgets.Check;
import de.topicmapslab.kuria.annotation.widgets.Editable;
import de.topicmapslab.kuria.annotation.widgets.TextField;

/**
 * @author Hannes Niederhausen
 * 
 */
@Editable
public class TopicRoleConstraintData extends GeneratorData {
	
	@TextField(label = "Field Name", optional = true)
	public String getName() {
		return getValueOf(NAME);
	}

	public void setName(String name) {
		setValue(NAME, name);
	}

	@Check(label = "Generate Java Field")
	public boolean isGenerateAttribute() {
		return getBooleanValueOf(GENERATE_ATTRIBUTE);
	}

	public void setGenerateAttribute(boolean val) {
		setValue(GENERATE_ATTRIBUTE, Boolean.toString(val));
	}

	@TextField(label = "Field Label", optional = true)
	public String getLabel() {
		return getValueOf(LABEL);
	}

	public void setLabel(String name) {
		setValue(LABEL, name);
	}
}
