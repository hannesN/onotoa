package de.topicmapslab.tmcledit.model.diagram.part;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.Tool;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.PaletteSeparator;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeConnectionTool;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeCreationTool;

import de.topicmapslab.tmcledit.model.diagram.providers.TmceleditElementTypes;

/**
 * @generated
 */
public class TmceleditPaletteFactory {

	/**
	 * @generated
	 */
	public void fillPalette(PaletteRoot paletteRoot) {
		paletteRoot.add(createDefault1Group());
		paletteRoot.add(createTopicTypes2Group());
		paletteRoot.add(createAssociations3Group());
		paletteRoot.add(createTopicElementConstraints4Group());
	}

	/**
	 * Creates "Default" palette tool group
	 * @generated
	 */
	private PaletteContainer createDefault1Group() {
		PaletteGroup paletteContainer = new PaletteGroup(
				Messages.Default1Group_title);
		paletteContainer.setDescription(Messages.Default1Group_desc);
		paletteContainer.add(new PaletteSeparator());
		paletteContainer.add(new PaletteSeparator());
		return paletteContainer;
	}

	/**
	 * Creates "Topic Types" palette tool group
	 * @generated
	 */
	private PaletteContainer createTopicTypes2Group() {
		PaletteDrawer paletteContainer = new PaletteDrawer(
				Messages.TopicTypes2Group_title);
		paletteContainer.setDescription(Messages.TopicTypes2Group_desc);
		paletteContainer.add(createTopicType1CreationTool());
		paletteContainer.add(createOccurenceType2CreationTool());
		paletteContainer.add(createNameType3CreationTool());
		paletteContainer.add(createScopeType4CreationTool());
		paletteContainer.add(createAssociationsType5CreationTool());
		paletteContainer.add(createRoleType6CreationTool());
		paletteContainer.add(createKindOf7CreationTool());
		paletteContainer.add(createIsA8CreationTool());
		return paletteContainer;
	}

	/**
	 * Creates "Associations" palette tool group
	 * @generated
	 */
	private PaletteContainer createAssociations3Group() {
		PaletteDrawer paletteContainer = new PaletteDrawer(
				Messages.Associations3Group_title);
		paletteContainer.add(createAssociationsTypeConstraint1CreationTool());
		paletteContainer.add(createRoleTypeConstraint2CreationTool());
		return paletteContainer;
	}

	/**
	 * Creates "TopicElementConstraints" palette tool group
	 * @generated
	 */
	private PaletteContainer createTopicElementConstraints4Group() {
		PaletteDrawer paletteContainer = new PaletteDrawer(
				Messages.TopicElementConstraints4Group_title);
		paletteContainer.add(createOccurenceTypeConstraint1CreationTool());
		paletteContainer.add(createNameTypeConstraint2CreationTool());
		paletteContainer.add(createSubjectLocatorConstraint3CreationTool());
		paletteContainer.add(createSubjectIdentifierConstraint4CreationTool());
		return paletteContainer;
	}

	/**
	 * @generated
	 */
	private ToolEntry createTopicType1CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(TmceleditElementTypes.TopicType_1007);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.TopicType1CreationTool_title,
				Messages.TopicType1CreationTool_desc, types);
		entry
				.setSmallIcon(TmceleditDiagramEditorPlugin
						.findImageDescriptor("/de.topicmapslab.tmcledit/icons/topictype_sm.gif")); //$NON-NLS-1$
		entry
				.setLargeIcon(TmceleditDiagramEditorPlugin
						.findImageDescriptor("/de.topicmapslab.tmcledit/icons/topictype.gif")); //$NON-NLS-1$
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createOccurenceType2CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(TmceleditElementTypes.OccurenceType_1005);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.OccurenceType2CreationTool_title,
				Messages.OccurenceType2CreationTool_desc, types);
		entry.setSmallIcon(TmceleditElementTypes
				.getImageDescriptor(TmceleditElementTypes.OccurenceType_1005));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createNameType3CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(TmceleditElementTypes.NameType_1001);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.NameType3CreationTool_title,
				Messages.NameType3CreationTool_desc, types);
		entry.setSmallIcon(TmceleditElementTypes
				.getImageDescriptor(TmceleditElementTypes.NameType_1001));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createScopeType4CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(TmceleditElementTypes.ScopeType_1002);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.ScopeType4CreationTool_title,
				Messages.ScopeType4CreationTool_desc, types);
		entry.setSmallIcon(TmceleditElementTypes
				.getImageDescriptor(TmceleditElementTypes.ScopeType_1002));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createAssociationsType5CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(TmceleditElementTypes.AssociationsType_1004);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.AssociationsType5CreationTool_title,
				Messages.AssociationsType5CreationTool_desc, types);
		entry
				.setSmallIcon(TmceleditElementTypes
						.getImageDescriptor(TmceleditElementTypes.AssociationsType_1004));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createRoleType6CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(TmceleditElementTypes.RoleType_1003);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.RoleType6CreationTool_title,
				Messages.RoleType6CreationTool_desc, types);
		entry.setSmallIcon(TmceleditElementTypes
				.getImageDescriptor(TmceleditElementTypes.RoleType_1003));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createKindOf7CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(TmceleditElementTypes.TopicTypeAko_3002);
		LinkToolEntry entry = new LinkToolEntry(
				Messages.KindOf7CreationTool_title,
				Messages.KindOf7CreationTool_desc, types);
		entry
				.setSmallIcon(TmceleditDiagramEditorPlugin
						.findImageDescriptor("/de.topicmapslab.tmcledit/icons/kindof_sm.gif")); //$NON-NLS-1$
		entry
				.setLargeIcon(TmceleditDiagramEditorPlugin
						.findImageDescriptor("/de.topicmapslab.tmcledit/icons/kindof.gif")); //$NON-NLS-1$
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createIsA8CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(TmceleditElementTypes.TopicTypeIsa_3003);
		LinkToolEntry entry = new LinkToolEntry(
				Messages.IsA8CreationTool_title,
				Messages.IsA8CreationTool_desc, types);
		entry
				.setSmallIcon(TmceleditDiagramEditorPlugin
						.findImageDescriptor("/de.topicmapslab.tmcledit/icons/isa_sm.gif")); //$NON-NLS-1$
		entry
				.setLargeIcon(TmceleditDiagramEditorPlugin
						.findImageDescriptor("/de.topicmapslab.tmcledit/icons/isa.gif")); //$NON-NLS-1$
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createAssociationsTypeConstraint1CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(TmceleditElementTypes.AssociationTypeConstraint_1006);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.AssociationsTypeConstraint1CreationTool_title,
				Messages.AssociationsTypeConstraint1CreationTool_desc, types);
		entry
				.setSmallIcon(TmceleditDiagramEditorPlugin
						.findImageDescriptor("/de.topicmapslab.tmcledit/icons/associationconstraint_sm.gif")); //$NON-NLS-1$
		entry
				.setLargeIcon(TmceleditDiagramEditorPlugin
						.findImageDescriptor("/de.topicmapslab.tmcledit/icons/associationconstraint.gif")); //$NON-NLS-1$
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createRoleTypeConstraint2CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(TmceleditElementTypes.RoleTypeConstraints_3001);
		LinkToolEntry entry = new LinkToolEntry(
				Messages.RoleTypeConstraint2CreationTool_title,
				Messages.RoleTypeConstraint2CreationTool_desc, types);
		entry
				.setSmallIcon(TmceleditDiagramEditorPlugin
						.findImageDescriptor("/de.topicmapslab.tmcledit/icons/associationroleconstraint_sm.gif")); //$NON-NLS-1$
		entry
				.setLargeIcon(TmceleditDiagramEditorPlugin
						.findImageDescriptor("/de.topicmapslab.tmcledit/icons/associationroleconstraint.gif")); //$NON-NLS-1$
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createOccurenceTypeConstraint1CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(6);
		types.add(TmceleditElementTypes.OccurenceTypeConstraint_2002);
		types.add(TmceleditElementTypes.OccurenceTypeConstraint_2006);
		types.add(TmceleditElementTypes.OccurenceTypeConstraint_2010);
		types.add(TmceleditElementTypes.OccurenceTypeConstraint_2014);
		types.add(TmceleditElementTypes.OccurenceTypeConstraint_2018);
		types.add(TmceleditElementTypes.OccurenceTypeConstraint_2022);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.OccurenceTypeConstraint1CreationTool_title,
				Messages.OccurenceTypeConstraint1CreationTool_desc, types);
		entry
				.setSmallIcon(TmceleditElementTypes
						.getImageDescriptor(TmceleditElementTypes.OccurenceTypeConstraint_2002));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createNameTypeConstraint2CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(6);
		types.add(TmceleditElementTypes.NameTypeConstraint_2001);
		types.add(TmceleditElementTypes.NameTypeConstraint_2005);
		types.add(TmceleditElementTypes.NameTypeConstraint_2009);
		types.add(TmceleditElementTypes.NameTypeConstraint_2013);
		types.add(TmceleditElementTypes.NameTypeConstraint_2017);
		types.add(TmceleditElementTypes.NameTypeConstraint_2021);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.NameTypeConstraint2CreationTool_title,
				Messages.NameTypeConstraint2CreationTool_desc, types);
		entry
				.setSmallIcon(TmceleditElementTypes
						.getImageDescriptor(TmceleditElementTypes.NameTypeConstraint_2001));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createSubjectLocatorConstraint3CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(6);
		types.add(TmceleditElementTypes.SubjectLocatorConstraint_2004);
		types.add(TmceleditElementTypes.SubjectLocatorConstraint_2008);
		types.add(TmceleditElementTypes.SubjectLocatorConstraint_2012);
		types.add(TmceleditElementTypes.SubjectLocatorConstraint_2016);
		types.add(TmceleditElementTypes.SubjectLocatorConstraint_2020);
		types.add(TmceleditElementTypes.SubjectLocatorConstraint_2024);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.SubjectLocatorConstraint3CreationTool_title,
				Messages.SubjectLocatorConstraint3CreationTool_desc, types);
		entry
				.setSmallIcon(TmceleditElementTypes
						.getImageDescriptor(TmceleditElementTypes.SubjectLocatorConstraint_2004));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createSubjectIdentifierConstraint4CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(6);
		types.add(TmceleditElementTypes.SubjectIdentifierConstraint_2003);
		types.add(TmceleditElementTypes.SubjectIdentifierConstraint_2007);
		types.add(TmceleditElementTypes.SubjectIdentifierConstraint_2011);
		types.add(TmceleditElementTypes.SubjectIdentifierConstraint_2015);
		types.add(TmceleditElementTypes.SubjectIdentifierConstraint_2019);
		types.add(TmceleditElementTypes.SubjectIdentifierConstraint_2023);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.SubjectIdentifierConstraint4CreationTool_title,
				Messages.SubjectIdentifierConstraint4CreationTool_desc, types);
		entry
				.setSmallIcon(TmceleditElementTypes
						.getImageDescriptor(TmceleditElementTypes.SubjectIdentifierConstraint_2003));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private static class NodeToolEntry extends ToolEntry {

		/**
		 * @generated
		 */
		private final List elementTypes;

		/**
		 * @generated
		 */
		private NodeToolEntry(String title, String description,
				List elementTypes) {
			super(title, description, null, null);
			this.elementTypes = elementTypes;
		}

		/**
		 * @generated
		 */
		public Tool createTool() {
			Tool tool = new UnspecifiedTypeCreationTool(elementTypes);
			tool.setProperties(getToolProperties());
			return tool;
		}
	}

	/**
	 * @generated
	 */
	private static class LinkToolEntry extends ToolEntry {

		/**
		 * @generated
		 */
		private final List relationshipTypes;

		/**
		 * @generated
		 */
		private LinkToolEntry(String title, String description,
				List relationshipTypes) {
			super(title, description, null, null);
			this.relationshipTypes = relationshipTypes;
		}

		/**
		 * @generated
		 */
		public Tool createTool() {
			Tool tool = new UnspecifiedTypeConnectionTool(relationshipTypes);
			tool.setProperties(getToolProperties());
			return tool;
		}
	}
}
