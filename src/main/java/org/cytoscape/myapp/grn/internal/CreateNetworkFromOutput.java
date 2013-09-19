package org.cytoscape.myapp.grn.internal;



import org.cytoscape.model.CyNetworkFactory;
import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.session.CyNetworkNaming;
import org.cytoscape.work.AbstractTaskFactory;
import org.cytoscape.work.TaskIterator;




public class CreateNetworkFromOutput extends AbstractTaskFactory {
	
	private final CyNetworkManager netMgr;
	private final CyNetworkNaming namingUtil;
	private final CyNetworkFactory grnFactory;
	private final String finalOutputFileName;
	
	public CreateNetworkFromOutput(final CyNetworkManager netMgr, 
			final CyNetworkNaming namingUtil, final CyNetworkFactory grnFactory,
			final String finalOutputFileName){
		
		this.netMgr = netMgr;
		this.namingUtil = namingUtil;
		this.grnFactory = grnFactory;
		this.finalOutputFileName = finalOutputFileName;
	}
	
	
	@Override
	public TaskIterator createTaskIterator() {
		return new TaskIterator(new CreateNetworkTask(netMgr, namingUtil, grnFactory));
	}
	
}
