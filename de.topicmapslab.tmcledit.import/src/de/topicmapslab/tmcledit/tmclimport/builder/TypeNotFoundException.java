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

    /**
     * Constructor
     * @param msg the error message
     */
    public TypeNotFoundException(String msg) {
    	super(msg);
    }
    
    /**
     * Constructor
     * @param msg the error message
     * @param cause a cause for the exception, which is an exception
     */
    public TypeNotFoundException(String msg, Throwable cause) {
    	super(msg, cause);
    }
}
