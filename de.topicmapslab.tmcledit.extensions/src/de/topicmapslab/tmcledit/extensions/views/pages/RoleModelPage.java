package de.topicmapslab.tmcledit.extensions.views.pages;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ListSelectionDialog;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Hyperlink;

import de.topicmapslab.tmcledit.extensions.util.CardTextObserver;
import de.topicmapslab.tmcledit.model.AssociationType;
import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.RoleConstraint;
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.dialogs.NewTopicTypeWizard;
import de.topicmapslab.tmcledit.model.util.ImageProvider;
import de.topicmapslab.tmcledit.model.util.ModelIndexer;

public class RoleModelPage extends AbstractModelPage{

	private Text cardMinText;
	private Text cardMaxText;
	private Text roleText;
	
	private Combo roleCombo;
	private Text roleCardMinText;
	private Text roleCardMaxText;

	public RoleModelPage() {
		super("role");
	}
	
	@Override
	public void updateUI() {
		RolePlayerConstraint rpc = getCastedModel();
		
		cardMinText.setText(rpc.getCardMin());
		cardMaxText.setText(rpc.getCardMax());
		
		if (rpc.getRole()!=null)
			roleText.setText(rpc.getRole().getType().getName());
		else
			roleText.setText("no type");
		
	}

	@Override
	public void createControl(Composite parent) {
		FormToolkit toolkit = new FormToolkit(parent.getDisplay());
		
		Composite comp = toolkit.createComposite(parent);
		comp.setLayout(new GridLayout(3, false));
		
	
		
		Hyperlink link = toolkit.createHyperlink(comp, "Role:", SWT.NONE);
		link.addHyperlinkListener(new HyperlinkAdapter() {
			@Override
			public void linkActivated(HyperlinkEvent e) {
				NewTopicTypeWizard wizard = new NewTopicTypeWizard();
				wizard.setDefaultType(KindOfTopicType.ROLE_TYPE);
				WizardDialog dlg = new WizardDialog(cardMinText.getShell(), wizard);
				
				if (dlg.open()==Dialog.OK) {
					TopicType tt = wizard.getNewTopicType();
					ModelIndexer.getInstance().getTopicMapSchema().getTopicTypes().add(tt);
					// TODO Command
				}
				
			}
		});
		
		roleText = toolkit.createText(comp, "", SWT.BORDER);
		roleText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		Button selectButton = toolkit.createButton(comp, "...", SWT.PUSH);
		selectButton.addSelectionListener(new SelectionListener());
		
				
		toolkit.createLabel(comp, "cardMin:");
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		cardMinText = toolkit.createText(comp, "", SWT.BORDER);
		cardMinText.setLayoutData(gd);
		CardTextObserver.observe(cardMinText, this, true);
				
		toolkit.createLabel(comp, "cardMax:");
		cardMaxText = toolkit.createText(comp, "", SWT.BORDER);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		cardMaxText.setLayoutData(gd);
		CardTextObserver.observe(cardMaxText, this, false);
		
		setControl(comp);
	}
	
	protected RolePlayerConstraint getCastedModel() {
		return (RolePlayerConstraint) getModel();
	}

	@Override
	public void notifyChanged(Notification notification) {
		updateUI();
		
	}

	private class SelectionListener extends SelectionAdapter {
		@Override
		public void widgetSelected(SelectionEvent e) {
			List<RoleConstraint> list = Collections.emptyList();
			AssociationType type = (AssociationType) ((AssociationTypeConstraint) getCastedModel().eContainer()).getType();
			
			list = type.getRoles();
			
			ListSelectionDialog dlg = new ListSelectionDialog(
					roleText.getShell(),
					list,
					new ArrayContentProvider(),
					new RoleConstraintLabelProvider(),
					"Choose the role");
			
			if (dlg.open()==Dialog.OK) {
				if (dlg.getResult().length>0)
					getCastedModel().setRole((RoleConstraint) dlg.getResult()[0]);
				else
					getCastedModel().setRole(null);
			}
			
			
		}
	}
	
	private class RoleConstraintLabelProvider implements ILabelProvider {

		@Override
		public Image getImage(Object element) {
			return ImageProvider.getTopicTypeImage(((RoleConstraint)element).getType());
		}

		@Override
		public String getText(Object element) {
			return ((RoleConstraint)element).getType().getName();
		}

		@Override
		public void addListener(ILabelProviderListener listener) {
		}

		@Override
		public void dispose() {
		}

		@Override
		public boolean isLabelProperty(Object element, String property) {
			return false;
		}

		@Override
		public void removeListener(ILabelProviderListener listener) {
		}
		
	}
}
