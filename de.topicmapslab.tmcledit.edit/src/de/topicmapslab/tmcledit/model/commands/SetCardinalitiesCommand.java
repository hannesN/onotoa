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
public class SetCardinalitiesCommand extends AbstractCommand {

	private AbstractCardinalityContraint cardinalityContraint;
	private String max;
	private String min;
	private String oldMin;
	private String oldMax;

	public SetCardinalitiesCommand(AbstractCardinalityContraint cardinalityContraint, String min, String max) {
		super();
		this.cardinalityContraint = cardinalityContraint;
		this.min = min;
		this.max = max;
	}

	public void execute() {
		cardinalityContraint.setCardMin(min);
		cardinalityContraint.setCardMax(max);
	}

	@Override
	public void undo() {
		cardinalityContraint.setCardMin(oldMin);
		cardinalityContraint.setCardMax(oldMax);
	}

	public void redo() {
		execute();
	}

	@Override
	protected boolean prepare() {
		oldMin = cardinalityContraint.getCardMin();
		oldMax = cardinalityContraint.getCardMax();
		try {
			int min = Integer.parseInt(this.min);
			int max = Integer.parseInt(this.max);

			if (min > max) {
				this.min = this.max;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return true;
	}

}
