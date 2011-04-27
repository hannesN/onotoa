/**
 * 
 */
package de.topicmapslab.onotoa.genny.generator.ui;

import org.eclipse.swt.widgets.Display;

/**
 * The listener is used to set text from a non ui thread to a ui thread. Any implementation should
 * be aware that the call could be from an non ui thread and there for changes in the UI should be done
 * in a {@link Display#asyncExec(Runnable)} call.
 * 
 * @author Hannes Niederhausen
 *
 */
public interface ITextListener {

	/**
	 * Sets the new text which should be visualized.
	 * @param text
	 */
	public void newText(String text);
	
	/**
	 * Sets the new text which should be visualized.
	 * @param text
	 * @param forceShow falg whether the textfield should be made visible if necessary
	 */
	public void newText(String text, boolean forceShow);
}
