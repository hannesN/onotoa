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

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.widgets.Shell;

import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.commands.DeleteDiagramCommand;
import de.topicmapslab.tmcledit.model.views.ModelView;

/**
 * @author Hannes Niederhausen
 * 
 */
public class DeleteDiagramAction extends Action implements ISelectionChangedListener {

	private Diagram diagram;
	private ModelView modelView;

	public DeleteDiagramAction(ModelView modelView) {
		setText("Delete Diagram");
		this.modelView = modelView;
		modelView.addSelectionChangedListener(this);
		setDiagram(null);
	}

	public void setDiagram(Diagram diagram) {
		this.diagram = diagram;
		setEnabled(diagram!=null);
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

	public void selectionChanged(SelectionChangedEvent event) {
		Diagram diagram = null;
		if ((!event.getSelection().isEmpty()) || ((event.getSelection() instanceof IStructuredSelection))) {

			IStructuredSelection sel = (IStructuredSelection) event.getSelection();

			Object obj = sel.getFirstElement();
			if (obj instanceof Diagram)
			diagram = (Diagram) obj;
		}
		setDiagram(diagram);

	}
}
