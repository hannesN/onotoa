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
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.IWorkbench;
import org.tmapi.core.TopicMap;
import org.tmapix.io.LTMTopicMapWriter;
import org.tmapix.io.TopicMapWriter;
import org.tmapix.io.XTM2TopicMapWriter;
import org.tmapix.io.XTM2TopicMapWriter.Version;

import de.topicmapslab.ctm.writer.core.CTMTopicMapWriter;
import de.topicmapslab.tmcledit.export.Activator;
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

	private TMCLExportWizardPage page;

	public TMCLExportWizard() {
	}

	@Override
	public boolean performFinish() {
		if (schema == null)
			schema = ModelIndexer.getInstance().getTopicMapSchema();

		java.io.File file = new java.io.File(page.getFileName());

		if (file.exists()) {
			if (!MessageDialog.openQuestion(getShell(), "File already exists",
			        "File already exists. Do you want to overwrite it?"))
				return false;

			file.delete();

		}
		try {
			TMCLTopicMapBuilder builder = new TMCLTopicMapBuilder(schema, page.isExportSchemaInfos(), page.isExportDiagramInfos());
			builder.setExportTopicTypesOnly(page.isExportTopicTypes());
			TopicMap tm = builder.createTopicMap();
			FileOutputStream stream = new FileOutputStream(file);
			
			TopicMapWriter writer = getTopicMapWriter(stream, schema.getBaseLocator(), tm);
			writer.write(tm);
			stream.flush();
			stream.close();

			
			Activator.getDefault().getPreferenceStore().putValue("exported_file", page.getFileName());
		} catch (Exception e) {
			MessageDialog.openError(getShell(), "Export Error!", "An error occurred while exporting: "+e.getMessage());
			throw new RuntimeException(e);
		}

		return true;
	}

	private TopicMapWriter getTopicMapWriter(FileOutputStream stream, String baseLocator, TopicMap topicMap) {
	    String suffix = page.getFileSuffix();
		
	    try {
	        if ("xtm".equals(suffix)) {
		        return new XTM2TopicMapWriter(stream, baseLocator, Version.XTM_20);
	        }
	        if ("ltm".equals(suffix)) {
		        return new LTMTopicMapWriter(stream, baseLocator);
	        }
	        if ("ctm".equals(suffix)) {
		        CTMTopicMapWriter writer = new CTMTopicMapWriter(stream, baseLocator);
		        
		        for (MappingElement me : schema.getMappings()) {
		        	writer.setPrefix(me.getKey(), me.getValue());
		        }
		        writer.setPrefix("tmcl", "http://psi.topicmaps.org/tmcl/");
		        writer.setPrefix("xsd", "http://www.w3.org/2001/XMLSchema#");
		        writer.setPrefix("iso", "http://psi.topicmaps.org/iso13250/");
		        
		        writer.addInclude("http://www.isotopicmaps.org/tmcl/templates.ctm");
		        // template detection doesn't work :(
//		        TMCLTemplateDefinitions def = new TMCLTemplateDefinitions(writer, topicMap);
//		        for (Template tmpl : def.getTemplates()) {
//		        	tmpl.setSerialize(false);
//		        	writer.addTemplate(tmpl);
//		        }
		        
		        return writer;
	        }
        } catch (Exception e) {
	        throw new RuntimeException(e);
        }
		return null;
    }

	@Override
	public void addPages() {
		page = new TMCLExportWizardPage("exportpage");
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

}
