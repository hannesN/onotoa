/**
 * 
 */
package de.topicmapslab.onotoa.genny.generator.model;

import static de.topicmapslab.onotoa.genny.generator.preferences.IPreferenceConstants.*;

import org.eclipse.jface.preference.IPreferenceStore;

import de.topicmapslab.kuria.annotation.widgets.Check;
import de.topicmapslab.kuria.annotation.widgets.Directory;
import de.topicmapslab.kuria.annotation.widgets.Editable;
import de.topicmapslab.kuria.annotation.widgets.Hidden;
import de.topicmapslab.kuria.annotation.widgets.TextField;

/**
 * Class containing the generator data.
 * 
 * @author Hannes Niederhausen
 *
 */
@Editable
public class GeneratorData {

	@Directory
	private String targetDir;
	
	@TextField
	private String applicationId;
	
	@TextField
	private String applicationName;

	@Check
	private boolean deleteTemporaryFiles;
	
	@Hidden
	private String mavenPath;
	
	@Hidden
	private String mavenOpts;

	/**
     * @return the targetDir
     */
    public String getTargetDir() {
    	return targetDir;
    }

	/**
     * @param targetDir the targetDir to set
     */
    public void setTargetDir(String targetDir) {
    	this.targetDir = targetDir;
    }

	/**
     * @return the applicationId
     */
    public String getApplicationId() {
    	return applicationId;
    }

	/**
     * @param applicationId the applicationId to set
     */
    public void setApplicationId(String applicationId) {
    	this.applicationId = applicationId;
    }

	/**
     * @return the applicationName
     */
    public String getApplicationName() {
    	return applicationName;
    }

	/**
     * @param applicationName the applicationName to set
     */
    public void setApplicationName(String applicationName) {
    	this.applicationName = applicationName;
    }

	/**
     * @return the deleteTemporaryFiles
     */
    public boolean isDeleteTemporaryFiles() {
    	return deleteTemporaryFiles;
    }

	/**
     * @param deleteTemporaryFiles the deleteTemporaryFiles to set
     */
    public void setDeleteTemporaryFiles(boolean deleteTemporaryFiles) {
    	this.deleteTemporaryFiles = deleteTemporaryFiles;
    }
	
	public void setMavenpath(String mavenpath) {
	    this.mavenPath = mavenpath;
    }
	
	public String getMavenpath() {
	    return mavenPath;
    }
	
	public void setMavenOpts(String mavenOpts) {
	    this.mavenOpts = mavenOpts;
    }
	
	public String getMavenOpts() {
	    return mavenOpts;
    }
	
	public void init(IPreferenceStore ps) {
		applicationId = ps.getString(P_LAST_PROJECTID);
		applicationName = ps.getString(P_LAST_PROJECTNAME);
		targetDir = ps.getString(P_LAST_TARGET);
	}
	
	public void save(IPreferenceStore ps) {
		ps.setValue(P_LAST_PROJECTID, applicationId);
		ps.setValue(P_LAST_PROJECTNAME, applicationName);
		ps.setValue(P_LAST_TARGET, targetDir);
	}
}
