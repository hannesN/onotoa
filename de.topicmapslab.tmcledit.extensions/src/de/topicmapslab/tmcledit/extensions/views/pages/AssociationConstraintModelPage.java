package de.topicmapslab.tmcledit.extensions.views.pages;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
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

import de.topicmapslab.tmcledit.extensions.dialogs.FilterTopicSelectionDialog;
import de.topicmapslab.tmcledit.extensions.dialogs.NewTopicTypeWizard;
import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.util.ModelIndexer;

/**
 * 
 * @author Hannes Niederhausen
 *
 */
public class AssociationConstraintModelPage extends AbstractModelPage {

	private Text typeText;

	public AssociationConstraintModelPage() {
		super("association_constraint");
	}
	
	@Override
	public void updateUI() {
		AssociationTypeConstraint asc = getCastedModel();
		if (asc.getAssociationType()==null)
			typeText.setText("");
		else
			typeText.setText(asc.getAssociationType().getName());
	}

	private AssociationTypeConstraint getCastedModel() {
		return (AssociationTypeConstraint) getModel();
	}

	@Override
	public void createControl(Composite parent) {
		FormToolkit toolkit = new FormToolkit(parent.getDisplay());
		
		Composite comp = toolkit.createComposite(parent);
		comp.setLayout(new GridLayout(3, false));
		
		Hyperlink link = toolkit.createHyperlink(comp, "Assoc. Type:", SWT.NONE);
		link.addHyperlinkListener(new HyperlinkAdapter() {
			@Override
			public void linkActivated(HyperlinkEvent e) {
				NewTopicTypeWizard wizard = new NewTopicTypeWizard();
				wizard.setDefaultType(KindOfTopicType.ASSOCIATION_TYPE);
				WizardDialog dlg = new WizardDialog(typeText.getShell(), wizard);
				
				if (dlg.open()==Dialog.OK) {
					TopicType tt = wizard.getNewTopicType();
					ModelIndexer.getInstance().getTopicMapSchema().getTopicTypes().add(tt);
					getCastedModel().setAssociationType(tt);
				}
				
			}
		});
		
		typeText = toolkit.createText(comp, "", SWT.BORDER);
		typeText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	
		
		Button button = toolkit.createButton(comp, "...", SWT.PUSH);
		hookAddTypeButtonListeners(button);
		
		setControl(comp);
	}

	private void hookAddTypeButtonListeners(Button button) {
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				KindOfTopicType type = (ModelIndexer.getInstance()
						.getTopicMapSchema()
						.isActiveAssociationTypeConstraint()) ? KindOfTopicType.ASSOCIATION_TYPE
						: null; 
				FilterTopicSelectionDialog dlg = new FilterTopicSelectionDialog(typeText.getShell(), type);
				
				if (dlg.open()==Dialog.OK) {
					getCastedModel().setAssociationType((TopicType) dlg.getFirstResult());
				}
				
			}
		});
	}

	@Override
	public void notifyChanged(Notification notification) {
		if (notification.getEventType()==Notification.REMOVING_ADAPTER) {
			return;
		}
		
		if (notification.getEventType()==Notification.SET) {
			if (notification.getFeatureID(TopicType.class)==ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__ASSOCIATION_TYPE) {
				if (notification.getOldValue()!=null)
					((EObject)notification.getOldValue()).eAdapters().remove(this);
				
				if (notification.getNewValue()!=null)
					((EObject)notification.getNewValue()).eAdapters().add(this);
			}
				
			
		}
		
		updateUI();
		
	}

}
