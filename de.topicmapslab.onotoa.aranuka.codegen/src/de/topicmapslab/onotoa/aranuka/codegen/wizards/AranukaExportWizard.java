/**
 * 
 */
package de.topicmapslab.onotoa.aranuka.codegen.wizards;

import static de.topicmapslab.onotoa.aranuka.codegen.preferences.IPreferenceConstants.P_LASTGENNYAGENERATION;
import static de.topicmapslab.onotoa.aranuka.codegen.preferences.IPreferenceConstants.P_LASTKURIAGENERATION;
import static de.topicmapslab.onotoa.aranuka.codegen.preferences.IPreferenceConstants.P_LASTPACKAGENAME;
import static de.topicmapslab.onotoa.aranuka.codegen.preferences.IPreferenceConstants.P_LASTSOURCEPATH;

import java.io.File;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
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
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.IWorkbench;
import org.tmapi.core.TopicMap;

import de.topicmapslab.codegenerator.CodeGenerator;
import de.topicmapslab.codegenerator.factories.AranukaDescriptorFactory;
import de.topicmapslab.onotoa.aranuka.codegen.Activator;
import de.topicmapslab.tmcledit.export.builder.TMCLTopicMapBuilder;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;

/**
 * 
 * 
 * @author Hannes Niederhausen
 * 
 */
public class AranukaExportWizard extends Wizard implements IExportWizard {

	private String path;
	private String packageName;
	private boolean generateKuriaAnnotations = false;
	private boolean generateGennyClasses = false;

	/**
	 * 
	 */
	public AranukaExportWizard() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public boolean performFinish() {
		try {
			TopicMapSchema schema = ModelIndexer.getInstance().getTopicMapSchema();

			TMCLTopicMapBuilder builder = new TMCLTopicMapBuilder(schema, false);

			TopicMap topicMap = builder.createTopicMap();

			AranukaDescriptorFactory fac = new AranukaDescriptorFactory(builder.getTopicMapSystem(), topicMap,
			        packageName, generateGennyClasses, generateKuriaAnnotations);
			CodeGenerator gen = fac.getCodeGenerator();
			gen.generateCode(new File(path));

			// storing new values
			IPreferenceStore ps = Activator.getDefault().getPreferenceStore();
			ps.setValue(P_LASTSOURCEPATH, path);
			ps.setValue(P_LASTPACKAGENAME, packageName);
			ps.setValue(P_LASTKURIAGENERATION, generateKuriaAnnotations);
			ps.setValue(P_LASTGENNYAGENERATION, generateGennyClasses);
			
		} catch (Exception e) {
			Activator.logException(e);
			MessageDialog.openError(getShell(), "Error while generating code", "An error occurred:" + e.getMessage()
			        + "[" + e.getClass().getName() + "]");

			return true;
		}

		return true;
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {

	}

	@Override
	public void addPages() {
		addPage(new ExportPage());
	}

	private class ExportPage extends WizardPage {
		private Text sourcePathText;
		private Text packageNameText;
		private Button browseButton;

		private Button kuriaButton;
		private Button gennyButton;

		protected ExportPage() {
			super("Export");
		}

		@Override
		public void createControl(Composite parent) {
			Composite comp = new Composite(parent, SWT.NONE);
			comp.setLayout(new GridLayout(3, false));

			Label l = new Label(comp, SWT.NONE);
			l.setText("Target Directory:");

			sourcePathText = new Text(comp, SWT.BORDER);
			sourcePathText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

			browseButton = new Button(comp, SWT.PUSH);
			browseButton.setText("...");

			l = new Label(comp, SWT.NONE);
			l.setText("Package Name:");
			packageNameText = new Text(comp, SWT.BORDER);
			packageNameText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			

			GridData gd = new GridData(GridData.FILL_HORIZONTAL);
			gd.horizontalSpan = 3;
			GridDataFactory fac = GridDataFactory.createFrom(gd);
			
			
			kuriaButton = new Button(comp, SWT.CHECK);
			kuriaButton.setText("Generate Kuria Annotations");
			fac.applyTo(kuriaButton);
			
			gennyButton = new Button(comp, SWT.CHECK);
			gennyButton.setText("Generate Genny ModelHandler Classes");
			fac.applyTo(gennyButton);

			setControl(comp);
			hookListeners();
			init();

			path = sourcePathText.getText();
			packageName = packageNameText.getText();
		}

		protected void init() {
			IPreferenceStore ps = Activator.getDefault().getPreferenceStore();
			String tmp = ps.getString(P_LASTSOURCEPATH);
			sourcePathText.setText(tmp);

			tmp = ps.getString(P_LASTPACKAGENAME);
			packageNameText.setText(tmp);
			
			gennyButton.setSelection(ps.getBoolean(P_LASTGENNYAGENERATION));
			kuriaButton.setSelection(ps.getBoolean(P_LASTKURIAGENERATION));
			
			generateGennyClasses = gennyButton.getSelection();
			kuriaButton.setEnabled(!generateGennyClasses);
			if (generateGennyClasses) {
				generateKuriaAnnotations = true;
				kuriaButton.setSelection(true);
			}
		}

		private void hookListeners() {
			browseButton.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					DirectoryDialog dlg = new DirectoryDialog(getShell());
					String s = dlg.open();
					if (s != null) {
						sourcePathText.setText(s);
					}
				}
			});

			sourcePathText.addModifyListener(new ModifyListener() {

				@Override
				public void modifyText(ModifyEvent e) {
					path = sourcePathText.getText();
				}
			});

			packageNameText.addModifyListener(new ModifyListener() {

				@Override
				public void modifyText(ModifyEvent e) {
					packageName = packageNameText.getText();
				}
			});

			kuriaButton.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					generateKuriaAnnotations = kuriaButton.getSelection();
				}
			});

			gennyButton.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					generateGennyClasses = gennyButton.getSelection();
					kuriaButton.setEnabled(!generateGennyClasses);
					if (generateGennyClasses) {
						generateKuriaAnnotations = true;
						kuriaButton.setSelection(true);
					}
				}
			});
		}

		
		
		@Override
		public boolean isPageComplete() {
			File file = new File(sourcePathText.getText());
			if (!file.isDirectory())
				return false;

			for (char c : packageNameText.getText().toCharArray()) {
				if (!(Character.isLetterOrDigit(c) || (c == '.')))
					return false;
			}

			path = sourcePathText.getText();
			if (!(path.endsWith("/")))
				path += "/";
			packageName = packageNameText.getText();

			return true;
		}

	}
}
