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


import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.actions.ActionFactory;

import de.topicmapslab.tmcledit.diagram.action.AddNameConstraintAction;
import de.topicmapslab.tmcledit.diagram.action.AddOccurrenceConstraintAction;
import de.topicmapslab.tmcledit.diagram.action.DeleteFromModelAction;
import de.topicmapslab.tmcledit.diagram.action.RemoveFromDiagramAction;

public class TMCLEditorContextMenuProvider extends ContextMenuProvider {

	private final ActionRegistry actionRegistry;
	private boolean active;
	
	public TMCLEditorContextMenuProvider(EditPartViewer viewer, ActionRegistry actionRegistry) {
		super(viewer);
		this.actionRegistry = actionRegistry;
		active = true;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public void buildContextMenu(IMenuManager menu) {
		if (!active)
			return;
		GEFActionConstants.addStandardActionGroups(menu);
		
		IAction action;
		action = getActionRegistry().getAction(ActionFactory.UNDO.getId());
		menu.appendToGroup(GEFActionConstants.GROUP_UNDO, action);

		action = getActionRegistry().getAction(ActionFactory.REDO.getId());
		menu.appendToGroup(GEFActionConstants.GROUP_UNDO, action);
		
		action = getActionRegistry().getAction(AddNameConstraintAction.ID);
		if (action.isEnabled())
			menu.appendToGroup(GEFActionConstants.GROUP_EDIT, action);
		
		action = getActionRegistry().getAction(AddOccurrenceConstraintAction.ID);
			if (action.isEnabled())
				menu.appendToGroup(GEFActionConstants.GROUP_EDIT, action);
			
		action = getActionRegistry().getAction(RemoveFromDiagramAction.ID);
		if (action.isEnabled())
			menu.appendToGroup(GEFActionConstants.GROUP_EDIT, action);
		
		action = getActionRegistry().getAction(DeleteFromModelAction.ID);
		if (action.isEnabled())
			menu.appendToGroup(GEFActionConstants.GROUP_EDIT, action);

	}
	
	public ActionRegistry getActionRegistry() {
		return actionRegistry;
	}

}
