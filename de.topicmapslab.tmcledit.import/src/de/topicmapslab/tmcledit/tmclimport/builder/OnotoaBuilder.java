/**
 * 
 */
package de.topicmapslab.tmcledit.tmclimport.builder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.tmapi.core.Locator;
import org.tmapi.core.TMAPIException;
import org.tmapi.core.Topic;
import org.tmapi.core.TopicMap;
import org.tmapi.core.TopicMapExistsException;
import org.tmapi.core.TopicMapSystem;
import org.tmapix.io.CTMTopicMapReader;
import org.tmapix.io.XTM10TopicMapReader;
import org.tmapix.io.XTM20TopicMapReader;

import de.topicmapslab.engine.core.TopicMapSystemFactoryImpl;
import de.topicmapslab.engine.inMemory.store.InMemoryTopicMapStore;
import de.topicmapslab.engine.store.TopicMapStoreProperty;
import de.topicmapslab.tmcl.loader.listener.IAssociationTypeConstraintsListener;
import de.topicmapslab.tmcl.loader.listener.INameTypeConstraintsListener;
import de.topicmapslab.tmcl.loader.listener.IOccurrenceTypeConstraintsListener;
import de.topicmapslab.tmcl.loader.listener.IRoleTypeConstraintsListener;
import de.topicmapslab.tmcl.loader.listener.ITopicTypeConstraintsListener;
import de.topicmapslab.tmcl.loader.listener.ITypeConstraintsListener;
import de.topicmapslab.tmcl.loader.listener.ITypesListener;
import de.topicmapslab.tmcl.loader.reader.ConstraintReader;
import de.topicmapslab.tmcledit.model.AssociationType;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.NameType;
import de.topicmapslab.tmcledit.model.NameTypeConstraint;
import de.topicmapslab.tmcledit.model.OccurrenceType;
import de.topicmapslab.tmcledit.model.OccurrenceTypeConstraint;
import de.topicmapslab.tmcledit.model.ReifiableTopicType;
import de.topicmapslab.tmcledit.model.ReifierConstraint;
import de.topicmapslab.tmcledit.model.RoleType;
import de.topicmapslab.tmcledit.model.ScopeConstraint;
import de.topicmapslab.tmcledit.model.ScopedTopicType;
import de.topicmapslab.tmcledit.model.SubjectIdentifierConstraint;
import de.topicmapslab.tmcledit.model.SubjectLocatorConstraint;
import de.topicmapslab.tmcledit.model.TopicReifiesConstraint;
import de.topicmapslab.tmcledit.model.TopicType;

/**
 * @author Hannes Niederhausen
 * 
 */
public class OnotoaBuilder implements ITypesListener, ITopicTypeConstraintsListener, INameTypeConstraintsListener,
        IOccurrenceTypeConstraintsListener, IAssociationTypeConstraintsListener, IRoleTypeConstraintsListener {

	private final String filename;

	private File file;

	private ModelFactory modelFactory;

	// map used to get topic type for a specific topic
	private Map<Topic, TopicType> topicTypeMap = new HashMap<Topic, TopicType>();

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
		modelFactory = ModelFactory.eINSTANCE;
		file = modelFactory.createFile();
		file.setTopicMapSchema(modelFactory.createTopicMapSchema());

		TopicMapSystemFactoryImpl topicMapSystemFactory = new TopicMapSystemFactoryImpl();
		// TODO remove with new default store impl
		if (topicMapSystemFactory instanceof TopicMapSystemFactoryImpl) {
			topicMapSystemFactory.setProperty(TopicMapStoreProperty.TOPICMAPSTORE_CLASS,
			        InMemoryTopicMapStore.class.getName());
		}
		TopicMapSystem system = topicMapSystemFactory.newTopicMapSystem();
		TopicMap topicMap = loadTopicMap(system);

		ConstraintReader reader = new ConstraintReader(topicMap, system);
		reader.addEventListener((ITypesListener) this);
		reader.parse();
		reader.removeListener((ITypesListener) this);

		reader.addEventListener((ITypeConstraintsListener) this);
		reader.parse();
	}

	private TopicMap loadTopicMap(TopicMapSystem system) throws TMAPIException, TopicMapExistsException, IOException {

		TopicMap topicMap = system.createTopicMap("http://onotoa.topicmapslab.de/schema/");

		java.io.File file = new java.io.File(filename);
		/*
		 * load topic map from file
		 */
		if (filename.endsWith(".ctm")) {
			CTMTopicMapReader reader = new CTMTopicMapReader(topicMap, file);
			reader.read();
		} else if (filename.endsWith(".xtm") || filename.endsWith(".xtm20")) {
			XTM20TopicMapReader reader = new XTM20TopicMapReader(topicMap, file);
			reader.read();
		} else if (filename.endsWith(".xtm10")) {
			XTM10TopicMapReader reader = new XTM10TopicMapReader(topicMap, file);
			reader.read();
		}
		return topicMap;
	}

	public void subjectIndicatorConstraintElement(Topic type, String arg1, String arg2, String arg3) {
		SubjectIdentifierConstraint sic = modelFactory.createSubjectIdentifierConstraint();
		sic.setCardMin(arg1);
		sic.setCardMax(arg2);
		sic.setRegexp(arg3);

		getTopicType(type).getSubjectIdentifierConstraints().add(sic);
	}

	public void subjectLocatorConstraintElement(Topic arg0, String arg1, String arg2, String arg3) {
		SubjectLocatorConstraint slc = modelFactory.createSubjectLocatorConstraint();
		slc.setCardMin(arg1);
		slc.setCardMax(arg2);
		slc.setRegexp(arg3);

		getTopicType(arg0).getSubjectLocatorConstraints().add(slc);

	}

	public void aKindOf(Topic arg0, Topic arg1) {
		try {
			TopicType tt1 = getTopicType(arg0);
			TopicType tt2 = getTopicType(arg1);
			tt1.getAko().add(tt2);
		} catch (Exception e) {
			// do nothing cause TMCL types aren't supported
		}
	}

	public void isAbstractElement(Topic arg0) {
		TopicType tt = topicTypeMap.get(arg0);
		tt.setAbstract(true);
	}

	public void isInstanceOf(Topic arg0, Topic arg1) {
		try {
			TopicType tt1 = getTopicType(arg0);
			TopicType tt2 = getTopicType(arg1);
			tt1.getIsa().add(tt2);
		} catch (Exception e) {
			// do nothing cause TMCL types aren't supported
		}
	}

	public void overlapDeclarationElement(Topic arg0, Topic arg1) {
		TopicType tt1 = getTopicType(arg0);
		TopicType tt2 = getTopicType(arg1);

		tt1.getOverlap().add(tt2);
		tt2.getOverlap().add(tt1);
	}

	public void subjectIdentifier(Topic arg0, String arg1) {
		
	}

	public void subjectLocator(Topic arg0, String arg1) {
	}

	public void typeName(Topic arg0, String arg1) {
	}

	public void scopeConstraintElement(Topic type, Topic scopeType, String cardMin, String cardMax) {
		try {
			ScopedTopicType stt = (ScopedTopicType) getTopicType(type);
			
			ScopeConstraint sc = modelFactory.createScopeConstraint();
			sc.setType(getTopicType(scopeType));
			sc.setCardMin(cardMin);
			sc.setCardMax(cardMax);
			
			stt.getScope().add(sc);
		}catch (Exception e) {
			// do nothing if types not found just ignore
		}
	}

	public void reifierConstraintElement(Topic topic, Topic reifier, String cardMin, String cardMax) {
		ReifierConstraint rc = modelFactory.createReifierConstraint();
		rc.setCardMin(cardMin);
		rc.setCardMax(cardMax);

		try {
			rc.setType(getTopicType(reifier));
		} catch (Exception e) {
			// if unknown type exception ignore
		}

		ReifiableTopicType rtt = (ReifiableTopicType) getTopicType(topic);
		rtt.setReifierConstraint(rc);

	}

	public void regularExpressionConstraintElement(Topic arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	public void associationRoleConstraintElement(Topic arg0, Topic arg1, String arg2, String arg3) {
		// TODO Auto-generated method stub

	}

	public void roleCombinationConstraintElement(Topic arg0, Topic arg1, Topic arg2, Topic arg3, Topic arg4) {
		// TODO Auto-generated method stub

	}

	public void occurrenceDatatypeConstraintElement(Topic arg0, String arg1) {
		try {
			OccurrenceType ot = (OccurrenceType) getTopicType(arg0);
			
			if (arg1.startsWith("http://www.w3.org/2001/XMLSchema#")) {
				arg1 = arg1.replace("http://www.w3.org/2001/XMLSchema#", "xsd:");
			}
			
			ot.setDataType(arg1);
			
			
		} catch (Exception e) {
			// if no type exists we simply ignore this
		}

	}

	public void uniqueValueConstraintElement(Topic arg0) {
		// TODO Auto-generated method stub

	}

	public void topicNameConstraintElement(Topic arg0, Topic arg1, String arg2, String arg3) {
		NameTypeConstraint ntc = modelFactory.createNameTypeConstraint();
		ntc.setCardMin(arg2);
		ntc.setCardMax(arg3);
		ntc.setType(topicTypeMap.get(arg1));

		TopicType tt = topicTypeMap.get(arg0);
		tt.getNameConstraints().add(ntc);
	}

	public void topicOccurrenceConstraintElement(Topic arg0, Topic arg1, String arg2, String arg3) {
		OccurrenceTypeConstraint otc = modelFactory.createOccurrenceTypeConstraint();
		otc.setCardMin(arg2);
		otc.setCardMax(arg3);
		otc.setType(topicTypeMap.get(arg1));

		TopicType tt = topicTypeMap.get(arg0);
		tt.getOccurrenceConstraints().add(otc);

	}

	public void topicReifiesConstraint(Topic type, Topic reifiedType, String cardMin, String cardMax) {
		try {
			TopicType tt = getTopicType(type);
			TopicReifiesConstraint tr = modelFactory.createTopicReifiesConstraint();
			tr.setCardMin(cardMin);
			tr.setCardMax(cardMax);
			
			tt.getTopicReifiesConstraints().add(tr);
			
			tr.setType(getTopicType(reifiedType));

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void topicRoleConstraintElement(Topic type, Topic roleType, Topic associationType, String cardMin,
	        String cardMax) {
		// TODO Auto-generated method stub

	}

	public void endOfDocument() {
	}

	public void associationTypeElement(Topic arg0) {
		if (topicTypeMap.containsKey(arg0))
			return;
		AssociationType at = modelFactory.createAssociationType();
		fillData(at, arg0);
	}

	public void nameTypeElement(Topic arg0) {
		if (topicTypeMap.containsKey(arg0))
			return;
		NameType nt = modelFactory.createNameType();
		fillData(nt, arg0);
	}

	public void occurrenceTypeElement(Topic arg0) {
		if (topicTypeMap.containsKey(arg0))
			return;
		OccurrenceType ot = modelFactory.createOccurrenceType();
		fillData(ot, arg0);

	}

	public void roleTypeElement(Topic arg0) {
		if (topicTypeMap.containsKey(arg0))
			return;
		RoleType rt = modelFactory.createRoleType();
		fillData(rt, arg0);
	}

	public void topicTypeElement(Topic arg0) {
		if (topicTypeMap.containsKey(arg0))
			return;
		TopicType tt = modelFactory.createTopicType();
		fillData(tt, arg0);
	}

	private void fillData(TopicType tt, Topic arg0) {
		if (arg0.getNames().size() == 0)
			throw new RuntimeException("Invalid topic - no name given");

		String name = arg0.getNames().iterator().next().getValue();
		tt.setName(name);

		for (Locator loc : arg0.getSubjectIdentifiers()) {
			tt.getIdentifiers().add(loc.toExternalForm());
		}

		topicTypeMap.put(arg0, tt);
		file.getTopicMapSchema().getTopicTypes().add(tt);
	}

	private TopicType getTopicType(Topic arg0) {
		TopicType tt = topicTypeMap.get(arg0);
		if (tt == null) {
			throw new RuntimeException("Unknown type!");
		}
		return tt;
	}

}
