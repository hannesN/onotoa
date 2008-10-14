/**
 * 
 */
package de.topicmapslab.tmcledit.extensions.views.pages;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.topicmapslab.tmcledit.model.MappingElement;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.commands.UpdatePrefixCommand;

/**
 * @author Hannes Niederhausen
 *
 */
public class PrefixMappingPage extends AbstractModelPage {

	private final String[] columnNames = {"key", "value"};

	private TableViewer tableViewer;
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void setModel(Object model) {
		TopicMapSchema schema = null;
		if (model instanceof MappingElement) {
			MappingElement me = (MappingElement) model;
			schema = (TopicMapSchema) me.eContainer(); 
		} else if (model instanceof EObjectContainmentEList) {
			// this must be the list of mapping elements
			model = ((EObjectContainmentEList)model).getEObject();
		}
		super.setModel(schema);
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
		
		createTableViewer(toolkit, comp);
		
		setControl(comp);
	}

	private void createTableViewer(FormToolkit toolkit, Composite parent) {
		Composite tableComp = toolkit.createComposite(parent);
		tableComp.setLayoutData(new GridData(GridData.FILL_BOTH));
		TableColumnLayout layout = new TableColumnLayout();
		tableComp.setLayout(layout);
		
		Table table = toolkit.createTable(tableComp, SWT.BORDER);
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

	@Override
	public void notifyChanged(Notification notification) {
		if (notification.getNotifier().equals(getModel()) ) {
			updateUI();
		}

	}

	private class TableLabelProvider implements ITableLabelProvider {

		@Override
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		@Override
		public String getColumnText(Object element, int columnIndex) {
			MappingElement me = (MappingElement) element;
			switch(columnIndex) {
			case 0: return me.getKey();
			case 1: return me.getValue();
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
	
	private class TableCellModifier implements ICellModifier {

		@Override
		public boolean canModify(Object element, String property) {
			return true;
		}

		@Override
		public Object getValue(Object element, String property) {
			MappingElement me = (MappingElement) element;
			if (columnNames[0].equals(property)) {
				return me.getKey();
			} else {
				return me.getValue();
			}
		}

		@Override
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
			getCommandStack().execute(new UpdatePrefixCommand(me, key, val));
			
		}
		
	}
	
}
