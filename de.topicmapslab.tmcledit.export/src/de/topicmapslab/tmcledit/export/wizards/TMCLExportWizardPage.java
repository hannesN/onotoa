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
import java.util.List;

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
import de.topicmapslab.tmcledit.export.wizards.TMCLExportWizardPage.FileType.Type;


/**
 * @author Hannes Niederhausen
 *
 */
public class TMCLExportWizardPage extends WizardPage {
	private Text fileName;
	
	private TableViewer typeViewer;
	
	private static List<FileType> extensions;

	private Button browseButton;
	
	private Button exportSchemaButton;
	
	private Button exportAnnotationButton;
	
	private Button useIndention;
	
	private Button onlyExportTopicTypes;
	
	private FileType selectedFileType;
	
	{
		extensions = new ArrayList<TMCLExportWizardPage.FileType>();
		
		String descr = "Export schema topic map as CTM file using the TMCL templates";
		extensions.add(new FileType(Type.CTM, "ctm", descr));
		
		descr = "Export schema topic map as XTM2.1.";
		extensions.add(new FileType(Type.XTM_2_1, "xtm", descr));
		
		descr = "Export schema topic map as XTM2.0.";
		extensions.add(new FileType(Type.XTM_2_0, "xtm", descr));
		
		descr = "Export schema topic map as LTM.";
		extensions.add(new FileType(Type.LTM, "ltm", descr));
		
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
	    
	    exportAnnotationButton = new Button(comp, SWT.CHECK);
	    exportAnnotationButton.setSelection(true);
	    exportAnnotationButton.setText("Export Annotations");
	    fac.applyTo(exportAnnotationButton);
	    
	    exportSchemaButton = new Button(comp, SWT.CHECK);
	    exportSchemaButton.setText("Export Schema Information");
	    fac.applyTo(exportSchemaButton);
	    
	    onlyExportTopicTypes = new Button(comp, SWT.CHECK);
	    onlyExportTopicTypes.setText("Export Topic Types only");
	    onlyExportTopicTypes.setToolTipText("You can use the exported topic map as stub for your instance topic map.");
	    fac.applyTo(onlyExportTopicTypes);
	    
	    useIndention = new Button(comp, SWT.CHECK);
	    useIndention.setSelection(true);
	    useIndention.setText("Indent XML formats");
	    useIndention.setToolTipText("Uses newline and indention chracters in XTM2 and XTM2.1");
	    fac.applyTo(useIndention);
	    
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
	    typeViewer.setInput(extensions);
	    
	    hookListeners();
	    setControl(comp);
    }

	public boolean isExportAnnotations() {
        return exportAnnotationButton.getSelection();
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
	
	public boolean isUseIndention() {
		return useIndention.getSelection();
	}
	
	
	
	public FileType getSelectedFileType() {
	    return selectedFileType;
    }

	/**
     * @param file
     * @return
     */
    private String updateType(String file) {
        StructuredSelection sel = null;
        FileType ft = findEntry("xtm");
        int idx = file.lastIndexOf('.');
        if (idx==-1) {
        	file += ".xtm";
        	sel = new StructuredSelection(ft);
        
        } else {
        	String suffix = file.substring(idx+1);
        	FileType ft2 = findEntry(suffix);
        	if (ft2!=null) {
        		sel = new StructuredSelection(ft2);
        		ft=ft2;
        	} else {
        		file += ".xtm";
        		sel = new StructuredSelection(ft);
        	}
        }
        selectedFileType = ft;
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
	    		for (FileType ft : extensions) {
	    			suffixes.add("*."+ft.fileSuffix);
	    			all=all+"*."+ft.fileSuffix+";";
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
			
            public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection sel = (IStructuredSelection) typeViewer.getSelection();
				if (sel.isEmpty())
					return;

				// we set the suffixe of the filename according to the selection
				String filename = getFileName();
				if (filename.length()==0)
					return;
				
				FileType ft = (FileType) sel.getFirstElement();
				selectedFileType = ft;
				if (filename.endsWith(ft.fileSuffix))
					return;
				
				// changing current filesuffix to selected one
				int idx = filename.lastIndexOf('.');
				if (idx!=-1) {
					String fSuffix = filename.substring(idx+1);
					if (findEntry(fSuffix)!=null) {
						filename = filename.substring(0, idx+1)+ft.fileSuffix;
						fileName.setText(filename);
						return;
					}
				}
				
				filename = filename+"."+ft.fileSuffix;
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

	private FileType findEntry(String key) {
		for (FileType ft : extensions) {
			if(ft.fileSuffix.equals(key))
				return ft;
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

        public String getColumnText(Object element, int columnIndex) {
			FileType e =  (FileType) element;
	        if (columnIndex==0)
	        	return e.description;
	        else
	        	return e.fileSuffix;
        }
		
	}

    public String getFileSuffix() {
		IStructuredSelection sel = (IStructuredSelection) typeViewer.getSelection();
		FileType e =  (FileType) sel.getFirstElement();
	    return e.fileSuffix;
    }
	
	static class FileType {
		enum Type {
			XTM_2_0,
			XTM_2_1,
			LTM,
			CTM
		};
		
		Type type;
		String fileSuffix;
		String description;
		public FileType(Type type, String fileSuffix, String description) {
	        super();
	        this.type = type;
	        this.fileSuffix = fileSuffix;
	        this.description = description;
        }
	}
	
}
