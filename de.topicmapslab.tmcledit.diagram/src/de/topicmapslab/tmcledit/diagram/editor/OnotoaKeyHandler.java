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

import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.parts.GraphicalViewerKeyHandler;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;

import de.topicmapslab.tmcledit.diagram.action.DeleteFromModelAction;
import de.topicmapslab.tmcledit.diagram.action.RemoveFromDiagramAction;

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

	@Override
	public boolean keyPressed(KeyEvent event) {
		switch (event.keyCode) {
		case SWT.DEL:
			if ((event.stateMask & SWT.SHIFT) != 0)
				removeSelectionFromModel();
			else
				removeSelectionFromDiagram();
			return true;
		}
		return super.keyPressed(event);

	}

	private void removeSelectionFromModel() {
		actionRegistry.getAction(DeleteFromModelAction.ID).run();
	}

	private void removeSelectionFromDiagram() {
		actionRegistry.getAction(RemoveFromDiagramAction.ID).run();
	}
}
