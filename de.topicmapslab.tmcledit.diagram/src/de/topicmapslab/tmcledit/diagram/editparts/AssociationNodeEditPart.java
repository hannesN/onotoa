/**
 * 
 */
package de.topicmapslab.tmcledit.diagram.editparts;

import java.util.List;

import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

/**
 * @author Hannes Niederhausen
 *
 */
public class AssociationNodeEditPart extends AbstractGraphicalEditPart {

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	 */
	@Override
	protected IFigure createFigure() {
		Ellipse figure = new Ellipse();
		
		figure.setSize(80, 80);
		
		return figure;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 */
	@Override
	protected void createEditPolicies() {
	}

	@SuppressWarnings("unchecked")
	@Override
	protected List getModelSourceConnections() {
		// TODO Auto-generated method stub
		return super.getModelSourceConnections();
	}
}
