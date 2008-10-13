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
import org.eclipse.gef.palette.SelectionToolEntry;
import org.eclipse.gef.requests.CreationFactory;

import de.topicmapslab.tmcledit.diagram.editparts.DiagramEditPart;
import de.topicmapslab.tmcledit.diagram.editparts.EdgeEditPart;
import de.topicmapslab.tmcledit.diagram.editparts.NameTypeConstraintEditPart;
import de.topicmapslab.tmcledit.diagram.editparts.OccurenceTypeConstraintEditPart;
import de.topicmapslab.tmcledit.diagram.editparts.PrefixMappingEditPart;
import de.topicmapslab.tmcledit.diagram.editparts.PrefixMappingElementEditPart;
import de.topicmapslab.tmcledit.diagram.editparts.TypeNodeEditPart;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.Edge;
import de.topicmapslab.tmcledit.model.EdgeType;
import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.MappingElement;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.NameTypeConstraint;
import de.topicmapslab.tmcledit.model.OccurenceTypeConstraint;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.TypeNode;

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

		pr.add(getTypePaletteGroup());
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
				}

				if (part != null)
					part.setModel(model);
				return part;
			}

		};
	}

	private static PaletteGroup getTypePaletteGroup() {
		PaletteGroup group = new PaletteGroup("Topic Types");

		group.add(new CreationToolEntry("Topic Type", "Topic Type",
				new TopicTypeCreationFactory(KindOfTopicType.TOPIC_TYPE), 
				null,
				null));
		group.add(new CreationToolEntry("Occurence Type", "Topic Type",
				new TopicTypeCreationFactory(KindOfTopicType.OCCURENCE_TYPE), 
				null,
				null));
		group.add(new CreationToolEntry("Role Type", "Topic Type",
				new TopicTypeCreationFactory(KindOfTopicType.ROLE_TYPE), 
				null,
				null));
		group.add(new CreationToolEntry("Association Type", "Topic Type",
				new TopicTypeCreationFactory(KindOfTopicType.ASSOCIATION_TYPE),
				null, 
				null));
		group.add(new CreationToolEntry("Name Type", "Topic Type",
				new TopicTypeCreationFactory(KindOfTopicType.NAME_TYPE), 
				null,
				null));
		group.add(new CreationToolEntry("Scope Type", "Topic Type",
				new TopicTypeCreationFactory(KindOfTopicType.SCOPE_TYPE), 
				null,
				null));

		return group;
	}

	private static PaletteGroup getTypeItemsPaletteGroup() {
		PaletteGroup group = new PaletteGroup("Topic Types Items");
		group.add(new CreationToolEntry("Occurence Constraints",
				"Occurence Constraints", new CreationFactory() {

					@Override
					public Object getNewObject() {
						return de.topicmapslab.tmcledit.model.ModelFactory.eINSTANCE
								.createOccurenceTypeConstraint();
					}

					@Override
					public Object getObjectType() {
						return OccurenceTypeConstraint.class;
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

				}, null, null));
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

				}, null, null));

		return group;
	}

	private static final class TopicTypeCreationFactory implements
			CreationFactory {
		private final KindOfTopicType kind;

		public TopicTypeCreationFactory(KindOfTopicType kind) {
			this.kind = kind;
		}

		@Override
		public Object getNewObject() {
			TypeNode node = ModelFactory.eINSTANCE.createTypeNode();
			TopicType tt = ModelFactory.eINSTANCE.createTopicType();
			tt.setId("foo:default");
			tt.setKind(kind);
			node.setTopicType(tt);
			return node;
		}

		@Override
		public Object getObjectType() {
			return TypeNode.class;
		}
	}

}
