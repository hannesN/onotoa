/**
 * 
 */
package de.topicmapslab.onotoa.genny.generator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
import de.topicmapslab.onotoa.genny.generator.util.ProcessInputReader;
import de.topicmapslab.tmcledit.export.builder.TMCLTopicMapBuilder;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;

/**
 * @author Hannes Niederhausen
 * 
 */
public class ProjectGenerator {

	private static final String LINE_SEPARATOR = System.getProperty("line.separator");
	private GeneratorData data;
	private IProgressMonitor monitor;
	private ITextListener textListener;

	private int work = 0;

	/**
	 * Generates the projects including code
	 * @param data the data for the generator
	 * @param listener a {@link ITextListener} to get text from the non UI thread 
	 * @param monitor a progress monitor
	 */
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
						monitor.done();
						addError(e);
						throw new RuntimeException(e);
					}
				}

			};

			processThread.start();

		} catch (Exception e) {
			Activator.logException(e);
			addError(e);
		}

	}

	protected Thread generateErrorThread(final Process p) {
		Thread isReadThread = new ProcessInputReader(p, new ITextListener() {
			@Override
			public void newText(String text) {
				newText(text, false);
			}
			
			@Override
			public void newText(String text, boolean forceShow) {
				addContent(text+LINE_SEPARATOR);			    
			}
		});
		return isReadThread;
	}

	protected Thread generateReadThread(final Process p) {
		Thread isReadThread = new ProcessInputReader(p, new ITextListener() {
			@Override
			public void newText(String text) {
				newText(text, false);
			}
			
			@Override
			public void newText(String text, boolean forceShow) {
				addContent(text+LINE_SEPARATOR);			    
			}
		});
		return isReadThread;
	}

	protected Process startProcess(File dir) throws IOException {
		// String[] cmd = new String[] { data.getMavenpath()+"/bin/mvn",
		// "package" };

		String osName = System.getProperty("os.name");
		String javaHome = System.getProperty("java.home");
		
		ProcessBuilder pb = new ProcessBuilder(data.getMavenpath() + "/bin/mvn", "--update-snapshots", "package");
		if (osName.toLowerCase().startsWith("windows")) {
			pb = new ProcessBuilder("cmd.exe", "/C", data.getMavenpath() + "/bin/mvn", "--update-snapshots", "package");
		} 
				
		pb.environment().put("MAVEN_OPTS", data.getMavenOpts());
		pb.environment().put("JAVA_HOME", javaHome);
		pb.directory(dir);

		final Process p = pb.start();
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
			        data.getApplicationId() + ".model", true, true);
			CodeGenerator gen = fac.getCodeGenerator();
			gen.generateCode(new File(srcFolder.getAbsolutePath()));
		} catch (Exception e) {
			addError(e);
			Activator.logException(e);
			monitor.done();
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
					if (isBin(o)) {
						copyBinaryFile(o, dir);
					} else {
						copyFile(o, dir);
					}
				}
			}
		} catch (Exception e) {
			Activator.logException(e);
			monitor.done();
			addError(e);
			throw new RuntimeException(e);
		}
	}

	private void copyBinaryFile(String file, File folder) {
		try {
	        URL entry = Activator.getDefault().getBundle().getEntry(file);
	        InputStream is = entry.openStream();
	        String filename = file.substring(file.lastIndexOf("/") + 1);
	        File newFile = new File(folder.getAbsolutePath() + "/" + filename);
	        if (newFile.exists())
	        	newFile.delete();

	        FileOutputStream fos = new FileOutputStream(newFile);
	        int c = 0;
	        byte[] buffer = new byte[1024];
	        do {
	        	c = is.read(buffer);
	        	if (c!=-1)
	        		fos.write(buffer, 0, c);
	        } while (c!=-1);
	        
	        fos.close();
	        is.close();
        } catch (Exception e) {
        	Activator.logException(e);
        	addError(e);
        	monitor.done();
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
			addError(e);
			monitor.done();
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

	private void addError(Throwable e) {
		textListener.newText(LINE_SEPARATOR+LINE_SEPARATOR+"An error ccurred:"+e.getMessage()+LINE_SEPARATOR, true);
	}
	
	private boolean isBin(String o) {
		String[] binSuffixes = new String[] { ".bmp", "*.gif", "*.png" };

		for (String s : binSuffixes) {
			if (o.endsWith(s))
				return true;
		}

		return false;
	}
}
