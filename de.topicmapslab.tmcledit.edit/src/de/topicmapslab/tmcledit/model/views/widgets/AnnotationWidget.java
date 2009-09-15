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

import java.util.Iterator;
import java.util.Map.Entry;

import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
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

import de.topicmapslab.tmcledit.model.TMCLConstruct;
import de.topicmapslab.tmcledit.model.commands.CreateAnnotationCommand;
import de.topicmapslab.tmcledit.model.commands.ModifyAnnotationKeyCommand;
import de.topicmapslab.tmcledit.model.commands.ModifyAnnotationValueCommand;
import de.topicmapslab.tmcledit.model.commands.RemoveAnnotationCommand;

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
	private Button editButton;
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
	    GridData gd = new GridData(GridData.FILL_VERTICAL);
	    gd.widthHint = 120;
	    comp.setLayoutData(gd);
	    
	    addButton = createButton(comp, "Add...");
	    editButton = createButton(comp, "Edit...");
	    editButton.setEnabled(false);
	    removeButton = createButton(comp, "Remove");
	    removeButton.setEnabled(false);
		hookButtonListeners();
    }

	private Button createButton(Composite parent, String text) {
		Button b = (toolkit==null) ? new Button(parent, SWT.PUSH) : toolkit.createButton(parent, text, SWT.PUSH);
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
		TableColumn tc = new TableColumn(table, SWT.NONE);
		tc.setText("Annotation");
		layout.setColumnData(tc, new ColumnWeightData(1));

		tc = new TableColumn(table, SWT.NONE);
		tc.setText("Value");
		layout.setColumnData(tc, new ColumnWeightData(1));

		viewer = new TableViewer(table);
		viewer.setColumnProperties(new String[]{"key", "value"});
		viewer.setCellModifier(new AnnotationCellModifier());
		viewer.setLabelProvider(new AnnotationLabelProvider());
		viewer.setContentProvider(new ArrayContentProvider());
		viewer.setCellEditors(new CellEditor[] { new TextCellEditor(table), new TextCellEditor(table) });
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
				
				if (sel.isEmpty()) {
					removeButton.setEnabled(false);
					editButton.setEnabled(false);
				} else {
					removeButton.setEnabled(true);
					editButton.setEnabled(true);
				}
			}
		});
		if (model!=null)
			viewer.setInput(model.getExtension().entrySet());
    }

	
	private void hookButtonListeners() {
		addButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addAnnotation();
			}
		});
		
		editButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				editAnnotation();
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
		System.out.println("Edit");
		viewer.refresh();
	}
	
	@SuppressWarnings("unchecked")
    private void removeAnnotation() {
		IStructuredSelection sel = (IStructuredSelection) viewer.getSelection();
		if (sel.isEmpty())
			return;
		Entry<String, String> e;
		if (sel.size()==1) {
			e = (Entry<String, String>) sel.getFirstElement();
			cmdStack.execute(new RemoveAnnotationCommand(model, e.getKey(), e.getValue()));
		} else {
			CompoundCommand ccmd = new CompoundCommand();
			Iterator it = sel.iterator();
			while (it.hasNext()) {
				e = (Entry<String, String>) it.next();
				ccmd.append(new RemoveAnnotationCommand(model, e.getKey(), e.getValue()));
			}
			cmdStack.execute(ccmd);
		}
		viewer.refresh();
	}
	
	private void addAnnotation() {
		String key;
		do {
			newCounter++;
			key = "key"+newCounter;
		} while (model.getExtension().get(key)!=null);
		
		cmdStack.execute(new CreateAnnotationCommand(model, key, "value"));
		viewer.refresh();
		
	}
	
	public void setModel(Object model) {
		if (this.model != null) {
			this.model.eAdapters().remove(adapter);
		}

		if (!(model instanceof TMCLConstruct)) {
			this.model = null;
			return;
		}
			
		
		this.model = (TMCLConstruct) model;
		if (model == null)
			return;

		this.model.eAdapters().add(adapter);
		updateAnnotationList();
	}

	private void updateAnnotationList() {
		if (viewer != null)
			viewer.setInput(model.getExtension().entrySet());
	}

	private class AnnotationLabelProvider implements ITableLabelProvider {

		public Image getColumnImage(Object element, int columnIndex) {
	        return null;
        }

		@SuppressWarnings("unchecked")
        public String getColumnText(Object element, int columnIndex) {
	        Entry<String, String> e = (Entry<String, String>) element;
			if (columnIndex==0)
	        	return e.getKey();
	        return e.getValue();
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
	
	private class AnnotationCellModifier implements ICellModifier {

		public boolean canModify(Object element, String property) {
	        return true;
        }

		@SuppressWarnings("unchecked")
        public Object getValue(Object element, String property) {
			Entry<String, String> e = (Entry<String, String>) element;
			
			if ("key".equals(property))
	        	return e.getKey();
	        return e.getValue();
        }

		@SuppressWarnings("unchecked")
        public void modify(Object element, String property, Object value) {
			if (value==null)
				return;
			TableItem item = (TableItem) element;
			Entry<String, String> e = (Entry<String, String>) item.getData();
			if ("key".equals(property)) {
	        	cmdStack.execute(new ModifyAnnotationKeyCommand(model, (String) value, e.getKey()));
	        	
			} else {
				cmdStack.execute(new ModifyAnnotationValueCommand(model, e.getKey(), (String) value));
			}
        }
		
	}
	
	private class Adapter extends AdapterImpl {
		@Override
		public void notifyChanged(Notification msg) {
			if (msg.getEventType() == Notification.REMOVING_ADAPTER)
				return;
			
			viewer.refresh();
		}
	}

	public void setCommandStack(CommandStack commandStack) {
	    cmdStack = commandStack;
    }

}
