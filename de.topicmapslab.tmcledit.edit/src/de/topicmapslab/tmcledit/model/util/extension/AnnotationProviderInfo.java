package de.topicmapslab.tmcledit.model.util.extension;

import de.topicmapslab.tmcledit.model.annotationprovider.IAnnotationProposalProvider;
import de.topicmapslab.tmcledit.model.annotationprovider.IAnnotationValidator;

public class AnnotationProviderInfo {
	private String id;
	private String name;
	private boolean internal;
	private IAnnotationValidator validator;
	private IAnnotationProposalProvider porposalProvider;
	
	public AnnotationProviderInfo(String id, String name, boolean internal, IAnnotationValidator validator,
            IAnnotationProposalProvider porposalProvider) {
	    super();
	    this.id = id;
	    this.name = name;
	    this.internal = internal;
	    this.validator = validator;
	    this.porposalProvider = porposalProvider;
    }

	public String getId() {
    	return id;
    }

	public boolean isInternal() {
	    return internal;
    }
	
	public String getName() {
    	return name;
    }

	public IAnnotationValidator getValidator() {
    	return validator;
    }

	public IAnnotationProposalProvider getPorposalProvider() {
    	return porposalProvider;
    }	
}
