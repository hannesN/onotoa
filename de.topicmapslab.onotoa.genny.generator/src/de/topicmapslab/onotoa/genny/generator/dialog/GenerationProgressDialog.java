/**
 * 
 */
package de.topicmapslab.onotoa.genny.generator.dialog;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
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

	private Button hideButton;

	private StringBuilder textCache;

	/**
	 * 
	 * Constructor
	 * 
	 * @param parent the parent shell
	 * @param data the data for the generator
	 */
	public GenerationProgressDialog(Shell parent, GeneratorData data) {
		super(parent);
		this.data = data;
		setShellStyle(getDefaultOrientation() | SWT.TITLE |SWT.BORDER | SWT.APPLICATION_MODAL);
	}

	/**
	 * Starts the generation process
	 * @return value from the dialog, see {@link Dialog.open()}
	 */
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
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.widthHint = 650;
		progressLabel.setLayoutData(gd);

		hideButton = new Button(comp, SWT.PUSH);
		hideButton.setText("Details >>");
		gd = new GridData();
		gd.horizontalAlignment = SWT.RIGHT;
		hideButton.setLayoutData(gd);

		textCache = new StringBuilder(500);

		hookButtonListener();

		return comp;
	}

	/**
	 * @param comp
	 */
	protected void createText(Composite comp) {
		GridData gd = new GridData(GridData.FILL_BOTH);
		styledText = new StyledText(comp, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL | SWT.READ_ONLY);
		gd.widthHint = 650;
		gd.heightHint = 250;
		styledText.setLayoutData(gd);
		styledText.setVisible(true);
		getShell().setSize(700, 500);
	}

	private void hookButtonListener() {
		hideButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				toggleTextFieldVisible();

			}

			
		});
	}

	/**
     * 
     */
    public void toggleTextFieldVisible() {
        if (textCache == null) {
			hideButton.setText("Details >>");
			styledText.setVisible(false);
			textCache = new StringBuilder(styledText.getText());
			getShell().setSize(700, 150);
		} else {
			hideButton.setText("Details <<");

			if (styledText == null) {
				createText((Composite) getDialogArea());
			}
			styledText.setText(textCache.toString());
			styledText.setVisible(true);
			// and set selection to scroll down. setting the caret to
			// the beginning of the last line
			int lineOffset = styledText.getLineAtOffset(styledText.getCharCount() - 3);
			lineOffset = styledText.getOffsetAtLine(lineOffset);

			styledText.setCaretOffset(lineOffset);
			styledText.setSelection(lineOffset, lineOffset);
			
			textCache = null;
		}
		GenerationProgressDialog.this.getShell().pack(true);
    }
	
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		closeButton = createButton(parent, IDialogConstants.CLOSE_ID, IDialogConstants.CLOSE_LABEL, true);
		closeButton.setEnabled(false);

	}

	@Override
	protected void buttonPressed(int buttonId) {
		if (buttonId == IDialogConstants.CLOSE_ID) {
			close();
		}
	}

	@Override
	protected void configureShell(Shell newShell) {
		newShell.setText("Generating Application...");
		newShell.setSize(700, 150);
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
		newText(text, false);
	}
	
	@Override
	public void newText(String text, boolean forceShow) {
		final String newText = text;
		
		if (textCache != null) {
			textCache.append(text);
			if (forceShow)
				toggleTextFieldVisible();
		} else {
			getShell().getDisplay().syncExec(new Runnable() {
				@Override
				public void run() {
					// set new text
					styledText.replaceTextRange(styledText.getCharCount(), 0, newText);

					// and set selection to scroll down. setting the caret to
					// the beginning of the last line
					int lineOffset = styledText.getLineAtOffset(styledText.getCharCount() - 3);
					lineOffset = styledText.getOffsetAtLine(lineOffset);

					styledText.setCaretOffset(lineOffset);
					styledText.setSelection(lineOffset, lineOffset);
				}
			});
		}
	}

	/**
	 * Sets the text for the label below the progress bar	
	 * @param text
	 */
	public void setProgressLabel(String text) {
		final String newText = text;
		getShell().getDisplay().syncExec(new Runnable() {
			@Override
			public void run() {
				progressLabel.setText(newText);
			}
		});
	}

	/**
	 * Sets the values of the {@link ProgressBar} values
	 * 
	 * @param sel the current selection, which must be between 0 and totalWork
	 * @param state the state, see {@link ProgressBar.state()} or -1 if it shouldn't be set
	 * @param totalWork the value for the maximum selection or -1 if it shouldn't be set
	 */
	public void setProgressBar(final int sel, final int state, final int totalWork) {
		getShell().getDisplay().syncExec(new Runnable() {
			@Override
			public void run() {
				if (totalWork >= 0)
					progressBar.setMaximum(totalWork);
				if (state != -1)
					progressBar.setState(state);
				progressBar.setSelection(sel);
			}
		});
	}

}
