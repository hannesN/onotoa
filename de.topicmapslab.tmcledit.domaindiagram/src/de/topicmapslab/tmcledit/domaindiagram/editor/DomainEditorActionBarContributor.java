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
package de.topicmapslab.tmcledit.domaindiagram.editor;

import java.io.File;

import org.eclipse.gef.ui.actions.ActionBarContributor;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.actions.ZoomComboContributionItem;
import org.eclipse.gef.ui.actions.ZoomInRetargetAction;
import org.eclipse.gef.ui.actions.ZoomOutRetargetAction;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.PlatformUI;

import de.topicmapslab.tmcledit.diagram.util.IPrintableDiagramEditor;
import de.topicmapslab.tmcledit.diagram.util.ImageExporter;
import de.topicmapslab.tmcledit.model.actions.ValidateAction;


/**
 * @author Hannes Niederhausen
 * 
 */
public class DomainEditorActionBarContributor extends ActionBarContributor {

	ExportAction exportAction = new ExportAction();
	ValidateAction validateAction = new ValidateAction();
	
	@Override
	public void contributeToToolBar(IToolBarManager toolBarManager) {
		toolBarManager.add(getAction(GEFActionConstants.ZOOM_IN));
		toolBarManager.add(new ZoomComboContributionItem(getPage()));
		toolBarManager.add(getAction(GEFActionConstants.ZOOM_OUT));
		toolBarManager.add(exportAction);
		toolBarManager.add(validateAction);
	}

	@Override
	public void contributeToMenu(IMenuManager menuManager) {
		super.contributeToMenu(menuManager);

		MenuManager viewMenu = new MenuManager("&View");
		viewMenu.add(getAction(GEFActionConstants.ZOOM_IN));
		viewMenu.add(getAction(GEFActionConstants.ZOOM_OUT));
		viewMenu.add(exportAction);
		viewMenu.add(validateAction);
		menuManager.insertAfter(IWorkbenchActionConstants.M_EDIT, viewMenu);

	}

	@Override
	protected void buildActions() {
		addRetargetAction(new ZoomInRetargetAction());
		addRetargetAction(new ZoomOutRetargetAction());
	}

	@Override
	protected void declareGlobalActionKeys() {
	}

	@Override
	public void setActiveEditor(IEditorPart editor) {
		super.setActiveEditor(editor);
		if (editor instanceof DomainDiagramEditor)
			exportAction.setEditor((DomainDiagramEditor) editor);
		else
			exportAction.setEditor(null);
		validateAction.setSite(getPage().getActiveEditor().getSite());
	}

	private class ExportAction extends Action {
		IPrintableDiagramEditor editor;

		public ExportAction() {
			setText("Export diagram");
		}

		public void setEditor(IPrintableDiagramEditor editor) {
			this.editor = editor;
		}
		
		@Override
		public void run() {
			if (editor == null)
				return;

			FileDialog dlg = new FileDialog(getShell(), SWT.SAVE);
			dlg.setFilterExtensions(new String[] { "*.svg;*.png", "*.png", "*.svg"});
			dlg.setText("Save as...");
			String file = dlg.open();
			if (file == null)
				return;

			if (file.endsWith(".svg")) {
				File f = new File(file);
				if (checkFileExists(f))
					ImageExporter.exportSvg(editor, f);
			} else {
				if (!file.endsWith(".png"))
					file += ".png";
				
				File f = new File(file);
				if (checkFileExists(f))
					ImageExporter.exportPng(editor, f);
			}

		}

		private Shell getShell() {
			return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor().getSite().getShell();
		}
		
		private boolean checkFileExists(File f) {
			if (f.exists()) {
				return MessageDialog.openConfirm(getShell(), "File already exists!", "The file you've chosen already exists. Do you want to overwrite it?");
			}
			return true;
		}

	}
}
