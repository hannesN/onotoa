package de.topicmapslab.tmcledit.diagram.editparts;

import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import de.topicmapslab.tmcledit.model.Diagram;

public class PrefixMappingEditPart extends AbstractGraphicalEditPart {

	private final Adapter adapter = new AdapterImpl() {
		@Override
		public void notifyChanged(Notification msg) {
		
		}
	};
	
	@Override
	protected IFigure createFigure() {
		if (figure == null) {
			figure = new Figure();
			
			ToolbarLayout layout = new ToolbarLayout(false);
			layout.setStretchMinorAxis(true);
			layout.setSpacing(5);
			figure.setLayoutManager(layout);
			figure.setBorder(new LineBorder(ColorConstants.black, 1));
		}
		return figure;
	}

	@Override
	protected void createEditPolicies() {
	}
	
	@SuppressWarnings("unchecked")
	EList getCastedModel() {
		return (EList) getModel();
	}
	
	@Override
	public void activate() {
		super.activate();
		((Diagram)getParent().getModel()).eAdapters().add(adapter);
	}
	
	@Override
	public void deactivate() {
		((Diagram)getParent().getModel()).eAdapters().add(adapter);
		super.deactivate();
	}
	
	@Override
	protected void refreshVisuals() {
		Rectangle r = ((GraphicalEditPart)getParent()).getFigure().getBounds().getCopy();
		r.setSize(-1, -1);
        ((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), r);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected List getModelChildren() {
		return (List) getModel();
	}

}
