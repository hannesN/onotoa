/**
 * 
 */
package de.topicmapslab.tmcledit.extensions.views.pages;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
import de.topicmapslab.tmcledit.model.RoleConstraints;
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
	public void updateUI() {
		control.setInput(getCastedModel().getRoles());
		super.updateUI();
	}
}
