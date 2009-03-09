/*******************************************************************************
 * Copyright (c) 2008, 2009 Topic Maps Lab and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Hannes Niederhausen - initial API and implementation
 *******************************************************************************/
/**
 * 
 */
package de.topicmapslab.tmcledit.export.builder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.topicmapslab.tmcledit.model.AssociationType;
import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.MappingElement;
import de.topicmapslab.tmcledit.model.NameTypeConstraint;
import de.topicmapslab.tmcledit.model.OccurrenceType;
import de.topicmapslab.tmcledit.model.OccurrenceTypeConstraint;
import de.topicmapslab.tmcledit.model.OtherRolePlayerConstraint;
import de.topicmapslab.tmcledit.model.RoleConstraint;
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;
import de.topicmapslab.tmcledit.model.RoleType;
import de.topicmapslab.tmcledit.model.ScopeConstraint;
import de.topicmapslab.tmcledit.model.ScopedTopicType;
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
	private Map<TopicType, List<PlayerConstraintInfo>> playerInfoMap = Collections.emptyMap();
	
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
		playerInfoMap = new HashMap<TopicType, List<PlayerConstraintInfo>>();
		
		indexRolePlayerConstraints();
		
		addLine("%prefix tmcl http://psi.topicmaps.org/tmcl");
		addLine("%prefix tm http://psi.topicmaps.org/tmdm/model");
		addLineSeparator();
		addLine("%include http://www.topicmaps.org.tmcl/templates.ctm");
		
	}
	
	private void indexRolePlayerConstraints() {
		for (AssociationTypeConstraint atc : schema.getAssociationTypeConstraints()) {
			for (RolePlayerConstraint rpc : atc.getPlayerConstraints()) {
				TopicType player = rpc.getPlayer();
				List<PlayerConstraintInfo> infoList = playerInfoMap.get(player);
				if (infoList==null) {
					infoList = new ArrayList<PlayerConstraintInfo>();
					playerInfoMap.put(player, infoList);
				}
				PlayerConstraintInfo info = new PlayerConstraintInfo(
						(AssociationType) atc.getType(), rpc);
				infoList.add(info);
			}
		}
	}

	private void addLineSeparator() {
		buffer.append(LINE_SEP);
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
	
	private void processMapping() {
		for (MappingElement me : schema.getMappings()) {
			buffer.append("%prefix ");
			buffer.append(me.getKey());
			buffer.append(" ");
			buffer.append(me.getValue());
			addLineSeparator();
		}
		addLineSeparator();
		addLineSeparator();
	}

	private void processTopicMapSchema() {
		if (schema.isActiveTopicTypeConstraint())
			addLine("ttc isa topictype-constraint .");
		
		if (schema.isActiveAssociationTypeConstraint())
			addLine("atc isa associationtype-constraint .");
		
		if (schema.isActiveNameTypeConstraint())
			addLine("ntc isa nametype-constraint .");
		
		if (schema.isActiveRoleTypeConstraint())
			addLine("rtc isa roletype-constraint .");
		
		if (schema.isActiveOccurrenceTypeConstraint())
			addLine("otc isa occurrencetype-constraint .");
		
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
		
		
		if (topicType.getKind()!=KindOfTopicType.NO_TYPE) {
			addIndention();
			buffer.append("ako ");
			buffer.append(topicType.getKind().getName().toLowerCase());
			buffer.append(";");
			addLineSeparator();
		}
		
		addLine("- \""+topicType.getName()+"\";");
		
		if (topicType.isAbstract()) {
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
		
		for (OccurrenceTypeConstraint otc : topicType.getOccurrenceConstraints()) {
			processOccurrenceTypeConstraint(otc);
		}
		
		if (topicType instanceof ScopedTopicType) {
			ScopedTopicType stt = (ScopedTopicType) topicType;
			
			for (ScopeConstraint sc : stt.getScope()) {
				processScopedTopicTypes(stt, sc);
			}
		}
		
		if (topicType instanceof AssociationType) {
			AssociationType at = (AssociationType) topicType;
			
			for (RoleConstraint rc : at.getRoles())
				processRoleContraint(rc);
		}
		
		if (topicType instanceof OccurrenceType) {
			processOccurrenceDatatype(topicType);
		}
		
		if (topicType instanceof RoleType) {
			for (OtherRolePlayerConstraint orpc : ((RoleType)topicType).getOtherRoles())
				processOtherRolePlayerConstraint(orpc);
		}
		
		List<PlayerConstraintInfo> infos = playerInfoMap.get(topicType);
		if (infos!=null)
			for(PlayerConstraintInfo info : infos) {
				processPlayerConstraintInfo(info);
			}
		
		
		indention = 0;
		int i = buffer.lastIndexOf(";", buffer.length());
		if (i>=0)			
			buffer.setLength(i);
		buffer.append(" .");
		addLineSeparator();
		addLineSeparator();
	}

	private void processPlayerConstraintInfo(PlayerConstraintInfo info) {
		RolePlayerConstraint rolePlayerConstraint = info.rolePlayerConstraint;
		
		addIndention();
		buffer.append("plays-role(");
		buffer.append(getIdString(rolePlayerConstraint.getRole().getType()));
		buffer.append(", ");
		buffer.append(getIdString(info.associationType));
		buffer.append(", ");
		buffer.append(rolePlayerConstraint.getCardMin());
		buffer.append(", ");
		buffer.append(rolePlayerConstraint.getCardMax());
		buffer.append(");");
		addLineSeparator();
	}

	private void processOccurrenceDatatype(TopicType topicType) {
		OccurrenceType ot = (OccurrenceType) topicType;
		addIndention();
		buffer.append("has-datatype(");
		buffer.append(ot.getDataType());
		buffer.append(");");
		addLineSeparator();
	}

	private void processScopedTopicTypes(ScopedTopicType stt, ScopeConstraint sc) {
		addIndention();
		switch(stt.getKind()) {
		case NAME_TYPE:
				buffer.append("has-scope(");
				break;
			case OCCURRENCE_TYPE:
				buffer.append("has-occurrence-scope(");
				break;
			case ASSOCIATION_TYPE:
				buffer.append("has-association-scope(");
				break;
		}

		buffer.append(getIdString(sc.getType()));
		buffer.append(", ");
		buffer.append(sc.getCardMin());
		buffer.append(", ");
		buffer.append(sc.getCardMax());
		buffer.append(");");
		addLineSeparator();
	}

	private void processNameTypeConstraint(NameTypeConstraint ntc) {
		String nameId=(ntc.getType()==null) ? "http://psi.topicmaps.org/iso13250/model/topic-name" :
			getIdString(ntc.getType());
		addIndention();
		buffer.append("has-name(");
		buffer.append(nameId);
		buffer.append(", ");
		buffer.append(ntc.getCardMin());
		buffer.append(", ");
		buffer.append(ntc.getCardMax());
		buffer.append(", \"");
		buffer.append(ntc.getRegexp());
		buffer.append("\");");
		addLineSeparator();
	}
	
	private void processOccurrenceTypeConstraint(OccurrenceTypeConstraint otc) {
		String idString = getIdString(otc.getType());
		addIndention();
		buffer.append("has-occurrence(");
		buffer.append(idString);
		buffer.append(", ");
		buffer.append(otc.getCardMin());
		buffer.append(", ");
		buffer.append(otc.getCardMax());
		buffer.append(", \"");
		buffer.append(otc.getRegexp());
		buffer.append("\");");
		addLineSeparator();

		if (otc.isUnique()) {
			addIndention();
			buffer.append("unqie-occurrence(");
			buffer.append(idString);
			buffer.append(");");
			addLineSeparator();
		}
	}

	private void processOtherRolePlayerConstraint(OtherRolePlayerConstraint orpc) {
		addIndention();
		buffer.append("other-role(");
		buffer.append(getIdString(orpc.getAssociationType()));
		buffer.append(", ");
		buffer.append(getIdString(orpc.getPlayer()));
		buffer.append(", ");
		buffer.append(getIdString(orpc.getOtherRole()));
		buffer.append(", ");
		buffer.append(getIdString(orpc.getOtherPlayer()));
		buffer.append(", ");
		buffer.append(orpc.getCardMin());
		buffer.append(", ");
		buffer.append(orpc.getCardMax());
		buffer.append(");");
		addLineSeparator();
	}
	
	private void processRoleContraint(RoleConstraint rtc) {
		addIndention();
		buffer.append("has-role(");
		buffer.append(getIdString(rtc.getType()));
		buffer.append(rtc.getCardMin());
		buffer.append(", ");
		buffer.append(rtc.getCardMax());
		buffer.append(");");
		addLineSeparator();
	}
	
	private void processSubjectIdentifierConstraint(SubjectIdentifierConstraint sic) {
		addIndention();
		buffer.append("has-subjectidentifier(");
		buffer.append(sic.getCardMin());
		buffer.append(", ");
		buffer.append(sic.getCardMax());
		buffer.append(", \"");
		buffer.append(sic.getRegexp());
		buffer.append("\");");
		addLineSeparator();
	}
	
	private void processSubjectLocatorConstraint(SubjectLocatorConstraint slc) {
		addIndention();
		buffer.append("has-subjectlocator(");
		buffer.append(slc.getCardMin());
		buffer.append(", ");
		buffer.append(slc.getCardMax());
		buffer.append(", \"");
		buffer.append(slc.getRegexp());
		buffer.append("\");");
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

	private class PlayerConstraintInfo {
		final AssociationType associationType;
		final RolePlayerConstraint rolePlayerConstraint;
		
		public PlayerConstraintInfo(AssociationType associationType,
				RolePlayerConstraint rolePlayerConstraint) {
			super();
			this.associationType = associationType;
			this.rolePlayerConstraint = rolePlayerConstraint;
		}
		
		
	}
	
}
