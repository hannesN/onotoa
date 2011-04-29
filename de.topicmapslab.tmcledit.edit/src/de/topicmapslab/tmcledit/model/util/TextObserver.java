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
package de.topicmapslab.tmcledit.model.util;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.widgets.Text;

import de.topicmapslab.tmcledit.model.commands.GenericSetCommand;

/**
 * Utility class to observ text fields which let the user modify the model
 * 
 * @author Hannes Niederhausen
 *
 */
public class TextObserver implements FocusListener, DisposeListener, KeyListener {

	private final IModelProvider modelProvider;
	private final int featureID;
	private final Text text;
	
	protected TextObserver(Text text, IModelProvider modelProvider, int featureID) {
		super();
		this.text = text;
		this.featureID = featureID;
		this.modelProvider = modelProvider;
		this.text.addDisposeListener(this);
		this.text.addFocusListener(this);
		this.text.addKeyListener(this);
		
	}

	public void focusGained(FocusEvent e) {
	}

	public void focusLost(FocusEvent e) {
		/*
		EStructuralFeature feature = modelProvider.getModel().eClass().getEStructuralFeature(featureID);
		modelProvider.getModel().eSet(feature, text.getText());
		*/
		finish();
	}

	private void finish() {
	    modelProvider.getCommandStack().execute(new GenericSetCommand(modelProvider.getModel(), featureID, text.getText()));
    }

	public void widgetDisposed(DisposeEvent e) {
		text.removeFocusListener(this);
	}

	/**
	 * Creates a text observer for the given text setting the values into the modelproviders model
	 * using the given featureID
	 * @param text the text which edits the models attribute
	 * @param modelProvider the provider of the model to modify
	 * @param featureID the featureID of the attribute in the model
	 */
	public static void observe(Text text, IModelProvider modelProvider,
			int featureID) {
		new TextObserver(text, modelProvider, featureID);
	}

	public void keyPressed(KeyEvent e) {
		if (e.character==SWT.CR)
			finish();
    }

	public void keyReleased(KeyEvent e) {
    }
}
