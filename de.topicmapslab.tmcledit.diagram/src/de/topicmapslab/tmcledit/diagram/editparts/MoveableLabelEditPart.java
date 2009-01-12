/**
 * 
 */
package de.topicmapslab.tmcledit.diagram.editparts;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionEndpointLocator;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;

import de.topicmapslab.tmcledit.model.LabelPos;

/**
 * @author Hannes Niederhausen
 *
 */
public class MoveableLabelEditPart extends AdapterGraphicalEditPart {

	private Label label;
	private String text;
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	 */
	@Override
	protected IFigure createFigure() {
		label = new Label();
		return label;
	}
	
	public void setText(String text) {
		this.text = text;
	}

	@Override
	protected void refreshVisuals() {
		label.setText(text);
		LabelPos lp = getCastesModel();
		
		ConnectionEndpointLocator locator = new ConnectionEndpointLocator((Connection) ((GraphicalEditPart) getParent()).getFigure(), true);
		locator.setUDistance(lp.getPosX());
		locator.setVDistance(lp.getPosY());
		try {
			((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), locator);
		} catch (NullPointerException e) {
			new RuntimeException(e);
		}
	}

	private LabelPos getCastesModel() {
		return (LabelPos) getModel();
	}
		
	@Override
	protected void createEditPolicies() {
	}

	@Override
	public void performRequest(Request req) {
		System.out.println("Edge:"+req);
		super.performRequest(req);
	}
	
	@Override
	public void notifyChanged(Notification notification) {
		refreshVisuals();		
	}

	
}
