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
package de.topicmapslab.tmcledit.model.views.pages;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * @author Hannes Niederhausen
 *
 */
public class RoleTypePage extends TopicTypePage {

	/**
	 * Constructor
	 */
	public RoleTypePage() {
		super("role type");
    }
	
	@Override
	protected void createAdditionalControls(Composite parent, FormToolkit toolkit) {
		// we don't need a refies part
	}
}
