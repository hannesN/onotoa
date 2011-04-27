/**
 * 
 */
package de.topicmapslab.tmcl.loader.util;

import de.topicmapslab.tmcl.loader.listener.IWorkMonitor;

/**
 * Moniutor which does nothing.
 * 
 * @author Hannes Niederhausen
 *
 */
public class NullWorkMonitor implements IWorkMonitor {
	
	private boolean started;
	
	@Override
	public void finished() {
		started = false;
	}

	@Override
	public void start(int numberOfSteps) {
		started = true;
	}

	@Override
	public boolean isStarted() {
		return started;
	}

	@Override
	public void stepDone() {
		// does nothing
	}

}
