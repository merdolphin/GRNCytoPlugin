package org.cytoscape.myapp.grn.internal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNetworkFactory;
import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.model.CyNode;
import org.cytoscape.session.CyNetworkNaming;
import org.cytoscape.work.AbstractTask;
import org.cytoscape.work.TaskMonitor;

public class CreateNetworkTask extends AbstractTask {

	private final CyNetworkManager netMgr;
	private final CyNetworkFactory grnNetworkFactory;
	private final CyNetworkNaming namingUtil;

	public CreateNetworkTask(final CyNetworkManager netMgr,
			final CyNetworkNaming namingUtil, final CyNetworkFactory grnNetworkFactory) {
		this.netMgr = netMgr;
		this.grnNetworkFactory = grnNetworkFactory;
		this.namingUtil = namingUtil;
	}

	public void run(TaskMonitor monitor) {

		CyNetwork grnNet = grnNetworkFactory.createNetwork();
		grnNet.getRow(grnNet).set(CyNetwork.NAME,
				namingUtil.getSuggestedNetworkTitle("GRN Network"));

		try {
			File file = new File("..//tmp//finalnetwork.txt");
			FileReader reader = new FileReader(file);
			BufferedReader in = new BufferedReader(reader);

			if (!file.exists()) {
				JOptionPane.showMessageDialog(null, "check file path");
			}

			String line;

			while ((line = in.readLine()) != null) {
				int j = 0;
				String temp[] = new String[3];
				for (String date : line.split("\t")) {
					temp[j++] = date;
				}

				if (!temp[0].equals(temp[1]) && Integer.parseInt(temp[2]) != 0) {
					JOptionPane.showMessageDialog(null, "reading ... file");
				}
			}

			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		CyNode node1 = grnNet.addNode();
		CyNode node2 = grnNet.addNode();

		grnNet.getDefaultNodeTable().getRow(node1.getSUID())
			.set("name", "node1");
		grnNet.getDefaultNodeTable().getRow(node2.getSUID())
			.set("name", "node2");

		grnNet.addEdge(node1, node2, true);

		netMgr.addNetwork(grnNet);

		boolean destroyNetwork = false;
		if (destroyNetwork) {
			netMgr.destroyNetwork(grnNet);
		}
	}
}