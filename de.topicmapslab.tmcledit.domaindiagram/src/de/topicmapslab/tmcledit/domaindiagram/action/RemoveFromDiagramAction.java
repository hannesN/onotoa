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
package de.topicmapslab.tmcledit.domaindiagram.action;

import java.util.Iterator;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import de.topicmapslab.tmcledit.domaindiagram.command.CommandAdapter;
import de.topicmapslab.tmcledit.domaindiagram.editor.DomainEditDomain;
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
		DomainEditDomain ed = null;

		CompoundCommand cmd = new CompoundCommand();
		Iterator<Object> it = getSelections().iterator();
		while (it.hasNext()) {
			EditPart part = (EditPart) it.next();
			if (ed == null)
				ed = (DomainEditDomain) part.getViewer().getEditDomain();
			if (part instanceof de.topicmapslab.tmcledit.domaindiagram.editparts.NodeEditPart) {
				Node node = (Node) part.getModel();
				cmd.append(new RemoveNodeCommand((Diagram) node.eContainer(),
						node));
			}
		}

		getCommandStack().execute(new CommandAdapter(ed.getEditingDomain()
				.getCommandStack(), cmd));

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
				if (!(it.next() instanceof de.topicmapslab.tmcledit.domaindiagram.editparts.NodeEditPart)) {
					setEnabled(false);
					return;
				}
			}
		} else {
			setEnabled(false);
		}
	}

}
