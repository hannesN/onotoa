package de.topicmapslab.tmcledit.diagram.editparts;

import org.eclipse.draw2d.AbstractBorder;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.EditPolicy;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;

import de.topicmapslab.tmcledit.diagram.policies.OccurenceConstraintDirectEditPolicy;
import de.topicmapslab.tmcledit.model.OccurenceType;
import de.topicmapslab.tmcledit.model.OccurenceTypeConstraint;
import de.topicmapslab.tmcledit.model.TopicType;

public class OccurenceTypeConstraintEditPart extends AbstractScopedLabeledEditPart {

	private static UnderlineLabelBorder border = new UnderlineLabelBorder();
	
	OccurenceTypeConstraint getCastedModel() {
		return (OccurenceTypeConstraint) getModel();
	}
	
	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new OccurenceConstraintDirectEditPolicy());
	}
		
	@Override
	public IFigure getFigure() {
		// TODO Auto-generated method stub
		return super.getFigure();
	}
	
	@Override
	protected void refreshVisuals() {
		OccurenceTypeConstraint otc = getCastedModel();
		StringBuffer buffer = new StringBuffer();
		
		if (otc.getType()!=null) {
			if (otc.isUnique())
				getNameLabel().setBorder(border);
			else
				getNameLabel().setBorder(null);
			getNameLabel().setText(otc.getType().getName());
		} else
			getNameLabel().setText("No Type Set");
		
		buffer.append(" : ");
		if (otc.getType() instanceof OccurenceType)
			buffer.append(((OccurenceType) otc.getType()).getDataType());
		else
			buffer.append("xsd:anyType");
		buffer.append(" ");
		buffer.append(otc.getCardMin());
		buffer.append("..");
		buffer.append(otc.getCardMax());	
		
		addScopeText(buffer);
		
		getTypeLabel().setText(buffer.toString());
	}

	@Override
	public void activate() {
		if (getCastedModel().getType()!=null)
			getCastedModel().getType().eAdapters().add(this);
		super.activate();
	}
	
	@Override
	public void deactivate() {
		if (getCastedModel().getType()!=null)
			getCastedModel().getType().eAdapters().remove(this);
		super.deactivate();

	}
	
	@Override
	public void notifyChanged(Notification notification) {
		super.notifyChanged(notification);
		if (notification.getEventType()==Notification.SET) {
			if (notification.getNewValue() instanceof TopicType) {
				TopicType old = (TopicType) notification.getOldValue();
				if (old!=null)
					old.eAdapters().remove(this);
				if (notification.getNewValue()!=null)
					((TopicType) notification.getNewValue()).eAdapters().add(this);
			
			}
		}
		refreshVisuals();
		
	}

	private static class UnderlineLabelBorder extends AbstractBorder {

		@Override
		public Insets getInsets(IFigure figure) {
			return new Insets(0, 0, 1, 0);
		}

		@Override
		public Dimension getPreferredSize(IFigure figure) {
			Dimension preferredSize = figure.getPreferredSize();
			return new Dimension(preferredSize.width, preferredSize.height+1);
		}

		@Override
		public void paint(IFigure figure, Graphics graphics, Insets insets) {
			Rectangle rec = figure.getBounds();
			int y = rec.y+rec.height-1;
			graphics.pushState();
			graphics.setForegroundColor(Display.getCurrent().getSystemColor(SWT.COLOR_BLACK));
			graphics.drawLine(rec.x, y, rec.x+rec.width, y);
			graphics.popState();
		}
		
	}
}
