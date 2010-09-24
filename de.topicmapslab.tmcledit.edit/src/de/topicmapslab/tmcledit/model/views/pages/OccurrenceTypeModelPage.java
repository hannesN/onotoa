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

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ListDialog;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.OccurrenceType;
import de.topicmapslab.tmcledit.model.commands.GenericSetCommand;
import de.topicmapslab.tmcledit.model.commands.SetDatatypeCommand;

/**
 * @author Hannes Niederhausen
 *
 */
public class OccurrenceTypeModelPage extends AbstractRegExpTopicTypeModelPage {

	private final String xsdDatatypes[] = {
			"xsd:anyType",
			"xsd:anyURI",
			"xsd:base64Binary",
			"xsd:boolean",
			"xsd:byte",
			"xsd:date",
			"xsd:dateTime",
			"xsd:decimal",
			"xsd:double",
			"xsd:duration",
			"xsd:ENTITIES",
			"xsd:ENTITY",
			"xsd:float",
			"xsd:gDay",
			"xsd:gMonth",
			"xsd:gMonthDay",
			"xsd:gYear",
			"xsd:gYearMonth",
			"xsd:hexBinary",
			"xsd:ID",
			"xsd:IDREF",
			"xsd:IDREFS",
			"xsd:int",
			"xsd:integer",
			"xsd:language",
			"xsd:long",
			"xsd:Name",
			"xsd:NCName",
			"xsd:negativeInteger",
			"xsd:NMTOKEN",
			"xsd:NMTOKENS",
			"xsd:nonNegativeInteger",
			"xsd:nonPositiveInteger",
			"xsd:normalizedString",
			"xsd:NOTATION",
			"xsd:positiveInteger",
			"xsd:QName",
			"xsd:short",
			"xsd:string",
			"xsd:time",
			"xsd:token",
			"xsd:unsignedByte",
			"xsd:unsignedInt",
			"xsd:unsignedLong",
			"xsd:unsignedShort",
			"xsd:WGS84"
	};
	
	private Text datatypeText;
	private Button datatypeButton;
	private Button uniqueButton;
	
	public OccurrenceTypeModelPage() {
		super("occurrence type");
	}

	@Override
	protected void createAdditionalControls(Composite parent,
			FormToolkit toolkit) {
		
		toolkit.createLabel(parent, "Unique:");
		uniqueButton = toolkit.createButton(parent, "", SWT.CHECK);
		GridData gd = new GridData();
		gd.horizontalSpan = 2;
		uniqueButton.setLayoutData(gd);
		
		toolkit.createLabel(parent, "Datatype:");
		datatypeText = toolkit.createText(parent, "", SWT.BORDER);
		datatypeText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		datatypeText.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				SetDatatypeCommand cmd = new SetDatatypeCommand(getCastedModel(), datatypeText.getText());
				getCommandStack().execute(cmd);
			}
		});
		datatypeText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.character==SWT.CR) {
					SetDatatypeCommand cmd = new SetDatatypeCommand(getCastedModel(), datatypeText.getText());
					getCommandStack().execute(cmd);
				}
			}
		});
		
		datatypeButton = toolkit.createButton(parent, "...", SWT.PUSH);
		hookButtonListener();
		
		super.createAdditionalControls(parent, toolkit);
	}
	
	@Override
	protected void setEnabled(boolean enabled) {
	    super.setEnabled(enabled);
	    uniqueButton.setEnabled(enabled);
	    datatypeText.setEnabled(enabled);
	    datatypeButton.setEnabled(enabled);
	}
	
	private void hookButtonListener() {
		
		datatypeButton.addSelectionListener(new SelectionAdapter() {
			@Override		
			public void widgetSelected(SelectionEvent e) {
				ListDialog dlg = new ListDialog(datatypeButton.getShell());
				dlg.setLabelProvider(new LabelProvider(){
					@Override
					public String getText(Object element) {
						return (String) element;
					}
				});
				dlg.setContentProvider(new ArrayContentProvider());
				
				if (getCastedModel().getDataType()!=null)
					dlg.setInitialSelections(new String[]{getCastedModel().getDataType()});
				
				dlg.setInput(xsdDatatypes);
				
				if (dlg.open()==Dialog.OK) {
					if (dlg.getResult().length>0) {
						String r = (String) dlg.getResult()[0];
						SetDatatypeCommand cmd = new SetDatatypeCommand(getCastedModel(), r);
						getCommandStack().execute(cmd);
					}
					
				}
			}
		});
		uniqueButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if ((getModel()!=null) &&
					(uniqueButton.getSelection()!=getCastedModel().isUnique()) )
					getCommandStack().execute(
					        new GenericSetCommand(getModel(), ModelPackage.OCCURRENCE_TYPE__UNIQUE, new Boolean(
					                uniqueButton.getSelection())));
			}
		});
	}
	
	private OccurrenceType getCastedModel() {
		return (OccurrenceType) getModel();
	}
	
	@Override
	protected int getRegExpFeatureId() {
	    return ModelPackage.OCCURRENCE_TYPE__REG_EXP;
	}
	
	@Override
	public void updateUI() {
		if (getModel()!=null) {
			if (getCastedModel().getDataType()!=null)
				datatypeText.setText(getCastedModel().getDataType());
			else 
				datatypeText.setText("");
			
			uniqueButton.setSelection(getCastedModel().isUnique());
		} else {
			uniqueButton.setSelection(false);
			datatypeText.setText("");
		}
		
		super.updateUI();
	}
	
}
