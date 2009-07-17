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

import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Hyperlink;
import org.eclipse.ui.forms.widgets.Section;

import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.OccurrenceTypeConstraint;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.commands.GenericSetCommand;
import de.topicmapslab.tmcledit.model.dialogs.FilterTopicSelectionDialog;
import de.topicmapslab.tmcledit.model.dialogs.NewTopicTypeWizard;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;

public class OccurrenceConstraintDetailPage extends AbstractConstraintModelPage {
	
	private Text typeText;
	private Button typeButton;
	private Button uniqueButton;
	private Section section;
	
	private OccurrenceTypeModelPage typeModelPage;
	private CTabItem item;
	
	public OccurrenceConstraintDetailPage() {
		super("occurrence constraint");
	}
	
	@Override
	protected void createItems(CTabFolder folder) {
		super.createItems(folder);
		FormToolkit toolkit = new FormToolkit(folder.getDisplay());
		
		item = new CTabItem(folder, SWT.NONE);
		item.setText("Occurrence Constraint Properties");
		item.setControl(createConstraintComposite(folder, toolkit));
		
		
		typeModelPage = new OccurrenceTypeModelPage();
		typeModelPage.createItems(folder);
		
		hookButtonListener();
	}
	
	@Override
	protected void setEnabled(boolean enabled) {
		item.getControl().setEnabled(enabled);
	}

	private Composite createConstraintComposite(Composite parent, FormToolkit toolkit) {
		section = toolkit.createSection(parent, Section.EXPANDED
				| Section.TITLE_BAR);
		section.setText("Occurrence Constraint");
		Composite comp = toolkit.createComposite(section);
		comp.setLayout(new GridLayout(2, false));
		
		Hyperlink link = toolkit.createHyperlink(comp, "Type:", SWT.NONE);
		link.addHyperlinkListener(new HyperlinkAdapter() {
			@Override
			public void linkActivated(HyperlinkEvent e) {
				NewTopicTypeWizard wizard = new NewTopicTypeWizard();
				wizard.setDefaultType(KindOfTopicType.OCCURRENCE_TYPE);
				WizardDialog dlg = new WizardDialog(section.getShell(), wizard);
				
				if (dlg.open()==Dialog.OK) {
					TopicType tt = wizard.getNewTopicType();
					ModelIndexer.getInstance().getTopicMapSchema().getTopicTypes().add(tt);
					getCastedModel().setType(tt);
				}
				
			}
		});

		createTypeComposite(toolkit, comp);
		
		createCommonConstraintControls(comp, toolkit);
		
		toolkit.createLabel(comp, "Unique:");
		uniqueButton = toolkit.createButton(comp, "", SWT.CHECK);
		section.setClient(comp);
		
		return section;
	}

	private void createTypeComposite(FormToolkit toolkit, Composite parent) {
		Composite typeComp = toolkit.createComposite(parent);
		typeComp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		GridLayout layout = new GridLayout(2, false);
		layout.marginWidth = 0;
		typeComp.setLayout(layout);
		typeText = toolkit.createText(typeComp, "", SWT.BORDER);
		typeText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		typeButton = toolkit.createButton(typeComp, "...", SWT.PUSH);
	}

	private void hookButtonListener() {
		typeButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FilterTopicSelectionDialog dlg = new FilterTopicSelectionDialog(typeButton.getShell(), KindOfTopicType.OCCURRENCE_TYPE);
				if (Dialog.OK==dlg.open()) {
					getCommandStack().execute(
									new GenericSetCommand(
											getModel(),
											ModelPackage.OCCURRENCE_TYPE_CONSTRAINT__TYPE,
											dlg.getFirstResult()));
				}
			}
		});
		
		uniqueButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				getCommandStack().execute(
						new GenericSetCommand(
								getModel(),
								ModelPackage.OCCURRENCE_TYPE_CONSTRAINT__UNIQUE,
								new Boolean(uniqueButton.getSelection())));
			}
		});
	}

	@Override
	public void updateUI() {
		OccurrenceTypeConstraint castedModel = getCastedModel();
		if (castedModel.getType()!=null) 
			typeText.setText(castedModel.getType().getName());
		else
			typeText.setText("");
		uniqueButton.setSelection(castedModel.isUnique());
		
		super.updateUI();
	}
	
	@Override
	public void setModel(Object model) {
		super.setModel(model);
		if (typeModelPage!=null)
			typeModelPage.setModel(getCastedModel().getType());
	}
	
	protected OccurrenceTypeConstraint getCastedModel() {
		return (OccurrenceTypeConstraint) getModel();
	}
	
	@Override
	public void setCommandStack(CommandStack commandStack) {
		super.setCommandStack(commandStack);
		if (typeModelPage!=null)
			typeModelPage.setCommandStack(commandStack);
	}
	
	@Override
	public void notifyChanged(Notification notification) {
		if (notification.getFeatureID(TopicType.class)==ModelPackage.OCCURRENCE_TYPE_CONSTRAINT__TYPE)
			typeModelPage.setModel(notification.getNewValue());
		super.notifyChanged(notification);
		
	}
}
