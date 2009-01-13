package de.topicmapslab.tmcledit.extensions.views.pages;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ListSelectionDialog;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.topicmapslab.tmcledit.extensions.dialogs.NewTopicTypeWizard;
import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.ScopeConstraint;
import de.topicmapslab.tmcledit.model.ScopedConstraint;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.commands.AddScopeConstraintsCommand;
import de.topicmapslab.tmcledit.model.commands.RemoveScopeConstraintsCommand;
import de.topicmapslab.tmcledit.model.commands.SetCardinalityCommand;
import de.topicmapslab.tmcledit.model.util.ImageConstants;
import de.topicmapslab.tmcledit.model.util.ImageProvider;
import de.topicmapslab.tmcledit.model.util.ModelIndexer;

@SuppressWarnings("unused")
public abstract class AbstractScopedContraintModelPage extends
		AbstractConstraintModelPage {

	protected final static String[] TABLE_PROPS = {"Scope Type", "cardMin", "cardMax"};
	
	protected TableViewer scopeTable;
	private Button addButton;
	private Button newButton;
	private Button removeButton;
	
	public AbstractScopedContraintModelPage(String id) {
		super(id);
	}
	
	@Override
	protected void createCommonConstraintControls(Composite parent,
			FormToolkit toolkit) {
		super.createCommonConstraintControls(parent, toolkit);
		
		GridDataFactory fac = GridDataFactory.createFrom(new GridData(GridData.FILL_HORIZONTAL));
		
		Label l = toolkit.createLabel(parent, "scope:");
		GridData gd = new GridData();
		gd.verticalAlignment = SWT.TOP;
		Composite comp = toolkit.createComposite(parent);
		GridLayout layout = new GridLayout(2, false);
		layout.marginWidth = 0;
		comp.setLayout(layout);
		fac.applyTo(comp);
		
		
		
		Composite scopeComp = toolkit.createComposite(comp);
		layout = new GridLayout(2, false);
		layout.marginWidth = 0;
		scopeComp.setLayout(layout);
		fac.applyTo(scopeComp);
		createScopeTable(scopeComp, toolkit);
		
		createScopeButtons(scopeComp, toolkit);
		
		hookButtonListeners();
		
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
		
		scopeTable = new TableViewer(table);
		scopeTable.setCellEditors(new CellEditor[]{null, getTextCellEditor(), getTextCellEditor()});
		scopeTable.setColumnProperties(TABLE_PROPS);
		scopeTable.setContentProvider(new ArrayContentProvider());
		scopeTable.setLabelProvider(new ScopeTableLabelProvider());
		scopeTable.setCellModifier(new ScopeCellModifier());
		return comp;
	}
	
	private CellEditor getTextCellEditor() {
		TextCellEditor editor = new TextCellEditor(scopeTable.getTable());
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

	protected void hookButtonListeners() {
		addButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				ModelIndexer instance = ModelIndexer.getInstance();
				TopicMapSchema topicMapSchema = instance.getTopicMapSchema();
				List<TopicType> list = new ArrayList<TopicType>();
				if (topicMapSchema.isActiveScopeTypeConstraint()) {
					list.addAll(instance.getScopeTypes());
				} else {
					list.addAll(topicMapSchema.getTopicTypes());
					list.remove(getCastedModel().eContainer());
				}
										
				ListSelectionDialog dlg = new ListSelectionDialog(
						scopeTable.getTable().getShell(),
						list,
						new ArrayContentProvider(),
						new TopicLabelProvider(),
						"Choose the Scope type");
				
				if (dlg.open()==Dialog.OK) {
					if (dlg.getResult().length==0)
						return;
					
					List<ScopeConstraint> scl = new ArrayList<ScopeConstraint>();
					for (Object tt : dlg.getResult()) {
						ScopeConstraint sc = ModelFactory.eINSTANCE.createScopeConstraint();
						sc.setType((TopicType) tt);
						sc.setCardMin("0");
						sc.setCardMax("1");
						scl.add(sc);
					}
					AddScopeConstraintsCommand cmd = new AddScopeConstraintsCommand(getCastedModel(), scl);
					getCommandStack().execute(cmd);
					
				}
				
			}
		});
		
		newButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				NewTopicTypeWizard wizard = new NewTopicTypeWizard();
				wizard.setDefaultType(KindOfTopicType.SCOPE_TYPE);
				WizardDialog dlg = new WizardDialog(cardMinText.getShell(), wizard);
				
				if (dlg.open()==Dialog.OK) {
					TopicType tt = wizard.getNewTopicType();
					ModelIndexer.getInstance().getTopicMapSchema().getTopicTypes().add(tt);
					List<ScopeConstraint> scl = new ArrayList<ScopeConstraint>();
					ScopeConstraint sc = ModelFactory.eINSTANCE.createScopeConstraint();
					sc.setType(tt);
					sc.setCardMin("0");
					sc.setCardMax("1");
					scl.add(sc);
					AddScopeConstraintsCommand cmd = new AddScopeConstraintsCommand(getCastedModel(), scl);
					getCommandStack().execute(cmd);
						
				}
			}
		});
		
		removeButton.addSelectionListener(new SelectionAdapter() {
			@SuppressWarnings("unchecked")
			@Override
			public void widgetSelected(SelectionEvent e) {
				IStructuredSelection sel = (IStructuredSelection) scopeTable.getSelection();
				
				if (sel.isEmpty())
					return;
				
				List<ScopeConstraint> removeList = new ArrayList<ScopeConstraint>();
				Iterator<ScopeConstraint> it = sel.iterator();
				while (it.hasNext()) {
					removeList.add(it.next());
				}
				
				RemoveScopeConstraintsCommand cmd = new RemoveScopeConstraintsCommand(
						getCastedModel(), removeList);
				getCommandStack().execute(cmd);
			}
		});
	}
	
	@Override
	public void notifyChanged(Notification notification) {
		if (notification.getNotifier() instanceof ScopeConstraint) {
			if (notification.getFeatureID(TopicType.class)==ModelPackage.SCOPE_CONSTRAINT__TYPE) {
				if (notification.getOldValue()!=null) {
					((TopicType)notification.getOldValue()).eAdapters().remove(this);
				}
				if (notification.getNewValue()!=null) {
					((TopicType)notification.getNewValue()).eAdapters().add(this);
				}
			} 
			scopeTable.refresh(notification.getNotifier());
			return;
			
		}
		
		
		super.notifyChanged(notification);
	}
	
	@Override
	public void setModel(Object model) {
		if (getCastedModel()!=null) {
			for (ScopeConstraint sc : getCastedModel().getScope()) {
				if (sc.getType()!=null)
					sc.getType().eAdapters().remove(this);
				sc.eAdapters().remove(this);
			}
		}
		
		super.setModel(model);
		for (ScopeConstraint sc : getCastedModel().getScope()) {
			if (sc.getType()!=null)
				sc.getType().eAdapters().add(this);
			sc.eAdapters().add(this);
		}
	}
	
	private ScopedConstraint getCastedModel() {
		return (ScopedConstraint) getModel();
	}
	
	
	@Override
	public void updateUI() {
		super.updateUI();
		scopeTable.setInput(getCastedModel().getScope());
		scopeTable.refresh();
	}
	
	private class ScopeCellModifier implements ICellModifier {

		@Override
		public boolean canModify(Object element, String property) {
			if ( (property.equals(TABLE_PROPS[1])) || property.equals(TABLE_PROPS[2]) )
				return true;
			return false;
		}

		@Override
		public Object getValue(Object element, String property) {
			ScopeConstraint scopeConstraint = (ScopeConstraint) element;
			if (property.equals(TABLE_PROPS[1]))
				return scopeConstraint.getCardMin();
			if (property.equals(TABLE_PROPS[2]))
				return scopeConstraint.getCardMax();
			
			return null;
		}

		@Override
		public void modify(Object element, String property, Object value) {
			TableItem item = (TableItem) element;
			ScopeConstraint scopeConstraint = (ScopeConstraint) item.getData();
			boolean isMin = true;
			if (property.equals(TABLE_PROPS[1])) {
				isMin = true;
			} else if (property.equals(TABLE_PROPS[2])) {
				isMin = false;
			}
			if (value instanceof String) {
				try {
					SetCardinalityCommand cmd = new SetCardinalityCommand(
							scopeConstraint, isMin, (String) value);
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
			ScopeConstraint sc = (ScopeConstraint) element;
			switch(columnIndex) {
			case 0: return sc.getType().getName();
			case 1: return sc.getCardMin();
			case 2: return sc.getCardMax();
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
	
	private class TopicLabelProvider implements ILabelProvider {

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
