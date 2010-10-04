/**
 * 
 */
package de.topicmapslab.onotoa.genny.generator.model;

import de.topicmapslab.kuria.annotation.widgets.Check;
import de.topicmapslab.kuria.annotation.widgets.Directory;
import de.topicmapslab.kuria.annotation.widgets.Editable;
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
	public String targetDir;
	
	@TextField
	public String applicationId;
	
	@TextField
	public String applicationName;
	
	@Check
	public boolean deleteTemporaryFiles;

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
	
	
	
}
