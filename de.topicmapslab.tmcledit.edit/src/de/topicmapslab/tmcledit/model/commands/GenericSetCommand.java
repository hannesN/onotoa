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
package de.topicmapslab.tmcledit.model.commands;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * This command is used to set a value using the EMF feature IDs
 * 
 * @author Hannes Niederhausen
 *
 */
public class GenericSetCommand extends AbstractCommand {

	private final int featureID;
	private EObject model;
	private Object newValue;
	private Object oldValue;
	
	private EStructuralFeature feature;
	
	
	/**
	 * Constructor
	 * 
	 * @param model the parent model
	 * @param featureID the feature id of the attribute
	 * @param newValue the new value
	 */
	public GenericSetCommand(EObject model, int featureID, Object newValue) {
		super();
		this.model = model;
		this.featureID = featureID;
		this.newValue = newValue;
	}

	@Override
	protected boolean prepare() {
		oldValue = model.eGet(getFeature()); 
		if (oldValue==null) {
			if (newValue==null) 
				return false;
		} else {
			if (oldValue.equals(newValue))
				return false;
		}
		return true;
	}
	
	private EStructuralFeature getFeature() {
		if (feature==null) {
			feature = model.eClass().getEStructuralFeature(featureID);
		}
		return feature;
	}
	
	public void execute() {
		model.eSet(getFeature(), newValue);
	}

	public void redo() {
		execute();
	}

	@Override
	public void undo() {
		model.eSet(getFeature(), oldValue);
	}

	@Override
	public String getLabel() {
		return "Set "+getFeature().getName();
	}
	
}
