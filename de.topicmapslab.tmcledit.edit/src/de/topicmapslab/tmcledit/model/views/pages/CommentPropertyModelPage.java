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
package de.topicmapslab.tmcledit.model.views.pages;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.topicmapslab.tmcledit.model.Comment;
import de.topicmapslab.tmcledit.model.commands.SetCommentContentCommand;

/**
 * @author Hannes Niederhausen
 *
 */
public class CommentPropertyModelPage extends AbstractEMFModelPage {

	private Text contentText;
	
	public CommentPropertyModelPage() {
		super("comment page");
	}

	@Override
	public void updateUI() {
		if (getCastedModel().getContent()==null)
			contentText.setText("");
		else
			contentText.setText(getCastedModel().getContent());

	}

	@Override
	protected void setEnabled(boolean enabled) {
		contentText.setEnabled(enabled);
	}
	
	private Comment getCastedModel() {
		return (Comment) getModel();
	}
	
	@Override
	protected void createItems(CTabFolder folder) {
		super.createItems(folder);
		CTabItem item = new CTabItem(folder, SWT.None);
		item.setText("Comment");
		item.setControl(createPage(folder));
	}
	
	public Control createPage(Composite parent) {
		FormToolkit toolkit = new FormToolkit(parent.getDisplay());
		Composite comp = toolkit.createComposite(parent);
		comp.setLayout(new FillLayout());
		
		contentText = new Text(comp, SWT.MULTI|SWT.H_SCROLL|SWT.V_SCROLL|SWT.WRAP|SWT.BORDER);
		contentText.addFocusListener(new FocusAdapter() {
			
			@Override
			public void focusGained(FocusEvent e) {
			}
			
			@Override
			public void focusLost(FocusEvent e) {
				getCommandStack().execute(new SetCommentContentCommand(getCastedModel(), contentText.getText()));
			}
		});
		contentText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if ( (e.character==SWT.CR) && ((e.stateMask&SWT.MOD1)!=0)) {
					int caretPos = contentText.getCaretPosition();
					getCommandStack().execute(new SetCommentContentCommand(getCastedModel(), contentText.getText()));
					e.doit = false;
					contentText.setSelection(caretPos, caretPos+1);
					
				}
			}
		});
		
		return comp;
	}

	public void notifyChanged(Notification notification) {
		updateUI();
	}
	
	@Override
	protected boolean hasDocumentation() {
		return false;
	}

}
