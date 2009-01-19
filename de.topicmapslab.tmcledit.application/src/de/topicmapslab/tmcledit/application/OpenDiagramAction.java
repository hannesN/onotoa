/**
 * 
 */
package de.topicmapslab.tmcledit.application;

import org.eclipse.emf.common.ui.action.WorkbenchWindowActionDelegate;
import org.eclipse.jface.action.IAction;
import org.eclipse.swt.widgets.FileDialog;

/**
 * @author Hannes Niederhausen
 *
 */
public class OpenDiagramAction extends WorkbenchWindowActionDelegate {
	@Override
	public void run(IAction action) {
		FileDialog dlg = new FileDialog(getWindow().getShell());
		dlg.setFilterExtensions(new String[]{"*.tmcl"});
		dlg.setFilterPath(System.getProperty("user.home"));
		
		String path = dlg.open();
		if (path!=null) {
			if (!path.endsWith(".tmcl"))
				path += ".tmcl";
						
			DiagramEditorActionBarAdvisor.openModelView(getWindow().getWorkbench(), path, false);
		}
		
	}
}
