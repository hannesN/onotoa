package de.topicmapslab.onotoa.instanceeditor.test.provider;

import org.tmapi.core.TopicMap;

import de.topicmapslab.onotoa.instanceeditor.service.ITopicMapProvider;
import de.topicmapslab.tmql4j.components.processor.prepared.IPreparedStatement;
import de.topicmapslab.tmql4j.components.processor.results.model.IResult;
import de.topicmapslab.tmql4j.components.processor.results.model.IResultSet;
import de.topicmapslab.tmql4j.components.processor.runtime.ITMQLRuntime;
import de.topicmapslab.tmql4j.components.processor.runtime.TMQLRuntimeFactory;
import de.topicmapslab.tmql4j.path.components.processor.runtime.TmqlRuntime2007;

/**
 * test {@link ITopicMapProvider} implementation
 * @author Christian Haß
 *
 */
public class MyTopicMapProvider implements ITopicMapProvider {

	private String baseLocator;
	private TopicMap topicMap;
	private ITMQLRuntime runtime;
		
	/**
	 * constructor
	 * @param map - the topic map
	 * @param baseLocator - the base locator
	 */
	public MyTopicMapProvider(TopicMap map, String baseLocator) {
		this.baseLocator = baseLocator;
		this.topicMap = map;
		this.runtime = TMQLRuntimeFactory.newFactory().newRuntime(TmqlRuntime2007.TMQL_2007);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getTopicMapBaseLocator() {
		return this.baseLocator;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IPreparedStatement createPreparedStatement(String statement) {
		return this.runtime.preparedStatement(statement);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public IResultSet<IResult> executePrepatedStement(IPreparedStatement statement) {
		
		statement.setTopicMap(this.topicMap);
		statement.run();
		IResultSet<?> results = statement.getResults();
		statement.setTopicMap(null);

		return (IResultSet<IResult>)results;

	}
	
	
	

}
