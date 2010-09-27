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
package de.topicmapslab.onotoa.aranuka.codegen.model;

import de.topicmapslab.kuria.swtgenerator.edit.IContentProvider;

/**
 * Contentprovider for the weight combo box
 * 
 * @author Hannes Niederhausen
 * 
 */
public class GeneratorDataContentProvider implements IContentProvider {

	private static Integer[]vals = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object[] getElements(String name, Object model) {
		if ("weight".equals(name))
			return vals;
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean hasContent(String arg0, Object arg1) {
		if ("weight".equals(arg0))
			return true;
		
		return false;
	}

}
