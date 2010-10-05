/**
 * 
 */
package de.topicmapslab.onotoa.genny.generator.dialog;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;

import de.topicmapslab.onotoa.genny.generator.ProjectGenerator;
import de.topicmapslab.onotoa.genny.generator.model.GeneratorData;
import de.topicmapslab.onotoa.genny.generator.ui.ITextListener;

/**
 * Dialog which executes the code generation, showing some progress
 * 
 * @author Hannes Niederhausen
 *
 */
public class GenerationProgressDialog extends Dialog implements IProgressMonitor, ITextListener {

	private ProgressBar progressBar;
	private StyledText styledText;
	private final GeneratorData data;
	private Label progressLabel;
	private Button closeButton;
	
	
	public GenerationProgressDialog(Shell parent, GeneratorData data) {
		super(parent);
		this.data = data;
		setShellStyle(getDefaultOrientation() | SWT.TITLE | SWT.APPLICATION_MODAL);
    }
	
	public int run() {
		
		if (getShell() == null || getShell().isDisposed()) {
			// create the window
			create();
		}
		
		getShell().getDisplay().asyncExec(new Runnable() {
			@Override
			public void run() {
				ProjectGenerator gen = new ProjectGenerator();
				gen.generateProjects(data, GenerationProgressDialog.this, GenerationProgressDialog.this);
			}
		});
		
		return open();
	}
	
	@Override
	public boolean close() {
		if (closeButton.getEnabled()) {
			setReturnCode(OK);
			return super.close();
		} else {
			return false;
		}
	}
	
	@Override
	protected int getShellStyle() {
	    return SWT.BORDER;
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite comp = new Composite(parent, SWT.NONE);
		comp.setLayout(new GridLayout());
		comp.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		progressBar = new ProgressBar(comp, SWT.SMOOTH);
		progressBar.setState(SWT.PAUSED);
		progressBar.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		progressLabel = new Label(comp, SWT.None);
		progressLabel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		styledText = new StyledText(comp, SWT.BORDER|SWT.MULTI|SWT.V_SCROLL|SWT.H_SCROLL|SWT.READ_ONLY);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.widthHint = 50;
		gd.heightHint = 50;
		styledText.setLayoutData(gd);
		
		
	    return comp;
	}
	
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		closeButton = createButton(parent, IDialogConstants.CLOSE_ID,
				IDialogConstants.CLOSE_LABEL, true);
		closeButton.setEnabled(false);
		
	}
	
	@Override
	protected void buttonPressed(int buttonId) {
		if (buttonId==IDialogConstants.CLOSE_ID) {
			close();
		}
	}
	
	@Override
	protected void configureShell(Shell newShell) {
		newShell.setText("Generating Application...");
		newShell.setSize(700, 500);
	    super.configureShell(newShell);
	}

	@Override
    public void beginTask(String name, int totalWork) {
	    setProgressBar(0, SWT.NORMAL, totalWork);
	    setProgressLabel(name);
    }

	@Override
    public void done() {
		
		getShell().getDisplay().syncExec(new Runnable() {
			@Override
			public void run() {
				setProgressBar(progressBar.getMaximum(), -1, -1);
				closeButton.setEnabled(true);			    
			}
		});
		
    }

	@Override
    public void internalWorked(double work) {
		// not needed
    }

	@Override
    public boolean isCanceled() {
	    return false;
    }

	@Override
    public void setCanceled(boolean value) {
		
    }

	@Override
    public void setTaskName(String name) {
		setProgressLabel(name);	    
    }

	@Override
    public void subTask(String name) {
		setProgressLabel(name);
    }

	@Override
    public void worked(int work) {
		setProgressBar(work, -1, -1);
    }

	@Override
    public void newText(String text) {
		final String newText = text;
	    getShell().getDisplay().syncExec(new Runnable() {
	    	@Override
	    	public void run() {
	    		// set new text
	    		styledText.replaceTextRange(styledText.getCharCount(), 0, newText);
	    		
	    		// and set selection to scroll down. setting the caret to the beginning of the last line
	    		int lineOffset = styledText.getLineAtOffset(styledText.getCharCount()-3);
	    		lineOffset = styledText.getOffsetAtLine(lineOffset);
	    			
	    		styledText.setCaretOffset(lineOffset);
	    		styledText.setSelection(lineOffset, lineOffset);
	    	}
	    });    
    }
	
    public void setProgressLabel(String text) {
		final String newText = text;
	    getShell().getDisplay().syncExec(new Runnable() {
	    	@Override
	    	public void run() {
	    		progressLabel.setText(newText);
	    	}
	    });    
    }
    
    public void setProgressBar(final int sel, final int state, final int totalWork) {
	    getShell().getDisplay().syncExec(new Runnable() {
	    	@Override
	    	public void run() {
	    		if (totalWork>=0)
	    			progressBar.setMaximum(totalWork);
	    		if (state!=-1)
	    			progressBar.setState(state);
	    		progressBar.setSelection(sel);
	    	}
	    });    
    }
	
}
