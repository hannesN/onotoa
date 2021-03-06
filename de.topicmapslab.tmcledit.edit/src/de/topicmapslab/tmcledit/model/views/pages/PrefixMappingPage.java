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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
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
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.topicmapslab.tmcledit.model.MappingElement;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.commands.CreatePrefixMappingCommand;
import de.topicmapslab.tmcledit.model.commands.RemovePrefixMappingCommand;
import de.topicmapslab.tmcledit.model.commands.ModifyPrefixCommand;
import de.topicmapslab.tmcledit.model.dialogs.NewPrefixMappingDialog;
import de.topicmapslab.tmcledit.model.util.ImageConstants;
import de.topicmapslab.tmcledit.model.util.ImageProvider;
import de.topicmapslab.tmcledit.model.util.PrefixKeyMatcher;

/**
 * Property page for the prefix mappings
 * 
 * @author Hannes Niederhausen
 * 
 */
public class PrefixMappingPage extends AbstractEMFModelPage {

	private final String[] columnNames = { "key", "value" };

	private TableViewer tableViewer;

	private Button addButton;

	private Button removeButton;

	private CTabItem item;

	private TopicMapSchema schema;

	/**
	 * Constructor
	 */
	public PrefixMappingPage() {
		super("prefix mapping");
	}

	@Override
	public void setModel(Object model) {
		schema = null;
		if (model instanceof MappingElement) {
			MappingElement me = (MappingElement) model;
			schema = (TopicMapSchema) me.eContainer();
		} else if (model instanceof EObjectContainmentEList) {
			// this must be the list of mapping elements
			schema = (TopicMapSchema) ((EObjectContainmentEList) model).getEObject();
		}
		super.setModel(schema);

		schema.eAdapters().add(this);
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
		if (tableViewer == null)
			return;

		TopicMapSchema schema = (TopicMapSchema) getModel();
		tableViewer.setInput(schema.getMappings());

	}

	/**
	 * Creates the page using the given parent
	 * @param parent the parent for the page widget
	 * @return the widget of the page
	 */
	public Composite createPage(Composite parent) {
		FormToolkit toolkit = new FormToolkit(parent.getDisplay());

		Composite comp = toolkit.createComposite(parent);
		comp.setLayout(new GridLayout(2, false));

		createTableViewer(toolkit, comp);
		createButtonComposite(toolkit, comp);
		return comp;
	}

	@Override
	protected boolean hasDocumentation() {
		return false;
	}

	@Override
	protected void setEnabled(boolean enabled) {
		item.getControl().setEnabled(enabled);
	}

	@Override
	protected void createItems(CTabFolder folder) {
		super.createItems(folder);
		item = new CTabItem(folder, SWT.None);
		item.setText("Prefixes");
		item.setControl(createPage(folder));
	}

	private void createButtonComposite(FormToolkit toolkit, Composite parent) {
		Composite comp = toolkit.createComposite(parent);
		comp.setLayout(new GridLayout());
		comp.setLayoutData(new GridData(GridData.END));

		GridDataFactory fac = GridDataFactory.createFrom(new GridData(SWT.VERTICAL));

		addButton = toolkit.createButton(comp, "", SWT.PUSH);
		addButton.setImage(ImageProvider.getImage(ImageConstants.NEW));
		addButton.setToolTipText("Create new Prefix");
		fac.applyTo(addButton);
		removeButton = toolkit.createButton(comp, "", SWT.PUSH);
		removeButton.setImage(ImageProvider.getImage(ImageConstants.REMOVE));
		removeButton.setToolTipText("Remove selected Prefix");
		removeButton.setEnabled(false);
		fac.applyTo(removeButton);

		hookAddButtonListener();
		hookRemoveButtonListener();

	}

	private void hookRemoveButtonListener() {
		removeButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				IStructuredSelection sel = (IStructuredSelection) tableViewer.getSelection();
				if (sel.isEmpty())
					return;
				MappingElement me = (MappingElement) sel.getFirstElement();
				getCommandStack().execute(new RemovePrefixMappingCommand((TopicMapSchema) getModel(), me));

			}
		});

	}

	private void hookAddButtonListener() {
		addButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Shell shell = addButton.getShell();
				NewPrefixMappingDialog dlg = new NewPrefixMappingDialog(shell);
				if (dlg.open() == Dialog.OK) {
					for (MappingElement me : schema.getMappings()) {
						if (me.getKey().equals(dlg.getKey())) {
							MessageDialog.openError(shell, "Invalid Key", "A prefix with that key already exists.");
							return;
						}
					}
					if (PrefixKeyMatcher.isValidKey(dlg.getKey()))
						getCommandStack()
						        .execute(
						                new CreatePrefixMappingCommand((TopicMapSchema) getModel(), dlg.getKey(), dlg
						                        .getUri()));
					else
						MessageDialog.openError(shell, "invalid key", "You've entered an invalid key!");
				}
			}
		});

	}

	private void createTableViewer(FormToolkit toolkit, Composite parent) {
		Composite tableComp = toolkit.createComposite(parent);
		tableComp.setLayoutData(new GridData(GridData.FILL_BOTH));
		TableColumnLayout layout = new TableColumnLayout();
		tableComp.setLayout(layout);

		Table table = toolkit.createTable(tableComp, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);

		TableColumn tc = new TableColumn(table, 0);
		tc.setText("Prefix");
		layout.setColumnData(tc, new ColumnWeightData(1));

		tc = new TableColumn(table, 0);
		tc.setText("URI");
		layout.setColumnData(tc, new ColumnWeightData(1));

		tableViewer = new TableViewer(table);
		tableViewer.setColumnProperties(columnNames);
		tableViewer.setCellEditors(new CellEditor[] { new TextCellEditor(table), new TextCellEditor(table) });
		tableViewer.setContentProvider(new ArrayContentProvider());
		tableViewer.setLabelProvider(new TableLabelProvider());
		tableViewer.setCellModifier(new TableCellModifier());

		tableViewer.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				if (!(event.getSelection() instanceof IStructuredSelection))
					return;
				IStructuredSelection sel = (IStructuredSelection) event.getSelection();

				if (sel.isEmpty())
					removeButton.setEnabled(false);
				else
					removeButton.setEnabled(true);

			}
		});

	}

	public void notifyChanged(Notification notification) {
		if (notification.getEventType() == Notification.REMOVING_ADAPTER)
			return;

		if (notification.getEventType() == Notification.ADD) {
			if (notification.getNewValue() instanceof MappingElement) {
				EObject obj = (EObject) notification.getNewValue();
				obj.eAdapters().add(this);
			}
		} else if (notification.getEventType() == Notification.REMOVE) {
			if (notification.getOldValue() instanceof MappingElement) {
				EObject obj = (EObject) notification.getOldValue();
				obj.eAdapters().remove(this);
			}
		}

		updateUI();
	}

	private class TableLabelProvider implements ITableLabelProvider {

		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		public String getColumnText(Object element, int columnIndex) {
			MappingElement me = (MappingElement) element;
			switch (columnIndex) {
			case 0:
				return me.getKey();
			case 1:
				return me.getValue();
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
			if (value == null)
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
			if (PrefixKeyMatcher.isValidKey(key)) {
				getCommandStack().execute(new ModifyPrefixCommand(me, key, val));
			} else {
				MessageDialog.openError(addButton.getShell(), "invalid key", "You've entered an invalid key!");
			}

		}

	}

}
