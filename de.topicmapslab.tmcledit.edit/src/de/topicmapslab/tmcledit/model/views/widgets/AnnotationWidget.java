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
package de.topicmapslab.tmcledit.model.views.widgets;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.ViewerCell;
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
import org.eclipse.ui.fieldassist.ContentAssistCommandAdapter;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.topicmapslab.tmcledit.model.Annotation;
import de.topicmapslab.tmcledit.model.TMCLConstruct;
import de.topicmapslab.tmcledit.model.TmcleditEditPlugin;
import de.topicmapslab.tmcledit.model.annotationprovider.IAnnotationProposalProvider;
import de.topicmapslab.tmcledit.model.annotationprovider.IAnnotationValidator;
import de.topicmapslab.tmcledit.model.annotationprovider.internal.AnnotationKeyProvider;
import de.topicmapslab.tmcledit.model.annotationprovider.internal.AnnotationValueProvider;
import de.topicmapslab.tmcledit.model.commands.CreateAnnotationCommand;
import de.topicmapslab.tmcledit.model.commands.ModifyAnnotationKeyCommand;
import de.topicmapslab.tmcledit.model.commands.ModifyAnnotationValueCommand;
import de.topicmapslab.tmcledit.model.commands.RemoveAnnotationCommand;
import de.topicmapslab.tmcledit.model.util.ImageConstants;
import de.topicmapslab.tmcledit.model.util.ImageProvider;
import de.topicmapslab.tmcledit.model.util.extension.AnnotationProviderInfo;

/**
 * @author Hannes Niederhausen
 * 
 */
public class AnnotationWidget extends Composite {

	private TMCLConstruct model;
	private TableViewer viewer;
	private Adapter adapter;

	private CommandStack cmdStack;

	private FormToolkit toolkit;
	private Button addButton;
	private Button removeButton;
	private int newCounter;

	public AnnotationWidget(Composite parent, int style, FormToolkit toolkit) {
		this(parent, style);
		this.toolkit = toolkit;
	}

	public AnnotationWidget(Composite parent, int style) {
		super(parent, style);
		adapter = new Adapter();
		createControl();
	}

	private void createControl() {
		setLayout(new GridLayout(2, false));

		createTable();

		createButtonBar();
	}

	private void createButtonBar() {
		Composite comp = (toolkit == null) ? new Composite(this, SWT.NONE) : toolkit.createComposite(this);
		comp.setLayout(new GridLayout());
		comp.setLayoutData(new GridData(GridData.FILL_VERTICAL));

		addButton = createButton(comp, "");
		addButton.setImage(ImageProvider.getImage(ImageConstants.NEW));
		addButton.setToolTipText("Create new Annotation");

		removeButton = createButton(comp, "");
		removeButton.setImage(ImageProvider.getImage(ImageConstants.REMOVE));
		removeButton.setToolTipText("Remove selected Annotation");
		removeButton.setEnabled(false);

		hookButtonListeners();
	}

	private Button createButton(Composite parent, String text) {
		Button b = (toolkit == null) ? new Button(parent, SWT.PUSH) : toolkit.createButton(parent, text, SWT.PUSH);
		b.setText(text);
		b.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		return b;
	}

	private void createTable() {
		Composite comp = (toolkit == null) ? new Composite(this, SWT.NONE) : toolkit.createComposite(this);
		TableColumnLayout layout = new TableColumnLayout();
		comp.setLayout(layout);
		comp.setLayoutData(new GridData(GridData.FILL_BOTH));

		Table table = null;
		if (toolkit != null) {
			table = toolkit.createTable(comp, SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION);
		} else {
			table = new Table(comp, SWT.BORDER);
		}
		table.setHeaderVisible(true);

		viewer = new TableViewer(table);
		viewer.setColumnProperties(new String[] { "key", "value" });
		viewer.setLabelProvider(new AnnotationLabelProvider());
		viewer.setContentProvider(new ArrayContentProvider());
		// viewer.setCellEditors(new CellEditor[] { new TextCellEditor(table),
		// new TextCellEditor(table) });
		viewer.addDoubleClickListener(new IDoubleClickListener() {

			public void doubleClick(DoubleClickEvent event) {
				editAnnotation();
			}
		});
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {

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

		TableViewerColumn tvc = new TableViewerColumn(viewer, SWT.NONE);
		tvc.setLabelProvider(new KeyLabelProvider());
		tvc.setEditingSupport(new KeyEditingSupport(viewer));
		TableColumn tc = tvc.getColumn();
		tc.setText("Annotation");
		layout.setColumnData(tc, new ColumnWeightData(1));

		tvc = new TableViewerColumn(viewer, SWT.NONE);
		tvc.setLabelProvider(new ValueLabelProvider());
		tvc.setEditingSupport(new ValueEditingSupport(viewer));
		tc = tvc.getColumn();
		tc.setText("Value");
		layout.setColumnData(tc, new ColumnWeightData(1));

		if (model != null)
			viewer.setInput(model.getAnnotations());
	}

	private void hookButtonListeners() {
		addButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addAnnotation();
			}
		});

		removeButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				removeAnnotation();
			}
		});
	}

	private void editAnnotation() {
		viewer.refresh();
	}

	private void removeAnnotation() {
		IStructuredSelection sel = (IStructuredSelection) viewer.getSelection();
		if (sel.isEmpty())
			return;
		Annotation a;
		if (sel.size() == 1) {
			a = (Annotation) sel.getFirstElement();
			cmdStack.execute(new RemoveAnnotationCommand(model, a));
		} else {
			CompoundCommand ccmd = new CompoundCommand();
			Iterator it = sel.iterator();
			while (it.hasNext()) {
				a = (Annotation) it.next();
				ccmd.append(new RemoveAnnotationCommand(model, a));
			}
			cmdStack.execute(ccmd);
		}
		viewer.refresh();
	}

	private void addAnnotation() {
		String key;
		do {
			newCounter++;
			key = "key" + newCounter;
		} while (findAnnotation(key) != null);

		cmdStack.execute(new CreateAnnotationCommand(model, key, "value"));
		viewer.refresh();

	}

	private Annotation findAnnotation(String key) {
		for (Annotation a : model.getAnnotations()) {
			if (a.getKey().equals(key))
				return a;
		}
		return null;
	}

	public void setModel(Object model) {
		if (this.model != null) {
			this.model.eAdapters().remove(adapter);
			for (Annotation a : this.model.getAnnotations()) {
				a.eAdapters().remove(adapter);
			}
		}

		if (!(model instanceof TMCLConstruct)) {
			this.model = null;
			return;
		}

		this.model = (TMCLConstruct) model;
		if (this.model == null)
			return;

		this.model.eAdapters().add(adapter);
		for (Annotation a : this.model.getAnnotations()) {
			a.eAdapters().add(adapter);
		}
		updateAnnotationList();
	}

	private void updateAnnotationList() {
		if (viewer != null)
			viewer.setInput(model.getAnnotations());
	}

	private class AnnotationLabelProvider implements ITableLabelProvider {

		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		public String getColumnText(Object element, int columnIndex) {
			Annotation a = (Annotation) element;
			if (columnIndex == 0)
				return a.getKey();
			return a.getValue();
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

	private class KeyEditingSupport extends EditingSupport {

		private TextCellEditor ed;

		public KeyEditingSupport(ColumnViewer viewer) {
			super(viewer);
		}

		@Override
		protected boolean canEdit(Object element) {
			return true;
		}

		@Override
		protected CellEditor getCellEditor(Object element) {
			if (ed == null) {
				ed = new TextCellEditor((Composite) getViewer().getControl());

				new ContentAssistCommandAdapter(ed.getControl(), new TextContentAdapter(), new AnnotationKeyProvider(),
				        null, AnnotationKeyProvider.KEYS);
			}
			return ed;
		}

		@Override
		protected Object getValue(Object element) {
			return cast(element).getKey();
		}

		@Override
		protected void setValue(Object element, Object value) {
			Annotation a = cast(element);
			cmdStack.execute(new ModifyAnnotationKeyCommand(a, (String) value));
		}

		private Annotation cast(Object e) {
			return (Annotation) e;
		}

	}

	private class KeyLabelProvider extends CellLabelProvider {

		@Override
		public void update(ViewerCell cell) {
			Annotation a = (Annotation) cell.getElement();
			cell.setText(a.getKey());
		}

	}

	private class ValueEditingSupport extends EditingSupport {

		private TextCellEditor textEditor;
		private CheckboxCellEditor booleanEditor;
		private ComboBoxCellEditor comboBoxCellEditor;

		private IAnnotationValidator currValidator;
		private AnnotationValueProvider proposalProvider;

		private boolean comboUsed = false;

		public ValueEditingSupport(ColumnViewer viewer) {
			super(viewer);
		}

		@Override
		protected boolean canEdit(Object element) {
			return true;
		}

		@Override
		protected CellEditor getCellEditor(Object element) {
			comboUsed = false;
			AnnotationProviderInfo info = getInfo(element);
			if (info != null) {
				Class<?> type = getType(element);
				if (type == Boolean.class) {
					currValidator = info.getValidator();
					return getBooleanEditor();
				}

				IAnnotationProposalProvider proposalProvider = info.getPorposalProvider();
				if (proposalProvider != null) {
					if (!proposalProvider.newValuesAllowed()) {
						getComboBoxCellEditor().setItems(proposalsToArray(proposalProvider));
						comboUsed = true;
						return getComboBoxCellEditor();
					}
				} else {
					getTextEditor();
					if (info != null) {
						currValidator = info.getValidator();
						this.proposalProvider.setProvider(proposalProvider);
					}
				}
			} else {
				currValidator = null;
				if (proposalProvider != null)
					proposalProvider.setProvider(null);
			}
			return getTextEditor();
		}

		private String[] proposalsToArray(IAnnotationProposalProvider proposalProvider) {
			List<String> list = new ArrayList<String>();

			for (String s : proposalProvider.getProposals()) {
				list.add(s);
			}

			return list.toArray(new String[list.size()]);
		}

		private Class<?> getType(Object element) {
			AnnotationProviderInfo info = getInfo(element);
			if (info == null)
				return String.class;
			return info.getValidator().getType();
		}

		private AnnotationProviderInfo getInfo(Object element) {
			String key = cast(element).getKey();

			AnnotationProviderInfo info = TmcleditEditPlugin.getPlugin().getAnnotionProviderInfo(key);
			return info;
		}

		public CheckboxCellEditor getBooleanEditor() {
			if (booleanEditor == null)
				booleanEditor = new CheckboxCellEditor((Composite) getViewer().getControl());
			return booleanEditor;
		}

		public ComboBoxCellEditor getComboBoxCellEditor() {
			if (comboBoxCellEditor == null)
				comboBoxCellEditor = new ComboBoxCellEditor((Composite) getViewer().getControl(), new String[] {});
			return comboBoxCellEditor;
		}

		private TextCellEditor getTextEditor() {
			if (textEditor == null) {
				textEditor = new TextCellEditor((Composite) getViewer().getControl());
				proposalProvider = new AnnotationValueProvider();
				new ContentAssistCommandAdapter(textEditor.getControl(), new TextContentAdapter(), proposalProvider,
				        null, AnnotationValueProvider.KEYS);
			}
			return textEditor;
		}

		@Override
		protected Object getValue(Object element) {
			String value = cast(element).getValue();
			if (getType(element) == Boolean.class)
				return Boolean.parseBoolean(value);

			if (comboUsed) {
				int i = 0;
				for (String s : getComboBoxCellEditor().getItems()) {
					if (value.equals(s))
						return new Integer(i);
					i++;
				}
				return new Integer(0);

			}

			return value;
		}

		@Override
		protected void setValue(Object element, Object value) {
			Annotation a = cast(element);

			if (value instanceof Boolean)
				value = ((Boolean) value).toString();

			if (comboUsed) {
				int index = ((Integer) value).intValue();
				value = getComboBoxCellEditor().getItems()[index];
			}

			if ((currValidator != null) && (!currValidator.isValid((String) value))) {
				return;
			}

			cmdStack.execute(new ModifyAnnotationValueCommand(a, (String) value));
		}

		private Annotation cast(Object e) {
			return (Annotation) e;
		}

	}

	private class ValueLabelProvider extends CellLabelProvider {

		@Override
		public void update(ViewerCell cell) {
			Annotation a = (Annotation) cell.getElement();
			cell.setText(a.getValue());
		}

	}

	private class Adapter extends AdapterImpl {
		@Override
		public void notifyChanged(Notification msg) {
			if (msg.getEventType() == Notification.REMOVING_ADAPTER)
				return;

			if (msg.getNotifier().equals(model)) {
				if (msg.getEventType() == Notification.ADD) {
					if (msg.getNewValue() instanceof EObject) {
						EObject obj = (EObject) msg.getNewValue();
						if (obj != null)
							obj.eAdapters().add(adapter);
					}
				}
				if (msg.getEventType() == Notification.REMOVE) {
					if (msg.getOldValue() instanceof EObject) {
						EObject obj = (EObject) msg.getOldValue();
						if (obj != null)
							obj.eAdapters().add(adapter);
					}
				}
			}

			if (msg.getNotifier() instanceof Annotation) {
				viewer.refresh(msg.getNotifier());
				return;
			}

			viewer.refresh();
		}
	}

	public void setCommandStack(CommandStack commandStack) {
		cmdStack = commandStack;
	}

}
