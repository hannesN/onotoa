/**
 * 
 */
package de.topicmapslab.tmcledit.diagram.editparts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.editpolicies.ContainerEditPolicy;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.TextCellEditor;

import de.topicmapslab.tmcledit.diagram.directedit.LabelCellEditorLocator;
import de.topicmapslab.tmcledit.diagram.directedit.TMCLDirectEditManager;
import de.topicmapslab.tmcledit.diagram.figures.CompartmentFigureBorder;
import de.topicmapslab.tmcledit.diagram.figures.CompartmentFigure;
import de.topicmapslab.tmcledit.diagram.policies.TopicTypeDirectEditPolicy;
import de.topicmapslab.tmcledit.diagram.policies.TypeContainerEditPolicy;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.TypeNode;

/**
 * @author Hannes Niederhausen
 * 
 */
public class TypeNodeEditPart extends AdapterGraphicalEditPart {

	private Label titleLabel;
	protected DirectEditManager manager;
	
	protected CompartmentFigure occurencesFigure; 
	protected CompartmentFigure basenameFigure;
	protected CompartmentFigure identifierFigure;
	
	@Override
	protected IFigure createFigure() {
		if (figure == null) {
			figure = new RectangleFigure();

			figure.setLayoutManager(new ToolbarLayout(false));
			titleLabel = new Label();
			figure.add(titleLabel);

			figure.setOpaque(true);
			figure.setBorder(new MarginBorder(10));
			figure.setBackgroundColor(ColorConstants.yellow);
			
			basenameFigure = new CompartmentFigure();
			figure.add(basenameFigure);			
			
			occurencesFigure = new CompartmentFigure();
			figure.add(occurencesFigure);
			
			identifierFigure = new CompartmentFigure();
			figure.add(identifierFigure);
			
		}
		return figure;
	}

	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new NonResizableEditPolicy());
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new TopicTypeDirectEditPolicy());
		installEditPolicy(EditPolicy.CONTAINER_ROLE, new TypeContainerEditPolicy());
	}
	
	@Override
	protected void refreshVisuals() {
		
		TypeNode tn = (TypeNode) getModel();
		if (titleLabel.isVisible()) {
			TopicType tt = (TopicType) tn.getType();
			titleLabel.setText(tt.getId());
		}
		Rectangle r = new Rectangle(tn.getPosX(), tn.getPosY(), -1, -1);
        ((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), r);

	}
	
	@Override
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
			manager = new TMCLDirectEditManager(this, TextCellEditor.class, titleLabel);					
		}
		manager.show();
	}

	private boolean directEditHitTest(Point requestLoc)
	{
		titleLabel.translateToRelative(requestLoc);
		if (titleLabel.containsPoint(requestLoc))
			return true;
		return false;
	}
	
	private TypeNode getCastedModel() {
		return (TypeNode) getModel();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected List getModelChildren() {
		List<EObject> list = new ArrayList<EObject>();
		list.addAll(getCastedModel().getType().getOccurenceConstraints());
		list.addAll(getCastedModel().getType().getNameContraints());
		list.addAll(getCastedModel().getType().getSubjectIdentifierConstraints());
		list.addAll(getCastedModel().getType().getSubjectLocatorConstraint());
		
		return list;
	}
	
	public void handleNameChange(String value) {
		titleLabel.setText(value);
		titleLabel.setVisible(false);
		refreshVisuals();
	}
	
	public void revertNameChange() {
		titleLabel.setVisible(true);
		refreshVisuals();
	}

	@Override
	public void activate() {
		super.activate();
		TypeNode tn = (TypeNode) getModel();
		tn.getType().eAdapters().add(this);
	}
	
	@Override
	public void deactivate() {
		TypeNode tn = (TypeNode) getModel();
		tn.getType().eAdapters().remove(this);
		super.deactivate();
	}
	
	@Override
	public void notifyChanged(Notification notification) {
		if (notification.getEventType() == Notification.SET) {
			refreshVisuals();
		}

	}

	/**
	 * adds the figure of the child editpart to the corresponding
	 * compartment figure
	 */
	@Override
	protected void addChildVisual(EditPart childEditPart, int index) {
		IFigure child = ((GraphicalEditPart)childEditPart).getFigure();
		if (childEditPart instanceof OccurenceTypeConstraintEditPart)
			occurencesFigure.add(child);
		else if (childEditPart instanceof NameTypeConstraintEditPart)
			basenameFigure.add(child);
		
	}
	
	@Override
	public IFigure getContentPane() {
		return occurencesFigure;
	}
	
}
