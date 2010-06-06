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

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.ui.PartInitException;

import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.TmcleditEditPlugin;
import de.topicmapslab.tmcledit.model.commands.CreateDomainDiagramCommand;
import de.topicmapslab.tmcledit.model.util.TMCLEditorInput;
import de.topicmapslab.tmcledit.model.views.ModelView;

public class CreateDomainDiagramAction extends Action {
	/**
	 * 
	 */
	private final ModelView modelView;

	/**
	 * @param modelView
	 */
	public CreateDomainDiagramAction(ModelView modelView) {
		this.modelView = modelView;
	}

	@Override
	public String getText() {
		return "Create New Domain Diagram...";
	}

	@Override
	public void run() {
		final File file = (File) modelView.getCurrentTopicMapSchema().eContainer();
		InputDialog dlg = new InputDialog(this.modelView.getSite().getShell(),
				"New Domain Diagram..",
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
			CreateDomainDiagramCommand command = new CreateDomainDiagramCommand(name, file);
			this.modelView.getEditingDomain().getCommandStack().execute(
					command);
			this.modelView.getViewer().expandToLevel(2);
			
			if (command.getDiagram()==null)
				return;
			try {
				TmcleditEditPlugin.getPlugin().getWorkbench().getActiveWorkbenchWindow()
						.getActivePage().openEditor(new TMCLEditorInput(command.getDiagram(), 
								this.modelView.getEditingDomain(),
								this.modelView.getActionRegistry(),
								modelView,
								true), TmcleditEditPlugin.DOMAIN_DIAGRAMEDITOR_ID);
			} catch (PartInitException e) {
				throw new RuntimeException(e);
			}
		}

	}
	
}