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
package de.topicmapslab.tmcledit.model.perspectives;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import de.topicmapslab.tmcledit.model.views.ModelView;
import de.topicmapslab.tmcledit.model.views.NotesView;
import de.topicmapslab.tmcledit.model.views.PropertyDetailView;

/**
 * @generated
 */
public class OnotoaPerspective implements IPerspectiveFactory {

	public static final String ID = "de.topicmapslab.tmcledit.extensions.OnotoaPerspective";
	
	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(true);
				
		
		layout.addPerspectiveShortcut(ID);
		IFolderLayout leftSide = layout.createFolder("left", IPageLayout.LEFT, 0.2f, layout.getEditorArea());
		leftSide.addView(ModelView.ID);
		
		IFolderLayout leftBottom = layout.createFolder(
				"leftBottom", IPageLayout.BOTTOM, 0.70f, "left"); //$NON-NLS-1$	//$NON-NLS-2$
		leftBottom.addView(IPageLayout.ID_OUTLINE);
		
		IFolderLayout bottom = layout.createFolder(
				"bottom", IPageLayout.BOTTOM, 0.75f, layout.getEditorArea()); //$NON-NLS-1$
		bottom.addView(PropertyDetailView.ID);
		
		layout.addView(NotesView.ID, IPageLayout.RIGHT, 0.4f, "bottom");
		
		
		
				   
	}
}
