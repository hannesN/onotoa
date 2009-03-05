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

import org.eclipse.gef.EditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.ui.actions.UpdateAction;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import de.topicmapslab.tmcledit.diagram.command.CommandAdapter;
import de.topicmapslab.tmcledit.diagram.editor.TMCLEditDomain;
import de.topicmapslab.tmcledit.diagram.editparts.NameTypeConstraintEditPart;
import de.topicmapslab.tmcledit.diagram.editparts.OccurrenceTypeConstraintEditPart;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.Node;
import de.topicmapslab.tmcledit.model.commands.RemoveNodeCommand;

public class RemoveFromDiagramAction extends Action implements UpdateAction {

	public final static String ID = "de.topicmapslab.tmcleditor.removefromdiagram";
	
	private EditPart selectedEditPart;
	
	private final CommandStack commandStack;
	
	
	
	public RemoveFromDiagramAction(CommandStack commandStack) {
		super();
		this.commandStack = commandStack;
		update();
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
		return sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE);
	}
	
	@Override
	public void run() {
		if (selectedEditPart instanceof NodeEditPart) {
			Node node = (Node) selectedEditPart.getModel();
			TMCLEditDomain ed = (TMCLEditDomain) selectedEditPart.getViewer().getEditDomain();
			commandStack.execute(
					new CommandAdapter (ed.getEditingDomain().getCommandStack(),
						new RemoveNodeCommand((Diagram) node.eContainer(), node)));
		}
		// TODO Remove of node sub elements
	}
	
	@Override
	public String getText() {
		return "Remove from Diagram";
	}
	
	@Override
	public String getId() {
		return ID;
	}
	
	public void setSelectedEditPart(EditPart selectedEditPart) {
		this.selectedEditPart = selectedEditPart;
		update();
	}
	
	public void update() {
		if ( (selectedEditPart instanceof NodeEditPart)
			|| (selectedEditPart instanceof OccurrenceTypeConstraintEditPart)
			|| (selectedEditPart instanceof NameTypeConstraintEditPart)
		 ) {
			setEnabled(true);
		} else {
			setEnabled(false);
		}
	}

}
