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
package de.topicmapslab.onotoa.search.util;

import java.util.Comparator;

import de.topicmapslab.tmcledit.model.TopicType;

/**
 * @author sip
 * 
 */
public class TopicTypeComparator implements Comparator<TopicType> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(TopicType o1, TopicType o2) {

		if (o1.getKind().getValue() == o2.getKind().getValue()) {

			if (o1.getName().compareTo(o2.getName()) == 0)
				return 0;
			else if (o1.getName().compareTo(o2.getName()) <= -1)
				return -1;
			return 1;

		} else if (o1.getKind().getValue() <= o2.getKind().getValue())
			return -1;

		return 1;
	}

}
