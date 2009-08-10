/**
 * 
 */
package de.topicmapslab.tmcledit.tmclimport.builder;

import static org.tinytim.voc.TMCL.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.tinytim.mio.TopicMapReader;
import org.tinytim.voc.Namespace;
import org.tinytim.voc.TMCL;
import org.tinytim.voc.TMDM;
import org.tmapi.core.Association;
import org.tmapi.core.Locator;
import org.tmapi.core.Name;
import org.tmapi.core.Occurrence;
import org.tmapi.core.Role;
import org.tmapi.core.Topic;
import org.tmapi.core.TopicMap;
import org.tmapi.core.TopicMapSystemFactory;

import de.topicmapslab.tmcledit.model.AbstractCardinalityContraint;
import de.topicmapslab.tmcledit.model.AbstractConstraint;
import de.topicmapslab.tmcledit.model.AssociationType;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.NameTypeConstraint;
import de.topicmapslab.tmcledit.model.OccurrenceType;
import de.topicmapslab.tmcledit.model.OccurrenceTypeConstraint;
import de.topicmapslab.tmcledit.model.ReifiableTopicType;
import de.topicmapslab.tmcledit.model.ReifierConstraint;
import de.topicmapslab.tmcledit.model.RoleConstraint;
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;
import de.topicmapslab.tmcledit.model.ScopeConstraint;
import de.topicmapslab.tmcledit.model.ScopedTopicType;
import de.topicmapslab.tmcledit.model.SubjectIdentifierConstraint;
import de.topicmapslab.tmcledit.model.SubjectLocatorConstraint;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.impl.NameTypeImpl;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;

/**
 * @author Hannes Niederhausen
 * 
 */
public class OnotoaBuilder {

	private final String filename;
	// loaded topic map
	private TopicMap topicMap;

	private File file;

	private ModelFactory modelFactory;

	private int counter = 0;

	Set<Topic> topicCache;
	Map<Topic, TopicType> topicTypeMap;
	Set<RolePlayerConstraintContainer> rpcContainer = new HashSet<RolePlayerConstraintContainer>();

	// some tmdm topics
	private Topic topicName;
	private Topic subject;

	// some tmcl topics

	// -- topic types
	private Topic topicType;
	private Topic associationType;
	private Topic roleType;
	private Topic occurrenceType;
	private Topic nameType;
	private Topic scopeType;

	// -- associations
	private Topic constraintStatement;
	private Topic cardMin;
	private Topic cardMax;
	private Topic constrainedTopicType;
	private Topic constrainedRole;
	private Topic allowedReifier;
	private Topic allowedScope;
	private Topic regExp;
	private Topic otherConstrainedRole;
	private Topic otherConstrainedTopicType;

	// -- roles
	private Topic constrains;
	private Topic constrained;
	private Topic allows;
	private Topic allowed;

	// -- constrains
	private Topic constraint;
	private Topic abstractTopicTypeConstraint;
	private Topic subjectIdentifierConstraint;
	private Topic subjectLocatorConstraint;
	private Topic topicNameConstraint;
	private Topic topicOccurrenceConstraint;
	private Topic rolePlayerConstraint;
	private Topic scopeConstraint;
	private Topic reifierConstraint;
	private Topic topicReifiesConstraint;
	private Topic associationRoleConstraint;
	private Topic roleCombinationConstraint;
	private Topic occurrenceDatatypeConstraint;
	private Topic uniqueValueConstraint;
	private Topic regularExpressionConstraint;

	public OnotoaBuilder(String filename) {
		this.filename = filename;
	}

	public File getFile() {
		if (file == null)
			try {
				createFile();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		return file;
	}

	private void createFile() throws Exception {
		topicMap = TopicMapSystemFactory.newInstance().newTopicMapSystem().createTopicMap("file:/" + filename);
		modelFactory = ModelFactory.eINSTANCE;

		file = modelFactory.createFile();
		file.setTopicMapSchema(modelFactory.createTopicMapSchema());

		ModelIndexer.createInstance(file);
		topicTypeMap = new HashMap<Topic, TopicType>();

		TopicMapreaderFactory readerFac = new TopicMapreaderFactory();
		TopicMapReader reader = readerFac.getReader(new java.io.File(filename), topicMap);

		if (reader != null) {
			reader.read();

			topicCache = new HashSet<Topic>(topicMap.getTopics());

			init();
			createTopicTypes();

			// after creating all topic types and surly removed all
			// topics representing constraint, add topic types to the
			// topicMapSchema
			for (Topic t : topicTypeMap.keySet()) {
				if (topicCache.contains(t)) {
					TopicType tt = topicTypeMap.get(t);
					file.getTopicMapSchema().getTopicTypes().add(tt);
					// TODO remove
					System.out.println("Created Topic Type: " + tt);
					System.out.println("From: " + t);
					System.out.println("Type: ");
					for (Topic type : t.getTypes()) {
						System.out.println("\t-" + type);
					}
					System.out.println("\n\n");
				}
			}
		}
	}

	private KindOfTopicType getKindOfTopicType(Topic t) {
		Set<Topic> types = t.getTypes();

		if (types.contains(scopeType))
			return KindOfTopicType.SCOPE_TYPE;

		if (types.contains(associationType))
			return KindOfTopicType.ASSOCIATION_TYPE;

		if (types.contains(roleType))
			return KindOfTopicType.ROLE_TYPE;

		if (types.contains(occurrenceType))
			return KindOfTopicType.OCCURRENCE_TYPE;

		if (types.contains(nameType))
			return KindOfTopicType.NAME_TYPE;

		if (types.contains(topicType))
			return KindOfTopicType.TOPIC_TYPE;

		return KindOfTopicType.NO_TYPE;
	}

	private void createTopicTypes() {
		for (Topic t : topicMap.getTopics()) {
			if (topicCache.contains(t))
				getTopic(t);
		}
	}

	private TopicType getTopic(Topic t) {
		TopicType tt = topicTypeMap.get(t);

		if (tt != null)
			return tt;

		tt = ModelIndexer.getTopicIndexer().createTopicType(getKindOfTopicType(t));

		if (t.getNames().isEmpty()) {
			tt.setName("default" + counter);
			counter++;
		} else {
			Name first = t.getNames().iterator().next();
			tt.setName(first.getValue());
		}

		for (Locator l : t.getSubjectIdentifiers()) {
			tt.getIdentifiers().add(l.toExternalForm());
		}

		for (Locator l : t.getSubjectLocators()) {
			tt.getLocators().add(l.toExternalForm());
		}

		topicTypeMap.put(t, tt);

		addConstraints(t, tt);
		return tt;
	}

	private void init() {

		// removing TMDM types from cache
		createStandardTopic(TMDM.TYPE);
		createStandardTopic(TMDM.INSTANCE);
		createStandardTopic(TMDM.TYPE_INSTANCE);
		createStandardTopic(TMDM.SUPERTYPE);
		createStandardTopic(TMDM.SUBTYPE);
		createStandardTopic(TMDM.SUPERTYPE_SUBTYPE);

		// initialize tmdm topics
		topicName = createStandardTopic(TMDM.TOPIC_NAME);
		subject = createStandardTopic(topicMap.createLocator(Namespace.TMDM_MODEL + "subject"));

		topicName = createStandardTopic(TMDM.TOPIC_NAME);

		// init topic types
		topicType = createStandardTopic(TOPIC_TYPE);
		nameType = createStandardTopic(NAME_TYPE);
		associationType = createStandardTopic(ASSOCIATION_TYPE);
		roleType = createStandardTopic(ROLE_TYPE);
		occurrenceType = createStandardTopic(OCCURRENCE_TYPE);
		scopeType = createStandardTopic(SCOPE_TYPE);

		cardMin = createStandardTopic(CARD_MIN);
		cardMax = createStandardTopic(CARD_MAX);
		regExp = createStandardTopic(REGEXP);

		// roles
		constrains = createStandardTopic(CONSTRAINS);
		constrained = createStandardTopic(CONSTRAINED);
		allowed = createStandardTopic(ALLOWED);
		allows = createStandardTopic(ALLOWS);

		// associations
		constraintStatement = createStandardTopic(CONSTRAINED_STATEMENT);
		constrainedTopicType = createStandardTopic(CONSTRAINED_TOPIC_TYPE);
		constrainedRole = createStandardTopic(CONSTRAINED_ROLE);
		otherConstrainedTopicType = createStandardTopic(OTHER_CONSTRAINED_TOPIC_TYPE);
		otherConstrainedRole = createStandardTopic(OTHER_CONSTRAINED_ROLE);
		allowedReifier = createStandardTopic(ALLOWED_REIFIER);
		allowedScope = createStandardTopic(ALLOWED_SCOPE);

		constrainedRole = createStandardTopic(CONSTRAINED_ROLE);
		otherConstrainedRole = createStandardTopic(OTHER_CONSTRAINED_ROLE);
		otherConstrainedTopicType = createStandardTopic(OTHER_CONSTRAINED_TOPIC_TYPE);

		constraint = createStandardTopic(CONSTRAINT);
		abstractTopicTypeConstraint = createStandardTopic(ABSTRACT_TOPIC_TYPE_CONSTRAINT);
		subjectIdentifierConstraint = createStandardTopic(SUBJECT_IDENTIFIER_CONSTRAINT);
		subjectLocatorConstraint = createStandardTopic(SUBJECT_LOCATOR_CONSTRAINT);
		topicNameConstraint = createStandardTopic(TOPIC_NAME_CONSTRAINT);
		topicOccurrenceConstraint = createStandardTopic(TOPIC_OCCURRENCE_CONSTRAINT);
		rolePlayerConstraint = createStandardTopic(ROLE_PLAYER_CONSTRAINT);
		scopeConstraint = createStandardTopic(SCOPE_CONSTRAINT);
		reifierConstraint = createStandardTopic(REIFIER_CONSTRAINT);
		topicReifiesConstraint = createStandardTopic(TOPIC_REIFIES_CONSTRAINT);
		associationRoleConstraint = createStandardTopic(ASSOCIATION_ROLE_CONSTRAINT);
		roleCombinationConstraint = createStandardTopic(ROLE_COMBINATION_CONSTRAINT);
		occurrenceDatatypeConstraint = createStandardTopic(OCCURRENCE_DATATYPE_CONSTRAINT);
		uniqueValueConstraint = createStandardTopic(UNIQUE_VALUE_CONSTRAINT);
		regularExpressionConstraint = createStandardTopic(REGULAR_EXPRESSION_CONSTRAINT);
	}

	private Topic createStandardTopic(Locator loc) {
		Topic t = topicMap.createTopicBySubjectIdentifier(loc);
		topicCache.remove(t);
		return t;
	}

	private void addConstraints(Topic t, TopicType tt) {
		for (Role role : t.getRolesPlayed(constrained, constrainedTopicType)) {
			Role constrRole = getConstrainRole(role);
			if (role != null) {
				Topic constr = constrRole.getPlayer();
				Topic otherPlayer = getConstrainedPlayer(constr, constraintStatement);

				TopicType tt2 = null;
				if (otherPlayer != null) {
					if (constr.getTypes().contains(topicNameConstraint)) {
						if (!topicName.equals(otherPlayer))
							tt2 = getTopic(otherPlayer);
						createNameConstraint(tt, tt2, constr);
					} else if (constr.getTypes().contains(topicOccurrenceConstraint)) {
						if (!subject.equals(otherPlayer))
							tt2 = getTopic(otherPlayer);
						createOccurrenceConstraint(tt, tt2, constr);
					} else if (constr.getTypes().contains(rolePlayerConstraint)) {
						tt2 = getTopic(otherPlayer);
						createRolePlayerConstraint(tt, tt2, constr);
					}
				} else {
					if (constr.getTypes().contains(abstractTopicTypeConstraint)) {
						tt.setAbstract(true);
					} else if (constr.getTypes().contains(subjectIdentifierConstraint)) {
						createSubjectIdentifierConstraint(tt, constr);
					} else if (constr.getTypes().contains(subjectLocatorConstraint)) {
						createSubjectLocatorConstraint(tt, constr);
					}
				}
				topicCache.remove(constr);
			}
		}
		for (Role role : t.getRolesPlayed(constrained, constraintStatement)) {
			Role cRole = getConstrainRole(role);
			if (cRole != null) {
				Topic constr = cRole.getPlayer();
				if (constr.getTypes().contains(uniqueValueConstraint)) {
					processUniqueValue(tt);
				} else if (constr.getTypes().contains(scopeConstraint)) {
					processScopeConstraint(tt, constr);
				} else if (constr.getTypes().contains(reifierConstraint)) {
					processReifierConstraint(tt, constr);
				} else if (constr.getTypes().contains(associationRoleConstraint)) {
					processAssociationRoleConstraint(tt, constr);
				} else if (constr.getTypes().contains(regularExpressionConstraint)) {
					processRegularExpressionConstraint(constr);
				}

				topicCache.remove(constr);
			}
		}

	}

	private void processRegularExpressionConstraint(Topic constr) {
	    // TODO Auto-generated method stub
	    
    }

	private void processAssociationRoleConstraint(TopicType tt, Topic constr) {
		Topic role = getConstrainedPlayer(constr, constrainedRole);
		if (role!=null) {
			createAssociationConstraint(tt, getTopic(role), constr);
		}
			

	}
	
	
	private void createAssociationConstraint(TopicType at, TopicType roleType, Topic constr) {
	    RoleConstraint rc = ModelFactory.eINSTANCE.createRoleConstraint();
	    rc.setType(roleType);
	    setCardinality(constr, rc);
	    
	    ((AssociationType)at).getRoles().add(rc);
	    
    }

	private Topic getConstrainedPlayer(Topic constr, Topic assocType) {
		Set<Role> rolesPlayed = constr.getRolesPlayed(constrains, assocType);
		if (rolesPlayed.size() == 1) {
			Role r2 = rolesPlayed.iterator().next();
			return ((Role) r2.getParent().getRoles(constrained).iterator().next()).getPlayer();
		}
		return null;
	}
	

	private Topic getOtherPlayer(Topic constr, Topic constrRole, Topic associationType, Topic otherRole) {
		Set<Role> cRoles = constr.getRolesPlayed(constrRole, associationType);
		if (cRoles.size() > 0) {
			Association assoc = cRoles.iterator().next().getParent();
			Set<Role> oRoles = assoc.getRoles(otherRole);
			if (oRoles.size() > 0) {
				return oRoles.iterator().next().getPlayer();
			}
		}
		return null;
	}

	private void processScopeConstraint(TopicType tt, Topic constr) {
		if (tt instanceof ScopedTopicType) {
			TopicType scopetype = getTopic(getOtherPlayer(constr, allows, allowedScope, allowed));
			if (scopetype != null)
				createScopeConstraint(tt, scopetype, constr);
		}
	}

	private void processReifierConstraint(TopicType tt, Topic constr) {
		if (tt instanceof ReifiableTopicType) {
			TopicType reifiertype = null;
			Topic reifier = getOtherPlayer(constr, allows, allowedReifier, allowed);
			if (!subject.equals(reifier))
				reifiertype = getTopic(reifier);
			if (reifiertype != null)
				createReifierConstraint(tt, reifiertype, constr);
		}
	}

	private void createReifierConstraint(TopicType tt, TopicType reifier, Topic constr) {
		ReifierConstraint rc = ModelFactory.eINSTANCE.createReifierConstraint();
		rc.setType(reifier);
		setCardinality(constr, rc);

		((ReifiableTopicType) tt).setReifierConstraint(rc);
	}

	private void processUniqueValue(TopicType tt) {
		if (tt.getKind() == KindOfTopicType.OCCURRENCE_TYPE) {
			OccurrenceType ot = (OccurrenceType) tt;
			ot.setUnique(true);
		}
	}

	private void createScopeConstraint(TopicType tt, TopicType scopeType, Topic constr) {
		ScopeConstraint sc = ModelFactory.eINSTANCE.createScopeConstraint();

		sc.setType(scopeType);
		setCardinality(constr, sc);
		((ScopedTopicType) tt).getScope().add(sc);
	}

	private void createRolePlayerConstraint(TopicType tt, TopicType at, Topic constr) {
		Set<Role> cRoles = constr.getRolesPlayed(constrains, constrainedRole);
		if (cRoles.size() > 0) {
			Association assoc = cRoles.iterator().next().getParent();
			Set<Role> rRoles = assoc.getRoles(constrained);
			if (rRoles.size() > 0) {
				Topic rPlayer = rRoles.iterator().next().getPlayer();

				TopicType roleType = getTopic(rPlayer);

				RolePlayerConstraintContainer rpcc = new RolePlayerConstraintContainer();
				rpcc.setPlayer(tt);
				rpcc.setRoleType(roleType);
				rpcc.setAssocType(at);

				setCardinality(constr, rpcc);
				rpcContainer.add(rpcc);
				// TODO transform rpccontainer to real rpc
			}
		}

	}

	private void createSubjectLocatorConstraint(TopicType tt, Topic constr) {
		SubjectIdentifierConstraint sic = ModelFactory.eINSTANCE.createSubjectIdentifierConstraint();

		setRegularExpression(constr, sic);
		setCardinality(constr, sic);

		tt.getSubjectIdentifierConstraints().add(sic);
	}

	private void createSubjectIdentifierConstraint(TopicType tt, Topic constr) {
		SubjectLocatorConstraint slc = ModelFactory.eINSTANCE.createSubjectLocatorConstraint();

		setRegularExpression(constr, slc);

		setCardinality(constr, slc);

		tt.getSubjectLocatorConstraint().add(slc);
	}

	private void setRegularExpression(Topic constr, AbstractConstraint ac) {
		Set<Occurrence> occurrences = constr.getOccurrences(regExp);
		if (occurrences.size() > 0)
			ac.setRegexp(occurrences.iterator().next().getValue());
	}

	private Role getConstrainRole(Role role) {
		Association assoc = role.getParent();
		Set<Role> roles = assoc.getRoles(constrains);
		if (roles.size() == 1)
			return roles.iterator().next();

		return null;
	}

	private void createOccurrenceConstraint(TopicType tt, TopicType nt, Topic constr) {
		OccurrenceTypeConstraint otc = ModelFactory.eINSTANCE.createOccurrenceTypeConstraint();
		otc.setType(nt);
		setCardinality(constr, otc);

		tt.getOccurrenceConstraints().add(otc);
	}

	private void createNameConstraint(TopicType tt, TopicType nt, Topic constr) {
		NameTypeConstraint ntc = ModelFactory.eINSTANCE.createNameTypeConstraint();
		ntc.setType(nt);
		setCardinality(constr, ntc);
		tt.getNameContraints().add(ntc);
	}

	private void setCardinality(Topic constr, AbstractCardinalityContraint cc) {
		Set<Occurrence> occurrences = constr.getOccurrences(cardMin);
		if (occurrences.size() > 0)
			cc.setCardMin(occurrences.iterator().next().getValue());

		occurrences = constr.getOccurrences(cardMax);
		if (occurrences.size() > 0)
			cc.setCardMax(occurrences.iterator().next().getValue());
	}

	private class RolePlayerConstraintContainer implements AbstractCardinalityContraint {

		private TopicType player;
		private TopicType assocType;
		private TopicType roleType;

		private String cardMin;
		private String cardMax;

		public TopicType getPlayer() {
			return player;
		}

		public void setPlayer(TopicType player) {
			this.player = player;
		}

		public TopicType getAssocType() {
			return assocType;
		}

		public void setAssocType(TopicType assocType) {
			this.assocType = assocType;
		}

		public TopicType getRoleType() {
			return roleType;
		}

		public void setRoleType(TopicType roleType) {
			this.roleType = roleType;
		}

		public String getCardMin() {
			return cardMin;
		}

		public void setCardMin(String cardMin) {
			this.cardMin = cardMin;
		}

		public String getCardMax() {
			return cardMax;
		}

		public void setCardMax(String cardMax) {
			this.cardMax = cardMax;
		}

		public String getComment() {
			return null;
		}

		public String getDescription() {
			return null;
		}

		public EMap<String, String> getExtension() {
			return null;
		}

		public String getSee_also() {
			return null;
		}

		public void setComment(String value) {

		}

		public void setDescription(String value) {

		}

		public void setSee_also(String value) {

		}

		public TreeIterator<EObject> eAllContents() {
			return null;
		}

		public EClass eClass() {
			return null;
		}

		public EObject eContainer() {
			return null;
		}

		public EStructuralFeature eContainingFeature() {
			return null;
		}

		public EReference eContainmentFeature() {
			return null;
		}

		public EList<EObject> eContents() {
			return null;
		}

		public EList<EObject> eCrossReferences() {
			return null;
		}

		public Object eGet(EStructuralFeature feature) {
			return null;
		}

		public Object eGet(EStructuralFeature feature, boolean resolve) {
			return null;
		}

		public boolean eIsProxy() {
			return false;
		}

		public boolean eIsSet(EStructuralFeature feature) {
			return false;
		}

		public Resource eResource() {
			return null;
		}

		public void eSet(EStructuralFeature feature, Object newValue) {
		}

		public void eUnset(EStructuralFeature feature) {
		}

		public EList<Adapter> eAdapters() {
			return null;
		}

		public boolean eDeliver() {
			return false;
		}

		public void eNotify(Notification notification) {

		}

		public void eSetDeliver(boolean deliver) {
		}

	}
}
