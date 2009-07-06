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
package de.topicmapslab.tmcledit.extensions.views.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
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
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import de.topicmapslab.tmcledit.model.OtherRolePlayerConstraint;
import de.topicmapslab.tmcledit.model.RoleType;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.commands.AddOtherRolePlayerConstraintCommand;
import de.topicmapslab.tmcledit.model.commands.RemoveOtherRolePlayerConstraintCommand;
import de.topicmapslab.tmcledit.model.dialogs.NewOtherRoleConstraintDialog;
import de.topicmapslab.tmcledit.model.util.ImageProvider;

/**
 * @author Hannes Niederhausen
 *
 */
public class RoleTypeModelPage extends TopicTypePage {
	private TableViewer otherRoleViewer;

	private Button addButton;
	private Button removeButton;
	
	public RoleTypeModelPage() {
		super("role type");
	}

	protected void createItems(CTabFolder folder) {
		super.createItems(folder);
		FormToolkit toolkit = new FormToolkit(folder.getDisplay());
		
		item.setText("Role Type");
		
		CTabItem item2 = new CTabItem(folder, SWT.None);
		item2.setText("Other Role Constraints");
		item2.setControl(getOtherRoleWidget(folder, toolkit));
		
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
		
		hookButtonListeners();
		return comp;
	}
	
	private Control getOtherRoleWidget(Composite parent, FormToolkit toolkit) {
		Section section = toolkit.createSection(parent, Section.TITLE_BAR|Section.EXPANDED);
		section.setText("Other Role Constraints");
		Composite comp = toolkit.createComposite(section);
		comp.setLayout(new GridLayout(2, false));
		
		Table table = toolkit.createTable(comp, SWT.BORDER|SWT.FULL_SELECTION);
		table.setLayoutData(new GridData(GridData.FILL_BOTH));
		table.setHeaderVisible(true);
		TableColumn tc = new TableColumn(table, SWT.NONE);
		tc.setText("Association");
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
		
		
		otherRoleViewer = new TableViewer(table);
		otherRoleViewer.setContentProvider(new ArrayContentProvider());
		otherRoleViewer.setLabelProvider(new OtherRoleLabelProvider());
		otherRoleViewer.setInput(Collections.EMPTY_LIST);

		Composite buttonBar = getButtonBar(comp, toolkit);
		buttonBar.setLayoutData(new GridData(GridData.FILL_VERTICAL));
		
		section.setClient(comp);
		return section;
	}
	
	
	
	private void hookButtonListeners() {
		addButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				NewOtherRoleConstraintDialog dlg = new NewOtherRoleConstraintDialog(addButton.getShell());
				if (dlg.open()==Dialog.OK) {
					AddOtherRolePlayerConstraintCommand cmd = new AddOtherRolePlayerConstraintCommand(getCastedModel(), 
							dlg.getOtherRole());
					getCommandStack().execute(cmd);
				}
			}
		});
		
		removeButton.addSelectionListener(new SelectionAdapter() {
			@SuppressWarnings("unchecked")
			@Override
			public void widgetSelected(SelectionEvent e) {
				IStructuredSelection sel = (IStructuredSelection) otherRoleViewer.getSelection();
				
				if (sel.isEmpty())
					return;
				
				List<OtherRolePlayerConstraint> removeList = new ArrayList<OtherRolePlayerConstraint>();
				for (Iterator it = sel.iterator(); it.hasNext();) {
					removeList.add((OtherRolePlayerConstraint) it.next());
				}
				RemoveOtherRolePlayerConstraintCommand cmd = new RemoveOtherRolePlayerConstraintCommand(
						getCastedModel(), removeList);
				getCommandStack().execute(cmd);
			}
		});
	}
	
	@Override
	public void setModel(Object model) {
		super.setModel(model);
		if (otherRoleViewer!=null)
			otherRoleViewer.setInput(getCastedModel().getOtherRoles());
	}

	private RoleType getCastedModel() {
		return (RoleType) getModel();
	}
	
	@Override
	public void notifyChanged(Notification notification) {
		super.notifyChanged(notification);
		if (otherRoleViewer!=null)
			otherRoleViewer.setInput(getCastedModel().getOtherRoles());
	}
	
	private class OtherRoleLabelProvider implements ITableLabelProvider {

		public Image getColumnImage(Object element, int columnIndex) {
			OtherRolePlayerConstraint c = (OtherRolePlayerConstraint) element;
			TopicType type = null;
			switch (columnIndex) {
			case 0:
				type = c.getAssociationType(); 
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
			OtherRolePlayerConstraint c = (OtherRolePlayerConstraint) element;
			switch (columnIndex) {
			case 0:
				return c.getAssociationType().getName();
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
