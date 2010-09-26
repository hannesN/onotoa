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
package de.topicmapslab.tmcledit.export.builder.scanner;

import org.tmapi.core.Association;
import org.tmapi.core.Topic;

import de.topicmapslab.ctm.writer.templates.TemplateMatching;
import de.topicmapslab.tmql4j.common.model.query.IQuery;
import de.topicmapslab.tmql4j.resultprocessing.model.IResult;

/**
 * @author Sven Krosse
 * @author Hannes Niederhausen
 * 
 */
public class HasItemIdentifierScanner extends AbstractConstraintScanner {

	protected void parseResults(IQuery q) {
	    for (IResult result : q.getResults()) {
	    	TemplateMatching matching = new TemplateMatching();
	    	matching.setContext((Topic) result.getResults().get(0));
	    	matching.addArgument(result.getResults().get(1));
	    	matching.addArgument(result.getResults().get(2));
	    	matching.addArgument(result.getResults().get(3));
	    	matching.addAffectedConstruct((Topic) result.getResults().get(4));
	    	matching.addAffectedConstruct((Association) result.getResults().get(5));
	    	addMatching(matching);
	    }
    }
	
	protected String getQuery() {
		String query = "FOR $c IN // tmcl:item-identifier-constraint "
		        + "RETURN  ( $c >> traverse tmcl:constrained-topic-type, "
		        + "$c  / tmcl:card-min || \"0\" , " 
		        + "$c / tmcl:card-max || \"*\" , " 
		        + "$c / tmcl:regexp || \".*\" , "
		        + "$c, "
		        + "$c << players)";
		return query;
	}

}
