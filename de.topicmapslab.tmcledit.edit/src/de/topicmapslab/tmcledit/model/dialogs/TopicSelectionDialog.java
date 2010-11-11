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
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;
import de.topicmapslab.tmcledit.model.util.ImageConstants;
import de.topicmapslab.tmcledit.model.util.ImageProvider;

/**
 * @author Hannes Niederhausen
 * 
 */
public class TopicSelectionDialog extends Dialog implements ISelectionChangedListener {

	private List<TopicType> selectedTopics = Collections.emptyList();

	private ListViewer availableTopicList;
	private ListViewer selectedTopicList;

	private TopicType workingTopicType;

	private KindOfTopicType kind;

	private Button addButton;

	private Button removeButton;

	private String title;
	
	public TopicSelectionDialog(Shell parentShell, TopicType workingTopicType) {
		this(parentShell, workingTopicType, KindOfTopicType.TOPIC_TYPE);
	}

	public TopicSelectionDialog(Shell parentShell, TopicType workingTopicType, KindOfTopicType kind) {
		super(parentShell);
		this.workingTopicType = workingTopicType;
		this.kind = kind;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite comp = new Composite(parent, SWT.NONE);
		comp.setLayout(new GridLayout(3, false));
		comp.setLayoutData(new GridData(GridData.FILL_BOTH));

		availableTopicList = new ListViewer(comp);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.widthHint = 150;
		availableTopicList.getList().setLayoutData(gd);
		availableTopicList.setContentProvider(new ArrayContentProvider());
		availableTopicList.setLabelProvider(new TopicLableProvider());
		availableTopicList.setInput(ModelIndexer.getTopicIndexer().getTopicTypes());
		availableTopicList.addDoubleClickListener(new IDoubleClickListener() {
			
			public void doubleClick(DoubleClickEvent event) {
				addSelection();
			}
		});
		availableTopicList.addFilter(new ViewerFilter() {

			@Override
			public boolean select(Viewer viewer, Object parentElement, Object element) {

				if (kind != KindOfTopicType.TOPIC_TYPE) {
					if (((TopicType) element).getKind() != kind)
						return false;
				}

				if (selectedTopics.contains(element))
					return false;
				if (element.equals(workingTopicType))
					return false;
				return true;
			}

		});

		availableTopicList.addSelectionChangedListener(this);
		createButtonRow(comp);

		selectedTopicList = new ListViewer(comp);
		gd = new GridData(GridData.FILL_BOTH);
		gd.widthHint = 150;
		selectedTopicList.getList().setLayoutData(gd);
		selectedTopicList.setContentProvider(new ArrayContentProvider());
		selectedTopicList.setLabelProvider(new TopicLableProvider());
		selectedTopicList.setInput(selectedTopics);
		selectedTopicList.addSelectionChangedListener(this);
		selectedTopicList.addDoubleClickListener(new IDoubleClickListener() {
			
			public void doubleClick(DoubleClickEvent event) {
				removeSelection();
			}
		});

		return comp;
	}

	private void createButtonRow(Composite parent) {
		Composite comp = new Composite(parent, SWT.NONE);
		comp.setLayout(new GridLayout());
		comp.setLayoutData(new GridData(GridData.FILL_VERTICAL));
		GridData gd = new GridData();
		//gd.widthHint = 100;
		GridDataFactory fac = GridDataFactory.createFrom(gd);

		addButton = new Button(comp, SWT.PUSH);
		addButton.setText("");
		addButton.setImage(ImageProvider.getImage(ImageConstants.ADD_ONE));
		addButton.setToolTipText("Add selected Topics");
		fac.applyTo(addButton);
		addButton.setEnabled(false);
		addButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addSelection();
			}
		});

		Button addAllButton = new Button(comp, SWT.PUSH);
		addAllButton.setText("");
		addAllButton.setImage(ImageProvider.getImage(ImageConstants.ADD_ALL));
		addAllButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addAll();
			}
		});
		addAllButton.setToolTipText("Add all Topics");
		
		fac.applyTo(addAllButton);

		removeButton = new Button(comp, SWT.PUSH);
		removeButton.setText("");
		removeButton.setImage(ImageProvider.getImage(ImageConstants.REMOVE_ONE));
		removeButton.setEnabled(false);
		removeButton.setToolTipText("Remove selected Topics");
		fac.applyTo(removeButton);
		removeButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				removeSelection();
			}
		});

		Button removeAllButton = new Button(comp, SWT.PUSH);
		removeAllButton.setText("");
		removeAllButton.setImage(ImageProvider.getImage(ImageConstants.REMOVE_ALL));
		removeAllButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				removeAll();
			}
		});
		removeAllButton.setToolTipText("Remove all Topics");
		fac.applyTo(removeAllButton);

		Button createButton = new Button(comp, SWT.PUSH);
		createButton.setToolTipText("Create new Topic");
		createButton.setText("");
		createButton.setImage(ImageProvider.getImage(ImageConstants.NEW_TOPICTYPE));
		createButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				NewTopicTypeWizard wizard = new NewTopicTypeWizard(KindOfTopicType.TOPIC_TYPE);
				WizardDialog dlg = new WizardDialog(availableTopicList.getList().getShell(), wizard);

				if (dlg.open() == Dialog.OK) {
					TopicType tt = wizard.getNewTopicType();
					ModelIndexer.getInstance().getTopicMapSchema().getTopicTypes().add(tt);
					selectedTopics.add(tt);
					selectedTopicList.refresh();
				}
			}
		});
		fac.applyTo(createButton);
	}

	private void addSelection() {
		IStructuredSelection sel = (IStructuredSelection) availableTopicList.getSelection();
		for (Iterator it = sel.iterator(); it.hasNext();) {
			selectedTopics.add((TopicType) it.next());
		}
		availableTopicList.refresh();
		selectedTopicList.refresh();
	}

	@SuppressWarnings("unchecked")
    private void addAll() {
		selectedTopics.addAll((Collection<? extends TopicType>) availableTopicList.getInput());		
		
		availableTopicList.refresh();
		selectedTopicList.refresh();
	}
	
	private void removeSelection() {
		IStructuredSelection sel = (IStructuredSelection) selectedTopicList.getSelection();
		for (Iterator it = sel.iterator(); it.hasNext();) {
			selectedTopics.remove((TopicType) it.next());
		}
		availableTopicList.refresh();
		selectedTopicList.refresh();
	}
	
	private void removeAll() {
		selectedTopics.clear();		
		
		availableTopicList.refresh();
		selectedTopicList.refresh();
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setSize(500, 400);
		newShell.setText(getTitle());
	}

	public List<TopicType> getSelectedTopics() {
		return selectedTopics;
	}

	public void setSelectedTopics(List<TopicType> selectedTopics) {
		this.selectedTopics = new ArrayList<TopicType>();
		this.selectedTopics.addAll(selectedTopics);
	}

	private class TopicLableProvider extends LabelProvider {
		@Override
		public String getText(Object element) {
			if (element instanceof TopicType)
				return ((TopicType) element).getName();

			return super.getText(element);
		}
	}

	public String getTitle() {
	    return title==null ? "" : title;
    }
	
	public void setTitle(String title) {
	    this.title = title;
    }
	
	public void selectionChanged(SelectionChangedEvent event) {
	    if (event.getSelectionProvider().equals(availableTopicList)) {
	    	if (event.getSelection().isEmpty()) {
	    		addButton.setEnabled(false);
	    	} else {
	    		addButton.setEnabled(true);
	    	}
	    } else if (event.getSelectionProvider().equals(selectedTopicList)) {
	    	if (event.getSelection().isEmpty()) {
	    		removeButton.setEnabled(false);
	    	} else {
	    		removeButton.setEnabled(true);
	    	}
	    }
	    
    }
}
