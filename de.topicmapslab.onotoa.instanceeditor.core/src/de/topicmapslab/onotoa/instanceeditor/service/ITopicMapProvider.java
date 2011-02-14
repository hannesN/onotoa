package de.topicmapslab.onotoa.instanceeditor.service;

import de.topicmapslab.tmql4j.components.processor.prepared.IPreparedStatement;
import de.topicmapslab.tmql4j.components.processor.results.IResult;
import de.topicmapslab.tmql4j.components.processor.results.IResultSet;

public interface ITopicMapProvider {

	public String getTopicMapBaseLocator();
	public IPreparedStatement createPreparedStatement(String statement);
	public IResultSet<IResult> executePrepatedStement(IPreparedStatement statement);
	
}
