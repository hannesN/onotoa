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
import de.topicmapslab.tmcledit.model.TopicMapSchema;

/**
 * Model for {@link TopicMapSchema}s
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

	@Check (label="Enable New instance Creation", weight=6)
    public boolean isCreateNew() {
    	return getBooleanValueOf(CREATE_NEW);
    }

	public void setCreateNew(boolean hidden) {
    	setValue(CREATE_NEW, Boolean.toString(hidden));
    }
	
	@Combo (label="Number of Rows", optional=true, weight=8)
    public int getRows() {
    	return getIntValueOf(ROWS);
    }

	public void setRows(int hidden) {
    	setValue(ROWS, Integer.toString(hidden));
    }
}
