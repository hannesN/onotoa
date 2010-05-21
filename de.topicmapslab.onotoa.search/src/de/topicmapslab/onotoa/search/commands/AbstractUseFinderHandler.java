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
package de.topicmapslab.onotoa.search.commands;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Shell;

import de.topicmapslab.onotoa.search.dialogs.ResultDialog;
import de.topicmapslab.onotoa.search.model.tree.TreeNode;
import de.topicmapslab.onotoa.search.util.UseFinder;
import de.topicmapslab.tmcledit.model.TopicType;

/**
 * @author niederhausen
 *
 */
public abstract class AbstractUseFinderHandler extends AbstractHandler {

	/**
	 * 
	 */
	public AbstractUseFinderHandler() {
		super();
	}

	/**
	 * @param shell
	 * @param tt
	 */
	protected void startSearch(Shell shell, TopicType tt) {
		ProgressMonitorDialog dlg = new ProgressMonitorDialog(shell);
		try {
			FindRunnable runnable = new FindRunnable(tt);
			dlg.run(false, false, runnable);
			
			ResultDialog resultDlg = new ResultDialog(shell, tt);
			resultDlg.setInput(runnable.getResult());
			
			resultDlg.open();
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	final class FindRunnable implements IRunnableWithProgress {
		
		private final TopicType type;
		private List<TreeNode> result;

		/**
		 * 
		 */
		public FindRunnable(TopicType type) {
			this.type = type;
		}
		
		public void run(IProgressMonitor monitor) throws InvocationTargetException,
				InterruptedException {
				result = UseFinder.findUse(type, monitor);
		}
		
		/**
		 * @return the result
		 */
		public List<TreeNode> getResult() {
			if (result==null)
				return Collections.emptyList();
			return result;
		}
	}
}