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
public class ConsoleWorkMonitor implements IWorkMonitor {
	
	private int currStep;
	private boolean started;
	private int numberOfSteps;
	
	@Override
	public void finished() {
		started = false;
	}

	@Override
	public void start(int numberOfSteps) {
		this.started = true;
		this.numberOfSteps = numberOfSteps;
		this.currStep = 0;
	}

	@Override
	public boolean isStarted() {
		return started;
	}

	@Override
	public void stepDone() {
		currStep++;
	}

	/**
	 * @return the numberOfSteps
	 */
	public int getNumberOfSteps() {
		return numberOfSteps;
	}
}
