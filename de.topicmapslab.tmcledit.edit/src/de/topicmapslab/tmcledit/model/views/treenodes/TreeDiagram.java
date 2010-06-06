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
package de.topicmapslab.tmcledit.model.views.treenodes;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;

import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.DomainDiagram;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.TmcleditEditPlugin;
import de.topicmapslab.tmcledit.model.commands.RenameDiagramCommand;
import de.topicmapslab.tmcledit.model.util.ImageConstants;
import de.topicmapslab.tmcledit.model.util.ImageProvider;
import de.topicmapslab.tmcledit.model.util.TMCLEditorInput;
import de.topicmapslab.tmcledit.model.views.ModelView;

public class TreeDiagram extends TreeObject {

	public TreeDiagram(ModelView modelView, Diagram diagram) {
		super(modelView, null);
		setModel(diagram);
		setHandleRename(true);
	}

	@Override
	public String getName() {
		return getDiagram().getName();
	}

	public Diagram getDiagram() {
		return (Diagram) getModel();
	}

	@Override
	public void handleDoubleClick() {
		try {
			IWorkbenchPage activePage = TmcleditEditPlugin.getPlugin().getWorkbench().getActiveWorkbenchWindow()
			        .getActivePage();

			TMCLEditorInput input = new TMCLEditorInput(getDiagram(), getModelView().getEditingDomain(), getModelView()
			        .getActionRegistry(), getModelView(), true);
			IEditorPart part = activePage.findEditor(input);
			
			
			if (part == null) {
				String editorId = (getDiagram() instanceof DomainDiagram) ? TmcleditEditPlugin.DOMAIN_DIAGRAMEDITOR_ID : TmcleditEditPlugin.DIAGRAMEDITOR_ID;
				activePage.openEditor(input, editorId);
			} else
				activePage.activate(part);
		} catch (PartInitException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Image getImage() {
		if (getDiagram() instanceof DomainDiagram)
			return ImageProvider.getImage(ImageConstants.DOMAINDIAGRAM);
		return ImageProvider.getImage(ImageConstants.DIAGRAM);
	}

	@Override
	public void handleRename() {
		String oldName = getDiagram().getName();
		InputDialog dlg = new InputDialog(getModelView().getViewer().getTree().getShell(), "New Diagram Name..",
		        "Please enter the new diagram name", oldName, 
						new IInputValidator() {

							public String isValid(String newText) {
								if (newText.length()==0)
									return "Please enter a name.";
								
								
								File file = (File) getDiagram().eContainer();
								for (Diagram d : file.getDiagrams()) {
									if ( (d.getName().equals(newText)) && (!(d.equals(getDiagram()))) ) {
										return "A diagram with that name already exists!";
									}
								}
								return null;										
							}
					
				});
		if (InputDialog.OK == dlg.open()) {
			getModelView().getEditingDomain().getCommandStack().execute(
			        new RenameDiagramCommand(dlg.getValue(), getDiagram()));
		}
	}
	
	@Override
	public void notifyChanged(Notification notification) {
		if ((notification.getEventType() == Notification.SET)
		        && (notification.getFeatureID(String.class) == ModelPackage.DIAGRAM__NAME)) {
			getModelView().getViewer().refresh(this);
		}
	}
}
