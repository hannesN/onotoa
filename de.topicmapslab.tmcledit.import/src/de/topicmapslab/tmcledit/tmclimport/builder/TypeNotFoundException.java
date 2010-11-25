/**
 * 
 */
package de.topicmapslab.tmcledit.tmclimport.builder;

/**
 * Exception thrown when a topic type of a specific topic doesn't exists.
 * 
 * @author Hannes Niederhausen
 *
 */
public class TypeNotFoundException extends RuntimeException {
	
    private static final long serialVersionUID = -2575057545928838332L;

    public TypeNotFoundException(String msg) {
    	super(msg);
    }
    
    public TypeNotFoundException(String msg, Throwable cause) {
    	super(msg, cause);
    }
}
