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
package de.topicmapslab.tmcledit.extensions.views.pages;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.commands.GenericSetCommand;

public class TopicMapSchemaPropertyPage extends AbstractModelPage {

	private Button ttButton;
	private Button atButton;
	private Button ntButton;
	private Button stButton;
	private Button otButton;
	private Button rtButton;
	
	public TopicMapSchemaPropertyPage() {
		super("topicmapschema");
	}
	
	@Override
	public void updateUI() {
		if (ttButton!=null) {
			ttButton.setSelection(getCastedModel().isActiveTopicTypeConstraint());
			rtButton.setSelection(getCastedModel().isActiveRoleTypeConstraint());
			atButton.setSelection(getCastedModel().isActiveAssociationTypeConstraint());
			stButton.setSelection(getCastedModel().isActiveScopeTypeConstraint());
			ntButton.setSelection(getCastedModel().isActiveNameTypeConstraint());
			otButton.setSelection(getCastedModel().isActiveOccurenceTypeConstraint());
		}
	}

	public TopicMapSchema getCastedModel() {
		return (TopicMapSchema) getModel();
	}
	
	@Override
	public void createControl(Composite parent) {
		FormToolkit toolkit = new FormToolkit(parent.getDisplay());
		
		Section section = toolkit.createSection(parent, SWT.TITLE);
		section.setText("Topic Map Schema");
		Composite comp = toolkit.createComposite(section);
		comp.setLayoutData(new GridData(GridData.FILL_BOTH));
		comp.setLayout(new GridLayout());
		section.setClient(comp);
		
		Section typeConstSection = toolkit.createSection(comp, Section.TITLE_BAR);
		typeConstSection.setText("Type constraint activation");
		
		comp = toolkit.createComposite(typeConstSection);
		comp.setLayout(new GridLayout());
		comp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		typeConstSection.setClient(comp);
		ttButton = createCheckButton(toolkit, comp, "Topic Type Constraint",
				ModelPackage.TOPIC_MAP_SCHEMA__ACTIVE_TOPIC_TYPE_CONSTRAINT);
		
		rtButton = createCheckButton(toolkit, comp, "Role Type Constraint",
				ModelPackage.TOPIC_MAP_SCHEMA__ACTIVE_ROLE_TYPE_CONSTRAINT);
		
		atButton = createCheckButton(
				toolkit,
				comp,
				"Association Type Constraint",
				ModelPackage.TOPIC_MAP_SCHEMA__ACTIVE_ASSOCIATION_TYPE_CONSTRAINT);
		
		stButton = createCheckButton(toolkit, comp, "Scope Type Constraint",
				ModelPackage.TOPIC_MAP_SCHEMA__ACTIVE_SCOPE_TYPE_CONSTRAINT);
		
		ntButton = createCheckButton(toolkit, comp, "Name Type Constraint",
				ModelPackage.TOPIC_MAP_SCHEMA__ACTIVE_NAME_TYPE_CONSTRAINT);
		
		otButton = createCheckButton(toolkit, comp,
				"Occurence Type Constraint",
				ModelPackage.TOPIC_MAP_SCHEMA__ACTIVE_OCCURENCE_TYPE_CONSTRAINT);
		
		setControl(section);
	}
	
	private Button createCheckButton(FormToolkit toolkit, Composite parent, String msg, final int featureID) {
		final Button tmp = toolkit.createButton(parent, msg, SWT.CHECK);
		tmp.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				GenericSetCommand cmd = new GenericSetCommand(getModel(), featureID, tmp.getSelection());
				getCommandStack().execute(cmd);
			}	
		});
		
		return tmp;
	}

	@Override
	public void notifyChanged(Notification notification) {
		updateUI();
	}

}
