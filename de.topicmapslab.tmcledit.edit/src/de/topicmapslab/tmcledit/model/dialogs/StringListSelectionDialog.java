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
package de.topicmapslab.tmcledit.model.dialogs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import de.topicmapslab.tmcledit.model.util.ImageConstants;
import de.topicmapslab.tmcledit.model.util.ImageProvider;

/**
 * @author Hannes Niederhausen
 * 
 */
public class StringListSelectionDialog extends Dialog {

	private ListViewer stringListViewer;
	private ArrayList<String> stringList;
	private IInputValidator validator;
	private String inputDescription;
	private String text;

	private Button addButton, removeButton, removeAllButton, editButton;

	public StringListSelectionDialog(Shell parentShell) {
		super(parentShell);
	}

	public void setValidator(IInputValidator validator) {
		this.validator = validator;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite comp = new Composite(parent, SWT.NONE);
		comp.setLayout(new GridLayout(3, false));
		comp.setLayoutData(new GridData(GridData.FILL_BOTH));

		stringListViewer = new ListViewer(comp);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.widthHint = 150;
		stringListViewer.getList().setLayoutData(gd);
		stringListViewer.setContentProvider(new ArrayContentProvider());
		stringListViewer.setLabelProvider(new StringLableProvider());
		stringListViewer.setInput(stringList);
		stringListViewer.addDoubleClickListener(new IDoubleClickListener() {

			public void doubleClick(DoubleClickEvent event) {
				IStructuredSelection sel = (IStructuredSelection) stringListViewer.getSelection();
				if (sel.isEmpty())
					return;
				editString((String) sel.getFirstElement());
			}
		});

		stringListViewer.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {

				IStructuredSelection sel = (IStructuredSelection) event.getSelection();
				if (sel.isEmpty()) {
					removeButton.setEnabled(false);
					editButton.setEnabled(false);
				} else {
					removeButton.setEnabled(true);
					editButton.setEnabled(true);
				}

			}
		});
		createButtonRow(comp);
		validate();
		return comp;
	}

	private void createButtonRow(Composite parent) {
		Composite comp = new Composite(parent, SWT.NONE);
		comp.setLayout(new GridLayout());
		comp.setLayoutData(new GridData(GridData.FILL_VERTICAL));
		GridData gd = new GridData();
		// gd.widthHint = 100;
		GridDataFactory fac = GridDataFactory.createFrom(gd);

		addButton = new Button(comp, SWT.PUSH);
		addButton.setText("");
		addButton.setImage(ImageProvider.getImage(ImageConstants.NEW));
		addButton.setToolTipText("Add selected Identifiers");
		fac.applyTo(addButton);
		addButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addString();
			}
		});

		editButton = new Button(comp, SWT.PUSH);
		editButton.setText("");
		editButton.setImage(ImageProvider.getImage(ImageConstants.EDIT));
		editButton.setToolTipText("Edit selected Identifier");
		editButton.setEnabled(false);
		fac.applyTo(editButton);
		editButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				IStructuredSelection selection = (IStructuredSelection) stringListViewer.getSelection();
				if (selection.isEmpty())
					return;

				editString((String) selection.getFirstElement());
			}
		});

		removeButton = new Button(comp, SWT.PUSH);
		removeButton.setText("");
		removeButton.setImage(ImageProvider.getImage(ImageConstants.REMOVE));
		removeButton.setToolTipText("Remove selected Identifiers");
		removeButton.setEnabled(false);
		fac.applyTo(removeButton);
		removeButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				removeSelection();
			}
		});

		removeAllButton = new Button(comp, SWT.PUSH);
		removeAllButton.setText("");
		removeAllButton.setImage(ImageProvider.getImage(ImageConstants.CLEAR));
		removeAllButton.setToolTipText("Remove all Identifiers");
		removeAllButton.setEnabled(false);
		fac.applyTo(removeAllButton);
		removeAllButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				stringList.clear();
				removeAllButton.setEnabled(false);
				stringListViewer.refresh();
			}
		});

	}

	protected void addString() {
		InputDialog dlg = getInputDialog("New..", getInputDescription(), "", validator);

		if (Dialog.OK == dlg.open()) {
			String result = dlg.getValue();
			stringList.add(result);
			stringListViewer.refresh();
			removeAllButton.setEnabled(true);
		}
	}

	protected InputDialog getInputDialog(String text, String inputDescription, String init, IInputValidator validator) {
		return new InputDialog(getShell(), text, inputDescription, init, validator);
	}

	protected void editString(String string) {
		InputDialog dlg = getInputDialog("Edit..", getInputDescription(), string, validator);
		if (Dialog.OK == dlg.open()) {
			String result = dlg.getValue();
			int i = stringList.indexOf(string);
			stringList.remove(string);
			stringList.add(i, result);
			stringListViewer.refresh();
		}
	}

	protected IInputValidator getValidator() {
		return validator;
	}

	protected ListViewer getStringListViewer() {
		return stringListViewer;
	}

	private void removeSelection() {
		IStructuredSelection sel = (IStructuredSelection) stringListViewer.getSelection();
		for (Iterator it = sel.iterator(); it.hasNext();) {
			stringList.remove((String) it.next());
		}
		stringListViewer.refresh();
		if (stringList.size() <= 0)
			removeAllButton.setEnabled(false);

	}

	public String getInputDescription() {
		return (inputDescription == null) ? "" : inputDescription;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return text == null ? "" : text;
	}

	public void setInputDescription(String inputDescription) {
		this.inputDescription = inputDescription;
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setSize(400, 500);
		newShell.setText(getText());
	}

	public ArrayList<String> getStringList() {
		return stringList;
	}

	public void setSelectedTopics(List<String> stringList) {
		this.stringList = new ArrayList<String>();
		this.stringList.addAll(stringList);
	}

	private class StringLableProvider extends LabelProvider {
		@Override
		public String getText(Object element) {
			return (String) element;
		}
	}

	private void validate() {

		if (stringList != null && stringList.size() > 0)
			removeAllButton.setEnabled(true);
		else
			removeAllButton.setEnabled(false);

	}
}
