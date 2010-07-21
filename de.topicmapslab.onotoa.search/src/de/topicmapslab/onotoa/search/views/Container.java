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

import java.util.ArrayList;
import java.util.List;

import de.topicmapslab.kuria.annotation.tree.Children;

/**
 * @author sip
 * 
 */
public class Container {

	@Children
	private List<DiagramWrapper> list = new ArrayList<DiagramWrapper>();

	/**
	 * @return the list
	 */
	public List<DiagramWrapper> getList() {
		
		for (DiagramWrapper dw : list)
		System.out.println(dw.getName().toString());
		return list;
	}

	/**
	 * @param list
	 *            the list to set
	 */
	public void setList(List<DiagramWrapper> list) {
		this.list = list;
	}

}
