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
package de.topicmapslab.onotoa.aranuka.codegen.modelview;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.topicmapslab.kuria.annotation.AnnotationBindingFactory;
import de.topicmapslab.kuria.swtgenerator.WidgetGenerator;
import de.topicmapslab.kuria.swtgenerator.edit.InputMask;
import de.topicmapslab.onotoa.aranuka.codegen.model.GeneratorData;
import de.topicmapslab.tmcledit.model.views.extension.AbstractModelPage;

/**
 * @author Hannes Niederhausen
 *
 */
public class CodeGeneratorModelPage extends AbstractModelPage {

	
	private Composite control;
	private InputMask inputMask;

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void createControl(Composite parent) {
		FormToolkit toolkit = new FormToolkit(parent.getDisplay());
		control = toolkit.createComposite(parent);
		control.setLayout(new GridLayout());
		AnnotationBindingFactory fac = new AnnotationBindingFactory();
		fac.addClass(GeneratorData.class);
	
		WidgetGenerator widgetGenerator = new WidgetGenerator(fac.getBindingContainer());
		inputMask = widgetGenerator.generateEditable(GeneratorData.class, control);
		inputMask.getComposite().setLayoutData(new GridData(GridData.FILL_BOTH));
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Control getControl() {
	    return control;
	}
	
	/**
     * {@inheritDoc}
     */
    @Override
    public void aboutToHide() {
    	setModel(null);
    }

	/**
     * {@inheritDoc}
     */
    @Override
    public void updateUI() {
	    inputMask.setModel(getModel());
	    inputMask.setEnabled(getModel()!=null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getId() {
        return "de.topcimapslab.codegenerator.annotaionpage";
    }
}
