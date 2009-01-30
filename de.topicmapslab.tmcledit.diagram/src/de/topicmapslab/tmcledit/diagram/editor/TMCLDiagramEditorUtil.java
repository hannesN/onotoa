/**
 * 
 */
package de.topicmapslab.tmcledit.diagram.editor;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.palette.ConnectionCreationToolEntry;
import org.eclipse.gef.palette.CreationToolEntry;
import org.eclipse.gef.palette.MarqueeToolEntry;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.PaletteSeparator;
import org.eclipse.gef.palette.SelectionToolEntry;
import org.eclipse.gef.requests.CreationFactory;

import de.topicmapslab.tmcledit.diagram.creationfactories.OccurenceConstraintCreationFactory;
import de.topicmapslab.tmcledit.diagram.creationfactories.TypeNodeCreationFactory;
import de.topicmapslab.tmcledit.diagram.editparts.AssociationNodeEditPart;
import de.topicmapslab.tmcledit.diagram.editparts.DiagramEditPart;
import de.topicmapslab.tmcledit.diagram.editparts.EdgeEditPart;
import de.topicmapslab.tmcledit.diagram.editparts.MoveableLabelEditPart;
import de.topicmapslab.tmcledit.diagram.editparts.NameTypeConstraintEditPart;
import de.topicmapslab.tmcledit.diagram.editparts.OccurenceTypeConstraintEditPart;
import de.topicmapslab.tmcledit.diagram.editparts.PrefixMappingEditPart;
import de.topicmapslab.tmcledit.diagram.editparts.PrefixMappingElementEditPart;
import de.topicmapslab.tmcledit.diagram.editparts.SubjectIdentifierConstraintEditPart;
import de.topicmapslab.tmcledit.diagram.editparts.SubjectLocatorConstraintEditPart;
import de.topicmapslab.tmcledit.diagram.editparts.TypeNodeEditPart;
import de.topicmapslab.tmcledit.model.AssociationNode;
import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.Edge;
import de.topicmapslab.tmcledit.model.EdgeType;
import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.LabelPos;
import de.topicmapslab.tmcledit.model.MappingElement;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.NameTypeConstraint;
import de.topicmapslab.tmcledit.model.OccurenceTypeConstraint;
import de.topicmapslab.tmcledit.model.SubjectIdentifierConstraint;
import de.topicmapslab.tmcledit.model.SubjectLocatorConstraint;
import de.topicmapslab.tmcledit.model.TypeNode;
import de.topicmapslab.tmcledit.model.util.ImageConstants;
import de.topicmapslab.tmcledit.model.util.ImageProvider;

/**
 * @author Hannes Niederhausen
 * 
 */
public class TMCLDiagramEditorUtil {

	public static final PaletteRoot getPaletteRoot() {
		PaletteRoot pr = new PaletteRoot();
		PaletteGroup group = new PaletteGroup("Tools");
		SelectionToolEntry selEntry = new SelectionToolEntry();
		group.add(selEntry);
		group.add(new MarqueeToolEntry());
		pr.add(group);
		pr.setDefaultEntry(selEntry);

		pr.add(new PaletteSeparator("selection.seperator"));
		pr.add(getTypePaletteGroup());
		pr.add(new PaletteSeparator("type.seperator"));
		pr.add(getTypeItemsPaletteGroup());
		pr.add(getConnectionsPaletteGroup());

		return pr;
	}

	public static EditPartFactory getEditPartFactory() {
		return new EditPartFactory() {

			@Override
			public EditPart createEditPart(EditPart context, Object model) {
				EditPart part = null;
				if (model instanceof Diagram) {
					part = new DiagramEditPart();
				} else if (model instanceof TypeNode) {
					part = new TypeNodeEditPart();
				} else if (model instanceof AssociationNode) {
					part = new AssociationNodeEditPart();
				} else if (model instanceof OccurenceTypeConstraint) {
					part = new OccurenceTypeConstraintEditPart();
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
				}

				if (part != null)
					part.setModel(model);
				return part;
			}

		};
	}

	private static PaletteGroup getTypePaletteGroup() {
		PaletteGroup group = new PaletteGroup("Topic Types");

		group.add(new CreationToolEntry("Topic", "Topic",
				new TypeNodeCreationFactory(KindOfTopicType.NO_TYPE),
				ImageProvider.getImageDescriptor(ImageConstants.TOPIC_SM),
				ImageProvider.getImageDescriptor(ImageConstants.TOPIC)));
		
		group.add(new CreationToolEntry("Topic Type", "Topic Type",
				new TypeNodeCreationFactory(KindOfTopicType.TOPIC_TYPE),
				ImageProvider.getImageDescriptor(ImageConstants.TOPICTYPE_SM),
				ImageProvider.getImageDescriptor(ImageConstants.TOPICTYPE)));

		group
				.add(new CreationToolEntry(
						"Occurence Type",
						"Occurence Type",
						new TypeNodeCreationFactory(
								KindOfTopicType.OCCURENCE_TYPE),
						ImageProvider
								.getImageDescriptor(ImageConstants.OCCURENCETYPE_SM),
						ImageProvider
								.getImageDescriptor(ImageConstants.OCCURENCETYPE)));

		group.add(new CreationToolEntry("Role Type", "Role Type",
				new TypeNodeCreationFactory(KindOfTopicType.ROLE_TYPE),
				ImageProvider.getImageDescriptor(ImageConstants.ROLETYPE_SM),
				ImageProvider.getImageDescriptor(ImageConstants.ROLETYPE)));

		group.add(new CreationToolEntry("Association Type", "Association Type",
				new TypeNodeCreationFactory(KindOfTopicType.ASSOCIATION_TYPE),
				ImageProvider
						.getImageDescriptor(ImageConstants.ASSOCIATIONTYPE_SM),
				ImageProvider
						.getImageDescriptor(ImageConstants.ASSOCIATIONTYPE)));

		group.add(new CreationToolEntry("Name Type", "Name Type",
				new TypeNodeCreationFactory(KindOfTopicType.NAME_TYPE),
				ImageProvider.getImageDescriptor(ImageConstants.NAMETYPE_SM),
				ImageProvider.getImageDescriptor(ImageConstants.NAMETYPE)));

		group.add(new CreationToolEntry("Scope Type", "Scope Type",
				new TypeNodeCreationFactory(KindOfTopicType.SCOPE_TYPE),
				ImageProvider.getImageDescriptor(ImageConstants.SCOPETYPE_SM),
				ImageProvider.getImageDescriptor(ImageConstants.SCOPETYPE)));

		return group;
	}

	private static PaletteGroup getTypeItemsPaletteGroup() {
		PaletteGroup group = new PaletteGroup("Topic Types Items");
		group
				.add(new CreationToolEntry(
						"Occurence Constraints",
						"Occurence Constraints",
						new OccurenceConstraintCreationFactory(),
						ImageProvider
								.getImageDescriptor(ImageConstants.OCCURENCECONSTRAINT_SM),
						ImageProvider
								.getImageDescriptor(ImageConstants.OCCURENCECONSTRAINT)));

		group
				.add(new CreationToolEntry(
						"Name Constraints",
						"Name Constraints",
						new CreationFactory() {

							@Override
							public Object getNewObject() {
								return ModelFactory.eINSTANCE
										.createNameTypeConstraint();
							}

							@Override
							public Object getObjectType() {
								return NameTypeConstraint.class;
							}

						},
						ImageProvider
								.getImageDescriptor(ImageConstants.NAMECONSTRAINT_SM),
						ImageProvider
								.getImageDescriptor(ImageConstants.NAMECONSTRAINT)));

		group.add(new CreationToolEntry("Subject Identifier Constraints",
				"Subject Identifier Constraints", new CreationFactory() {

					@Override
					public Object getNewObject() {
						return ModelFactory.eINSTANCE
								.createSubjectIdentifierConstraint();
					}

					@Override
					public Object getObjectType() {
						return SubjectIdentifierConstraint.class;
					}

				}, null, null));

		group.add(new CreationToolEntry("Subject Locator Constraints",
				"Subject Locator Constraints", new CreationFactory() {

					@Override
					public Object getNewObject() {
						return ModelFactory.eINSTANCE
								.createSubjectLocatorConstraint();
					}

					@Override
					public Object getObjectType() {
						return SubjectLocatorConstraint.class;
					}

				}, null, null));

		return group;
	}

	private static PaletteGroup getConnectionsPaletteGroup() {
		PaletteGroup group = new PaletteGroup("Connection Items");
		group.add(new ConnectionCreationToolEntry("Is A ...",
				"Create Is A connection", new CreationFactory() {

					@Override
					public Object getNewObject() {
						Edge e = ModelFactory.eINSTANCE.createEdge();
						e.setType(EdgeType.IS_ATYPE);
						return e;
					}

					@Override
					public Object getObjectType() {
						return Edge.class;
					}

				}, ImageProvider.getImageDescriptor(ImageConstants.ISA_SM),
				ImageProvider.getImageDescriptor(ImageConstants.ISA)));

		group.add(new ConnectionCreationToolEntry("Kind Of ...",
				"Create kind of connection", new CreationFactory() {

					@Override
					public Object getNewObject() {
						Edge e = ModelFactory.eINSTANCE.createEdge();
						e.setType(EdgeType.AKO_TYPE);
						return e;
					}

					@Override
					public Object getObjectType() {
						return Edge.class;
					}

				}, ImageProvider.getImageDescriptor(ImageConstants.KINDOF_SM),
				ImageProvider.getImageDescriptor(ImageConstants.KINDOF)));

		group
				.add(new CreationToolEntry(
						"Association Constraint",
						"Association Constraint",
						new CreationFactory() {

							@Override
							public Object getNewObject() {
								AssociationNode node = ModelFactory.eINSTANCE
										.createAssociationNode();
								AssociationTypeConstraint ac = ModelFactory.eINSTANCE
										.createAssociationTypeConstraint();
								node.setAssociationConstraint(ac);
								return node;
							}

							@Override
							public Object getObjectType() {
								return AssociationNode.class;
							}

						},
						ImageProvider
								.getImageDescriptor(ImageConstants.ASSOCIATIONCONSTRAINT_SM),
						ImageProvider
								.getImageDescriptor(ImageConstants.ASSOCIATIONCONSTRAINT)));

		group.add(new ConnectionCreationToolEntry("Role Constraint ...",
				"Create Role Constraint connection", new CreationFactory() {

					@Override
					public Object getNewObject() {
						Edge e = ModelFactory.eINSTANCE.createEdge();
						e.setRoleConstraint(ModelFactory.eINSTANCE
								.createRolePlayerConstraints());
						e.setType(EdgeType.ROLE_CONSTRAINT_TYPE);
						return e;
					}

					@Override
					public Object getObjectType() {
						return Edge.class;
					}

				}, null, null));

		return group;
	}

}
