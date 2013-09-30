package org.cytoscape.grnapps.grn.internal;

import org.cytoscape.work.AbstractTaskFactory;
import org.cytoscape.work.TaskIterator;


public class RFtaskFactory extends AbstractTaskFactory{
	public RFtaskFactory(){
		
	}
	
	public TaskIterator createTaskIterator(){
		
		return new TaskIterator(new RFtask());
	}

}
