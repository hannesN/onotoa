package de.topicmapslab.tmcl.loader.reader;

import de.topicmapslab.tmcl.loader.listener.IWorkMonitor;

/**
 * Interface definition of a TMCL reader
 * 
 * @author Sven Krosse
 * @email krosse@informatik.uni-leipzig.de
 * 
 */
public interface ITMCLReader {

	/**
	 * Parse method of all TMCL reader to extract the informations they
	 * interested in.
	 * 
	 * 
	 * @throws Exception
	 *             thrown if parsing fails
	 */
	public void parse(IWorkMonitor monitor) throws Exception;
}
