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
package de.topicmapslab.tmcledit.model.views.treenodes;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;

import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.TmcleditEditPlugin;
import de.topicmapslab.tmcledit.model.commands.RenameDiagramCommand;
import de.topicmapslab.tmcledit.model.util.TMCLEditorInput;
import de.topicmapslab.tmcledit.model.views.ModelView;

public class TreeDiagram extends TreeObject {

	public TreeDiagram(ModelView modelView, Diagram diagram) {
		super(modelView, null);
		setModel(diagram);
	}

	@Override
	public String getName() {
		return getDiagram().getName();
	}

	public Diagram getDiagram() {
		return (Diagram) getModel();
	}

	@Override
	public void handleDoubleClick() {
		try {
			IWorkbenchPage activePage = TmcleditEditPlugin.getPlugin().getWorkbench().getActiveWorkbenchWindow()
			        .getActivePage();

			TMCLEditorInput input = new TMCLEditorInput(getDiagram(), getModelView().getEditingDomain(), getModelView()
			        .getActionRegistry(), true);
			IEditorPart part = activePage.findEditor(input);
			if (part == null)
				activePage.openEditor(input, TmcleditEditPlugin.DIAGRAMEDITOR_ID);
			else
				activePage.activate(part);
		} catch (PartInitException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Image getImage() {
		return null;// ImageProvider.getImage(ImageConstants.DIAGRAM);
	}

	@Override
	public void handleRename() {
		String oldName = getDiagram().getName();
		InputDialog dlg = new InputDialog(getModelView().getViewer().getTree().getShell(), "New Diagram Name..",
		        "Please enter the new diagram name", oldName, new IInputValidator() {

			        public String isValid(String newText) {
				        if (newText.length() == 0)
					        return "no name given";

				        return null;
			        }
		        });
		if (InputDialog.OK == dlg.open()) {
			getModelView().getEditingDomain().getCommandStack().execute(
			        new RenameDiagramCommand(dlg.getValue(), getDiagram()));
		}
	}

	@Override
	public void notifyChanged(Notification notification) {
		if ((notification.getEventType() == Notification.SET)
		        && (notification.getFeatureID(String.class) == ModelPackage.DIAGRAM__NAME)) {
			getModelView().getViewer().refresh(this);
		}
	}
}
