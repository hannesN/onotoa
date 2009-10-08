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

import org.eclipse.emf.common.util.EList;
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
import de.topicmapslab.tmcledit.domaindiagram.editparts.PrefixMappingEditPart;
import de.topicmapslab.tmcledit.domaindiagram.editparts.PrefixMappingElementEditPart;
import de.topicmapslab.tmcledit.domaindiagram.editparts.ReifierConstraintEditPart;
import de.topicmapslab.tmcledit.domaindiagram.editparts.ScopeConstraintEditPart;
import de.topicmapslab.tmcledit.domaindiagram.editparts.SubjectIdentifierConstraintEditPart;
import de.topicmapslab.tmcledit.domaindiagram.editparts.SubjectLocatorConstraintEditPart;
import de.topicmapslab.tmcledit.domaindiagram.editparts.TypeNodeEditPart;
import de.topicmapslab.tmcledit.model.AssociationNode;
import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.Comment;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.Edge;
import de.topicmapslab.tmcledit.model.EdgeType;
import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.LabelPos;
import de.topicmapslab.tmcledit.model.MappingElement;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.NameTypeConstraint;
import de.topicmapslab.tmcledit.model.OccurrenceTypeConstraint;
import de.topicmapslab.tmcledit.model.ReifierConstraint;
import de.topicmapslab.tmcledit.model.ScopeConstraint;
import de.topicmapslab.tmcledit.model.SubjectIdentifierConstraint;
import de.topicmapslab.tmcledit.model.SubjectLocatorConstraint;
import de.topicmapslab.tmcledit.model.TypeNode;
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

			@SuppressWarnings("unchecked")
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
				} else if (model instanceof EList) {
					part = new PrefixMappingEditPart();
				} else if (model instanceof MappingElement) {
					part = new PrefixMappingElementEditPart();
				} else if (model instanceof SubjectIdentifierConstraint) {
					part = new SubjectIdentifierConstraintEditPart();
				} else if (model instanceof SubjectLocatorConstraint) {
					part = new SubjectLocatorConstraintEditPart();
				} else if (model instanceof LabelPos) {
					part = new MoveableLabelEditPart();
				} else if (model instanceof Comment) {
					part = new CommentEditPart();
				} else if (model instanceof ScopeConstraint) {
					part = new ScopeConstraintEditPart();
				} else if (model instanceof ReifierConstraint) {
					part = new ReifierConstraintEditPart();
				}

				if (part != null)
					part.setModel(model);
				return part;
			}

		};
	}

	private static PaletteGroup getTypePaletteGroup() {
		PaletteGroup group = new PaletteGroup("Topic Types");

		group.add(new CombinedTemplateCreationEntry("Topic", "Topic",
				new TypeNodeCreationFactory(KindOfTopicType.NO_TYPE),
				ImageProvider.getImageDescriptor(ImageConstants.TOPIC_SM),
				ImageProvider.getImageDescriptor(ImageConstants.TOPIC)));

		group.add(new CombinedTemplateCreationEntry("Topic Type", "Topic Type",
				new TypeNodeCreationFactory(KindOfTopicType.TOPIC_TYPE),
				ImageProvider.getImageDescriptor(ImageConstants.TOPICTYPE_SM),
				ImageProvider.getImageDescriptor(ImageConstants.TOPICTYPE)));

		group
				.add(new CombinedTemplateCreationEntry(
						"Occurrence Type",
						"Occurrence Type",
						new TypeNodeCreationFactory(
								KindOfTopicType.OCCURRENCE_TYPE),
						ImageProvider
								.getImageDescriptor(ImageConstants.OCCURRENCETYPE_SM),
						ImageProvider
								.getImageDescriptor(ImageConstants.OCCURRENCETYPE)));

		group.add(new CombinedTemplateCreationEntry("Role Type", "Role Type",
				new TypeNodeCreationFactory(KindOfTopicType.ROLE_TYPE),
				ImageProvider.getImageDescriptor(ImageConstants.ROLETYPE_SM),
				ImageProvider.getImageDescriptor(ImageConstants.ROLETYPE)));

		group.add(new CombinedTemplateCreationEntry("Association Type", "Association Type",
				new TypeNodeCreationFactory(KindOfTopicType.ASSOCIATION_TYPE),
				ImageProvider
						.getImageDescriptor(ImageConstants.ASSOCIATIONTYPE_SM),
				ImageProvider
						.getImageDescriptor(ImageConstants.ASSOCIATIONTYPE)));

		group.add(new CombinedTemplateCreationEntry("Name Type", "Name Type",
				new TypeNodeCreationFactory(KindOfTopicType.NAME_TYPE),
				ImageProvider.getImageDescriptor(ImageConstants.NAMETYPE_SM),
				ImageProvider.getImageDescriptor(ImageConstants.NAMETYPE)));
		return group;
	}

	private static PaletteGroup getTypeItemsPaletteGroup() {
		PaletteGroup group = new PaletteGroup("Topic Types Items");
		group.add(new CombinedTemplateCreationEntry(
						"Occurrence Constraint",
						"Occurrence Constraint",
						new OccurrenceConstraintCreationFactory(),
						ImageProvider
								.getImageDescriptor(ImageConstants.OCCURRENCECONSTRAINT_SM),
						ImageProvider
								.getImageDescriptor(ImageConstants.OCCURRENCECONSTRAINT)));

		group.add(new CombinedTemplateCreationEntry(
						"Name Constraint",
						"Name Constraint",
						new CreationFactory() {

							public Object getNewObject() {
								return ModelFactory.eINSTANCE
										.createNameTypeConstraint();
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
				"Scope Constraint",
				"Scope Constraint",
				new CreationFactory() {

					public Object getNewObject() {
						return ModelFactory.eINSTANCE
								.createScopeConstraint();
					}

					public Object getObjectType() {
						return ScopeConstraint.class;
					}

				},
				null,
				null));
		
		group.add(new CombinedTemplateCreationEntry(
				"Reifier Constraint",
				"Reifier Constraint",
				new CreationFactory() {

					public Object getNewObject() {
						ReifierConstraint reifierConstraint = ModelFactory.eINSTANCE
								.createReifierConstraint();
						reifierConstraint.setCardMax("1");
						return reifierConstraint;
					}

					public Object getObjectType() {
						return ReifierConstraint.class;
					}

				},
				null,
				null));

		group.add(new CombinedTemplateCreationEntry(
						"Subject Identifier Constraint",
						"Subject Identifier Constraint",
						new CreationFactory() {

							public Object getNewObject() {
								return ModelFactory.eINSTANCE
										.createSubjectIdentifierConstraint();
							}

							public Object getObjectType() {
								return SubjectIdentifierConstraint.class;
							}

						},
						ImageProvider
								.getImageDescriptor(ImageConstants.SUBJECTIDENTIFIERCONSTRAINT),
						ImageProvider
								.getImageDescriptor(ImageConstants.SUBJECTIDENTIFIERCONSTRAINT)));

		group.add(new CombinedTemplateCreationEntry(
						"Subject Locator Constraint",
						"Subject Locator Constraint",
						new CreationFactory() {

							public Object getNewObject() {
								return ModelFactory.eINSTANCE
										.createSubjectLocatorConstraint();
							}

							public Object getObjectType() {
								return SubjectLocatorConstraint.class;
							}

						},
						ImageProvider
								.getImageDescriptor(ImageConstants.SUBJECTLOCATORCONSTRAINT),
						ImageProvider
								.getImageDescriptor(ImageConstants.SUBJECTLOCATORCONSTRAINT)));
		return group;
	}

	private static PaletteGroup getConnectionsPaletteGroup() {
		PaletteGroup group = new PaletteGroup("Connection Items");
		group.add(new ConnectionCreationToolEntry("Is A ...",
				"Create Is A connection", new CreationFactory() {

					public Object getNewObject() {
						Edge e = ModelFactory.eINSTANCE.createEdge();
						e.setType(EdgeType.IS_ATYPE);
						return e;
					}

					public Object getObjectType() {
						return Edge.class;
					}

				}, ImageProvider.getImageDescriptor(ImageConstants.ISA_SM),
				ImageProvider.getImageDescriptor(ImageConstants.ISA)));

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

		group
				.add(new CombinedTemplateCreationEntry(
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
