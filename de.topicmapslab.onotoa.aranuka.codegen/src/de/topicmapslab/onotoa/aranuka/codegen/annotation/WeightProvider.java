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
package de.topicmapslab.onotoa.aranuka.codegen.annotation;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import de.topicmapslab.tmcledit.model.annotationprovider.IAnnotationProposalProvider;

/**
 * Completion provider which values are allowed.
 * 
 * @author Hannes Niederhausen
 *
 */
public class WeightProvider implements IAnnotationProposalProvider {

	/**
     * 
     */
    private static final List<String> VALUE_LIST = Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10");

	/**
	 * 
	 */
	public WeightProvider() {
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean newValuesAllowed() {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Collection<String> getProposals() {
		return VALUE_LIST;
	}

}
