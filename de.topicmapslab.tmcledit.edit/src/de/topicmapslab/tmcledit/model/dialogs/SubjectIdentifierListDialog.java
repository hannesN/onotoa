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
package de.topicmapslab.tmcledit.model.dialogs;

import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.fieldassist.ContentAssistCommandAdapter;

import de.topicmapslab.tmcledit.model.psiprovider.PSIContentProposalProvider;

/**
 * @author Hannes Niederhausen
 *
 */
public class SubjectIdentifierListDialog extends StringListSelectionDialog {

	private String topicName;
	
	public SubjectIdentifierListDialog(Shell parentShell) {
	    super(parentShell);
    }


	@Override
	protected InputDialog getInputDialog(String text, String inputDescription, String init, IInputValidator validator) {
		return new AssistInputDialog(getShell(), text, inputDescription, init, validator);
	}
	
	public void setTopicName(String topicName) {
	    this.topicName = topicName;
    }
	
	private class AssistInputDialog extends InputDialog {
		public AssistInputDialog(Shell parentShell, String dialogTitle, String dialogMessage, String initialValue,
                IInputValidator validator) {
	        super(parentShell, dialogTitle, dialogMessage, initialValue, validator);
        }

		@Override
		protected Control createDialogArea(Composite parent) {
		    Control control = super.createDialogArea(parent);

		    PSIContentProposalProvider proposalProvider = new PSIContentProposalProvider();
		    proposalProvider.setName(topicName);
			new ContentAssistCommandAdapter(getText(), 
					new TextContentAdapter(), 
					proposalProvider, 
					null,
					PSIContentProposalProvider.KEYS); 
		    
		    return control;
		}
	}
}
