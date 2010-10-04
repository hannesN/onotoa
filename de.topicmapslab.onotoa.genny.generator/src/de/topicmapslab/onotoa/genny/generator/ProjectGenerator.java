/**
 * 
 */
package de.topicmapslab.onotoa.genny.generator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;

import org.eclipse.core.runtime.IProgressMonitor;
import org.tmapi.core.TopicMap;

import de.topicmapslab.codegenerator.CodeGenerator;
import de.topicmapslab.codegenerator.factories.AranukaDescriptorFactory;
import de.topicmapslab.onotoa.genny.generator.model.GeneratorData;
import de.topicmapslab.tmcledit.export.builder.TMCLTopicMapBuilder;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;

/**
 * @author Hannes Niederhausen
 *
 */
public class ProjectGenerator {

	private GeneratorData data;
	private StringBuilder messages;
	private IProgressMonitor monitor;

	public void generateProjects(GeneratorData data, StringBuilder messages, IProgressMonitor monitor) {
		this.data = data;
		this.messages = messages;
		this.monitor = monitor;
		File f = new File(data.getTargetDir());
		if (!f.exists()) {
			f.mkdirs();
		}
		
		createFeatureDirectory();
		createRelengDirectory();
		createApplicationDirectory();
		createProjectDirectory();
		
		buildProject();
		
	}


	private void buildProject() {
		
		File dir = new File(data.getTargetDir()+"/"+data.getApplicationId()+"-releng");
		
	    Runtime runtime = Runtime.getRuntime();
	    
	    String[] cmd = new String[]{"/home/niederhausen/java/apache-maven-3.0-beta-3/bin/mvn", "package"};
	    
	    try {
	        final Process p = runtime.exec(cmd, new String[]{}, dir);
	        
	        Thread isReadThread = new Thread(new Runnable() {

				@Override
                public void run() {
					InputStream is = p.getInputStream();
			        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			        
			        while (true) {
			        	try {
	                        String line = null;
	                        while ((line=reader.readLine())!=null) {
	                        	messages.append(line);
	                        	System.out.println(line);
	                        }
                        } catch (IOException e) {
                        	Activator.logException(e);
                        }
                        try {
	                        Thread.sleep(1000);
                        } catch (InterruptedException e) {
                        }
			        }
                }
	        });
	        
	        // start reading thread
	        isReadThread.start();
	        // wait the build process to end
	        p.waitFor();
	        // stop read process
	        isReadThread.interrupt();
	        
        } catch (Exception e) {
        	Activator.logException(e);
        	
        	
        }
	    
    }


	private void createRelengDirectory() {
		monitor.subTask("Creating Release Engineering Project");
		messages.append("Creating Release Engineering project... ");
	    File dir = new File(data.getTargetDir()+"/"+data.getApplicationId()+"-releng");
	    dir.mkdir();
	    
	    copyResource(dir, "/resources/releng");
	    
	    messages.append("finished\n");
	    
    }


	private void createApplicationDirectory() {
		monitor.subTask("Creating Application Project");
		messages.append("Creating application project... ");
	    File dir = new File(data.getTargetDir()+"/"+data.getApplicationId()+"-application");
	    dir.mkdir();
	    
	    copyResource(dir, "/resources/application");
	    
	    messages.append("finished\n");
	    
    }


	private void createProjectDirectory() {
		monitor.subTask("Creating Model Project");
		messages.append("Creating model project... ");
	    File dir = new File(data.getTargetDir()+"/"+data.getApplicationId());
	    dir.mkdir();
	    
	    copyResource(dir, "/resources/project");
	    
	    File srcFolder = new File(dir.getAbsolutePath()+"/src");
	    srcFolder.mkdir();
	    
	    TopicMapSchema schema = ModelIndexer.getInstance().getTopicMapSchema();

		TMCLTopicMapBuilder builder = new TMCLTopicMapBuilder(schema, false);

		TopicMap topicMap = builder.createTopicMap();

		try {
	        AranukaDescriptorFactory fac = new AranukaDescriptorFactory(builder.getTopicMapSystem(), topicMap, data.getApplicationId()+".model");
	        CodeGenerator gen = fac.getCodeGenerator();
	        gen.generateCode(new File(srcFolder.getAbsolutePath()));
        } catch (Exception e) {
        	Activator.logException(e);
        	throw new RuntimeException(e);
        }
		
        // now we copy the activator to the src dir
        
        String packagePath = data.getApplicationId().replaceAll("\\.", "/");
        File path = new File(srcFolder.getAbsolutePath()+"/"+packagePath);
        
        copyFile("resources/template/Activator.java", path);
        
	    
	    messages.append("finished\n");
	    
    }


	private void createFeatureDirectory() {
		monitor.subTask("Creating Feature Project");
		messages.append("Creating feature project... ");
	    File dir = new File(data.getTargetDir()+"/"+data.getApplicationId()+"-feature");
	    dir.mkdir();
	    
	    copyResource(dir, "/resources/feature");
	    
	    messages.append("finished\n");
    }

	/**
	 * Copies the resource found in srcPath to the target directory
	 * @param dir target directory
	 * @param srcPath srcpath in the the bundle
	 */
	private void copyResource(File dir, String srcPath) {
        Enumeration<?> entryPaths = Activator.getDefault().getBundle().getEntryPaths(srcPath);

		if (entryPaths == null)
			return;

		List<String> entryList = new ArrayList<String>();
		while (entryPaths.hasMoreElements()) {
			entryList.add((String) entryPaths.nextElement());
		}

		// sort list so subfolders get copied first
		Collections.sort(entryList, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				boolean slash1 = o1.endsWith("/");
				boolean slash2 = o1.endsWith("/");

				if (slash1 == slash2)
					return o1.compareTo(o2);

				if (slash1)
					return -1;

				return 1;
			}
		});

		try {
			for (String o : entryList) {
				if (o.endsWith("/")) {
					String tmp = o.substring(0, o.length() - 1);
					tmp = tmp.substring(tmp.lastIndexOf("/"));

					File subDir = new File(dir.getAbsolutePath()+"/"+tmp);
					if (subDir.exists())
						subDir.delete();

					subDir.mkdir();

					copyResource(subDir, o);
				} else {
					copyFile(o, dir);
				}
			}
		} catch (Exception e) {
			Activator.logException(e);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Copies a file to the given target folder
	 * @param file the path and name of the file
	 * @param folder the target folder
	 */
	private void copyFile(String file, File folder) {
		try {
			StringBuilder builder = readFileEntry(file);

			String filename = file.substring(file.lastIndexOf("/") + 1);

			if ("name.product".equals(filename)) {
				filename = getValidName(data.getApplicationName())+".product";
			}

			String content = builder.toString();

			content = content.replaceAll(Matcher.quoteReplacement("$name"),
					data.getApplicationName());
			content = content.replaceAll(Matcher.quoteReplacement("$validname"),
					getValidName(data.getApplicationName()));
			content = content.replaceAll(Matcher.quoteReplacement("$pluginId"),
					data.getApplicationId());

			File newFile = new File(folder.getAbsolutePath()+"/"+filename);
			if (newFile.exists())
				newFile.delete();
			
			FileOutputStream fos = new FileOutputStream(newFile);
			fos.write(content.getBytes());
			fos.close();

		} catch (Exception e) {
			Activator.logException(e);
		}

	}

	/**
	 * Reads the content of a file in the bundles resources
	 * @param file
	 * @return
	 * @throws IOException
	 */
	private StringBuilder readFileEntry(String file) throws IOException {
		URL entry = Activator.getDefault().getBundle().getEntry(file);
		InputStream is = entry.openStream();

		StringBuilder builder = new StringBuilder();

		int c;
		while ((c = is.read()) != -1) {
			builder.append((char) c);
		}
		return builder;
	}

	private String getValidName(String name) {
		String tmp = name.replaceAll(" ", "_");
		
		return tmp.toLowerCase();
		
	}
}
