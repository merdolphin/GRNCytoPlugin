// written by lina <lina.oahz@gmail.com>
// on Sun Sep 22 00:16:42 SGT 2013

package org.cytoscape.grnapps.grn.internal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

import javax.swing.JOptionPane;

import org.cytoscape.grnapps.grn.internal.Pair;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNetworkFactory;
import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.model.CyNode;
import org.cytoscape.session.CyNetworkNaming;
import org.cytoscape.view.layout.CyLayoutAlgorithm;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.CyNetworkViewFactory;
import org.cytoscape.view.model.CyNetworkViewManager;
import org.cytoscape.view.model.View;
import org.cytoscape.view.vizmap.VisualMappingManager;
import org.cytoscape.view.vizmap.VisualStyle;
import org.cytoscape.work.AbstractTask;
import org.cytoscape.work.TaskMonitor;

public class CreateNetworkTask extends AbstractTask {

	private final CyNetworkManager netMgr;
	private final CyNetworkFactory grnNetworkFactory;
	private final CyNetworkNaming namingUtil;
	private final CyNetworkViewManager networkViewManager;
	private final CyNetworkViewFactory cnvf;
	private CyNetworkView grnView;
	private final CyLayoutAlgorithm grnLayout;
	private final VisualMappingManager grnVMM;
	
	public CreateNetworkTask(final CyNetworkManager netMgr,
			final CyNetworkNaming namingUtil, 
			final CyNetworkFactory grnNetworkFactory,
			final CyNetworkViewManager networkViewManager,
			final CyNetworkViewFactory cnvf,
			final CyNetworkView grnView,
			final CyLayoutAlgorithm grnLayout,
			final VisualMappingManager grnVMM) {
		this.netMgr = netMgr;
		this.grnNetworkFactory = grnNetworkFactory;
		this.namingUtil = namingUtil;
		this.networkViewManager = networkViewManager;
		this.cnvf = cnvf;
		this.grnView = grnView;
		this.grnLayout = grnLayout;
		this.grnVMM = grnVMM;
	}

	

	@Override
	public void run(TaskMonitor monitor) throws Exception {
	
			CyNetwork grnNet = grnNetworkFactory.createNetwork();
			grnNet.getRow(grnNet).set(CyNetwork.NAME,
					namingUtil.getSuggestedNetworkTitle("GRN Network"));
			
			
			HashMap<Pair, Integer> matrix = new HashMap<Pair, Integer>();
			
			HashSet<String> allNodes = new HashSet<String>();
	
			try {
				String tmpDir = System.getProperty("java.io.tmpdir");		
				File file = new File(tmpDir, "finalnetwork.txt");
				//File file = new File("/home/lina/scratch/Network/grn/tmp/finalnetwork.txt");
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
					
	
					if (! temp[0].equals(temp[1]) && Integer.parseInt(temp[2]) != 0) {
						
						allNodes.add(temp[0]);
						allNodes.add(temp[1]);
						
						Pair duplicatedPair = new Pair(null, null);
						
						for(Pair key : matrix.keySet()){
							if(key.getSource().equals(temp[1]) && key.getTarget().equals(temp[0])){
								duplicatedPair.setSource(temp[1]);
								duplicatedPair.setTarget(temp[0]);
							}
						}
						
						Pair tempPair = new Pair(null, null);
						
						if(duplicatedPair.getSource() == null){
							tempPair.setSource(temp[0]);
							tempPair.setTarget(temp[1]);
						}else{
							tempPair = duplicatedPair;
						}
						matrix.put(tempPair,Integer.parseInt(temp[2]));
					}
					
				}
	
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	
			HashMap <String, CyNode> nodesnameMap = new HashMap<String, CyNode>();
			
			for(String nodename : allNodes){
				
				CyNode node = grnNet.addNode();
	
				grnNet.getDefaultNodeTable().getRow(node.getSUID())
					.set(CyNetwork.NAME, nodename);
				nodesnameMap.put(nodename, node);
			}
				
		
			for(Pair key: matrix.keySet()){
			
				grnNet.addEdge(nodesnameMap.get(key.getSource()), nodesnameMap.get(key.getTarget()), true);
			}
			
			
			netMgr.addNetwork(grnNet);
			
			final Collection<CyNetworkView> views = networkViewManager.getNetworkViews(grnNet);
			
			grnView = null;
			
			if(views.size() != 0)
				grnView = views.iterator().next();
			if(grnView == null){
				grnView = cnvf.createNetworkView(grnNet);
				networkViewManager.addNetworkView(grnView);
			}else{
				System.out.println("networkView already existed.");
			}
				
			insertTasksAfterCurrentTask(grnLayout.createTaskIterator(grnView, grnLayout.createLayoutContext(),
					new HashSet<View<CyNode>>(grnView.getNodeViews()), CyNetwork.NAME));
			
			VisualStyle vs = grnVMM.getDefaultVisualStyle();
			

			vs.apply(grnView);
			grnView.updateView();
			
			boolean destroyNetwork = false;
			if (destroyNetwork) {
				netMgr.destroyNetwork(grnNet);
			}
		}
}