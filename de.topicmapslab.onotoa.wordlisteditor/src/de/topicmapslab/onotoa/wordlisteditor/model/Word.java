/*******************************************************************************
 * Copyright (c) 2008-2011 Topic Maps Lab and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *      Hannes Niederhausen - initial API and implementation
 ******************************************************************************/
package de.topicmapslab.onotoa.wordlisteditor.model;

import de.topicmapslab.tmcledit.model.KindOfTopicType;

/**
 * Model containing one word and its kind of topic type.
 * 
 * @author Hannes Niederhausen
 *
 */
public class Word {

	private String word;
	
	private KindOfTopicType type;
	
	private String comment;
	
	/**
     * 
     */
    public Word() {
	    word = "new word";
	    type=KindOfTopicType.NO_TYPE;
    }
	
	/**
     * @return the type
     */
    public KindOfTopicType getType() {
	    return type;
    }
    
    /**
     * @return the word
     */
    public String getWord() {
	    return word;
    }
    
    /**
     * @param type the type to set
     */
    public void setType(KindOfTopicType type) {
	    this.type = type;
    }
    
    /**
     * @param word the word to set
     */
    public void setWord(String word) {
	    this.word = word;
    }
	
    
    /**
     * @return the comment
     */
    public String getComment() {
	    return comment;
    }
    
    /**
     * @param comment the comment to set
     */
    public void setComment(String comment) {
	    this.comment = comment;
    }
}
