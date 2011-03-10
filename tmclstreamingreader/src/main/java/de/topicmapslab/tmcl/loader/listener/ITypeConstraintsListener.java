package de.topicmapslab.tmcl.loader.listener;

import org.tmapi.core.Topic;

import de.topicmapslab.tmcl.loader.reader.ConstraintReader;

/**
 * Interface definition of a Simple API for TMCL (SAT) listener.
 * <p>
 * The listener is called if a {@link ConstraintReader} found a constraints for
 * a type definition. The following constraints are supported:<br />
 * - a type name: <code> - "name" </code><br />
 * - an instanceOf declaration: <code> "isa type" </code> <br />
 * - a kindOf declaration: <code> "ako type" </code> <br />
 * - a tmcl:abstract-constraint: <code> is-abstract() </code><br />
 * - a tmcl:overlap-declaration: <code> overlaps(tt) </code>
 * 
 * </p>
 * 
 * @author Sven Krosse
 * @email krosse@informatik.uni-leipzig.de
 * 
 */
public interface ITypeConstraintsListener {

	/**
	 * Event is fired if a topic type name is found.<br />
	 * <p>
	 * CTM: <code> - "name" </code><br />
	 * </p>
	 * 
	 * @param type
	 *            the topic type
	 * @param name
	 *            the found name of the type
	 */
	public void typeName(final Topic type, final String name);

	/**
	 * Event is fired if a tmcl:overlap-declaration is found.<br />
	 * <p>
	 * CTM: <code> 
	 * overlaps(tt)
	 * </code>
	 * </p>
	 * 
	 * @param type
	 *            the topic type
	 * @param otherType
	 *            the overlapping otherType
	 */
	public void overlapDeclarationElement(final Topic type,
			final Topic otherType);

	/**
	 * Event is fired if a tmcl:abstract-constrain is found.<br />
	 * <p>
	 * CTM: <code> 
	 * is-abstract()
	 * </code>
	 * </p>
	 * 
	 * @param type
	 *            the topic type
	 */
	public void isAbstractElement(final Topic type);

	/**
	 * Event is fired if an instanceOf declaration is found.<br />
	 * <p>
	 * CTM: <code> 
	 * isa type
	 * </code>
	 * </p>
	 * 
	 * @param type
	 *            the topic type
	 * @param supertype
	 *            the found type
	 */
	public void isInstanceOf(final Topic type, final Topic supertype);

	/**
	 * Event is fired if a kindOf declaration is found.<br />
	 * <p>
	 * CTM: <code>
	 * ako type 
	 * </code>
	 * </p>
	 * 
	 * @param type
	 *            the topic type
	 * @param supertype
	 *            the found supertype
	 */
	public void aKindOf(final Topic type, final Topic supertype);
	
	/**
	 * Event is fired if a topic type subject locator is found.<br />
	 * <p>
	 * CTM: <code> = <IRI> </code><br />
	 * </p>
	 * 
	 * @param type
	 *            the topic type
	 * @param locator
	 *            the found subject locator of the type
	 */
	public void subjectLocator(final Topic type, final String locator);
	
	/**
	 * Event is fired if a topic type subject identifier is found.<br />
	 * <p>
	 * CTM: <code> <IRI> </code><br />
	 * </p>
	 * 
	 * @param type
	 *            the topic type
	 * @param identifier
	 *            the found subject identifier of the type
	 */
	public void subjectIdentifier(final Topic type, final String identifier);
	
	/**
	 * Event is fired if end of document is found
	 */
	public void endOfDocument();
}
