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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import de.topicmapslab.onotoa.search.util.TopicTypeComparator;
import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;
import de.topicmapslab.tmcledit.model.util.ImageConstants;
import de.topicmapslab.tmcledit.model.util.ImageProvider;

/**
 * @author sip
 * 
 */
public class TopicTypeSearchAdvancedPart implements ISelectionChangedListener {

	private List<TopicType> selectedTopics = new ArrayList<TopicType>();
	private TableViewer availableTopicList, selectedTopicList;

	private Shell dockingShell;
	private Group group;
	private Button addButton, removeButton, clearButton, typeFilterButton;
	private Button filterOccurrence, filterName, filterRole, filterAssociation;
	private Text textFilterAvailable, textFilterSelected;;

	private int filterValue = -1;
	private boolean isTypeFiltered = false;
	private boolean isTextFilteredAvailable = false;
	private boolean isTextFilteredSelected = false;
	private String textFilterSelectedValue, textFilterAvailableValue;

	public TopicTypeSearchAdvancedPart() {

		GridData gridData;
		Button dummyButton;

		dockingShell = new Shell();

		gridData = new GridData(GridData.FILL_BOTH);
		gridData.horizontalSpan = 2;

		group = new Group(dockingShell, SWT.SHADOW_OUT);
		group.setText("Used Topic Types");
		group.setLayout(new GridLayout(3, false));
		group.setLayoutData(gridData);

		gridData = new GridData(GridData.FILL_HORIZONTAL);

		textFilterAvailable = new Text(group, SWT.SINGLE | SWT.BORDER);
		textFilterAvailable.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		textFilterAvailable.setText("filter results");
		textFilterAvailable.addFocusListener(new FocusListener() {

			public void focusLost(FocusEvent e) {
			}

			public void focusGained(FocusEvent e) {
				textFilterAvailable.setText("");
				textFilterAvailable.removeFocusListener(this);
				textFilterAvailable.addKeyListener(new KeyListener() {

					public void keyReleased(KeyEvent e) {
						isTextFilteredAvailable = true;
						textFilterAvailableValue = textFilterAvailable.getText();
						refreshLists();
					}

					public void keyPressed(KeyEvent e) {
						// TODO Auto-generated method stub
					}
				});
			}
		});
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalAlignment = SWT.CENTER;

		dummyButton = new Button(group, SWT.PUSH);
		dummyButton.setLayoutData(gridData);
		dummyButton.setText("=");
		dummyButton.setVisible(false);

		textFilterSelected = new Text(group, SWT.SINGLE | SWT.BORDER);
		textFilterSelected.setText("filter results");
		textFilterSelected.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		textFilterSelected.addFocusListener(new FocusListener() {

			public void focusLost(FocusEvent e) {
			}

			public void focusGained(FocusEvent e) {
				textFilterSelected.setText("");
				textFilterSelected.removeFocusListener(this);
				textFilterSelected.addKeyListener(new KeyListener() {

					public void keyReleased(KeyEvent e) {
						isTextFilteredSelected = true;
						textFilterSelectedValue = textFilterSelected.getText();
						refreshLists();
					}

					public void keyPressed(KeyEvent e) {
						// TODO Auto-generated method stub
					}
				});
			}
		});

		gridData = new GridData(GridData.FILL_BOTH);
		gridData.widthHint = 200;

		availableTopicList = new TableViewer(group, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
		availableTopicList.getTable().setLayoutData(gridData);
		availableTopicList.setLabelProvider(new TopicLableProvider());
		availableTopicList.setContentProvider(new ArrayContentProvider());

		// throws java.lang.IllegalArgumentException: The 'no duplicates'
		// constraint is violated

		// TopicTypeComparator comparator = new TopicTypeComparator();
		// List<TopicType> listAvailable =
		// ModelIndexer.getTopicIndexer().getTopicTypes();
		// Collections.sort(listAvailable, comparator);

		availableTopicList.setInput(ModelIndexer.getTopicIndexer().getTopicTypes());
		availableTopicList.addSelectionChangedListener(this);
		availableTopicList.addDoubleClickListener(new IDoubleClickListener() {

			public void doubleClick(DoubleClickEvent event) {
				addSelection();
			}

		});

		availableTopicList.addFilter(new ViewerFilter() {

			@Override
			public boolean select(Viewer viewer, Object parentElement, Object element) {

				if (((TopicType) element).getKind().getValue() == KindOfTopicType.TOPIC_TYPE_VALUE)
					return false;

				if (selectedTopics.contains(element))
					return false;

				if (isTypeFiltered)
					if (((TopicType) element).getKind().getValue() != filterValue && filterValue != -1)
						return false;

				if (isTextFilteredAvailable)
					if ((!((TopicType) element).getName().startsWith(textFilterAvailableValue)))
						return false;

				return true;

			}

		});

		createButtonBar();

		gridData = new GridData(GridData.FILL_BOTH);
		gridData.widthHint = 200;

		selectedTopicList = new TableViewer(group, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
		selectedTopicList.getTable().setLayoutData(gridData);
		selectedTopicList.setLabelProvider(new TopicLableProvider());
		selectedTopicList.setContentProvider(new ArrayContentProvider());
		selectedTopicList.setInput(selectedTopics);
		selectedTopicList.addSelectionChangedListener(this);

		selectedTopicList.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				removeSelection();
			}
		});
		selectedTopicList.addFilter(new ViewerFilter() {

			@Override
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				if (isTextFilteredSelected)
					if ((!((TopicType) element).getName().startsWith(textFilterSelectedValue)))
						return false;

				return true;
			}
		});

	}

	/**
     * 
     */
	private void createButtonBar() {

		Composite buttonBar;

		buttonBar = new Composite(group, SWT.NONE);
		buttonBar.setLayout(new GridLayout());
		buttonBar.setLayoutData(new GridData(GridData.FILL_VERTICAL));

		clearButton = new Button(buttonBar, SWT.PUSH);
		clearButton.setText("Clear");
		clearButton.setToolTipText("Remove all selected Topics");
		clearButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		clearButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				clearSelection();
			}
		});

		addButton = new Button(buttonBar, SWT.PUSH);
		addButton.setText(">");
		addButton.setToolTipText("Add selected Topics");
		addButton.setEnabled(false);
		addButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		addButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addSelection();
			}
		});

		removeButton = new Button(buttonBar, SWT.PUSH);
		removeButton.setText("<");
		removeButton.setEnabled(false);
		removeButton.setToolTipText("Remove selected Topics");
		removeButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		removeButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				removeSelection();
			}
		});

		typeFilterButton = new Button(buttonBar, SWT.Activate);
		typeFilterButton.setText("Filter >>");
		typeFilterButton.setToolTipText("Enable filter for list of available Topics");
		typeFilterButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		typeFilterButton.setSelection(false);
		hookFilterListener();

		filterOccurrence = new Button(buttonBar, SWT.PUSH);
		filterOccurrence.setToolTipText("Show only Occurrence TYpes");
		filterOccurrence.setImage(ImageProvider.getImage(ImageConstants.OCCURRENCETYPE));
		filterOccurrence.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		filterOccurrence.setVisible(false);
		filterOccurrence.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				filterValue = KindOfTopicType.OCCURRENCE_TYPE_VALUE;
				refreshLists();
			}
		});

		filterName = new Button(buttonBar, SWT.PUSH);
		filterName.setToolTipText("Show only Name Types");
		filterName.setImage(ImageProvider.getImage(ImageConstants.NAMETYPE));
		filterName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		filterName.setVisible(false);
		filterName.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				filterValue = KindOfTopicType.NAME_TYPE_VALUE;
				refreshLists();
			}
		});

		filterRole = new Button(buttonBar, SWT.PUSH);
		filterRole.setToolTipText("Show only Role Types");
		filterRole.setImage(ImageProvider.getImage(ImageConstants.ROLETYPE));
		filterRole.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		filterRole.setVisible(false);
		filterRole.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				filterValue = KindOfTopicType.ROLE_TYPE_VALUE;
				refreshLists();
			}
		});

		filterAssociation = new Button(buttonBar, SWT.PUSH);
		filterAssociation.setToolTipText("Show only Association Types");
		filterAssociation.setImage(ImageProvider.getImage(ImageConstants.ASSOCIATIONTYPE));
		filterAssociation.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		filterAssociation.setVisible(false);
		filterAssociation.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				filterValue = KindOfTopicType.ASSOCIATION_TYPE_VALUE;
				refreshLists();
			}
		});
	}

	private class TopicLableProvider extends LabelProvider {

		@Override
		public Image getImage(Object element) {

			if (element instanceof TopicType)
				return ImageProvider.getTopicTypeImage((TopicType) element);

			return super.getImage(element);

		}

		@Override
		public String getText(Object element) {

			if (element instanceof TopicType)
				return ((TopicType) element).getName();

			return super.getText(element);

		}

	}

	private void clearSelection() {

		textFilterAvailable.setText("");
		textFilterSelected.setText("");
		textFilterAvailableValue = "";
		textFilterSelectedValue = "";
		selectedTopics.clear();
		availableTopicList.refresh();
		selectedTopicList.refresh();

	}

	private void addSelection() {

		IStructuredSelection sel = (IStructuredSelection) availableTopicList.getSelection();

		for (Iterator it = sel.iterator(); it.hasNext();) {
			selectedTopics.add((TopicType) it.next());
		}
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

	private void hookFilterListener() {
		typeFilterButton.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {

				if (typeFilterButton.getText().equals("Filter >>")) {

					filterOccurrence.setVisible(true);
					filterName.setVisible(true);
					filterRole.setVisible(true);
					filterAssociation.setVisible(true);

					isTypeFiltered = true;
					typeFilterButton.setText("Filter <<");

				} else {

					filterOccurrence.setVisible(false);
					filterName.setVisible(false);
					filterRole.setVisible(false);
					filterAssociation.setVisible(false);

					isTypeFiltered = false;
					filterValue = -1;
					refreshLists();
					typeFilterButton.setText("Filter >>");

				}
			}

		});

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

	private void refreshLists() {

		selectedTopicList.refresh();
		availableTopicList.refresh();

	}

	public List<TopicType> getSelectedList() {

		return (List<TopicType>) selectedTopicList.getInput();

	}

	public Group getAdvancedTopicComposite() {
		return this.group;
	}

	public Group getAdvancedAssociationComposite() {

		typeFilterButton.setVisible(false);
		isTypeFiltered = true;
		filterValue = KindOfTopicType.ROLE_TYPE_VALUE;
		refreshLists();

		return this.group;
	}
}
