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
import static de.topicmapslab.onotoa.aranuka.codegen.model.IAnnotationKeys.HIDDEN;
import static de.topicmapslab.onotoa.aranuka.codegen.model.IAnnotationKeys.LABEL;
import static de.topicmapslab.onotoa.aranuka.codegen.model.IAnnotationKeys.NAME;
import static de.topicmapslab.onotoa.aranuka.codegen.model.IAnnotationKeys.TYPELABEL;
import static de.topicmapslab.onotoa.aranuka.codegen.model.IAnnotationKeys.WEIGHT;
import de.topicmapslab.kuria.annotation.widgets.Check;
import de.topicmapslab.kuria.annotation.widgets.Combo;
import de.topicmapslab.kuria.annotation.widgets.TextField;
import de.topicmapslab.tmcledit.model.TMCLConstruct;

/**
 * @author Hannes Niederhausen
 *
 */
public abstract class FieldData extends GeneratorData {

	/**
	 * 
	 */
	public FieldData() {
		super();
	}

	/**
	 * @param parent
	 */
	public FieldData(TMCLConstruct parent) {
		super(parent);
	}

	@TextField(label = "Field Name", optional = true, weight=10)
    public String getName() {
    	return getValueOf(NAME);
    }

	public void setName(String name) {
    	setValue(NAME, name);
    }

	@TextField(label = "Field Label", optional = true, weight=9)
    public String getLabel() {
    	return getValueOf(LABEL);
    }

	public void setLabel(String name) {
    	setValue(LABEL, name);
    }
	
	@Combo(label="Weight", weight=8)
	public int getWeight() {
		int tmp = getIntValueOf(WEIGHT);
		return (tmp!=-1) ? tmp : 1;
	}
	
	public void setWeight(int weight) {
		setValue(WEIGHT, weight);
	}

	@Check(label = "Generate Java Field", weight=6)
    public boolean isGenerateAttribute() {
    	return getBooleanValueOf(GENERATE_ATTRIBUTE, true);
    }

	public void setGenerateAttribute(boolean val) {
    	setValue(GENERATE_ATTRIBUTE, Boolean.toString(val));
    }

	@Check(weight=6)
    public boolean isHidden() {
    	return getBooleanValueOf(HIDDEN);
    }

	public void setHidden(boolean hidden) {
    	setValue(HIDDEN, Boolean.toString(hidden));
    }
	
	@Check(weight=6, label="Read Only")
    public boolean isReadOnly() {
    	return getBooleanValueOf(READONLY);
    }

	public void setReadOnly(boolean readOnly) {
    	setValue(READONLY, Boolean.toString(readOnly));
    }

	@Check(label = "Use value as String representation", weight=6)
    public boolean isTypeLabel() {
    	return getBooleanValueOf(TYPELABEL);
    }

	public void setTypeLabel(boolean val) {
    	setValue(TYPELABEL, Boolean.toString(val));
    }

}