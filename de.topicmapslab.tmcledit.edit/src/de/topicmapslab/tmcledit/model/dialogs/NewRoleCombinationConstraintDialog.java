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

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
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
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.RoleCombinationConstraint;
import de.topicmapslab.tmcledit.model.RoleConstraint;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.commands.AddRoleCombinationConstraintCommand;
import de.topicmapslab.tmcledit.model.commands.GenericSetCommand;

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
	
	private List<TopicType> possibleRoles;
	
	private Button roleButton;
	private Button playerButton;
	private Button otherRoleButton;
	private Button otherPlayerButton;
	
	private TopicType role;
	private TopicType player;
	private TopicType otherRole;
	private TopicType otherPlayer;
	
	private AssociationType at;
	
	
	public NewRoleCombinationConstraintDialog(Shell parentShell, AssociationType at) {
		super(parentShell);
		possibleRoles = new ArrayList<TopicType>(at.getRoles().size());
		this.at = at;
		for (RoleConstraint rc : at.getRoles()) {
			if (rc.getType()!=null)
				possibleRoles.add((TopicType) rc.getType());
		}
	}
	
	public NewRoleCombinationConstraintDialog(Shell parentShell, AssociationType at, RoleCombinationConstraint rcc) {
		this(parentShell, at);
		this.roleCombination = rcc;
		role = rcc.getRole();
		otherRole = rcc.getOtherRole();
		player = rcc.getPlayer();
		otherPlayer = rcc.getOtherPlayer();
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setSize(500, 300);
		newShell.setText("New Role Combination Constraint...");
	}

	@Override
	protected Control createDialogArea(Composite parent) {
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
		
		// filling textfields
		if (roleCombination!=null) {
			roleText.setText(roleCombination.getRole().getName());
			otherRoleText.setText(roleCombination.getOtherRole().getName());
			playerText.setText(roleCombination.getPlayer().getName());
			otherPlayerText.setText(roleCombination.getOtherPlayer().getName());
			
			role = roleCombination.getRole();
			otherRole = roleCombination.getOtherRole();
			player = roleCombination.getPlayer();
			otherPlayer = roleCombination.getOtherPlayer();
			
			
		} 
		
		validate();
		return super.createDialogArea(parent);
	}


	private void validate() {
		boolean finished = true;
		if (roleCombination==null)
			finished = false;
		else if (role==null)
			finished = false;
		else if (player==null)
			finished = false;
		else if (otherRole==null)
			finished = false;
		else if (otherPlayer==null)
			finished = false;
		
		if (getButton(IDialogConstants.OK_ID)!=null)
			getButton(IDialogConstants.OK_ID).setEnabled(finished);
	}

	public Command getCreateCommand() {
		roleCombination = ModelFactory.eINSTANCE.createRoleCombinationConstraint();
		roleCombination.setPlayer(player);
		roleCombination.setRole(role);
		roleCombination.setOtherPlayer(otherPlayer);
		roleCombination.setOtherRole(otherRole);
		
		return new AddRoleCombinationConstraintCommand(at, roleCombination);
	}
	
	public Command getModifyCommand() {
		CompoundCommand cmd = new CompoundCommand();
		
		if (roleCombination==null)
			cmd.append(getCreateCommand());
		else {
			if (!roleCombination.getPlayer().equals(player)) {
				cmd.append(new GenericSetCommand(roleCombination, ModelPackage.ROLE_COMBINATION_CONSTRAINT__PLAYER, player));
			}
			if (!roleCombination.getOtherPlayer().equals(otherPlayer)) {
				cmd.append(new GenericSetCommand(roleCombination, ModelPackage.ROLE_COMBINATION_CONSTRAINT__OTHER_PLAYER, otherPlayer));
			}
			if (!roleCombination.getRole().equals(role)) {
				cmd.append(new GenericSetCommand(roleCombination, ModelPackage.ROLE_COMBINATION_CONSTRAINT__ROLE, role));
			}
			if (!roleCombination.getOtherRole().equals(otherRole)) {
				cmd.append(new GenericSetCommand(roleCombination, ModelPackage.ROLE_COMBINATION_CONSTRAINT__OTHER_ROLE, otherRole));
			}
		}
		return cmd;		
	}
	
	public void widgetDisposed(DisposeEvent e) {
	}

	private void hookButtonListeners() {
		roleButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FilterTopicSelectionDialog dlg = new FilterTopicSelectionDialog(
						roleButton.getShell(), KindOfTopicType.TOPIC_TYPE, KindOfTopicType.ROLE_TYPE);
				if (dlg.open()==Dialog.OK) {
					TopicType rt =  (TopicType) dlg.getFirstResult();

					role = rt;
					roleText.setText(role.getName());
				}
			}
		});
		playerButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FilterTopicSelectionDialog dlg = new FilterTopicSelectionDialog(
						roleButton.getShell(), KindOfTopicType.TOPIC_TYPE);
				if (dlg.open()==Dialog.OK) {
					TopicType p = (TopicType) dlg.getFirstResult();
					player = p;
					playerText.setText(player.getName());
				}
			}
		});
		
		otherPlayerButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FilterTopicSelectionDialog dlg = new FilterTopicSelectionDialog(
						roleButton.getShell(), KindOfTopicType.TOPIC_TYPE);
				if (dlg.open()==Dialog.OK) {
					TopicType op = (TopicType) dlg.getFirstResult();
					
					otherPlayer = op;
					otherPlayerText.setText(op.getName());
				}
			}
		});
		otherRoleButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FilterTopicSelectionDialog dlg = new FilterTopicSelectionDialog(
						roleButton.getShell(), KindOfTopicType.TOPIC_TYPE, KindOfTopicType.ROLE_TYPE);
				if (dlg.open()==Dialog.OK) {
					TopicType rt = (TopicType) dlg.getFirstResult();
					
					otherRole = rt;
					otherRoleText.setText(otherRole.getName());
				}
			}
		});
	}
	
}
