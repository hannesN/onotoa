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
import de.topicmapslab.kuria.annotation.widgets.Editable;
import de.topicmapslab.kuria.annotation.widgets.TextField;
import de.topicmapslab.tmcledit.model.TMCLConstruct;
import de.topicmapslab.tmcledit.model.TopicMapSchema;

/**
 * Model for {@link TopicMapSchema}s
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

	@TextField(label = "Category", optional=true)
	public String getCategory() {
		return getValueOf(CATEGORY);
	}
	
	public void setCategory(String category) {
		setValue(CATEGORY, category);
	}
	
	@TextField(label="Class name", optional=true)
	public String getName() {
		return getValueOf(NAME);
	}
	
	public void setName(String name) {
		setValue(NAME, name);
	}
	
}
