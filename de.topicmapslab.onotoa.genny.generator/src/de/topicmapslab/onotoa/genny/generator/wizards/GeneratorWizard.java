/**
 * 
 */
package de.topicmapslab.onotoa.genny.generator.wizards;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.dialogs.PreferencesUtil;

import de.topicmapslab.kuria.annotation.AnnotationBindingFactory;
import de.topicmapslab.kuria.swtgenerator.WidgetGenerator;
import de.topicmapslab.kuria.swtgenerator.edit.IInputMaskListener;
import de.topicmapslab.kuria.swtgenerator.edit.InputMask;
import de.topicmapslab.onotoa.genny.generator.Activator;
import de.topicmapslab.onotoa.genny.generator.dialog.GenerationProgressDialog;
import de.topicmapslab.onotoa.genny.generator.model.GeneratorData;
import de.topicmapslab.onotoa.genny.generator.preferences.GennyPreferencePage;
import de.topicmapslab.onotoa.genny.generator.preferences.IPreferenceConstants;
import de.topicmapslab.onotoa.genny.generator.ui.ITextListener;
import de.topicmapslab.onotoa.genny.generator.util.ProcessInputReader;

/**
 * Wizard for generating the application.
 * 
 * @author Hannes Niederhausen
 * 
 */
public class GeneratorWizard extends Wizard implements IExportWizard {

	private GeneratorData data;
	private GeneratorWizardPage page;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean performFinish() {
		page.persist();

		
		getContainer().updateButtons();
		
		String path = getNewPath();
		if (path==null)
			return true;
		
		IPreferenceStore ps = Activator.getDefault().getPreferenceStore();
		path = ps.getString(IPreferenceConstants.P_MAVEN_HOME);
		data.setMavenpath(path);
		data.setMavenOpts(ps.getString(IPreferenceConstants.P_MAVEN_OPTS));
		data.save(ps);
		int result = new GenerationProgressDialog(getShell(), data).run();
		
		return result==Dialog.OK;
	}

	/**
	 * Opens the preference dialog if no or an invalid path is set. Does this until the path is valid or the user aborted the operation.
	 * @return <code>null</code> if aborted, the valid path else
	 */
	protected String getNewPath() {
		// check if maven path is set
		IPreferenceStore ps = Activator.getDefault().getPreferenceStore();
		String path = ps.getString(IPreferenceConstants.P_MAVEN_HOME);
	    while ((path.length()==0) || (!isValidMaven(path))) {
			boolean result = MessageDialog.openQuestion(getShell(), "Error.", "The path to Maven 3 is not set. Do you want to open the preference page to set it?");
			if (!result) {
				// we just quit and don't export
				return null;
			} else {
				PreferenceDialog dlg = PreferencesUtil.createPreferenceDialogOn(getShell(), GennyPreferencePage.ID, null, null);
				dlg.open();
			}
			path = ps.getString(IPreferenceConstants.P_MAVEN_HOME);
		}
	    return path;
    }


	private boolean isValidMaven(String path) {
		File f = new File(path+"/bin/mvn");
		if (!f.exists())
			return false;
		
		ProcessInputReader pir = null;
		try {
			String osName = System.getProperty("os.name");
			String javaHome = System.getProperty("java.home");
			
			String cmd[] = new String[] {f.getAbsolutePath(), "--version"};
			if (osName.toLowerCase().startsWith("windows")) {
				cmd = new String[] {"cmd.exe", "/C", f.getAbsolutePath(), "--version"};
			} 
			
			List<String> envs = new ArrayList<String>();
			for (Entry<String, String> e : System.getenv().entrySet()) {
				envs.add(e.getKey()+"="+e.getValue());
			}
			envs.add("JAVA_HOME="+javaHome);
			
	        Process p = Runtime.getRuntime().exec(cmd, envs.toArray(new String[envs.size()]));
	        
	        ProcessListener pl = new ProcessListener();
			pir = new ProcessInputReader(p, pl);

	        // starting read thread and wait for process to finish
	        pir.start();
	        p.waitFor();

	        return pl.isCorrectVersion();
	        
        } catch (Exception e) {
        	Activator.logException(e);
        	return false;
        } finally {
        	// t be sure we don't produce zombies
        	if ((pir!=null) && (!pir.isInterrupted()))
        		pir.interrupt();
        }
    }

	@Override
	public void addPages() {
		page = new GeneratorWizardPage();
		addPage(page);
	}

	private class GeneratorWizardPage extends WizardPage implements IInputMaskListener {

		private InputMask im;

		protected GeneratorWizardPage() {
			super("Generator Data...");
		}

		@Override
		public void createControl(Composite parent) {
			Composite comp = new Composite(parent, SWT.NONE);
			comp.setLayout(new GridLayout());
			
			AnnotationBindingFactory fac = new AnnotationBindingFactory();
			fac.addClass(GeneratorData.class);
			
			WidgetGenerator gen = new WidgetGenerator(fac.getBindingContainer());
			
			im = gen.generateEditable(GeneratorData.class, comp);
			im.getComposite().setLayoutData(new GridData(GridData.FILL_BOTH));
			
			data = new GeneratorData();
			data.init(Activator.getDefault().getPreferenceStore());
			im.setModel(data);
			
			im.addInputMaskListeners(this);
			
			setControl(comp);
		}

		private void persist() {
			im.persist();
			setPageComplete(false);
		}
		
		@Override
        public void dirtyChanged() {
			Collection<String> msgs = im.getErrorMessages();
			if (msgs.size()>0) {
				setErrorMessage(msgs.iterator().next());
			} else {
				setErrorMessage(null);
			}
			setPageComplete(im.isValid());
        }

		@Override
        public void newModelElement(Object arg0) {
        }
	}
	
	
	private class ProcessListener implements ITextListener {
		boolean correctVersion = false;
		
		public boolean isCorrectVersion() {
	        return correctVersion;
        }
		
		@Override
		public void newText(String text) {
			if (text.contains("Apache Maven 3"))
				correctVersion = true;		    
		}
	}
}
