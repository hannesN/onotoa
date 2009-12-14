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
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.IWorkbench;
import org.tinytim.mio.CTMTopicMapWriter;
import org.tmapi.core.TopicMap;

import de.topicmapslab.tmcledit.export.builder.TMCLTopicMapBuilder;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.MappingElement;
import de.topicmapslab.tmcledit.model.Node;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;

public class TMCLExportWizard extends Wizard implements IExportWizard {

	private TopicMapSchema schema;

	private CTMFileSelectionWizardPage page;

	public TMCLExportWizard() {
	}

	@Override
	public boolean performFinish() {
		if (schema == null)
			schema = ModelIndexer.getInstance().getTopicMapSchema();

		java.io.File file = new java.io.File(page.getFilename());

		if (file.exists()) {
			if (!MessageDialog.openQuestion(getShell(), "File already exists",
			        "File already exists. Do you want to overwrite it?"))
				return false;

			file.delete();

		}
		try {
			TMCLTopicMapBuilder builder = new TMCLTopicMapBuilder(schema, page.isExportSchemaInfos());
			TopicMap tm = builder.createTopicMap();
			FileOutputStream stream = new FileOutputStream(file);
			CTMTopicMapWriter writer = new CTMTopicMapWriter(stream, schema.getBaseLocator());
			for (MappingElement me : schema.getMappings()) {
				writer.addPrefix(me.getKey(), me.getValue());
			}
			writer.setTMCL(true);
			writer.setAuthor("Onotoa");
			writer.setComment("This schema was created with onotoa\nhttp://onotoa.topicmapslab.de");
			writer.write(tm);
			stream.flush();
			stream.close();

		} catch (Exception e) {
			MessageDialog.openError(getShell(), "Export Error!", "An error occurred while exporting: "+e.getMessage());
			throw new RuntimeException(e);
		}

		return true;
	}

	@Override
	public void addPages() {
		page = new CTMFileSelectionWizardPage();
		page.setFileExtensions(getFilterExtensions());
		page.setTitle("TMCL Export - File selection");
		addPage(page);
	}

	public void init(IWorkbench workbench, IStructuredSelection selection) {
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
		return new String[] { "*.ctm" };
	}

	private class CTMFileSelectionWizardPage extends FileSelectionWizardPage {
		private boolean exportSchemaInfos = false;

		public boolean isExportSchemaInfos() {
			return exportSchemaInfos;
		}

		@Override
		public void addAdditionalWidgets(Composite parent) {
			Button exportSchemainfosbButton = new Button(parent, SWT.CHECK);
			exportSchemainfosbButton.setText("Export Schema Infos");
			exportSchemainfosbButton.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					exportSchemaInfos = ((Button) e.widget).getSelection();
				}
			});
		}
	}
}
