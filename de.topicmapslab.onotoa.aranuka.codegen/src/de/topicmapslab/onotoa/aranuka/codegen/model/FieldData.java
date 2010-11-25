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
 * Abstract class which holds fields for identifier and characteristics
 * 
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

	/**
	 * 
	 * @return the name of the generated field (attribute)
	 */
	@TextField(label = "Field Name", optional = true, weight=10)
    public String getName() {
    	return getValueOf(NAME);
    }

	/**
	 * Sets @return the name of the generated field (attribute)
	 * @param name the new name
	 */
	public void setName(String name) {
    	setValue(NAME, name);
    }

	/**
	 * 
	 * @return the label used in the generated input mask for this field
	 */
	@TextField(label = "Field Label", optional = true, weight=9)
    public String getLabel() {
    	return getValueOf(LABEL);
    }
	/**
	 * Sets the label used in the generated input mask for this field
	 */
	public void setLabel(String name) {
    	setValue(LABEL, name);
    }
	
	/**
	 * 
	 * @return the description of the field used in tooltips for the input mask
	 */
	@TextField(label="Description", optional=true, weight=8, rows=8)
	public String getDescription() {
		return getValueOf(DESCRIPTION);
	}
	
	/**
	 * Sets the description of the field used in tooltips for the input mask
	 * @param dsc the description
	 */
	public void setDescription(String dsc) {
		setValue(DESCRIPTION, dsc);
	}
	
	/**
	 * 
	 * @return the weight which is used to order the field widgets
	 */
	@Combo(label="Weight", weight=8)
	public int getWeight() {
		int tmp = getIntValueOf(WEIGHT);
		return (tmp!=-1) ? tmp : 1;
	}
	
	/**
	 *  Sets the weight which is used to order the field widgets
	 * @param weight the weight 0 is minimum 10 is max; 1 is default
	 */
	public void setWeight(int weight) {
		setValue(WEIGHT, weight);
	}

	/**
	 * 
	 * @return the flag whether to generate a field in the class 
	 */
	@Check(label = "Generate Java Field", weight=6)
    public boolean isGenerateAttribute() {
    	return getBooleanValueOf(GENERATE_ATTRIBUTE, true);
    }

	/**
	 * the flag whether to generate a field in the class
	 * @param val <code>true</code> the field will be created (default)
	 */
	public void setGenerateAttribute(boolean val) {
    	setValue(GENERATE_ATTRIBUTE, Boolean.toString(val));
    }

	/**
	 * 
	 * @return flag whether the generated field should be visible in the input mask
	 */
	@Check(weight=6)
    public boolean isHidden() {
    	return getBooleanValueOf(HIDDEN);
    }

	/**
	 * Sets the flag whether the generated field should be visible in the input mask
	 * @param hidden <code>true</code> if the field should be visible (default)
	 */
	public void setHidden(boolean hidden) {
    	setValue(HIDDEN, Boolean.toString(hidden));
    }
	
	/**
	 * 
	 * @return flag whether the field is read-only
	 */
	@Check(weight=6, label="Read Only")
    public boolean isReadOnly() {
    	return getBooleanValueOf(READONLY);
    }

	/**
	 * Sets the flag whether the field is read-only
	 * @param readOnly <code>true</code> flag is read-only; <code>false</code> is default
	 */
	public void setReadOnly(boolean readOnly) {
    	setValue(READONLY, Boolean.toString(readOnly));
    }

	/**
	 * 
	 * @return flag whether the field should be used instead of toString()
	 */
	@Check(label = "Use value as String representation", weight=6)
    public boolean isTypeLabel() {
    	return getBooleanValueOf(TYPELABEL);
    }

	/**
	 * Sets the flag whether the field should be used instead of toString()
	 * @param val <code>false</code> field is not used (default)
	 */
	public void setTypeLabel(boolean val) {
    	setValue(TYPELABEL, Boolean.toString(val));
    }

}