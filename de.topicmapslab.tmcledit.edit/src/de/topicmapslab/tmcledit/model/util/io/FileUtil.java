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
package de.topicmapslab.tmcledit.model.util.io;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.workspace.impl.WorkspaceCommandStackImpl;

import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.ModelFactory;

public class FileUtil {

	public static final File loadFile(String path) {
		try {
			java.io.File file = new java.io.File(path);
			File result = null;
			if (file.isDirectory())
				throw new RuntimeException(file.getName() + " is a directory");
			if (!file.exists()) {
				ModelFactory einstance = ModelFactory.eINSTANCE;
				result = einstance.createFile();
				result.setTopicMapSchema(einstance.createTopicMapSchema());
				// createTestData(result);
			} else {
				ModelDeserializer md = new ModelDeserializerFactory().createDeserializer(path);
				result = md.deserialize(path);
			}
			result.setFilename(path);
			
			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public static final void saveFile(File file, EditingDomain editingDomain) throws IOException {
		try {
			java.io.File ioFile = new java.io.File(file.getFilename());
			FileWriter writer = new FileWriter(ioFile);

			ModelSerializeOno1 ms = new ModelSerializeOno1();
			writer.append(ms.serialize(file));
			writer.close();

			if (editingDomain != null) {
				WorkspaceCommandStackImpl cmdStack = (WorkspaceCommandStackImpl) editingDomain.getCommandStack();
				cmdStack.saveIsDone();
				file.setDirty(false);

				String filename = file.getFilename();
				IFile fileRes = ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(new Path(filename));
				if (fileRes != null)
					fileRes.refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public static final void saveFileAs(File file, String newFilename) throws IOException {
		try {
			URI uri = URI.createFileURI(file.getFilename());
			Resource resource = new XMIResourceFactoryImpl().createResource(uri);
			resource.getContents().add(file);

			resource.save(Collections.EMPTY_MAP);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

}
