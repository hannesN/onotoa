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
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.TypedEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.commands.GenericSetCommand;

public class TopicMapSchemaPropertyPage extends AbstractEMFModelPage {

	private Text nameText;
	private Text baseLocatorText;
	private Text versionText;
	private Text schemaResourceText;
	private CTabItem item;
	
	private PrefixMappingPage prefixPage;

	public TopicMapSchemaPropertyPage() {
		super("topicmapschema");
	}

	@Override
	public void updateUI() {
		super.updateUI();
		if (nameText == null)
			return;
		if (getCastedModel() != null) {
			String tmp = getCastedModel().getBaseLocator();
			if (tmp != null)
				baseLocatorText.setText(tmp);
			else
				baseLocatorText.setText("");
			
			tmp = getCastedModel().getName();
			if (tmp != null)
				nameText.setText(tmp);
			else
				nameText.setText("");
			
			tmp = getCastedModel().getVersion();
			if (tmp != null)
				versionText.setText(tmp);
			else
				versionText.setText("");
			
			tmp = getCastedModel().getSchemaResource();
			if (tmp != null)
				schemaResourceText.setText(tmp);
			else
				schemaResourceText.setText("");
			
		} else {
			nameText.setText("");
			baseLocatorText.setText("");
			versionText.setText("");
			schemaResourceText.setText("");
		}
	}

	public TopicMapSchema getCastedModel() {
		return (TopicMapSchema) getModel();
	}
	
	@Override
	public void setCommandStack(CommandStack commandStack) {
	    super.setCommandStack(commandStack);
	    if (prefixPage!=null)
	    	prefixPage.setCommandStack(commandStack);
	}

	protected Composite createPage(CTabFolder folder) {
		FormToolkit toolkit = new FormToolkit(folder.getDisplay());

		GridDataFactory fac = GridDataFactory.createFrom(new GridData(GridData.FILL_HORIZONTAL));
		
		Composite comp = toolkit.createComposite(folder);
		comp.setLayoutData(new GridData(GridData.FILL_BOTH));
		comp.setLayout(new GridLayout(2, false));

		toolkit.createLabel(comp, "Name:");
		nameText = toolkit.createText(comp, "", SWT.BORDER);
		fac.applyTo(nameText);
		nameText.setToolTipText("The nameText of the Topic Map.");
		
		toolkit.createLabel(comp, "Version:");
		versionText = toolkit.createText(comp, "", SWT.BORDER);
		fac.applyTo(versionText);
		versionText.setToolTipText("The version of the Topic Map schema.");

		toolkit.createLabel(comp, "Base Locator:");
		baseLocatorText = toolkit.createText(comp, "", SWT.BORDER);
		fac.applyTo(baseLocatorText);
		baseLocatorText
				.setToolTipText("The base locator of the Topic Map. It is used to create subject identifiers"
						+ " or subject locators using this url and the nameText of the topic.");

		toolkit.createLabel(comp, "Schema Resource:");
		schemaResourceText = toolkit.createText(comp, "", SWT.BORDER);
		fac.applyTo(schemaResourceText);
		schemaResourceText.setToolTipText("The schema resource of the Topic Map schema, which is a URL where to download the schema.");
		
		updateUI();

		// instantiate listeners, they reigster themselves
		new TextFocusListener(nameText, ModelPackage.TOPIC_MAP_SCHEMA__NAME);
		new TextFocusListener(baseLocatorText, ModelPackage.TOPIC_MAP_SCHEMA__BASE_LOCATOR);
		new TextFocusListener(versionText, ModelPackage.TOPIC_MAP_SCHEMA__VERSION);
		new TextFocusListener(schemaResourceText, ModelPackage.TOPIC_MAP_SCHEMA__SCHEMA_RESOURCE);
		
		return comp;
	}
	
	@Override
	protected void setEnabled(boolean enabled) {
		item.getControl().setEnabled(enabled);
	}
	
	@Override
	protected void createItems(CTabFolder folder) {
		super.createItems(folder);
		item = new CTabItem(folder, SWT.None);
		item.setText("Topic Map Schema");
		item.setControl(createPage(folder));
		
		prefixPage = new PrefixMappingPage();
		prefixPage.createItems(folder);
	}

	public void notifyChanged(Notification notification) {
		updateUI();
	}
	
	@Override
	public void setModel(Object model) {
		super.setModel(model);
		if (prefixPage!=null)
			prefixPage.setModel(getCastedModel().getMappings());
	}

	private final class TextFocusListener extends FocusAdapter implements KeyListener {
		private final int feature;

		
		public TextFocusListener(Text text, int feature) {
			super();
			text.addKeyListener(this);
			text.addFocusListener(this);
			this.feature = feature;
		}

		public void focusLost(FocusEvent e) {
			commit(e);
		}

		public void keyPressed(KeyEvent e) {
			if (e.character == SWT.CR) {
				commit(e);
			}
		}

		/**
         * @param e
         */
        private void commit(TypedEvent e) {
	        Text text = (Text) e.widget;
	        getCommandStack().execute(new GenericSetCommand(getModel(), feature, text.getText()));
        }

		public void keyReleased(KeyEvent e) {
        }
	}

}
