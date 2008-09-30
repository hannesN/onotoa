/**
 * 
 */
package de.topicmapslab.tmcledit.diagram.directedit;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Text;

/**
 * @author mai00ckx
 *
 */
public class TMCLDirectEditManager extends DirectEditManager {

	private String originalValue;
	private Label label;
	private Font figureFont;

	public TMCLDirectEditManager(GraphicalEditPart source, Class editorType,
			Label label) {
		super(source, editorType, new LabelCellEditorLocator(label));
		this.label = label;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.tools.DirectEditManager#initCellEditor()
	 */
	@Override
	protected void initCellEditor() {
		Text text = (Text) getCellEditor().getControl();

		
		//set the initial value of the
		originalValue = this.label.getText();
		getCellEditor().setValue(originalValue);

		//calculate the font size of the underlying
		IFigure figure = ((GraphicalEditPart) getEditPart()).getFigure();
		figureFont = figure.getFont();
		FontData data = figureFont.getFontData()[0];
		Dimension fontSize = new Dimension(0, data.getHeight());

		//set the font to be used
		this.label.translateToAbsolute(fontSize);
		data.setHeight(fontSize.height);
		figureFont = new Font(null, data);

		text.setFont(figureFont);
		text.selectAll();
	}

	
}
