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
package de.topicmapslab.tmcledit.model.views.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.dialogs.ListSelectionDialog;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.topicmapslab.tmcledit.model.AbstractTypedCardinalityConstraint;
import de.topicmapslab.tmcledit.model.AssociationType;
import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.RoleCombinationConstraint;
import de.topicmapslab.tmcledit.model.RoleConstraint;
import de.topicmapslab.tmcledit.model.ScopeConstraint;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.commands.AddRoleCombinationConstraintCommand;
import de.topicmapslab.tmcledit.model.commands.AddRoleConstraintCommand;
import de.topicmapslab.tmcledit.model.commands.CreateTopicTypeCommand;
import de.topicmapslab.tmcledit.model.commands.RemoveRoleCombinationConstraintCommand;
import de.topicmapslab.tmcledit.model.commands.RemoveRoleConstraintCommand;
import de.topicmapslab.tmcledit.model.dialogs.NewRoleCombinationConstraintDialog;
import de.topicmapslab.tmcledit.model.dialogs.NewTopicTypeWizard;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;
import de.topicmapslab.tmcledit.model.index.TopicIndexer;
import de.topicmapslab.tmcledit.model.util.ImageProvider;
import de.topicmapslab.tmcledit.model.views.widgets.TypedCardinalityConstraintWidget;

/**
 * @author Hannes Niederhausen
 * 
 */
public class AssociationTypeModelPage extends ScopedTopicTypePage {

	private TypedCardinalityConstraintWidget control;
	private TableViewer roleCombinationViewer;
	private Button addButton;
	private Button removeButton;

	public AssociationTypeModelPage() {
		super("asssociation type");
	}

	@Override
	protected void createAdditionalControls(Composite parent, FormToolkit toolkit) {

		control = new TypedCardinalityConstraintWidget(parent, toolkit, getCommandStack());
		control.setText("roles:");

		super.createAdditionalControls(parent, toolkit);
	}

	@Override
	protected void setEnabled(boolean enabled) {
	     super.setEnabled(enabled);
	     addButton.setEnabled(enabled);
	     removeButton.setEnabled(enabled);
	     roleCombinationViewer.getControl().setEnabled(enabled);
	     control.setEnabled(enabled);
	}
	
	@Override
	public void dispose() {
		control.dispose();
	    super.dispose();
	}
	
	@Override
	public void setCommandStack(CommandStack commandStack) {
		super.setCommandStack(commandStack);
		if (control != null)
			control.setCommandStack(commandStack);
	}

	private AssociationType getCastedModel() {
		return (AssociationType) getModel();
	}

	private void hookButtonListeners() {
		control.getAddButton().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				TopicIndexer instance = ModelIndexer.getTopicIndexer();
				List<TopicType> list = new ArrayList<TopicType>();
				list.addAll(instance.getRoleTypes());
				
				for (RoleConstraint rc : getCastedModel().getRoles()) {
					if (rc.getType()!=null)
						list.remove(rc.getType());
				}

				ListSelectionDialog dlg = new ListSelectionDialog(control.getShell(), list, new ArrayContentProvider(),
				        control.new TopicLabelProvider(), "Choose the Role type");

				if (dlg.open() == Dialog.OK) {
					if (dlg.getResult().length == 0)
						return;

					List<RoleConstraint> rcl = new ArrayList<RoleConstraint>();
					for (Object tt : dlg.getResult()) {
						RoleConstraint rc = ModelFactory.eINSTANCE.createRoleConstraint();
						rc.setType((TopicType) tt);
						rc.setCardMin("1");
						rc.setCardMax("1");
						rcl.add(rc);
					}
					AddRoleConstraintCommand cmd = new AddRoleConstraintCommand(getCastedModel(), rcl);
					getCommandStack().execute(cmd);
				}
			}
		});

		control.getNewButton().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				NewTopicTypeWizard wizard = new NewTopicTypeWizard(KindOfTopicType.ROLE_TYPE);
				WizardDialog dlg = new WizardDialog(control.getShell(), wizard);

				if (dlg.open() == Dialog.OK) {
					CompoundCommand cmd = new CompoundCommand();
					TopicType tt = wizard.getNewTopicType();
					cmd.append(new CreateTopicTypeCommand((TopicMapSchema)getCastedModel().eContainer(), tt));
					RoleConstraint rc = ModelFactory.eINSTANCE.createRoleConstraint();
					rc.setType(tt);
					rc.setCardMin("1");
					rc.setCardMax("1");
					cmd.append(new AddRoleConstraintCommand(getCastedModel(), rc));
					if (cmd.canExecute())
						getCommandStack().execute(cmd);
				}
			}
		});

		control.getRemoveButton().addSelectionListener(new SelectionAdapter() {
			@SuppressWarnings("unchecked")
			@Override
			public void widgetSelected(SelectionEvent e) {
				IStructuredSelection sel = (IStructuredSelection) control.getTableViewer().getSelection();

				if (sel.isEmpty())
					return;

				List<RoleConstraint> removeList = new ArrayList<RoleConstraint>();
				Iterator<RoleConstraint> it = sel.iterator();
				while (it.hasNext()) {
					removeList.add(it.next());
				}

				RemoveRoleConstraintCommand cmd = new RemoveRoleConstraintCommand(getCastedModel(), removeList);
				getCommandStack().execute(cmd);
			}
		});

		addButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				NewRoleCombinationConstraintDialog dlg = new NewRoleCombinationConstraintDialog(addButton.getShell(), getCastedModel());
				if (dlg.open() == Dialog.OK) {
					AddRoleCombinationConstraintCommand cmd = new AddRoleCombinationConstraintCommand(getCastedModel(),
					        dlg.getOtherRole());
					getCommandStack().execute(cmd);
				}
			}
		});

		removeButton.addSelectionListener(new SelectionAdapter() {
			@SuppressWarnings("unchecked")
			@Override
			public void widgetSelected(SelectionEvent e) {
				IStructuredSelection sel = (IStructuredSelection) roleCombinationViewer.getSelection();

				if (sel.isEmpty())
					return;

				List<RoleCombinationConstraint> removeList = new ArrayList<RoleCombinationConstraint>();
				for (Iterator it = sel.iterator(); it.hasNext();) {
					removeList.add((RoleCombinationConstraint) it.next());
				}
				RemoveRoleCombinationConstraintCommand cmd = new RemoveRoleCombinationConstraintCommand(
				        getCastedModel(), removeList);
				getCommandStack().execute(cmd);
			}
		});
	}

	@Override
	public void notifyChanged(Notification notification) {
		if (notification.getNotifier() instanceof ScopeConstraint) {
			if (notification.getFeatureID(TopicType.class) == ModelPackage.ROLE_CONSTRAINT__TYPE) {
				if (notification.getOldValue() != null) {
					((TopicType) notification.getOldValue()).eAdapters().remove(this);
				}
				if (notification.getNewValue() != null) {
					((TopicType) notification.getNewValue()).eAdapters().add(this);
				}
			}
			return;
		}
		if (roleCombinationViewer != null)
			roleCombinationViewer.setInput(getCastedModel().getRoleCombinations());

		super.notifyChanged(notification);
	}

	@Override
	public void setModel(Object model) {
		if (getCastedModel() != null) {
			for (RoleConstraint rc : getCastedModel().getRoles()) {
				if (rc.getType() != null)
					rc.getType().eAdapters().remove(this);
				rc.eAdapters().remove(this);
			}
		}

		super.setModel(model);
		if (model == null)
			return;

		for (RoleConstraint rc : getCastedModel().getRoles()) {
			if (rc.getType() != null)
				rc.getType().eAdapters().add(this);
			rc.eAdapters().add(this);
		}
		if (roleCombinationViewer != null)
			roleCombinationViewer.setInput(getCastedModel().getRoleCombinations());
	}

	private Control getRoleCombinationWidget(Composite parent, FormToolkit toolkit) {
		Composite comp = toolkit.createComposite(parent);
		comp.setLayout(new GridLayout(2, false));

		Table table = toolkit.createTable(comp, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLayoutData(new GridData(GridData.FILL_BOTH));
		table.setHeaderVisible(true);
		TableColumn tc = new TableColumn(table, SWT.NONE);
		tc.setText("Role Type");
		tc.setWidth(130);

		tc = new TableColumn(table, SWT.NONE);
		tc.setText("Topic Type");
		tc.setWidth(80);

		tc = new TableColumn(table, SWT.NONE);
		tc.setText("Other Role");
		tc.setWidth(80);

		tc = new TableColumn(table, SWT.NONE);
		tc.setText("Other Topic Type");
		tc.setWidth(80);

		roleCombinationViewer = new TableViewer(table);
		roleCombinationViewer.setContentProvider(new ArrayContentProvider());
		roleCombinationViewer.setLabelProvider(new OtherRoleLabelProvider());
		roleCombinationViewer.setInput(Collections.EMPTY_LIST);

		Composite buttonBar = getButtonBar(comp, toolkit);
		buttonBar.setLayoutData(new GridData(GridData.FILL_VERTICAL));

		return comp;
	}

	@Override
	protected void createItems(CTabFolder folder) {
	    super.createItems(folder);
	    FormToolkit toolkit = new FormToolkit(folder.getDisplay());
	    
	    CTabItem item2 = new CTabItem(folder, SWT.None);
		item2.setText("Role Combination Constraints");
		item2.setControl(getRoleCombinationWidget(folder, toolkit));
		
		hookButtonListeners();
	}
	
	private Composite getButtonBar(Composite parent, FormToolkit toolkit) {
		Composite comp = toolkit.createComposite(parent);
		comp.setLayout(new GridLayout());

		GridData gd = new GridData();
		gd.verticalAlignment = SWT.CENTER;
		gd.widthHint = 80;
		GridDataFactory fac = GridDataFactory.createFrom(gd);

		addButton = toolkit.createButton(comp, "Add..", SWT.PUSH);
		fac.applyTo(addButton);

		removeButton = toolkit.createButton(comp, "Remove", SWT.PUSH);
		fac.applyTo(removeButton);

		return comp;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void updateUI() {
		super.updateUI();
		if (getCastedModel() == null)
			control.setInput((List<? extends AbstractTypedCardinalityConstraint>) Collections.emptyList());
		else
			control.setInput(getCastedModel().getRoles());
		super.updateUI();
	}

	private class OtherRoleLabelProvider implements ITableLabelProvider {

		public Image getColumnImage(Object element, int columnIndex) {
			RoleCombinationConstraint c = (RoleCombinationConstraint) element;
			TopicType type = null;
			switch (columnIndex) {
			case 0:
				type = c.getRole();
				break;
			case 1:
				type = c.getPlayer();
				break;
			case 2:
				type = c.getOtherRole();
				break;
			case 3:
				type = c.getOtherPlayer();
				break;
			}

			return ImageProvider.getTopicTypeImage(type);
		}

		public String getColumnText(Object element, int columnIndex) {
			RoleCombinationConstraint c = (RoleCombinationConstraint) element;
			switch (columnIndex) {
			case 0:
				return c.getRole().getName();
			case 1:
				return c.getPlayer().getName();
			case 2:
				return c.getOtherRole().getName();
			case 3:
				return c.getOtherPlayer().getName();
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
}
