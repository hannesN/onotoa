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
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ListSelectionDialog;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.ReifiableTopicType;
import de.topicmapslab.tmcledit.model.ReifierConstraint;
import de.topicmapslab.tmcledit.model.ScopeConstraint;
import de.topicmapslab.tmcledit.model.ScopedTopicType;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.commands.AddScopeConstraintsCommand;
import de.topicmapslab.tmcledit.model.commands.GenericSetCommand;
import de.topicmapslab.tmcledit.model.commands.RemoveScopeConstraintsCommand;
import de.topicmapslab.tmcledit.model.commands.SetCardinalitiesCommand;
import de.topicmapslab.tmcledit.model.dialogs.FilterTopicSelectionDialog;
import de.topicmapslab.tmcledit.model.dialogs.NewTopicTypeWizard;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;
import de.topicmapslab.tmcledit.model.views.widgets.TypedCardinalityConstraintWidget;

/**
 * @author Hannes Niederhausen
 * 
 */
public abstract class ScopedTopicTypePage extends TopicTypePage {

	private TypedCardinalityConstraintWidget control;
	private Text reifiertypeText;
	private Button browseButton;
	private CCombo cardCombo;
	private Button hasReifierConstraintButton;

	public ScopedTopicTypePage(String id) {
		super(id);
	}

	@Override
	protected void createAdditionalControls(Composite parent, FormToolkit toolkit) {

		createReifierControl(parent, toolkit);

		control = new TypedCardinalityConstraintWidget(parent, toolkit, getCommandStack());
		control.setText("scope:");

		hookButtonListeners();
	}

	private void createReifierControl(Composite parent, FormToolkit toolkit) {
		GridData gd = new GridData();
		gd.verticalAlignment = SWT.TOP;
		toolkit.createLabel(parent, "reifier:").setLayoutData(gd);

		Composite comp = toolkit.createComposite(parent);
		GridLayout layout = new GridLayout(3, false);
		comp.setLayout(layout);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		comp.setLayoutData(gd);

		hasReifierConstraintButton = toolkit.createButton(comp, "has reifier constraint", SWT.CHECK);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 3;
		hasReifierConstraintButton.setLayoutData(gd);

		cardCombo = new CCombo(comp, SWT.BORDER);
		cardCombo.setItems(new String[] { "may", "cannot", "must" });
		cardCombo.select(0);
		

		reifiertypeText = toolkit.createText(comp, "", SWT.BORDER | SWT.READ_ONLY);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		reifiertypeText.setLayoutData(gd);
		
		browseButton = toolkit.createButton(comp, "...", SWT.PUSH);

		hookReifierListener();
	}

	private void hookReifierListener() {
		cardCombo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				switch (cardCombo.getSelectionIndex()) {
				case 0:
					setMayReifier();
					break;
				case 1:
					setCannotReifier();
					break;
				case 2:
					setMustHaveReifier();
					break;
				}

			}
		});
		hasReifierConstraintButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (hasReifierConstraintButton.getSelection()) {
					cardCombo.select(0);
					cardCombo.setEnabled(true);
					browseButton.setEnabled(true);
					setMayReifier();
				} else {
					cardCombo.clearSelection();
					cardCombo.setEnabled(false);
					browseButton.setEnabled(false);
					reifiertypeText.setText("");
					getCommandStack().execute(new GenericSetCommand(getReifiableType(), ModelPackage.SCOPED_REIFIABLE_TOPIC_TYPE__REIFIER_CONSTRAINT, null));					
				}
			}
		});
		
		browseButton.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				FilterTopicSelectionDialog dlg = new FilterTopicSelectionDialog(browseButton.getShell(), false);
				if (dlg.open()==Dialog.OK) {
					getCommandStack().execute(
							new GenericSetCommand(getReifiableType().getReifierConstraint(), 
								ModelPackage.REIFIER_CONSTRAINT__TYPE, dlg.getFirstResult()));
				}
			}
		});
	}

	private void setMustHaveReifier() {
		browseButton.setEnabled(true);
		setReifierCardinality(1, 1);
	}

	private void setCannotReifier() {
		browseButton.setEnabled(false);
		setReifierCardinality(0, 0);
	}

	private void setMayReifier() {
		browseButton.setEnabled(true);
		setReifierCardinality(0, 1);
	}

	@Override
	public void setCommandStack(CommandStack commandStack) {
		super.setCommandStack(commandStack);
		if (control != null)
			control.setCommandStack(commandStack);
	}

	private void hookButtonListeners() {
		control.getAddButton().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				ModelIndexer instance = ModelIndexer.getInstance();
				List<TopicType> list = new ArrayList<TopicType>();
				list.addAll(instance.getScopeTypes());

				ListSelectionDialog dlg = new ListSelectionDialog(control.getShell(), list, new ArrayContentProvider(),
				        control.new TopicLabelProvider(), "Choose the Scope type");

				if (dlg.open() == Dialog.OK) {
					if (dlg.getResult().length == 0)
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

		control.getNewButton().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				NewTopicTypeWizard wizard = new NewTopicTypeWizard();
				wizard.setDefaultType(KindOfTopicType.SCOPE_TYPE);
				WizardDialog dlg = new WizardDialog(control.getShell(), wizard);

				if (dlg.open() == Dialog.OK) {
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

		control.getRemoveButton().addSelectionListener(new SelectionAdapter() {
			@SuppressWarnings("unchecked")
			@Override
			public void widgetSelected(SelectionEvent e) {
				IStructuredSelection sel = (IStructuredSelection) control.getTableViewer().getSelection();

				if (sel.isEmpty())
					return;

				List<ScopeConstraint> removeList = new ArrayList<ScopeConstraint>();
				Iterator<ScopeConstraint> it = sel.iterator();
				while (it.hasNext()) {
					removeList.add(it.next());
				}

				RemoveScopeConstraintsCommand cmd = new RemoveScopeConstraintsCommand(getCastedModel(), removeList);
				getCommandStack().execute(cmd);
			}
		});
	}

	@Override
	public void notifyChanged(Notification notification) {
		if (notification.getEventType()==Notification.REMOVING_ADAPTER)
			return;

		if (notification.getNotifier() instanceof ScopeConstraint) {
			if (notification.getFeatureID(TopicType.class) == ModelPackage.SCOPE_CONSTRAINT__TYPE) {
				if (notification.getOldValue() != null) {
					((TopicType) notification.getOldValue()).eAdapters().remove(this);
				}
				if (notification.getNewValue() != null) {
					((TopicType) notification.getNewValue()).eAdapters().add(this);
				}
			}
			return;
		}
		if (notification.getNotifier() instanceof ReifierConstraint) {
			updateReifierUI();
			return;
		}
		if (notification.getNotifier() instanceof ReifiableTopicType) {
			if (notification.getFeatureID(ReifierConstraint.class)==ModelPackage.SCOPED_REIFIABLE_TOPIC_TYPE__REIFIER_CONSTRAINT) {
				ReifierConstraint rc = (ReifierConstraint) notification.getOldValue();
				if (rc!=null)
					rc.eAdapters().remove(this);
				rc = (ReifierConstraint) notification.getNewValue();
				if (rc!=null)
					rc.eAdapters().add(this);
				updateReifierUI();
				return;
			}
		}

		super.notifyChanged(notification);
	}

	private void updateReifierUI() {
		if (getReifiableType()==null) {
			hasReifierConstraintButton.setEnabled(false);
			cardCombo.setEnabled(false);
			browseButton.setEnabled(false);
			reifiertypeText.setEnabled(false);
			return;
		}
		ReifierConstraint rc = getReifiableType().getReifierConstraint();
		hasReifierConstraintButton.setEnabled(true);
		cardCombo.setEnabled(true);
		browseButton.setEnabled(true);
		reifiertypeText.setEnabled(true);
		hasReifierConstraintButton.setSelection(rc!=null);
		if (rc!=null) {
			if (rc.getType()!=null)
				reifiertypeText.setText(rc.getType().getName());
			if (rc.getCardMin().equals("0")) {
				if (rc.getCardMax().equals("0"))
					cardCombo.select(1);
				else
					cardCombo.select(0);
			} else {
				cardCombo.select(2);
			}
		} else {
			cardCombo.setEnabled(false);
			browseButton.setEnabled(false);
		}
			

	}

	@Override
	public void setModel(Object model) {
		if (getCastedModel() != null) {
			for (ScopeConstraint sc : getCastedModel().getScope()) {
				if (sc.getType() != null)
					sc.getType().eAdapters().remove(this);
				sc.eAdapters().remove(this);
			}
			ReifierConstraint rc = getReifiableType().getReifierConstraint();
			if (rc != null) {
				rc.eAdapters().remove(this);
			}

		}

		super.setModel(model);
		if (model == null)
			return;
		for (ScopeConstraint sc : getCastedModel().getScope()) {
			if (sc.getType() != null)
				sc.getType().eAdapters().add(this);
			sc.eAdapters().add(this);
		}
		ReifierConstraint rc = getReifiableType().getReifierConstraint();
		if (rc != null) {
			rc.eAdapters().add(this);
		}
	}

	private ScopedTopicType getCastedModel() {
		return (ScopedTopicType) getModel();
	}

	private void setReifierCardinality(int min, int max) {
		ReifierConstraint rc = getReifiableType().getReifierConstraint();
		if (rc == null) {
			rc = ModelFactory.eINSTANCE.createReifierConstraint();
			rc.setCardMin(Integer.toString(min));
			rc.setCardMax(Integer.toString(max));
			getCommandStack().execute(new GenericSetCommand(
					getReifiableType(), ModelPackage.SCOPED_REIFIABLE_TOPIC_TYPE__REIFIER_CONSTRAINT, rc));
			return;
		}
		
		getCommandStack().execute(new SetCardinalitiesCommand(rc, Integer.toString(min),
				Integer.toString(max)));
	}

	private ReifiableTopicType getReifiableType() {
		return (ReifiableTopicType) getModel();
	}

	@Override
	public void dispose() {
		control.dispose();
	    super.dispose();
	}
	
	@Override
	public void updateUI() {
		super.updateUI();
		if (getCastedModel() != null)
			control.setInput(getCastedModel().getScope());
		else
			control.setInput(null);

		updateReifierUI();		
	}

}