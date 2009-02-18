/**
 * 
 */
package de.topicmapslab.tmcledit.wizards;

import org.eclipse.jface.wizard.Wizard;

/**
 * @author Hannes Niederhausen
 *
 */
public class FileWizard extends Wizard {

	private FileWizardPage page1;

	private final boolean newFile;
	
	

	public FileWizard(boolean newFile) {
		super();
		this.newFile = newFile;
	}

	@Override
	public void addPages() {
		String title = (newFile) ? "New File.." : "OpenFile";
		page1 = new FileWizardPage(title);
		addPage(page1);
		
	}
	
	public String getPath() {
		return page1.getPath();
	}
	
	@Override
	public boolean performFinish() {
		return true;
	}

}
