/**
 * 
 */
package de.topicmapslab.tmcledit.model.diagram.application;

import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.ui.action.WorkbenchWindowActionDelegate;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;

import de.topicmapslab.tmcledit.model.diagram.part.Messages;

/**
 * @author Hannes Niederhausen
 * 
 */
public abstract class AbstractTmcleditAction extends WorkbenchWindowActionDelegate {

	
	
	public boolean openEditor(IWorkbench workbench, URI fileURI) {
		IWorkbenchWindow workbenchWindow = workbench.getActiveWorkbenchWindow();
		IWorkbenchPage page = workbenchWindow.getActivePage();
		IEditorDescriptor editorDescriptor = workbench.getEditorRegistry()
				.getDefaultEditor(fileURI.toFileString());
		if (editorDescriptor == null) {
			MessageDialog
					.openError(
							workbenchWindow.getShell(),
							Messages.DiagramEditorActionBarAdvisor_DefaultFileEditorTitle,
							NLS
									.bind(
											Messages.DiagramEditorActionBarAdvisor_DefaultFileEditorMessage,
											fileURI.toFileString()));
			return false;
		} else {
			try {
				page.openEditor(new URIEditorInput(fileURI), editorDescriptor
						.getId());
			} catch (PartInitException exception) {
				MessageDialog
						.openError(
								workbenchWindow.getShell(),
								Messages.DiagramEditorActionBarAdvisor_DefaultEditorOpenErrorTitle,
								exception.getMessage());
				return false;
			}
		}
		return true;
	}
}
