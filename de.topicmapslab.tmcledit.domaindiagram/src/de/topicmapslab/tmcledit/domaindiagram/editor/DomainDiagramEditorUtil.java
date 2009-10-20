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
package de.topicmapslab.tmcledit.domaindiagram.editor;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.ConnectionCreationToolEntry;
import org.eclipse.gef.palette.MarqueeToolEntry;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.PaletteSeparator;
import org.eclipse.gef.palette.SelectionToolEntry;
import org.eclipse.gef.requests.CreationFactory;

import de.topicmapslab.tmcledit.domaindiagram.creationfactories.OccurrenceConstraintCreationFactory;
import de.topicmapslab.tmcledit.domaindiagram.creationfactories.TypeNodeCreationFactory;
import de.topicmapslab.tmcledit.domaindiagram.editparts.AssociationNodeEditPart;
import de.topicmapslab.tmcledit.domaindiagram.editparts.CommentEditPart;
import de.topicmapslab.tmcledit.domaindiagram.editparts.DiagramEditPart;
import de.topicmapslab.tmcledit.domaindiagram.editparts.EdgeEditPart;
import de.topicmapslab.tmcledit.domaindiagram.editparts.MoveableLabelEditPart;
import de.topicmapslab.tmcledit.domaindiagram.editparts.NameTypeConstraintEditPart;
import de.topicmapslab.tmcledit.domaindiagram.editparts.OccurrenceTypeConstraintEditPart;
import de.topicmapslab.tmcledit.domaindiagram.editparts.TopicRoleEditPart;
import de.topicmapslab.tmcledit.domaindiagram.editparts.TypeNodeEditPart;
import de.topicmapslab.tmcledit.model.AssociationNode;
import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.Comment;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.Edge;
import de.topicmapslab.tmcledit.model.EdgeType;
import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.LabelPos;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.NameTypeConstraint;
import de.topicmapslab.tmcledit.model.OccurrenceTypeConstraint;
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.TypeNode;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;
import de.topicmapslab.tmcledit.model.util.ImageConstants;
import de.topicmapslab.tmcledit.model.util.ImageProvider;

/**
 * @author Hannes Niederhausen
 * 
 */
public class DomainDiagramEditorUtil {

	public static final PaletteRoot getPaletteRoot() {
		PaletteRoot pr = new PaletteRoot();
		PaletteGroup group = new PaletteGroup("Tools");
		SelectionToolEntry selEntry = new SelectionToolEntry();
		group.add(selEntry);
		group.add(new MarqueeToolEntry());
		pr.add(group);
		pr.setDefaultEntry(selEntry);

		group.add(new CombinedTemplateCreationEntry("Comment", "Comment",
				new CreationFactory() {

					public Object getNewObject() {
						Comment c = ModelFactory.eINSTANCE.createComment();
						c.setWidth(50);
						c.setHeight(40);
						return c;
					}

					public Object getObjectType() {
						return Comment.class;
					}

				}, ImageProvider.getImageDescriptor(ImageConstants.COMMENT),
				ImageProvider.getImageDescriptor(ImageConstants.COMMENT)));

		pr.add(new PaletteSeparator("selection.seperator"));
		pr.add(getTypePaletteGroup());
		pr.add(new PaletteSeparator("type.seperator"));
		pr.add(getTypeItemsPaletteGroup());
		pr.add(getConnectionsPaletteGroup());

		return pr;
	}

	public static EditPartFactory getEditPartFactory() {
		return new EditPartFactory() {

			public EditPart createEditPart(EditPart context, Object model) {
				EditPart part = null;
				if (model instanceof Diagram) {
					part = new DiagramEditPart();
				} else if (model instanceof TypeNode) {
					part = new TypeNodeEditPart();
				} else if (model instanceof AssociationNode) {
					part = new AssociationNodeEditPart();
				} else if (model instanceof OccurrenceTypeConstraint) {
					part = new OccurrenceTypeConstraintEditPart();
				} else if (model instanceof NameTypeConstraint) {
					part = new NameTypeConstraintEditPart();
				} else if (model instanceof Edge) {
					part = new EdgeEditPart();
				} else if (model instanceof LabelPos) {
					part = new MoveableLabelEditPart();
				} else if (model instanceof Comment) {
					part = new CommentEditPart();
				} else if (model instanceof RolePlayerConstraint) {
					part = new TopicRoleEditPart();
				}

				if (part != null)
					part.setModel(model);
				return part;
			}

		};
	}

	private static PaletteGroup getTypePaletteGroup() {
		PaletteGroup group = new PaletteGroup("Topic");

		group.add(new CombinedTemplateCreationEntry("Topic", "Topic",
				new TypeNodeCreationFactory(KindOfTopicType.TOPIC_TYPE),
				ImageProvider.getImageDescriptor(ImageConstants.TOPIC),
				ImageProvider.getImageDescriptor(ImageConstants.TOPIC_SM)));
		return group;
	}

	private static PaletteGroup getTypeItemsPaletteGroup() {
		PaletteGroup group = new PaletteGroup("Topic Items");
		group.add(new CombinedTemplateCreationEntry(
						"Name",
						"Name",
						new CreationFactory() {

							public Object getNewObject() {
								NameTypeConstraint ntc = ModelFactory.eINSTANCE
										.createNameTypeConstraint();
								
								int i=0;
								String n = "name";
								TopicType tt = null; 
								while ( (tt=ModelIndexer.getTopicIndexer().getTopicTypeByName(n+i)) != null) {
									i++;
								}
								tt = ModelFactory.eINSTANCE.createNameType();
								tt.setName(n+i);
								
								ntc.setType(tt);
								
								
								return ntc;
							}

							public Object getObjectType() {
								return NameTypeConstraint.class;
							}

						},
						ImageProvider
								.getImageDescriptor(ImageConstants.NAMECONSTRAINT_SM),
						ImageProvider
								.getImageDescriptor(ImageConstants.NAMECONSTRAINT)));
		
		group.add(new CombinedTemplateCreationEntry(
				"Occurrence",
				"Occurrence",
				new OccurrenceConstraintCreationFactory(),
				ImageProvider
						.getImageDescriptor(ImageConstants.OCCURRENCECONSTRAINT_SM),
				ImageProvider
						.getImageDescriptor(ImageConstants.OCCURRENCECONSTRAINT)));
		
		return group;
	}

	private static PaletteGroup getConnectionsPaletteGroup() {
		PaletteGroup group = new PaletteGroup("Connection Items");
		
		group.add(new ConnectionCreationToolEntry("Kind Of ...",
				"Create kind of connection", new CreationFactory() {

					public Object getNewObject() {
						Edge e = ModelFactory.eINSTANCE.createEdge();
						e.setType(EdgeType.AKO_TYPE);
						return e;
					}

					public Object getObjectType() {
						return Edge.class;
					}

				}, ImageProvider.getImageDescriptor(ImageConstants.KINDOF_SM),
				ImageProvider.getImageDescriptor(ImageConstants.KINDOF)));

		group.add(new CombinedTemplateCreationEntry(
						"Association Constraint",
						"Association Constraint",
						new CreationFactory() {

							public Object getNewObject() {
								AssociationNode node = ModelFactory.eINSTANCE
										.createAssociationNode();
								AssociationTypeConstraint ac = ModelFactory.eINSTANCE
										.createAssociationTypeConstraint();
								node.setAssociationConstraint(ac);
								return node;
							}

							public Object getObjectType() {
								return AssociationNode.class;
							}

						},
						ImageProvider
								.getImageDescriptor(ImageConstants.ASSOCIATIONCONSTRAINT_SM),
						ImageProvider
								.getImageDescriptor(ImageConstants.ASSOCIATIONCONSTRAINT)));

		group.add(new ConnectionCreationToolEntry("Topic Role Constraint",
				"Create Topic Role Constraint connection",
				new CreationFactory() {

					public Object getNewObject() {
						Edge e = ModelFactory.eINSTANCE.createEdge();
						e.setRoleConstraint(ModelFactory.eINSTANCE
								.createRolePlayerConstraint());
						e.setType(EdgeType.ROLE_CONSTRAINT_TYPE);
						return e;
					}

					public Object getObjectType() {
						return Edge.class;
					}

				}, ImageProvider
						.getImageDescriptor(ImageConstants.TOPICROLE_SM),
				ImageProvider.getImageDescriptor(ImageConstants.TOPICROLE)));

		return group;
	}

}
