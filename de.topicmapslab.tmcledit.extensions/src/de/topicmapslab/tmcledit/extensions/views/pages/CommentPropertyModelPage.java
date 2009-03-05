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
package de.topicmapslab.tmcledit.extensions.views.pages;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import de.topicmapslab.tmcledit.model.Comment;

/**
 * @author Hannes Niederhausen
 *
 */
public class CommentPropertyModelPage extends AbstractModelPage {

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

	private Comment getCastedModel() {
		return (Comment) getModel();
	}
	
	@Override
	public void createControl(Composite parent) {
		FormToolkit toolkit = new FormToolkit(parent.getDisplay());
		Section section = toolkit.createSection(parent, Section.EXPANDED|Section.TITLE_BAR);
		section.setText("Comment:");
		
		Composite comp = toolkit.createComposite(section);
		comp.setLayout(new FillLayout());
		
		contentText = new Text(comp, SWT.MULTI|SWT.H_SCROLL|SWT.V_SCROLL|SWT.WRAP|SWT.BORDER);
		contentText.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				getCastedModel().setContent(contentText.getText());				
			}
		});
		section.setClient(comp);
		setControl(section);
	}

	public void notifyChanged(Notification notification) {
		updateUI();
	}

}
