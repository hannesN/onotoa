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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.topicmapslab.tmcledit.model.AbstractCardinalityContraint;
import de.topicmapslab.tmcledit.model.util.CardTextObserver;

/**
 * 
 * @author Hannes Niederhausen
 *
 */
public abstract class AbstractCardinalityConstraintModelPage extends AbstractModelPage {

	protected Text cardMinText;
	protected Text cardMaxText;

	
	public AbstractCardinalityConstraintModelPage(String id) {
		super(id);
	}
	
	@Override
	public void updateUI() {
		super.updateUI();
		cardMinText.setText(getCastedModel().getCardMin());
		cardMaxText.setText(getCastedModel().getCardMax());
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
		CardTextObserver.observe(cardMinText, this, true);
		
		toolkit.createLabel(parent, "cardMax");
		cardMaxText = toolkit.createText(parent, "", SWT.BORDER);
		fac.applyTo(cardMaxText);
		CardTextObserver.observe(cardMaxText, this, false);
	}

	

	public void notifyChanged(Notification notification) {
		if (notification.getEventType()==Notification.REMOVING_ADAPTER)
			return;
		
		if (notification.getNotifier().equals(getModel())) {
			updateUI();
		}
	}
	
	private AbstractCardinalityContraint getCastedModel() {
		return (AbstractCardinalityContraint) getModel();
	}

}
