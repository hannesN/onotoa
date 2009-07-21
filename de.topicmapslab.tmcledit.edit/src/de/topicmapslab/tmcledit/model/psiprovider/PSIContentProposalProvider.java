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
package de.topicmapslab.tmcledit.model.psiprovider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.jface.fieldassist.IContentProposalProvider;

import de.topicmapslab.tmcledit.model.TmcleditEditPlugin;
import de.topicmapslab.tmcledit.model.util.extension.PSIProviderInfo;

/**
 * @author Hannes Niederhausen
 *
 */
public class PSIContentProposalProvider implements IContentProposalProvider {
	public final static char keys[] = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
	        'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '_', 'a', 'b', 'c', 'd', 'e', 'f', 'g',
	        'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '1', '2',
	        '3', '4', '5', '6', '7', '8', '9', '0' };
	
	private List<PSIProviderInfo> infos = TmcleditEditPlugin.getPlugin().getPsiProviderInfos(); 
	
	private String name;
	
	public IContentProposal[] getProposals(String contents, int position) {
		ArrayList<PSIContentProposal> results = new ArrayList<PSIContentProposal>();
		
		String subString = null;
		if ((position+1)<contents.length())
			subString = contents.substring(0,position+1);
		else
			subString = contents;
		
		for (PSIProviderInfo info : infos) {
			IPSIProvider prov = info.getProvider();
			prov.setName(name);
			
			for (PSIProviderResult s : prov.getSubjectIdentifierStartingWith(subString)) {
				results.add(new PSIContentProposal(s));
			}
		}
		
		// sorting results
		Collections.sort(results);
		
		return results.toArray(new PSIContentProposal[results.size()]);
	}

	public void setName(String name) {
	    this.name = name;
    }
	
	
	private class PSIContentProposal implements IContentProposal, Comparable<PSIContentProposal> {

		private final PSIProviderResult result;
		
		public PSIContentProposal(PSIProviderResult result) {
	        super();
	        this.result = result;
        }

		public String getContent() {
	        return result.getIdentifier();
        }

		public int getCursorPosition() {
	       return result.getIdentifier().length();
        }

		public String getDescription() {
	        return result.getDescription()!=null?result.getDescription():"";
        }

		public String getLabel() {
	        return result.getIdentifier();
        }
		
		public int compareTo(PSIContentProposal o) {
			return result.getIdentifier().compareTo(o.result.getIdentifier());
		}
		
	}
}
