package de.topicmapslab.tmcledit.model.util;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.Edge;
import de.topicmapslab.tmcledit.model.EdgeType;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.MappingElement;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.NameTypeConstraint;
import de.topicmapslab.tmcledit.model.Node;
import de.topicmapslab.tmcledit.model.OccurenceTypeConstraint;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.TypeNode;

public class FileUtil {
	
	public static final File loadFile(String path) {
		try {
			java.io.File file = new java.io.File(path);
			File result = null;
			if (file.isDirectory())
				return null; //TODO Exception
			if (!file.exists()) {
				ModelFactory einstance = ModelFactory.eINSTANCE;
				result = einstance.createFile();
				result.setTopicMapSchema(einstance.createTopicMapSchema());
				//createTestData(result);
			} else {
				Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
				Map<String, Object> map = reg.getExtensionToFactoryMap();
				map.put("tmcl", new XMIResourceFactoryImpl());
				ResourceSet resSet = new ResourceSetImpl();
				URI uri = URI.createFileURI(path);
				result = (File) resSet.getResource(uri, true).getContents()
						.get(0);
			}
			result.setFilename(path);
			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public static final void saveFile(File file) throws IOException {
		try {
			URI uri = URI.createFileURI(file.getFilename());
			Resource resource = new XMIResourceFactoryImpl().createResource(uri);
			resource.getContents().add(file);
			
			resource.save(Collections.EMPTY_MAP);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
	protected static final void createTestData(File file) {
		ModelFactory modelInstance = ModelFactory.eINSTANCE;
		
		TopicMapSchema schema = file.getTopicMapSchema(); 
		
		Diagram diagram = modelInstance.createDiagram();
		diagram.setName("diagram 1");
		file.getDiagrams().add(diagram);
		
		MappingElement me = ModelFactory.eINSTANCE.createMappingElement();
		me.setKey("foo");
		me.setValue("http://psi.foo.com");
		schema.getMappings().add(me);
		
		me = ModelFactory.eINSTANCE.createMappingElement();
		me.setKey("tmcl");
		me.setValue("http://psi.topicmaps.org/tmcl/");
		schema.getMappings().add(me);
		
		TopicType tt = modelInstance.createTopicType();
		tt.setName("Person");
		tt.getIdentifiers().add("foo:Person");
		schema.getTopicTypes().add(tt);
		
		TopicType tt2 = modelInstance.createTopicType();
		tt2.setName("Boss");
		tt2.getIdentifiers().add("foo:Boss");
		tt2.getIsa().add(tt);
		schema.getTopicTypes().add(tt2);
		
		
		TypeNode tn = modelInstance.createTypeNode();
		tn.setPosX(50);
		tn.setPosY(50);
		tn.setTopicType(tt);
		diagram.getNodes().add((Node) tn);
		
		TypeNode tn2 = modelInstance.createTypeNode();
		tn2.setPosX(450);
		tn2.setPosY(50);
		tn2.setTopicType(tt2);
		diagram.getNodes().add((Node) tn2);
		
		Edge e = modelInstance.createEdge();
		e.setSource(tn2);
		e.setTarget(tn);
		e.setType(EdgeType.IS_ATYPE);
		diagram.getEdges().add(e);
		
		TopicType ot = modelInstance.createTopicType();
		ot.setKind(KindOfTopicType.OCCURENCE_TYPE);
		ot.setName("Addresse");
		ot.getIdentifiers().add("foo:Address");
		schema.getTopicTypes().add(ot);
		
		OccurenceTypeConstraint otc = modelInstance.createOccurenceTypeConstraint();
		otc.setCardMax("*");
		otc.setCardMin("0");
		otc.setType(ot);
		tt.getOccurenceConstraints().add(otc);
		
		TopicType nt = modelInstance.createTopicType();
		nt.setKind(KindOfTopicType.NAME_TYPE);
		nt.setName("Firstname");
		nt.getIdentifiers().add("foo:Firstname");
		schema.getTopicTypes().add(nt);
		
		NameTypeConstraint ntc = modelInstance.createNameTypeConstraint();
		ntc.setType(nt);
		tt.getNameContraints().add(ntc);
		
		TopicType rt = ModelFactory.eINSTANCE.createTopicType();
		rt.setName("Employee");
		rt.getIdentifiers().add("foo:Employee");
		rt.setKind(KindOfTopicType.ROLE_TYPE);
		schema.getTopicTypes().add(rt);
		
		rt = ModelFactory.eINSTANCE.createTopicType();
		rt.setName("Employer");
		rt.getIdentifiers().add("foo:Employer");
		rt.setKind(KindOfTopicType.ROLE_TYPE);
		schema.getTopicTypes().add(rt);
		
		
		
		
		
	}
}
