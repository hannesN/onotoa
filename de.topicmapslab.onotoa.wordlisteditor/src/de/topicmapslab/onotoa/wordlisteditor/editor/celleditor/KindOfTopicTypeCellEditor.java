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
package de.topicmapslab.onotoa.wordlisteditor.editor.celleditor;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.topicmapslab.tmcledit.model.KindOfTopicType;

/**
 * Cell editor using a widget with radio buttons
 * 
 * @author Hannes Niederhausen
 *
 */
public class KindOfTopicTypeCellEditor extends CellEditor {

	private KindOfTopicType value;
	private Button buttons[];
	private Shell popupShell;
	private Composite comp;
	
	
	/**
	 * Constructor
	 * 
	 * @param parent the parent widget, which means the table
	 */
	public KindOfTopicTypeCellEditor(Composite parent) {
	    super(parent);
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Control createControl(Composite parent) {
		
		popupShell = new Shell(parent.getDisplay(), SWT.POP_UP);
		popupShell.setLayout(new FillLayout());
		popupShell.setVisible(false);
		
		FormToolkit toolkit = new FormToolkit(parent.getDisplay());
		comp = toolkit.createComposite(popupShell);
		comp.setLayout(new GridLayout(2, false));
		
		buttons = new Button[6];
		
		buttons[0] = toolkit.createButton(comp, "Topic Type", SWT.RADIO);
		buttons[1] = toolkit.createButton(comp, "Occurrence Type", SWT.RADIO);
		buttons[2] = toolkit.createButton(comp, "Name Type", SWT.RADIO);
		buttons[3] = toolkit.createButton(comp, "Role Type", SWT.RADIO);
		buttons[4] = toolkit.createButton(comp, "Association Type", SWT.RADIO);
		buttons[5] = toolkit.createButton(comp, "NoType", SWT.RADIO);
	
		return popupShell;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void activate() {
	    
	    int val = Math.min(value.getValue(), 5);
	    buttons[val].setSelection(true);
	    
	    
	    
	    popupShell.setSize(comp.computeSize(SWT.DEFAULT, SWT.DEFAULT, true));
	    popupShell.setVisible(true);
	    
	    super.activate();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deactivate() {

		for (int i=0; i<6; i++) {
			if (buttons[i].getSelection()) {
				value = KindOfTopicType.get(i);
			}
		}
		
		if (value==KindOfTopicType.SCOPE_TYPE)
			value=KindOfTopicType.NO_TYPE;
		
	    super.deactivate();
	    popupShell.setVisible(true);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void dispose() {
		popupShell.dispose();
	    super.dispose();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Object doGetValue() {
		return value;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doSetFocus() {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doSetValue(Object value) {
		this.value = (KindOfTopicType) value;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public LayoutData getLayoutData() {
		LayoutData result = new LayoutData();
		Control control = getControl();
		if (control != null) {
			Point computeSize = control.computeSize(SWT.DEFAULT, SWT.DEFAULT,
					true);
			result.minimumWidth = computeSize.x;
			result.minimumHeight = computeSize.y;
		}
		
		return result;
	}
	
	
}
