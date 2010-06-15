package de.topicmapslab.tmcledit.model.preferences;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;
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

import de.topicmapslab.tmcledit.model.TmcleditEditPlugin;

/**
 * This class manages the recent used files. It retrieves the last five opened files from the preference store and 
 *  
 * 
 * @author Hannes Niederhausen
 *
 */
public class RecentUsedManager {

	private static List<String> list;

	public static List<String> getFilesList() {
		if (list == null) {
			try {
				RecentUsedHandler dh = new RecentUsedHandler();
				String xml = TmcleditEditPlugin.getPlugin().getPreferenceStore()
						.getString(PreferenceConstants.RECENT_USED);

				if ((xml!=null) && (xml.length()>0)) {
					SAXParserFactory fac = SAXParserFactory.newInstance();
					SAXParser parser;
	
					parser = fac.newSAXParser();
					
					parser.parse(new ByteArrayInputStream(xml.getBytes()),
							dh);
				}
				
				list = dh.getList();
			} catch (Exception e) {
				TmcleditEditPlugin.logError(e);
			}
		}
		return list;
	}
	
	public static void addFile(String file) {
		
		List<String> list = getFilesList();
		
		int idx = list.indexOf(file);
		if (idx!=-1) {
			list.remove(idx);
		}
		list.add(0, file);
		
		if (list.size()>5)
			list.remove(5);
		
		persistList();
	}

	private static void persistList() {
		
		try {
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().getDOMImplementation().createDocument(
			        "onotoa.topicmapslab.de", "files", null);

			for (String path : list) {
				Element e = doc.createElement("file");
				e.setAttribute("path", path);
				doc.getDocumentElement().appendChild(e);
			}
			
			TransformerFactory tf = TransformerFactory.newInstance();
			try {
				tf.setAttribute("indent-number", Integer.toString(4));
			} catch (IllegalArgumentException iae) {
				TmcleditEditPlugin.logError(iae);
				throw new RuntimeException(iae);
			}
			
			
			DOMSource domSource = new DOMSource(doc);
			StringWriter writer = new StringWriter();
			StreamResult streamResult = new StreamResult(writer);
			
			Transformer serializer = tf.newTransformer();
			serializer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			
			serializer.transform(domSource, streamResult);
			
			TmcleditEditPlugin.getPlugin().getPreferenceStore().putValue(PreferenceConstants.RECENT_USED, writer.getBuffer().toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
}
