package org.cytoscape.myapp.grn.internal;

import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class OpenFileFrame extends JFrame {
	
	
	private static final long serialVersionUID = -6728821400725883984L;

	public OpenFileFrame(){
	
	JFrame frame = new JFrame("Load File");
	JButton choosefilebutton = new JButton("Choose"); 
	
	JFileChooser fc = new JFileChooser();
	int returnVal = fc.showOpenDialog(choosefilebutton);
	
	if(returnVal == JFileChooser.APPROVE_OPTION){
		File file = fc.getSelectedFile();
		JOptionPane.showMessageDialog(null, file.getName());
	}
	
	frame.add(choosefilebutton);
	this.add(frame);
	
	}
	
}
