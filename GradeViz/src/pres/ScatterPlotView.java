/**
 * This file is licensed under the University of Illinois/NCSA Open Source License. See LICENSE.TXT for details.
 */
package pres;
/**
 * @author Roshanak Zilouchian
 */
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ScatterPlotView extends JPanel{

	private MainWindow mainForm;
		
	private JFrame currentFrame;
		
	private ScatterPlotGeneral scatterPlotGeneral;
	
	//private ScatterPlotTopic scatterPlotTopic;
	
	
	public ScatterPlotView(MainWindow mainForm) {
		this.mainForm = mainForm;
		this.setLayout(new GridBagLayout());
		scatterPlotGeneral = new ScatterPlotGeneral(this);
		//scatterPlotTopic = new ScatterPlotTopic(this);
		
		//position: gridx, gridy - numberofCells:gridwidth, gridheight - resizewaigth: waightx, y 
	
		this.add(scatterPlotGeneral, new GridBagConstraints(0, 0, 3, 1, 1.0, 1.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
						0, 0, 0, 0), 0, 0));
		
		/*this.add(scatterPlotTopic, new GridBagConstraints(2, 0, 1, 2,
				1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0), 0, 0));
		*/
	
	}

	public void paintScatterPlot(){
		scatterPlotGeneral.paintPoints();
	}
	
	public void setMainForm(MainWindow form) {
		mainForm = form;
	}

	public MainWindow getMainForm() {
		return mainForm;
	}
	
	public ScatterPlotGeneral getScatterPlotGeneral(){
		return scatterPlotGeneral;
	}
	
//	public ScatterPlotTopic getScatterPlotTopic(){
//		return scatterPlotTopic;
//	}
	
	public JFrame getCurrentFrame() {
		return currentFrame;
	}

	public void hideStudent(String studentName) {
		scatterPlotGeneral.hideStudent(studentName);
//		scatterPlotTopic.hideStudent(studentName);
	}
	
	public void showStudent(String studentName) {
		scatterPlotGeneral.showStudent(studentName);
//		scatterPlotTopic.showStudent(studentName);
	}
	
	public void hideTopic(String topic) {
		scatterPlotGeneral.hideTopic(topic);	
	}
	
	public void showTopic(String topic) {
		scatterPlotGeneral.showTopic(topic);
	}

	public void hideLabels() {
		scatterPlotGeneral.setAreLabelsHidden(true);		
	}

	public void showLabels() {
		scatterPlotGeneral.setAreLabelsHidden(false);	
	}

	public void hideIndicatorLines() {
		scatterPlotGeneral.setAreIndicatorLinesHidden(true);	
	}

	public void showIndicatorLines() {
		scatterPlotGeneral.setAreIndicatorLinesHidden(false);	
	}

	public void hideAverage() {
		scatterPlotGeneral.setIsAverageHidden(true);	
	}
	
	public void showAverage() {
		scatterPlotGeneral.setIsAverageHidden(false);
	}
		
}
