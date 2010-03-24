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
package de.topicmapslab.tmcledit.diagram.editor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.parts.GraphicalViewerKeyHandler;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;

import de.topicmapslab.tmcledit.diagram.action.DeleteFromModelAction;
import de.topicmapslab.tmcledit.diagram.action.RemoveFromDiagramAction;
import de.topicmapslab.tmcledit.diagram.editparts.NodeEditPart;

/**
 * This class is the key handler for the graphical viewer.
 * 
 * @author Hannes Niederhausen
 * 
 */
public class OnotoaKeyHandler extends GraphicalViewerKeyHandler {
	final ActionRegistry actionRegistry; 
	public OnotoaKeyHandler(GraphicalViewer viewer, ActionRegistry actionRegistry) {
		super(viewer);
		this.actionRegistry = actionRegistry;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean keyPressed(KeyEvent event) {
		switch (event.keyCode) {
		case SWT.DEL:
			if ((event.stateMask & SWT.SHIFT) != 0)
				removeSelectionFromModel();
			else
				removeSelectionFromDiagram();
			return true;
		case 97:
			List<EditPart> selectList = new ArrayList<EditPart>();
			if ((event.stateMask & SWT.CTRL) != 0) {
				Iterator<EditPart> it = getViewer().getEditPartRegistry().values().iterator();
				while (it.hasNext()) {
					EditPart ep = it.next();
					if (ep instanceof NodeEditPart) {
						selectList.add(ep);
					}
				}
				getViewer().setSelection(new StructuredSelection(selectList));
			}
			return true;
		}
		
		return super.keyPressed(event);

	}

	private void removeSelectionFromModel() {
		IAction action = actionRegistry.getAction(DeleteFromModelAction.ID);
		if (action.isEnabled())
			action.run();
	}

	private void removeSelectionFromDiagram() {
		IAction action = actionRegistry.getAction(RemoveFromDiagramAction.ID);
		if (action.isEnabled())
			action.run();
	}
}
