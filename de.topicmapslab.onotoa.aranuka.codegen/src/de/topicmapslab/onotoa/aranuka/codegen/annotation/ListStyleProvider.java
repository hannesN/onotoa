/**
 * 
 */
package de.topicmapslab.onotoa.aranuka.codegen.annotation;

import java.util.Arrays;
import java.util.Collection;

import de.topicmapslab.tmcledit.model.annotationprovider.IAnnotationProposalProvider;

/**
 * @author Hannes Niederhausen
 *
 */
public class ListStyleProvider implements IAnnotationProposalProvider {

	/**
	 * 
	 */
	public ListStyleProvider() {
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
		return Arrays.asList("COMPACT", "TABLE");
	}

}
