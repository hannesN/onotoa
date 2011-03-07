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
package de.topicmapslab.tmcledit.model.views.widgets;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.topicmapslab.tmcledit.model.AbstractTypedCardinalityConstraint;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.commands.SetCardinalityCommand;
import de.topicmapslab.tmcledit.model.util.CardTextObserver;
import de.topicmapslab.tmcledit.model.util.ImageConstants;
import de.topicmapslab.tmcledit.model.util.ImageProvider;

/**
 * @author Hannes Niederhausen
 * 
 */
public class TypedCardinalityConstraintWidget extends AdapterImpl {
	protected final static String[] TABLE_PROPS = { "Type", "cardMin", "cardMax" };

	protected TableViewer tableViewer;
	private Button selectButton;
	private Button newButton;
	private Button removeButton;

	private int minCardinality = 0;
	private int maxCardinality = -1;

	private boolean createLabel;
	private boolean createNewButton;

	private CommandStack commandStack;
	private List<? extends AbstractTypedCardinalityConstraint> input;

	private Label label;

	/**
	 * 
	 * @param parent parent widget
	 * @param toolkit {@link FormToolkit} 
	 * @param commandStack the {@link CommandStack} used to execute modification operations
	 */
	public TypedCardinalityConstraintWidget(Composite parent, FormToolkit toolkit, CommandStack commandStack) {
		this(parent, toolkit, commandStack, true);
	}

	/**
	 * 
	 * @param parent parent widget
	 * @param toolkit {@link FormToolkit} 
	 * @param commandStack the {@link CommandStack} used to execute modification operations
	 * @param createLabel flag if a label for the widget should be created
	 */
	public TypedCardinalityConstraintWidget(Composite parent, FormToolkit toolkit, CommandStack commandStack,
	        boolean createLabel, boolean createNewButton) {
		this.createLabel = createLabel;
		this.commandStack = commandStack;
		this.createNewButton = createNewButton;
		createControls(parent, toolkit);
	}
	
	/**
	 * 
	 * @param parent parent widget
	 * @param toolkit {@link FormToolkit} 
	 * @param commandStack the {@link CommandStack} used to execute modification operations
	 * @param createLabel flag if a label for the widget should be created
	 */
	public TypedCardinalityConstraintWidget(Composite parent, FormToolkit toolkit, CommandStack commandStack,
	        boolean createLabel) {
		this(parent, toolkit, commandStack, true, true);
	}

	/**
	 * Sets the {@link CommandStack}
	 * @param commandStack the {@link CommandStack}
	 */
	public void setCommandStack(CommandStack commandStack) {
		this.commandStack = commandStack;
	}

	private CommandStack getCommandStack() {
		return commandStack;
	}

	/**
	 * Sets the text for the label
	 * @param text the content of the label
	 */
	public void setText(String text) {
		label.setText(text);
	}

	/**
	 * Frees all resources and removes registered listeners
	 */
	public void dispose() {
		for (AbstractTypedCardinalityConstraint tcc : getInput()) {
			tcc.eAdapters().remove(this);
		}
	}

	private List<? extends AbstractTypedCardinalityConstraint> getInput() {
		return ((input == null) ? Collections.<AbstractTypedCardinalityConstraint> emptyList() : input);
	}

	/**
	 * Sets the input for the table
	 * @param input the input for the table
	 */
	public void setInput(List<? extends AbstractTypedCardinalityConstraint> input) {
		for (AbstractTypedCardinalityConstraint tcc : getInput()) {
			tcc.eAdapters().remove(this);
			if (tcc.getType() != null)
				tcc.getType().eAdapters().remove(this);
		}

		this.input = input;
		tableViewer.setInput(getInput());

		for (AbstractTypedCardinalityConstraint tcc : getInput()) {
			tcc.eAdapters().add(this);
			if (tcc.getType() != null)
				tcc.getType().eAdapters().add(this);
		}

	}

	protected void createControls(Composite parent, FormToolkit toolkit) {

		if (createLabel) {
			label = toolkit.createLabel(parent, "label:");
			GridData gd = new GridData();
			gd.verticalAlignment = SWT.TOP;
			label.setLayoutData(gd);
		}

		Composite comp = toolkit.createComposite(parent);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		comp.setLayoutData(gd);
		GridLayout layout = new GridLayout(2, false);
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		comp.setLayout(layout);

		createTable(comp, toolkit);
		tableViewer.setInput(Collections.emptyList());
		createButtons(comp, toolkit);
	}

	private void createButtons(Composite parent, FormToolkit toolkit) {
		Composite comp = toolkit.createComposite(parent);
		comp.setLayout(new GridLayout());
		comp.setLayoutData(new GridData(GridData.FILL_VERTICAL));

		GridData bgd = new GridData();
		bgd.verticalAlignment = SWT.CENTER;
		GridDataFactory fac = GridDataFactory.createFrom(bgd);

		selectButton = toolkit.createButton(comp, "", SWT.PUSH);
		selectButton.setToolTipText("Create a new contraint using an existing topic type.");
		selectButton.setImage(ImageProvider.getImage(ImageConstants.SELECT));
		selectButton.setAlignment(SWT.LEFT);
		fac.applyTo(selectButton);

		if (createNewButton) {
			newButton = toolkit.createButton(comp, "", SWT.PUSH);
			newButton.setToolTipText("Create a new contraint with a new topic type.");
			newButton.setImage(ImageProvider.getImage(ImageConstants.NEW));
			newButton.setAlignment(SWT.LEFT);
			fac.applyTo(newButton);
		}

		removeButton = toolkit.createButton(comp, "", SWT.PUSH);
		removeButton.setToolTipText("Removing the contraints selected in the table.");
		removeButton.setImage(ImageProvider.getImage(ImageConstants.REMOVE));
		removeButton.setAlignment(SWT.LEFT);
		fac.applyTo(removeButton);

	}

	private Composite createTable(Composite parent, FormToolkit toolkit) {
		Composite comp = toolkit.createComposite(parent);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 100;
		comp.setLayoutData(gd);

		// TableColumnLayout layout = new TableColumnLayout();
		GridLayout layout = new GridLayout();
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		comp.setLayout(layout);
		Table table = toolkit.createTable(comp, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		table.setHeaderVisible(true);
		table.setLayoutData(new GridData(GridData.FILL_BOTH));

		for (int i = 0; i < TABLE_PROPS.length; i++) {
			TableColumn tc = new TableColumn(table, i);
			tc.setText(TABLE_PROPS[i]);
			if (i == 0)
				tc.setWidth(150);
			else
				tc.setWidth(80);
			// layout.setColumnData(tc, new ColumnWeightData(1, 50));
		}

		tableViewer = new TableViewer(table);
		tableViewer.setCellEditors(new CellEditor[] { null, getTextCellEditor(true), getTextCellEditor(false) });
		tableViewer.setColumnProperties(TABLE_PROPS);
		tableViewer.setContentProvider(new ArrayContentProvider());
		tableViewer.setLabelProvider(new ScopeTableLabelProvider());
		tableViewer.setCellModifier(new ConstraintCellModifier());

		tableViewer.setSorter(new TypeViewerSorter());
		tableViewer.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {

				IStructuredSelection sel = (IStructuredSelection) event.getSelection();
				if (sel.isEmpty())
					removeButton.setEnabled(false);
				else
					removeButton.setEnabled(true);

			}
		});

		return comp;
	}

	@Override
	public void notifyChanged(Notification msg) {
		if (msg.getEventType() == Notification.SET)
			tableViewer.refresh(msg.getNotifier());
	}

	private CellEditor getTextCellEditor(final boolean isMin) {
		TextCellEditor editor = new TextCellEditor(tableViewer.getTable());
		editor.setValidator(new ICellEditorValidator() {
			boolean min = isMin;

			public String isValid(Object value) {
				String val = (String) value;
				if (val.length() == 0)
					return "No text given";
				if (val.equals("*")) {
					if (!min)
						return null;
					else
						return "No infinity for card min allowed";
				}
				char[] chars = ((String) value).toCharArray();
				for (int i = 0; i < chars.length; i++) {
					if (!Character.isDigit(chars[i])) {
						return "use only digits";
					}
				}

				int v = Integer.parseInt(val);
				if (min) {
					if (v < minCardinality) {
						return "cardMin must be at least:" + minCardinality;
					}
				} else {
					if ((maxCardinality != -1) && (v > maxCardinality)) {
						return "cardMax must be at least:" + maxCardinality;
					}
				}

				return null;
			}
		});

		Text text = (Text) editor.getControl();
		CardTextObserver.addVerifyListener(text, isMin);

		return editor;
	}

	/**
	 * 
	 * @return the button which is used to open a selection dialog
	 */
	public Button getAddButton() {
		return selectButton;
	}

	/**
	 * 
	 * @return the button which opens a new type dialog
	 */
	public Button getNewButton() {
		return newButton;
	}

	/**
	 * 
	 * @return the button which removes the selected elements in the table
	 */
	public Button getRemoveButton() {
		return removeButton;
	}


	/**
	 * Sets a the maximum value for a valid card-max
	 * @param maxCardinality the possible card-max
	 */
	public void setMaxCardinality(int maxCardinality) {
		this.maxCardinality = maxCardinality;
	}

	/**
	 * Sets the minimum value for a valid card-min
	 * @param minCardinality the possiblie card-min
	 */
	public void setMinCardinality(int minCardinality) {
		this.minCardinality = minCardinality;
	}

	private class ConstraintCellModifier implements ICellModifier {

		public boolean canModify(Object element, String property) {
			if ((property.equals(TABLE_PROPS[1])) || property.equals(TABLE_PROPS[2]))
				return true;
			return false;
		}

		public Object getValue(Object element, String property) {
			AbstractTypedCardinalityConstraint constraint = (AbstractTypedCardinalityConstraint) element;
			if (property.equals(TABLE_PROPS[1]))
				return constraint.getCardMin();
			if (property.equals(TABLE_PROPS[2]))
				return constraint.getCardMax();

			return null;
		}

		public void modify(Object element, String property, Object value) {
			TableItem item = (TableItem) element;
			AbstractTypedCardinalityConstraint constraint = (AbstractTypedCardinalityConstraint) item.getData();
			boolean isMin = true;

			if (property.equals(TABLE_PROPS[2])) {
				isMin = false;
			}

			if (value instanceof String) {
				try {
					SetCardinalityCommand cmd = new SetCardinalityCommand(constraint, isMin, (String) value);
					getCommandStack().execute(cmd);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}

		}

	}

	private class TypeViewerSorter extends ViewerSorter {

		@Override
		public int compare(Viewer viewer, Object e1, Object e2) {
			AbstractTypedCardinalityConstraint tc1 = (AbstractTypedCardinalityConstraint) e1;
			AbstractTypedCardinalityConstraint tc2 = (AbstractTypedCardinalityConstraint) e2;

			String name1 = tc1.getType().getName();
			String name2 = tc2.getType().getName();
			return name1.compareTo(name2);
		}
	}

	private class ScopeTableLabelProvider implements ITableLabelProvider {

		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		public String getColumnText(Object element, int columnIndex) {
			AbstractTypedCardinalityConstraint tc = (AbstractTypedCardinalityConstraint) element;
			switch (columnIndex) {
			case 0:
				return tc.getType() == null ? "tmdm:subject" : tc.getType().getName();
			case 1:
				return tc.getCardMin();
			case 2:
				return tc.getCardMax();
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

	/**
	 * LabelProvider for a {@link TopicType}. It renders the name next to the type icon.
	 * 
	 * @author Hannes Niederhausen
	 *
	 */
	public class TopicLabelProvider implements ILabelProvider {

		public Image getImage(Object element) {
			String img = ImageConstants.TOPICTYPE;

			switch (((TopicType) element).getKind()) {
			case ROLE_TYPE:
				img = ImageConstants.ROLETYPE;
				break;
			case ASSOCIATION_TYPE:
				img = ImageConstants.ASSOCIATIONTYPE;
				break;
			case OCCURRENCE_TYPE:
				img = ImageConstants.OCCURRENCETYPE;
				break;
			case NAME_TYPE:
				img = ImageConstants.NAMETYPE;
				break;
			}

			return ImageProvider.getImage(img);
		}

		public String getText(Object element) {
			return ((TopicType) element).getName();
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


	/**
	 * 
	 * @return the used tableViewer
	 */
	public TableViewer getTableViewer() {
		return tableViewer;
	}

	/**
	 * 
	 * @return the shell of the table viewer
	 */
	public Shell getShell() {
		return tableViewer.getTable().getShell();
	}

	/**
	 * Disables/Enables the widget which means all children will be enabled or disabled 
	 * @param enabled
	 */
	public void setEnabled(boolean enabled) {
		selectButton.setEnabled(enabled);
		removeButton.setEnabled(enabled);
		
		if (createNewButton)
			newButton.setEnabled(enabled);
		
		tableViewer.getControl().setEnabled(enabled);
		validate();
	}

	private void validate() {

		if (tableViewer.getSelection().isEmpty())
			removeButton.setEnabled(false);
		else
			removeButton.setEnabled(true);
	}

}
