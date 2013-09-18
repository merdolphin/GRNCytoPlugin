package org.cytoscape.myapp.grn.internal;


import java.awt.event.ActionEvent;

import org.cytoscape.application.swing.AbstractCyAction;
import org.cytoscape.application.swing.CySwingApplication;
import org.cytoscape.application.swing.CytoPanel;
import org.cytoscape.application.swing.CytoPanelName;
import org.cytoscape.application.swing.CytoPanelState;

public class GRNcontrolPanelAction extends AbstractCyAction{

	private static final long serialVersionUID = 3501171931978206287L;
	
	private CySwingApplication desktopApp;
	private final CytoPanel cytoPanelSouth;
	private GRNcontrolPanel grnControlPanel;
	
	public GRNcontrolPanelAction(CySwingApplication desktopApp, GRNcontrolPanel grnControlPanel) {
		super("GRN");
		setPreferredMenu("GRN analysis");
		
		this.desktopApp = desktopApp;
		this.cytoPanelSouth = this.desktopApp.getCytoPanel(CytoPanelName.SOUTH);
		this.grnControlPanel = grnControlPanel;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(cytoPanelSouth.getState() == CytoPanelState.HIDE){
			cytoPanelSouth.setState(CytoPanelState.DOCK);
		}
		
		int index = cytoPanelSouth.indexOfComponent(grnControlPanel);
		if(index == -1){
			return;
		}
		cytoPanelSouth.setSelectedIndex(index);
		}
		
	}


