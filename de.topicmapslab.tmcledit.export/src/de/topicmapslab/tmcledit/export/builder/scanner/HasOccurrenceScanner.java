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

import java.util.Collection;
import java.util.List;

import org.tmapi.core.Construct;
import org.tmapi.core.Topic;

import de.topicmapslab.ctm.writer.templates.TemplateMatching;
import de.topicmapslab.tmql4j.components.processor.results.model.IResult;
import de.topicmapslab.tmql4j.query.IQuery;

/**
 * @author Sven Krosse
 * @author Hannes Niederhausen
 * 
 */
public class HasOccurrenceScanner extends AbstractConstraintScanner {

	protected void parseResults(IQuery q) {
	    for (IResult result : q.getResults()) {
	    	TemplateMatching matching = new TemplateMatching();
	    	matching.setContext((Topic) ((List<?>) result.getResults().get(0)).get(0));
	    	matching.addArgument(((List<?>) result.getResults().get(1)).get(0));
	    	matching.addArgument(((List<?>) result.getResults().get(2)).get(0));
	    	matching.addArgument(((List<?>) result.getResults().get(2)).get(0));
	    	matching.addAffectedConstruct((Topic) result.getResults().get(4));
	    	Object res5 = result.get(5);
	    	if (res5 instanceof Collection<?>)
	    		addAffectedConstructs((Collection<?>) res5, matching);
	    	else
	    		matching.addAffectedConstruct((Construct) res5);
			
	    	addMatching(matching);
	    }
    }
	
	protected String getQuery() {
		String query = TMCLPREFIX+"FOR $c IN // tmcl:topic-occurrence-constraint "
				+ "group by $4 "
		        + "RETURN  ( $c >> traverse tmcl:constrained-topic-type, "
		        + "$c >> traverse tmcl:constrained-statement, "
		        + "$c  / tmcl:card-min || \"0\" , $c / tmcl:card-max || \"*\" , $c, $c << players << roles)";
		return query;
	}

}
