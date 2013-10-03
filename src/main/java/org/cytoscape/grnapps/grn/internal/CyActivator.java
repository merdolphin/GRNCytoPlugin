package org.cytoscape.grnapps.grn.internal;

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
import org.cytoscape.view.model.CyNetworkViewFactory;
import org.cytoscape.view.model.CyNetworkViewManager;
import org.cytoscape.work.TaskFactory;
import org.osgi.framework.BundleContext;

public class CyActivator extends AbstractCyActivator {

	@Override
	public void start(BundleContext context) throws Exception {
		
		
		CyApplicationManager cyAppManager = getService(context, CyApplicationManager.class);
		CySwingApplication cytoscapeDesktopService = getService(context, CySwingApplication.class);
		
		
		OpenFileMenuAction action = new OpenFileMenuAction(cyAppManager, "Open");
		
		GRNcontrolPanel grnControlPanel = new GRNcontrolPanel();
		GRNcontrolPanelAction controlPanelAction = new GRNcontrolPanelAction(cytoscapeDesktopService, grnControlPanel);
		
		
		RFtaskFactory rfTaskFactory = new RFtaskFactory();
		
		
		CyNetworkManager cyNetworkManagerServiceRef = getService(context, CyNetworkManager.class);
		CyNetworkNaming cyNetworkNamingServiceRef = getService(context, CyNetworkNaming.class);
		CyNetworkFactory cyNetworkFactoryServiceRef = getService(context, CyNetworkFactory.class);
		CyNetworkViewManager networkViewManagerServiceRef = getService(context, CyNetworkViewManager.class);
		CyNetworkViewFactory cyNetworkViewFactoryServiceRef = getService(context, CyNetworkViewFactory.class);
		
		CreateNetworkFromOutput  createNetworkFromOutput = new CreateNetworkFromOutput(cyNetworkManagerServiceRef, 
				cyNetworkNamingServiceRef,cyNetworkFactoryServiceRef,networkViewManagerServiceRef, cyNetworkViewFactoryServiceRef);
	
		
		CyNetworkView grnNetworkView = getService(context, CyNetworkView.class);
		CyEventHelper eventHelper = getService(context, CyEventHelper.class);
		
		eventHelper.flushPayloadEvents();
		grnNetworkView.updateView();
		
		Properties properties = new Properties();
		
		registerAllServices(context, action, properties);
		registerService(context,grnControlPanel,CytoPanelComponent.class,properties);
		registerService(context, controlPanelAction, CyAction.class, properties);
		
		
		Properties rfTaskMonitorTaskFactoryProps = new Properties();
		Properties createNetworkViewTaskFactoryProps = new Properties();
		
		createNetworkViewTaskFactoryProps.setProperty("preferredMenu","Apps.GRN Analysis");
		createNetworkViewTaskFactoryProps.setProperty("title","Create Network");
		
		rfTaskMonitorTaskFactoryProps.setProperty("preferredMenu","Apps.GRN Analysis");
		rfTaskMonitorTaskFactoryProps.setProperty("title","Random Forest");
		
		registerService(context, rfTaskFactory,TaskFactory.class, rfTaskMonitorTaskFactoryProps);
	
		registerService(context, createNetworkFromOutput,TaskFactory.class, createNetworkViewTaskFactoryProps);
		
		 
	}

}
