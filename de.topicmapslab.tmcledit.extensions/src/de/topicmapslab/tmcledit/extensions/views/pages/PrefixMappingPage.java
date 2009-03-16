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
package de.topicmapslab.tmcledit.extensions.views.pages;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.topicmapslab.tmcledit.model.MappingElement;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.commands.CreatePrefixMappingCommand;
import de.topicmapslab.tmcledit.model.commands.RemovePrefixMappingCommand;
import de.topicmapslab.tmcledit.model.commands.UpdatePrefixCommand;
import de.topicmapslab.tmcledit.model.dialogs.NewPrefixMappingDialog;
import de.topicmapslab.tmcledit.model.util.PrefixKeyMatcher;

/**
 * @author Hannes Niederhausen
 *
 */
public class PrefixMappingPage extends AbstractModelPage {

	private final String[] columnNames = {"key", "value"};

	private TableViewer tableViewer;

	private Button addButton;

	private Button removeButton;

	public PrefixMappingPage() {
		super("prefix mapping");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void setModel(Object model) {
		TopicMapSchema schema = null;
		if (model instanceof MappingElement) {
			MappingElement me = (MappingElement) model;
			schema = (TopicMapSchema) me.eContainer(); 
		} else if (model instanceof EObjectContainmentEList) {
			// this must be the list of mapping elements
			schema = (TopicMapSchema) ((EObjectContainmentEList)model).getEObject();
		}
		super.setModel(schema);
		
		for (MappingElement me : schema.getMappings()) {
			me.eAdapters().add(this);
		}
	}
	
	@Override
	public void aboutToHide() {
		TopicMapSchema schema = (TopicMapSchema) getModel();
		for (MappingElement me : schema.getMappings()) {
			me.eAdapters().remove(this);
		}
		super.aboutToHide();
	}
	
	@Override
	public void updateUI() {
		if (tableViewer==null)
			return;
		
		TopicMapSchema schema = (TopicMapSchema) getModel();
		tableViewer.setInput(schema.getMappings());
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.Page#createControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createControl(Composite parent) {
		FormToolkit toolkit = new FormToolkit(parent.getDisplay());
		
		Composite comp = toolkit.createComposite(parent);
		comp.setLayout(new GridLayout());
		
		createButtonComposite(toolkit, comp);
		createTableViewer(toolkit, comp);
		
		setControl(comp);
	}

	private void createButtonComposite(FormToolkit toolkit, Composite parent) {
		Composite comp = toolkit.createComposite(parent);
		comp.setLayout(new GridLayout(2, false));
		GridData gd = new GridData(GridData.END);
		comp.setLayoutData(gd);
		
		gd=new GridData();
		gd.widthHint = 120;
		GridDataFactory fac = GridDataFactory.createFrom(gd);
		
		addButton = toolkit.createButton(comp, "Add...", SWT.PUSH);
		fac.applyTo(addButton);
		removeButton = toolkit.createButton(comp, "Remove...", SWT.PUSH);
		fac.applyTo(removeButton);
		
		hookAddButtonListener();
		hookRemoveButtonListener();
		
	}

	private void hookRemoveButtonListener() {
		removeButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				IStructuredSelection sel = (IStructuredSelection) tableViewer
						.getSelection();
				if (sel.isEmpty())
					return;
				MappingElement me = (MappingElement) sel.getFirstElement();
				getCommandStack().execute(
						new RemovePrefixMappingCommand(
								(TopicMapSchema) getModel(), me));

			}
		});
		
	}

	private void hookAddButtonListener() {
		addButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				NewPrefixMappingDialog dlg = new NewPrefixMappingDialog(
						addButton.getShell());
				if (dlg.open() == Dialog.OK) {
					if (PrefixKeyMatcher.isValidKey(dlg.getKey()))
						getCommandStack().execute(
								new CreatePrefixMappingCommand(
										(TopicMapSchema) getModel(), dlg.getKey(),
										dlg.getUri()));
					else
						MessageDialog
								.openError(addButton.getShell(), "invalid key",
										"You've entered an invalid key!");
				}
			}
		});
		
	}

	private void createTableViewer(FormToolkit toolkit, Composite parent) {
		Composite tableComp = toolkit.createComposite(parent);
		tableComp.setLayoutData(new GridData(GridData.FILL_BOTH));
		TableColumnLayout layout = new TableColumnLayout();
		tableComp.setLayout(layout);
		
		Table table = toolkit.createTable(tableComp, SWT.BORDER|SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		
		
		TableColumn tc = new TableColumn(table, 0);
		tc.setText("Prefix");
		layout.setColumnData(tc, new ColumnWeightData(1));
		
		tc = new TableColumn(table, 0);
		tc.setText("URI");
		layout.setColumnData(tc, new ColumnWeightData(1));
		
		tableViewer = new TableViewer(table);
		tableViewer.setColumnProperties(columnNames);
		tableViewer.setCellEditors(new CellEditor[]{new TextCellEditor(table), new TextCellEditor(table)});
		tableViewer.setContentProvider(new ArrayContentProvider());
		tableViewer.setLabelProvider(new TableLabelProvider());
		tableViewer.setCellModifier(new TableCellModifier());
		
	}

	public void notifyChanged(Notification notification) {
		if (notification.getEventType()==Notification.REMOVING_ADAPTER)
			return;
		//if (notification.getNotifier() instanceof  ) {
			updateUI();
		

	}

	private class TableLabelProvider implements ITableLabelProvider {

		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		public String getColumnText(Object element, int columnIndex) {
			MappingElement me = (MappingElement) element;
			switch(columnIndex) {
			case 0: return me.getKey();
			case 1: return me.getValue();
			}
			return null;
		}

		public void addListener(ILabelProviderListener listener) {
		}

		public void dispose() {
		}

		public boolean isLabelProperty(Object element, String property) {
			return false;
		}

		public void removeListener(ILabelProviderListener listener) {
		}		
	}
	
	private class TableCellModifier implements ICellModifier {

		public boolean canModify(Object element, String property) {
			return true;
		}

		public Object getValue(Object element, String property) {
			MappingElement me = (MappingElement) element;
			if (columnNames[0].equals(property)) {
				return me.getKey();
			} else {
				return me.getValue();
			}
		}

		public void modify(Object element, String property, Object value) {
			if (value==null)
				return;
			MappingElement me = (MappingElement) ((TableItem) element).getData();
			String key = me.getKey();
			String val = me.getValue();
			if (columnNames[0].equals(property)) {
				if (key.equals(value))
					return;
				key = (String) value;
			} else {
				if (val.equals(value))
					return;
				val = (String) value;
			}
			if (PrefixKeyMatcher.isValidKey(key))
				getCommandStack().execute(new UpdatePrefixCommand(me, key, val));
			else
				MessageDialog
				.openError(addButton.getShell(), "invalid key",
						"You've entered an invalid key!");
			
		}
		
	}
	
}
