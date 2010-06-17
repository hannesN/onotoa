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
package de.topicmapslab.onotoa.search.views;

import de.topicmapslab.kuria.annotation.Text;
import de.topicmapslab.kuria.annotation.tree.TreeNode;
import de.topicmapslab.kuria.annotation.widgets.TextField;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.ModelFactory;

/**
 * @author sip
 * 
 */
@TreeNode
public class DiagramWrapper {

	private static Diagram dia = ModelFactory.eINSTANCE.createDiagram();;

	public DiagramWrapper(Diagram dia) {

		this.dia = dia;
		

	}
	public String getName() {
		return dia.getName().toString();
	}

}
