package org.cytoscape.myapp.grn.internal;

import java.util.Properties;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.swing.CyAction;
import org.cytoscape.application.swing.CySwingApplication;
import org.cytoscape.application.swing.CytoPanelComponent;
import org.cytoscape.service.util.AbstractCyActivator;
import org.osgi.framework.BundleContext;




public class CyActivator extends AbstractCyActivator {

	@Override
	public void start(BundleContext context) throws Exception {
		
		CyApplicationManager cyAppManager = getService(context, CyApplicationManager.class);
		CySwingApplication cytoscapeDesktopService = getService(context, CySwingApplication.class);
		
		
		MenuAction action = new MenuAction(cyAppManager, "GRN analysis");
		
		GRNcontrolPanel grnControlPanel = new GRNcontrolPanel();
		GRNcontrolPanelAction controlPanelAction = new GRNcontrolPanelAction(cytoscapeDesktopService, grnControlPanel);
		
		
		Properties properties = new Properties();
		
		registerService(context,grnControlPanel,CytoPanelComponent.class,properties);
		registerService(context, controlPanelAction, CyAction.class, properties);
		registerAllServices(context, action, properties);
		
		 
	}

}
