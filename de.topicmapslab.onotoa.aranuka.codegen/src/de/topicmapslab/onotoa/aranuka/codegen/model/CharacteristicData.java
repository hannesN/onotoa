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

import static de.topicmapslab.onotoa.aranuka.codegen.model.IAnnotationKeys.CREATE_NEW;
import static de.topicmapslab.onotoa.aranuka.codegen.model.IAnnotationKeys.ROWS;
import de.topicmapslab.kuria.annotation.widgets.Check;
import de.topicmapslab.kuria.annotation.widgets.Combo;
import de.topicmapslab.kuria.annotation.widgets.Editable;
import de.topicmapslab.tmcledit.model.TMCLConstruct;

/**
 * Model for occurrence and name constraints
 * 
 * @author Hannes Niederhausen
 * 
 */
@Editable
public class CharacteristicData extends FieldData {

	/**
     * @param parent
     */
    public CharacteristicData(TMCLConstruct parent) {
    	super(parent);
    }

    /**
     * 
     * @return flag whether to provide a button to create a new instance 
     */
	@Check (label="Enable New instance Creation", weight=6)
    public boolean isCreateNew() {
    	return getBooleanValueOf(CREATE_NEW);
    }

	/**
	 * Flag whether to provide a button to create a new instance
	 * @param createnew 
	 */
	public void setCreateNew(boolean createnew) {
    	setValue(CREATE_NEW, Boolean.toString(createnew));
    }
	
	/**
	 * 
	 * @return the number of rows for a text field for the attribute
	 */
	@Combo (label="Number of Rows", optional=true, weight=8)
    public int getRows() {
    	return getIntValueOf(ROWS);
    }

	/**
	 * Sets the number of rows for a text field for the attribute
	 * @param numRows the number of rows: 1..10
	 */
	public void setRows(int numRows) {
    	setValue(ROWS, Integer.toString(numRows));
    }
}
