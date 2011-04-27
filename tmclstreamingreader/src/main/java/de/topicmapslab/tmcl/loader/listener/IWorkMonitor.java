/**
 * 
 */
package de.topicmapslab.tmcl.loader.listener;

/**
 * Interface to set communicate a current state of the import.
 * 
 * @author Hannes Niederhausen
 *
 */
public interface IWorkMonitor {

	/**
	 * Is called when the work is done
	 */
	public void finished();
	
	/**
	 * Starts a work and sets the number of steps until the work is done
	 */
	public void start(int numberOfSteps);
	
	/**
	 * Returns if {@link IWorkMonitor#start(int)} was already called.
	 * 
	 * @return <code>true</code> if start was already called, <code>false</code> else
	 */
	public boolean isStarted();
	
	/**
	 * Increases the internal step  counter
	 */
	public void stepDone();
				
}
