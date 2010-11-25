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
package de.topicmapslab.onotoa.aranuka.codegen.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import de.topicmapslab.onotoa.aranuka.codegen.Activator;
import de.topicmapslab.tmcledit.model.views.ModelView;

/**
 * @author Hannes Niederhausen
 *
 */
public class CodegenPage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	/**
     * 
     */
    public CodegenPage() {
    	setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("Preference page for the Aranuka/Kuria Code Generation.");
    }
	
	/**
     * {@inheritDoc}
     */
    @Override
    public void init(IWorkbench workbench) {
    }

	/**
     * {@inheritDoc}
     */
    @Override
    protected void createFieldEditors() {
    	addField(new BooleanFieldEditor(IPreferenceConstants.P_HIDE_NODES, "Hide Code Generation ModelView nodes.", getFieldEditorParent()));	    
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean performOk() {
        boolean result = super.performOk();
        ModelView.getInstance().refreshView(true);
        return result;
    }
    
}
