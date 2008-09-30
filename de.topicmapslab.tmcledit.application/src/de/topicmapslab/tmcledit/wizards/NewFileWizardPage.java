/**
 * 
 */
package de.topicmapslab.tmcledit.wizards;

import java.io.File;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * @author Hannes Niederhausen
 *
 */
public class NewFileWizardPage extends WizardPage {

	
	private Text pathText;
	private String path;

	protected NewFileWizardPage(String pageName) {
		super(pageName);
	}

	@Override
	public void createControl(Composite parent) {
		Composite comp = new Composite(parent, SWT.NONE);
		comp.setLayout(new GridLayout(3, false));
		Label label = new Label(comp, SWT.NONE);
		label.setText("Path:");
		
		pathText = new Text(comp, SWT.BORDER);
		pathText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		pathText.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				path = pathText.getText();
			}
			
		});
		
		Button button = new Button(comp, SWT.PUSH);
		button.setText("...");
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog dlg = new FileDialog(pathText.getShell());
				dlg.setFilterExtensions(new String[]{"tmcl"});
				dlg.setFilterPath(System.getProperty("user.home"));
				
				String path = dlg.open();
				if (path!=null) {
					if (!path.endsWith(".tmcl"))
						path += ".tmcl";
					pathText.setText(path);
					NewFileWizardPage.this.path = path;
				}
				
			}
		});
		path = System.getProperty("user.home")+File.separator+"default.tmcl";
		pathText.setText(path);
		setControl(comp);
	}
	
	public String getPath() {
		return path;
	}

}
