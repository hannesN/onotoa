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
package de.topicmapslab.tmcledit.diagram.action;

import org.eclipse.emf.common.command.Command;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import de.topicmapslab.tmcledit.diagram.command.CommandAdapter;
import de.topicmapslab.tmcledit.diagram.editor.TMCLEditDomain;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.TypeNode;

/**
 * @author Hannes Niederhausen
 * 
 */
public abstract class AddConstraintAction extends AbstractSelectionAction {

	public AddConstraintAction(CommandStack commandStack) {
		super(commandStack);
	}

	@Override
	public void run() {
		EditPart selectedEditPart = (EditPart) getSelections()
				.getFirstElement();
		TMCLEditDomain ed = null;
		if (ed == null)
			ed = (TMCLEditDomain) selectedEditPart.getViewer().getEditDomain();

		getCommandStack().execute(
				new CommandAdapter(ed.getEditingDomain().getCommandStack(),
						getEmfCommand()));
	}

	public void update() {
		if ((getSelections().size() == 1)
				&& (getSelections().getFirstElement() instanceof EditPart)
				&& (isValidModel(((EditPart) getSelections().getFirstElement())
						.getModel()))) {
			setEnabled(true);
		} else {
			setEnabled(false);
		}
	}
	
	@Override
	public ImageDescriptor getImageDescriptor() {
		ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
		return sharedImages.getImageDescriptor(ISharedImages.IMG_OBJ_ADD);
	}
	
	private boolean isValidModel(Object model) {
		return (model instanceof TypeNode);
	}

	protected TopicType getSelectedTopicType() {
		EditPart ep = (EditPart) getSelections().getFirstElement();
		TypeNode node = (TypeNode) ep.getModel();
		return node.getTopicType();
	}

	protected abstract Command getEmfCommand();
}
