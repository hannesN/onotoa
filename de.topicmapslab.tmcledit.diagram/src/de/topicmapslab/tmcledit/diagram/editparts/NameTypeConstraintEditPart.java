package de.topicmapslab.tmcledit.diagram.editparts;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.emf.common.notify.Notification;

import de.topicmapslab.tmcledit.model.NameTypeConstraint;

public class NameTypeConstraintEditPart extends AdapterGraphicalEditPart {

	Label nameLabel;
	Label cardLabel;
	Label typeLabel;
	
	@Override
	protected IFigure createFigure() {

		figure = new Figure();
		
		figure.setLayoutManager(new ToolbarLayout(true));

		nameLabel = new Label();
		figure.add(nameLabel);
		
		typeLabel = new Label();
		figure.add(typeLabel);
		
		cardLabel = new Label();
		figure.add(cardLabel);
		
		return figure;
	}

	private NameTypeConstraint getCastedModel() {
		return (NameTypeConstraint) getModel();
	}
	
	@Override
	protected void createEditPolicies() {
		// TODO Auto-generated method stub

	}
	
	@Override
	protected void refreshVisuals() {
		NameTypeConstraint ntc = getCastedModel();
		nameLabel.setText(ntc.getName());
		typeLabel.setText(":"+ntc.getType().getId());
		cardLabel.setText("  "+ntc.getCardMin()+".."+ntc.getCardMax());	
		
	}

	@Override
	public void notifyChanged(Notification notification) {
		if (notification.getEventType()==Notification.SET)
			refreshVisuals();
		
	}

}
