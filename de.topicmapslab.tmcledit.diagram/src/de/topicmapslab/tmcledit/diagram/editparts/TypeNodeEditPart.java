/*******************************************************************************
 * Copyright (c) 2008, 2009 Topic Maps Lab and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Hannes Niederhausen - initial API and implementation
 *******************************************************************************/
/**
 * 
 */
package de.topicmapslab.tmcledit.diagram.editparts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.MarginBorder;
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
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Display;

import de.topicmapslab.tmcledit.diagram.directedit.TMCLDirectEditManager;
import de.topicmapslab.tmcledit.diagram.figures.LineFigure;
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
	protected DirectEditManager editManager;
	
	private Label titleLabel;
	
	protected Figure identifierFigure;

	protected Figure compartmentFigure;
	
	private static Font identifierFont;
	private Font nameFont;
	private static int refCounter=0;
	
	private LineFigure firstLine;
	private LineFigure secondLine;
	
	@Override
	protected IFigure createFigure() {
		if (figure == null) {
			figure = new Figure() {
				@Override
				protected void paintFigure(Graphics graphics) {
					graphics.pushState();
					graphics.setBackgroundColor(ColorConstants.white);
					graphics.setForegroundColor(ColorConstants.yellow);
					graphics.fillGradient(getBounds(), true);
					graphics.popState();
				}
			};
			
			ToolbarLayout layout = new ToolbarLayout(false);
			layout.setStretchMinorAxis(true);
			layout.setSpacing(3);
			figure.setLayoutManager(layout);
			figure.setBorder(new LineBorder(ColorConstants.black, 1));
			
			titleLabel = new Label();
			titleLabel.setBorder(new MarginBorder(2, 5, 2, 5));
			titleLabel.setIcon(ImageProvider.getTopicTypeImage(getCastedModel().getTopicType()));
			figure.add(titleLabel);
			

			if (identifierFont==null) {
				Font tmp = Display.getCurrent().getSystemFont();
				FontData fd = tmp.getFontData()[0];
				fd.setHeight(7);
				identifierFont = new Font(Display.getCurrent(), fd);
			}
			
			identifierFigure = new Figure();
			identifierFigure.setBorder(new MarginBorder(2, 5, 2, 5));
			layout = new ToolbarLayout(false);
			layout.setStretchMinorAxis(false);
			layout.setSpacing(3);
			identifierFigure.setLayoutManager(layout);
			figure.add(identifierFigure);
			
			figure.setOpaque(false);
			figure.setBackgroundColor(ColorConstants.yellow);
			
			ScopedFigureLayoutManager manager = new ScopedFigureLayoutManager();
			compartmentFigure = new Figure();
			compartmentFigure.setLayoutManager(manager);
			figure.add(compartmentFigure);
			
			firstLine = new LineFigure();
			secondLine = new LineFigure();
			compartmentFigure.add(new LineFigure());
			compartmentFigure.add(firstLine);
			compartmentFigure.add(secondLine);
			
		}
		return figure;
	}
	
	private void createNameFont() {
		if (nameFont!=null) {
			nameFont.dispose();
		}
		Font tmp = Display.getCurrent().getSystemFont();
		FontData fd = tmp.getFontData()[0];
		if (getCastedModel().getTopicType().isAbstract())
			fd.setStyle(SWT.ITALIC);
		
		nameFont = new Font(Display.getCurrent(), fd);
		titleLabel.setFont(nameFont);
	}
	
	protected void fillIdentifierFigure() {
		identifierFigure.removeAll();
		
		for (String s : getCastedModel().getTopicType().getIdentifiers()) {
			Label l = new Label();
			l.setText(s);
			l.setFont(identifierFont);
			identifierFigure.add(l);
		}
		for (String s : getCastedModel().getTopicType().getLocators()) {
			Label l = new Label();
			l.setText("="+s);
			l.setFont(identifierFont);
			identifierFigure.add(l);
		}
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
			titleLabel.setText(tt.getName());
			
			createNameFont();
			fillIdentifierFigure();
			
		}
		Rectangle r = new Rectangle(tn.getPosX(), tn.getPosY(), -1, -1);
		try {
        ((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), r);
		} catch (NullPointerException e) {
			throw new RuntimeException(e);
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
		if (editManager == null) {
			editManager = new TMCLDirectEditManager(this, TextCellEditor.class, titleLabel);					
		}
		editManager.show();
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
		list.addAll(topicType.getOccurrenceConstraints());
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
		refCounter++;
	}
	
	@Override
	public void deactivate() {
		TypeNode tn = (TypeNode) getModel();
		tn.getTopicType().eAdapters().remove(this);
		refCounter--;
		if (refCounter==0) {
			identifierFont.dispose();
			identifierFont = null;
		}
		
		super.deactivate();
	}
	
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
		if (childEditPart instanceof OccurrenceTypeConstraintEditPart) {
			int i = compartmentFigure.getChildren().indexOf(secondLine);
			compartmentFigure.add(child, i);
		}
		else if (childEditPart instanceof NameTypeConstraintEditPart) {
			int i = compartmentFigure.getChildren().indexOf(firstLine);
			compartmentFigure.add(child, i);
		} else if ( (childEditPart instanceof SubjectLocatorConstraintEditPart) ||
				  ((childEditPart instanceof SubjectIdentifierConstraintEditPart)) )
			compartmentFigure.add(child);
		
	}
			
	@Override
	public IFigure getContentPane() {
		return compartmentFigure;
	}
	
}
