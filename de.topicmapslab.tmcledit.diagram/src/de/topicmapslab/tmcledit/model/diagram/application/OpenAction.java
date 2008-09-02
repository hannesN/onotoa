package de.topicmapslab.tmcledit.model.diagram.application;

import java.io.File;

import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.action.IAction;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;

/**
 * @generated
 */
public class OpenAction extends AbstractTmcleditAction {

	/**
	 * @generated
	 */
	public void run(IAction action) {
		FileDialog fileDialog = new FileDialog(getWindow().getShell(),
				SWT.OPEN);
		fileDialog.open();
		if (fileDialog.getFileName() != null
				&& fileDialog.getFileName().length() > 0) {
			openEditor(getWindow().getWorkbench(), URI
					.createFileURI(fileDialog.getFilterPath()
							+ File.separator + fileDialog.getFileName()));
		}
	}
}