package org.cytoscape.myapp.grn.internal;



import java.awt.Component;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.cytoscape.application.swing.CytoPanel;
import org.cytoscape.application.swing.CytoPanelComponent;
import org.cytoscape.application.swing.CytoPanelName;



public class GRNcontrolPanel extends JPanel implements CytoPanelComponent {
	
	private static final long serialVersionUID = 1L;

	public GRNcontrolPanel(){
		JLabel lbxyz = new JLabel("lina");
		
		this.add(lbxyz);
		this.setVisible(true);
	}
		

	@Override
	public Component getComponent() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public Icon getIcon() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return "GRN Control Panel";
	}


	@Override
	public CytoPanelName getCytoPanelName() {
		// TODO Auto-generated method stub
		return CytoPanelName.SOUTH;
	}


}
