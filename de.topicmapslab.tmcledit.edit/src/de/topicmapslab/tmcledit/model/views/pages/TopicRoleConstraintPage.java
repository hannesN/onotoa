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
package de.topicmapslab.tmcledit.model.views.pages;

import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.topicmapslab.tmcledit.model.AssociationType;
import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.RoleConstraint;
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;
import de.topicmapslab.tmcledit.model.TmcleditEditPlugin;
import de.topicmapslab.tmcledit.model.commands.SetRoleConstraintCommand;
import de.topicmapslab.tmcledit.model.util.CardTextObserver;

public class TopicRoleConstraintPage extends AbstractEMFModelPage {
	private Label playerLabel;
	private Text cardMinText;
	private Text cardMaxText;
	private CCombo roleCombo;
	private CTabItem item;

	private AssociationTypeModelPage assPage;

	public TopicRoleConstraintPage() {
		super("role");
	}

	@Override
	public void updateUI() {
		RolePlayerConstraint rpc = getCastedModel();

		updateCombo();

		if (rpc==null) {
			cardMinText.setText("");
			cardMaxText.setText("");
			playerLabel.setText("");	
		} else {
			cardMinText.setText(rpc.getCardMin());
			cardMaxText.setText(rpc.getCardMax());
			playerLabel.setText(rpc.getPlayer().getName());	
		}
		
		super.updateUI();
	}

	@Override
	public void aboutToHide() {
		setModel(null);
	}
	
	@Override
	protected void createItems(CTabFolder folder) {
		super.createItems(folder);
		FormToolkit toolkit = new FormToolkit(folder.getDisplay());

		item = new CTabItem(folder, SWT.NONE);
		item.setText("Topic Role Constraint");
		Composite comp = createRoleConstraintProps(folder, toolkit);

		item.setControl(comp);

		assPage = new AssociationTypeModelPage();
		assPage.createItems(folder);
	}

	@Override
	protected void setEnabled(boolean enabled) {
		item.getControl().setEnabled(enabled);
	}

	@Override
	public void dispose() {
		assPage.dispose();
		super.dispose();
	}

	@Override
	public void setCommandStack(CommandStack commandStack) {
		super.setCommandStack(commandStack);
		assPage.setCommandStack(commandStack);
	}

	private Composite createRoleConstraintProps(Composite parent, FormToolkit toolkit) {

		Composite comp = toolkit.createComposite(parent);
		GridLayout gridLayout = new GridLayout(2, false);
		gridLayout.marginWidth = 5;
		gridLayout.marginHeight = 5;
		gridLayout.verticalSpacing = 0;
		gridLayout.horizontalSpacing = 2;
		comp.setLayout(gridLayout);

		toolkit.createLabel(comp, "Player:");
		playerLabel = toolkit.createLabel(comp, "", SWT.READ_ONLY | SWT.BORDER | SWT.NO_FOCUS);
		playerLabel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		playerLabel.setCapture(false);
		playerLabel.setBackground(comp.getDisplay().getSystemColor(SWT.COLOR_WHITE));

		toolkit.createLabel(comp, "Role:");
		roleCombo = new CCombo(comp, SWT.BORDER);
		roleCombo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		toolkit.adapt(roleCombo);

		roleCombo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int index = roleCombo.getSelectionIndex();
				if (index > -1) {
					RoleConstraint rc = getAssociationType().getRoles().get(index);
					CompoundCommand ccmd = new CompoundCommand();

					SetRoleConstraintCommand cmd = new SetRoleConstraintCommand(getCastedModel(), rc);
					ccmd.append(cmd);

					getCommandStack().execute(ccmd);
				}
			}
		});

		toolkit.createLabel(comp, "cardMin:");
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		cardMinText = toolkit.createText(comp, "", SWT.BORDER);
		cardMinText.setLayoutData(gd);
		CardTextObserver.observe(cardMinText, this, true);

		toolkit.createLabel(comp, "cardMax:");
		cardMaxText = toolkit.createText(comp, "", SWT.BORDER);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		cardMaxText.setLayoutData(gd);
		CardTextObserver.observe(cardMaxText, this, false);

		return comp;
	}

	private void updateCombo() {
		int index = -1;
		int i = 0;
		roleCombo.removeAll();
		if (getAssociationType() == null)
			return;
		for (RoleConstraint rc : getAssociationType().getRoles()) {
			roleCombo.add(rc.getType().getName());
			if (rc.equals(getCastedModel().getRole()))
				index = i;
			i++;
		}
		if (index > -1)
			roleCombo.select(index);
	}

	protected RolePlayerConstraint getCastedModel() {
		return (RolePlayerConstraint) getModel();
	}

	protected AssociationType getAssociationType() {
		try {
			if (getModel()==null)
				return null;
			return (AssociationType) ((AssociationTypeConstraint) getCastedModel().eContainer()).getType();
		} catch (Exception e) {
			TmcleditEditPlugin.logError(e);
		}
		return (AssociationType) ((AssociationTypeConstraint) getCastedModel().eContainer()).getType();
	}

	public void notifyChanged(Notification notification) {
		if (notification.getEventType() == Notification.REMOVING_ADAPTER)
			return;
		
		if (notification.getEventType() == Notification.SET) {
			if (notification.getNotifier().equals(getCastedModel().eContainer())) {
				AssociationType at = (AssociationType) notification.getOldValue();
				if (at != null)
					at.eAdapters().remove(this);
				
				at = (AssociationType) notification.getNewValue();
				if (at != null)
					at.eAdapters().add(this);
					
			}
			
		}
		
		updateUI();
	}

	@Override
	public void setModel(Object model) {
		RolePlayerConstraint rpc = (RolePlayerConstraint) getCastedModel();

		if (rpc != null) {
			if (rpc.getRole() != null)
				rpc.getRole().eAdapters().remove(this);

			if (rpc.getPlayer() != null)
				rpc.getPlayer().eAdapters().remove(this);
			
			AssociationTypeConstraint atc = (AssociationTypeConstraint) rpc.eContainer();
			atc.eAdapters().remove(this);
			if (atc.getType()!=null) {
				atc.getType().eAdapters().remove(this);
			}
		}

		super.setModel(model);

		if (model==null) {
			assPage.setModel(null);
			return;
		}
		
		rpc = (RolePlayerConstraint) model;

		if (rpc.getRole() != null)
			rpc.getRole().eAdapters().add(this);

		rpc.getPlayer().eAdapters().add(this);
		
		AssociationTypeConstraint atc = (AssociationTypeConstraint) rpc.eContainer();
		atc.eAdapters().add(this);
		if (atc.getType()!=null) {
			atc.getType().eAdapters().add(this);
		}

		if (assPage != null)
			assPage.setModel(getAssociationType());
	}

}
