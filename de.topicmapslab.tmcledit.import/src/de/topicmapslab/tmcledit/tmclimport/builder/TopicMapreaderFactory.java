/**
 * 
 */
package de.topicmapslab.tmcledit.tmclimport.builder;


/**
 * 
 * This class i9s used to get the right reader for a file containg a topic map.
 * 
 * @author Haannes Niederhausen
 *
 */
public class TopicMapreaderFactory {

//	private TopicMap topicMap;
//	
//	public TopicMapReader getReader(File file, TopicMap topicMap) throws FileNotFoundException, IOException {
//		TopicMapReader reader = null;
//		this.topicMap = topicMap;
//		
//		String lowerCase = file.getName().toLowerCase();
//		if (lowerCase.endsWith(".ctm")) {
//			reader = createCTMReader(file);
//		} else if (lowerCase.endsWith(".xtm")) {
//			reader = new XTMTopicMapReader(topicMap, file);
//		} else if (lowerCase.endsWith(".ltm")) {
//			reader = new LTMTopicMapReader(topicMap, file);
//		}
//		return reader;
//	}
//	
//	private boolean isCTMPrefix(String line) {
//		return ( (line.startsWith("%prefix")) 
//				|| (line.startsWith("%encoding"))
//				|| (line.startsWith("%include"))
//				|| (line.startsWith("%version")) );
//	}
//	
//	private TopicMapReader createCTMReader(java.io.File file)
//			throws IOException, FileNotFoundException {
//		TopicMapReader reader;
//		URL url = Activator.getDefault().getBundle().getEntry(
//				"/ctm/templates.ctm");
//
//		InputStream is = url.openStream();
//		StringBuffer tmp = new StringBuffer();
//		int ch;
//
//		BufferedReader br = new BufferedReader(new FileReader(file));
//		while (br.ready()) {
//			String line = br.readLine();
//			if (isCTMPrefix(line))	{
//				tmp.append(line);
//				tmp.append("\n");
//			}
//		}
//
//		while ((ch = is.read()) != -1) {
//			tmp.append((char) ch);
//		}
//		is.close();
//		
//		br = new BufferedReader(new FileReader(file));
//		while (br.ready()) {
//			String line = br.readLine();
//			if (!isCTMPrefix(line)) {
//				tmp.append(line);
//				tmp.append("\n");
//			}
//		}
//
//		reader = new CTMTopicMapReader(topicMap, new ByteArrayInputStream(
//				tmp.toString().getBytes()), "file:"+file.getAbsolutePath());
//		return reader;
//	}

}
