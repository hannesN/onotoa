/*******************************************************************************
 * Copyright (c) 2008, 2009 Topic Maps Lab and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Hannes Niederhausen - initial API and implementation
 *******************************************************************************/
package de.topicmapslab.tmcledit.export.wizards;

import java.util.ArrayList; 
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnPixelData;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

import de.topicmapslab.tmcledit.export.Activator;


/**
 * @author niederhausen
 *
 */
public class TMCLExportWizardPage extends WizardPage {
	private Text fileName;
	
	private TableViewer typeViewer;
	
	private static Map<String, String> extensionMap;

	private Button browseButton;
	
	private Button exportSchemaButton;
	
	private Button onlyExportTopicTypes;
	
	{
		extensionMap = new HashMap<String, String>(2);
		
		String descr = "Export schema topic map as CTM file using the TMCL templates";
		extensionMap.put("ctm", descr);
		
		descr = "Export schema topic map as XTM2.0.";
		extensionMap.put("xtm", descr);
		
		descr = "Export schema topic map as LTM.";
		extensionMap.put("ltm", descr);
		
	}
	
	public TMCLExportWizardPage(String pageName) {
	    super("TMCL Export");
    }

	public String getFileName() {
	    return fileName.getText();
    }
		
	public void createControl(Composite parent) {
	    Composite comp = new Composite(parent, SWT.NONE);
	    comp.setLayout(new GridLayout(3, false));
	    
	    
	    GridData gd = new GridData(GridData.FILL_HORIZONTAL);
	    gd.horizontalSpan = 3;
	    GridDataFactory fac = GridDataFactory.createFrom(gd);
	    
	    Label label = new Label(comp, SWT.NONE);
	    label.setText("File: ");
	    
	    fileName = new Text(comp, SWT.BORDER);
	    fileName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	    
	    browseButton = new Button(comp, SWT.PUSH);
	    browseButton.setText("...");
	    
	    exportSchemaButton = new Button(comp, SWT.CHECK);
	    exportSchemaButton.setText("Export Schema Information");
	    fac.applyTo(exportSchemaButton);
	    
	    onlyExportTopicTypes = new Button(comp, SWT.CHECK);
	    onlyExportTopicTypes.setText("Export Topic Types only");
	    onlyExportTopicTypes.setToolTipText("You can use the exported topic map as stub for your instance topic map.");
	    fac.applyTo(onlyExportTopicTypes);
	    
	    label = new Label(comp, SWT.NONE);
	    label.setText("Filetype:");
	    fac.applyTo(label);
	 
	    
	    Composite tableComp = new Composite(comp, SWT.NONE);
	    TableColumnLayout layout = new TableColumnLayout();
		tableComp.setLayout(layout);
	    gd = fac.create();
	    gd.verticalAlignment = SWT.FILL;
	    tableComp.setLayoutData(gd);
	    
	    typeViewer = new TableViewer(tableComp);
	    
	    
	    Table table = typeViewer.getTable();
	    table.setHeaderVisible(true);
	    // creating table columns with layout
		TableColumn tc = new TableColumn(table, SWT.NONE);
		tc.setText("Description");
	    layout.setColumnData(tc, new ColumnWeightData(1));
	    
	    tc = new TableColumn(table, SWT.NONE);
	    tc.setText("Suffix");
	    layout.setColumnData(tc, new ColumnPixelData(100));
	  
	    typeViewer.setContentProvider(new ArrayContentProvider());
	    typeViewer.setLabelProvider(new EntryLabelProvider());
	    typeViewer.setInput(extensionMap.entrySet());
	    
	    hookListeners();
	    setControl(comp);
    }

	
	
	
	public boolean isExportSchemaInfos() {
        return exportSchemaButton.getSelection();
    }
	
	public boolean isExportTopicTypes() {
		return onlyExportTopicTypes.getSelection();
	}
	
	public boolean isExportDiagramInfos() {
		return false;
	}

	/**
     * @param file
     * @return
     */
    private String updateType(String file) {
        StructuredSelection sel = null;
        int idx = file.lastIndexOf('.');
        if (idx==-1) {
        	file += ".xtm";
        	sel = new StructuredSelection(findEntry("xtm"));
        
        } else {
        	String suffix = file.substring(idx+1);
        	Entry<String, String> entry = findEntry(suffix);
        	if (entry!=null) {
        		sel = new StructuredSelection(entry);
        	} else {
        		file += ".xtm";
        		sel = new StructuredSelection(findEntry("xtm"));
        	}
        }
        typeViewer.setSelection(sel);
        return file;
    }

	private void hookListeners() {
		browseButton.addSelectionListener(new SelectionAdapter() {
	    	@Override
	    	public void widgetSelected(SelectionEvent e) {
	    		FileDialog dlg = new FileDialog(getShell(), SWT.SAVE);
	    		
	    		ArrayList<String> suffixes = new ArrayList<String>();
	    		String all = "";
	    		for (String s : extensionMap.keySet()) {
	    			suffixes.add("*."+s);
	    			all=all+"*."+s+";";
	    		}
	    		suffixes.add(0, all.substring(0, all.length()-1)); 
	    			
	    		String[] fileExtensions = suffixes.toArray(new String[suffixes.size()]);
				dlg.setFilterExtensions(fileExtensions);
				
				String file = Activator.getDefault().getPreferenceStore().getString("exported_file");
				if (file!=null) {
//					dlg.setFileName(file);
					int idx = file.lastIndexOf("/");
					if (idx!=-1)
						dlg.setFilterPath(file.substring(0, idx));
					
				}
					
				
				file = dlg.open();
				if (file!=null) {
					file = updateType(file);
					fileName.setText(file);
					return;
				}
	    	}
	    });
	    onlyExportTopicTypes.addSelectionListener(new SelectionAdapter() {
	    	@Override
	    	public void widgetSelected(SelectionEvent e) {
	    		if (onlyExportTopicTypes.getSelection()) {
	    			exportSchemaButton.setEnabled(false);
	    		} else {
	    			exportSchemaButton.setEnabled(true);
	    		}
	    	}
	    });
	    typeViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@SuppressWarnings("unchecked")
            public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection sel = (IStructuredSelection) typeViewer.getSelection();
				if (sel.isEmpty())
					return;

				// we set the suffixe of the filename according to the selection
				String filename = getFileName();
				if (filename.length()==0)
					return;
				
				Entry<String, String> entry = (Entry<String, String>) sel.getFirstElement();
				
				if (filename.endsWith(entry.getKey()))
					return;
				
				int idx = filename.lastIndexOf('.');
				if (idx!=-1) {
					String fSuffix = filename.substring(idx+1);
					if (extensionMap.keySet().contains(fSuffix)) {
						filename = filename.substring(0, idx+1)+entry.getKey();
						fileName.setText(filename);
						return;
					}
				}
				
				filename = filename+"."+entry.getKey();
				fileName.setText(filename);
			}
		});
	    
	    fileName.addFocusListener(new FocusAdapter() {
            @Override
	    	public void focusLost(FocusEvent e) {
	    		String tmp = getFileName();
	    		updateType(tmp);
	    	}
		});
    }

	private Entry<String, String> findEntry(String key) {
		for (Entry<String, String> e : extensionMap.entrySet()) {
			if(e.getKey().equals(key))
				return e;
		}
		return null;
	}

	private class EntryLabelProvider implements ITableLabelProvider {

		public void addListener(ILabelProviderListener listener) {
        }

		public void dispose() {
        }

		public boolean isLabelProperty(Object element, String property) {
	        return false;
        }

		public void removeListener(ILabelProviderListener listener) {
	        
        }

		public Image getColumnImage(Object element, int columnIndex) {
	        return null;
        }

		@SuppressWarnings("unchecked")
        public String getColumnText(Object element, int columnIndex) {
			Map.Entry<String, String> e = (Entry<String, String>) element;
	        if (columnIndex==1)
	        	return e.getKey();
	        else
	        	return e.getValue();
        }
		
	}

	@SuppressWarnings("unchecked")
    public String getFileSuffix() {
		IStructuredSelection sel = (IStructuredSelection) typeViewer.getSelection();
		Entry<String, String> e = (Entry<String, String>) sel.getFirstElement();
	    return e.getKey();
    }
	
	
}
