package de.topicmapslab.tmcledit.model.util;

import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.swt.graphics.Image;

import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.provider.TmcleditEditPlugin;

public class ImageProvider {

	public static String ASSOCIATIONNODE = "full/obj16/AssociationNode";
	public static String ASSOCIATIONSTYPE = "full/obj16/AssociationsType";
	public static String OCCURENCETYPE = "full/obj16/OccurenceType";
	public static String NAMETYPECONSTRAINT = "full/obj16/NameTypeConstraint";
	public static String NAMETYPE = "full/obj16/NameType";
	public static String MAPPINGELEMENT = "full/obj16/MappingElement";
	public static String TOPICTYPE = "full/obj16/TopicType";
	public static String TYPENODE = "full/obj16/TypeNode";
	public static String DIAGRAM = "full/obj16/Diagram";
	public static String OCCURENCETYPECONSTRAINT = "full/obj16/OccurenceTypeConstraint";
	public static String TOPICMAPSCHEMA = "full/obj16/TopicMapSchema";
	public static String FILE = "full/obj16/File";
	public static String SUBJECTIDENTIFIERCONSTRAINT = "full/obj16/subjectIdentifierConstraint";
	public static String EDGE = "full/obj16/Edge";
	public static String SCOPETYPE = "full/obj16/ScopeType";
	public static String ROLETYPECONSTRAINTS = "full/obj16/RoleTypeConstraints";
	public static String ROLETYPE = "full/obj16/RoleType";
	public static String SUBJECTLOCATORCONSTRAINT = "full/obj16/subjectLocatorConstraint";
	public static String ASSOCIATIONTYPECONSTRAINT = "full/obj16/AssociationTypeConstraint";
	public static String BENDPOINTS = "full/obj16/Bendpoints";
	public static String NODE = "full/obj16/Node";
	public static String CREATETOPICMAPSCHEMA_TOPICTYPES_NAMETYPE = "full/ctool16/CreateTopicMapSchema_topicTypes_NameType";
	public static String CREATEDIAGRAM_NODES_NODE = "full/ctool16/CreateDiagram_nodes_Node";
	public static String CREATEASSOCIATIONTYPECONSTRAINT_ROLETYPECONSTRAINTS_ROLETYPECONSTRAINTS = "full/ctool16/CreateAssociationTypeConstraint_roleTypeConstraints_RoleTypeConstraints";
	public static String CREATETOPICMAPSCHEMA_TOPICTYPES_ASSOCIATIONSTYPE = "full/ctool16/CreateTopicMapSchema_topicTypes_AssociationsType";
	public static String CREATETOPICMAPSCHEMA_TOPICTYPES_ROLETYPE = "full/ctool16/CreateTopicMapSchema_topicTypes_RoleType";
	public static String CREATEDIAGRAM_NODES_TYPENODE = "full/ctool16/CreateDiagram_nodes_TypeNode";
	public static String CREATEDIAGRAM_NODES_ASSOCIATIONNODE = "full/ctool16/CreateDiagram_nodes_AssociationNode";
	public static String CREATETOPICTYPE_SUBJECTLOCATORCONSTRAINT_SUBJECTLOCATORCONSTRAINT = "full/ctool16/CreateTopicType_subjectLocatorConstraint_subjectLocatorConstraint";
	public static String CREATETOPICTYPE_SUBJECTIDENTIFIERCONSTRAINTS_SUBJECTIDENTIFIERCONSTRAINT = "full/ctool16/CreateTopicType_subjectIdentifierConstraints_subjectIdentifierConstraint";
	public static String CREATETOPICTYPE_NAMECONTRAINTS_NAMETYPECONSTRAINT = "full/ctool16/CreateTopicType_nameContraints_NameTypeConstraint";
	public static String CREATEDIAGRAM_EDGES_EDGE = "full/ctool16/CreateDiagram_edges_Edge";
	public static String CREATETOPICTYPE_OCCURENCECONSTRAINTS_OCCURENCETYPECONSTRAINT = "full/ctool16/CreateTopicType_occurenceConstraints_OccurenceTypeConstraint";
	public static String CREATEFILE_TOPICMAPSCHEMA_TOPICMAPSCHEMA = "full/ctool16/CreateFile_topicMapSchema_TopicMapSchema";
	public static String CREATEFILE_DIAGRAMS_DIAGRAM = "full/ctool16/CreateFile_diagrams_Diagram";
	public static String CREATEEDGE_BENDPOINTS_BENDPOINTS = "full/ctool16/CreateEdge_bendpoints_Bendpoints";
	public static String CREATETOPICMAPSCHEMA_ASSOCIATIONTYPECONSTRAINTS_ASSOCIATIONTYPECONSTRAINT = "full/ctool16/CreateTopicMapSchema_associationTypeConstraints_AssociationTypeConstraint";
	public static String CREATETOPICMAPSCHEMA_TOPICTYPES_SCOPETYPE = "full/ctool16/CreateTopicMapSchema_topicTypes_ScopeType";
	public static String CREATETOPICMAPSCHEMA_TOPICTYPES_TOPICTYPE = "full/ctool16/CreateTopicMapSchema_topicTypes_TopicType";
	public static String CREATETOPICMAPSCHEMA_TOPICTYPES_OCCURENCETYPE = "full/ctool16/CreateTopicMapSchema_topicTypes_OccurenceType";

	
	private static ExtendedImageRegistry imageRegistry;
	
	public static Image getImage(String key) {
		return getExtendedImageRegistry().getImage(TmcleditEditPlugin.INSTANCE.getImage(key));
	}
	
	public static Image getTopicTypeImage(TopicType topicType) {
		switch(topicType.getKind())
		{
			case ASSOCIATION_TYPE:
				return ImageProvider.getImage(ImageProvider.ASSOCIATIONSTYPE);
			case NAME_TYPE:
				return ImageProvider.getImage(ImageProvider.NAMETYPE);
			case OCCURENCE_TYPE:
				return ImageProvider.getImage(ImageProvider.OCCURENCETYPE);
			case ROLE_TYPE:
				return ImageProvider.getImage(ImageProvider.ROLETYPE);
			case SCOPE_TYPE:
				return ImageProvider.getImage(ImageProvider.SCOPETYPE);
			default:
				return ImageProvider.getImage(ImageProvider.TOPICTYPE);
		
		}
	}
	
	private static ExtendedImageRegistry getExtendedImageRegistry() {
		if (imageRegistry == null) {
			imageRegistry = new ExtendedImageRegistry();
			
		}

		return imageRegistry;
	}
}
