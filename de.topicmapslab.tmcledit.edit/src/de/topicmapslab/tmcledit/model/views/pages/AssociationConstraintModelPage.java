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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Hyperlink;

import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.commands.CreateTopicTypeCommand;
import de.topicmapslab.tmcledit.model.commands.SetAssociationTypeCommand;
import de.topicmapslab.tmcledit.model.dialogs.FilterTopicSelectionDialog;
import de.topicmapslab.tmcledit.model.dialogs.NewTopicTypeWizard;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;

/**
 * 
 * @author Hannes Niederhausen
 * 
 */
public class AssociationConstraintModelPage extends AbstractEMFModelPage {

	private Text typeText;
	private CTabItem item;

	private AssociationTypeModelPage typeModelPage;

	/**
	 * Constructor
	 */
	public AssociationConstraintModelPage() {
		super("association_constraint");
	}

	@Override
	public void updateUI() {
		super.updateUI();
		AssociationTypeConstraint asc = getCastedModel();
		if (asc.getType() == null) {
			typeText.setText("");
		} else {
			typeText.setText(asc.getType().getName());
		}
	}

	private AssociationTypeConstraint getCastedModel() {
		return (AssociationTypeConstraint) getModel();
	}

	@Override
	public void dispose() {
		typeModelPage.dispose();
	    super.dispose();
	}
	
	@Override
	protected void createItems(CTabFolder folder) {
		super.createItems(folder);
		FormToolkit toolkit = new FormToolkit(folder.getDisplay());

		item = new CTabItem(folder, SWT.NONE);
		item.setText("Association Constraint");
		item.setControl(createConstraintSection(folder, toolkit));

		typeModelPage = new AssociationTypeModelPage();
		typeModelPage.createItems(folder);
		
	}

	private Composite createConstraintSection(Composite parent,
			FormToolkit toolkit) {
		Composite comp = toolkit.createComposite(parent);
		comp.setLayout(new GridLayout(3, false));


		Hyperlink link = toolkit
				.createHyperlink(comp, "Type:", SWT.NONE);
		link.addHyperlinkListener(new HyperlinkAdapter() {
			@Override
			public void linkActivated(HyperlinkEvent e) {
				NewTopicTypeWizard wizard = new NewTopicTypeWizard(KindOfTopicType.ASSOCIATION_TYPE);
				WizardDialog dlg = new WizardDialog(typeText.getShell(), wizard);

				if (dlg.open() == Dialog.OK) {
					TopicType tt = wizard.getNewTopicType();
					CompoundCommand cmd = new CompoundCommand();
					CreateTopicTypeCommand c1 = new CreateTopicTypeCommand(
							ModelIndexer.getInstance().getTopicMapSchema(), tt);
					cmd.append(c1);
					SetAssociationTypeCommand c2 = new SetAssociationTypeCommand(
							getCastedModel(), tt);
					cmd.append(c2);
					getCommandStack().execute(cmd);
				}

			}
		});

		typeText = toolkit.createText(comp, "", SWT.BORDER|SWT.READ_ONLY);
		typeText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Button button = toolkit.createButton(comp, "...", SWT.PUSH);
		hookAddTypeButtonListeners(button);

		return comp;
	}

	private void hookAddTypeButtonListeners(Button button) {
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FilterTopicSelectionDialog dlg = new FilterTopicSelectionDialog(
						typeText.getShell(), KindOfTopicType.ASSOCIATION_TYPE);

				if (dlg.open() == Dialog.OK) {
					TopicType tt = ((TopicType) dlg.getFirstResult());
					SetAssociationTypeCommand cmd = new SetAssociationTypeCommand(
							getCastedModel(), tt);
					getCommandStack().execute(cmd);
				}

			}
		});
	}

	@Override
	public void setModel(Object model) {
		if (getModel() != null) {
			TopicType modelType = getCastedModel().getType();
			if (modelType != null)
				modelType.eAdapters().remove(this);
		}
		super.setModel(model);
		typeModelPage.setModel(getCastedModel().getType());
		typeModelPage.getItem().setText("Association Type");

		if (getModel() == null) {
			return;
		}
		TopicType modelType = getCastedModel().getType();
		if (modelType != null)
			modelType.eAdapters().add(this);
	}
	
	@Override
	protected void setEnabled(boolean enabled) {
		item.getControl().setEnabled(enabled);
	}

	@Override
	public void setCommandStack(CommandStack commandStack) {
		super.setCommandStack(commandStack);
		typeModelPage.setCommandStack(commandStack);
	}

	public void notifyChanged(Notification notification) {
		if (notification.getEventType() == Notification.REMOVING_ADAPTER) {
			return;
		}
		if (notification.getEventType() == Notification.SET) {
			if (notification.getFeatureID(TopicType.class) == ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__TYPE) {
				if (notification.getOldValue() != null)
					((EObject) notification.getOldValue()).eAdapters().remove(
							this);

				if (notification.getNewValue() != null)
					((EObject) notification.getNewValue()).eAdapters()
							.add(this);

				typeModelPage.setModel(getCastedModel().getType());
			}
		}
		updateUI();
	}
}
