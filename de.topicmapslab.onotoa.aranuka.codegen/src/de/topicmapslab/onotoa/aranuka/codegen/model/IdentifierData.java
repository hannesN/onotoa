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

import static de.topicmapslab.onotoa.aranuka.codegen.model.IAnnotationKeys.AUTO_GENERATE;
import de.topicmapslab.kuria.annotation.widgets.Check;
import de.topicmapslab.kuria.annotation.widgets.Editable;
import de.topicmapslab.tmcledit.model.TMCLConstruct;

/**
 * Model for identifier constraints 
 * 
 * @author Hannes Niederhausen
 * 
 */
@Editable
public class IdentifierData extends FieldData {

	/**
     * @param parent
     */
    public IdentifierData(TMCLConstruct parent) {
    	super(parent);
    }

    /**
     * 
     * @return Flag whether the value should be auto generated
     */
	@Check
	public boolean isAutoGenerated() {
		return getBooleanValueOf(AUTO_GENERATE);
	}
	
	/**
	 * Sets the flag whether the value should be auto generated
	 * @param val 
	 */
	public void setAutoGenerated(boolean val) {
		setValue(AUTO_GENERATE, Boolean.toString(val));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		String r = "IdentifierData=[";
		
		r += "name="+getName();
		r += ";hidden="+isHidden();
		r += ";autoGenerated="+isAutoGenerated();
		r += ";typeLabel="+isTypeLabel();
		
		return r+"]";
	}
}
