package de.topicmapslab.tmcledit.application;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

/**
 * @generated
 */
public class DiagramEditorPerspective implements IPerspectiveFactory {
	/**
	 * @generated NOT
	 */
	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(true);
		
		
		
		layout.addPerspectiveShortcut(DiagramEditorWorkbenchAdvisor.PERSPECTIVE_ID);
		IFolderLayout bottom = layout.createFolder(
				"bottom", IPageLayout.BOTTOM, 0.55f, layout.getEditorArea()); //$NON-NLS-1$
		bottom.addView(IPageLayout.ID_OUTLINE);
		IFolderLayout bottomRight = layout.createFolder(
				"bottomRight", IPageLayout.RIGHT, 0.5f, "bottom"); //$NON-NLS-1$	//$NON-NLS-2$
		bottomRight.addView(IPageLayout.ID_PROP_SHEET);
		
		layout.addView("de.topicmapslab.tmcledit.extensions.views.ModelView",
				   IPageLayout.LEFT, 0.3f, layout.getEditorArea());
				   
	}
}
