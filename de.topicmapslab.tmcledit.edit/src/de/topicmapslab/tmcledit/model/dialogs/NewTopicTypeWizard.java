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
package de.topicmapslab.tmcledit.model.dialogs;

import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.fieldassist.ContentAssistCommandAdapter;

import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;
import de.topicmapslab.tmcledit.model.psiprovider.PSIContentProposalProvider;

/**
 * @author Hannes Niederhausen
 *
 */
public class NewTopicTypeWizard extends Wizard {

	private NewTypeWizardPage page1;
	
	private TopicType newTopicType;
	private KindOfTopicType kind = KindOfTopicType.TOPIC_TYPE;
	
	public NewTopicTypeWizard(KindOfTopicType kind) {
		this.kind = kind;
		setWindowTitle("New "+kind.getLiteral()+"...");
	}
	
	@Override
	public void addPages() {
		page1 = new NewTypeWizardPage();
		addPage(page1);
	}
	
	@Override
	public boolean performFinish() {
		newTopicType = page1.createNewType();
		return true;
	}
	
	public TopicType getNewTopicType() {
		return newTopicType;
	}

	private class NewTypeWizardPage extends WizardPage {

		private Text nameText;
		private Text identifierText;
		private Text locatorText;

		private boolean syncIdentifier;
	
		private PSIContentProposalProvider proposalProvider;
		private ControlDecoration nameDecoration;
		
		protected NewTypeWizardPage() {
			super("new type");
			setTitle("Create "+kind.getLiteral()+" ...");
			String baseLocator = ModelIndexer.getInstance().getTopicMapSchema().getBaseLocator();
			syncIdentifier = ((baseLocator!=null) && (baseLocator.length()>0));
		}

		public void createControl(Composite parent) {
			Composite comp = new Composite(parent, SWT.NONE);
			comp.setLayout(new GridLayout());
			
			createNameControl(comp);
			
			
			setControl(comp);
			setErrorMessage("Topic Type has no name");
			setPageComplete(false);
		}

				
		@Override
		public void setPageComplete(boolean complete) {
		    super.setPageComplete(complete);
		    if (complete)
		    	setErrorMessage(null);
		}
		
		private void createNameControl(Composite parent) {
			Composite comp = new Composite(parent, SWT.NONE);
			comp.setLayoutData(new GridData(GridData.FILL_BOTH));
			comp.setLayout(new GridLayout(2, false));
			
			GridDataFactory fac = GridDataFactory.createFrom(new GridData(
					GridData.FILL_HORIZONTAL));
			
			Label label = new Label(comp, SWT.NONE);
			label.setText("Name:");
			
			
			nameText = new Text(comp, SWT.BORDER);
			nameDecoration = new ControlDecoration(nameText, SWT.TOP|SWT.LEFT);
			FieldDecorationRegistry fieldDecoReg = FieldDecorationRegistry.getDefault();
			nameDecoration.setImage(fieldDecoReg.getFieldDecoration(FieldDecorationRegistry.DEC_ERROR).getImage());
			fac.applyTo(nameText);
			nameText.addModifyListener(new ModifyListener() {

				public void modifyText(ModifyEvent e) {
					String newName = nameText.getText();
					if (ModelIndexer.getTopicIndexer().getTopicTypeByName(newName)!=null) {
						String errMsg = "Type with this name already exists";
						nameDecoration.setDescriptionText(errMsg);
						nameDecoration.show();
						setErrorMessage(errMsg);
						setPageComplete(false);
						return;
					}
					
					proposalProvider.setName(newName);
					if (newName.length()>0) {
						if (syncIdentifier) {
							
							String bl = ModelIndexer.getInstance().getTopicMapSchema().getBaseLocator();
							StringBuilder builder = new StringBuilder(bl);
							if ( (!bl.endsWith(":")) && (!bl.endsWith("/")) ) 
								builder.append("/");
							builder.append(newName.toLowerCase().replaceAll(" ", "_"));
							
							
							identifierText.setText(builder.toString());
							syncIdentifier=true;
						}
						nameDecoration.hide();
						setPageComplete(true);
					} else {
						if (syncIdentifier) {
							identifierText.setText("");
							syncIdentifier=true;
						}
						String errMsg = "Topic Type has no name";
						setErrorMessage(errMsg);
						nameDecoration.setDescriptionText(errMsg);
						nameDecoration.show();
						setPageComplete(false);
					}
					
				}
				
			});
			
			label = new Label(comp, SWT.NONE);
			label.setText("Subject Identifier:");
			
			identifierText = new Text(comp, SWT.BORDER);
			identifierText.addModifyListener(new ModifyListener() {
				
				public void modifyText(ModifyEvent e) {
					syncIdentifier = false;
				}
			});

			ControlDecoration idDeco = new ControlDecoration(identifierText, SWT.TOP|SWT.LEFT);
			idDeco.setImage(fieldDecoReg.getFieldDecoration(FieldDecorationRegistry.DEC_CONTENT_PROPOSAL).getImage());
			idDeco.setShowOnlyOnFocus(true);
			
			proposalProvider = new PSIContentProposalProvider();
			new ContentAssistCommandAdapter(identifierText, 
					new TextContentAdapter(), 
					proposalProvider, 
					null,
					PSIContentProposalProvider.KEYS); 
			fac.applyTo(identifierText);
			
			label = new Label(comp, SWT.NONE);
			label.setText("Subject Locator:");
			
			locatorText = new Text(comp, SWT.BORDER);
			fac.applyTo(locatorText);
		}

		@Override
		public void setVisible(boolean visible) {
			super.setVisible(visible);
			if (visible)
				nameText.setFocus();
		}
		
		private TopicType createNewType() {
			TopicType type = ModelIndexer.getTopicIndexer().createTopicType(kind);
			type.setName(nameText.getText());
			
			if (identifierText.getText().length()>0)
				type.getIdentifiers().add(identifierText.getText());

			if (locatorText.getText().length()>0)
				type.getLocators().add(locatorText.getText());
	
			return type;
		}
	}
}
