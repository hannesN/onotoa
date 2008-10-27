package de.topicmapslab.tmcledit.extensions.views.pages;

import java.util.Iterator;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.topicmapslab.tmcledit.extensions.util.TextObserver;
import de.topicmapslab.tmcledit.model.AbstractConstraint;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.TopicType;

/**
 * 
 * @author Hannes Niederhausen
 *
 */
public abstract class AbstractConstraintModelPage extends AbstractModelPage {

	protected Text cardMinText;
	protected Text cardMaxText;
	protected Text regExpText;
	protected Text scopeText;
	
	@Override
	public void updateUI() {
		cardMinText.setText(getCastedModel().getCardMin());
		cardMaxText.setText(getCastedModel().getCardMax());
		regExpText.setText(getCastedModel().getRegexp());
		redrawScopeList();		
	}

	private void redrawScopeList() {
		StringBuffer buffer = new StringBuffer();
		
		for (Iterator<TopicType> it = getCastedModel().getScope().iterator(); it.hasNext();) {
			TopicType tt = it.next();
			buffer.append(tt.getId());
			if (it.hasNext())
				buffer.append(", ");
		}
		
		scopeText.setText(buffer.toString());
	}

	/**
	 * Creates the widgets for cardinality, scope and regular expression.
	 * 
	 * Attention: The parent control needs to have a 2 column grid layout!!
	 * 
	 * @param parent the parent control
	 */
	protected void createCommonConstraintControls(Composite parent, FormToolkit toolkit) {
		toolkit.createLabel(parent, "cardMin");
		cardMinText = toolkit.createText(parent, "", SWT.BORDER);
		TextObserver.observe(cardMinText, this, ModelPackage.ABSTRACT_CONSTRAINT__CARD_MIN);
		
		toolkit.createLabel(parent, "cardMax");
		cardMaxText = toolkit.createText(parent, "", SWT.BORDER);
		TextObserver.observe(cardMinText, this, ModelPackage.ABSTRACT_CONSTRAINT__CARD_MAX);
		
		toolkit.createLabel(parent, "regExp");
		regExpText = toolkit.createText(parent, "", SWT.BORDER);
		TextObserver.observe(regExpText, this, ModelPackage.ABSTRACT_CONSTRAINT__REGEXP);
		
		// TODO Scope stuff
		
	}

	@Override
	public void notifyChanged(Notification notification) {
		if (notification.getNotifier().equals(getModel())) {
			updateUI();
		}
	}
	
	private AbstractConstraint getCastedModel() {
		return (AbstractConstraint) getModel();
	}

}
