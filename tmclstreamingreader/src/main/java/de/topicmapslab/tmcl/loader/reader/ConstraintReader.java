package de.topicmapslab.tmcl.loader.reader;

import java.io.File;

import org.tmapi.core.TopicMap;
import org.tmapi.core.TopicMapSystem;
import org.tmapi.core.TopicMapSystemFactory;

import de.topicmapslab.tmcl.loader.listener.ITypeConstraintsListener;
import de.topicmapslab.tmcl.loader.listener.IWorkMonitor;
import de.topicmapslab.tmcl.loader.util.Constraints;
import de.topicmapslab.tmql4j.exception.TMQLRuntimeException;

/**
 * Implementation of a {@link ITMCLReader}
 * 
 * <p>
 * The implementation of this TMCL reader extract all constraint definitions of
 * the TMCL file.
 * </p>
 * 
 * @author Sven Krosse
 * @email krosse@informatik.uni-leipzig.de
 * 
 */
public class ConstraintReader extends TMCLTypesReader {

	/**
	 * internal reference of constraints utility
	 */
	private final Constraints constraints;

	/**
	 * base constructor to create a simple instance
	 * <p>
	 * The constructor supports a serialized topic map given in Compact Topic
	 * Maps Notation ( *.ctm ) or in XML Topic Maps Notation ( *.xtm , *.xtm20 ,
	 * *.xtm10 )
	 * </p>
	 * 
	 * @param file
	 *            the serialized topic map as file
	 * @throws Exception
	 *             thrown if initialization fails
	 */
	public ConstraintReader(File file) throws Exception {
		super(file);
		this.constraints = new Constraints(getTopicMap(), getTopicMapSystem());
	}

	/**
	 * base constructor to create a simple instance
	 * <p>
	 * The constructor supports a serialized topic map given in Compact Topic
	 * Maps Notation ( *.ctm ) or in XML Topic Maps Notation ( *.xtm , *.xtm20 ,
	 * *.xtm10 )
	 * </p>
	 * 
	 * @param file
	 *            the serialized topic map as file
	 * @param topicMapSystemFactory
	 *            the topicMapSystemFactory to use
	 * @throws Exception
	 *             thrown if initialization fails
	 */
	public ConstraintReader(File file,
			TopicMapSystemFactory topicMapSystemFactory) throws Exception {
		super(file, topicMapSystemFactory);
		this.constraints = new Constraints(getTopicMap(), getTopicMapSystem());
	}

	/**
	 * Constructor which requires a topic map and a topic map system
	 * 
	 * @param tm
	 *            the topic map which contains the schema
	 * @param tms
	 *            a topic map system which can create new topic maps
	 * @throws TMQLRuntimeException
	 */
	public ConstraintReader(TopicMap tm, TopicMapSystem tms)
			throws TMQLRuntimeException {
		super(tm, tms);
		this.constraints = new Constraints(tm, tms);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void parse(IWorkMonitor monitor) throws Exception {

		monitor.start(23);

		// parsing types
		parseTopicTypes(monitor);

		constraints.hasDatatype();
		monitor.stepDone();

		constraints.hasItemIdentifier();
		monitor.stepDone();

		constraints.hasSubjectIdentifier();
		monitor.stepDone();
		
		constraints.hasSubjectLocator();
		monitor.stepDone();

		constraints.instanceOf();
		monitor.stepDone();
		
		constraints.kindOf();
		monitor.stepDone();
		
		constraints.isAbstract();
		monitor.stepDone();

		constraints.hasName();
		monitor.stepDone();

		constraints.hasOccurrence();
		monitor.stepDone();

		constraints.hasRole();
		monitor.stepDone();

		constraints.hasScope();
		monitor.stepDone();

		constraints.isUnique();
		monitor.stepDone();

		constraints.playsRole();
		monitor.stepDone();

		constraints.matchesRegexp();
		monitor.stepDone();

		constraints.overlaps();
		monitor.stepDone();

		constraints.reifierConstraint();
		monitor.stepDone();

		constraints.roleCombination();
		monitor.stepDone();

		constraints.topicRefiesConstraint();
		monitor.stepDone();

		monitor.finished();
	}

	/**
	 * Add the given listener to the internal list if it was not add before.
	 * 
	 * @param listener
	 *            the listener to add
	 */
	public void addEventListener(ITypeConstraintsListener listener) {
		constraints.addEventListener(listener);
	}

	/**
	 * Removes the given listener from the internal list
	 * 
	 * @param listener
	 *            the listener to remove
	 */
	public void removeListener(ITypeConstraintsListener listener) {
		constraints.removeListener(listener);
	}

}
