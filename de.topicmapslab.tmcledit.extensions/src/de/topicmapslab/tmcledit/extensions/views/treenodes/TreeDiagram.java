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
package de.topicmapslab.tmcledit.extensions.views.treenodes;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.ui.action.RedoAction;
import org.eclipse.emf.edit.ui.action.UndoAction;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.ActionFactory;

import de.topicmapslab.tmcledit.diagram.editor.TMCLDiagramEditor;
import de.topicmapslab.tmcledit.diagram.editor.TMCLEditorInput;
import de.topicmapslab.tmcledit.extensions.Activator;
import de.topicmapslab.tmcledit.extensions.views.ModelView;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.commands.RenameDiagramCommand;

public class TreeDiagram extends TreeObject {

	public TreeDiagram(ModelView modelView, Diagram diagram) {
		super(modelView);
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
			Activator.getDefault().getWorkbench().getActiveWorkbenchWindow()
					.getActivePage().openEditor(new TMCLEditorInput(getDiagram(), 
							getModelView().getEditingDomain(),
							(UndoAction) getModelView().getActionRegistry().get(ActionFactory.UNDO.getId()),
							(RedoAction) getModelView().getActionRegistry().get(ActionFactory.REDO.getId()),
							true), TMCLDiagramEditor.ID);
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Image getImage() {
		return null;// ImageProvider.getImage(ImageConstants.DIAGRAM);
	}
	
	@Override
	public void handleRename() {
		String oldName = getDiagram().getName();
		InputDialog dlg = new InputDialog(getModelView().getViewer().getTree()
				.getShell(), "New Diagram Name..", "Please enter the new diagram name",
				oldName, new IInputValidator() {

					@Override
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
		if ( (notification.getEventType()==Notification.SET) && (notification.getFeatureID(String.class)==ModelPackage.DIAGRAM__NAME)){
			getModelView().getViewer().refresh(this);
		}
	}
}
