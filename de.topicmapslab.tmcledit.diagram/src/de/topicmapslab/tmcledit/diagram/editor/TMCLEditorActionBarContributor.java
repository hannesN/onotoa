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
package de.topicmapslab.tmcledit.diagram.editor;

import java.io.FileOutputStream;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.SWTGraphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ui.actions.ActionBarContributor;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.actions.ZoomComboContributionItem;
import org.eclipse.gef.ui.actions.ZoomInRetargetAction;
import org.eclipse.gef.ui.actions.ZoomOutRetargetAction;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchActionConstants;

/**
 * @author Hannes Niederhausen
 * 
 */
public class TMCLEditorActionBarContributor extends ActionBarContributor {

	ExportAction exportAction = new ExportAction();
	
	@Override
	public void contributeToToolBar(IToolBarManager toolBarManager) {
		toolBarManager.add(getAction(GEFActionConstants.ZOOM_IN));
		toolBarManager.add(new ZoomComboContributionItem(getPage()));
		toolBarManager.add(getAction(GEFActionConstants.ZOOM_OUT));
		toolBarManager.add(exportAction);
	}

	@Override
	public void contributeToMenu(IMenuManager menuManager) {
		super.contributeToMenu(menuManager);

		MenuManager viewMenu = new MenuManager("&View");
		viewMenu.add(getAction(GEFActionConstants.ZOOM_IN));
		viewMenu.add(getAction(GEFActionConstants.ZOOM_OUT));
		viewMenu.add(exportAction);
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
		if (editor instanceof TMCLDiagramEditor)
			exportAction.setEditor((TMCLDiagramEditor) editor);
		else
			exportAction.setEditor(null);
	}

	private class ExportAction extends Action {
		TMCLDiagramEditor editor;

		public ExportAction() {
			setText("Export diagram");
		}

		public void setEditor(TMCLDiagramEditor editor) {
			this.editor = editor;
		}
		
		@Override
		public void run() {
			if (editor == null)
				return;

			FileDialog dlg = new FileDialog(editor.getSite().getShell());
			dlg.setFilterExtensions(new String[] { "*.png" });
			dlg.setText("Save as...");
			String file = dlg.open();
			if (file == null)
				return;

			if (!file.endsWith(".png"))
				file += ".png";
			
			
			IFigure figure = editor.getPrintableFigure();
			Device device = dlg.getParent().getDisplay();
			Rectangle r = figure.getBounds();
			Image image = null;
			GC gc = null;
			Graphics g = null;
			try {
				FileOutputStream result = new FileOutputStream(file);

				image = new Image(device, r.width, r.height);
				gc = new GC(image);
				g = new SWTGraphics(gc);
				g.translate(r.x * -1, r.y * -1);

				figure.paint(g);

				ImageLoader imageLoader = new ImageLoader();
				imageLoader.data = new ImageData[] { image.getImageData() };
				imageLoader.save(result, SWT.IMAGE_PNG);
			} catch (Exception e) {
				throw new RuntimeException(e);
			} finally {
				if (g != null) {
					g.dispose();
				}
				if (gc != null) {
					gc.dispose();
				}
				if (image != null) {
					image.dispose();
				}
			}

		}
	}
}
