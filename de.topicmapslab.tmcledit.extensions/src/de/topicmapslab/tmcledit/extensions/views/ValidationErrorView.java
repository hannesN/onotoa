/**
 * 
 */
package de.topicmapslab.tmcledit.extensions.views;

import java.util.List;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.part.ViewPart;

import de.topicmapslab.tmcledit.model.validation.ValidationResult;

/**
 * @author Hannes Niederhausen
 *
 */
public class ValidationErrorView extends ViewPart {

	public static final String ID = "de.topicmapslab.tmcledit.extensions.views.ValidationErrorView";
	
	private TableViewer viewer;

	@Override
	public void createPartControl(Composite parent) {
		FormToolkit toolkit = new FormToolkit(parent.getDisplay());
		
		Composite comp = toolkit.createComposite(parent);
		TableColumnLayout layout = new TableColumnLayout();
		comp.setLayout(layout);
		
		Table table = toolkit.createTable(comp, SWT.BORDER|SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		
		TableColumn tc = new TableColumn(table, SWT.None);
		tc.setText("Message");
		layout.setColumnData(tc, new ColumnWeightData(10, 150, true));
		
		viewer = new TableViewer(table);
		viewer.setContentProvider(new ArrayContentProvider());
		viewer.setLabelProvider(new ValidationLabelProvider());
		
		hookContextMenu();
		
	}
	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				ValidationErrorView.this.fillContextMenu(manager);
			}
		});
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, viewer);
	}
	
	protected void fillContextMenu(IMenuManager manager) {
		IStructuredSelection sel = (IStructuredSelection) viewer.getSelection();
		
		if (sel.isEmpty())
			return;
		
		ValidationResult result = (ValidationResult) sel.getFirstElement();
		for (IAction a : result.getActions()) {
			manager.add(a);
		}
		
	}
	
	@Override
	public void setFocus() {
	}

	public void setValidationResults(List<ValidationResult> results) {
		viewer.setInput(results);
	}
	
	private class ValidationLabelProvider implements ITableLabelProvider {

		@Override
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		@Override
		public String getColumnText(Object element, int columnIndex) {
			return ((ValidationResult)element).getMessage();
		}

		@Override
		public void addListener(ILabelProviderListener listener) {
		}

		@Override
		public void dispose() {
		}

		@Override
		public boolean isLabelProperty(Object element, String property) {
			return false;
		}

		@Override
		public void removeListener(ILabelProviderListener listener) {
		}
		
	}
}
