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
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableColorProvider;
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
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;

import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.util.ImageConstants;
import de.topicmapslab.tmcledit.model.util.ImageProvider;

/**
 * @author sip
 * 
 */
public class CleanSchemaComposite implements ISelectionChangedListener {

	private Composite comp, buttonBar;
	private TableViewer unusedTopicsTable;

	private Group group;
	private Button typeFilterButton, deleteButton, restoreButton, clearButton;
	private Button filterTopicType, filterOccurrence, filterName, filterRole, filterAssociation;
	private Text textFilter;

	private int filterValue = -1;
	private boolean isTypeFiltered = false;
	private boolean isTextFiltered = false;
	private String textFilterValue;

	private Set<TopicType> selectedSet = new HashSet<TopicType>();

	/**
	 * Constructor
	 * 
	 * @param parent
	 */

	public CleanSchemaComposite(Composite parent, List<TopicType> unusedTopicsList) {

		GridData gridData;
		Button dummyButton;

		comp = new Composite(parent, SWT.NONE);
		comp.setLayoutData(new GridData(GridData.FILL_BOTH));
		comp.setLayout(new GridLayout());

		// gridData = new GridData(GridData.FILL_BOTH);
		// gridData.horizontalSpan = 2;

		// Group that contains all elements
		group = new Group(comp, SWT.SHADOW_OUT);
		group.setText("These Topic Types are never used");
		group.setLayout(new GridLayout(2, false));
		group.setLayoutData(new GridData(GridData.FILL_BOTH));

		gridData = new GridData(GridData.FILL_HORIZONTAL);

		// typing sensitive filter for available TopicTypes
		textFilter = new Text(group, SWT.SINGLE | SWT.BORDER);
		textFilter.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		textFilter.setText("filter results");
		textFilter.addFocusListener(new FocusListener() {

			public void focusLost(FocusEvent e) {
				// not in use
			}

			// delete help message at the textBox after focus gained (par
			// example by mouse click)
			public void focusGained(FocusEvent e) {
				textFilter.setText("");

				// important: remove focusListener. Otherwise every focus clear
				// the filter
				textFilter.removeFocusListener(this);
				textFilter.addKeyListener(new KeyListener() {

					// listens to done key strokes an refreshes list
					public void keyReleased(KeyEvent e) {
						isTextFiltered = true;
						textFilterValue = textFilter.getText();
						unusedTopicsTable.refresh();
					}

					public void keyPressed(KeyEvent e) {
						// not in use
					}
				});
			}
		});

		// gridData = new GridData(GridData.FILL_HORIZONTAL);
		// gridData.horizontalAlignment = SWT.CENTER;

		// dummy button for layout purposes
		dummyButton = new Button(group, SWT.PUSH);
		dummyButton.setLayoutData(new GridData());
		dummyButton.setText("=");
		dummyButton.setVisible(false);

		gridData = new GridData(GridData.FILL_BOTH);
		// gridData.widthHint = 200;

		// left table with unused TopicTypes from the schema
		unusedTopicsTable = new TableViewer(group, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
		unusedTopicsTable.getTable().setLayoutData(gridData);
		unusedTopicsTable.setLabelProvider(new TopicLableProvider());
		unusedTopicsTable.setContentProvider(new ArrayContentProvider());

		// set input and add listeners
		unusedTopicsTable.setInput(unusedTopicsList);
		unusedTopicsTable.addSelectionChangedListener(this);
		unusedTopicsTable.addDoubleClickListener(new IDoubleClickListener() {

			// double click deletes selected type
			public void doubleClick(DoubleClickEvent event) {
				chooseAction();
			}

		});

		// filter displayed TopicTypes
		unusedTopicsTable.addFilter(new ViewerFilter() {

			@Override
			public boolean select(Viewer viewer, Object parentElement, Object element) {

				// filter by type
				if (isTypeFiltered)
					if (((TopicType) element).getKind().getValue() != filterValue && filterValue != -1)
						return false;
				// filter by name
				if (isTextFiltered)
					if ((!((TopicType) element).getName().startsWith(textFilterValue)))
						return false;

				return true;

			}

		});

		// creates button bar at the right hand side
		createButtonBar();

	}

	/**
	 * Creation of the button bar that is responsible for most user interaction.
	 * Including delete, restore and filter button.
	 */

	private void createButtonBar() {

		buttonBar = new Composite(group, SWT.NONE);
		buttonBar.setLayout(new GridLayout());
		buttonBar.setLayoutData(new GridData(GridData.FILL_VERTICAL));

		// delete button to delete selected TopicType(s)
		deleteButton = new Button(buttonBar, SWT.PUSH);
		deleteButton.setText("Delete");
		deleteButton.setToolTipText("Mark selected Topic for deletion");
		deleteButton.setEnabled(false);
		deleteButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		deleteButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				deleteSelection();
			}
		});

		// delete all unused TopicTypes
		restoreButton = new Button(buttonBar, SWT.PUSH);
		restoreButton.setText("Restore");
		restoreButton.setToolTipText("Restore selected Topic");
		restoreButton.setEnabled(false);
		restoreButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		restoreButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				restoreSelection();
			}
		});

		// reset button to take all choices back
		clearButton = new Button(buttonBar, SWT.PUSH);
		clearButton.setText("Clear");
		clearButton.setToolTipText("Clear all desicions");
		clearButton.setEnabled(true);
		clearButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		clearButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				clearSelection();
			}
		});

		// button activates filtering available topics by their types
		typeFilterButton = new Button(buttonBar, SWT.Activate);
		typeFilterButton.setText("Filter >>");
		typeFilterButton.setToolTipText("Enable filter for list of available Topics");
		typeFilterButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		typeFilterButton.setSelection(false);
		hookFilterListener();

		// topicType filter
		filterTopicType = new Button(buttonBar, SWT.PUSH);
		filterTopicType.setToolTipText("Show only Topic Types");
		filterTopicType.setImage(ImageProvider.getImage(ImageConstants.TOPICTYPE));
		filterTopicType.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		filterTopicType.setVisible(false);
		filterTopicType.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				// set global filterValue
				filterValue = KindOfTopicType.TOPIC_TYPE_VALUE;
				unusedTopicsTable.refresh();
			}
		});

		// occurrenceType filter
		filterOccurrence = new Button(buttonBar, SWT.PUSH);
		filterOccurrence.setToolTipText("Show only Occurrence Types");
		filterOccurrence.setImage(ImageProvider.getImage(ImageConstants.OCCURRENCETYPE));
		filterOccurrence.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		filterOccurrence.setVisible(false);
		filterOccurrence.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				// set global filterValue
				filterValue = KindOfTopicType.OCCURRENCE_TYPE_VALUE;
				unusedTopicsTable.refresh();
			}
		});

		// nameType filter
		filterName = new Button(buttonBar, SWT.PUSH);
		filterName.setToolTipText("Show only Name Types");
		filterName.setImage(ImageProvider.getImage(ImageConstants.NAMETYPE));
		filterName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		filterName.setVisible(false);
		filterName.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				// set global filterValue
				filterValue = KindOfTopicType.NAME_TYPE_VALUE;
				unusedTopicsTable.refresh();
			}
		});

		// roleType filter
		filterRole = new Button(buttonBar, SWT.PUSH);
		filterRole.setToolTipText("Show only Role Types");
		filterRole.setImage(ImageProvider.getImage(ImageConstants.ROLETYPE));
		filterRole.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		filterRole.setVisible(false);
		filterRole.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				// set global filterValue
				filterValue = KindOfTopicType.ROLE_TYPE_VALUE;
				unusedTopicsTable.refresh();
			}
		});

		// AssociationType filter
		filterAssociation = new Button(buttonBar, SWT.PUSH);
		filterAssociation.setToolTipText("Show only Association Types");
		filterAssociation.setImage(ImageProvider.getImage(ImageConstants.ASSOCIATIONTYPE));
		filterAssociation.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		filterAssociation.setVisible(false);
		filterAssociation.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				// set global filterValue
				filterValue = KindOfTopicType.ASSOCIATION_TYPE_VALUE;
				unusedTopicsTable.refresh();
			}
		});
	}

	/**
	 * Provider for text and label of each table entry.
	 */

	private class TopicLableProvider extends LabelProvider implements ITableColorProvider {

		public Image getImage(Object element) {

			// get image according to TopicType kind
			if (element instanceof TopicType)

				// black/white icons for deleted icons
				if (selectedSet.contains(element)) {
					switch (((TopicType) element).getKind().getValue()) {

					case 0:
						return ImageProvider.getImage(ImageConstants.TOPICTYPE_BW);
					case 1:
						return ImageProvider.getImage(ImageConstants.OCCURRENCETYPE_BW);
					case 2:
						return ImageProvider.getImage(ImageConstants.NAMETYPE_BW);
					case 3:
						return ImageProvider.getImage(ImageConstants.ROLETYPE_BW);
					case 4:
						return ImageProvider.getImage(ImageConstants.ASSOCIATIONTYPE_BW);
					default:
						return ImageProvider.getImage(ImageConstants.TOPIC_BW);

					}
				} else
					return ImageProvider.getTopicTypeImage((TopicType) element);

			return null;

		}

		public String getText(Object element) {

			// return the name of the TopicType as label
			if (element instanceof TopicType)
				return ((TopicType) element).getName();
			return null;

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.eclipse.jface.viewers.ITableColorProvider#getForeground(java.
		 * lang.Object, int)
		 */
		public Color getForeground(Object element, int columnIndex) {

			// use grey as font to show delete-selection
			if (element instanceof TopicType)
				if (selectedSet.contains(element))
					return comp.getDisplay().getSystemColor(SWT.COLOR_GRAY);

			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.eclipse.jface.viewers.ITableColorProvider#getBackground(java.
		 * lang.Object, int)
		 */
		public Color getBackground(Object element, int columnIndex) {
			// not in use
			return null;
		}

	}

	/**
	 * Reset all filters (type and name) and remove all selected TopicTypes
	 */

	private void clearSelection() {

		textFilter.setText("");
		selectedSet.clear();
		unusedTopicsTable.refresh();

	}

	/**
	 * Choose between deleting and restoring action after double click happens
	 * to TableViewer depending on the clicked TopicType.
	 */

	private void chooseAction() {

		IStructuredSelection sel = (IStructuredSelection) unusedTopicsTable.getSelection();

		if (selectedSet.contains((TopicType) sel.getFirstElement()))
			restoreSelection();
		else
			deleteSelection();

	}

	/**
	 * Add actual selected TopicType from the list of unused TopicTypes (left
	 * table) to the list of selected TopicTypes (right table)
	 */

	private void deleteSelection() {

		// iterate over all selected types (multi selection is allowed!)
		IStructuredSelection sel = (IStructuredSelection) unusedTopicsTable.getSelection();
		for (Iterator it = sel.iterator(); it.hasNext();) {
			selectedSet.add((TopicType) it.next());
		}
		unusedTopicsTable.refresh();

		// change button immediately to re-enable restoring
		deleteButton.setEnabled(false);
		restoreButton.setEnabled(true);

	}

	/**
	 * Remove actual selected TopicType from the list selected TopicTypes (right
	 * table)
	 */

	private void restoreSelection() {

		// iterate over all selected types (multi selection is allowed!)
		IStructuredSelection sel = (IStructuredSelection) unusedTopicsTable.getSelection();
		for (Iterator it = sel.iterator(); it.hasNext();) {
			selectedSet.remove((TopicType) it.next());
		}
		unusedTopicsTable.refresh();

		// change button immediately to re-enable deleting
		deleteButton.setEnabled(true);
		restoreButton.setEnabled(false);

	}

	/**
	 * Add listener to button filter button that allows type filtering
	 * (TopicType, OccurrenceTypes, NameTypes ...)
	 */

	private void hookFilterListener() {
		typeFilterButton.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {

				// activation
				if (typeFilterButton.getText().equals("Filter >>")) {

					// make each specific type filter button visible
					filterTopicType.setVisible(true);
					filterOccurrence.setVisible(true);
					filterName.setVisible(true);
					filterRole.setVisible(true);
					filterAssociation.setVisible(true);

					// set flag and change label
					isTypeFiltered = true;
					typeFilterButton.setText("Filter <<");

					// deactivation
				} else {

					// make each specific type filter button invisible
					filterTopicType.setVisible(false);
					filterOccurrence.setVisible(false);
					filterName.setVisible(false);
					filterRole.setVisible(false);
					filterAssociation.setVisible(false);

					// set flag
					isTypeFiltered = false;

					/*
					 * set global filterValue to -1 to show all results maybe
					 * their should be an "all" button that provides this
					 * feature
					 */
					filterValue = -1;
					unusedTopicsTable.refresh();

					// change label
					typeFilterButton.setText("Filter >>");

				}
			}

		});

	}

	/**
	 * Selection handler to enable the delete and restore button depending on
	 * already selected Types
	 */

	public void selectionChanged(SelectionChangedEvent event) {

		IStructuredSelection sel = (IStructuredSelection) unusedTopicsTable.getSelection();

		if (event.getSelectionProvider().equals(unusedTopicsTable)) {

			if (!event.getSelection().isEmpty() && !selectedSet.contains((TopicType) sel.getFirstElement())) {
				deleteButton.setEnabled(true);
				restoreButton.setEnabled(false);
			} else if (!event.getSelection().isEmpty() && selectedSet.contains((TopicType) sel.getFirstElement())) {
				deleteButton.setEnabled(false);
				restoreButton.setEnabled(true);
			} else {
				deleteButton.setEnabled(false);
				restoreButton.setEnabled(false);
			}

		}
	}

	/**
	 * Getter for whole Composite
	 * 
	 * @return the created composite
	 */
	public Composite getComposite() {
		return this.comp;
	}

	/**
	 * Getter for cleaning list of opicTypes
	 * 
	 * @return list with TopicTypes
	 */
	public List<TopicType> getCleanList() {

		List<TopicType> cleanList = new ArrayList<TopicType>();

		Iterator it = selectedSet.iterator();
		while (it.hasNext()) {
			cleanList.add((TopicType) it.next());
		}

		return cleanList;
	}

}
