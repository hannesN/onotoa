package de.topicmapslab.tmcledit.model.diagram.providers;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.GetParserOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParserProvider;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.ui.services.parser.ParserHintAdapter;
import org.eclipse.gmf.runtime.notation.View;

import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.AssociationsTypeIdEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.NameTypeConstraint2EditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.NameTypeConstraint3EditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.NameTypeConstraint4EditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.NameTypeConstraint5EditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.NameTypeConstraintEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.NameTypeIdEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.OccurenceTypeConstraint2EditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.OccurenceTypeConstraint3EditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.OccurenceTypeConstraint4EditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.OccurenceTypeConstraint5EditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.OccurenceTypeConstraint6EditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.OccurenceTypeConstraintEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.OccurenceTypeIdEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.RoleTypeConstraintsCardMinCardMaxEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.RoleTypeConstraintsRoleEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.RoleTypeIdEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.ScopeTypeIdEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.SubjectIdentifierConstraint2EditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.SubjectIdentifierConstraint3EditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.SubjectIdentifierConstraint4EditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.SubjectIdentifierConstraint5EditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.SubjectIdentifierConstraint6EditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.SubjectIdentifierConstraintEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.SubjectLocatorConstraint2EditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.SubjectLocatorConstraint3EditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.SubjectLocatorConstraint4EditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.SubjectLocatorConstraint5EditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.SubjectLocatorConstraint6EditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.SubjectLocatorConstraint7EditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.SubjectLocatorConstraintEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.TopicTypeIdEditPart;
import de.topicmapslab.tmcledit.model.diagram.parsers.MessageFormatParser;
import de.topicmapslab.tmcledit.model.diagram.part.TmcleditVisualIDRegistry;

/**
 * @generated
 */
public class TmcleditParserProvider extends AbstractProvider implements
		IParserProvider {

	/**
	 * @generated
	 */
	private IParser nameTypeId_4001Parser;

	/**
	 * @generated
	 */
	private IParser getNameTypeId_4001Parser() {
		if (nameTypeId_4001Parser == null) {
			nameTypeId_4001Parser = createNameTypeId_4001Parser();
		}
		return nameTypeId_4001Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createNameTypeId_4001Parser() {
		EAttribute[] features = new EAttribute[] { ModelPackage.eINSTANCE
				.getTopicType_Id(), };
		MessageFormatParser parser = new MessageFormatParser(features);
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser scopeTypeId_4002Parser;

	/**
	 * @generated
	 */
	private IParser getScopeTypeId_4002Parser() {
		if (scopeTypeId_4002Parser == null) {
			scopeTypeId_4002Parser = createScopeTypeId_4002Parser();
		}
		return scopeTypeId_4002Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createScopeTypeId_4002Parser() {
		EAttribute[] features = new EAttribute[] { ModelPackage.eINSTANCE
				.getTopicType_Id(), };
		MessageFormatParser parser = new MessageFormatParser(features);
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser roleTypeId_4003Parser;

	/**
	 * @generated
	 */
	private IParser getRoleTypeId_4003Parser() {
		if (roleTypeId_4003Parser == null) {
			roleTypeId_4003Parser = createRoleTypeId_4003Parser();
		}
		return roleTypeId_4003Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createRoleTypeId_4003Parser() {
		EAttribute[] features = new EAttribute[] { ModelPackage.eINSTANCE
				.getTopicType_Id(), };
		MessageFormatParser parser = new MessageFormatParser(features);
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser associationsTypeId_4004Parser;

	/**
	 * @generated
	 */
	private IParser getAssociationsTypeId_4004Parser() {
		if (associationsTypeId_4004Parser == null) {
			associationsTypeId_4004Parser = createAssociationsTypeId_4004Parser();
		}
		return associationsTypeId_4004Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createAssociationsTypeId_4004Parser() {
		EAttribute[] features = new EAttribute[] { ModelPackage.eINSTANCE
				.getTopicType_Id(), };
		MessageFormatParser parser = new MessageFormatParser(features);
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser occurenceTypeId_4005Parser;

	/**
	 * @generated
	 */
	private IParser getOccurenceTypeId_4005Parser() {
		if (occurenceTypeId_4005Parser == null) {
			occurenceTypeId_4005Parser = createOccurenceTypeId_4005Parser();
		}
		return occurenceTypeId_4005Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createOccurenceTypeId_4005Parser() {
		EAttribute[] features = new EAttribute[] { ModelPackage.eINSTANCE
				.getTopicType_Id(), };
		MessageFormatParser parser = new MessageFormatParser(features);
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser topicTypeId_4006Parser;

	/**
	 * @generated
	 */
	private IParser getTopicTypeId_4006Parser() {
		if (topicTypeId_4006Parser == null) {
			topicTypeId_4006Parser = createTopicTypeId_4006Parser();
		}
		return topicTypeId_4006Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createTopicTypeId_4006Parser() {
		EAttribute[] features = new EAttribute[] { ModelPackage.eINSTANCE
				.getTopicType_Id(), };
		MessageFormatParser parser = new MessageFormatParser(features);
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser nameTypeConstraint_2001Parser;

	/**
	 * @generated
	 */
	private IParser getNameTypeConstraint_2001Parser() {
		if (nameTypeConstraint_2001Parser == null) {
			nameTypeConstraint_2001Parser = createNameTypeConstraint_2001Parser();
		}
		return nameTypeConstraint_2001Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createNameTypeConstraint_2001Parser() {
		EAttribute[] features = new EAttribute[] {
				ModelPackage.eINSTANCE.getAbstractContraint_Name(),
				ModelPackage.eINSTANCE.getAbstractContraint_CardMin(),
				ModelPackage.eINSTANCE.getAbstractContraint_CardMax(),
				ModelPackage.eINSTANCE.getAbstractContraint_Regexp(), };
		MessageFormatParser parser = new MessageFormatParser(features);
		parser.setViewPattern("{0} : {1}..{2} ({3})");
		parser.setEditorPattern("{0} : {1}..{2} ({3})");
		parser.setEditPattern("{0} : {1}..{2} ({3})");
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser occurenceTypeConstraint_2002Parser;

	/**
	 * @generated
	 */
	private IParser getOccurenceTypeConstraint_2002Parser() {
		if (occurenceTypeConstraint_2002Parser == null) {
			occurenceTypeConstraint_2002Parser = createOccurenceTypeConstraint_2002Parser();
		}
		return occurenceTypeConstraint_2002Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createOccurenceTypeConstraint_2002Parser() {
		EAttribute[] features = new EAttribute[] {
				ModelPackage.eINSTANCE.getAbstractContraint_Name(),
				ModelPackage.eINSTANCE.getOccurenceTypeConstraint_DataType(),
				ModelPackage.eINSTANCE.getAbstractContraint_CardMin(),
				ModelPackage.eINSTANCE.getAbstractContraint_CardMax(), };
		MessageFormatParser parser = new MessageFormatParser(features);
		parser.setViewPattern("{0} : {1} \t {2}..{3}");
		parser.setEditorPattern("{0} : {1} \t {2}..{3}");
		parser.setEditPattern("{0} : {1} \t {2}..{3}");
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser subjectIdentifierConstraint_2003Parser;

	/**
	 * @generated
	 */
	private IParser getSubjectIdentifierConstraint_2003Parser() {
		if (subjectIdentifierConstraint_2003Parser == null) {
			subjectIdentifierConstraint_2003Parser = createSubjectIdentifierConstraint_2003Parser();
		}
		return subjectIdentifierConstraint_2003Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createSubjectIdentifierConstraint_2003Parser() {
		EAttribute[] features = new EAttribute[] { ModelPackage.eINSTANCE
				.getAbstractContraint_Name(), };
		MessageFormatParser parser = new MessageFormatParser(features);
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser subjectLocatorConstraint_2004Parser;

	/**
	 * @generated
	 */
	private IParser getSubjectLocatorConstraint_2004Parser() {
		if (subjectLocatorConstraint_2004Parser == null) {
			subjectLocatorConstraint_2004Parser = createSubjectLocatorConstraint_2004Parser();
		}
		return subjectLocatorConstraint_2004Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createSubjectLocatorConstraint_2004Parser() {
		EAttribute[] features = new EAttribute[] { ModelPackage.eINSTANCE
				.getAbstractContraint_Name(), };
		MessageFormatParser parser = new MessageFormatParser(features);
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser nameTypeConstraint_2005Parser;

	/**
	 * @generated
	 */
	private IParser getNameTypeConstraint_2005Parser() {
		if (nameTypeConstraint_2005Parser == null) {
			nameTypeConstraint_2005Parser = createNameTypeConstraint_2005Parser();
		}
		return nameTypeConstraint_2005Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createNameTypeConstraint_2005Parser() {
		EAttribute[] features = new EAttribute[] {
				ModelPackage.eINSTANCE.getAbstractContraint_Name(),
				ModelPackage.eINSTANCE.getAbstractContraint_CardMin(),
				ModelPackage.eINSTANCE.getAbstractContraint_CardMax(),
				ModelPackage.eINSTANCE.getAbstractContraint_Regexp(), };
		MessageFormatParser parser = new MessageFormatParser(features);
		parser.setViewPattern("{0} : {1}..{2} ({3})");
		parser.setEditorPattern("{0} : {1}..{2} ({3})");
		parser.setEditPattern("{0} : {1}..{2} ({3})");
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser occurenceTypeConstraint_2006Parser;

	/**
	 * @generated
	 */
	private IParser getOccurenceTypeConstraint_2006Parser() {
		if (occurenceTypeConstraint_2006Parser == null) {
			occurenceTypeConstraint_2006Parser = createOccurenceTypeConstraint_2006Parser();
		}
		return occurenceTypeConstraint_2006Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createOccurenceTypeConstraint_2006Parser() {
		EAttribute[] features = new EAttribute[] {
				ModelPackage.eINSTANCE.getAbstractContraint_Name(),
				ModelPackage.eINSTANCE.getOccurenceTypeConstraint_DataType(),
				ModelPackage.eINSTANCE.getAbstractContraint_CardMin(),
				ModelPackage.eINSTANCE.getAbstractContraint_CardMax(), };
		MessageFormatParser parser = new MessageFormatParser(features);
		parser.setViewPattern("{0} : {1} \t {2}..{3}");
		parser.setEditorPattern("{0} : {1} \t {2}..{3}");
		parser.setEditPattern("{0} : {1} \t {2}..{3}");
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser subjectIdentifierConstraint_2007Parser;

	/**
	 * @generated
	 */
	private IParser getSubjectIdentifierConstraint_2007Parser() {
		if (subjectIdentifierConstraint_2007Parser == null) {
			subjectIdentifierConstraint_2007Parser = createSubjectIdentifierConstraint_2007Parser();
		}
		return subjectIdentifierConstraint_2007Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createSubjectIdentifierConstraint_2007Parser() {
		EAttribute[] features = new EAttribute[] { ModelPackage.eINSTANCE
				.getAbstractContraint_Name(), };
		MessageFormatParser parser = new MessageFormatParser(features);
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser subjectLocatorConstraint_2008Parser;

	/**
	 * @generated
	 */
	private IParser getSubjectLocatorConstraint_2008Parser() {
		if (subjectLocatorConstraint_2008Parser == null) {
			subjectLocatorConstraint_2008Parser = createSubjectLocatorConstraint_2008Parser();
		}
		return subjectLocatorConstraint_2008Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createSubjectLocatorConstraint_2008Parser() {
		EAttribute[] features = new EAttribute[] { ModelPackage.eINSTANCE
				.getAbstractContraint_Name(), };
		MessageFormatParser parser = new MessageFormatParser(features);
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser nameTypeConstraint_2009Parser;

	/**
	 * @generated
	 */
	private IParser getNameTypeConstraint_2009Parser() {
		if (nameTypeConstraint_2009Parser == null) {
			nameTypeConstraint_2009Parser = createNameTypeConstraint_2009Parser();
		}
		return nameTypeConstraint_2009Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createNameTypeConstraint_2009Parser() {
		EAttribute[] features = new EAttribute[] {
				ModelPackage.eINSTANCE.getAbstractContraint_Name(),
				ModelPackage.eINSTANCE.getAbstractContraint_CardMin(),
				ModelPackage.eINSTANCE.getAbstractContraint_CardMax(),
				ModelPackage.eINSTANCE.getAbstractContraint_Regexp(), };
		MessageFormatParser parser = new MessageFormatParser(features);
		parser.setViewPattern("{0} : {1}..{2} ({3})");
		parser.setEditorPattern("{0} : {1}..{2} ({3})");
		parser.setEditPattern("{0} : {1}..{2} ({3})");
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser occurenceTypeConstraint_2010Parser;

	/**
	 * @generated
	 */
	private IParser getOccurenceTypeConstraint_2010Parser() {
		if (occurenceTypeConstraint_2010Parser == null) {
			occurenceTypeConstraint_2010Parser = createOccurenceTypeConstraint_2010Parser();
		}
		return occurenceTypeConstraint_2010Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createOccurenceTypeConstraint_2010Parser() {
		EAttribute[] features = new EAttribute[] {
				ModelPackage.eINSTANCE.getAbstractContraint_Name(),
				ModelPackage.eINSTANCE.getOccurenceTypeConstraint_DataType(),
				ModelPackage.eINSTANCE.getAbstractContraint_CardMin(),
				ModelPackage.eINSTANCE.getAbstractContraint_CardMax(), };
		MessageFormatParser parser = new MessageFormatParser(features);
		parser.setViewPattern("{0} : {1} \t {2}..{3}");
		parser.setEditorPattern("{0} : {1} \t {2}..{3}");
		parser.setEditPattern("{0} : {1} \t {2}..{3}");
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser subjectIdentifierConstraint_2011Parser;

	/**
	 * @generated
	 */
	private IParser getSubjectIdentifierConstraint_2011Parser() {
		if (subjectIdentifierConstraint_2011Parser == null) {
			subjectIdentifierConstraint_2011Parser = createSubjectIdentifierConstraint_2011Parser();
		}
		return subjectIdentifierConstraint_2011Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createSubjectIdentifierConstraint_2011Parser() {
		EAttribute[] features = new EAttribute[] { ModelPackage.eINSTANCE
				.getAbstractContraint_Name(), };
		MessageFormatParser parser = new MessageFormatParser(features);
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser subjectLocatorConstraint_2012Parser;

	/**
	 * @generated
	 */
	private IParser getSubjectLocatorConstraint_2012Parser() {
		if (subjectLocatorConstraint_2012Parser == null) {
			subjectLocatorConstraint_2012Parser = createSubjectLocatorConstraint_2012Parser();
		}
		return subjectLocatorConstraint_2012Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createSubjectLocatorConstraint_2012Parser() {
		EAttribute[] features = new EAttribute[] { ModelPackage.eINSTANCE
				.getAbstractContraint_Name(), };
		MessageFormatParser parser = new MessageFormatParser(features);
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser nameTypeConstraint_2013Parser;

	/**
	 * @generated
	 */
	private IParser getNameTypeConstraint_2013Parser() {
		if (nameTypeConstraint_2013Parser == null) {
			nameTypeConstraint_2013Parser = createNameTypeConstraint_2013Parser();
		}
		return nameTypeConstraint_2013Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createNameTypeConstraint_2013Parser() {
		EAttribute[] features = new EAttribute[] {
				ModelPackage.eINSTANCE.getAbstractContraint_Name(),
				ModelPackage.eINSTANCE.getAbstractContraint_CardMin(),
				ModelPackage.eINSTANCE.getAbstractContraint_CardMax(),
				ModelPackage.eINSTANCE.getAbstractContraint_Regexp(), };
		MessageFormatParser parser = new MessageFormatParser(features);
		parser.setViewPattern("{0} : {1}..{2} ({3})");
		parser.setEditorPattern("{0} : {1}..{2} ({3})");
		parser.setEditPattern("{0} : {1}..{2} ({3})");
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser occurenceTypeConstraint_2014Parser;

	/**
	 * @generated
	 */
	private IParser getOccurenceTypeConstraint_2014Parser() {
		if (occurenceTypeConstraint_2014Parser == null) {
			occurenceTypeConstraint_2014Parser = createOccurenceTypeConstraint_2014Parser();
		}
		return occurenceTypeConstraint_2014Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createOccurenceTypeConstraint_2014Parser() {
		EAttribute[] features = new EAttribute[] {
				ModelPackage.eINSTANCE.getAbstractContraint_Name(),
				ModelPackage.eINSTANCE.getOccurenceTypeConstraint_DataType(),
				ModelPackage.eINSTANCE.getAbstractContraint_CardMin(),
				ModelPackage.eINSTANCE.getAbstractContraint_CardMax(), };
		MessageFormatParser parser = new MessageFormatParser(features);
		parser.setViewPattern("{0} : {1} \t {2}..{3}");
		parser.setEditorPattern("{0} : {1} \t {2}..{3}");
		parser.setEditPattern("{0} : {1} \t {2}..{3}");
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser subjectIdentifierConstraint_2015Parser;

	/**
	 * @generated
	 */
	private IParser getSubjectIdentifierConstraint_2015Parser() {
		if (subjectIdentifierConstraint_2015Parser == null) {
			subjectIdentifierConstraint_2015Parser = createSubjectIdentifierConstraint_2015Parser();
		}
		return subjectIdentifierConstraint_2015Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createSubjectIdentifierConstraint_2015Parser() {
		EAttribute[] features = new EAttribute[] { ModelPackage.eINSTANCE
				.getAbstractContraint_Name(), };
		MessageFormatParser parser = new MessageFormatParser(features);
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser subjectLocatorConstraint_2016Parser;

	/**
	 * @generated
	 */
	private IParser getSubjectLocatorConstraint_2016Parser() {
		if (subjectLocatorConstraint_2016Parser == null) {
			subjectLocatorConstraint_2016Parser = createSubjectLocatorConstraint_2016Parser();
		}
		return subjectLocatorConstraint_2016Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createSubjectLocatorConstraint_2016Parser() {
		EAttribute[] features = new EAttribute[] { ModelPackage.eINSTANCE
				.getAbstractContraint_Name(), };
		MessageFormatParser parser = new MessageFormatParser(features);
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser nameTypeConstraint_2017Parser;

	/**
	 * @generated
	 */
	private IParser getNameTypeConstraint_2017Parser() {
		if (nameTypeConstraint_2017Parser == null) {
			nameTypeConstraint_2017Parser = createNameTypeConstraint_2017Parser();
		}
		return nameTypeConstraint_2017Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createNameTypeConstraint_2017Parser() {
		EAttribute[] features = new EAttribute[] {
				ModelPackage.eINSTANCE.getAbstractContraint_Name(),
				ModelPackage.eINSTANCE.getAbstractContraint_CardMin(),
				ModelPackage.eINSTANCE.getAbstractContraint_CardMax(),
				ModelPackage.eINSTANCE.getAbstractContraint_Regexp(), };
		MessageFormatParser parser = new MessageFormatParser(features);
		parser.setViewPattern("{0} : {1}..{2} ({3})");
		parser.setEditorPattern("{0} : {1}..{2} ({3})");
		parser.setEditPattern("{0} : {1}..{2} ({3})");
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser occurenceTypeConstraint_2018Parser;

	/**
	 * @generated
	 */
	private IParser getOccurenceTypeConstraint_2018Parser() {
		if (occurenceTypeConstraint_2018Parser == null) {
			occurenceTypeConstraint_2018Parser = createOccurenceTypeConstraint_2018Parser();
		}
		return occurenceTypeConstraint_2018Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createOccurenceTypeConstraint_2018Parser() {
		EAttribute[] features = new EAttribute[] {
				ModelPackage.eINSTANCE.getAbstractContraint_Name(),
				ModelPackage.eINSTANCE.getOccurenceTypeConstraint_DataType(),
				ModelPackage.eINSTANCE.getAbstractContraint_CardMin(),
				ModelPackage.eINSTANCE.getAbstractContraint_CardMax(), };
		MessageFormatParser parser = new MessageFormatParser(features);
		parser.setViewPattern("{0} : {1} \t {2}..{3}");
		parser.setEditorPattern("{0} : {1} \t {2}..{3}");
		parser.setEditPattern("{0} : {1} \t {2}..{3}");
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser subjectIdentifierConstraint_2019Parser;

	/**
	 * @generated
	 */
	private IParser getSubjectIdentifierConstraint_2019Parser() {
		if (subjectIdentifierConstraint_2019Parser == null) {
			subjectIdentifierConstraint_2019Parser = createSubjectIdentifierConstraint_2019Parser();
		}
		return subjectIdentifierConstraint_2019Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createSubjectIdentifierConstraint_2019Parser() {
		EAttribute[] features = new EAttribute[] { ModelPackage.eINSTANCE
				.getAbstractContraint_Name(), };
		MessageFormatParser parser = new MessageFormatParser(features);
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser subjectLocatorConstraint_2020Parser;

	/**
	 * @generated
	 */
	private IParser getSubjectLocatorConstraint_2020Parser() {
		if (subjectLocatorConstraint_2020Parser == null) {
			subjectLocatorConstraint_2020Parser = createSubjectLocatorConstraint_2020Parser();
		}
		return subjectLocatorConstraint_2020Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createSubjectLocatorConstraint_2020Parser() {
		EAttribute[] features = new EAttribute[] { ModelPackage.eINSTANCE
				.getAbstractContraint_Name(), };
		MessageFormatParser parser = new MessageFormatParser(features);
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser nameTypeConstraint_2021Parser;

	/**
	 * @generated
	 */
	private IParser getNameTypeConstraint_2021Parser() {
		if (nameTypeConstraint_2021Parser == null) {
			nameTypeConstraint_2021Parser = createNameTypeConstraint_2021Parser();
		}
		return nameTypeConstraint_2021Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createNameTypeConstraint_2021Parser() {
		EAttribute[] features = new EAttribute[] {
				ModelPackage.eINSTANCE.getAbstractContraint_Name(),
				ModelPackage.eINSTANCE.getAbstractContraint_CardMin(),
				ModelPackage.eINSTANCE.getAbstractContraint_CardMax(),
				ModelPackage.eINSTANCE.getAbstractContraint_Regexp(), };
		MessageFormatParser parser = new MessageFormatParser(features);
		parser.setViewPattern("{0} : {1}..{2} ({3})");
		parser.setEditorPattern("{0} : {1}..{2} ({3})");
		parser.setEditPattern("{0} : {1}..{2} ({3})");
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser occurenceTypeConstraint_2022Parser;

	/**
	 * @generated
	 */
	private IParser getOccurenceTypeConstraint_2022Parser() {
		if (occurenceTypeConstraint_2022Parser == null) {
			occurenceTypeConstraint_2022Parser = createOccurenceTypeConstraint_2022Parser();
		}
		return occurenceTypeConstraint_2022Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createOccurenceTypeConstraint_2022Parser() {
		EAttribute[] features = new EAttribute[] {
				ModelPackage.eINSTANCE.getAbstractContraint_Name(),
				ModelPackage.eINSTANCE.getOccurenceTypeConstraint_DataType(),
				ModelPackage.eINSTANCE.getAbstractContraint_CardMin(),
				ModelPackage.eINSTANCE.getAbstractContraint_CardMax(), };
		MessageFormatParser parser = new MessageFormatParser(features);
		parser.setViewPattern("{0} : {1} \t {2}..{3}");
		parser.setEditorPattern("{0} : {1} \t {2}..{3}");
		parser.setEditPattern("{0} : {1} \t {2}..{3}");
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser subjectIdentifierConstraint_2023Parser;

	/**
	 * @generated
	 */
	private IParser getSubjectIdentifierConstraint_2023Parser() {
		if (subjectIdentifierConstraint_2023Parser == null) {
			subjectIdentifierConstraint_2023Parser = createSubjectIdentifierConstraint_2023Parser();
		}
		return subjectIdentifierConstraint_2023Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createSubjectIdentifierConstraint_2023Parser() {
		EAttribute[] features = new EAttribute[] { ModelPackage.eINSTANCE
				.getAbstractContraint_Name(), };
		MessageFormatParser parser = new MessageFormatParser(features);
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser subjectLocatorConstraint_2024Parser;

	/**
	 * @generated
	 */
	private IParser getSubjectLocatorConstraint_2024Parser() {
		if (subjectLocatorConstraint_2024Parser == null) {
			subjectLocatorConstraint_2024Parser = createSubjectLocatorConstraint_2024Parser();
		}
		return subjectLocatorConstraint_2024Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createSubjectLocatorConstraint_2024Parser() {
		EAttribute[] features = new EAttribute[] { ModelPackage.eINSTANCE
				.getAbstractContraint_Name(), };
		MessageFormatParser parser = new MessageFormatParser(features);
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser roleTypeConstraintsCardMinCardMax_4007Parser;

	/**
	 * @generated
	 */
	private IParser getRoleTypeConstraintsCardMinCardMax_4007Parser() {
		if (roleTypeConstraintsCardMinCardMax_4007Parser == null) {
			roleTypeConstraintsCardMinCardMax_4007Parser = createRoleTypeConstraintsCardMinCardMax_4007Parser();
		}
		return roleTypeConstraintsCardMinCardMax_4007Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createRoleTypeConstraintsCardMinCardMax_4007Parser() {
		EAttribute[] features = new EAttribute[] {
				ModelPackage.eINSTANCE.getRoleTypeConstraints_CardMin(),
				ModelPackage.eINSTANCE.getRoleTypeConstraints_CardMax(), };
		MessageFormatParser parser = new MessageFormatParser(features);
		parser.setViewPattern("{0}..{1}");
		parser.setEditorPattern("{0}..{1}");
		parser.setEditPattern("{0}..{1}");
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser roleTypeConstraintsCardMin_4008Parser;

	/**
	 * @generated
	 */
	private IParser getRoleTypeConstraintsCardMin_4008Parser() {
		if (roleTypeConstraintsCardMin_4008Parser == null) {
			roleTypeConstraintsCardMin_4008Parser = createRoleTypeConstraintsCardMin_4008Parser();
		}
		return roleTypeConstraintsCardMin_4008Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createRoleTypeConstraintsCardMin_4008Parser() {
		EAttribute[] features = new EAttribute[] { ModelPackage.eINSTANCE
				.getRoleTypeConstraints_CardMin(), };
		MessageFormatParser parser = new MessageFormatParser(features);
		return parser;
	}

	/**
	 * @generated
	 */
	protected IParser getParser(int visualID) {
		switch (visualID) {
		case NameTypeIdEditPart.VISUAL_ID:
			return getNameTypeId_4001Parser();
		case ScopeTypeIdEditPart.VISUAL_ID:
			return getScopeTypeId_4002Parser();
		case RoleTypeIdEditPart.VISUAL_ID:
			return getRoleTypeId_4003Parser();
		case AssociationsTypeIdEditPart.VISUAL_ID:
			return getAssociationsTypeId_4004Parser();
		case OccurenceTypeIdEditPart.VISUAL_ID:
			return getOccurenceTypeId_4005Parser();
		case TopicTypeIdEditPart.VISUAL_ID:
			return getTopicTypeId_4006Parser();
		case NameTypeConstraintEditPart.VISUAL_ID:
			return getNameTypeConstraint_2001Parser();
		case OccurenceTypeConstraintEditPart.VISUAL_ID:
			return getOccurenceTypeConstraint_2002Parser();
		case SubjectIdentifierConstraintEditPart.VISUAL_ID:
			return getSubjectIdentifierConstraint_2003Parser();
		case SubjectLocatorConstraintEditPart.VISUAL_ID:
			return getSubjectLocatorConstraint_2004Parser();
		case SubjectLocatorConstraint2EditPart.VISUAL_ID:
			return getNameTypeConstraint_2005Parser();
		case OccurenceTypeConstraint2EditPart.VISUAL_ID:
			return getOccurenceTypeConstraint_2006Parser();
		case SubjectIdentifierConstraint2EditPart.VISUAL_ID:
			return getSubjectIdentifierConstraint_2007Parser();
		case SubjectLocatorConstraint3EditPart.VISUAL_ID:
			return getSubjectLocatorConstraint_2008Parser();
		case NameTypeConstraint2EditPart.VISUAL_ID:
			return getNameTypeConstraint_2009Parser();
		case OccurenceTypeConstraint3EditPart.VISUAL_ID:
			return getOccurenceTypeConstraint_2010Parser();
		case SubjectIdentifierConstraint3EditPart.VISUAL_ID:
			return getSubjectIdentifierConstraint_2011Parser();
		case SubjectLocatorConstraint4EditPart.VISUAL_ID:
			return getSubjectLocatorConstraint_2012Parser();
		case NameTypeConstraint3EditPart.VISUAL_ID:
			return getNameTypeConstraint_2013Parser();
		case OccurenceTypeConstraint4EditPart.VISUAL_ID:
			return getOccurenceTypeConstraint_2014Parser();
		case SubjectIdentifierConstraint4EditPart.VISUAL_ID:
			return getSubjectIdentifierConstraint_2015Parser();
		case SubjectLocatorConstraint5EditPart.VISUAL_ID:
			return getSubjectLocatorConstraint_2016Parser();
		case NameTypeConstraint4EditPart.VISUAL_ID:
			return getNameTypeConstraint_2017Parser();
		case OccurenceTypeConstraint5EditPart.VISUAL_ID:
			return getOccurenceTypeConstraint_2018Parser();
		case SubjectIdentifierConstraint5EditPart.VISUAL_ID:
			return getSubjectIdentifierConstraint_2019Parser();
		case SubjectLocatorConstraint6EditPart.VISUAL_ID:
			return getSubjectLocatorConstraint_2020Parser();
		case NameTypeConstraint5EditPart.VISUAL_ID:
			return getNameTypeConstraint_2021Parser();
		case OccurenceTypeConstraint6EditPart.VISUAL_ID:
			return getOccurenceTypeConstraint_2022Parser();
		case SubjectIdentifierConstraint6EditPart.VISUAL_ID:
			return getSubjectIdentifierConstraint_2023Parser();
		case SubjectLocatorConstraint7EditPart.VISUAL_ID:
			return getSubjectLocatorConstraint_2024Parser();
		case RoleTypeConstraintsCardMinCardMaxEditPart.VISUAL_ID:
			return getRoleTypeConstraintsCardMinCardMax_4007Parser();
		case RoleTypeConstraintsRoleEditPart.VISUAL_ID:
			return getRoleTypeConstraintsCardMin_4008Parser();
		}
		return null;
	}

	/**
	 * @generated
	 */
	public IParser getParser(IAdaptable hint) {
		String vid = (String) hint.getAdapter(String.class);
		if (vid != null) {
			return getParser(TmcleditVisualIDRegistry.getVisualID(vid));
		}
		View view = (View) hint.getAdapter(View.class);
		if (view != null) {
			return getParser(TmcleditVisualIDRegistry.getVisualID(view));
		}
		return null;
	}

	/**
	 * @generated
	 */
	public boolean provides(IOperation operation) {
		if (operation instanceof GetParserOperation) {
			IAdaptable hint = ((GetParserOperation) operation).getHint();
			if (TmcleditElementTypes.getElement(hint) == null) {
				return false;
			}
			return getParser(hint) != null;
		}
		return false;
	}

	/**
	 * @generated
	 */
	public static class HintAdapter extends ParserHintAdapter {

		/**
		 * @generated
		 */
		private final IElementType elementType;

		/**
		 * @generated
		 */
		public HintAdapter(IElementType type, EObject object, String parserHint) {
			super(object, parserHint);
			assert type != null;
			elementType = type;
		}

		/**
		 * @generated
		 */
		public Object getAdapter(Class adapter) {
			if (IElementType.class.equals(adapter)) {
				return elementType;
			}
			return super.getAdapter(adapter);
		}
	}

}
