package de.topicmapslab.tmcl.loader.listener;

import org.tmapi.core.Topic;

/**
 * Interface definition of a Simple API for TMCL (SAT) listener.
 * <p>
 * The listener is called if a TMCLTypesReader found a type element.
 * </p>
 * 
 * @author Sven Krosse
 * @email krosse@informatik.uni-leipzig.de
 * 
 */
public interface ITypesListener {

	/**
	 * Event is fired if a schema definition is found during the parsing
	 * process of the TMCL file.
	 * 
	 * @param type
	 *            the found tmcl:schema
	 */
	public void schemaElement(final Topic type);
	
	/**
	 * Event is fired if a topic-type definition is found during the parsing
	 * process of the TMCL file.
	 * 
	 * @param type
	 *            the found tmcl:topic-type
	 */
	public void topicTypeElement(final Topic type);

	/**
	 * Event is fired if a name-type definition is found during the parsing
	 * process of the TMCL file.
	 * 
	 * @param type
	 *            the found tmcl:name-type
	 */
	public void nameTypeElement(final Topic type);

	/**
	 * Event is fired if a occurrence-type definition is found during the
	 * parsing process of the TMCL file.
	 * 
	 * @param type
	 *            the found tmcl:occurrence-type
	 */
	public void occurrenceTypeElement(final Topic type);

	/**
	 * Event is fired if a role-type definition is found during the parsing
	 * process of the TMCL file.
	 * 
	 * @param type
	 *            the found tmcl:role-type
	 */
	public void roleTypeElement(final Topic type);

	/**
	 * Event is fired if a association-type definition is found during the
	 * parsing process of the TMCL file.
	 * 
	 * @param type
	 *            the found tmcl:association-type
	 */
	public void associationTypeElement(final Topic type);

	/**
	 * Event is fired if end of document is found
	 */
	public void endOfDocument();
}
