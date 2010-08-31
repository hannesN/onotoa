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

import java.util.Iterator;

import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.Node;
import de.topicmapslab.tmcledit.model.commands.RemoveNodeCommand;

public class RemoveFromDiagramAction extends AbstractSelectionAction {

	public final static String ID = "de.topicmapslab.tmcleditor.removefromdiagram";


	public RemoveFromDiagramAction(CommandStack commandStack) {
		super(commandStack);
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		ISharedImages sharedImages = PlatformUI.getWorkbench()
				.getSharedImages();
		return sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void run() {
		EditDomain ed = null;

		CompoundCommand cmd = new CompoundCommand();
		Iterator<Object> it = getSelections().iterator();
		while (it.hasNext()) {
			EditPart part = (EditPart) it.next();
			if (ed == null)
				ed =  part.getViewer().getEditDomain();
			if (part.getModel() instanceof Node) {
				Node node = (Node) part.getModel();
				cmd.append(new RemoveNodeCommand((Diagram) node.eContainer(),
						node));
			}
		}

		getCommandStack().execute(cmd);

	}

	@Override
	public String getText() {
		return "Remove from Diagram";
	}

	@Override
	public String getId() {
		return ID;
	}

	@SuppressWarnings("unchecked")
	public void update() {
		setEnabled(true);
		if (!getSelections().isEmpty()) {
			Iterator<Object> it = getSelections().iterator();
			while (it.hasNext()) {
				EditPart ep = (EditPart) it.next();
				if (!(ep.getModel() instanceof Node)) {
					setEnabled(false);
					return;
				}
			}
		} else {
			setEnabled(false);
		}
	}

}
