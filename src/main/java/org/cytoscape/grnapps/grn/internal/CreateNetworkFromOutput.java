package org.cytoscape.grnapps.grn.internal;

import org.cytoscape.model.CyNetworkFactory;
import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.session.CyNetworkNaming;
import org.cytoscape.view.layout.CyLayoutAlgorithm;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.CyNetworkViewFactory;
import org.cytoscape.view.model.CyNetworkViewManager;
import org.cytoscape.view.vizmap.VisualMappingManager;
import org.cytoscape.work.AbstractTaskFactory;
import org.cytoscape.work.TaskIterator;


public class CreateNetworkFromOutput extends AbstractTaskFactory{
	
	private final CyNetworkManager netMgr;
	private final CyNetworkNaming namingUtil;
	private final CyNetworkFactory grnFactory;
	private final CyNetworkViewManager networkViewManager;
	private final CyNetworkViewFactory cnvf;
	private CyNetworkView grnView;
	private final CyLayoutAlgorithm grnLayout;
	private final VisualMappingManager grnVMM;
	
	public CreateNetworkFromOutput(final CyNetworkManager netMgr, 
			final CyNetworkNaming namingUtil, final CyNetworkFactory grnFactory,
			final CyNetworkViewManager networkViewManager,
			final CyNetworkViewFactory cnvf,
			final CyNetworkView grnView,
			final CyLayoutAlgorithm grnLayout,
			final VisualMappingManager grnVMM){
		
		this.netMgr = netMgr;
		this.namingUtil = namingUtil;
		this.grnFactory = grnFactory;
		this.networkViewManager = networkViewManager;
		this.cnvf = cnvf;
		this.grnView = grnView;
		this.grnLayout = grnLayout;
		this.grnVMM = grnVMM;
	}
	
	
	@Override
	public TaskIterator createTaskIterator() {
	
		return new TaskIterator(new CreateNetworkTask(netMgr, namingUtil, 
				grnFactory,networkViewManager, cnvf, grnView, grnLayout,grnVMM) );
	
	}
	
	
}
