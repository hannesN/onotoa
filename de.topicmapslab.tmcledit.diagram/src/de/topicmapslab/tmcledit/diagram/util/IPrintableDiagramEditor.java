package de.topicmapslab.tmcledit.diagram.util;

import org.eclipse.draw2d.IFigure;

/**
 * Interface to get the printable figure of a diagram type
 * 
 * @author Hannes Niederhausen
 *
 */
public interface IPrintableDiagramEditor {

	/**
	 * Returns the figure representing the printable diagram parts.
	 * @return
	 */
	public abstract IFigure getPrintableFigure();

}