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
package de.topicmapslab.tmcledit.domaindiagram.editor;

import java.util.Iterator;

import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.actions.ActionFactory;

import de.topicmapslab.tmcledit.diagram.action.AddNameConstraintAction;
import de.topicmapslab.tmcledit.diagram.action.AddOccurrenceConstraintAction;
import de.topicmapslab.tmcledit.diagram.action.CopyToDiagramAction;
import de.topicmapslab.tmcledit.diagram.action.DeleteFromModelAction;
import de.topicmapslab.tmcledit.diagram.action.MoveToDiagramAction;
import de.topicmapslab.tmcledit.diagram.action.RemoveFromDiagramAction;
import de.topicmapslab.tmcledit.domaindiagram.editparts.IContextMenuProvider;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.Node;

public class DomainEditorContextMenuProvider extends ContextMenuProvider {

	private final ActionRegistry actionRegistry;
	private boolean active;
	private final Diagram diagram;

	private EditPart selectedEditPart;

	public DomainEditorContextMenuProvider(EditPartViewer viewer,
			ActionRegistry actionRegistry, Diagram diagram) {
		super(viewer);
		this.actionRegistry = actionRegistry;
		active = true;
		this.diagram = diagram;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void setSelectedEditPart(EditPart selectedEditPart) {
		this.selectedEditPart = selectedEditPart;
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

		buildMoveToDiagramActions(menu);
		buildCopyToDiagramActions(menu);

		if ((selectedEditPart != null)
				&& (selectedEditPart instanceof IContextMenuProvider)) {
			for (IAction a : ((IContextMenuProvider) selectedEditPart)
					.getActions()) {
				if (a.isEnabled())
					menu.add(a);
			}
			for (IContributionItem i : ((IContextMenuProvider) selectedEditPart)
					.getItems()) {
				menu.add(i);
			}
		}

	}

	private void buildMoveToDiagramActions(IMenuManager menu) {
		File file = (File) diagram.eContainer();
		if ((file.getDiagrams().size() > 1) && (mayMove())) {
			MenuManager moveMenu = new MenuManager("&Move To...");

			for (Diagram d : file.getDiagrams()) {
				if (!d.equals(diagram)) {
					org.eclipse.emf.common.command.CommandStack commandStack = ((DomainEditDomain) getViewer()
							.getEditDomain()).getEditingDomain()
							.getCommandStack();
					MoveToDiagramAction a = new MoveToDiagramAction(
							commandStack, d, getViewer());
					moveMenu.add(a);
				}
			}
			menu.add(moveMenu);

		}
	}

	private void buildCopyToDiagramActions(IMenuManager menu) {
		File file = (File) diagram.eContainer();
		if ((file.getDiagrams().size() > 1) && (mayMove())) {
			MenuManager moveMenu = new MenuManager("&Copy To...");

			for (Diagram d : file.getDiagrams()) {
				if (!d.equals(diagram)) {
					org.eclipse.emf.common.command.CommandStack commandStack = ((DomainEditDomain) getViewer()
							.getEditDomain()).getEditingDomain()
							.getCommandStack();
					CopyToDiagramAction a2 = new CopyToDiagramAction(
							commandStack, d, getViewer());
					moveMenu.add(a2);
				}
			}
			menu.add(moveMenu);

		}
	}

	@SuppressWarnings("unchecked")
	private boolean mayMove() {
		IStructuredSelection sel = (IStructuredSelection) getViewer()
				.getSelection();
		if (sel.isEmpty())
			return false;

		Iterator it = sel.iterator();
		while (it.hasNext()) {
			Object next = it.next();
			if (next instanceof EditPart) {
				if (((EditPart) next).getModel() instanceof Node)
					return true;
			}
		}

		return false;

	}

	public ActionRegistry getActionRegistry() {
		return actionRegistry;
	}

}
