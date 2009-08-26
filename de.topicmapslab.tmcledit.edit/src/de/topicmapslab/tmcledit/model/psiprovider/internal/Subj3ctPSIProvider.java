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
package de.topicmapslab.tmcledit.model.psiprovider.internal;


import java.io.StringReader;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.xml.sax.InputSource;

import de.topicmapslab.tmcledit.model.psiprovider.PSIProvider;
import de.topicmapslab.tmcledit.model.psiprovider.PSIProviderResult;

/**
 * @author Hannes Niederhausen
 *
 */
public class Subj3ctPSIProvider extends PSIProvider {
	
	public Set<PSIProviderResult> getSubjectIdentifier() {
		if (getName().length()==0)
			return Collections.emptySet();
		

    	HttpMethod method = null;
    	try {
    		String url = "http://api.subj3ct.com/subjects/search";
    		
    		HttpClient client = new HttpClient();
    		method = new GetMethod(url);
    
    		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>(2);
    		params.add(new NameValuePair("format", "xml"));
    		params.add(new NameValuePair("query", getName()));
    		method.setQueryString(params.toArray(new NameValuePair[params.size()]));

    		client.getParams().setSoTimeout(500);
            client.executeMethod(method);
            
            String result = method.getResponseBodyAsString();
            
            	        
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            Subj3ctXmlHandler handler = new Subj3ctXmlHandler();
    		parser.parse(new InputSource(new StringReader(result)), handler);
            
    		List<Subje3ctResult> resultList = handler.getResultList();
    		if (resultList.size()==0) {
    			return Collections.emptySet();
    		}
    		
    		Set<PSIProviderResult> resultSet = new HashSet<PSIProviderResult>(resultList.size());
            for (Subje3ctResult r : resultList) {
            	String description = "";
            	if (r.name!=null)
            		description = "Name: "+r.name+"\n";
            	if (r.description!=null)
            		description += "Description: "+r.description+"\n";
            	
            	description += "\n\nThis service is provided by http://www.subj3ct.com";
            	
            	resultSet.add(new PSIProviderResult(r.identifier, description));
            }
            
            return Collections.unmodifiableSet(resultSet);
        } catch (UnknownHostException e) {
        	// no http connection -> no results
        	return Collections.emptySet();
    	} catch (Exception e) {
            throw new RuntimeException(e);
            
        } finally {
        	if (method != null)
        		method.releaseConnection();
        }		
	}
	
}
