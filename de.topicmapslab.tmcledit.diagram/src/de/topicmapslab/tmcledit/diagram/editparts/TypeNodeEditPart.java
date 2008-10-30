/**
 * 
 */
package de.topicmapslab.tmcledit.diagram.editparts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.jface.viewers.TextCellEditor;

import de.topicmapslab.tmcledit.diagram.directedit.TMCLDirectEditManager;
import de.topicmapslab.tmcledit.diagram.figures.CompartmentFigure;
import de.topicmapslab.tmcledit.diagram.policies.TopicTypeDirectEditPolicy;
import de.topicmapslab.tmcledit.diagram.policies.TypeContainerEditPolicy;
import de.topicmapslab.tmcledit.diagram.policies.TypeNodeLayoutEditPolicy;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.TypeNode;
import de.topicmapslab.tmcledit.model.util.ImageProvider;

/**
 * @author Hannes Niederhausen
 * 
 */
public class TypeNodeEditPart extends de.topicmapslab.tmcledit.diagram.editparts.NodeEditPart implements NodeEditPart {
	protected DirectEditManager manager;
	
	private Label titleLabel;

	protected CompartmentFigure occurencesFigure; 
	protected CompartmentFigure basenameFigure;
	protected CompartmentFigure identifierFigure;
	
	@Override
	protected IFigure createFigure() {
		if (figure == null) {
			figure = new Figure();
			
			ToolbarLayout layout = new ToolbarLayout(false);
			layout.setStretchMinorAxis(true);
			layout.setSpacing(3);
			figure.setLayoutManager(layout);
			figure.setBorder(new LineBorder(ColorConstants.black, 1));
			
			titleLabel = new Label();
			titleLabel.setIcon(ImageProvider.getTopicTypeImage(getCastedModel().getTopicType()));
			figure.add(titleLabel);

			figure.setOpaque(true);
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
		super.createEditPolicies();
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new TopicTypeDirectEditPolicy());
		installEditPolicy(EditPolicy.CONTAINER_ROLE, new TypeContainerEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new TypeNodeLayoutEditPolicy());
	}
	
	@Override
	protected void refreshVisuals() {
		
		TypeNode tn = (TypeNode) getModel();
		if (titleLabel.isVisible()) {
			TopicType tt = (TopicType) tn.getTopicType();
			titleLabel.setText(tt.getId());
		}
		Rectangle r = new Rectangle(tn.getPosX(), tn.getPosY(), -1, -1);
		try {
        ((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), r);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
        super.refreshVisuals();
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
	
	public TypeNode getCastedModel() {
		return (TypeNode) getModel();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected List getModelChildren() {
		List<EObject> list = new ArrayList<EObject>();
		TopicType topicType = getCastedModel().getTopicType();
		list.addAll(topicType.getOccurenceConstraints());
		list.addAll(topicType.getNameContraints());
		list.addAll(topicType.getSubjectIdentifierConstraints());
		list.addAll(topicType.getSubjectLocatorConstraint());
		
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
		tn.getTopicType().eAdapters().add(this);
	}
	
	@Override
	public void deactivate() {
		TypeNode tn = (TypeNode) getModel();
		tn.getTopicType().eAdapters().remove(this);
		super.deactivate();
	}
	
	@Override
	public void notifyChanged(Notification notification) {
		if (notification.getEventType() == Notification.REMOVING_ADAPTER)
			return;
		
		if (notification.getNotifier() instanceof Diagram) {
			if (notification.getEventType()==Notification.REMOVE)
				return;
			if (notification.getFeatureID(EList.class)==ModelPackage.DIAGRAM__EDGES) {
				refreshSourceConnections();
				refreshTargetConnections();
			}
		}
				
		if (notification.getNotifier().equals(((TypeNode)getModel()).getTopicType())) {
			refreshChildren();
		}
		refreshVisuals();
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
		else if ( (childEditPart instanceof SubjectLocatorConstraintEditPart) ||
				  ((childEditPart instanceof SubjectIdentifierConstraintEditPart)) )
			identifierFigure.add(child);
		
	}
	
	@Override
	protected void removeChildVisual(EditPart childEditPart) {
		IFigure child = ((GraphicalEditPart)childEditPart).getFigure();
		if (childEditPart instanceof OccurenceTypeConstraintEditPart)
			occurencesFigure.remove(child);
		else if (childEditPart instanceof NameTypeConstraintEditPart)
			basenameFigure.remove(child);
		else if ( (childEditPart instanceof SubjectLocatorConstraintEditPart) ||
				  ((childEditPart instanceof SubjectIdentifierConstraintEditPart)) )
			identifierFigure.remove(child);
	}
		
	@Override
	public IFigure getContentPane() {
		return occurencesFigure;
	}
	
}
