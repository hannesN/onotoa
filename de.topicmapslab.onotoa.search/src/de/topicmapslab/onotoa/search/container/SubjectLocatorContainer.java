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
package de.topicmapslab.onotoa.search.container;

import java.util.List;

import de.topicmapslab.kuria.annotation.Text;
import de.topicmapslab.kuria.annotation.tree.Children;
import de.topicmapslab.kuria.annotation.tree.TreeNode;
import de.topicmapslab.onotoa.search.searchImpl.ISearcher;

/**
 * @author sip
 *
 */
@TreeNode
public class SubjectLocatorContainer extends AbstractContainer{

	/**
     * @param label
     * @param searcher
     */
    public SubjectLocatorContainer(String label, ISearcher searcher) {
	    super(label, searcher);
	    // TODO Auto-generated constructor stub
    }
    @Text
    public String getlabel(){
    	return super.getLabel();
    }

    @Children
    public List<Object> getChildren(){
    	return super.getContentList();
    }
	/**
     * {@inheritDoc}
     */
    public void addAdapter() {
	    // TODO Auto-generated method stub
	    
    }

}
