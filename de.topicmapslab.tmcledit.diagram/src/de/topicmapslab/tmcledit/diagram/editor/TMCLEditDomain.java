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
/**
 * 
 */
package de.topicmapslab.tmcledit.diagram.editor;

import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.ui.IEditorPart;

/**
 * Implementation of the {@link IOnotoaEditDomain} interface
 * 
 * @author Hannes Niederhausen
 *
 */
public class TMCLEditDomain extends DefaultEditDomain implements IOnotoaEditDomain {

	private EditingDomain editingDomain;
	
	/**
	 * Constructor 
	 * 
	 * @param editorPart the editor part containing the diagram
	 */
	public TMCLEditDomain(IEditorPart editorPart) {
		super(editorPart);
	}

	public void setEditingDomain(EditingDomain editingDomain) {
		this.editingDomain = editingDomain;
	}
	
	public EditingDomain getEditingDomain() {
		return editingDomain;
	}
	
}