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
/**
 * 
 */
package de.topicmapslab.tmcledit.model.commands;

import org.eclipse.emf.common.command.AbstractCommand;

import de.topicmapslab.tmcledit.model.AbstractCardinalityContraint;

/**
 * @author Hannes Niederhausen
 *
 */
public class SetCardinalityCommand extends AbstractCommand {

	private AbstractCardinalityContraint cardinalityContraint;
	private boolean isMin;
	private String newValue;
	private String oldValue;
	private String oldMax;
	
	public SetCardinalityCommand(AbstractCardinalityContraint cardinalityContraint,
			boolean isMin, String newValue) {
		super();
		this.cardinalityContraint = cardinalityContraint;
		this.isMin = isMin;
		this.newValue = newValue;
	}

	public void execute() {
		if (isMin) {
			cardinalityContraint.setCardMin(newValue);
			if (cardinalityContraint.getCardMax().equals("*"))
				return;
			try {
				int min = Integer.parseInt(cardinalityContraint.getCardMin());
				int max = Integer.parseInt(cardinalityContraint.getCardMax());
			
				if (min>max) {
					oldMax = cardinalityContraint.getCardMax();
					cardinalityContraint.setCardMax(newValue);
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		} else
			cardinalityContraint.setCardMax(newValue);
	}

	@Override
	public void undo() {
		if (isMin) {
			cardinalityContraint.setCardMin(oldValue);
			if (oldMax!=null)
				cardinalityContraint.setCardMax(oldMax);
		} else {
			cardinalityContraint.setCardMax(oldValue);
		}
	}
	
	public void redo() {
		execute();
	}
	
	@Override
	protected boolean prepare() {
		if (isMin)
			oldValue = cardinalityContraint.getCardMin();
		else
			oldValue = cardinalityContraint.getCardMax();
		
		if (oldValue.equals(newValue))
			return false;
		
		return true;
	}

}
