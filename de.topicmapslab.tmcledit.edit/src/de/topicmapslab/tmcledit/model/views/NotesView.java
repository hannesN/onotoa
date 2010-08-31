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
package de.topicmapslab.tmcledit.model.views;

import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.part.ViewPart;

import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.TmcleditEditPlugin;
import de.topicmapslab.tmcledit.model.commands.GenericSetCommand;

/**
 * @author Hannes Niederhausen
 *
 */
public class NotesView extends ViewPart implements ISelectionChangedListener {

	public static final String ID = "de.topicmapslab.tmcledit.model.views.NotesView";
	
	private File file;
	
	private Text notesText;
	
	public NotesView() {
	}

	@Override
	public void init(IViewSite site) throws PartInitException {
	    super.init(site);
	    TmcleditEditPlugin.getPlugin().getOnotoaSelectionService().addSelectionChangedListener(this);
	}
	
	@Override
	public void dispose() {
		TmcleditEditPlugin.getPlugin().getOnotoaSelectionService().removeSelectionChangedListener(this);
		super.dispose();
	}
	
	@Override
	public void createPartControl(Composite parent) {
		FormToolkit toolkit = new FormToolkit(parent.getDisplay());
		Composite comp = toolkit.createComposite(parent);
		comp.setLayout(new FillLayout());
		
		notesText = toolkit.createText(comp, "", SWT.BORDER|SWT.WRAP|SWT.V_SCROLL);
		
		update();

		hookListeners();
	}

	private void hookListeners() {
	    notesText.addFocusListener(new FocusAdapter() {
	    	@Override
	    	public void focusLost(FocusEvent e) {
	    		ModelView view  = ModelView.getInstance();
	    		
	    		if ( (view == null) || (file == null) ){
	    			return;
	    		}
	    		
	    		CommandStack cmdStack = (CommandStack) view.getAdapter(CommandStack.class);
	    		
	    		GenericSetCommand cmd = new GenericSetCommand(file, ModelPackage.FILE__NOTES, notesText.getText());
	    		
	    		cmdStack.execute(cmd);
	    	}
		});
    }

	public void update() {
		String content = "";
		
		file = TmcleditEditPlugin.getPlugin().getOnotoaSelectionService().getOnotoaFile();
		if ( (file!=null) && (file.getNotes()!=null) )
			content = file.getNotes();
		
		notesText.setText(content);
		notesText.setEnabled(file!=null);
    }

	
	@Override
	public void setFocus() {
		notesText.setFocus();
	}

	public void selectionChanged(SelectionChangedEvent event) {
	    update();
    }
}
