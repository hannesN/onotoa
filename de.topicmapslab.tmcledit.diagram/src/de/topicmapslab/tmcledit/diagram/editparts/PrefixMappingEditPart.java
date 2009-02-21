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
package de.topicmapslab.tmcledit.diagram.editparts;

import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.MappingElement;

public class PrefixMappingEditPart extends AbstractGraphicalEditPart {

	private Figure compartment;
	
	private final Adapter adapter = new AdapterImpl() {
		@Override
		public void notifyChanged(Notification msg) {
			refreshChildren();
		}
	};

	@Override
	protected IFigure createFigure() {
		if (figure == null) {
			figure = new Figure();

			ToolbarLayout layout = new ToolbarLayout(false);
			layout.setStretchMinorAxis(false);
			layout.setSpacing(5);
			figure.setLayoutManager(layout);
			figure.setBorder(new LineBorder(ColorConstants.black, 1));
			figure.setMinimumSize(new Dimension(50, 20));
			Label l = new Label();
			l.setText("Prefixes:");
			
			compartment = new Figure();
			GridLayout gridLayout = new GridLayout(2, false);
			gridLayout.verticalSpacing = 1;
			compartment.setLayoutManager(gridLayout);
			
			figure.add(l);
			figure.add(compartment);
			
		}
		return figure;
	}

	@Override
	public IFigure getContentPane() {
		if (compartment==null)
			createFigure();
		return compartment;
	}
	
	@Override
	protected void addChildVisual(EditPart childEditPart, int index) {
		if (childEditPart instanceof PrefixMappingElementEditPart) {
			PrefixMappingElementEditPart ep = (PrefixMappingElementEditPart) childEditPart;
			getContentPane().add(ep.getKeyLabel());
			getContentPane().add(ep.getUriLabel());
		} else 
			super.addChildVisual(childEditPart, index);
	}
	
	@Override
	protected void removeChildVisual(EditPart childEditPart) {
		if (childEditPart instanceof PrefixMappingElementEditPart) {
			PrefixMappingElementEditPart ep = (PrefixMappingElementEditPart) childEditPart;
			getContentPane().remove(ep.getKeyLabel());
			getContentPane().remove(ep.getUriLabel());
		} else 
			super.removeChildVisual(childEditPart);
	}
	
	@Override
	protected void createEditPolicies() {
	}

	@SuppressWarnings("unchecked")
	EObjectContainmentEList<MappingElement> getCastedModel() {
		return (EObjectContainmentEList<MappingElement>) getModel();
	}

	@Override
	public void activate() {
		super.activate();
		getCastedModel().getEObject().eAdapters().add(adapter);
		((Diagram) getParent().getModel()).eAdapters().add(adapter);
	}

	@Override
	public void deactivate() {
		((Diagram) getParent().getModel()).eAdapters().remove(adapter);
		getCastedModel().getEObject().eAdapters().remove(adapter);
		super.deactivate();
	}

	@Override
	protected void refreshVisuals() {
		Rectangle r = ((GraphicalEditPart) getParent()).getFigure().getBounds()
				.getCopy();
		r.setSize(-1, -1);
		((GraphicalEditPart) getParent()).setLayoutConstraint(this,
				getFigure(), r);

	}

	@SuppressWarnings("unchecked")
	@Override
	protected List getModelChildren() {
		return (List) getModel();
	}

}
