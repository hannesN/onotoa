package de.topicmapslab.tmcledit.model.util.extension;

import de.topicmapslab.tmcledit.model.annotationprovider.IAnnotationProposalProvider;
import de.topicmapslab.tmcledit.model.annotationprovider.IAnnotationValidator;

public class AnnotationProviderInfo {
	private String id;
	private String name;
	private IAnnotationValidator validator;
	private IAnnotationProposalProvider porposalProvider;
	
	public AnnotationProviderInfo(String id, String name, IAnnotationValidator validator,
            IAnnotationProposalProvider porposalProvider) {
	    super();
	    this.id = id;
	    this.name = name;
	    this.validator = validator;
	    this.porposalProvider = porposalProvider;
    }

	public String getId() {
    	return id;
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
