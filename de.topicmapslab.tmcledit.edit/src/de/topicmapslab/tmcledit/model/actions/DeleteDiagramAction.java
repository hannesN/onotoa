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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.command.CompoundCommand;
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

	private List<Diagram> diagrams;
	private ModelView modelView;

	public DeleteDiagramAction(ModelView modelView) {
		setText("Delete");
		this.modelView = modelView;
		diagrams = new ArrayList<Diagram>();
		modelView.addSelectionChangedListener(this);
		setDiagram(null);
	}

	@SuppressWarnings("unchecked")
    public void setDiagram(IStructuredSelection sel) {
		diagrams.clear();
		if (sel!=null) {
			Iterator it = sel.iterator();
			
			while (it.hasNext()) {
				Object o = it.next();
				if (o instanceof Diagram) {
					diagrams.add((Diagram) o);
				}
			}
		}
		setEnabled(!diagrams.isEmpty());
	}

	@Override
	public void run() {
		Shell shell = modelView.getSite().getShell();
		if (MessageDialog.openQuestion(shell, "Are you sure?",
				"Do you really want to delete the selected diagram?")) {
			
			CompoundCommand ccmd = new CompoundCommand();
			
			List<DeleteDiagramCommand> cmdList = new ArrayList<DeleteDiagramCommand>();
			for (Diagram d : diagrams) {
				DeleteDiagramCommand cmd = new DeleteDiagramCommand(d);
				cmdList.add(cmd);
			}
			
			// sorting commands, so the diagram deletion can be undone (command uses position in
			// diagram list therefor the diagrams with the lower indices need to be deleted last)
			Collections.sort(cmdList, new Comparator<DeleteDiagramCommand>() {

				public int compare(DeleteDiagramCommand o1, DeleteDiagramCommand o2) {
					if (o1.getIndex()==o2.getIndex())
						return 0;
					if (o1.getIndex()<o2.getIndex())
						return 1;
					else return -1;
                }
				
			});
			for (DeleteDiagramCommand cmd : cmdList)
				ccmd.append(cmd);
			
			modelView.getEditingDomain().getCommandStack().execute(ccmd);
		}

	}

	public void selectionChanged(SelectionChangedEvent event) {
		setDiagram((IStructuredSelection) event.getSelection());
	}
}
