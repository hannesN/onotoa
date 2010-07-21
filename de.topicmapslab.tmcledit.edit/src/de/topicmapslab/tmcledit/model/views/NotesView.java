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
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.part.ViewPart;

import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.commands.GenericSetCommand;

/**
 * @author niederhausen
 *
 */
public class NotesView extends ViewPart {

	public static final String ID = "de.topicmapslab.tmcledit.model.views.NotesView";
	
	private File file;
	
	private Text notesText;
	
	public NotesView() {
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
	    		IViewPart view = getViewSite().getWorkbenchWindow().getActivePage().findView(ModelView.ID);
	    		CommandStack cmdStack = (CommandStack) view.getAdapter(CommandStack.class);
	    		
	    		GenericSetCommand cmd = new GenericSetCommand(file, ModelPackage.FILE__NOTES, notesText.getText());
	    		
	    		cmdStack.execute(cmd);
	    	}
		});
    }

	public void update() {
		String content = "";
		
		IWorkbenchWindow workbenchWindow = getViewSite().getWorkbenchWindow();
		IWorkbenchPage activePage = workbenchWindow.getActivePage();
		if (activePage!=null) {
			IViewPart view = activePage.findView(ModelView.ID);
			if (view!=null)
				file = (File) view.getAdapter(File.class);
		}
		if ( (file!=null) && (file.getNotes()!=null) )
			content = file.getNotes();
		
		notesText.setText(content);
		notesText.setEnabled(file!=null);
    }

	
	@Override
	public void setFocus() {
		notesText.setFocus();
	}
}
