/**
 * 
 */
package de.topicmapslab.onotoa.export.maiana.wizard;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.IWorkbench;

import de.topicmapslab.maiana_io.widgets.UploadWidget;
import de.topicmapslab.onotoa.export.maiana.Activator;
import de.topicmapslab.onotoa.selection.service.IOnotoaSelectionService;
import de.topicmapslab.tmcledit.export.builder.TMCLTopicMapBuilder;
import de.topicmapslab.tmcledit.model.File;

/**
 * Export wizard for the Maiana Export
 * 
 * @author Hannes Niederhausen
 *
 */
public class MaianaExport extends Wizard implements IExportWizard {

	private Page page;

	/**
	 * 
	 */
	public MaianaExport() {
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {

	}

	@Override
	public boolean performFinish() {
		return page.performFinish();
	}

	@Override
	public void addPages() {
		page = new Page();
		addPage(page);
	}
	
	private class Page extends WizardPage {

		private UploadWidget widget;
		
		protected Page() {
	        super("Maiana Data");
        }

		@Override
        public void createControl(Composite parent) {
	        widget = new UploadWidget(parent, SWT.NONE);
	        
	        setControl(widget);
        }
		
		public boolean performFinish() {
			try {
				IOnotoaSelectionService selServ = Activator.getDefault().getSelectionService();
				File f = selServ.getOnotoaFile();
				
				TMCLTopicMapBuilder builder = new TMCLTopicMapBuilder(f.getTopicMapSchema(), false, false);
				
				widget.upload(builder.createTopicMap(), true);
				
				return true;
			} catch (RuntimeException e) {
				MessageDialog.openError(getShell(), "An error occured!", e.getMessage());
				Activator.logError(e);
				return false;
			}
		}
	}
}
