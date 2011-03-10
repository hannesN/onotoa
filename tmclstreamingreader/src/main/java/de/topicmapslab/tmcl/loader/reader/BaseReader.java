package de.topicmapslab.tmcl.loader.reader;

import java.io.File;

import org.tmapi.core.Locator;
import org.tmapi.core.TopicMap;
import org.tmapi.core.TopicMapSystem;
import org.tmapi.core.TopicMapSystemFactory;
import org.tmapi.index.LiteralIndex;
import org.tmapi.index.ScopedIndex;
import org.tmapi.index.TypeInstanceIndex;
import org.tmapix.io.CTMTopicMapReader;
import org.tmapix.io.XTMTopicMapReader;

import de.topicmapslab.tmql4j.components.processor.runtime.ITMQLRuntime;
import de.topicmapslab.tmql4j.components.processor.runtime.TMQLRuntimeFactory;
import de.topicmapslab.tmql4j.exception.TMQLRuntimeException;
import de.topicmapslab.tmql4j.path.components.processor.runtime.TmqlRuntime2007;

/**
 * Abstract implementation of an {@link ITMCLReader}.
 * 
 * @author Sven Krosse
 * @email krosse@informatik.uni-leipzig.de
 * 
 */
public abstract class BaseReader implements ITMCLReader {

	/**
	 * the base-locator of TMCL types
	 */
	protected static final String tmclPrefix = "http://psi.topicmaps.org/tmcl/";
	/**
	 * the internal topic map representation of the CTM or XTM file
	 */
	private final TopicMap topicMap;
	/**
	 * the topic map system
	 */
	private final TopicMapSystem system;
	

	/**
	 * Base constructor to create a new instance of the base reader by file.
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
	public BaseReader(File file) throws Exception {
		this(file, TopicMapSystemFactory.newInstance());
	}
	
	/**
	 * Base constructor to create a new instance of the base reader by file.
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
	public BaseReader(File file, TopicMapSystemFactory topicMapSystemFactory) throws Exception {
		final String baseLocator = "http://tmcl.topicmapslab.de/temp";
		
		system = topicMapSystemFactory.newTopicMapSystem();
		topicMap = system.createTopicMap(baseLocator);

		/*
		 * load topic map from file
		 */
		if (file.getName().endsWith(".ctm")) {
			CTMTopicMapReader reader = new CTMTopicMapReader(topicMap, file);
			reader.read();
		} else if (file.getName().endsWith(".xtm")
				|| file.getName().endsWith(".xtm20")) {
			XTMTopicMapReader reader = new XTMTopicMapReader(topicMap, file);
			reader.read();
		}
		
		topicMap.getIndex(TypeInstanceIndex.class).open();
		topicMap.getIndex(ScopedIndex.class).open();
		topicMap.getIndex(LiteralIndex.class).open();
	}
	
	/**
	 * Basic Reader which requires a topic map and a topic map system
	 * @param tm the topic map which contains the schema
	 * @param tms a topic map system which can create new topic maps
	 * @throws TMQLRuntimeException
	 */
	public BaseReader(TopicMap tm, TopicMapSystem tms) throws TMQLRuntimeException {
		system = tms;
		topicMap = tm;
		topicMap.getIndex(TypeInstanceIndex.class).open();
		topicMap.getIndex(ScopedIndex.class).open();
		topicMap.getIndex(LiteralIndex.class).open();
	}
	
	/**
	 * Get access to the internal representation of the topic map given in
	 * Compact Topic Maps Notation or in XML Topic Maps Notation
	 * 
	 * @return the reference
	 */
	protected TopicMap getTopicMap() {
		return topicMap;
	}

	/**
	 * Get access to the internal topic map system reference
	 * 
	 * @return the reference
	 */
	protected TopicMapSystem getTopicMapSystem() {
		return system;
	}

	/**
	 * Get access to the created TMQL4J runtime
	 * 
	 * @return the reference
	 * @throws TMQLRuntimeException 
	 */
	protected ITMQLRuntime getRuntime() throws TMQLRuntimeException {
		ITMQLRuntime runtime = TMQLRuntimeFactory.newFactory().newRuntime(
				getTopicMapSystem(), TmqlRuntime2007.TMQL_2007);
		
		return runtime;
	}
	
	/**
	 * Checks if a topic with the given subject identifier exists in the topic map
	 * @param subjectIdentifier 
	 * @return
	 */
	protected boolean topicExists(String subjectIdentifier) {
		Locator l = topicMap.createLocator(subjectIdentifier);
		return topicMap.getTopicBySubjectIdentifier(l)!=null;
	}
}
