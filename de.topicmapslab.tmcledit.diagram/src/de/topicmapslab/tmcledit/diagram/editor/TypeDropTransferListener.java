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
package de.topicmapslab.tmcledit.diagram.editor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.Request;
import org.eclipse.gef.dnd.AbstractTransferDropTargetListener;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;

import de.topicmapslab.tmcledit.diagram.creationfactories.AssociationNodeCreationFactory;
import de.topicmapslab.tmcledit.diagram.creationfactories.TypeNodeCreationFactory;
import de.topicmapslab.tmcledit.diagram.editparts.DiagramEditPart;
import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;

public class TypeDropTransferListener extends
		AbstractTransferDropTargetListener {

	private TypeNodeCreationFactory nodeFac = new TypeNodeCreationFactory(true);
	// private OccurrenceConstraintCreationFactory occFac = new
	// OccurrenceConstraintCreationFactory();
	private final AssociationNodeCreationFactory assocConstrFac = new AssociationNodeCreationFactory();

	private List<TopicType> movedTypes = Collections.emptyList();
	private transient AssociationTypeConstraint atc;
	private transient boolean dropAllowed;

	private final TopicMapSchema schema;

	public TypeDropTransferListener(EditPartViewer viewer, Diagram diagram) {
		super(viewer);
		this.schema = ((File) diagram.eContainer()).getTopicMapSchema();
	}

	@Override
	public Transfer getTransfer() {
		return TextTransfer.getInstance();
	}

	@Override
	public void setCurrentEvent(DropTargetEvent currentEvent) {
		super.setCurrentEvent(currentEvent);
		if ((currentEvent == null) || (currentEvent.data == null)) {
			movedTypes = Collections.emptyList();
			return;
		}

		atc = null;
		for (AssociationTypeConstraint tmp : schema
				.getAssociationTypeConstraints()) {
			if (currentEvent.data.equals(tmp.toString())) {
				atc = tmp;
				assocConstrFac.setAssociationTypeConstraint(atc);
				return;
			}
		}

		String ids[] = ((String) currentEvent.data).split("--_--");
		movedTypes = new ArrayList<TopicType>(ids.length);
		for (String id : ids) {
			for (TopicType tt : schema.getTopicTypes()) {
				if (tt.toString().equals(id))
					movedTypes.add(tt);
			}
		}
	}

	@Override
	protected void updateTargetRequest() {
		CreateRequest req = ((CreateRequest) getTargetRequest());
		req.setLocation(getDropLocation());
		EditPart part = getViewer().findObjectAt(getDropLocation());
		if (part instanceof DiagramEditPart) {
			dropAllowed = true;

			if (atc != null) {
				req.setFactory(assocConstrFac);
			} else {
				req.setFactory(nodeFac);
			}
		} else {
			dropAllowed = false;
		}
	}

	@Override
	protected Request createTargetRequest() {
		CreateRequest req = new CreateRequest();
		return req;
	}

	@Override
	protected void handleDragOver() {
		getCurrentEvent().detail = DND.DROP_COPY;
		super.handleDragOver();
	}

	@Override
	protected void handleDrop() {
		CreateRequest req = ((CreateRequest) getTargetRequest());

		nodeFac.setTopicTypes(movedTypes);
		req.setFactory(nodeFac);

		if (!dropAllowed || ((movedTypes.isEmpty()) && (atc == null))) {
			getCurrentEvent().detail = DND.DROP_NONE;
			return;
		}
		super.handleDrop();
	}

}
