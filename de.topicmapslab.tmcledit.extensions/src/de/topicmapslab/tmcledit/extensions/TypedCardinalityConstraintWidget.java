/**
 * 
 */
package de.topicmapslab.tmcledit.extensions;

import java.util.List;

import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.topicmapslab.tmcledit.model.AbstractTypedCardinalityConstraint;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.commands.SetCardinalityCommand;
import de.topicmapslab.tmcledit.model.util.ImageConstants;
import de.topicmapslab.tmcledit.model.util.ImageProvider;

/**
 * @author Hannes Niederhausen
 *
 */
public class TypedCardinalityConstraintWidget {
	protected final static String[] TABLE_PROPS = {"Type", "cardMin", "cardMax"};
	
	protected TableViewer tableViewer;
	private Button addButton;
	private Button newButton;
	private Button removeButton;
	
	private CommandStack commandStack;

	private Label label;
	
	public TypedCardinalityConstraintWidget(Composite parent, FormToolkit toolkit, CommandStack commandStack) {
		this.commandStack = commandStack;
		createControls(parent, toolkit);
	}
	
	private CommandStack getCommandStack() {
		return commandStack;
	}
	
	public void setText(String text) {
		label.setText(text);
	}
	
	public void setInput(List<? extends AbstractTypedCardinalityConstraint> input) {
		tableViewer.setInput(input);
	}
	
	protected void createControls(Composite parent,
			FormToolkit toolkit) {
	

		GridDataFactory fac = GridDataFactory.createFrom(new GridData(GridData.FILL_HORIZONTAL));
		
		label = toolkit.createLabel(parent, "label:");
		GridData gd = new GridData();
		gd.verticalAlignment = SWT.TOP;
		label.setLayoutData(gd);
		Composite comp = toolkit.createComposite(parent);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		comp.setLayoutData(gd);
		GridLayout layout = new GridLayout(2, false);
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		comp.setLayout(layout);
		fac.applyTo(comp);
		
		
		
		Composite scopeComp = toolkit.createComposite(comp);
		layout = new GridLayout(2, false);
		layout.marginWidth = 0;
		scopeComp.setLayout(layout);
		fac.applyTo(scopeComp);
		createScopeTable(scopeComp, toolkit);
		
		createScopeButtons(scopeComp, toolkit);
	}
	
	private void createScopeButtons(Composite parent, FormToolkit toolkit) {
		Composite comp = toolkit.createComposite(parent);
		comp.setLayout(new GridLayout());
		comp.setLayoutData(new GridData(GridData.FILL_VERTICAL));
		
		GridData bgd = new GridData();
		bgd.widthHint = 100;
		bgd.verticalAlignment = SWT.CENTER;
		GridDataFactory fac = GridDataFactory.createFrom(bgd);
		
		addButton = toolkit.createButton(comp, "Add..", SWT.PUSH);
		fac.applyTo(addButton);
		
		newButton = toolkit.createButton(comp, "New..", SWT.PUSH);
		fac.applyTo(newButton);
		
		removeButton = toolkit.createButton(comp, "Remove", SWT.PUSH);
		fac.applyTo(removeButton);
		
	}

	private Composite createScopeTable(Composite parent, FormToolkit toolkit) {
		Composite comp = toolkit.createComposite(parent);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint=100;
		comp.setLayoutData(gd);
		
		//TableColumnLayout layout = new TableColumnLayout();
		GridLayout layout = new GridLayout();
		comp.setLayout(layout);
		Table table = toolkit.createTable(comp, SWT.BORDER);
		table.setHeaderVisible(true);
		table.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		for (int i=0; i<TABLE_PROPS.length; i++) {
			TableColumn tc = new TableColumn(table, i);
			tc.setText(TABLE_PROPS[i]);
			if (i==0)
				tc.setWidth(150);
			else
				tc.setWidth(80);
		//	layout.setColumnData(tc, new ColumnWeightData(1, 50));
		}
		
		tableViewer = new TableViewer(table);
		tableViewer.setCellEditors(new CellEditor[]{null, getTextCellEditor(), getTextCellEditor()});
		tableViewer.setColumnProperties(TABLE_PROPS);
		tableViewer.setContentProvider(new ArrayContentProvider());
		tableViewer.setLabelProvider(new ScopeTableLabelProvider());
		tableViewer.setCellModifier(new ConstraintCellModifier());
		return comp;
	}
	
	private CellEditor getTextCellEditor() {
		TextCellEditor editor = new TextCellEditor(tableViewer.getTable());
		editor.setValidator(new ICellEditorValidator() {

			@Override
			public String isValid(Object value) {
				String val = (String) value;
				if (val.length()==0)
					return "No text given";
				if (val.equals("*"))
					return null;
				char[] chars = ((String)value).toCharArray();
				for (int i = 0; i < chars.length; i++) {
					if (!Character.isDigit(chars[i])) {
						return "use only digits or *";
					}
				}				
				return null;
			}
		});
		return editor;
	}
	
	public Button getAddButton() {
		return addButton;
	}
	
	public Button getNewButton() {
		return newButton;
	}
	
	public Button getRemoveButton() {
		return removeButton;
	}
	private class ConstraintCellModifier implements ICellModifier {

		@Override
		public boolean canModify(Object element, String property) {
			if ( (property.equals(TABLE_PROPS[1])) || property.equals(TABLE_PROPS[2]) )
				return true;
			return false;
		}

		@Override
		public Object getValue(Object element, String property) {
			AbstractTypedCardinalityConstraint constraint = (AbstractTypedCardinalityConstraint) element;
			if (property.equals(TABLE_PROPS[1]))
				return constraint.getCardMin();
			if (property.equals(TABLE_PROPS[2]))
				return constraint.getCardMax();
			
			return null;
		}

		@Override
		public void modify(Object element, String property, Object value) {
			TableItem item = (TableItem) element;
			AbstractTypedCardinalityConstraint constraint = (AbstractTypedCardinalityConstraint) item.getData();
			boolean isMin = true;
			if (property.equals(TABLE_PROPS[1])) {
				isMin = true;
			} else if (property.equals(TABLE_PROPS[2])) {
				isMin = false;
			}
			if (value instanceof String) {
				try {
					SetCardinalityCommand cmd = new SetCardinalityCommand(
							constraint, isMin, (String) value);
					getCommandStack().execute(cmd);
				} catch (Exception e) {
					new RuntimeException(e);
				}
			}
			
		}
		
	}
	
	private class ScopeTableLabelProvider implements ITableLabelProvider {

		@Override
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		@Override
		public String getColumnText(Object element, int columnIndex) {
			AbstractTypedCardinalityConstraint tc = (AbstractTypedCardinalityConstraint) element;
			switch(columnIndex) {
			case 0: return tc.getType().getName();
			case 1: return tc.getCardMin();
			case 2: return tc.getCardMax();
			}
			return null;
		}

		@Override
		public void addListener(ILabelProviderListener listener) {
		}

		@Override
		public void dispose() {
		}

		@Override
		public boolean isLabelProperty(Object element, String property) {
			return false;
		}

		@Override
		public void removeListener(ILabelProviderListener listener) {
		}
		
	}
	
	protected class TopicLabelProvider implements ILabelProvider {

		@Override
		public Image getImage(Object element) {
			return ImageProvider.getImage(ImageConstants.ROLETYPE);
		}

		@Override
		public String getText(Object element) {
			return ((TopicType)element).getName();
		}

		@Override
		public void addListener(ILabelProviderListener listener) {
		}

		@Override
		public void dispose() {
		}

		@Override
		public boolean isLabelProperty(Object element, String property) {
			return false;
		}

		@Override
		public void removeListener(ILabelProviderListener listener) {
		}
		
	}
	
}
