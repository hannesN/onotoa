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
package de.topicmapslab.onotoa.net.sync.common;

import org.eclipse.ecf.core.ContainerFactory;
import org.eclipse.ecf.core.IContainer;
import org.eclipse.ecf.core.identity.ID;
import org.eclipse.ecf.core.identity.IDFactory;
import org.eclipse.ecf.core.util.ECFException;
import org.eclipse.ecf.datashare.IChannel;
import org.eclipse.ecf.datashare.IChannelContainerAdapter;
import org.eclipse.ecf.datashare.IChannelListener;

import de.topicmapslab.onotoa.net.sync.Activator;

/**
 * @author Hannes Niederhausen
 *
 */
public abstract class AbstractChannelHandler implements IChannelListener {
	public static final String namespace = "http://onotoa.topicmapslab.de"; 
	public static final String channelName = "onotoa sync";
	
	private IChannel channel;	
	
	private ServerData serverData;
	private IContainer clientContainer;
	
	
	protected void createChannel() {
		if (serverData==null)
			throw new RuntimeException("No server data set");
		
		try {
			
			ID serverID = IDFactory.getDefault().createID(namespace, serverData.getURI());
			clientContainer = ContainerFactory.getDefault().createContainer("ecf.gerneric.client", serverID);
			if (clientContainer==null) {
				disconnect();
				throw new RuntimeException("Could not create client container");
			}
			
			IChannelContainerAdapter cca = (IChannelContainerAdapter) clientContainer.getAdapter(IChannelContainerAdapter.class);
			if (cca==null) {
				disconnect();
				throw new RuntimeException("Could not create channel container adapter");
			}
			
			ID channelID = IDFactory.getDefault().createID(namespace, channelName);
			channel = cca.createChannel(channelID, this, null);
			if (channel==null) {
				disconnect();
				throw new RuntimeException("Could not create Channel");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Activator.log(e);
		}
	}


	public IChannel getChannel() {
		return channel;
	}
	
	public ServerData getServerData() {
		return serverData;
	}
	
	public void sendMessage(String msg) {
		if (getChannel()!=null)
			try {
				getChannel().sendMessage(msg.getBytes());
			} catch (ECFException e) {
				e.printStackTrace();
				Activator.log(e);
			}
	}
	

	public void disconnect() {
		if (channel!=null) {
			channel.dispose();
			channel=null;
		}
		if (clientContainer!=null) {
			clientContainer.disconnect();
			clientContainer.dispose();
			clientContainer = null;
		}
	}
	
	
}
