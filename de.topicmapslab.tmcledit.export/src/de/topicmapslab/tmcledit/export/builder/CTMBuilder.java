/**
 * 
 */
package de.topicmapslab.tmcledit.export.builder;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.MappingElement;
import de.topicmapslab.tmcledit.model.NameTypeConstraint;
import de.topicmapslab.tmcledit.model.OccurenceTypeConstraint;
import de.topicmapslab.tmcledit.model.RoleTypeConstraints;
import de.topicmapslab.tmcledit.model.SubjectIdentifierConstraint;
import de.topicmapslab.tmcledit.model.SubjectLocatorConstraint;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;

/**
 * @author Hannes Niederhausen
 *
 */
public class CTMBuilder {
	private StringBuffer buffer;
	private TopicMapSchema schema;
	String LINE_SEP = System.getProperty("line.separator");
	
	private Map<TopicType, String> typeIdMap = Collections.emptyMap();
	
	private String indentString;
	private int indention = 0;

	public CTMBuilder() {
		super();
	}
	
	public String getCTMText(TopicMapSchema schema) {
		this.schema = schema;
		init();
		processMapping();
		processTopicMapSchema();
		
		return buffer.toString();
	}
			
	
	private void init() {
		indentString = "    ";
		indention = 0;
		buffer = new StringBuffer();
		typeIdMap = new HashMap<TopicType, String>();
		
		addLine("%prefix tmcl http://psi.topicmaps.org/tmcl");
		addLineSeparator();
		addLine("%prefix tm http://psi.topicmaps.org/tmdm/model");
		addLineSeparator();
		addLine("%inlcude http://www.topicmaps.org.tmcl/templates.ctm");
		
	}
	
	private void addLineSeparator() {
		buffer.append(LINE_SEP);
	}
	
	private void processMapping() {
		for (MappingElement me : schema.getMappings()) {
			buffer.append("% prefix ");
			buffer.append(me.getKey());
			buffer.append(" ");
			buffer.append(me.getValue());
			addLineSeparator();
		}
		addLineSeparator();
		addLineSeparator();
	}
	
	private void addLine(String text) {
		addIndention();
		buffer.append(text);
		addLineSeparator();
	}

	private void addIndention() {
		for (int i = 0; i<indention; i++)
			buffer.append(indentString);
	}
	
	private void processTopicMapSchema() {
		if (schema.isActiveTopicTypeConstraint())
			addLine("ttc isa topictypeconstraint .");
		
		if (schema.isActiveAssociationTypeConstraint())
			addLine("atc isa associationtype-constraint .");
		
		if (schema.isActiveNameTypeConstraint())
			addLine("ntc isa nametype-constraint .");
		
		if (schema.isActiveRoleTypeConstraint())
			addLine("rtc isa roletype-constraint .");
		
		if (schema.isActiveOccurenceTypeConstraint())
			addLine("otc isa occurencetype-constraint .");
		
		addLineSeparator();
		
		for (TopicType tt : schema.getTopicTypes()) {
			processTopicType(tt);
		}
	}
	
	private void processTopicType(TopicType topicType) {
		String id = getIdString(topicType);
		
		addLine(id);
		indention = 1;
		
		for (String s : topicType.getIdentifiers()) {
			if (s.equals(id))
				continue;
			addLine(s+";");
		}
		for (String s : topicType.getLocators()) {
			String tmp = "= "+s+";";
			if (tmp.equals(id))
				continue;
			addLine(tmp);
		}
		
		addLine("- "+topicType.getName());
		
		if (topicType.isIsAbstract()) {
			addLine("isAbstract();");
		}
		
		for (TopicType ett : topicType.getExclusive()) {
			String exId = getIdString(ett);

			addLine("exclusive-with("+exId+");");
		}
		
		for (SubjectIdentifierConstraint sic : topicType.getSubjectIdentifierConstraints()) {
			processSubjectIdentifierConstraint(sic);
		}
		
		for (SubjectLocatorConstraint slc : topicType.getSubjectLocatorConstraint()) {
			processSubjectLocatorConstraint(slc);
		}
		
		for (NameTypeConstraint ntc : topicType.getNameContraints()) {
			processNameTypeConstraint(ntc);
		}
		
		
		
		
		indention = 0;
		buffer.append(" .");
		addLineSeparator();
		
	}

	private String getIdString(TopicType topicType) {
		String id = typeIdMap.get(topicType);
		if (id == null) {

			if (topicType.getIdentifiers().size() > 0) {
				id = topicType.getIdentifiers().get(0);
			}

			if (topicType.getLocators().size() > 0) {
				id = "= " + topicType.getLocators().get(0);
			}

			if (id == null)
				id = topicType.getName().toLowerCase();

			typeIdMap.put(topicType, id);
		}
		return id;
	}
	
	private void processNameTypeConstraint(NameTypeConstraint ntc) {
		buffer.append("has-name(");
		buffer.append(ntc.getType().getName());
		buffer.append(",");
		buffer.append(ntc.getCardMin());
		buffer.append(",");
		buffer.append(ntc.getCardMax());
		buffer.append(",\"");
		buffer.append(ntc.getRegexp());
		buffer.append("\");");
		addLineSeparator();
	}
	
	private void processOccurenceTypeConstraint(OccurenceTypeConstraint otc) {
		
	}
	
	private void processAssociationConstraint(AssociationTypeConstraint atc) {
		
	}
	
	private void processRoleContraint(RoleTypeConstraints rtc) {
		
	}
	
	private void processSubjectIdentifierConstraint(SubjectIdentifierConstraint sic) {
		addIndention();
		buffer.append("has-subjectidentifier(");
		buffer.append(sic.getCardMin());
		buffer.append(",");
		buffer.append(sic.getCardMax());
		buffer.append(",\"");
		buffer.append(sic.getRegexp());
		buffer.append("\");");
		addLineSeparator();
	}
	
	private void processSubjectLocatorConstraint(SubjectLocatorConstraint slc) {
		addIndention();
		buffer.append("has-subjectlocator(");
		buffer.append(slc.getCardMin());
		buffer.append(",");
		buffer.append(slc.getCardMax());
		buffer.append(",\"");
		buffer.append(slc.getRegexp());
		buffer.append("\");");
		addLineSeparator();
	}
	
	
}
