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
package de.topicmapslab.tmcledit.extensions.actions;

import org.eclipse.emf.edit.ui.action.RedoAction;
import org.eclipse.emf.edit.ui.action.UndoAction;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.ActionFactory;

import de.topicmapslab.tmcledit.diagram.editor.TMCLDiagramEditor;
import de.topicmapslab.tmcledit.diagram.editor.TMCLEditorInput;
import de.topicmapslab.tmcledit.extensions.Activator;
import de.topicmapslab.tmcledit.extensions.views.ModelView;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.commands.CreateDiagramCommand;

public class CreateDiagramAction extends Action {
	/**
	 * 
	 */
	private final ModelView modelView;

	/**
	 * @param modelView
	 */
	public CreateDiagramAction(ModelView modelView) {
		this.modelView = modelView;
	}

	@Override
	public String getText() {
		return "Create Diagram";
	}

	@Override
	public void run() {
		final File file = (File) modelView.getCurrentTopicMapSchema().eContainer();
		InputDialog dlg = new InputDialog(this.modelView.getSite().getShell(),
				"New Diagram..",
				"Please Enter the name of the new diagram", "", 
				new IInputValidator() {

					public String isValid(String newText) {
						if (newText.length()==0)
							return "Please enter a name.";
						
						
						
						for (Diagram d : file.getDiagrams()) {
							if (d.getName().equals(newText)) {
								return "A diagram with that name already exists!";
							}
						}
						return null;										
					}
			
		});

		if (dlg.open() == Dialog.OK) {
			String name = dlg.getValue();
			CreateDiagramCommand command = new CreateDiagramCommand(name, file);
			this.modelView.getEditingDomain().getCommandStack().execute(
					command);
			this.modelView.getViewer().expandToLevel(2);
			
			if (command.getDiagram()==null)
				return;
			try {
				Activator.getDefault().getWorkbench().getActiveWorkbenchWindow()
						.getActivePage().openEditor(new TMCLEditorInput(command.getDiagram(), 
								this.modelView.getEditingDomain(),
								(UndoAction) this.modelView.getActionRegistry().get(ActionFactory.UNDO.getId()),
								(RedoAction) this.modelView.getActionRegistry().get(ActionFactory.REDO.getId()),
								true), TMCLDiagramEditor.ID);
			} catch (PartInitException e) {
				throw new RuntimeException(e);
			}
		}

	}
}