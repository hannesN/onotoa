/**
 * 
 */
package de.topicmapslab.tmcledit.tmclimport.builder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.tmapi.core.Locator;
import org.tmapi.core.TMAPIException;
import org.tmapi.core.Topic;
import org.tmapi.core.TopicMap;
import org.tmapi.core.TopicMapExistsException;
import org.tmapi.core.TopicMapSystem;
import org.tmapix.io.CTMTopicMapReader;
import org.tmapix.io.XTMTopicMapReader;

import de.topicmapslab.majortom.core.TopicMapSystemFactoryImpl;
import de.topicmapslab.majortom.inmemory.store.InMemoryTopicMapStore;
import de.topicmapslab.majortom.store.TopicMapStoreProperty;
import de.topicmapslab.tmcl.loader.listener.IAssociationTypeConstraintsListener;
import de.topicmapslab.tmcl.loader.listener.INameTypeConstraintsListener;
import de.topicmapslab.tmcl.loader.listener.IOccurrenceTypeConstraintsListener;
import de.topicmapslab.tmcl.loader.listener.IRoleTypeConstraintsListener;
import de.topicmapslab.tmcl.loader.listener.ITopicTypeConstraintsListener;
import de.topicmapslab.tmcl.loader.listener.ITypeConstraintsListener;
import de.topicmapslab.tmcl.loader.listener.ITypesListener;
import de.topicmapslab.tmcl.loader.listener.IWorkMonitor;
import de.topicmapslab.tmcl.loader.reader.ConstraintReader;
import de.topicmapslab.tmcledit.model.AbstractRegExpTopicType;
import de.topicmapslab.tmcledit.model.AbstractUniqueValueTopicType;
import de.topicmapslab.tmcledit.model.AssociationType;
import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.ItemIdentifierConstraint;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.NameType;
import de.topicmapslab.tmcledit.model.NameTypeConstraint;
import de.topicmapslab.tmcledit.model.OccurrenceType;
import de.topicmapslab.tmcledit.model.OccurrenceTypeConstraint;
import de.topicmapslab.tmcledit.model.ReifiableTopicType;
import de.topicmapslab.tmcledit.model.ReifierConstraint;
import de.topicmapslab.tmcledit.model.RoleCombinationConstraint;
import de.topicmapslab.tmcledit.model.RoleConstraint;
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;
import de.topicmapslab.tmcledit.model.RoleType;
import de.topicmapslab.tmcledit.model.ScopeConstraint;
import de.topicmapslab.tmcledit.model.ScopedTopicType;
import de.topicmapslab.tmcledit.model.SubjectIdentifierConstraint;
import de.topicmapslab.tmcledit.model.SubjectLocatorConstraint;
import de.topicmapslab.tmcledit.model.TopicReifiesConstraint;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;
import de.topicmapslab.tmcledit.tmclimport.Activator;

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
	private Map<Topic, TopicType> topicTypeMap = new Hashtable<Topic, TopicType>();

	private IWorkMonitor monitor;

	public OnotoaBuilder(String filename) {
		this.filename = filename;
	}

	public File getFile(IWorkMonitor monitor) {
		if (file == null)
			try {
				this.monitor = monitor;
				createFile();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		return file;
	}

	public void subjectIdentifierConstraintElement(Topic type, String arg1, String arg2, String arg3) {
		SubjectIdentifierConstraint sic = modelFactory.createSubjectIdentifierConstraint();
		sic.setCardMin(arg1);
		sic.setCardMax(arg2);
		sic.setRegexp(arg3);

		getTopicType(type).getSubjectIdentifierConstraints().add(sic);
	}
	
	public void itemIdentififerConstraintElement(Topic type, String cardMin, String cardMax, String regExp) {
		ItemIdentifierConstraint iic = modelFactory.createItemIdentifierConstraint();
		iic.setCardMin(cardMin);
		iic.setCardMax(cardMax);
		iic.setRegexp(regExp);

		getTopicType(type).getItemIdentifierConstraints().add(iic);
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
		TopicType tt = getTopicType(arg0);
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
		// done in fill data
	}

	public void subjectLocator(Topic arg0, String arg1) {
		// done in fill data
	}

	public void typeName(Topic arg0, String arg1) {
		// done in fill data
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
		TopicType tt = getTopicType(arg0);
		if (tt instanceof AbstractRegExpTopicType) {
			((AbstractRegExpTopicType) tt).setRegExp(arg1);
		}

	}

	public void associationRoleConstraintElement(Topic type, Topic roleType, String cardMin, String cardMax) {
		try {
			AssociationType at = (AssociationType) getTopicType(type);
			TopicType rt = getTopicType(roleType);
			RoleConstraint rc = modelFactory.createRoleConstraint();
			rc.setCardMin(cardMin);
			rc.setCardMax(cardMax);
			rc.setType(rt);
			at.getRoles().add(rc);
		} catch (Exception e) {
			
		}
	    
	}

	public void roleCombinationConstraintElement(Topic type, Topic roleType, Topic playerType, Topic otherRoleType,
	        Topic otherPlayerType) {
		try {
			AssociationType at = (AssociationType) getTopicType(type);
			
			TopicType rt = getTopicType(roleType);
			TopicType pt = getTopicType(playerType);
			
			TopicType ort = getTopicType(otherRoleType);
			TopicType opt = getTopicType(otherPlayerType);
			
			RoleCombinationConstraint rcc = modelFactory.createRoleCombinationConstraint();
			rcc.setPlayer(pt);
			rcc.setRole(rt);
			rcc.setOtherPlayer(opt);
			rcc.setOtherRole(ort);
			
			at.getRoleCombinations().add(rcc);
			
		} catch (Exception e) {
			
		}
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
		TopicType tt = getTopicType(arg0);
		if (tt instanceof AbstractUniqueValueTopicType)
			((AbstractUniqueValueTopicType) tt).setUnique(true);
	}

	public void topicNameConstraintElement(Topic arg0, Topic arg1, String arg2, String arg3) {
		NameTypeConstraint ntc = modelFactory.createNameTypeConstraint();
		ntc.setCardMin(arg2);
		ntc.setCardMax(arg3);

		TopicType tt = getTopicType(arg0);
		tt.getNameConstraints().add(ntc);
		
		try {
			ntc.setType(getTopicType(arg1));
		} catch (Exception e) {
			// noop and use default
		}
	}

	public void topicOccurrenceConstraintElement(Topic arg0, Topic arg1, String arg2, String arg3) {
		OccurrenceTypeConstraint otc = modelFactory.createOccurrenceTypeConstraint();
		otc.setCardMin(arg2);
		otc.setCardMax(arg3);

		TopicType tt = getTopicType(arg0);
		tt.getOccurrenceConstraints().add(otc);
		
		try {
			otc.setType(getTopicType(arg1));
		} catch (Exception e) {
			// noop and use default
		}

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

	public void topicRoleConstraintElement(Topic type, Topic associationType, Topic roleType, String cardMin,
	        String cardMax) {
		try {
			AssociationType at =  (AssociationType) getTopicType(associationType);
			TopicType rt = getTopicType(roleType);
			TopicType player = getTopicType(type);
			
			
			AssociationTypeConstraint atc = findAssociationConstraint(at);
			
			RolePlayerConstraint rpc = modelFactory.createRolePlayerConstraint();
			rpc.setPlayer(player);
			rpc.setRole(findRoleConstraint(at, rt));
			rpc.setCardMin(cardMin);
			rpc.setCardMax(cardMax);

			atc.getPlayerConstraints().add(rpc);
		} catch (TypeNotFoundException e) {
			Activator.getDefault().logError(e);
		} 
		

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

	private void createFile() throws Exception {
    	modelFactory = ModelFactory.eINSTANCE;
    	file = modelFactory.createFile();
    	file.setTopicMapSchema(modelFactory.createTopicMapSchema());
    
    	ModelIndexer.createInstance(file);
    	
    	
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
    	reader.addEventListener((ITypeConstraintsListener) this);
    	reader.parse(monitor);
    	
    	refactorAssociationConstraints();
    }

	private void refactorAssociationConstraints() {
        EList<AssociationTypeConstraint> atcList = file.getTopicMapSchema().getAssociationTypeConstraints();
    	List<AssociationTypeConstraint> associationTypeConstraints = new ArrayList<AssociationTypeConstraint>(atcList);
        for (AssociationTypeConstraint currAtc : associationTypeConstraints) {
        	AssociationType at = (AssociationType)currAtc.getType();
    		EList<RoleCombinationConstraint> roleCombinations = at.getRoleCombinations();
    		if (roleCombinations.size()==0) {
        		continue;
        	}
    		for (RoleCombinationConstraint rcc : roleCombinations) {
    			AssociationTypeConstraint atc = modelFactory.createAssociationTypeConstraint();
    			atc.setType(currAtc.getType());
    			
    			RolePlayerConstraint rpc1 = createRolePlayerConstraintFor(currAtc, rcc.getRole(), rcc.getPlayer());
    			if (rpc1==null) {
    				Activator.getDefault().logInfo("Invalid role combination constraint in :"+at.getName());
    			}
    			
    			RolePlayerConstraint rpc2 = createRolePlayerConstraintFor(currAtc, rcc.getOtherRole(), rcc.getOtherPlayer());
    			if (rpc2==null) {
    				Activator.getDefault().logInfo("Invalid role combination constraint in :"+at.getName());
    			}
    				
    			atc.getPlayerConstraints().add(rpc1);
    			atc.getPlayerConstraints().add(rpc2);
    			atcList.add(atc);
    		}
        }
        
    }

	private RolePlayerConstraint createRolePlayerConstraintFor(AssociationTypeConstraint atc, TopicType role,
            TopicType player) {
        for (RolePlayerConstraint rpc : atc.getPlayerConstraints()) {
        	if ((rpc.getRole().getType().equals(role)) && (rpc.getPlayer().equals(player))) {
        		RolePlayerConstraint result = modelFactory.createRolePlayerConstraint();
        		result.setCardMin(rpc.getCardMin());
        		result.setCardMax(rpc.getCardMax());
        		result.setComment(rpc.getComment());
        		result.setDescription(rpc.getDescription());
        		result.setPlayer(player);
        		result.setRole(rpc.getRole());
        		
        	   return result;
        	}
        }
        return null;
    }

	private TopicMap loadTopicMap(TopicMapSystem system) throws TMAPIException, TopicMapExistsException, IOException {
    
    	String docIri = "http://onotoa.topicmapslab.de/schema/";
    	TopicMap topicMap = system.createTopicMap(docIri);
    
    	InputStream is;
    	if (filename.startsWith("http://")) {
    		is = new URL(filename).openStream();
    	} else {
    		java.io.File file = new java.io.File(filename);
    		is = new FileInputStream(file);
    	}
    	/*
    	 * load topic map from file
    	 */
    	if (filename.endsWith(".ctm")) {
    		CTMTopicMapReader reader = new CTMTopicMapReader(topicMap, is, docIri);
    		reader.read();
    	} else if (filename.endsWith(".xtm") || filename.endsWith(".xtm20")) {
    		XTMTopicMapReader reader = new XTMTopicMapReader(topicMap, is, docIri);
    		reader.read();
    	}
    	return topicMap;
    }

	private AssociationTypeConstraint findAssociationConstraint(AssociationType at) {
		for (AssociationTypeConstraint atc : file.getTopicMapSchema().getAssociationTypeConstraints()) {
			if (at.equals(atc.getType()))
				return atc;
		}

		AssociationTypeConstraint atc = modelFactory.createAssociationTypeConstraint();
		atc.setType(at);
		file.getTopicMapSchema().getAssociationTypeConstraints().add(atc);

		return atc;
	}

	private RoleConstraint findRoleConstraint(AssociationType at, TopicType rt) {
        RoleConstraint roleConstraint = null;
        
        for (RoleConstraint rc : at.getRoles()) {
        	if (rc.getType().equals(rt))
        		roleConstraint = rc;
        }
        return roleConstraint;
    }

	private synchronized void fillData(TopicType tt, Topic arg0) {
		if (arg0.getNames().size() == 0)
			throw new RuntimeException("Invalid topic - no name given");

		
		String name = arg0.getNames().iterator().next().getValue();
		tt.setName(name);

		for (Locator loc : arg0.getSubjectIdentifiers()) {
			tt.getIdentifiers().add(loc.toExternalForm());
		}
		
		for (Locator loc : arg0.getSubjectLocators()) {
			tt.getLocators().add(loc.toExternalForm());
		}

		topicTypeMap.put(arg0, tt);
		file.getTopicMapSchema().getTopicTypes().add(tt);
	}

	private TopicType getTopicType(Topic arg0) {
		if (arg0==null)
			throw new NullPointerException("Argument is null");
		TopicType tt = topicTypeMap.get(arg0);
		if (tt == null) {
			String appendix = "";
			if (arg0.getNames().size()>0) {
				appendix = arg0.getNames().iterator().next().getValue();
			}
			if (arg0.getSubjectIdentifiers().size()>0) {
				appendix = arg0.getSubjectIdentifiers().iterator().next().toExternalForm();
			}
			throw new TypeNotFoundException("Unknown type for Topic: "+appendix );
		}
		return tt;
	}

}
