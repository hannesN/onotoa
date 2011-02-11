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
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.TypeNode;

/**
 * Action to add a constraint
 * 
 * @author Hannes Niederhausen
 * 
 */
public abstract class AddConstraintAction extends AbstractSelectionAction {

	/**
	 * Constructor
	 * 
	 * @param commandStack the {@link CommandStack} used to execute commands
	 */
	public AddConstraintAction(CommandStack commandStack) {
		super(commandStack);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void run() {
		getCommandStack().execute(getEmfCommand());
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
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
	
	/**
	 * 
	 * {@inheritDoc}
	 */
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
