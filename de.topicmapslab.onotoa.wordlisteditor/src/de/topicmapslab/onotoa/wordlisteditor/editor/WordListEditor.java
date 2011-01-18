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
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ColumnViewerEditor;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationStrategy;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TableViewerEditor;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.part.EditorPart;

import de.topicmapslab.onotoa.selection.service.IOnotoaSelectionService;
import de.topicmapslab.onotoa.wordlisteditor.Activator;
import de.topicmapslab.onotoa.wordlisteditor.editor.input.WordListEditorInput;
import de.topicmapslab.onotoa.wordlisteditor.emfcommands.AddWordCommand;
import de.topicmapslab.onotoa.wordlisteditor.emfcommands.ModifyWordCommand;
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

	private Button applyButton;

	private TableViewer viewer;

	private Button removeButton;

	private CommandStack commandStack;

	private DirtyAdapter dirtyAdapter;

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
	public void createPartControl(Composite parent) {
		FormToolkit toolkit = new FormToolkit(parent.getDisplay());

		Composite comp = toolkit.createComposite(parent);
		comp.setLayout(new GridLayout(2, false));

		// table composite with table layout
		Composite tableComp = toolkit.createComposite(comp);
		tableComp.setLayout(new TableColumnLayout());
		tableComp.setLayoutData(new GridData(GridData.FILL_BOTH));

		Table table = toolkit.createTable(tableComp, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		initTable(table);

		createTableButtonBar(comp, toolkit);

		applyButton = toolkit.createButton(comp, "Apply", SWT.PUSH);

		updateInput();

		updateButtons();
		hookListeners();

	}

	/**
     * 
     */
    protected void updateInput() {
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

		removeButton.addSelectionListener(new SelectionAdapter() {
			/**
			 * {@inheritDoc}
			 */
			@Override
			public void widgetSelected(SelectionEvent e) {
				IStructuredSelection sel = (IStructuredSelection) viewer.getSelection();

				CompoundCommand compundCmd = new CompoundCommand();
				WordListContainer container = (WordListContainer) viewer.getInput();

				Iterator<?> it = sel.iterator();
				while (it.hasNext()) {
					Word w = ((Word) it.next());
					compundCmd.append(new RemoveWordCommand(container, w));
				}

				commandStack.execute(compundCmd);
				viewer.refresh();
			}
		});
		
		applyButton.addSelectionListener(new SelectionAdapter() {
			/**
			 * {@inheritDoc}
			 */
			@Override
			public void widgetSelected(SelectionEvent e) {
				createTopicTypes();
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

	private void updateButtons() {
		removeButton.setEnabled(!(viewer.getSelection().isEmpty()));
		applyButton.setEnabled(!((WordListContainer) viewer.getInput()).isEmpty());
	}

	/**
	 * @param comp
	 * @param toolkit
	 */
	private void createTableButtonBar(Composite parent, FormToolkit toolkit) {
		Composite comp = toolkit.createComposite(parent);
		comp.setLayout(new GridLayout());

		GridData gd = new GridData();
		gd.widthHint = 120;
		GridDataFactory fac = GridDataFactory.createFrom(gd);

		removeButton = toolkit.createButton(comp, "Remove", SWT.PUSH);
		fac.applyTo(removeButton);

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

		viewer = new TableViewer(table);
		viewer.setContentProvider(new WordListProvider());
		

		ColumnViewerEditorActivationStrategy actSupport = new ColumnViewerEditorActivationStrategy(viewer) {
			protected boolean isEditorActivationEvent(ColumnViewerEditorActivationEvent event) {
				return event.eventType == ColumnViewerEditorActivationEvent.TRAVERSAL
				        || event.eventType == ColumnViewerEditorActivationEvent.MOUSE_DOUBLE_CLICK_SELECTION
				        || (event.eventType == ColumnViewerEditorActivationEvent.KEY_PRESSED && event.keyCode == SWT.CR)
				        || event.eventType == ColumnViewerEditorActivationEvent.PROGRAMMATIC;
			}
		};
		
		TableViewerEditor.create(viewer, null, actSupport, 
				  ColumnViewerEditor.TABBING_HORIZONTAL
				| ColumnViewerEditor.TABBING_MOVE_TO_ROW_NEIGHBOR
				| ColumnViewerEditor.TABBING_VERTICAL 
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
				
				AbstractCommand cmd = null;
				if (wlc.contains(w)) {
					cmd = new ModifyWordCommand(w, (String) value);
					commandStack.execute(cmd);
					viewer.refresh(element);
				} else {
					cmd = new AddWordCommand(wlc, (String) value, KindOfTopicType.NO_TYPE);
					commandStack.execute(cmd);
					viewer.refresh();
				}
				
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

		// type column
		tvc = new TableViewerColumn(viewer, SWT.NONE);
		tvc.getColumn().setText("Type");
		layout.setColumnData(tvc.getColumn(), new ColumnWeightData(1));
		tvc.setLabelProvider(new CellLabelProvider() {

			@Override
			public void update(ViewerCell cell) {
				cell.setText(((Word) cell.getElement()).getType().getName());
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
				return new ComboBoxCellEditor(table, ITEMS);
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
	public void dispose() {
		this.file.eAdapters().remove(dirtyAdapter);
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
    
    private void createTopicTypes() {
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
		
    	for (Word w : wc) {
    		
    		if ( (existingTypesMap.containsKey(w.getWord())) 
    			|| (w.getType()==KindOfTopicType.NO_TYPE)) {
    			errorWords.add(w);
    			continue;
    		}
    		
    		cmd.append(new CreateTopicTypeCommand(file.getTopicMapSchema(), w.getWord(), w.getType()));
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

	private class WordListProvider implements IStructuredContentProvider {

		private WordListContainer container;
		
		/**
         * {@inheritDoc}
         */
        @Override
        public void dispose() {
        }

		/**
         * {@inheritDoc}
         */
        @Override
        public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        	container = (WordListContainer) newInput;
        }

		/**
         * {@inheritDoc}
         */
        @Override
        public Object[] getElements(Object inputElement) {

        	Word[] words = new Word[container.size()+1];
        	
        	container.toArray(words);
        	words[container.size()] = new Word();
	        return words;
        }
		
	}
}
