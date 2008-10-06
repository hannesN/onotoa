package de.topicmapslab.tmcledit.diagram.editparts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.jface.viewers.TextCellEditor;

import de.topicmapslab.tmcledit.diagram.directedit.TMCLDirectEditManager;
import de.topicmapslab.tmcledit.diagram.figures.EditableLabel;
import de.topicmapslab.tmcledit.diagram.figures.SelectionFigure;
import de.topicmapslab.tmcledit.diagram.policies.OccurenceConstraintDirectEditPolicy;
import de.topicmapslab.tmcledit.model.OccurenceTypeConstraint;
import de.topicmapslab.tmcledit.model.TopicType;

public class OccurenceTypeConstraintEditPart extends AdapterGraphicalEditPart {
	protected DirectEditManager manager;
	
	private EditableLabel nameLabel;
	private Label typeLabel;
	
	@Override
	protected IFigure createFigure() {

		figure = new SelectionFigure();
		
		figure.setLayoutManager(new ToolbarLayout(true));
		
		nameLabel = new EditableLabel("");
		figure.add(nameLabel);
		
		typeLabel = new Label();
		figure.add(typeLabel);
		
		return figure;
	}

	private OccurenceTypeConstraint getCastedModel() {
		return (OccurenceTypeConstraint) getModel();
	}
	
	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new OccurenceConstraintDirectEditPolicy());
	}
	
	@Override
	public void setSelected(int value) {
		super.setSelected(value);
		
		SelectionFigure f = (SelectionFigure) getFigure();
		if (value==EditPart.SELECTED_NONE) {
			f.setSelected(false);
		} else {
			f.setSelected(true);
		}
		
		
	}
	
	
	public void performRequest(Request req) {
		if (req.getType() == RequestConstants.REQ_DIRECT_EDIT) {
			if (req instanceof DirectEditRequest && !directEditHitTest(((DirectEditRequest) req).getLocation().getCopy()))
				return;
			performDirectEdit();
		}
		super.performRequest(req);
	}
	
	private void performDirectEdit() {
		if (manager == null) {
			manager = new TMCLDirectEditManager(this, TextCellEditor.class, nameLabel);					
		}
		manager.show();
	}

	private boolean directEditHitTest(Point requestLoc)
	{
		getFigure().translateToRelative(requestLoc);
		if (getFigure().containsPoint(requestLoc))
			return true;
		return false;
	}
	
	@Override
	protected void refreshVisuals() {
		OccurenceTypeConstraint otc = getCastedModel();
		StringBuffer text = new StringBuffer();
		
		nameLabel.setText(otc.getName());
		
		TopicType type = otc.getType();
		if (type!=null)
			text.append(":"+otc.getType().getId());
		
		text.append("\t"+otc.getCardMin()+".."+otc.getCardMax());	
		
		typeLabel.setText(text.toString());
	}

	public void revertNameChange() {
		figure.setVisible(true);
		refreshVisuals();
	}
	
	public void handleNameChange(String value) {
		nameLabel.setText(value);
		figure.setVisible(false);
		refreshVisuals();
	}
	
	@Override
	public void notifyChanged(Notification notification) {
		if (notification.getEventType()==Notification.SET)
			refreshVisuals();
		
	}

}
