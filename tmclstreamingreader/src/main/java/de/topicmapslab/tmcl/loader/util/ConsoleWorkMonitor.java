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
		System.out.println("Finished after "+currStep+" of "+numberOfSteps+" steps.");
	}

	@Override
	public void start(int numberOfSteps) {
		this.started = true;
		this.numberOfSteps = numberOfSteps;
		this.currStep = 0;
		System.out.println("Starting with steps: "+numberOfSteps);
	}

	@Override
	public boolean isStarted() {
		return started;
	}

	@Override
	public void stepDone() {
		currStep++;
		System.out.println("Step done: "+currStep);
	}

}
