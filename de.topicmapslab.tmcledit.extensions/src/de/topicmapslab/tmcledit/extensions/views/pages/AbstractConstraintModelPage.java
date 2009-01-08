package de.topicmapslab.tmcledit.extensions.views.pages;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.topicmapslab.tmcledit.extensions.util.TextObserver;
import de.topicmapslab.tmcledit.model.AbstractConstraint;
import de.topicmapslab.tmcledit.model.ModelPackage;

/**
 * 
 * @author Hannes Niederhausen
 *
 */
public abstract class AbstractConstraintModelPage extends AbstractModelPage {

	protected Text cardMinText;
	protected Text cardMaxText;
	protected Text regExpText;

	
	public AbstractConstraintModelPage(String id) {
		super(id);
	}
	
	@Override
	public void updateUI() {
		cardMinText.setText(getCastedModel().getCardMin());
		cardMaxText.setText(getCastedModel().getCardMax());
		regExpText.setText(getCastedModel().getRegexp());
	}


	/**
	 * Creates the widgets for cardinality, scope and regular expression.
	 * 
	 * Attention: The parent control needs to have a 2 column grid layout!!
	 * 
	 * @param parent the parent control
	 */
	protected void createCommonConstraintControls(Composite parent, FormToolkit toolkit) {
		GridDataFactory fac = GridDataFactory.createFrom(new GridData(GridData.FILL_HORIZONTAL));
		
		toolkit.createLabel(parent, "cardMin");
		cardMinText = toolkit.createText(parent, "", SWT.BORDER);
		fac.applyTo(cardMinText);
		TextObserver.observe(cardMinText, this, ModelPackage.ABSTRACT_CONSTRAINT__CARD_MIN);
		
		toolkit.createLabel(parent, "cardMax");
		cardMaxText = toolkit.createText(parent, "", SWT.BORDER);
		fac.applyTo(cardMaxText);
		TextObserver.observe(cardMaxText, this, ModelPackage.ABSTRACT_CONSTRAINT__CARD_MAX);
		
		toolkit.createLabel(parent, "reg. exp");
		regExpText = toolkit.createText(parent, "", SWT.BORDER);
		fac.applyTo(regExpText);
		TextObserver.observe(regExpText, this, ModelPackage.ABSTRACT_CONSTRAINT__REGEXP);
		
		
		
	}

	

	@Override
	public void notifyChanged(Notification notification) {
		if (notification.getEventType()==Notification.REMOVING_ADAPTER)
			return;
		
		if (notification.getNotifier().equals(getModel())) {
			updateUI();
		}
	}
	
	private AbstractConstraint getCastedModel() {
		return (AbstractConstraint) getModel();
	}

}
