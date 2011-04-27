package de.topicmapslab.tmcl;

import java.io.File;

import junit.framework.TestCase;

import org.tmapi.core.Topic;

import de.topicmapslab.tmcl.loader.listener.IAssociationTypeConstraintsListener;
import de.topicmapslab.tmcl.loader.listener.INameTypeConstraintsListener;
import de.topicmapslab.tmcl.loader.listener.IOccurrenceTypeConstraintsListener;
import de.topicmapslab.tmcl.loader.listener.IRoleTypeConstraintsListener;
import de.topicmapslab.tmcl.loader.listener.ITopicTypeConstraintsListener;
import de.topicmapslab.tmcl.loader.listener.ITypesListener;
import de.topicmapslab.tmcl.loader.reader.ConstraintReader;
import de.topicmapslab.tmcl.loader.util.ConsoleWorkMonitor;

/**
 * @author Hannes Niederhausen
 *
 */
public class BaseReaderTest extends TestCase implements ITypesListener,
		ITopicTypeConstraintsListener, INameTypeConstraintsListener,
		IOccurrenceTypeConstraintsListener,
		IAssociationTypeConstraintsListener, IRoleTypeConstraintsListener {

	private File file = new File("src/test/resources/scheme.ctm");

	/**
	 * @throws Exception
	 */
	public void testBaseReader() throws Exception {
		ConstraintReader reader = new ConstraintReader(file);
		reader.addEventListener((ITypesListener) this);
		// reader.addEventListener((ITypeConstraintsListener) this);
		reader.parse(new ConsoleWorkMonitor());
	}

	@Override
	public void associationTypeElement(Topic identifier) {
		System.out.println();
		System.out.println("########################");
		System.out.println("### association-type ###");
		System.out.println("########################");
		System.out.println("identifier: " + identifier);
		System.out.println("--------------------");
	}

	@Override
	public void nameTypeElement(Topic identifier) {
		System.out.println();
		System.out.println("#################");
		System.out.println("### name-type ###");
		System.out.println("#################");
		System.out.println("identifier: " + identifier);
		System.out.println("--------------------");
	}

	@Override
	public void occurrenceTypeElement(Topic identifier) {
		System.out.println();
		System.out.println("#######################");
		System.out.println("### occurrence-type ###");
		System.out.println("#######################");
		System.out.println("identifier: " + identifier);
		System.out.println("--------------------");
	}

	@Override
	public void roleTypeElement(Topic identifier) {
		System.out.println();
		System.out.println("#################");
		System.out.println("### role-type ###");
		System.out.println("#################");
		System.out.println("identifier: " + identifier);
		System.out.println("--------------------");
	}

	@Override
	public void topicTypeElement(Topic identifier) {
		System.out.println();
		System.out.println("##################");
		System.out.println("### topic-type ###");
		System.out.println("##################");
		System.out.println("identifier: " + identifier);
		System.out.println("--------------------");
	}
	
	@Override
	public void schemaElement(Topic identifier) {
		System.out.println();
		System.out.println("##################");
		System.out.println("### schema #######");
		System.out.println("##################");
		System.out.println("identifier: " + identifier);
		System.out.println("--------------------");
	}

	@Override
	public void occurrenceDatatypeConstraintElement(Topic type, String datatype) {
		System.out.println("has-datatype");
		System.out.println("type: " + type);
		System.out.println("datatype: " + datatype);
		System.out.println("--------------------");
	}

	@Override
	public void regularExpressionConstraintElement(Topic type, String regexp) {
		System.out.println("matches-regexp");
		System.out.println("type: " + type);
		System.out.println("regexp: " + regexp);
		System.out.println("--------------------");
	}

	@Override
	public void scopeConstraintElement(Topic type, Topic scopeType,
			String cardMin, String cardMax) {
		System.out.println("has-scope");
		System.out.println("type: " + type);
		System.out.println("scopeType: " + scopeType);
		System.out.println("card-min: " + cardMin);
		System.out.println("card-max: " + cardMax);
		System.out.println("--------------------");
	}

	@Override
	public void reifierConstraintElement(Topic type, Topic reifierType,
			String cardMin, String cardMax) {
		System.out.println("reifier-constraint");
		System.out.println("type: " + type);
		System.out.println("reifierType: " + reifierType);
		System.out.println("card-min: " + cardMin);
		System.out.println("card-max: " + cardMax);
		System.out.println("--------------------");
	}

	@Override
	public void topicNameConstraintElement(Topic type, Topic nameType,
			String cardMin, String cardMax) {
		System.out.println("has-name");
		System.out.println("type: " + type);
		System.out.println("nameType: " + nameType);
		System.out.println("card-min: " + cardMin);
		System.out.println("card-max: " + cardMax);
		System.out.println("--------------------");

	}

	@Override
	public void topicOccurrenceConstraintElement(Topic type,
			Topic occurrenceType, String cardMin, String cardMax) {
		System.out.println("has-occurrence");
		System.out.println("type: " + type);
		System.out.println("occurrenceType: " + occurrenceType);
		System.out.println("card-min: " + cardMin);
		System.out.println("card-max: " + cardMax);
		System.out.println("--------------------");
	}

	@Override
	public void subjectIdentifierConstraintElement(Topic type, String cardMin,
			String cardMax, String regExp) {
		System.out.println("has-subject-identifier");
		System.out.println("type: " + type);
		System.out.println("card-min: " + cardMin);
		System.out.println("card-max: " + cardMax);
		System.out.println("regExp: " + regExp);
		System.out.println("--------------------");
	}

	@Override
	public void subjectLocatorConstraintElement(Topic type, String cardMin,
			String cardMax, String regExp) {
		System.out.println("has-subject-locator");
		System.out.println("type: " + type);
		System.out.println("card-min: " + cardMin);
		System.out.println("card-max: " + cardMax);
		System.out.println("regExp: " + regExp);
		System.out.println("--------------------");
	}

	@Override
	public void isAbstractElement(Topic type) {
		System.out.println("is-abstract");
		System.out.println("type: " + type);
		System.out.println("--------------------");
	}

	@Override
	public void uniqueValueConstraintElement(Topic type) {
		System.out.println("is-unique");
		System.out.println("type: " + type);
		System.out.println("--------------------");
	}

	@Override
	public void overlapDeclarationElement(Topic type, Topic otherType) {
		System.out.println("overlaps");
		System.out.println("type: " + type);
		System.out.println("otherType: " + otherType);
		System.out.println("--------------------");
	}

	@Override
	public void topicRoleConstraintElement(Topic type, Topic roleType,
			Topic associationType, String cardMin, String cardMax) {
		System.out.println("plays-role");
		System.out.println("type: " + type);
		System.out.println("roleType: " + roleType);
		System.out.println("associationType: " + associationType);
		System.out.println("card-min: " + cardMin);
		System.out.println("card-max: " + cardMax);
		System.out.println("--------------------");
	}

	@Override
	public void topicReifiesConstraint(Topic type, Topic reifierType,
			String cardMin, String cardMax) {
		System.out.println("reifies-constraint");
		System.out.println("type: " + type);
		System.out.println("reifierType: " + reifierType);
		System.out.println("card-min: " + cardMin);
		System.out.println("card-max: " + cardMax);
		System.out.println("--------------------");
	}

	@Override
	public void associationRoleConstraintElement(Topic type, Topic roleType,
			String cardMin, String cardMax) {
		System.out.println("has-role");
		System.out.println("type: " + type);
		System.out.println("roleType: " + roleType);
		System.out.println("card-min: " + cardMin);
		System.out.println("card-max: " + cardMax);
		System.out.println("--------------------");
	}

	@Override
	public void roleCombinationConstraintElement(Topic type, Topic roleType,
			Topic playerType, Topic otherRoleType, Topic otherPlayerType) {
		System.out.println("role-combination");
		System.out.println("type: " + type);
		System.out.println("roleType: " + roleType);
		System.out.println("playerType: " + playerType);
		System.out.println("otherRoleType: " + otherRoleType);
		System.out.println("otherPlayerType: " + otherPlayerType);
		System.out.println("--------------------");
	}

	@Override
	public void aKindOf(Topic type, Topic supertype) {
		System.out.println("kinf-of");
		System.out.println("type: " + type);
		System.out.println("supertype: " + supertype);
		System.out.println("--------------------");
	}

	@Override
	public void isInstanceOf(Topic type, Topic supertype) {
		System.out.println("is-a");
		System.out.println("type: " + type);
		System.out.println("supertype: " + supertype);
		System.out.println("--------------------");
	}

	@Override
	public void typeName(Topic type, String name) {
		System.out.println("type-name");
		System.out.println("type: " + type);
		System.out.println("name: " + name);
		System.out.println("--------------------");
	}

	@Override
	public void subjectIdentifier(Topic type, String identifier) {
		System.out.println("subject-identifier");
		System.out.println("type: " + type);
		System.out.println("identifier: " + identifier);
		System.out.println("--------------------");
	}

	@Override
	public void subjectLocator(Topic type, String locator) {
		System.out.println("subject-locator");
		System.out.println("type: " + type);
		System.out.println("locator: " + locator);
		System.out.println("--------------------");
	}

	@Override
	public void endOfDocument() {
		System.out.println("### END-OF-DOCUMENT ###");
	}

	@Override
	public void itemIdentififerConstraintElement(Topic type, String cardMin,
			String cardMax, String regExp) {
		System.out.println("has-item-identifier");
		System.out.println("type: " + type);
		System.out.println("card-min: " + cardMin);
		System.out.println("card-max: " + cardMax);
		System.out.println("regExp: " + regExp);
		System.out.println("--------------------");

	}
}
