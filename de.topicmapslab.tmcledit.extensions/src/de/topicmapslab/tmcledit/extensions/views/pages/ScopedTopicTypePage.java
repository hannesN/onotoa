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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.dialogs.ListSelectionDialog;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.topicmapslab.tmcledit.extensions.views.widgets.TypedCardinalityConstraintWidget;
import de.topicmapslab.tmcledit.model.AbstractTypedCardinalityConstraint;
import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.ScopeConstraint;
import de.topicmapslab.tmcledit.model.ScopedTopicType;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.commands.AddScopeConstraintsCommand;
import de.topicmapslab.tmcledit.model.commands.RemoveScopeConstraintsCommand;
import de.topicmapslab.tmcledit.model.dialogs.NewTopicTypeWizard;
import de.topicmapslab.tmcledit.model.util.ModelIndexer;

/**
 * @author Hannes Niederhausen
 *
 */
public abstract class ScopedTopicTypePage extends TopicTypePage {
	
	private TypedCardinalityConstraintWidget control;
	
	public ScopedTopicTypePage(String id) {
		super(id);
	}
	
	@Override
	protected void createAdditionalControls(Composite parent,
			FormToolkit toolkit) {
	

		control = new TypedCardinalityConstraintWidget(parent, toolkit, getCommandStack());
		control.setText("scope:");
		
		
		hookButtonListeners();
	}
	
	@Override
	public void setCommandStack(CommandStack commandStack) {
		super.setCommandStack(commandStack);
		if (control != null)
			control.setCommandStack(commandStack);
	}
	
	private void hookButtonListeners() {
		control.getAddButton().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				ModelIndexer instance = ModelIndexer.getInstance();
				TopicMapSchema topicMapSchema = instance.getTopicMapSchema();
				List<TopicType> list = new ArrayList<TopicType>();
				if (topicMapSchema.isActiveScopeTypeConstraint()) {
					list.addAll(instance.getScopeTypes());
				} else {
					list.addAll(topicMapSchema.getTopicTypes());
					list.remove(getCastedModel().eContainer());
				}
										
				ListSelectionDialog dlg = new ListSelectionDialog(
						control.getShell(),
						list,
						new ArrayContentProvider(),
						control.new TopicLabelProvider(),
						"Choose the Scope type");
				
				if (dlg.open()==Dialog.OK) {
					if (dlg.getResult().length==0)
						return;
					
					List<ScopeConstraint> scl = new ArrayList<ScopeConstraint>();
					for (Object tt : dlg.getResult()) {
						ScopeConstraint sc = ModelFactory.eINSTANCE.createScopeConstraint();
						sc.setType((TopicType) tt);
						sc.setCardMin("0");
						sc.setCardMax("1");
						scl.add(sc);
					}
					AddScopeConstraintsCommand cmd = new AddScopeConstraintsCommand(getCastedModel(), scl);
					getCommandStack().execute(cmd);
					
				}
				
			}
		});
		
		control.getNewButton().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				NewTopicTypeWizard wizard = new NewTopicTypeWizard();
				wizard.setDefaultType(KindOfTopicType.SCOPE_TYPE);
				WizardDialog dlg = new WizardDialog(control.getShell(), wizard);
				
				if (dlg.open()==Dialog.OK) {
					TopicType tt = wizard.getNewTopicType();
					ModelIndexer.getInstance().getTopicMapSchema().getTopicTypes().add(tt);
					List<ScopeConstraint> scl = new ArrayList<ScopeConstraint>();
					ScopeConstraint sc = ModelFactory.eINSTANCE.createScopeConstraint();
					sc.setType(tt);
					sc.setCardMin("0");
					sc.setCardMax("1");
					scl.add(sc);
					AddScopeConstraintsCommand cmd = new AddScopeConstraintsCommand(getCastedModel(), scl);
					getCommandStack().execute(cmd);
						
				}
			}
		});
		
		control.getRemoveButton().addSelectionListener(new SelectionAdapter() {
			@SuppressWarnings("unchecked")
			@Override
			public void widgetSelected(SelectionEvent e) {
				IStructuredSelection sel = (IStructuredSelection) control.getTableViewer().getSelection();
				
				if (sel.isEmpty())
					return;
				
				List<ScopeConstraint> removeList = new ArrayList<ScopeConstraint>();
				Iterator<ScopeConstraint> it = sel.iterator();
				while (it.hasNext()) {
					removeList.add(it.next());
				}
				
				RemoveScopeConstraintsCommand cmd = new RemoveScopeConstraintsCommand(
						getCastedModel(), removeList);
				getCommandStack().execute(cmd);
			}
		});
	}
	
	@Override
	public void notifyChanged(Notification notification) {
		if (notification.getNotifier() instanceof ScopeConstraint) {
			if (notification.getFeatureID(TopicType.class)==ModelPackage.SCOPE_CONSTRAINT__TYPE) {
				if (notification.getOldValue()!=null) {
					((TopicType)notification.getOldValue()).eAdapters().remove(this);
				}
				if (notification.getNewValue()!=null) {
					((TopicType)notification.getNewValue()).eAdapters().add(this);
				}
			} 
			control.getTableViewer().refresh(notification.getNotifier());
			return;
			
		}
		
		super.notifyChanged(notification);
	}
	
	@Override
	public void setModel(Object model) {
		if (getCastedModel()!=null) {
			for (ScopeConstraint sc : getCastedModel().getScope()) {
				if (sc.getType()!=null)
					sc.getType().eAdapters().remove(this);
				sc.eAdapters().remove(this);
			}
		}
		
		super.setModel(model);
		if (model==null)
			return;
		for (ScopeConstraint sc : getCastedModel().getScope()) {
			if (sc.getType()!=null)
				sc.getType().eAdapters().add(this);
			sc.eAdapters().add(this);
		}
	}
	
	private ScopedTopicType getCastedModel() {
		return (ScopedTopicType) getModel();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void updateUI() {
		super.updateUI();
		if (getCastedModel()!=null)
			control.setInput(getCastedModel().getScope());
		else
			control.setInput((List<? extends AbstractTypedCardinalityConstraint>) Collections.emptyList());
		
		control.getTableViewer().refresh();
	}

}