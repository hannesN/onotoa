package de.topicmapslab.tmcledit.export.wizards;

import java.io.FileOutputStream;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.IWorkbench;
import org.tinytim.mio.XTM20TopicMapWriter;

import de.topicmapslab.tmcledit.export.builder.TinyTiMTopicMapBuilder;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.Node;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;

public class XTMExportWizard extends Wizard implements IExportWizard {
	private Text text;
	
	private TopicMapSchema schema;
	
	public XTMExportWizard() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean performFinish() {
		if (schema==null)
			throw new RuntimeException("No topic map schema selected.");
		
		TinyTiMTopicMapBuilder ttbuilder = new TinyTiMTopicMapBuilder(schema);
		
		
		java.io.File file = new java.io.File(text.getText());
		
		if (file.exists())
			file.delete();
		try
		{
			FileOutputStream os = new FileOutputStream(file);
			XTM20TopicMapWriter writer = new XTM20TopicMapWriter(os, "http://wasauchimmer.de/");
			writer.write(ttbuilder.getTopicMap(false));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return true;
	}

	@Override
	public void addPages() {
		FileSelectionWizardPage page1 = new FileSelectionWizardPage();
		page1.setFileExtensions(getFilterExtensions());
		addPage(page1);
	}
	
	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		if (selection.isEmpty())
			return;
		
		if (!(selection.getFirstElement() instanceof EObject))
			return;
		
		EObject obj = (EObject) selection.getFirstElement();
		if (obj instanceof TopicMapSchema) {
			schema = (TopicMapSchema) obj;
		} else if (obj instanceof File) { 
			schema = ((File)obj).getTopicMapSchema();
		} else if (obj.eContainer() instanceof TopicType) {
			schema = (TopicMapSchema) obj.eContainer().eContainer();
		} else if (obj.eContainer() instanceof TopicMapSchema) {
			schema = (TopicMapSchema) obj.eContainer();
		} else if (obj.eContainer() instanceof Diagram) {
			File file = (File) obj.eContainer().eContainer();
			schema = file.getTopicMapSchema();
		} else if (obj.eContainer() instanceof Node) {
			File file = (File) obj.eContainer().eContainer().eContainer();
			schema = file.getTopicMapSchema();
		} else if (obj instanceof Diagram) {
			File file = (File) obj.eContainer();
			schema = file.getTopicMapSchema();
		}
		
	}

	@Override
	public void createPageControls(Composite pageContainer) {
		Composite comp = new Composite(pageContainer, SWT.None);
		comp.setLayout(new GridLayout(3, false));
		
		Label l = new Label(comp, SWT.NONE);
		l.setText("&Filename");
		
		text = new Text(comp, SWT.BORDER);
		text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		Button browseButton = new Button(comp, SWT.PUSH);
		browseButton.setText("...");
		browseButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog dlg = new FileDialog(text.getShell());
				dlg.setFilterExtensions(getFilterExtensions());
				String file = dlg.open();
				if (file!=null) {
					if (!file.endsWith(".xtm"))
						file+=".xtm";
					text.setText(file);
				}
			}
		});
	}

	
	public String[] getFilterExtensions() {
		return new String[]{"*.xtm"};
	}
}
