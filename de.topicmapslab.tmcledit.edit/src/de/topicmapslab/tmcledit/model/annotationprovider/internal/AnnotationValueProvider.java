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
package de.topicmapslab.tmcledit.model.annotationprovider.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.jface.fieldassist.IContentProposalProvider;

import de.topicmapslab.tmcledit.model.annotationprovider.IAnnotationProposalProvider;

/**
 * @author Hannes Niederhausen
 *
 */
public class AnnotationValueProvider implements IContentProposalProvider {
	/**
	 * An array for keys which trigger the auto completion proposals
	 */
	public final static char KEYS[] = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
        'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '_', 'a', 'b', 'c', 'd', 'e', 'f', 'g',
        'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '1', '2',
        '3', '4', '5', '6', '7', '8', '9', '0' };

	private IAnnotationProposalProvider provider;
	
	/**
	 * {@inheritDoc}
	 */
	public IContentProposal[] getProposals(String contents, int position) {
		List<AnnotationKeyProposal> results = null;
		
		
		
		if (provider==null)
			return new IContentProposal[0];
		
		results = new ArrayList<AnnotationKeyProposal>();
		for (String s : provider.getProposals()) {
			results.add(new AnnotationKeyProposal(s, position));			
		}
		
		Collections.sort(results);
		
		return results.toArray(new IContentProposal[results.size()]);
	}

	/**
	 * Sets the proposal provider
	 * @param provider the {@link IAnnotationProposalProvider} instance
	 */
	public void setProvider(IAnnotationProposalProvider provider) {
	    this.provider = provider;
    }
	
	private class AnnotationKeyProposal implements IContentProposal, Comparable<AnnotationKeyProposal> {
		private final String s;
		private final int offset;
		
		public AnnotationKeyProposal(String s, int offset) {
			this.s = s;
			this.offset = offset;
		}
		
		public String getContent() {
	        return s.substring(offset);
        }

		public int getCursorPosition() {
	       return s.length()-offset;
        }

		
		public int compareTo(AnnotationKeyProposal o) {
			return s.compareTo(o.s);
		}

		public String getDescription() {
	        return null;
        }

		public String getLabel() {
	        return s;
        }
		
	}
}
