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
package de.topicmapslab.onotoa.net.sync.common.util;

 
import static de.topicmapslab.onotoa.net.sync.common.util.IServerDataElements.A_NAME;
import static de.topicmapslab.onotoa.net.sync.common.util.IServerDataElements.A_HOST;
import static de.topicmapslab.onotoa.net.sync.common.util.IServerDataElements.A_ACTIVE;
import static de.topicmapslab.onotoa.net.sync.common.util.IServerDataElements.A_PORT;
import static de.topicmapslab.onotoa.net.sync.common.util.IServerDataElements.E_SERVER;
import static de.topicmapslab.onotoa.net.sync.common.util.IServerDataElements.E_SERVERS;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import de.topicmapslab.onotoa.net.sync.Activator;
import de.topicmapslab.onotoa.net.sync.common.ServerData;

/**
 * @author Hannes Niederhausen
 * 
 */
public class ServerDataSerializer {

	public String serialize(Collection<ServerData> data) {
		try {
			Document doc = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder().getDOMImplementation().createDocument("onotoa.topicmapslab.de", E_SERVERS, null);

			

			for (ServerData sd : data) {
				Element e = doc.createElement(E_SERVER);

				e.setAttribute(A_NAME, sd.getName());
				e.setAttribute(A_PORT, sd.getPort());
				e.setAttribute(A_HOST, sd.getHost());
				if (!sd.isActive())
					e.setAttribute(A_ACTIVE, Boolean.toString(sd.isActive()));

				doc.getDocumentElement().appendChild(e);
			}

			DOMSource domSource = new DOMSource(doc);
			StringWriter writer = new StringWriter();
			StreamResult streamResult = new StreamResult(writer);

			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer serializer = tf.newTransformer();
			serializer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			serializer.setOutputProperty(OutputKeys.INDENT, "NO");
			serializer.transform(domSource, streamResult);
			
			return writer.toString();
		} catch (Exception e) {
			Activator.log(e);
		}

		return null;
	}

	public static void main(String[] args) {
		List<ServerData> l = new ArrayList<ServerData>(5);

		l.add(new ServerData("Name1", "www.name1.de", "8080"));
		l.add(new ServerData("Name2", "www.name2.de", "8081"));
		l.add(new ServerData("Name3", "www.name3.de", "8082"));
		l.add(new ServerData("Name4", "www.name4.de", "8083"));

		String tmp = new ServerDataSerializer().serialize(l);
		
		try {
			SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
			ServerDataHandler dh = new ServerDataHandler();
			parser.parse(new ByteArrayInputStream(tmp.getBytes()), dh);
			
			if (dh.getData().size()!=l.size()) {
				System.out.println("Error at parsing");
				return;
			}
			
			for (ServerData sd : l) {
				if (!(dh.getData().contains(sd))) {
					System.out.println("Error!!"+sd);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
