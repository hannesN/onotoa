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

import java.io.InputStream;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import de.topicmapslab.tmcledit.model.File;

/**
 * @author Hannes Niederhausen
 *
 */
public class EMFModelDeserialzer implements ModelDeserializer {

	public File deserialize(String filename) {
		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> map = reg.getExtensionToFactoryMap();
		map.put("tmcl", new XMIResourceFactoryImpl());
		map.put("ono", new XMIResourceFactoryImpl());
		ResourceSet resSet = new ResourceSetImpl();
		
		URI uri = URI.createFileURI(filename);
		return (File) resSet.getResource(uri, true).getContents().get(0);
	}

	public String getVersionString() {
		return "EMF";
	}

	public File deserialize(InputStream is) {
	    throw new UnsupportedOperationException("Method not supported. Use public File deserialize(String filename) instead.");
    }

}
