/**
 * 
 */
package de.topicmapslab.onotoa.update;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.equinox.p2.core.IProvisioningAgent;
import org.eclipse.equinox.p2.operations.ProvisioningSession;
import org.eclipse.equinox.p2.operations.UpdateOperation;
import org.eclipse.equinox.p2.ui.LoadMetadataRepositoryJob;
import org.eclipse.equinox.p2.ui.ProvisioningUI;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ui.handlers.HandlerUtil;

import de.topicmapslab.onotoa.update.util.P2Util;

/**
 * This command is used to check for updates and install them if some were
 * found.
 * 
 * @author Hannes Niederhausen
 * 
 */
public class CheckUpdateCommand extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
//		ProgressMonitorDialog dlg = new ProgressMonitorDialog(HandlerUtil.getActiveShell(event));
//		try {
//			dlg.run(false, false, new IRunnableWithProgress() {
//
//				@Override
//				public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
//					IProvisioningAgent agent = Activator.getAgent();
//					IStatus s = P2Util.checkForUpdates(agent, monitor);
//					if (s.getCode()==UpdateOperation.STATUS_NOTHING_TO_UPDATE) {
//						System.out.println("Nothing to update");
//					}
//				}
//			});
//		} catch (Exception e) {
//
//			Activator.logException(e);
//			throw new RuntimeException(e);
//		}
		ProvisioningSession session = new ProvisioningSession(Activator.getAgent());
		ProvisioningUI.getDefaultUI().openUpdateWizard(false, new UpdateOperation(session), new LoadMetadataRepositoryJob(ProvisioningUI.getDefaultUI()));
		

		return null;
	}

}
