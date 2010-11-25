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
import de.topicmapslab.tmcledit.model.util.ImageConstants;
import de.topicmapslab.tmcledit.model.util.ImageProvider;

/**
 * Class provides the advanced part of the TopicType search dialog (situated at
 * the bottom of the dialog) where the user can add constraints to specify the
 * search.
 * 
 * Specific filter buttons and the list of available topics should implemented /
 * given by the subclass, otherwise there will be no filters and no content!
 * 
 * @author Sebastian Lippert
 */
public abstract class AbstractTopicTypeSearchAdvancedPart implements ISelectionChangedListener {

	private List<TopicType> selectedTopics = new ArrayList<TopicType>();
	private TableViewer availableTopicList, selectedTopicList;

	private Shell dockingShell;
	private Composite buttonBar;
	private Group group;
	private Button addButton, removeButton, clearButton, typeFilterButton;
	private Text textFilterAvailable, textFilterSelected;;

	private int filterValue = -1;
	private boolean enableTypeFilter;
	private boolean isTypeFiltered = false;
	private boolean isTextFilteredAvailable = false;
	private boolean isTextFilteredSelected = false;
	private String textFilterSelectedValue, textFilterAvailableValue;

	private TopicTypeComparator comparator;
	private List<TopicType> inputList;
	private List<Button> filterButtonList;

	/**
	 * Constructor creates the whole bottom part of the TopicType search dialog,
	 * if the advanced button was pressed. That includes two tables for
	 * TopicTypes (available and selected), a textField at the top of each table
	 * to filter results and some button for user interaction (add, remove,
	 * clear, type filtering)
	 * 
	 * @param inputList
	 *            List with available topics to specify the search
	 * @param enableTypeFilter
	 *            Boolean that enables or disables the type filter button
	 */

	public AbstractTopicTypeSearchAdvancedPart(List<TopicType> inputList, boolean enableTypeFilter) {

		this.inputList = inputList;
		this.enableTypeFilter = enableTypeFilter;
		filterButtonList = new ArrayList<Button>();

		GridData gridData;
		Button dummyButton;

		comparator = new TopicTypeComparator();
		dockingShell = new Shell();

		gridData = new GridData(GridData.FILL_BOTH);
		gridData.horizontalSpan = 2;

		// Group that contains all elements
		group = new Group(dockingShell, SWT.SHADOW_OUT);
		group.setText("Used Topic Types");
		group.setLayout(new GridLayout(3, false));
		group.setLayoutData(gridData);

		gridData = new GridData(GridData.FILL_HORIZONTAL);

		// typing sensitive filter for available TopicTypes
		textFilterAvailable = new Text(group, SWT.SINGLE | SWT.BORDER);
		textFilterAvailable.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		textFilterAvailable.setText("filter results");
		textFilterAvailable.addFocusListener(new FocusListener() {

			public void focusLost(FocusEvent e) {
				// not in use
			}

			// delete help message at the textBox after focus gained (par
			// example by mouse click)
			public void focusGained(FocusEvent e) {
				textFilterAvailable.setText("");

				// important: remove focusListener. Otherwise every focus clear
				// the filter
				textFilterAvailable.removeFocusListener(this);
				textFilterAvailable.addKeyListener(new KeyListener() {

					// listens to done key strokes an refreshes list
					public void keyReleased(KeyEvent e) {
						isTextFilteredAvailable = true;
						textFilterAvailableValue = textFilterAvailable.getText();
						refreshLists();
					}

					public void keyPressed(KeyEvent e) {
						// not in use
					}
				});
			}
		});

		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalAlignment = SWT.CENTER;

		// dummy button for layout purposes
		dummyButton = new Button(group, SWT.PUSH);
		dummyButton.setLayoutData(gridData);
		dummyButton.setText("=");
		dummyButton.setVisible(false);

		// typing sensitive filter for selected TopicTypes
		textFilterSelected = new Text(group, SWT.SINGLE | SWT.BORDER);
		textFilterSelected.setText("filter results");
		textFilterSelected.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		textFilterSelected.addFocusListener(new FocusListener() {

			public void focusLost(FocusEvent e) {
				// not in use
			}

			// delete help message at the textBox after focus gained (par
			// example by mouse click)
			public void focusGained(FocusEvent e) {
				textFilterSelected.setText("");

				// important: remove focusListener. Otherwise every focus clear
				// the filter
				textFilterSelected.removeFocusListener(this);
				textFilterSelected.addKeyListener(new KeyListener() {

					public void keyReleased(KeyEvent e) {
						isTextFilteredSelected = true;
						textFilterSelectedValue = textFilterSelected.getText();
						refreshLists();
					}

					public void keyPressed(KeyEvent e) {
						// not in use
					}
				});
			}
		});

		gridData = new GridData(GridData.FILL_BOTH);
		gridData.widthHint = 200;

		// left table with available TopicTypes from the schema
		availableTopicList = new TableViewer(group, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
		availableTopicList.getTable().setLayoutData(gridData);
		availableTopicList.setLabelProvider(new TopicLableProvider());
		availableTopicList.setContentProvider(new ArrayContentProvider());

		Collections.sort(this.inputList, comparator);

		// set input and add listeners
		availableTopicList.setInput(this.inputList);
		availableTopicList.addSelectionChangedListener(this);
		availableTopicList.addDoubleClickListener(new IDoubleClickListener() {

			// double click adds the TopicType to the left table
			public void doubleClick(DoubleClickEvent event) {
				addSelection();
			}

		});

		// add filter for input list
		availableTopicList.addFilter(new ViewerFilter() {

			@Override
			public boolean select(Viewer viewer, Object parentElement, Object element) {

				/*
				 * check if TopicType is already selected. If true, don't
				 * display him (more easier than move always topics between both
				 * tables)
				 */
				if (selectedTopics.contains(element))
					return false;

				// filter by type
				if (isTypeFiltered)
					if (((TopicType) element).getKind().getValue() != filterValue && filterValue != -1)
						return false;
				// filter by name
				if (isTextFilteredAvailable)
					if ((!((TopicType) element).getName().startsWith(textFilterAvailableValue)))
						return false;

				return true;

			}

		});

		// creates the whole part between both tables
		createButtonBar();

		gridData = new GridData(GridData.FILL_BOTH);
		gridData.widthHint = 200;

		// right table that shows selected TopicTypes
		selectedTopicList = new TableViewer(group, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
		selectedTopicList.getTable().setLayoutData(gridData);
		selectedTopicList.setLabelProvider(new TopicLableProvider());
		selectedTopicList.setContentProvider(new ArrayContentProvider());

		// set input (empty list) and add listeners
		selectedTopicList.setInput(selectedTopics);
		selectedTopicList.addSelectionChangedListener(this);
		selectedTopicList.addDoubleClickListener(new IDoubleClickListener() {

			// double click removes the TopicType from the right table
			public void doubleClick(DoubleClickEvent event) {
				removeSelection();
			}

		});

		// filter displayed TopicTypes
		selectedTopicList.addFilter(new ViewerFilter() {

			@Override
			public boolean select(Viewer viewer, Object parentElement, Object element) {

				// filter by name
				if (isTextFilteredSelected)
					if ((!((TopicType) element).getName().startsWith(textFilterSelectedValue)))
						return false;

				return true;
			}

		});

		if (!this.enableTypeFilter)
			typeFilterButton.setVisible(false);

	}

	/**
	 * Creation of the button bar that is responsible for most user interaction.
	 * Including add, remove, clear and filter button.
	 */

	private void createButtonBar() {

		buttonBar = new Composite(group, SWT.NONE);
		buttonBar.setLayout(new GridLayout());
		buttonBar.setLayoutData(new GridData(GridData.FILL_VERTICAL));

		// clear button to reset both tables
		clearButton = new Button(buttonBar, SWT.PUSH);
		clearButton.setText("");
		clearButton.setImage(ImageProvider.getImage(ImageConstants.CLEAR));
		clearButton.setToolTipText("Remove all selected Topics");
		clearButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		clearButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				clearSelection();
			}
		});

		// button for adding one topic to the list of selected TopicTypes
		addButton = new Button(buttonBar, SWT.PUSH);
		addButton.setText("");
		addButton.setImage(ImageProvider.getImage(ImageConstants.ADD_ONE));
		addButton.setToolTipText("Add selected Topics");
		addButton.setEnabled(false);
		addButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		addButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addSelection();
			}
		});

		// button for removing one topic from the list of selected TopicTypes
		removeButton = new Button(buttonBar, SWT.PUSH);
		removeButton.setText("");
		removeButton.setImage(ImageProvider.getImage(ImageConstants.REMOVE_ONE));
		removeButton.setEnabled(false);
		removeButton.setToolTipText("Remove selected Topics");
		removeButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		removeButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				removeSelection();
			}
		});

		// button activates filtering available topics by their types
		typeFilterButton = new Button(buttonBar, SWT.Activate);
		typeFilterButton.setText("");
		typeFilterButton.setImage(de.topicmapslab.onotoa.search.util.ImageProvider
		        .getImage(de.topicmapslab.onotoa.search.util.ImageConstants.FILTER_OFF));
		typeFilterButton.setToolTipText("Enable filter for list of available Topics");
		typeFilterButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		typeFilterButton.setSelection(false);
		hookFilterListener();

	}

	/**
	 * Provider for text and label of each table entry.
	 */

	private class TopicLableProvider extends LabelProvider {

		@Override
		public Image getImage(Object element) {

			// get image according to TopicType kind
			if (element instanceof TopicType)
				return ImageProvider.getTopicTypeImage((TopicType) element);

			return super.getImage(element);
		}

		@Override
		public String getText(Object element) {

			// return the name of the TopicType as label
			if (element instanceof TopicType)
				return ((TopicType) element).getName();

			return super.getText(element);
		}

	}

	/**
	 * Reset all filters (type and name) and remove all selected TopicTypes
	 */

	private void clearSelection() {

		textFilterAvailable.setText("");
		textFilterSelected.setText("");
		textFilterAvailableValue = "";
		textFilterSelectedValue = "";
		selectedTopics.clear();
		availableTopicList.refresh();
		selectedTopicList.refresh();

	}

	/**
	 * Add actual selected TopicType(s) from the list of available TopicTypes
	 * (left table) to the list of selected TopicTypes (right table)
	 */

	private void addSelection() {

		IStructuredSelection sel = (IStructuredSelection) availableTopicList.getSelection();

		// iterate over all selected types (multi selection is allowed!)
		for (Iterator it = sel.iterator(); it.hasNext();) {
			selectedTopics.add((TopicType) it.next());
		}
		Collections.sort(selectedTopics, comparator);
		availableTopicList.refresh();
		selectedTopicList.refresh();

	}

	/**
	 * Remove actual selected TopicType(s) from the list selected TopicTypes
	 * (right table)
	 */

	private void removeSelection() {

		IStructuredSelection sel = (IStructuredSelection) selectedTopicList.getSelection();

		// iterate over all selected types (multi selection is allowed!)
		for (Iterator it = sel.iterator(); it.hasNext();) {
			selectedTopics.remove((TopicType) it.next());
		}
		availableTopicList.refresh();
		selectedTopicList.refresh();

	}

	/**
	 * Add listener to button filter button that allows type filtering (only
	 * OccurrenceTypes, NameTypes ...)
	 */

	private void hookFilterListener() {
		typeFilterButton.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {

				// activation
				if (!isTypeFiltered) {
					// make each specific type filter button visible
					for (Button b : filterButtonList)
						b.setVisible(true);

					// change button image
					isTypeFiltered = true;
					typeFilterButton.setImage(de.topicmapslab.onotoa.search.util.ImageProvider
					        .getImage(de.topicmapslab.onotoa.search.util.ImageConstants.FILTER_ON));

					// deactivation
				} else {

					// make each specific type filter button invisible
					for (Button b : filterButtonList)
						b.setVisible(false);

					// set flag
					isTypeFiltered = false;

					/*
					 * set global filterValue to -1 to show all results maybe
					 * their should be an "all" button that provides this
					 * feature
					 */
					filterValue = -1;
					refreshLists();

					// change button image
					typeFilterButton.setImage(de.topicmapslab.onotoa.search.util.ImageProvider
					        .getImage(de.topicmapslab.onotoa.search.util.ImageConstants.FILTER_OFF));

				}
			}

		});

	}

	/**
	 * Selection handler to enable the add and remove button only if at least
	 * one TopicType is selected
	 */

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

	/**
	 * Refresh both tables after changes happened to display them in real time
	 */

	private void refreshLists() {

		selectedTopicList.refresh();
		availableTopicList.refresh();

	}

	/**
	 * Add a filterButton depending on the function argument to the buttonBar
	 * 
	 * @param KindOfTopicType
	 *            type the button should filter
	 */

	protected void addFilterButton(KindOfTopicType type) {

		Button filterButton;

		switch (type) {
		case TOPIC_TYPE:

			// topicType filter
			filterButton = new Button(buttonBar, SWT.PUSH);
			filterButton.setToolTipText("Show only Topic Types");
			filterButton.setImage(ImageProvider.getImage(ImageConstants.TOPICTYPE));
			filterButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			filterButton.setVisible(false);
			filterButton.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {

					// set global filterValue
					filterValue = KindOfTopicType.TOPIC_TYPE_VALUE;
					refreshLists();
				}
			});
			filterButtonList.add(filterButton);
			break;

		case OCCURRENCE_TYPE:

			// occurrenceType filter
			filterButton = new Button(buttonBar, SWT.PUSH);
			filterButton.setToolTipText("Show only Occurrence Types");
			filterButton.setImage(ImageProvider.getImage(ImageConstants.OCCURRENCETYPE));
			filterButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			filterButton.setVisible(false);
			filterButton.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {

					// set global filterValue
					filterValue = KindOfTopicType.OCCURRENCE_TYPE_VALUE;
					refreshLists();
				}
			});
			filterButtonList.add(filterButton);
			break;

		case NAME_TYPE:

			// nameType filter
			filterButton = new Button(buttonBar, SWT.PUSH);
			filterButton.setToolTipText("Show only Name Types");
			filterButton.setImage(ImageProvider.getImage(ImageConstants.NAMETYPE));
			filterButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			filterButton.setVisible(false);
			filterButton.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {

					// set global filterValue
					filterValue = KindOfTopicType.NAME_TYPE_VALUE;
					refreshLists();
				}
			});
			filterButtonList.add(filterButton);
			break;

		case ROLE_TYPE:

			// roleType filter
			filterButton = new Button(buttonBar, SWT.PUSH);
			filterButton.setToolTipText("Show only Role Types");
			filterButton.setImage(ImageProvider.getImage(ImageConstants.ROLETYPE));
			filterButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			filterButton.setVisible(false);
			filterButton.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {

					// set global filterValue
					filterValue = KindOfTopicType.ROLE_TYPE_VALUE;
					refreshLists();
				}
			});
			filterButtonList.add(filterButton);
			break;

		case ASSOCIATION_TYPE:

			// AssociationType filter
			filterButton = new Button(buttonBar, SWT.PUSH);
			filterButton.setToolTipText("Show only Association Types");
			filterButton.setImage(ImageProvider.getImage(ImageConstants.ASSOCIATIONTYPE));
			filterButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			filterButton.setVisible(false);
			filterButton.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {

					// set global filterValue
					filterValue = KindOfTopicType.ASSOCIATION_TYPE_VALUE;
					refreshLists();
				}
			});
			filterButtonList.add(filterButton);
			break;

		default:
			break;
		}

	}

	/**
	 * Getter for selected TopicTypes
	 * 
	 * @return list of TopicTypes
	 */

	public List<TopicType> getSelectedList() {

		return (List<TopicType>) selectedTopicList.getInput();

	}

	/**
	 * Getter for whole advanced dialog part
	 * 
	 * @return Group that contains every element
	 */

	public Group getAdvancedGroup() {
		return this.group;
	}

}
