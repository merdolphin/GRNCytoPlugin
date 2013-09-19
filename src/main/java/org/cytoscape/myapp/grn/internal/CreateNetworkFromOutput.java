package org.cytoscape.myapp.grn.internal;


import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNetworkFactory;
import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.work.AbstractTaskFactory;
import org.cytoscape.work.TaskIterator;



public class CreateNetworkFromOutput extends AbstractTaskFactory {
	
	private final CyNetworkManager netMgr;
	private final CyNetwork grnNetwork;
	private final CyNetworkFactory grnFactory;
	
	public CreateNetworkFromOutput(final CyNetworkManager netMgr, 
			final CyNetwork grnNetwork, final CyNetworkFactory grnFactory ){
		
		this.netMgr = netMgr;
		this.grnNetwork = grnNetwork;
		this.grnFactory = grnFactory;
	}
	
	public TaskIterator createTaskIterator(){
		return new TaskIterator(new CreateNetworkTask(netMgr, grnNetwork, grnFactory));
	}
}