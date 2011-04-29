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
package de.topicmapslab.tmcledit.model.views.pages;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.commands.RenameDiagramCommand;

/**
 * Property page for diagrams
 * 
 * @author Hannes Niederhausen
 *
 */
public class DiagramPage extends AbstractEMFModelPage {

	private Text nameText;
	private ControlDecoration nameDecorator;
	
	/**
	 * Constructor
	 */
	public DiagramPage() {
		super("diagram");
	}

	@Override
	public void updateUI() {
		super.updateUI();
		nameText.setText(((Diagram) getModel()).getName());
	}
	
	@Override
	public boolean canAnnotate() {
		return false;
	}

	/**
	 * Creates the widget of the page 
	 *  
	 * @param parent the parent widget
	 * @return the widget of the page
	 */
	public Composite createPage(Composite parent) {
		FormToolkit toolkit = new FormToolkit(parent.getDisplay());
		Composite comp = toolkit.createComposite(parent);
		GridLayout layout = new GridLayout(2, false);
		layout.horizontalSpacing=10;
		comp.setLayout(layout);

		toolkit.createLabel(comp, "Name:");
		nameText = toolkit.createText(comp, "", SWT.BORDER);
		nameText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		nameDecorator = new ControlDecoration(nameText, SWT.LEFT|SWT.TOP);
		nameDecorator.setMarginWidth(2);
		nameDecorator.setShowOnlyOnFocus(true);
		
		nameText.addModifyListener(new ModifyListener() {
			
			public void modifyText(ModifyEvent e) {
				Diagram d = getDiagramByName(nameText.getText());
				if ( (d!=null) && (!d.equals(getModel())) ){
					nameDecorator.setImage(FieldDecorationRegistry.getDefault().getFieldDecoration(FieldDecorationRegistry.DEC_ERROR).getImage());
					nameDecorator.setDescriptionText("Name already in use!");
					nameDecorator.show();
				} else {
					nameDecorator.hide();
				}
			}
		});
		hookNameTextListeners();
		return comp;
	}

	private Diagram getDiagramByName(String name) {
		File file = (File) getModel().eContainer();
		for (Diagram d : file.getDiagrams()) {
			if (d.getName().equals(name))
				return d;
		}
		return null;
	}
	
	@Override
	protected void createItems(CTabFolder folder) {
		super.createItems(folder);
		CTabItem item = new CTabItem(folder, SWT.NONE, SWT.NONE);
		item.setText("Diagram");

		item.setControl(createPage(folder));
	}

	@Override
	protected void setEnabled(boolean enabled) {
		nameText.setEnabled(enabled);
	}

	private void hookNameTextListeners() {
		nameText.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String newName = nameText.getText();

				if (newName.length() == 0)
					return;

				if (getDiagramByName(newName)!=null) {
					nameText.setText(((Diagram)getModel()).getName());
					return;
				}
				
					
				
				getCommandStack()
						.execute(
								new RenameDiagramCommand(newName,
										(Diagram) getModel()));

			}
		});

	}

	@Override
	protected boolean hasDocumentation() {
		return false;
	}

	public void notifyChanged(Notification notification) {
		if (notification.getEventType()==Notification.REMOVING_ADAPTER)
			return;
		updateUI();
	}
}