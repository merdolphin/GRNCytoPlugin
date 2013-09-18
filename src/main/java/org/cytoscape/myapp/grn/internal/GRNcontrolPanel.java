package org.cytoscape.myapp.grn.internal;



import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import org.cytoscape.application.swing.CytoPanel;
import org.cytoscape.application.swing.CytoPanelComponent;
import org.cytoscape.application.swing.CytoPanelName;



public class GRNcontrolPanel extends JPanel implements CytoPanelComponent {
	
	private static final long serialVersionUID = 1L;
	
	private JLayeredPane algorithmLayeredPane;

	private JCheckBox bayesianCheckbox;
	private JCheckBox randomForestCheckbox;
	private JCheckBox ridgeRegressionCheckbox;
	private JButton generateNetworkButton;

	public GRNcontrolPanel(){
		
		
		// creat the algorithm layered pane
		algorithmLayeredPane = new JLayeredPane();
		algorithmLayeredPane.setPreferredSize(new Dimension(250,200));
		algorithmLayeredPane.setBorder(BorderFactory.createTitledBorder("Algorithm"));
		algorithmLayeredPane.setLayout(new GridLayout(5,1));
		
		bayesianCheckbox = new JCheckBox("Dynamic Bayesian Networks");
		bayesianCheckbox.setSelected(true);
		
		randomForestCheckbox = new JCheckBox("Random Forest");
		ridgeRegressionCheckbox = new JCheckBox("Ridge Regression");
		generateNetworkButton = new JButton("Generate Network");
		
		algorithmLayeredPane.add(bayesianCheckbox);
		algorithmLayeredPane.add(randomForestCheckbox);
		algorithmLayeredPane.add(ridgeRegressionCheckbox);
		algorithmLayeredPane.add(new JSeparator());
		algorithmLayeredPane.add(generateNetworkButton);

		this.add(algorithmLayeredPane);
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
