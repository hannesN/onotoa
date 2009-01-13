/**
 * 
 */
package de.topicmapslab.tmcledit.diagram.editparts;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.tools.DragEditPartsTracker;

import de.topicmapslab.tmcledit.diagram.figures.MovableConnectionLocator;
import de.topicmapslab.tmcledit.model.LabelPos;

/**
 * @author Hannes Niederhausen
 * 
 */
public class MoveableLabelEditPart extends AdapterGraphicalEditPart {

	private Label label;
	private String text;

	private MovableConnectionLocator locator;
	private DragTracker dragTracker;

	/*
	 * (non-Javadoc)
	 * 
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

		MovableConnectionLocator locator = getLocator();
		locator.setLabelPos(lp);
		try {
			((GraphicalEditPart) getParent()).setLayoutConstraint(this,
					getFigure(), locator);
		} catch (NullPointerException e) {
			new RuntimeException(e);
		}
	}
	

	private LabelPos getCastesModel() {
		return (LabelPos) getModel();
	}

	@Override
	public DragTracker getDragTracker(Request request) {
		return getDragTracker();
	}

	private DragTracker getDragTracker() {
		if (dragTracker == null) {
			dragTracker = new DragEditPartsTracker(this) {
				@Override
				protected boolean isMove() {
					return true;
				}
			};
		}

		return dragTracker;
	}
	
	public MovableConnectionLocator getLocator() {
		if (locator == null) {
			locator = new MovableConnectionLocator(
					(Connection) ((GraphicalEditPart) getParent()).getFigure(),
					true);
			
			if  ( (getCastesModel().getPosX()==0) &&
				  (getCastesModel().getPosY()==0) ){
				locator.setVDistance(15);			
			
				if (getParent().getChildren().indexOf(this)==0)
					locator.setUDistance(15);
				else
					locator.setUDistance(35);
			}
			
			locator.setLabelPos(getCastesModel());
		}
		return locator;
	}

	@Override
	protected void createEditPolicies() {
	}

	@Override
	public void notifyChanged(Notification notification) {
		refreshVisuals();
	}
}
