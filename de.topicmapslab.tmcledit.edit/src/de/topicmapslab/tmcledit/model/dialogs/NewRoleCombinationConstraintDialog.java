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
import java.util.List;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import de.topicmapslab.tmcledit.model.AssociationType;
import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.RoleCombinationConstraint;
import de.topicmapslab.tmcledit.model.RoleConstraint;
import de.topicmapslab.tmcledit.model.RoleType;
import de.topicmapslab.tmcledit.model.TopicType;

/**
 * @author Hannes Niederhausen
 *
 */
public class NewRoleCombinationConstraintDialog extends Dialog implements DisposeListener {

	private Text roleText;
	private Text playerText;
	private Text otherRoleText;
	private Text otherPlayerText;
	
	private RoleCombinationConstraint roleCombination;
	
	private List<RoleType> possibleRoles;
	
	private Button roleButton;
	private Button playerButton;
	private Button otherRoleButton;
	private Button otherPlayerButton;
	
	private Adapter roleListener = new AdapterImpl() {
		@Override
		public void notifyChanged(Notification msg) 
			{
				validate();
			}
	};
	
	public NewRoleCombinationConstraintDialog(Shell parentShell, AssociationType at) {
		super(parentShell);
		possibleRoles = new ArrayList<RoleType>(at.getRoles().size());
		for (RoleConstraint rc : at.getRoles()) {
			if (rc.getType()!=null)
				possibleRoles.add((RoleType) rc.getType());
		}
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setSize(500, 300);
		newShell.setText("New Role Combination Constraint...");
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		roleCombination = ModelFactory.eINSTANCE.createRoleCombinationConstraint();
		roleCombination.eAdapters().add(roleListener);
		
		Composite comp = new Composite(parent, SWT.NONE);
		comp.setLayoutData(new GridData(GridData.FILL_BOTH));
		comp.setLayout(new GridLayout(3, false));
		
		Label l = new Label(comp, SWT.NONE);
		l.setText("&Role:");
		roleText = new Text(comp, SWT.BORDER|SWT.READ_ONLY);
		roleText.addDisposeListener(this);
		roleText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		roleButton = new Button(comp, SWT.PUSH);
		roleButton.setText("...");
		
		l = new Label(comp, SWT.NONE);
		l.setText("&Player:");
		playerText = new Text(comp, SWT.BORDER|SWT.READ_ONLY);
		playerText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		playerButton = new Button(comp, SWT.PUSH);
		playerButton.setText("...");
		
		l = new Label(comp, SWT.NONE);
		l.setText("&Other Role:");
		otherRoleText = new Text(comp, SWT.BORDER|SWT.READ_ONLY);
		otherRoleText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		otherRoleButton = new Button(comp, SWT.PUSH);
		otherRoleButton.setText("...");
		
		l = new Label(comp, SWT.NONE);
		l.setText("O&ther Player:");
		otherPlayerText = new Text(comp, SWT.BORDER|SWT.READ_ONLY);
		otherPlayerText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		otherPlayerButton = new Button(comp, SWT.PUSH);
		otherPlayerButton.setText("...");
		
		hookButtonListeners();
		
		validate();
		return super.createDialogArea(parent);
	}


	private void validate() {
		boolean finished = true;
		if (roleCombination.getRole()==null)
			finished = false;
		else if (roleCombination.getPlayer()==null)
			finished = false;
		else if (roleCombination.getOtherRole()==null)
			finished = false;
		else if (roleCombination.getOtherPlayer()==null)
			finished = false;
		else if (roleCombination.getRole().equals(roleCombination.getOtherRole()))
			finished = false;
		
		
		if (getButton(IDialogConstants.OK_ID)!=null)
			getButton(IDialogConstants.OK_ID).setEnabled(finished);
	}

	public RoleCombinationConstraint getOtherRole() {
		return roleCombination;
	}
	
	public void widgetDisposed(DisposeEvent e) {
		roleCombination.eAdapters().remove(roleListener);
	}

	private void hookButtonListeners() {
		roleButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FilterTopicSelectionDialog dlg = new FilterTopicSelectionDialog(
						roleButton.getShell(), KindOfTopicType.ROLE_TYPE);
				if (dlg.open()==Dialog.OK) {
					roleCombination.setRole((RoleType) dlg.getFirstResult());
					roleText.setText(roleCombination.getRole().getName());
				}
			}
		});
		playerButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FilterTopicSelectionDialog dlg = new FilterTopicSelectionDialog(
						roleButton.getShell(), KindOfTopicType.TOPIC_TYPE);
				if (dlg.open()==Dialog.OK) {
					roleCombination.setPlayer( (TopicType) dlg.getFirstResult());
					playerText.setText(roleCombination.getPlayer().getName());
				}
			}
		});
		
		otherPlayerButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FilterTopicSelectionDialog dlg = new FilterTopicSelectionDialog(
						roleButton.getShell(), KindOfTopicType.TOPIC_TYPE);
				if (dlg.open()==Dialog.OK) {
					TopicType tt = (TopicType) dlg.getFirstResult();
					if (tt.equals(roleCombination.getPlayer()))
						return;
					roleCombination.setOtherPlayer( (TopicType) dlg.getFirstResult());
					otherPlayerText.setText(roleCombination.getOtherPlayer().getName());
				}
			}
		});
		otherRoleButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FilterTopicSelectionDialog dlg = new FilterTopicSelectionDialog(
						roleButton.getShell(), KindOfTopicType.ROLE_TYPE);
				if (dlg.open()==Dialog.OK) {
					RoleType rt = (RoleType) dlg.getFirstResult();
					roleCombination.setOtherRole(rt);
					otherRoleText.setText(roleCombination.getOtherRole().getName());
				}
			}
		});
	}
	
}
