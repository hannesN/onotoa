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

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.topicmapslab.tmcledit.model.AbstractRegExpTopicType;
import de.topicmapslab.tmcledit.model.util.TextObserver;

/**
 * The property page for {@link AbstractRegExpTopicType}s
 * 
 * @author Hannes Niederhausen
 *
 */
public abstract class AbstractRegExpTopicTypeModelPage extends ScopedTopicTypePage {

	private Text regExpText; 
	
	/**
	 * Constructor
	 * @param id page id
	 */
	public AbstractRegExpTopicTypeModelPage(String id) {
	    super(id);
    }
	
	@Override
	protected void createAdditionalControls(Composite parent, FormToolkit toolkit) {
		toolkit.createLabel(parent, "reg. exp");
		regExpText = toolkit.createText(parent, "", SWT.BORDER);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		regExpText.setLayoutData(gd);
		TextObserver.observe(regExpText, this, getRegExpFeatureId());
		
		super.createAdditionalControls(parent, toolkit);
	}

	protected abstract int getRegExpFeatureId();
	
	@Override
	protected void setEnabled(boolean enabled) {
	    super.setEnabled(enabled);
	    regExpText.setEnabled(enabled);
	}
	
	@Override
	public void updateUI() {
		AbstractRegExpTopicType type = (AbstractRegExpTopicType) getModel();
		if (type==null)
			regExpText.setText("");
		else
			regExpText.setText(type.getRegExp());
		
		super.updateUI();
	}
}
