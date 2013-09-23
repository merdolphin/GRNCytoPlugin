package org.cytoscape.myapp.grn.internal;

import java.util.Properties;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.swing.CyAction;
import org.cytoscape.application.swing.CySwingApplication;
import org.cytoscape.application.swing.CytoPanelComponent;
import org.cytoscape.event.CyEventHelper;
import org.cytoscape.model.CyNetworkFactory;
import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.service.util.AbstractCyActivator;
import org.cytoscape.session.CyNetworkNaming;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.work.TaskFactory;
import org.osgi.framework.BundleContext;
import org.cytoscape.myapp.grn.internal.CreateNetworkFromOutput;

public class CyActivator extends AbstractCyActivator {

	@Override
	public void start(BundleContext context) throws Exception {
		
		CyApplicationManager cyAppManager = getService(context, CyApplicationManager.class);
		CySwingApplication cytoscapeDesktopService = getService(context, CySwingApplication.class);
		
		
		MenuAction action = new MenuAction(cyAppManager, "Open");
		
		GRNcontrolPanel grnControlPanel = new GRNcontrolPanel();
		GRNcontrolPanelAction controlPanelAction = new GRNcontrolPanelAction(cytoscapeDesktopService, grnControlPanel);
		
		
		CyNetworkManager cyNetworkManagerServiceRef = getService(context, CyNetworkManager.class);
		CyNetworkNaming cyNetworkNamingServiceRef = getService(context, CyNetworkNaming.class);
		CyNetworkFactory cyNetworkFactoryServiceRef = getService(context, CyNetworkFactory.class);
		
		CreateNetworkFromOutput  createNetworkFromOutput = new CreateNetworkFromOutput(cyNetworkManagerServiceRef, 
				cyNetworkNamingServiceRef,cyNetworkFactoryServiceRef);
	
		
		CyNetworkView grnNetworkView = getService(context, CyNetworkView.class);
		CyEventHelper eventHelper = getService(context, CyEventHelper.class);
		
		eventHelper.flushPayloadEvents();
		grnNetworkView.updateView();
		
		Properties properties = new Properties();
		
		registerAllServices(context, action, properties);
		registerService(context,grnControlPanel,CytoPanelComponent.class,properties);
		registerService(context, controlPanelAction, CyAction.class, properties);
		
		Properties createNetworkViewTaskFactoryProps = new Properties();
		createNetworkViewTaskFactoryProps.setProperty("preferredMenu","Apps.GRN Analysis");
		createNetworkViewTaskFactoryProps.setProperty("title","Create Network");
		registerService(context, createNetworkFromOutput,TaskFactory.class, createNetworkViewTaskFactoryProps);
		
		 
	}

}
