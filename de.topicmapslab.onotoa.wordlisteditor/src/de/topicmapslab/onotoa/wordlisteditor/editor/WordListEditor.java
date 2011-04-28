/*******************************************************************************
 * Copyright (c) 2008-2011 Topic Maps Lab and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *      Hannes Niederhausen - initial API and implementation
 ******************************************************************************/
package de.topicmapslab.onotoa.wordlisteditor.editor;

import java.util.ArrayList;
import java.util.EventObject;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ColumnViewerEditor;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationStrategy;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TableViewerEditor;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.part.EditorPart;

import de.topicmapslab.onotoa.selection.service.IOnotoaSelectionService;
import de.topicmapslab.onotoa.wordlisteditor.Activator;
import de.topicmapslab.onotoa.wordlisteditor.editor.actions.SelectAllAction;
import de.topicmapslab.onotoa.wordlisteditor.editor.input.WordListEditorInput;
import de.topicmapslab.onotoa.wordlisteditor.emfcommands.AddWordCommand;
import de.topicmapslab.onotoa.wordlisteditor.emfcommands.ModifyWordCommand;
import de.topicmapslab.onotoa.wordlisteditor.emfcommands.ModifyWordCommentCommand;
import de.topicmapslab.onotoa.wordlisteditor.emfcommands.ModifyWordTypeCommand;
import de.topicmapslab.onotoa.wordlisteditor.emfcommands.RemoveWordCommand;
import de.topicmapslab.onotoa.wordlisteditor.model.Word;
import de.topicmapslab.onotoa.wordlisteditor.model.WordListContainer;
import de.topicmapslab.tmcledit.model.Annotation;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.commands.CreateTopicTypeCommand;
import de.topicmapslab.tmcledit.model.util.ImageProvider;
import de.topicmapslab.tmcledit.model.views.ModelView;

/**
 * Editor which provides a table to create a word list and and specifies topic
 * types using the words as names.
 * 
 * @author Hannes Niederhausen
 * 
 */
public class WordListEditor extends EditorPart implements CommandStackListener {

	private static final String WORDLIST_ANNOTATION_KEY = "de.topicmapslab.onotoa.wordlist";

	/**
	 * Editor ID
	 */
	public final static String ID = "de.topicmapslab.onotoa.wordlisteditor.editor.WordListEditor";

	private ModelView modelView;

	private File file;

	private Button createSelectedTypesButton;
	
	private Button createAllTypesButton;

	private CheckboxTableViewer viewer;

	private Button removeButton;

	private CommandStack commandStack;

	private DirtyAdapter dirtyAdapter;

	private Text filterText;

	private Text inputText;

	private Button addButton;

	private SelectAllAction selectAllAction;

	private SelectAllAction deselectAllAction;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		if (!(input instanceof WordListEditorInput))
			throw new PartInitException("Illegal Editor Input");

		setSite(site);
		setInput(input);

		this.modelView = (ModelView) input.getAdapter(ModelView.class);
		this.commandStack = this.modelView.getCommandStack();

		assert (this.commandStack != null);

		this.commandStack.addCommandStackListener(this);

		final IOnotoaSelectionService onotoaSelectionService = Activator.getDefault().getOnotoaSelectionService();
		this.file = onotoaSelectionService.getOnotoaFile();

		// add a listener to close the editor if the file is set to null
		// this happens only on closing the ModelView
		onotoaSelectionService.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				if (onotoaSelectionService.getSelection().isEmpty()) {
					getEditorSite().getPage().closeEditor(WordListEditor.this, false);
				}

			}
		});

		dirtyAdapter = new DirtyAdapter();
		this.file.eAdapters().add(dirtyAdapter);

		registerGlobalActions();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void createPartControl(Composite parent) {
		FormToolkit toolkit = new FormToolkit(parent.getDisplay());

		Composite comp = toolkit.createComposite(parent);
		GridLayout layout = new GridLayout();
		layout.horizontalSpacing = 5;
		comp.setLayout(layout);

		// create new word and filter texts
		createInputWidgetes(comp, toolkit);

		// table composite with table layout
		Composite tableComp = toolkit.createComposite(comp);
		tableComp.setLayout(new TableColumnLayout());
		GridData gd = new GridData(GridData.FILL_BOTH);
		tableComp.setLayoutData(gd);

		Table table = toolkit.createTable(tableComp, SWT.BORDER | SWT.FULL_SELECTION | SWT.CHECK);
		initTable(table);

		createTableButtonBar(comp, toolkit);

		gd = new GridData();
		gd.widthHint = 150;
		GridDataFactory fac = GridDataFactory.createFrom(gd);
		
		createSelectedTypesButton = toolkit.createButton(comp, "Create selected types", SWT.PUSH);
		createSelectedTypesButton.setToolTipText("Creates Types for the selected words.");
		fac.applyTo(createSelectedTypesButton);
		
		createAllTypesButton = toolkit.createButton(comp, "Create types", SWT.PUSH);
		createAllTypesButton.setToolTipText("Creates Types for every word.");
		fac.applyTo(createAllTypesButton);
		
		updateInput();

		updateButtons();
		hookListeners();
		createContextMenu();
	}

	/**
	 * @param parent
	 * @param toolkit
	 */
	private void createInputWidgetes(Composite parent, FormToolkit toolkit) {
		Composite inputComp = toolkit.createComposite(parent);
		GridLayout layout = new GridLayout(3, false);
		layout.marginWidth = 0;
		inputComp.setLayout(layout);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		inputComp.setLayoutData(gd);

		toolkit.createLabel(inputComp, "New Word:");
		inputText = toolkit.createText(inputComp, "", SWT.BORDER);
		inputText.setLayoutData(gd);

		inputText.addKeyListener(new KeyAdapter() {
			/**
			 * {@inheritDoc}
			 */
			@Override
			public void keyPressed(KeyEvent e) {
				if ( (e.keyCode == SWT.CR) || (e.keyCode == SWT.KEYPAD_CR)) {
					if (addButton.isEnabled()) {
						addWord();
					}
				}
			}
		});

		inputText.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				String text = inputText.getText();

				WordListContainer wlc = (WordListContainer) viewer.getInput();
				addButton.setEnabled(!((text.length() == 0) || (wlc.containsWord(text))));
			}
		});
		addButton = toolkit.createButton(inputComp, "&Add", SWT.PUSH);
		addButton.setEnabled(false);
		addButton.addSelectionListener(new SelectionAdapter() {
			/**
			 * {@inheritDoc}
			 */
			@Override
			public void widgetSelected(SelectionEvent e) {
				addWord();
			}
		});

		toolkit.createLabel(inputComp, "Filter Words:");

		filterText = toolkit.createText(inputComp, "", SWT.BORDER | SWT.SEARCH | SWT.CANCEL);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		filterText.setLayoutData(gd);
		filterText.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				viewer.refresh();
			}
		});
	}

	
	private void updateInput() {
		Annotation a = getWordListAnnotation();
		if ((a.getValue() == null) || (a.getValue().length() == 0)) {
			WordListContainer tmp = new WordListContainer();
			viewer.setInput(tmp);
		} else {
			viewer.setInput(WordListContainer.parseXML(a.getValue()));
		}
	}

	/**
	 * hooks the listeners to the buttons and widgets
	 */
	private void hookListeners() {

		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				updateButtons();
			}
		});

		viewer.addFilter(new ViewerFilter() {

			@Override
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				String tmp = filterText.getText();
				if (tmp.length() == 0)
					return true;

				if (!((WordListContainer) viewer.getInput()).contains(element))
					return true;

				return ((Word) element).getWord().startsWith(tmp);
			}
		});
		
		removeButton.addSelectionListener(new SelectionAdapter() {
			/**
			 * {@inheritDoc}
			 */
			@Override
			public void widgetSelected(SelectionEvent e) {

				CompoundCommand compundCmd = new CompoundCommand();
				WordListContainer container = (WordListContainer) viewer.getInput();

				for (Object w : viewer.getCheckedElements()) {
					compundCmd.append(new RemoveWordCommand(container, (Word) w));
				}

				commandStack.execute(compundCmd);
				viewer.refresh();
			}
		});

		createAllTypesButton.addSelectionListener(new SelectionAdapter() {
			/**
			 * {@inheritDoc}
			 */
			@Override
			public void widgetSelected(SelectionEvent e) {
				createTopicTypes(true);
			}
		});
		
		createSelectedTypesButton.addSelectionListener(new SelectionAdapter() {
			/**
			 * {@inheritDoc}
			 */
			@Override
			public void widgetSelected(SelectionEvent e) {
				createTopicTypes(false);
			}
		});
	}

	/**
     * 
     */
	private void registerGlobalActions() {
		ActionRegistry ar = (ActionRegistry) modelView.getAdapter(ActionRegistry.class);
		if (ar != null) {
			IActionBars actionBars = getEditorSite().getActionBars();

			String tmp = ActionFactory.UNDO.getId();
			actionBars.setGlobalActionHandler(tmp, (IAction) ar.getAction(tmp));

			tmp = ActionFactory.REDO.getId();
			actionBars.setGlobalActionHandler(tmp, (IAction) ar.getAction(tmp));

			tmp = ActionFactory.SAVE.getId();
			actionBars.setGlobalActionHandler(tmp, (IAction) ar.getAction(tmp));

			tmp = ActionFactory.CLOSE.getId();
			actionBars.setGlobalActionHandler(tmp, (IAction) ar.getAction(tmp));
		}

	}
	
	/**
	 * Updates the button states according to the selection in the viewer.
	 */
	public void updateButtons() {
		removeButton.setEnabled(viewer.getCheckedElements().length != 0);
		createAllTypesButton.setEnabled(!((WordListContainer) viewer.getInput()).isEmpty());
		createSelectedTypesButton.setEnabled(viewer.getCheckedElements().length != 0);
	}

	/**
	 * @param comp
	 * @param toolkit
	 */
	private void createTableButtonBar(Composite parent, FormToolkit toolkit) {

		GridData gd = new GridData();
		gd.widthHint = 120;
		gd.horizontalAlignment = SWT.RIGHT;

		removeButton = toolkit.createButton(parent, "Remove Words", SWT.PUSH);
		removeButton.setLayoutData(gd);

	}

	/**
	 * Creates the table viewer and columns.
	 * 
	 * @param table
	 *            the table which will be used by the wrapper
	 */
	private void initTable(final Table table) {

		TableColumnLayout layout = (TableColumnLayout) table.getParent().getLayout();
		table.setLinesVisible(true);
		table.setHeaderVisible(true);

		table.setMenu(new Menu(table));
		
		table.addKeyListener(new KeyAdapter() {
			/**
			 * {@inheritDoc}
			 */
			@Override
			public void keyPressed(KeyEvent e) {
				if ((e.keyCode == (int) 'c') && ((e.stateMask & SWT.CTRL) != 0)) {
					Clipboard clipboard = new Clipboard(e.widget.getDisplay());

					StringBuilder builder = new StringBuilder();

					String lineSeparator = System.getProperty("line.separator");
					IStructuredSelection sel = (IStructuredSelection) viewer.getSelection();

					Iterator<?> it = sel.iterator();
					while (it.hasNext()) {
						Word w = (Word) it.next();
						builder.append("\"");
						builder.append(w.getWord());
						builder.append("\"");
						builder.append(";");
						builder.append(w.getType().getName());
						if (it.hasNext()) {
							builder.append(lineSeparator);
						}
					}
					clipboard.setContents(new Object[] { builder.toString() },
					        new Transfer[] { TextTransfer.getInstance() });

					clipboard.dispose();
				}
			}
		});

		viewer = new CheckboxTableViewer(table);
		viewer.setContentProvider(ArrayContentProvider.getInstance());
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				removeButton.setEnabled(viewer.getCheckedElements().length != 0);
			}
		});

		ColumnViewerEditorActivationStrategy actSupport = new ColumnViewerEditorActivationStrategy(viewer) {
			protected boolean isEditorActivationEvent(ColumnViewerEditorActivationEvent event) {
				return event.eventType == ColumnViewerEditorActivationEvent.TRAVERSAL
				        || event.eventType == ColumnViewerEditorActivationEvent.MOUSE_CLICK_SELECTION
				        || (event.eventType == ColumnViewerEditorActivationEvent.KEY_PRESSED && event.keyCode == SWT.CR)
				        || event.eventType == ColumnViewerEditorActivationEvent.PROGRAMMATIC;
			}
		};

		TableViewerEditor.create(viewer, null, actSupport, ColumnViewerEditor.TABBING_HORIZONTAL
		        | ColumnViewerEditor.TABBING_MOVE_TO_ROW_NEIGHBOR | ColumnViewerEditor.TABBING_VERTICAL
		        | ColumnViewerEditor.KEYBOARD_ACTIVATION);

		// word column
		TableViewerColumn tvc = new TableViewerColumn(viewer, SWT.NONE);
		layout.setColumnData(tvc.getColumn(), new ColumnWeightData(1));
		tvc.getColumn().setText("Word");
		tvc.setLabelProvider(new CellLabelProvider() {

			@Override
			public void update(ViewerCell cell) {
				cell.setText(((Word) cell.getElement()).getWord());
			}
		});

		tvc.setEditingSupport(new EditingSupport(viewer) {

			@Override
			protected void setValue(Object element, Object value) {
				WordListContainer wlc = (WordListContainer) viewer.getInput();

				Word w = (Word) element;
				if (w.getWord().equals(value))
					return;

				if (wlc.containsWord((String) value)) {
					MessageDialog.openInformation(table.getShell(), "Word already entered", "The word <" + value
					        + "> was already entered");
					return;
				}

				AbstractCommand cmd = null;
				cmd = new ModifyWordCommand(w, (String) value);
				commandStack.execute(cmd);
				viewer.refresh(element);

			}

			@Override
			protected Object getValue(Object element) {
				Word w = (Word) element;
				return w.getWord();
			}

			@Override
			protected CellEditor getCellEditor(Object element) {
				return new TextCellEditor(table);
			}

			@Override
			protected boolean canEdit(Object element) {
				return true;
			}
		});

		// create column sorter
		new AbstractColumnViewerSorter(viewer, tvc) {

			@Override
			public String getText(Object element) {
				return ((Word) element).getWord();
			}
		};

		// type column
		tvc = new TableViewerColumn(viewer, SWT.NONE);
		tvc.getColumn().setText("Type");
		layout.setColumnData(tvc.getColumn(), new ColumnWeightData(1));
		tvc.setLabelProvider(new CellLabelProvider() {

			@Override
			public void update(ViewerCell cell) {
				String name = ((Word) cell.getElement()).getType().getName();
				cell.setText(name);
				cell.setImage(ImageProvider.getImageOfKindOfTopic(KindOfTopicType.getByName(name)));
				
				
			}
		});
		tvc.setEditingSupport(new EditingSupport(viewer) {
			final String[] ITEMS = { KindOfTopicType.TOPIC_TYPE.getName(), KindOfTopicType.OCCURRENCE_TYPE.getName(),
			        KindOfTopicType.NAME_TYPE.getName(), KindOfTopicType.ROLE_TYPE.getName(),
			        KindOfTopicType.ASSOCIATION_TYPE.getName(), KindOfTopicType.NO_TYPE.getName() };

			@Override
			protected void setValue(Object element, Object value) {
				int val = (Integer) value;
				// jump from scope to no type index
				if (val == 5)
					val = 6;

				KindOfTopicType type = KindOfTopicType.get(val);

				WordListContainer wlc = (WordListContainer) viewer.getInput();
				Word w = (Word) element;

				AbstractCommand cmd = null;
				if (wlc.contains(w)) {
					cmd = new ModifyWordTypeCommand((Word) element, type);
					commandStack.execute(cmd);
					viewer.refresh(element);
				} else {
					cmd = new AddWordCommand(wlc, w.getWord(), type);
					commandStack.execute(cmd);
					viewer.refresh();
				}
			}

			@Override
			protected Object getValue(Object element) {
				int val = ((Word) element).getType().getValue();
				// switching from scope to no type
				if (val == 6)
					val = 5;
				return val;
			}

			@Override
			protected CellEditor getCellEditor(Object element) {
				ComboBoxCellEditor comboBoxCellEditor = new ComboBoxCellEditor(table, ITEMS, SWT.READ_ONLY);
				return comboBoxCellEditor;
			}

			@Override
			protected boolean canEdit(Object element) {
				return true;
			}
		});

		// create column sorter
		new AbstractColumnViewerSorter(viewer, tvc) {

			@Override
			public String getText(Object element) {
				if (((WordListContainer) viewer.getInput()).contains(element))
					return ((Word) element).getType().getName();
				// hack so hopefully the new element is always the last
				else
					return "zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzZ";
			}
		};

		tvc = new TableViewerColumn(viewer, SWT.NONE);
		layout.setColumnData(tvc.getColumn(), new ColumnWeightData(2));
		tvc.getColumn().setText("Comment");
		tvc.setLabelProvider(new CellLabelProvider() {

			@Override
			public void update(ViewerCell cell) {
				String tmp = ((Word) cell.getElement()).getComment();
				if (tmp == null)
					tmp = "";
				cell.setText(tmp);
			}
		});

		tvc.setEditingSupport(new EditingSupport(viewer) {

			@Override
			protected void setValue(Object element, Object value) {
				AbstractCommand cmd = null;
				cmd = new ModifyWordCommentCommand((Word) element, (String) value);
				commandStack.execute(cmd);
				viewer.refresh(element);

			}

			@Override
			protected Object getValue(Object element) {
				String tmp = ((Word) element).getComment();
				if (tmp == null)
					return "";
				return tmp;
			}

			@Override
			protected CellEditor getCellEditor(Object element) {
				return new TextCellEditor(table);
			}

			@Override
			protected boolean canEdit(Object element) {
				return true;
			}
		});
	}

	private Annotation getWordListAnnotation() {
		for (Annotation a : file.getTopicMapSchema().getAnnotations()) {
			if (WORDLIST_ANNOTATION_KEY.equals(a.getKey())) {
				return a;
			}
		}

		Annotation a = ModelFactory.eINSTANCE.createAnnotation();
		a.setKey(WORDLIST_ANNOTATION_KEY);
		file.getTopicMapSchema().getAnnotations().add(a);
		return a;
	}

	/**
	 * Adds the given word using a command and the command stack, clears the
	 * input field and scrolls to the new word in the table.
	 * 
	 * The word is taken form the input field.
	 */
	private void addWord() {
		String newWord = inputText.getText();
		AddWordCommand cmd = new AddWordCommand((WordListContainer) viewer.getInput(), newWord);
		if (cmd.canExecute()) {
			commandStack.execute(cmd);
			inputText.setText("");
			viewer.reveal(cmd.getNewWord());
		}
	}
	
	private void createContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
    	menuMgr.setRemoveAllWhenShown(true);
    	menuMgr.addMenuListener(new IMenuListener() {
    		public void menuAboutToShow(IMenuManager manager) {
    			WordListEditor.this.fillContextMenu(manager);
    		}
    	});
    	
    	Menu menu = menuMgr.createContextMenu(viewer.getControl());
    	viewer.getControl().setMenu(menu);
		
	}

	/**
	 * Fills the context menu for the table viewer.
	 * 
     * @param manager
     */
    private void fillContextMenu(IMenuManager manager) {
    	// lazy instantiation of actions
    	if (selectAllAction==null)
    		selectAllAction = new SelectAllAction(this, viewer, true);
    	if (deselectAllAction==null)
    		deselectAllAction = new SelectAllAction(this, viewer, false);
    	
    	// if there's no input we have no menu
    	if (((WordListContainer) viewer.getInput()).isEmpty())
    		return;
    	
    	manager.add(selectAllAction);
    	manager.add(deselectAllAction);
    	
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setFocus() {
		viewer.getTable().setFocus();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isDirty() {
		return modelView.isDirty();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isSaveAsAllowed() {
		return modelView.isSaveAsAllowed();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void dispose() {
		this.file.eAdapters().remove(dirtyAdapter);
		this.commandStack.removeCommandStackListener(this);
		super.dispose();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void doSave(IProgressMonitor monitor) {
		persistWordList();
		modelView.doSave(monitor);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void doSaveAs() {
		persistWordList();
		modelView.doSaveAs();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * Returns false, because save on close is needed in {@link ModelView}
	 */
	@Override
	public boolean isSaveOnCloseNeeded() {
		return false;
	}

	/**
     * 
     */
	protected void persistWordList() {
		WordListContainer wc = (WordListContainer) viewer.getInput();
		Annotation a = getWordListAnnotation();

		if (wc.isEmpty()) {
			this.file.getTopicMapSchema().getAnnotations().remove(a);
		} else {
			a.setValue(wc.toXML());
		}
	}

	private void createTopicTypes(boolean allTypes) {
		WordListContainer wc = (WordListContainer) viewer.getInput();

		// list of words which types could not be created
		List<Word> errorWords = new ArrayList<Word>();

		// map of the existing types for easier lookup
		Map<String, TopicType> existingTypesMap = new HashMap<String, TopicType>();

		// find a topic with the name
		for (TopicType tt : file.getTopicMapSchema().getTopicTypes()) {
			existingTypesMap.put(tt.getName(), tt);
		}

		CompoundCommand cmd = new CompoundCommand();

		
		if (allTypes) {
			for (Word w : wc) {

				if ((existingTypesMap.containsKey(w.getWord())) || (w.getType() == KindOfTopicType.NO_TYPE)) {
					errorWords.add(w);
					continue;
				}

				cmd.append(new CreateTopicTypeCommand(file.getTopicMapSchema(), w.getWord(), w.getType()));
			}
		} else {
			for (Object obj : viewer.getCheckedElements()) {
				Word w = (Word) obj;
				
				if ((existingTypesMap.containsKey(w.getWord())) || (w.getType() == KindOfTopicType.NO_TYPE)) {
					errorWords.add(w);
					continue;
				}

				cmd.append(new CreateTopicTypeCommand(file.getTopicMapSchema(), w.getWord(), w.getType()));
			}
		}

		if (!cmd.isEmpty()) {
			commandStack.execute(cmd);
		}

		if (!errorWords.isEmpty()) {
			// TODO show errors
			System.out.println("Errors exist");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void commandStackChanged(EventObject event) {
		// refreshing viewer
		viewer.refresh();
	}

	private class DirtyAdapter extends AdapterImpl {
		@Override
		public void notifyChanged(Notification msg) {
			if (msg.getNotifier() instanceof File) {
				if (msg.getFeatureID(Boolean.class) == ModelPackage.FILE__DIRTY) {
					firePropertyChange(EditorPart.PROP_DIRTY);
				}
			}
		}
	}
}
