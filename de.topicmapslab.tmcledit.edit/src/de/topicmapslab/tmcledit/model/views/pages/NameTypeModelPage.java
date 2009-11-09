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
/**
 * 
 */
package de.topicmapslab.tmcledit.model.views.pages;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.topicmapslab.tmcledit.model.ModelPackage;

/**
 * @author Hannes Niederhausen
 * 
 */
public class NameTypeModelPage extends AbstractRegExpTopicTypeModelPage {
	public NameTypeModelPage() {
		super("name type");
	}

	@Override
	protected void createAdditionalControls(Composite parent, FormToolkit toolkit) {

		super.createAdditionalControls(parent, toolkit);
	}

	@Override
	protected int getRegExpFeatureId() {
		return ModelPackage.NAME_TYPE__REG_EXP;
	}
}
