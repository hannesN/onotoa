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
import de.topicmapslab.onotoa.genny.generator.ui.ITextListener;
import de.topicmapslab.tmcledit.export.builder.TMCLTopicMapBuilder;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;

/**
 * @author Hannes Niederhausen
 * 
 */
public class ProjectGenerator {

	private GeneratorData data;
	private IProgressMonitor monitor;
	private ITextListener textListener;

	private int work = 0;

	public void generateProjects(GeneratorData data, ITextListener listener, IProgressMonitor monitor) {
		this.data = data;
		this.monitor = monitor;
		this.textListener = listener;
		File f = new File(data.getTargetDir());
		if (!f.exists()) {
			f.mkdirs();
		}

		monitor.beginTask("Generating Application..", 15);

		createFeatureDirectory();
		createRelengDirectory();
		createApplicationDirectory();
		createProjectDirectory();

		buildProject();

	}

	private void buildProject() {

		final File dir = new File(data.getTargetDir() + "/" + data.getApplicationId() + "-releng");
		monitor.subTask("Compiling application..");
		work++;
		monitor.worked(work);
		addContent("Compiling application...");

		try {
			Thread processThread = new Thread() {
				public void run() {
					try {
	                    final Process p = startProcess(dir);

	                    Thread isReadThread = generateReadThread(p);

	                    // start reading thread
	                    isReadThread.start();
	                    // wait the build process to end
	                    p.waitFor();
	                    // stop read process
	                    isReadThread.interrupt();
	                    
	                    monitor.done();
                    } catch (Exception e) {
                    	Activator.logException(e);
                    	throw new RuntimeException(e);
                    }
				}

			};

			processThread.start();
			
			
		} catch (Exception e) {
			Activator.logException(e);

		}

	}

	protected Thread generateReadThread(final Process p) {
		Thread isReadThread = new Thread() {

			@Override
			public void run() {
				InputStream is = p.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(is));

				String lineSep = System.getProperty("line.separator");
				String line = null;
				while (true) {
					try {
						while ((line = reader.readLine()) != null) {
							addContent(line + lineSep);
						}
					} catch (IOException e) {
						Activator.logException(e);
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						System.out.println("Thread interrupted");
					}
				}
			}
		};
		return isReadThread;
	}

	protected Process startProcess(File dir) throws IOException {
		Runtime runtime = Runtime.getRuntime();
		String[] cmd = new String[] { "/home/niederhausen/java/apache-maven-3.0-beta-3/bin/mvn", "package" };
		final Process p = runtime.exec(cmd, new String[] {}, dir);
		return p;
	}

	private void createRelengDirectory() {
		monitor.subTask("Creating Release Engineering Project");
		work++;
		monitor.worked(work);
		addContent("Creating Release Engineering project... ");
		File dir = new File(data.getTargetDir() + "/" + data.getApplicationId() + "-releng");
		dir.mkdir();

		copyResource(dir, "/resources/releng");

		addContent("finished\n");

	}

	private void createApplicationDirectory() {
		monitor.subTask("Creating Application Project");
		work++;
		monitor.worked(work);
		addContent("Creating application project... ");
		File dir = new File(data.getTargetDir() + "/" + data.getApplicationId() + "-application");
		dir.mkdir();

		copyResource(dir, "/resources/application");

		addContent("finished\n");

	}

	private void createProjectDirectory() {
		monitor.subTask("Creating Model Project");
		work++;
		monitor.worked(work);
		addContent("Creating model project... ");
		File dir = new File(data.getTargetDir() + "/" + data.getApplicationId());
		dir.mkdir();

		copyResource(dir, "/resources/project");

		File srcFolder = new File(dir.getAbsolutePath() + "/src");
		srcFolder.mkdir();

		TopicMapSchema schema = ModelIndexer.getInstance().getTopicMapSchema();

		TMCLTopicMapBuilder builder = new TMCLTopicMapBuilder(schema, false);

		TopicMap topicMap = builder.createTopicMap();

		try {
			AranukaDescriptorFactory fac = new AranukaDescriptorFactory(builder.getTopicMapSystem(), topicMap,
			        data.getApplicationId() + ".model");
			CodeGenerator gen = fac.getCodeGenerator();
			gen.generateCode(new File(srcFolder.getAbsolutePath()));
		} catch (Exception e) {
			Activator.logException(e);
			throw new RuntimeException(e);
		}

		// now we copy the activator to the src dir

		String packagePath = data.getApplicationId().replaceAll("\\.", "/");
		File path = new File(srcFolder.getAbsolutePath() + "/" + packagePath);

		copyFile("resources/template/Activator.java", path);

		addContent("finished\n");

	}

	private void createFeatureDirectory() {
		monitor.subTask("Creating Feature Project");
		work++;
		monitor.worked(work);
		addContent("Creating feature project... ");
		File dir = new File(data.getTargetDir() + "/" + data.getApplicationId() + "-feature");
		dir.mkdir();

		copyResource(dir, "/resources/feature");

		addContent("finished\n");
	}

	/**
	 * Copies the resource found in srcPath to the target directory
	 * 
	 * @param dir
	 *            target directory
	 * @param srcPath
	 *            srcpath in the the bundle
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

					File subDir = new File(dir.getAbsolutePath() + "/" + tmp);
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
	 * 
	 * @param file
	 *            the path and name of the file
	 * @param folder
	 *            the target folder
	 */
	private void copyFile(String file, File folder) {
		try {
			StringBuilder builder = readFileEntry(file);

			String filename = file.substring(file.lastIndexOf("/") + 1);

			if ("name.product".equals(filename)) {
				filename = getValidName(data.getApplicationName()) + ".product";
			}

			String content = builder.toString();

			content = content.replaceAll(Matcher.quoteReplacement("$name"), data.getApplicationName());
			content = content.replaceAll(Matcher.quoteReplacement("$validname"),
			        getValidName(data.getApplicationName()));
			content = content.replaceAll(Matcher.quoteReplacement("$pluginId"), data.getApplicationId());

			File newFile = new File(folder.getAbsolutePath() + "/" + filename);
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
	 * 
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

	private void addContent(String text) {
		textListener.newText(text);
	}
}
