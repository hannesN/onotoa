/**
 * 
 */
package de.topicmapslab.tmcledit.wizards;

import org.eclipse.jface.wizard.Wizard;

/**
 * @author Hannes Niederhausen
 *
 */
public class NewFileWizard extends Wizard {

	private NewFileWizardPage page1;
	

	@Override
	public void addPages() {
		page1 = new NewFileWizardPage("New File..");
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
