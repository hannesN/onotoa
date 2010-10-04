/**
 * 
 */
package de.topicmapslab.onotoa.genny.generator.wizards;

import java.util.Collection;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.IWorkbench;

import de.topicmapslab.kuria.annotation.AnnotationBindingFactory;
import de.topicmapslab.kuria.swtgenerator.WidgetGenerator;
import de.topicmapslab.kuria.swtgenerator.edit.IInputMaskListener;
import de.topicmapslab.kuria.swtgenerator.edit.InputMask;
import de.topicmapslab.onotoa.genny.generator.ProjectGenerator;
import de.topicmapslab.onotoa.genny.generator.model.GeneratorData;

/**
 * Wizard for generating the application.
 * 
 * @author Hannes Niederhausen
 * 
 */
public class GeneratorWizard extends Wizard implements IExportWizard {

	private GeneratorData data;
	private GeneratorWizardPage page;
	
	public GeneratorWizard() {
	}

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
		ProjectGenerator gen = new ProjectGenerator();

		StringBuilder builder = new StringBuilder();
		NullProgressMonitor monitor = new NullProgressMonitor();

		gen.generateProjects(data, builder, monitor);
		
		return true;
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
			im.setModel(data);
			
			im.addInputMaskListeners(this);
			
			setControl(comp);
		}

		private void persist() {
			im.persist();
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
}
