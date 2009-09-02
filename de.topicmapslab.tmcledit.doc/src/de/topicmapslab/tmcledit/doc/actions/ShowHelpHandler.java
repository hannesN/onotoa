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
package de.topicmapslab.tmcledit.doc.actions;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.expressions.EvaluationContext;
import org.eclipse.ui.IWorkbenchWindow;

/**
 * @author Hannes Hannes Niederhausen
 *
 */
public class ShowHelpHandler extends AbstractHandler {
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		EvaluationContext ctx = (EvaluationContext) event.getApplicationContext();
		IWorkbenchWindow w = (IWorkbenchWindow) ctx.getParent().getVariable("activeWorkbenchWindow");
		
		w.getWorkbench().getHelpSystem().displayHelp();
		
		
		return null;
	}

}
