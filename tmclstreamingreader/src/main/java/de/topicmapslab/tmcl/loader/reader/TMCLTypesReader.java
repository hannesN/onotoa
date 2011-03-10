package de.topicmapslab.tmcl.loader.reader;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import org.tmapi.core.Topic;
import org.tmapi.core.TopicMap;
import org.tmapi.core.TopicMapSystem;
import org.tmapi.core.TopicMapSystemFactory;

import de.topicmapslab.tmcl.loader.listener.ITypesListener;
import de.topicmapslab.tmcl.loader.listener.IWorkMonitor;
import de.topicmapslab.tmql4j.components.processor.results.model.IResult;
import de.topicmapslab.tmql4j.components.processor.results.model.IResultSet;
import de.topicmapslab.tmql4j.components.processor.results.tmdm.SimpleResultSet;
import de.topicmapslab.tmql4j.components.processor.runtime.ITMQLRuntime;
import de.topicmapslab.tmql4j.exception.TMQLRuntimeException;
import de.topicmapslab.tmql4j.query.IQuery;

/**
 * Implementation of a {@link ITMCLReader}.
 * 
 * <p>
 * This TMCL reader only extract the topic-types, name-types, occurrence-types,
 * role-types and association-types.
 * </p>
 * 
 * @author Sven Krosse
 * @email krosse@informatik.uni-leipzig.de
 * 
 */
public class TMCLTypesReader extends BaseReader {

	/**
	 * Internal listener list
	 */
	private final List<ITypesListener> listeners = new LinkedList<ITypesListener>();
	
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
	public TMCLTypesReader(File file) throws Exception {
		super(file);
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
	 * 			  the topicMapSystemFactory to use
	 * @throws Exception
	 *             thrown if initialization fails
	 */
	public TMCLTypesReader(File file,
			TopicMapSystemFactory topicMapSystemFactory) throws Exception {
		super(file, topicMapSystemFactory);
	}

	
	/**
	 * Constructor which requires a topic map and a topic map system
	 * @param tm the topic map which contains the schema
	 * @param tms a topic map system which can create new topic maps
	 * @throws TMQLRuntimeException
	 */
	public TMCLTypesReader(TopicMap tm, TopicMapSystem tms)
			throws TMQLRuntimeException {
		super(tm, tms);
	}



	/**
	 * Add the given listener to the internal list if it was not add before.
	 * 
	 * @param listener
	 *            the listener to add
	 */
	public void addEventListener(ITypesListener listener) {
		if (!listeners.contains(listener)) {
			listeners.add(listener);
		}
	}

	/**
	 * Removes the given listener from the internal list
	 * 
	 * @param listener
	 *            the listener to remove
	 */
	public void removeListener(ITypesListener listener) {
		this.listeners.remove(listener);
	}

	/**
	 * {@inheritDoc}
	 */
	public void parse(IWorkMonitor monitor) throws Exception {
		
		monitor.start(5);
		
		parseTopicTypes(monitor);
		
		monitor.finished();
	}


	/**
	 * Parses the topic types and uses the given work monitor
	 * @param monitor
	 */
	protected void parseTopicTypes(IWorkMonitor monitor) {
		parserTopicTypes();
		monitor.stepDone();
		
		parserAssociationTypes();
		monitor.stepDone();
		
		parserNameTypes();
		monitor.stepDone();
		
		parserOccurrenceTypes();
		monitor.stepDone();
		
		parserRoleTypes();
		monitor.stepDone();
		

		for (ITypesListener listener : listeners) {
			listener.endOfDocument();
		}
	}

	/**
	 * parses the schema topics
	 */
	public void parserSchemaTopics() {
		if (!topicExists(tmclPrefix + "topic-type"))
			return;
		final String query = tmclPrefix + "schema" + "  >> instances ";
		try {
			SimpleResultSet set = execute(query);

			for (IResult result : set) {
				for (Object obj : result) {
					for (ITypesListener listener : listeners) {
						listener.schemaElement((Topic) obj);
					}
				}
			}

		} catch (TMQLRuntimeException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Internal parse method to extract all topic-types defined by the TMCL
	 * file.
	 */
	public void parserTopicTypes() {
		if (!topicExists(tmclPrefix + "topic-type"))
			return;
		
		final String query = tmclPrefix + "topic-type" + "  >> instances ";
		try {
			SimpleResultSet set = execute(query);

			for (IResult result : set) {
				for (Object obj : result) {
					for (ITypesListener listener : listeners) {
						listener.topicTypeElement((Topic) obj);
					}
				}
			}

		} catch (TMQLRuntimeException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Internal parse method to extract all name-types defined by the TMCL file.
	 */
	public void parserNameTypes() {
		if (!topicExists(tmclPrefix + "name-type"))
			return;
		
		final String query = tmclPrefix + "name-type" + "  >> instances ";

		try {
			SimpleResultSet set = execute(query);

			for (IResult result : set) {
				for (Object obj : result) {
					for (ITypesListener listener : listeners) {
						listener.nameTypeElement((Topic) obj);
					}
				}
			}

		} catch (TMQLRuntimeException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Internal parse method to extract all occurrence-types defined by the TMCL
	 * file.
	 */
	public void parserOccurrenceTypes() {
		if (!topicExists(tmclPrefix + "occurrence-type"))
			return;
		final String query = tmclPrefix + "occurrence-type" + "  >> instances ";

		try {
			SimpleResultSet set = execute(query);

			for (IResult result : set) {
				for (Object obj : result) {
					for (ITypesListener listener : listeners) {
						listener.occurrenceTypeElement((Topic) obj);
					}
				}
			}

		} catch (TMQLRuntimeException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Internal parse method to extract all role-types defined by the TMCL file.
	 */
	public void parserRoleTypes() {
		if (!topicExists(tmclPrefix + "role-type"))
			return;
		final String query = tmclPrefix + "role-type" + "  >> instances ";

		try {
			SimpleResultSet set = execute(query);

			for (IResult result : set) {
				for (Object obj : result) {
					for (ITypesListener listener : listeners) {
						listener.roleTypeElement((Topic) obj);
					}
				}
			}

		} catch (TMQLRuntimeException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Internal parse method to extract all association-types defined by the
	 * TMCL file.
	 */
	public void parserAssociationTypes() {
		if (!topicExists(tmclPrefix + "association-type"))
			return;
		final String query = tmclPrefix + "association-type"
				+ "  >> instances ";

		try {
			SimpleResultSet set = execute(query);

			for (IResult result : set) {
				for (Object obj : result) {
					for (ITypesListener listener : listeners) {
						listener.associationTypeElement((Topic) obj);
					}
				}
			}

		} catch (TMQLRuntimeException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Internal method to execute a TMQL query.
	 * 
	 * @param <T>
	 *            the result set type
	 * @param query
	 *            the query to execute
	 * @return the result set of the given query
	 * @throws TMQLRuntimeException
	 *             thrown if execution fails
	 */
	@SuppressWarnings("unchecked")
	private final <T extends IResultSet<?>> T execute(final String query)
			throws TMQLRuntimeException {
		ITMQLRuntime runtime = getRuntime();
		IQuery q = runtime.run(getTopicMap(), query);
		return (T) q.getResults();
	}
}
