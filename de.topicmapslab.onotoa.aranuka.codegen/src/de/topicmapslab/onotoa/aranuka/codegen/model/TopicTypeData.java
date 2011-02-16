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

import static de.topicmapslab.onotoa.aranuka.codegen.model.IAnnotationKeys.CATEGORY;
import static de.topicmapslab.onotoa.aranuka.codegen.model.IAnnotationKeys.NAME;
import de.topicmapslab.kuria.annotation.widgets.Editable;
import de.topicmapslab.kuria.annotation.widgets.TextField;
import de.topicmapslab.tmcledit.model.TMCLConstruct;
import de.topicmapslab.tmcledit.model.TopicType;

/**
 * Model for {@link TopicType}s
 * 
 * @author Hannes Niederhausen
 * 
 */
@Editable
public class TopicTypeData extends GeneratorData {

	/**
     * @param parent
     */
    public TopicTypeData(TMCLConstruct parent) {
    	super(parent);
    }

    /**
     * 
     * @return the category name
     */
	@TextField(label = "Category", optional=true)
	public String getCategory() {
		return getValueOf(CATEGORY);
	}
	
	/**
	 * Sets the name of the category
	 * @param category the new name
	 */
	public void setCategory(String category) {
		setValue(CATEGORY, category);
	}
	
	/**
	 * The name of the generated class
	 * @return class name, must be Java compatible
	 */
	@TextField(label="Class name", optional=true)
	public String getName() {
		return getValueOf(NAME);
	}
	
	/**
	 * 
	 * @param name the name of the class
	 */
	public void setName(String name) {
		setValue(NAME, name);
	}
	
}
