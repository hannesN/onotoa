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

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.IWorkbench;
import org.tmapi.core.Locator;
import org.tmapi.core.Topic;
import org.tmapi.core.TopicMap;
import org.tmapix.io.LTMTopicMapWriter;
import org.tmapix.io.TopicMapWriter;
import org.tmapix.io.XTM2TopicMapWriter;
import org.tmapix.io.XTMVersion;

import de.topicmapslab.ctm.writer.core.CTMTopicMapWriter;
import de.topicmapslab.ctm.writer.templates.Template;
import de.topicmapslab.tmcledit.export.Activator;
import de.topicmapslab.tmcledit.export.builder.TMCLTemplateDefinitions;
import de.topicmapslab.tmcledit.export.builder.TMCLTopicMapBuilder;
import de.topicmapslab.tmcledit.export.voc.Namespaces.TMCL;
import de.topicmapslab.tmcledit.export.voc.Namespaces.TMDM;
import de.topicmapslab.tmcledit.export.wizards.TMCLExportWizardPage.FileType;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.MappingElement;
import de.topicmapslab.tmcledit.model.TopicMapSchema;

/**
 * Wizard to export to a topic map serialization
 * @author Hannes Niederhausen
 *
 */
public class TMCLExportWizard extends Wizard implements IExportWizard {

	private TopicMapSchema schema;

	private TMCLExportWizardPage page;

	@Override
	public boolean performFinish() {
		if (schema == null) {
			File onotoaFile = Activator.getDefault().getSelectionService().getOnotoaFile();
			if (onotoaFile!=null)
				schema = onotoaFile.getTopicMapSchema();
			else {
				MessageDialog.openError(getShell(), "Error!", "No onotoa file opened!");
				return true;
			}
		}
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
			builder.setExportAnnotations(page.isExportAnnotations());
			builder.setExportSchema(page.isExportSchemaInfos());
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
	    FileType.Type type = page.getSelectedFileType().type;
	    
	    try {
	    	switch (type) {
			case CTM:
				CTMTopicMapWriter writer = new CTMTopicMapWriter(stream, baseLocator);
		        
		        for (MappingElement me : schema.getMappings()) {
		        	writer.setPrefix(me.getKey(), me.getValue());
		        }
		        
		        writer.setPrefix("tmdm", "http://psi.topicmaps.org/iso13250/model/");
		        writer.setPrefix("tmcl", "http://psi.topicmaps.org/tmcl/");
		        writer.setPrefix("xsd", "http://www.w3.org/2001/XMLSchema#");
		        writer.setPrefix("iso", "http://psi.topicmaps.org/iso13250/");
		        
		        
		        writer.addInclude("http://www.isotopicmaps.org/tmcl/templates.ctm");
		        // template detection doesn't work :( though modifications of TMQL
		        // TODO rewrite scanner
		        TMCLTemplateDefinitions def = new TMCLTemplateDefinitions(writer, topicMap);
		        for (Template tmpl : def.getTemplates()) {
		        	tmpl.setSerialize(false);
		        	writer.addTemplate(tmpl);
		        }
		        
		        for (Topic t : topicMap.getTopics()) {
		        	if (t.getSubjectIdentifiers().size()==1) {
		        		Locator si = t.getSubjectIdentifiers().iterator().next();
						if ( (si.toExternalForm().startsWith(TMCL.PREFIX))
							||(si.toExternalForm().startsWith(TMDM.PREFIX)) ){
							writer.addIgnoredConstruct(t);
						}
		        	}
		        }
		        
		        
		        return writer;
			case LTM:
				return new LTMTopicMapWriter(stream, baseLocator);
			case XTM_2_0:
				XTM2TopicMapWriter xtmWriter = new XTM2TopicMapWriter(stream, baseLocator, XTMVersion.XTM_2_0);
				if (page.isUseIndention()) {
					xtmWriter.setPrettify(true);
				}
				return xtmWriter;
			case XTM_2_1:
				xtmWriter = new XTM2TopicMapWriter(stream, baseLocator, XTMVersion.XTM_2_1);
				if (page.isUseIndention()) {
					xtmWriter.setPrettify(true);
				}
				return xtmWriter;
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
	}
}
