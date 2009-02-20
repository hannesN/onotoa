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

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

import de.topicmapslab.tmcledit.extensions.views.ModelView;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.commands.DeleteDiagramCommand;

/**
 * @author Hannes Niederhausen
 * 
 */
public class DeleteDiagramAction extends Action {

	private Diagram diagram;
	private ModelView modelView;

	public DeleteDiagramAction(ModelView modelView) {
		setText("Delete Diagram");
		this.modelView = modelView;
	}

	public void setDiagram(Diagram diagram) {
		this.diagram = diagram;
	}

	@Override
	public void run() {
		Shell shell = modelView.getSite().getShell();
		if (MessageDialog.openQuestion(shell, "Are you sure?",
				"Do you really want to delete the selected diagram?")) {
			
			AbstractCommand cmd = new DeleteDiagramCommand(diagram);
			modelView.getEditingDomain().getCommandStack().execute(cmd);
		}

	}
	
	
}
