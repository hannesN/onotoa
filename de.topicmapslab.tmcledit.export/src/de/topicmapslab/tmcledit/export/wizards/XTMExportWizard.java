/*******************************************************************************
 * Copyright (c) 2008, 2009 Topic Maps Lab and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Hannes Niederhausen - initial API and implementation
 *******************************************************************************/
package de.topicmapslab.tmcledit.export.wizards;

import java.io.FileOutputStream;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.IWorkbench;
import org.tinytim.mio.XTM20TopicMapWriter;

import de.topicmapslab.tmcledit.export.builder.TinyTiMTopicMapBuilder;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.Node;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;

public class XTMExportWizard extends Wizard implements IExportWizard {
	private boolean exportConstraintInfos = true;
	private TopicMapSchema schema;
	private WizardPage page1;

	public XTMExportWizard() {
	}

	@Override
	public boolean performFinish() {
		if (schema == null)
			schema = ModelIndexer.getInstance().getTopicMapSchema();

		TinyTiMTopicMapBuilder ttbuilder = new TinyTiMTopicMapBuilder(schema);

		java.io.File file = new java.io.File(page1.getFilename());

		if (file.exists()) {
			if (!MessageDialog.openQuestion(getShell(), "File already exists",
			        "File already exists. Do you want to overwrite it?"))
				return false;

			file.delete();

		}
		try {
			FileOutputStream os = new FileOutputStream(file);
			XTM20TopicMapWriter writer = new XTM20TopicMapWriter(os, "http://psi.topicmapslab.de/tmclschema");
			writer.write(ttbuilder.getTopicMap(exportConstraintInfos));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return true;
	}

	@Override
	public void addPages() {

		page1 = new WizardPage();
		page1.setFileExtensions(getFilterExtensions());
		page1.setTitle("XTM Export - File selection");
		addPage(page1);

	}

	public void init(IWorkbench workbench, IStructuredSelection selection) {
		if (selection.isEmpty())
			return;

		if (!(selection.getFirstElement() instanceof EObject))
			return;

		EObject obj = (EObject) selection.getFirstElement();
		if (obj instanceof TopicMapSchema) {
			schema = (TopicMapSchema) obj;
		} else if (obj instanceof File) {
			schema = ((File) obj).getTopicMapSchema();
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

	public String[] getFilterExtensions() {
		return new String[] { "*.xtm" };
	}

	private class WizardPage extends FileSelectionWizardPage {
		@Override
		public void addAdditionalWidgets(Composite parent) {
			Button exportButton = new Button(parent, SWT.CHECK);
			exportButton.setSelection(exportConstraintInfos);
			exportButton.setText("export constraint infos as occurrences");
			GridData gd = new GridData();
			gd.horizontalSpan = 3;
			exportButton.setLayoutData(gd);
			exportButton.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					exportConstraintInfos = ((Button) e.widget).getSelection();
				}
			});

		}
	}
}
