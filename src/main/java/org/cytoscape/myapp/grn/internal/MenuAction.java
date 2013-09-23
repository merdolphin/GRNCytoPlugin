package org.cytoscape.myapp.grn.internal;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.swing.AbstractCyAction;
import org.cytoscape.application.swing.CytoPanelComponent;
import org.cytoscape.application.swing.CytoPanelName;


public class MenuAction extends AbstractCyAction implements CytoPanelComponent, ActionListener {

	private static final long serialVersionUID = -8449628090722031884L;

	public MenuAction(CyApplicationManager cyApplicationManager, final String menuTitle) {
		
		super(menuTitle, cyApplicationManager, null, null);
		setPreferredMenu("Apps.GRN Analysis");
		
	}



	@Override
	public Component getComponent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CytoPanelName getCytoPanelName() {
		// TODO Auto-generated method stub
		return CytoPanelName.SOUTH;
	}

	@Override
	public Icon getIcon() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void actionPerformed(ActionEvent e) {
		OpenFileFrame newFrame = new OpenFileFrame(); 
	}
}
