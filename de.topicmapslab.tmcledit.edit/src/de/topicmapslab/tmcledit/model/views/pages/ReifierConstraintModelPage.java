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

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
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

import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.ReifierConstraint;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.commands.CreateTopicTypeCommand;
import de.topicmapslab.tmcledit.model.commands.GenericSetCommand;
import de.topicmapslab.tmcledit.model.commands.SetCardinalitiesCommand;
import de.topicmapslab.tmcledit.model.dialogs.FilterTopicSelectionDialog;
import de.topicmapslab.tmcledit.model.dialogs.NewTopicTypeWizard;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;

/**
 * Property page for reifier and scope constraints
 * 
 * @author Hannes Niederhausen
 * 
 */
public class ReifierConstraintModelPage extends AbstractEMFModelPage {

	private Text typeText;
	private CCombo cardCombo;
	private CTabItem item;
	private Button browseButton;
	private Hyperlink link;
	
	public ReifierConstraintModelPage() {
		super("reifier constraint");
	}

	@Override
	protected void createItems(CTabFolder folder) {
		super.createItems(folder);

		FormToolkit toolkit = new FormToolkit(folder.getDisplay());
		Composite comp = toolkit.createComposite(folder);

		GridLayout layout = new GridLayout(2, false);
		comp.setLayout(layout);

		createTypeWidget(toolkit, comp);
		
		toolkit.createLabel(comp, "cardinality:");
		cardCombo = new CCombo(comp, SWT.BORDER);
		cardCombo.setItems(new String[] { "may", "cannot", "must" });
		cardCombo.select(0);
		cardCombo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				switch (cardCombo.getSelectionIndex()) {
				case 0:
					setMayReifier();
					break;
				case 1:
					setCannotReifier();
					break;
				case 2:
					setMustHaveReifier();
					break;
				}

			}
		});

		item = new CTabItem(folder, SWT.NONE);
		item.setText("Reifier Constraint");
		item.setControl(comp);
	}

	private void setMustHaveReifier() {
		enableTypeSelection(true);
		setReifierCardinality(1, 1);
	}

	private void setCannotReifier() {
		enableTypeSelection(false);
		setReifierCardinality(0, 0);
	}

	private void setMayReifier() {
		enableTypeSelection(true);
		setReifierCardinality(0, 1);
	}
	
	private void setReifierCardinality(int min, int max) {
		ReifierConstraint rc = getCastedModel();

		getCommandStack().execute(new SetCardinalitiesCommand(rc, Integer.toString(min), Integer.toString(max)));
	}

	
	private void createTypeWidget(FormToolkit toolkit, Composite parent) {
	    link = toolkit.createHyperlink(parent, "Topic Type:", SWT.NONE);
		link.addHyperlinkListener(new HyperlinkAdapter() {
			@Override
			public void linkActivated(HyperlinkEvent e) {
				NewTopicTypeWizard wizard = new NewTopicTypeWizard(KindOfTopicType.TOPIC_TYPE);
				WizardDialog dlg = new WizardDialog(typeText.getShell(), wizard);

				if (dlg.open() == Dialog.OK) {
					TopicType tt = wizard.getNewTopicType();
					CompoundCommand cmd = new CompoundCommand();
					CreateTopicTypeCommand c1 = new CreateTopicTypeCommand(ModelIndexer.getInstance()
					        .getTopicMapSchema(), tt);
					cmd.append(c1);
					int featureID = ModelPackage.REIFIER_CONSTRAINT__TYPE;
						
					GenericSetCommand c2 = new GenericSetCommand(getModel(), featureID, tt);
					cmd.append(c2);
					getCommandStack().execute(cmd);
				}

			}
		});

		
		Composite comp = toolkit.createComposite(parent);
		GridLayout layout = new GridLayout(2, false);
		layout.marginWidth = 0;
		comp.setLayout(layout);
		comp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		typeText = toolkit.createText(comp, "", SWT.BORDER|SWT.READ_ONLY);
		typeText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		browseButton = toolkit.createButton(comp, "...", SWT.PUSH);
		hookAddTypeButtonListeners(browseButton);
    }

	private void hookAddTypeButtonListeners(Button button) {
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FilterTopicSelectionDialog dlg = new FilterTopicSelectionDialog(
						typeText.getShell(), KindOfTopicType.TOPIC_TYPE);

				
				if (dlg.open() == Dialog.OK) {
					TopicType tt = ((TopicType) dlg.getFirstResult());
					int featureID = ModelPackage.REIFIER_CONSTRAINT__TYPE;
						
					GenericSetCommand cmd = new GenericSetCommand(getModel(), featureID, tt);
					getCommandStack().execute(cmd);
				}

			}
		});
	    
    }
	
	private ReifierConstraint getCastedModel() {
		return (ReifierConstraint) getModel();
	}

	@Override
	public void updateUI() {
	    super.updateUI();
	    if (getCastedModel().getType()==null)
	    	typeText.setText("tmdm:subject");
	    else
	    	typeText.setText(getCastedModel().getType().getName());
	    
	    boolean enabled = true;
	    if ("0".equals(getCastedModel().getCardMin())) {
	    	if ("0".equals(getCastedModel().getCardMax())) {
	    		cardCombo.select(1);
	    		enabled = false;
	    	} else {
	    		cardCombo.select(0);
	    	}
	    } else {
	    	cardCombo.select(2);
	    }
	    
	    enableTypeSelection(enabled);
	    
    	item.setText("Reifier Constraint");
	}

	@Override
	public void setModel(Object model) {
		if (getModel()!=null) {
			if (getCastedModel().getType()!=null)
				getCastedModel().getType().eAdapters().remove(this);
		}
	    super.setModel(model);
	    if (getModel()!=null) {
			if (getCastedModel().getType()!=null)
				getCastedModel().getType().eAdapters().add(this);
		}
	}
	
	private void enableTypeSelection(boolean enabled) {
	    browseButton.setEnabled(enabled);
		link.setEnabled(enabled);
    }

	public void notifyChanged(Notification notification) {
		if (notification.getFeatureID(TopicType.class) == ModelPackage.REIFIER_CONSTRAINT__TYPE) {
			if (notification.getEventType()==Notification.SET) {
				EObject tmp = (EObject) notification.getOldValue();
				if (tmp!=null)
					tmp.eAdapters().remove(this);
				
				tmp = (EObject) notification.getNewValue();
				if (tmp!=null)
					tmp.eAdapters().add(this);
			}
		}	
		updateUI();
    }
}
