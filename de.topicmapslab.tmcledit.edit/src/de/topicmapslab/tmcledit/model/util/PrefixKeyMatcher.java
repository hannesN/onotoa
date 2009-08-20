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
package de.topicmapslab.tmcledit.model.util;

import java.util.regex.Pattern;

/**
 * @author Hannes Niederhausen
 *
 */
public class PrefixKeyMatcher {
	private static final String regExp = "(\\p{Alpha}|[\\u00C0-\\u00D6]|"
	+ "[\\u00D8-\\u00F6]|" + "[\\u00F8-\\u02FF]|"
	+ "[\\u0370-\\u037D]|" + "[\\u037F-\\u1FFF]|"
	+ "[\\u200C-\\u200D]|" + "[\\u2070-\\u218F]|"
	+ "[\\u2C00-\\u2FEF]|" + "[\\u3001-\\uD7FF]|"
	+ "[\\uF900-\\uFDCF]|" + "[\\uFDF0-\\uFFFD]|"
	+ "[\\u10000-\\uEFFFF])*(\\." + "(\\p{Alpha}|"
	+ "[\\u00C0-\\u00D6]|" + "[\\u00D8-\\u00F6]|"
	+ "[\\u00F8-\\u02FF]|" + "[\\u0370-\\u037D]|"
	+ "[\\u037F-\\u1FFF]|" + "[\\u200C-\\u200D]|"
	+ "[\\u2070-\\u218F]|" + "[\\u2C00-\\u2FEF]|"
	+ "[\\u3001-\\uD7FF]|" + "[\\uF900-\\uFDCF]|"
	+ "[\\uFDF0-\\uFFFD]|" + "[\\u10000-\\uEFFFF]"
	+ "\\d|'-'|\\u00B7|[\\u0300-\\u036F] | [\\u203F-\\u2040])*)*";

	@SuppressWarnings("unused")
    private static Pattern pattern = Pattern.compile(regExp);
	
	public static boolean isValidKey(String key) {
		return true;
		//pattern.matcher(key).matches();
	}
	
	public static void main(String[] args) {
	    String tests[] = {
	    		"urn:hannes/dort",
	    		"http://www.hannesniederhausen.de",
	    		"urn:onotoa-x",
	    		"lala:¼½³¼.de"    		    		
	    };
	    
	    for (String s : tests) {
	    	System.out.println(s+" - "+isValidKey(s));
	    }
    }
}
