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
package de.topicmapslab.tmcledit.model.actions;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.PlatformUI;

import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.commands.CreateTopicTypeCommand;
import de.topicmapslab.tmcledit.model.dialogs.NewTopicTypeWizard;
import de.topicmapslab.tmcledit.model.views.ModelView;
import de.topicmapslab.tmcledit.model.views.treenodes.TreeObject;

public class CreateTopicAction extends AbstractTreeListenerAction {
	private KindOfTopicType kindOfTopicType;

	/**
	 * @param modelView
	 */
	public CreateTopicAction(ModelView modelView) {
		super(modelView);
		setText("Create Topic Type...");
	}

	public void setKindOfTopicType(KindOfTopicType kindOfTopicType) {
		this.kindOfTopicType = kindOfTopicType;
		if (kindOfTopicType != null)
			setText("Create " + kindOfTopicType.getName());
		setEnabled();
	}

	private void setEnabled() {
		if ((getTreeObject() == null) || (getTreeObject().getModel() != null) || (kindOfTopicType == null))
			setEnabled(false);
		else {
			setEnabled(true);
		}
	}

	@Override
	protected void setTreeObject(TreeObject treeObject) {
		super.setTreeObject(treeObject);
		setEnabled();
	}

	@Override
	public void run() {
		NewTopicTypeWizard wizard = new NewTopicTypeWizard();
		wizard.setDefaultType(kindOfTopicType);

		WizardDialog dlg = new WizardDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), wizard);

		if (dlg.open() == Dialog.OK) {
			getView().getEditingDomain().getCommandStack().execute(
			        new CreateTopicTypeCommand(getView().getCurrentTopicMapSchema(), wizard.getNewTopicType()));
		}

	}
}