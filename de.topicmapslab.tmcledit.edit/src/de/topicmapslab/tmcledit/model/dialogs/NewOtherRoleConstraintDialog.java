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

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
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
import de.topicmapslab.tmcledit.model.OtherRolePlayerConstraint;
import de.topicmapslab.tmcledit.model.RoleType;
import de.topicmapslab.tmcledit.model.TopicType;

/**
 * @author Hannes Niederhausen
 *
 */
public class NewOtherRoleConstraintDialog extends Dialog implements DisposeListener {

	private Text associationText;
	private Text playerText;
	private Text otherRoleText;
	private Text otherPlayerText;
	private Text cardMinText;
	private Text cardMaxText;
	
	private OtherRolePlayerConstraint otherRole;
	private Button assButton;
	private Button playerButton;
	private Button otherRoleButton;
	private Button otherPlayerButton;
	
	private Adapter roleListener = new AdapterImpl() {
		public void notifyChanged(Notification msg) 
			{
				validate();
			}
	};
	
	public NewOtherRoleConstraintDialog(Shell parentShell) {
		super(parentShell);
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setSize(500, 300);
		newShell.setText("New OtherRolePlayerConstraint...");
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		otherRole = ModelFactory.eINSTANCE.createOtherRolePlayerConstraint();
		otherRole.eAdapters().add(roleListener);
		
		Composite comp = new Composite(parent, SWT.NONE);
		comp.setLayoutData(new GridData(GridData.FILL_BOTH));
		comp.setLayout(new GridLayout(3, false));
		
		Label l = new Label(comp, SWT.NONE);
		l.setText("&Association:");
		associationText = new Text(comp, SWT.BORDER|SWT.READ_ONLY);
		associationText.addDisposeListener(this);
		associationText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		assButton = new Button(comp, SWT.PUSH);
		assButton.setText("...");
		
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
		
		l = new Label(comp, SWT.NONE);
		l.setText("&cardMin:");
		cardMinText = new Text(comp, SWT.BORDER);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		cardMinText.setLayoutData(gd);
		
		
		l = new Label(comp, SWT.NONE);
		l.setText("&cardMax:");
		cardMaxText = new Text(comp, SWT.BORDER);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		cardMaxText.setLayoutData(gd);	
		
		hookTextListeners();
		hookButtonListeners();
		
		validate();
		return super.createDialogArea(parent);
	}


	private void validate() {
		boolean finished = true;
		if (otherRole.getAssociationType()==null)
			finished = false;
		else if (otherRole.getPlayer()==null)
			finished = false;
		else if (otherRole.getOtherRole()==null)
			finished = false;
		else if (otherRole.getOtherPlayer()==null)
			finished = false;
		
		if (getButton(IDialogConstants.OK_ID)!=null)
			getButton(IDialogConstants.OK_ID).setEnabled(finished);
		
		if ("*".equals(cardMaxText.getText()))
			return;
		
		int cardMin = Integer.parseInt(otherRole.getCardMin());
		int cardMax = Integer.parseInt(otherRole.getCardMax());

		if (cardMin>cardMax) {
			otherRole.setCardMax(otherRole.getCardMin());
			cardMaxText.setText(cardMinText.getText());
		}
		
		
	}

	public OtherRolePlayerConstraint getOtherRole() {
		return otherRole;
	}
	
	@Override
	public void widgetDisposed(DisposeEvent e) {
		otherRole.eAdapters().remove(roleListener);
	}

	private void hookButtonListeners() {
		assButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FilterTopicSelectionDialog dlg = new FilterTopicSelectionDialog(
						assButton.getShell(), KindOfTopicType.ASSOCIATION_TYPE);
				if (dlg.open()==Dialog.OK) {
					otherRole.setAssociationType((AssociationType) dlg.getFirstResult());
					associationText.setText(otherRole.getAssociationType().getName());
				}
			}
		});
		playerButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FilterTopicSelectionDialog dlg = new FilterTopicSelectionDialog(
						assButton.getShell(), KindOfTopicType.TOPIC_TYPE);
				if (dlg.open()==Dialog.OK) {
					otherRole.setPlayer( (TopicType) dlg.getFirstResult());
					playerText.setText(otherRole.getPlayer().getName());
				}
			}
		});
		
		otherPlayerButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FilterTopicSelectionDialog dlg = new FilterTopicSelectionDialog(
						assButton.getShell(), KindOfTopicType.TOPIC_TYPE);
				if (dlg.open()==Dialog.OK) {
					TopicType tt = (TopicType) dlg.getFirstResult();
					if (tt.equals(otherRole.getPlayer()))
						return;
					otherRole.setOtherPlayer( (TopicType) dlg.getFirstResult());
					otherPlayerText.setText(otherRole.getOtherPlayer().getName());
				}
			}
		});
		otherRoleButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FilterTopicSelectionDialog dlg = new FilterTopicSelectionDialog(
						assButton.getShell(), KindOfTopicType.ROLE_TYPE);
				if (dlg.open()==Dialog.OK) {
					RoleType rt = (RoleType) dlg.getFirstResult();
					otherRole.setOtherRole(rt);
					otherRoleText.setText(otherRole.getOtherRole().getName());
				}
			}
		});
	}
	
	private void hookTextListeners() {
		cardMinText.addVerifyListener(new NumberVerifyListener(false));
		cardMaxText.addVerifyListener(new NumberVerifyListener(true));
		
		cardMinText.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				otherRole.setCardMin(cardMinText.getText());
				validate();
			}
		});
		cardMaxText.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				otherRole.setCardMax(cardMaxText.getText());
				validate();
			}
		});
	}

	private class NumberVerifyListener implements VerifyListener {

		private final boolean isMax;
		
		public NumberVerifyListener(boolean isMax) {
			this.isMax = isMax;
		}
		
		@Override
		public void verifyText(VerifyEvent e) {
			if (isMax) {
				Text text = (Text) e.widget;
				if ( (text.getText().length()==0) && (e.text.equals("*")) )
					return;
			}
			for (int i=0; i<e.text.length(); i++) {
				if (!Character.isDigit(e.text.charAt(i)))
					e.doit = false;
			}
		}
		
	}
}
