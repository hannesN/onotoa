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
package de.topicmapslab.onotoa.search.dialogs;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import de.topicmapslab.tmcledit.model.TopicType;

/**
 * @author Hannes Niederhausen
 *
 */
public class ResultDialog extends Dialog {

	private Object input;
	private TreeViewer viewer;
	private TopicType topicType;
	
	/**
	 * @param parentShell
	 */
	public ResultDialog(Shell parentShell, TopicType topicType) {
		super(parentShell);
		this.topicType = topicType;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite comp = new Composite(parent, SWT.NONE);
		comp.setLayoutData(new GridData(GridData.FILL_BOTH));
		comp.setLayout(new FillLayout());

		viewer = new TreeViewer(comp, SWT.BORDER);
		viewer.setContentProvider(new TreeNodeContentProvider());
		viewer.setLabelProvider(new TreeNodeLabelProvider());
		
		if (input!=null)
			viewer.setInput(input);
		
		return comp; 
	}

	@Override
	protected void configureShell(Shell newShell) {
		newShell.setSize(700, 500);
		newShell.setText("Results for "+topicType.getName()+":");
		super.configureShell(newShell);
	}
	
	/**
	 * @param input the input to set
	 */
	public void setInput(Object input) {
		this.input = input;
		if (viewer!=null)
			viewer.setInput(input);
	}
	
}
