package org.cytoscape.grnapps.grn.internal;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import org.cytoscape.work.AbstractTask;
import org.cytoscape.work.TaskMonitor;
import birc.grni.rf.Genie3Time;

public class RFtask extends AbstractTask{
	
	public RFtask(){
	}

	public void run(final TaskMonitor taskMonitor) throws FileNotFoundException, IOException, InterruptedException {
		
		taskMonitor.setTitle("Random Forest");

		taskMonitor.setProgress(0.1);
		
		String loadedFilePath = OpenFileMenuAction.getInputFilePath();

		
		double matrix[][] = Genie3Time.run(loadedFilePath, "RF", 100);
		
		String tmpDir = System.getProperty("java.io.tmpdir");		
		File outputfile = new File(tmpDir, "finalnetwork.txt");
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(outputfile));
		
		for(int i=1; i<=matrix.length; i++){
			for(int j=1; j<=matrix[i-1].length;j++)
			{
				bw.write("G"+i+"\t"+"G"+j+"\t" + (int)matrix[i-1][j-1]);
				bw.newLine();
			}
		}
			
		bw.close();
		
		taskMonitor.setProgress(1.0);
	}
		   
}
