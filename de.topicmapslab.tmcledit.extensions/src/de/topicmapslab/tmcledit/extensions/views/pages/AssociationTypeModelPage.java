/**
 * 
 */
package de.topicmapslab.tmcledit.extensions.views.pages;

import java.util.ArrayList;
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

import de.topicmapslab.tmcledit.extensions.TypedCardinalityConstraintWidget;
import de.topicmapslab.tmcledit.model.AssociationType;
import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.RoleConstraints;
import de.topicmapslab.tmcledit.model.ScopeConstraint;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.commands.AddRoleConstraintsCommand;
import de.topicmapslab.tmcledit.model.commands.RemoveRoleConstraintsCommand;
import de.topicmapslab.tmcledit.model.dialogs.NewTopicTypeWizard;
import de.topicmapslab.tmcledit.model.util.ModelIndexer;

/**
 * @author Hannes Niederhausen
 *
 */
public class AssociationTypeModelPage extends ScopedTopicTypePage {

	private TypedCardinalityConstraintWidget control;
	
	public AssociationTypeModelPage() {
		super("asssociation type");
	}
	
	@Override
	protected void createAdditionalControls(Composite parent,
			FormToolkit toolkit) {

		control = new TypedCardinalityConstraintWidget(parent, toolkit, getCommandStack());
		control.setText("roles:");
		
		hookButtonListeners();
		
		super.createAdditionalControls(parent, toolkit);
	}

	@Override
	public void setCommandStack(CommandStack commandStack) {
		super.setCommandStack(commandStack);
		if (control != null)
			control.setCommandStack(commandStack);
	}
	
	private AssociationType getCastedModel() {
		return (AssociationType) getModel();
	}

	private void hookButtonListeners() {
		control.getAddButton().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				ModelIndexer instance = ModelIndexer.getInstance();
				TopicMapSchema topicMapSchema = instance.getTopicMapSchema();
				List<TopicType> list = new ArrayList<TopicType>();
				if (topicMapSchema.isActiveRoleTypeConstraint()) {
					list.addAll(instance.getRoleTypes());
				} else {
					list.addAll(topicMapSchema.getTopicTypes());
					list.remove(getCastedModel().eContainer());
				}
										
				ListSelectionDialog dlg = new ListSelectionDialog(
						control.getShell(),
						list,
						new ArrayContentProvider(),
						control.new TopicLabelProvider(),
						"Choose the Role type");
				
				if (dlg.open()==Dialog.OK) {
					if (dlg.getResult().length==0)
						return;
					
					List<RoleConstraints> rcl = new ArrayList<RoleConstraints>();
					for (Object tt : dlg.getResult()) {
						RoleConstraints rc = ModelFactory.eINSTANCE.createRoleConstraints();
						rc.setType((TopicType) tt);
						rc.setCardMin("1");
						rc.setCardMax("1");
						rcl.add(rc);
					}
					AddRoleConstraintsCommand cmd = new AddRoleConstraintsCommand(getCastedModel(), rcl);
					getCommandStack().execute(cmd);
				}
			}
		});
		
		control.getNewButton().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				NewTopicTypeWizard wizard = new NewTopicTypeWizard();
				wizard.setDefaultType(KindOfTopicType.ROLE_TYPE);
				WizardDialog dlg = new WizardDialog(control.getShell(), wizard);
				
				if (dlg.open()==Dialog.OK) {
					TopicType tt = wizard.getNewTopicType();
					ModelIndexer.getInstance().getTopicMapSchema().getTopicTypes().add(tt);
					RoleConstraints rc = ModelFactory.eINSTANCE.createRoleConstraints();
					rc.setType(tt);
					rc.setCardMin("1");
					rc.setCardMax("1");
					AddRoleConstraintsCommand cmd = new AddRoleConstraintsCommand(getCastedModel(), rc);
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
				
				List<RoleConstraints> removeList = new ArrayList<RoleConstraints>();
				Iterator<RoleConstraints> it = sel.iterator();
				while (it.hasNext()) {
					removeList.add(it.next());
				}
				
				RemoveRoleConstraintsCommand cmd = new RemoveRoleConstraintsCommand(
						getCastedModel(), removeList);
				getCommandStack().execute(cmd);
			}
		});
	}
	
	@Override
	public void notifyChanged(Notification notification) {
		if (notification.getNotifier() instanceof ScopeConstraint) {
			if (notification.getFeatureID(TopicType.class)==ModelPackage.ROLE_CONSTRAINTS__TYPE) {
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
			for (RoleConstraints rc : getCastedModel().getRoles()) {
				if (rc.getType()!=null)
					rc.getType().eAdapters().remove(this);
				rc.eAdapters().remove(this);
			}
		}
		
		super.setModel(model);
		for (RoleConstraints rc : getCastedModel().getRoles()) {
			if (rc.getType()!=null)
				rc.getType().eAdapters().add(this);
			rc.eAdapters().add(this);
		}
	}
	
	@Override
	public void updateUI() {
		control.setInput(getCastedModel().getRoles());
		super.updateUI();
	}
}
