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
package de.topicmapslab.onotoa.wordlisteditor.editor.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.CheckboxTableViewer;

import de.topicmapslab.onotoa.wordlisteditor.editor.WordListEditor;

/**
 * Action to (de)select all elements in a {@link CheckboxTableViewer}
 * 
 * @author Hannes Niederhausen
 *
 */
public class SelectAllAction extends Action {
	
	private final CheckboxTableViewer viewer;

	private final boolean checked;
	
	private final WordListEditor editor;
	
	/**
	 * Constructor
	 * 
	 * @param viewer viewer with selectable elements
	 * @param checked flag whether the elements should be checked or unchecked
	 */
	public SelectAllAction(WordListEditor editor, CheckboxTableViewer viewer, boolean checked) {
	    super();
	    this.editor = editor;
	    this.viewer = viewer;
	    this.checked = checked;
	    
	    if (checked) {
	    	setText("&Select All");
	    } else {
	    	setText("&Deselect All");
	    }
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void run() {
		viewer.setAllChecked(checked);
		editor.updateButtons();
	}
}
