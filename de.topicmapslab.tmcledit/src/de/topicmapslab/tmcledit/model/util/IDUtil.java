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

/**
 * @author Hannes Niederhausen
 *
 */
public class IDUtil {
	private static int lastId;
	
	public static void setLastId(int lastId) {
	    if (IDUtil.lastId<lastId)
	    	IDUtil.lastId = lastId;
    }
	
	public static int nextId() {
		lastId++;
		return lastId; 
	}
}
