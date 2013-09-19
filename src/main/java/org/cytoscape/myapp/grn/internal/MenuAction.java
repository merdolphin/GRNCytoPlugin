package org.cytoscape.myapp.grn.internal;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.swing.AbstractCyAction;

/**
 * Creates a new menu item under Apps menu section.
 *
 */
public class MenuAction extends AbstractCyAction {

	private static final long serialVersionUID = -8449628090722031884L;

	public MenuAction(CyApplicationManager cyApplicationManager, final String menuTitle) {
		
		super(menuTitle, cyApplicationManager, null, null);
		setPreferredMenu("Apps");
		
	}

	public void actionPerformed(ActionEvent e) {

		// Write your own function here.
		JOptionPane.showMessageDialog(null, "so slow");
		
	}
}
