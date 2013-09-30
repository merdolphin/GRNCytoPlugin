package org.cytoscape.grnapps.grn.internal;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Icon;
import javax.swing.JFileChooser;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.swing.AbstractCyAction;
import org.cytoscape.application.swing.CytoPanelComponent;
import org.cytoscape.application.swing.CytoPanelName;



public class OpenFileMenuAction extends AbstractCyAction implements CytoPanelComponent, ActionListener {

	private static final long serialVersionUID = -8449628090722031884L;
	
	private static String inputFilePath;

	public OpenFileMenuAction(CyApplicationManager cyApplicationManager, final String menuTitle) {
		
		super(menuTitle, cyApplicationManager, null, null);
		setPreferredMenu("Apps.GRN Analysis");
	}

	public void actionPerformed(ActionEvent e) {
		
		try {
		   
			File inputFile;
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showOpenDialog(null);
			
			if(returnVal == JFileChooser.APPROVE_OPTION){
				inputFile = fc.getSelectedFile();
				inputFilePath = inputFile.getAbsolutePath();
			}
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public static String getInputFilePath() {
		return inputFilePath;
	}

	public void setInputFilePath(String inputFilePath) {
		this.inputFilePath = inputFilePath;
	}

	@Override
	public Component getComponent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CytoPanelName getCytoPanelName() {
		// TODO Auto-generated method stub
		return null;
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

}
