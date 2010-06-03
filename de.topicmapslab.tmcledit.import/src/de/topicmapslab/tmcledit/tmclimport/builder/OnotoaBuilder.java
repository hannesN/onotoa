/**
 * 
 */
package de.topicmapslab.tmcledit.tmclimport.builder;


import static de.topicmapslab.tmcledit.tmclimport.voc.Namespaces.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.tmapi.core.Association;
import org.tmapi.core.Locator;
import org.tmapi.core.Name;
import org.tmapi.core.Occurrence;
import org.tmapi.core.Role;
import org.tmapi.core.Topic;
import org.tmapi.core.TopicMap;
import org.tmapi.core.TopicMapSystemFactory;
import org.tmapi.index.TypeInstanceIndex;
import org.tmapix.io.TopicMapReader;

import de.topicmapslab.tmcledit.model.AbstractCardinalityConstraint;
import de.topicmapslab.tmcledit.model.AbstractRegExpConstraint;
import de.topicmapslab.tmcledit.model.AbstractRegExpTopicType;
import de.topicmapslab.tmcledit.model.Annotation;
import de.topicmapslab.tmcledit.model.AssociationType;
import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.NameTypeConstraint;
import de.topicmapslab.tmcledit.model.OccurrenceType;
import de.topicmapslab.tmcledit.model.OccurrenceTypeConstraint;
import de.topicmapslab.tmcledit.model.ReifiableTopicType;
import de.topicmapslab.tmcledit.model.ReifierConstraint;
import de.topicmapslab.tmcledit.model.RoleCombinationConstraint;
import de.topicmapslab.tmcledit.model.RoleConstraint;
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;
import de.topicmapslab.tmcledit.model.ScopeConstraint;
import de.topicmapslab.tmcledit.model.ScopedTopicType;
import de.topicmapslab.tmcledit.model.SubjectIdentifierConstraint;
import de.topicmapslab.tmcledit.model.SubjectLocatorConstraint;
import de.topicmapslab.tmcledit.model.TMCLConstruct;
import de.topicmapslab.tmcledit.model.TopicReifiesConstraint;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;
import de.topicmapslab.tmcledit.tmclimport.Activator;

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

	// some tmdm topicsov
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
	
	private Topic topicMapSchema;

	// -- associations
	private Topic constraintStatement;
	private Topic constrainedTopicType;
	private Topic constrainedRole;
	private Topic allowedReifier;
	private Topic allowedScope;
	private Topic otherConstrainedRole;
	private Topic otherConstrainedTopicType;
//	private Topic overlaps;
	private Topic belongsTo;

	// -- roles
	private Topic constrains;
	private Topic constrained;
	private Topic allows;
	private Topic allowed;
	

	// -- constrains
	//private Topic constraint; // -- not needed is super type
	private Topic abstractConstraint;
	private Topic subjectIdentifierConstraint;
	private Topic subjectLocatorConstraint;
	private Topic topicNameConstraint;
	private Topic topicOccurrenceConstraint;
	private Topic topicRoleConstraint;
	private Topic scopeConstraint;
	private Topic reifierConstraint;
	private Topic topicReifiesConstraint;
	private Topic associationRoleConstraint;
	private Topic roleCombinationConstraint;
	private Topic occurrenceDatatypeConstraint;
	private Topic uniqueValueConstraint;
	private Topic regularExpressionConstraint;
	private Topic overlapDeclaration;
	
	// -- occurrence types
	private Topic datatype;
	private Topic cardMin;
	private Topic cardMax;
	private Topic regExp;
	private Topic comment;
	private Topic seeAlso;
	private Topic description;

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
			removeSchemaInformation();
			createTopicTypes();

			
			
			// after creating all topic types and surly removed all
			// topics representing constraint, add topic types to the
			// topicMapSchema
			for (Topic t : topicTypeMap.keySet()) {
				if (topicCache.contains(t)) {
					TopicType tt = topicTypeMap.get(t);
					file.getTopicMapSchema().getTopicTypes().add(tt);
				}
			}
			
			createAssociationConstrains();
		}
	}

	private void removeSchemaInformation() {
	    TypeInstanceIndex idx = topicMap.getIndex(TypeInstanceIndex.class);
	    for (Association assoc : idx.getAssociations(belongsTo)) {
	    	assoc.remove();
	    }
	    for (Topic t : idx.getTopics(topicMapSchema)) {
	    	t.remove();
	    }
    }

	private void createAssociationConstrains() {
	    // now create all association constrains
	    for(RolePlayerConstraintContainer rpcc : rpcContainer) {
	    	
	    	AssociationType at = (AssociationType) rpcc.getAssocType();
	    	
	    	AssociationTypeConstraint atc = getAssociationTypeConstraint(at);

	    	RoleConstraint roleConstr = null;
	    	for (RoleConstraint rc : at.getRoles()) {
	    		if (rc.getType().equals(rpcc.getRoleType()))
	    			roleConstr = rc;
	    	}
	    	
	    	if (roleConstr==null) {
	    		Activator.getDefault().logInfo("Found no role constraint for role player");
	    		continue; // something is wrong
	    	}
	    	
	    	RolePlayerConstraint rpc = modelFactory.createRolePlayerConstraint();
	    	rpc.setCardMin(rpcc.getCardMin());
	    	rpc.setCardMax(rpcc.getCardMax());
	    	rpc.setComment(rpcc.getComment());
	    	rpc.setPlayer(rpcc.getPlayer());
	    	rpc.setRole(roleConstr);
	    	
	    	atc.getPlayerConstraints().add(rpc);			
	    	
	    	EList<AssociationTypeConstraint> atcList = file.getTopicMapSchema().getAssociationTypeConstraints();
	    	if (!atcList.contains(atc))
	    		atcList.add(atc);
	    	
	    }
    }
	
	private AssociationTypeConstraint getAssociationTypeConstraint(AssociationType at) {
		for (AssociationTypeConstraint atc : file.getTopicMapSchema().getAssociationTypeConstraints()) {
			if (at.equals(atc.getType())) {
				return atc;
			}
		}
		
		AssociationTypeConstraint atc = modelFactory.createAssociationTypeConstraint();
		atc.setType(at);
		return atc;
	}

	private KindOfTopicType getKindOfTopicType(Topic t) {
		Set<Topic> types = t.getTypes();

		if (types.contains(scopeType))
			return KindOfTopicType.TOPIC_TYPE;

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

		TypeInstanceIndex tii = topicMap.getIndex(TypeInstanceIndex.class);

		for (Topic constr : tii.getTopics(overlapDeclaration)) {
			List<TopicType> typeList = new ArrayList<TopicType>();
			for (Role constrR : constr.getRolesPlayed(allows)) {
				Association a = constrR.getParent();
				Role role = a.getRoles(allowed).iterator().next();
				typeList.add(getTopic(role.getPlayer()));
			}

			for (int i = 0; i < typeList.size(); i++) {
				TopicType currType = typeList.get(i);
				for (int j = i + 1; j < typeList.size(); j++) {
					typeList.get(j).getOverlap().add(currType);
					currType.getOverlap().add(typeList.get(j));
				}
			}

			topicCache.remove(constr);

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
		setDocumentation(tt, t);
		addConstraints(t, tt);
		
		for (Topic topic2 : t.getTypes()) {
			TopicType tt2 = getTopic(topic2);
			tt.getIsa().add(tt2);
		}
		// get supertype-subtype
		for (Role r : t.getRolesPlayed(createStandardTopic(TMDM.SUBTYPE))) {
			for (Role r2 : r.getParent().getRoles(createStandardTopic(TMDM.SUPERTYPE))) {
				TopicType supertype = getTopic(r2.getPlayer());
				tt.getAko().add(supertype);
			}
		}
		
		return tt;
	}

	@SuppressWarnings("deprecation")
    private void init() {

		// removing TMDM types from cache
		createStandardTopic(TMDM.TYPE);
		createStandardTopic(TMDM.INSTANCE);
		createStandardTopic(TMDM.TYPE_INSTANCE);
		createStandardTopic(TMDM.SUPERTYPE);
		createStandardTopic(TMDM.SUBTYPE);
		createStandardTopic(TMDM.SUPERTYPE_SUBTYPE);
		// removing TMCL roles from cache
		createStandardTopic(TMCL.CONTAINEE);
		createStandardTopic(TMCL.CONTAINER);
		
		// initialize tmdm topics
		topicName = createStandardTopic(TMDM.TOPIC_NAME);
		subject = createStandardTopic(TMDM.SUBJECT);

		topicName = createStandardTopic(TMDM.TOPIC_NAME);

		topicMapSchema = createStandardTopic(TMCL.SCHEMA);
		belongsTo = createStandardTopic(TMCL.BELONGS_TO);

		// init topic types
		topicType = createStandardTopic(TMCL.TOPIC_TYPE);
		nameType = createStandardTopic(TMCL.NAME_TYPE);
		associationType = createStandardTopic(TMCL.ASSOCIATION_TYPE);
		roleType = createStandardTopic(TMCL.ROLE_TYPE);
		occurrenceType = createStandardTopic(TMCL.OCCURRENCE_TYPE);

		cardMin = createStandardTopic(TMCL.CARD_MIN);
		cardMax = createStandardTopic(TMCL.CARD_MAX);
		regExp = createStandardTopic(TMCL.REGEXP);
		datatype = createStandardTopic(TMCL.DATATYPE);
		comment = createStandardTopic(TMCL.COMMENT);
		description = createStandardTopic(TMCL.DESCRIPTION);
		seeAlso = createStandardTopic(TMCL.SEE_ALSO);

		// roles
		constrains = createStandardTopic(TMCL.CONSTRAINT);
		constrained = createStandardTopic(TMCL.CONSTRAINED);
		allowed = createStandardTopic(TMCL.ALLOWED);
		allows = createStandardTopic(TMCL.ALLOWS);

		// associations
		constraintStatement = createStandardTopic(TMCL.CONSTRAINED_STATEMENT);
		constrainedTopicType = createStandardTopic(TMCL.CONSTRAINED_TOPIC_TYPE);
		constrainedRole = createStandardTopic(TMCL.CONSTRAINED_ROLE);
		otherConstrainedTopicType = createStandardTopic(TMCL.OTHER_CONSTRAINED_TOPIC_TYPE);
		otherConstrainedRole = createStandardTopic(TMCL.OTHER_CONSTRAINED_ROLE);
		allowedReifier = createStandardTopic(TMCL.ALLOWED_REIFIER);
		constrainedRole = createStandardTopic(TMCL.CONSTRAINED_ROLE);
		otherConstrainedRole = createStandardTopic(TMCL.OTHER_CONSTRAINED_ROLE);
		otherConstrainedTopicType = createStandardTopic(TMCL.OTHER_CONSTRAINED_TOPIC_TYPE);
//		overlaps = 
			createStandardTopic(TMCL.OVERLAP_DECLARATION);
			createStandardTopic(TMCL.OVERLAPS);

//		constraint = createStandardTopic(TMCL.CONSTRAINT);
		abstractConstraint = createStandardTopic(TMCL.ABSTRACT_CONSTRAINT);
		subjectIdentifierConstraint = createStandardTopic(TMCL.SUBJECT_IDENTIFIER_CONSTRAINT);
		subjectLocatorConstraint = createStandardTopic(TMCL.SUBJECT_LOCATOR_CONSTRAINT);
		topicNameConstraint = createStandardTopic(TMCL.TOPIC_NAME_CONSTRAINT);
		topicOccurrenceConstraint = createStandardTopic(TMCL.TOPIC_OCCURRENCE_CONSTRAINT);
		topicRoleConstraint = createStandardTopic(TMCL.TOPIC_ROLE_CONSTRAINT);
		scopeConstraint = createStandardTopic(TMCL.SCOPE_CONSTRAINT);
		reifierConstraint = createStandardTopic(TMCL.REIFIER_CONSTRAINT);
		topicReifiesConstraint = createStandardTopic(TMCL.TOPIC_REIFIES_CONSTRAINT);
		associationRoleConstraint = createStandardTopic(TMCL.ASSOCIATION_ROLE_CONSTRAINT);
		roleCombinationConstraint = createStandardTopic(TMCL.ROLE_COMBINATION_CONSTRAINT);
		occurrenceDatatypeConstraint = createStandardTopic(TMCL.OCCURRENCE_DATATYPE_CONSTRAINT);
		uniqueValueConstraint = createStandardTopic(TMCL.UNIQUE_VALUE_CONSTRAINT);
		regularExpressionConstraint = createStandardTopic(TMCL.REGULAR_EXPRESSION_CONSTRAINT);
		overlapDeclaration = createStandardTopic(TMCL.OVERLAP_DECLARATION);
	}

	private Topic createStandardTopic(String si) {
		Topic t = topicMap.createTopicBySubjectIdentifier(topicMap.createLocator(si));
		topicCache.remove(t);
		return t;
	}
	
	

	private void addConstraints(Topic t, TopicType tt) {
		for (Role role : t.getRolesPlayed(constrained, constrainedTopicType)) {
			Role constrRole = getConstrainRole(role);
			if (role != null) {
				Topic constr = constrRole.getPlayer();
				
				if (constr.getTypes().contains(topicReifiesConstraint))
					processTopicReifiesConstraint(tt, constr);
				
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
					} else if (constr.getTypes().contains(topicRoleConstraint)) {
						tt2 = getTopic(otherPlayer);
						createRolePlayerConstraint(tt, tt2, constr);
					} else if (constr.getTypes().contains(roleCombinationConstraint)) {
						tt2 = getTopic(otherPlayer);
						createRoleCombinationConstraint(tt, tt2, constr);
					}
				} else {
					if (constr.getTypes().contains(abstractConstraint)) {
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
					processRegularExpressionConstraint(tt, constr);
				} else if (constr.getTypes().contains(occurrenceDatatypeConstraint)) {
					processOccurrenceDatatypeConstraint(tt, constr);
				}

				topicCache.remove(constr);
			}
		}
		
	}

	private void processTopicReifiesConstraint(TopicType tt, Topic constr) {
	    
	    TopicReifiesConstraint trc = modelFactory.createTopicReifiesConstraint();
	    setDocumentation(trc, constr);
	    setCardinality(constr, trc);
	    
	    Topic t = getConstrainedPlayer(constr, constraintStatement);
	    if ( (t!=null) && (t!=subject) )
	    	trc.setType(getTopic(t));
	    tt.getTopicReifiesConstraints().add(trc);
    }

	private void createRoleCombinationConstraint(TopicType tt, TopicType at, Topic constr) {
		Topic roleType = getOtherPlayer(constr, constrains, constrainedRole, constrained);
		Topic otherRoleType = getOtherPlayer(constr, constrains, otherConstrainedRole, constrained);
		
		Topic otherTopicType = getOtherPlayer(constr, constrains, otherConstrainedTopicType, constrained);
	    
		RoleCombinationConstraint rcc = modelFactory.createRoleCombinationConstraint();
		rcc.setPlayer(tt);
		rcc.setRole(getTopic(roleType));
		rcc.setOtherPlayer(getTopic(otherTopicType));
		rcc.setOtherRole(getTopic(otherRoleType));
		
		setDocumentation(rcc, constr);
		((AssociationType)at).getRoleCombinations().add(rcc);
    }

	private void processOccurrenceDatatypeConstraint(TopicType tt, Topic constr) {
		if (tt instanceof OccurrenceType) {
			String dt = getOccurrenceValue(constr, datatype);
			((OccurrenceType)tt).setDataType(dt);
		}
    }

	private void processRegularExpressionConstraint(TopicType tt, Topic constr) {
		if (tt instanceof AbstractRegExpTopicType) {
			String regexp = getOccurrenceValue(constr, regExp);
			((AbstractRegExpTopicType)tt).setRegExp(regexp);
		}
		
    }
	
	private String getOccurrenceValue(Topic topic, Topic occType) {
		Set<Occurrence> occurrences = topic.getOccurrences(occType);
		if (occurrences.size()==0)
			return null;
		
		return occurrences.iterator().next().getValue();
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
			Topic otherPlayer = getOtherPlayer(constr, allows, allowedScope, allowed);
			TopicType scopetype = null;
			if (!otherPlayer.equals(subject))
				scopetype = getTopic(otherPlayer);
			
			createScopeConstraint(tt, scopetype, constr);
		}
	}

	private void processReifierConstraint(TopicType tt, Topic constr) {
		if (tt instanceof ReifiableTopicType) {
			TopicType reifiertype = null;
			Topic reifier = getOtherPlayer(constr, allows, allowedReifier, allowed);
			if (!subject.equals(reifier))
				reifiertype = getTopic(reifier);
			
			createReifierConstraint(tt, reifiertype, constr);
		}
	}

	private void createReifierConstraint(TopicType tt, TopicType reifier, Topic constr) {
		ReifierConstraint rc = ModelFactory.eINSTANCE.createReifierConstraint();
		rc.setType(reifier);
		setCardinality(constr, rc);
		setDocumentation(rc, constr);
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
		Topic role = getOtherPlayer(constr, constrains, constrainedRole, constrained);
		
		TopicType roleType = getTopic(role);

		RolePlayerConstraintContainer rpcc = new RolePlayerConstraintContainer();
		rpcc.setPlayer(tt);
		rpcc.setRoleType(roleType);
		rpcc.setAssocType(at);

		
		setCardinality(constr, rpcc);
		setDocumentation(rpcc, constr);
		rpcContainer.add(rpcc);

	}

	private void createSubjectLocatorConstraint(TopicType tt, Topic constr) {
		SubjectIdentifierConstraint sic = ModelFactory.eINSTANCE.createSubjectIdentifierConstraint();

		setRegularExpression(constr, sic);
		setCardinality(constr, sic);
		setDocumentation(sic, constr);

		tt.getSubjectIdentifierConstraints().add(sic);
	}

	private void createSubjectIdentifierConstraint(TopicType tt, Topic constr) {
		SubjectLocatorConstraint slc = ModelFactory.eINSTANCE.createSubjectLocatorConstraint();

		setRegularExpression(constr, slc);

		setCardinality(constr, slc);
		setDocumentation(slc, constr);
		tt.getSubjectLocatorConstraints().add(slc);
	}

	private void setRegularExpression(Topic constr, AbstractRegExpConstraint ac) {
		String value = getOccurrenceValue(constr, regExp);
		ac.setRegexp(value);
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
		setDocumentation(otc, constr);
		tt.getOccurrenceConstraints().add(otc);
	}

	private void createNameConstraint(TopicType tt, TopicType nt, Topic constr) {
		NameTypeConstraint ntc = ModelFactory.eINSTANCE.createNameTypeConstraint();
		ntc.setType(nt);
		setCardinality(constr, ntc);
		setDocumentation(ntc, constr);
		tt.getNameConstraints().add(ntc);
	}

	private void setCardinality(Topic constr, AbstractCardinalityConstraint cc) {
		Set<Occurrence> occurrences = constr.getOccurrences(cardMin);
		if (occurrences.size() > 0)
			cc.setCardMin(occurrences.iterator().next().getValue());

		occurrences = constr.getOccurrences(cardMax);
		if (occurrences.size() > 0)
			cc.setCardMax(occurrences.iterator().next().getValue());
	}
	
	private void setDocumentation(TMCLConstruct construct, Topic constr) {
		String comment = getOccurrenceValue(constr, this.comment);
		if (comment!=null)
			construct.setComment(comment);
		
		String seeAlso = getOccurrenceValue(constr, this.seeAlso);
		if (seeAlso!=null)
			construct.setSee_also(seeAlso);
		
		String description = getOccurrenceValue(constr, this.description);
		if (description!=null)
			construct.setDescription(description);
	}

	private class RolePlayerConstraintContainer implements AbstractCardinalityConstraint {

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

		public EList<Annotation> getAnnotations() {
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

		public int getId() {
	        return 0;
        }

		public void setId(int value) {
        }

		@SuppressWarnings("unused")
		// needed in new emf version
        public Object eInvoke(EOperation operation, EList<?> arguments) throws InvocationTargetException {
	        return null;
        }

	}
}
