package de.topicmapslab.tmcledit.diagram.preferences;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.PlatformUI;

import de.topicmapslab.tmcledit.diagram.DiagramActivator;
import de.topicmapslab.tmcledit.diagram.editor.TMCLDiagramEditor;

/**
 * Preference Page for the color schemes
 * 
 * @author Hannes Niederhausen
 */
public class ColorSchemePreferencePage extends PreferencePage implements
		IWorkbenchPreferencePage {

	private Button addButton;
	private Button removeButton;
	private Button editButton;

	private List<ColorScheme> schemeList;
	private ColorScheme selected;
	private CheckboxTableViewer viewer;
	private Button exportButton;
	private Button importButton;

	/**
	 * Constructor
	 */
	public ColorSchemePreferencePage() {
		super();
		setPreferenceStore(DiagramActivator.getDefault().getPreferenceStore());
		setDescription("Configure the visualization of diagram elements.");
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	public void init(IWorkbench workbench) {
		schemeList = new ArrayList<ColorScheme>(DiagramActivator.getDefault()
				.getSchemeList());
		schemeList.add(0, ColorScheme.getDefault());
		selected = DiagramActivator.getCurrentColorScheme();
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public boolean performOk() {
		save();
		return true;
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	protected void performApply() {
		save();
	}

	private void save() {
		IPreferenceStore preferenceStore = DiagramActivator.getDefault()
				.getPreferenceStore();

		preferenceStore.setValue(PreferenceConstants.P_ACTIVE_SCHEME,
				selected.getName());

		SchemeXMLBuilder builder = new SchemeXMLBuilder();
		schemeList.remove(ColorScheme.getDefault());
		String tmp = builder.buildSchemeXML(schemeList);

		preferenceStore.setValue(PreferenceConstants.P_COLOR_SCHEMES, tmp);

		DiagramActivator.getDefault().resetCache();

		IEditorPart activeEditor = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		if (activeEditor instanceof TMCLDiagramEditor) {
			((TMCLDiagramEditor) activeEditor).refresh();
		}
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	protected Control createContents(Composite parent) {
		Composite comp = new Composite(parent, SWT.NONE);
		comp.setLayout(new GridLayout(2, false));

		viewer = CheckboxTableViewer
				.newCheckList(comp, SWT.BORDER | SWT.SINGLE);
		Control table = viewer.getControl();
		table.setLayoutData(new GridData(GridData.FILL_BOTH));

		viewer.setContentProvider(new ArrayContentProvider());
		viewer.setLabelProvider(new SchemeLabelProvider());
		viewer.addCheckStateListener(new ICheckStateListener() {

			public void checkStateChanged(CheckStateChangedEvent event) {
				if ((event.getElement() == selected) && (!event.getChecked())
						&& (viewer.getCheckedElements().length == 0)) {
					viewer.setChecked(selected, true);
					return;
				}
				if ((event.getElement() != selected) && event.getChecked()) {
					Object old = selected;
					viewer.setChecked(old, false);
				}
				selected = (ColorScheme) event.getElement();
			}
		});

		viewer.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				removeButton.setEnabled(!event.getSelection().isEmpty());
				editButton.setEnabled(!event.getSelection().isEmpty());
			}
		});

		viewer.addDoubleClickListener(new IDoubleClickListener() {

			public void doubleClick(DoubleClickEvent event) {
				ColorScheme c = (ColorScheme) ((IStructuredSelection) viewer
						.getSelection()).getFirstElement();
				openEditorDialog(c);
			}
		});

		viewer.setInput(schemeList);
		if (selected != null)
			viewer.setChecked(selected, true);

		createButtonBar(comp);

		removeButton.setEnabled(false);
		editButton.setEnabled(false);

		return comp;
	}

	private void createButtonBar(Composite parent) {
		Composite comp = new Composite(parent, SWT.NONE);

		comp.setLayout(new GridLayout());
		GridData gd = new GridData();
		gd.widthHint = 130;
		gd.verticalAlignment = SWT.CENTER;
		comp.setLayoutData(gd);

		addButton = new Button(comp, SWT.PUSH);
		addButton.setText("Add...");
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.verticalAlignment = SWT.CENTER;
		addButton.setLayoutData(gd);

		editButton = new Button(comp, SWT.PUSH);
		editButton.setText("Edit...");
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.verticalAlignment = SWT.CENTER;
		editButton.setLayoutData(gd);

		removeButton = new Button(comp, SWT.PUSH);
		removeButton.setText("Remove...");
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.verticalAlignment = SWT.CENTER;
		removeButton.setLayoutData(gd);

		importButton = new Button(comp, SWT.PUSH);
		importButton.setText("Import...");
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.verticalAlignment = SWT.CENTER;
		importButton.setLayoutData(gd);

		exportButton = new Button(comp, SWT.PUSH);
		exportButton.setText("Export...");
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.verticalAlignment = SWT.CENTER;
		exportButton.setLayoutData(gd);

		hookButtonListener();

	}

	private void startImport() {
		FileDialog dlg = new FileDialog(getShell(), SWT.OPEN);
		dlg.setFilterExtensions(new String[] { "*.xml" });

		String filename = dlg.open();
		if (filename == null)
			return;

		if (!filename.endsWith(".xml"))
			filename += ".xml";

		File file = new File(filename);

		try {
			List<ColorScheme> tmpList = SchemesXMLHandler
					.parseSchemeList(new FileInputStream(file));
			if (tmpList == null) {
				MessageDialog.openError(getShell(), "Invalid file",
						"The given xml file does not containt scheme data!");
				return;
			}
			schemeList = new ArrayList<ColorScheme>(tmpList);
			schemeList.add(0, ColorScheme.getDefault());
			viewer.setInput(schemeList);
			viewer.setAllChecked(false);
			viewer.setChecked(ColorScheme.getDefault(), true);
			selected = ColorScheme.getDefault();
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}

	}

	private void startExport() {
		FileDialog dlg = new FileDialog(getShell(), SWT.SAVE);
		dlg.setFilterExtensions(new String[] { "*.xml" });

		String filename = dlg.open();
		if (filename == null)
			return;
		schemeList.remove(ColorScheme.getDefault());
		String xml = new SchemeXMLBuilder().buildSchemeXML(schemeList, true);

		File file = new File(filename);
		if (file.exists()) {
			if (!MessageDialog.openQuestion(getShell(),
					"File already exists...",
					"Do you want to overwrigt the existing file?")) {
				return;
			}
		}
		try {
			FileWriter writer = new FileWriter(file);
			writer.write(xml);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			schemeList.add(0, ColorScheme.getDefault());
		}
	}

	private void hookButtonListener() {
		addButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openEditorDialog(null);
			}
		});

		editButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				IStructuredSelection sel = (IStructuredSelection) viewer
						.getSelection();

				ColorScheme el = (ColorScheme) sel.getFirstElement();
				if (el != ColorScheme.getDefault())
					openEditorDialog(el);
			}
		});

		removeButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				IStructuredSelection sel = (IStructuredSelection) viewer
						.getSelection();
				Object firstElement = sel.getFirstElement();
				ColorScheme el = (ColorScheme) firstElement;
				if (el != ColorScheme.getDefault()) {
					if (viewer.getChecked(firstElement)) {
						viewer.setChecked(ColorScheme.getDefault(), true);
					}
					schemeList.remove(firstElement);
					viewer.refresh();
				}
			}
		});

		importButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				startImport();
			}
		});

		exportButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				startExport();
			}
		});
	}

	protected void openEditorDialog(ColorScheme scheme) {
		ColorSchemeEditor ed = null;
		Shell shell = addButton.getShell();
		if (scheme == null)
			ed = new ColorSchemeEditor(shell);
		else
			ed = new ColorSchemeEditor(shell, scheme);

		if (ed.open() == Dialog.OK) {
			if (scheme == null) {
				schemeList.add(ed.getScheme());
				viewer.refresh();
			} else {
				viewer.refresh(scheme);
			}
		}
	}

	private class SchemeLabelProvider implements ITableLabelProvider {

		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		public String getColumnText(Object element, int columnIndex) {
			ColorScheme scheme = (ColorScheme) element;
			return scheme.getName();
		}

		public void addListener(ILabelProviderListener listener) {
		}

		public void dispose() {
		}

		public boolean isLabelProperty(Object element, String property) {
			return false;
		}

		public void removeListener(ILabelProviderListener listener) {
		}

	}
}