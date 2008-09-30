/**
 * 
 */
package de.topicmapslab.tmcledit.diagram.directedit;

import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Text;

/**
 * This locator sets the cell editor to the position of the given Label.
 * 
 * @author Hannes Niederhausen
 *
 */
public class LabelCellEditorLocator implements CellEditorLocator {

	private final Label label;
	
	public LabelCellEditorLocator(Label label) {
		this.label = label;
	}
	
	@Override
	public void relocate(CellEditor celleditor) {
		Text text = (Text) celleditor.getControl();

		Point pref = text.computeSize(SWT.DEFAULT, SWT.DEFAULT);
		Rectangle rect = label.getTextBounds().getCopy();
		label.translateToAbsolute(rect);
		if (text.getCharCount() > 1)
			text.setBounds(rect.x - 1, rect.y - 1, pref.x + 1, pref.y + 1);
		else
			text.setBounds(rect.x - 1, rect.y - 1, pref.y + 1, pref.y + 1);

	}

}
