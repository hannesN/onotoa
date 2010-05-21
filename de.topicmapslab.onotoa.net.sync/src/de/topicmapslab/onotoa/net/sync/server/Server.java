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
package de.topicmapslab.onotoa.net.sync.server;

import org.eclipse.ecf.core.ContainerFactory;
import org.eclipse.ecf.core.IContainer;
import org.eclipse.ecf.core.IContainerListener;
import org.eclipse.ecf.core.events.IContainerEvent;
import org.eclipse.ecf.core.identity.ID;
import org.eclipse.ecf.core.identity.IDFactory;
import org.eclipse.ecf.datashare.events.IChannelEvent;

import de.topicmapslab.onotoa.net.sync.Activator;
import de.topicmapslab.onotoa.net.sync.common.AbstractChannelHandler;

/**
 * @author Hannes Niederhausen
 *
 */
public class Server extends AbstractChannelHandler {
	private IContainer container;
	
	public void start() {
		if (getServerData()==null)
			throw new RuntimeException("No server data set");
		try {
			ID serverID = IDFactory.getDefault().createID(namespace, getServerData().getURI());
			container = ContainerFactory.getDefault().createContainer("ecf.gerneric.server", serverID);
			if (container==null)
				throw new RuntimeException("Could not create server container");
			
			container.addListener(new IContainerListener() {
				
				public void handleEvent(IContainerEvent event) {
					System.out.println("Container Listener: "+event);
				}
			});
			
			createChannel();
			
			
		} catch (Exception e) {
			e.printStackTrace();
			Activator.log(e);		
		}
	}
	
	public boolean isConnected() {
		return (getChannel()!=null) && (container!=null);
	}
	
	public void stop() {
		disconnect();
	}
	
	public void disconnect() {
		super.disconnect();
		if (container!=null) {
			container.disconnect();
			container.dispose();
			container = null;
		}
	}

	public void handleChannelEvent(IChannelEvent event) {
		System.out.println("Server Channel: "+event);
	}
	
}
