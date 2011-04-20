package de.topicmapslab.tmcledit.model.util.extension;

import de.topicmapslab.tmcledit.model.annotationprovider.IAnnotationProposalProvider;
import de.topicmapslab.tmcledit.model.annotationprovider.IAnnotationValidator;

/**
 * Info class containing information about a eclipse extension
 * @author Hannes Niederhausen
 *
 */
public class AnnotationProviderInfo {
	private String id;
	private String name;
	private boolean internal;
	private IAnnotationValidator validator;
	private IAnnotationProposalProvider porposalProvider;
	
	/**
	 * Constructor
	 * @param id
	 * @param name
	 * @param internal
	 * @param validator
	 * @param porposalProvider
	 */
	public AnnotationProviderInfo(String id, String name, boolean internal, IAnnotationValidator validator,
            IAnnotationProposalProvider porposalProvider) {
	    super();
	    this.id = id;
	    this.name = name;
	    this.internal = internal;
	    this.validator = validator;
	    this.porposalProvider = porposalProvider;
    }

	/**
	 * @return
	 */
	public String getId() {
    	return id;
    }

	/**
	 * @return
	 */
	public boolean isInternal() {
	    return internal;
    }
	
	/**
	 * @return
	 */
	public String getName() {
    	return name;
    }

	/**
	 * @return
	 */
	public IAnnotationValidator getValidator() {
    	return validator;
    }

	/**
	 * @return
	 */
	public IAnnotationProposalProvider getPorposalProvider() {
    	return porposalProvider;
    }	
}
