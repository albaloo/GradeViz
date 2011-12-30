/**
 * This file is licensed under the University of Illinois/NCSA Open Source License. See LICENSE.TXT for details.
 */
package pres;
/**
 * @author Roshanak Zilouchian
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ScatterPlot extends JPanel {

	protected final static int PANEL_INSETS = 80;

	protected ArrayList<GradePoint> allGradePoints = new ArrayList<GradePoint>();
	
	protected ArrayList<GradePoint> hiddenGradePoints = new ArrayList<GradePoint>();
	
	protected Font f = new Font("sans-serif", Font.PLAIN, 9);

	protected final static int RULER_Height = 8;
	
	protected int hRulerDistanceStep = 40;
	
	protected int vRulerDistanceStep = 34;
	
	protected ScatterPlotView parentComponent;
	
	protected boolean isFixated = false;
	
	protected boolean averageCanBeShown = false;
	
	public ScatterPlot(ScatterPlotView parentComponent) {
		this.parentComponent = parentComponent;
		this.setLayout(null);
	}

	protected void drawVerticalAxis(Graphics2D g){	
		int axisWidth = PANEL_INSETS;
		int axisHeight = getHeight() - PANEL_INSETS - vRulerDistanceStep * 20 - vRulerDistanceStep/2;

		Font f2 = new Font ("sans-serif", Font.BOLD, 10);
	    g.setFont (f2);
	    g.setColor(Color.BLACK);
	    
		g.rotate(Math.toRadians(270), PANEL_INSETS - 23, getHeight()/2);
		g.drawString("Grades", PANEL_INSETS - 23, getHeight()/2);
		g.rotate(Math.toRadians(-270), PANEL_INSETS - 23, getHeight()/2); 

		g.setFont(f);

		g.drawLine(axisWidth, getHeight() - PANEL_INSETS, axisWidth, axisHeight);
		
		int y = getHeight() - PANEL_INSETS;//vRulerDistanceStep;
		for (int grade = 0; grade <= 100; grade+=5) {
			g.drawLine(axisWidth, y, axisWidth + RULER_Height / 2, y);
			if (grade % (10) == 0) {
				g.drawLine(axisWidth, y, axisWidth + RULER_Height, y);
				g.drawString("" + grade, axisWidth - 18, y+2);
			}
			y -= vRulerDistanceStep;
		}
		
		int[] xPoints = {axisWidth - 4 , axisWidth + 4, axisWidth};
		int[] yPoints = {axisHeight, axisHeight, axisHeight - 6};
		
		g.fillPolygon(xPoints, yPoints, 3);

	}

	protected void drawHorizontalAxis(Graphics2D g){
	
	}
	protected void drawIndicatorLinesForSelectedPoints(Graphics2D g){
		
	}
	protected void drawAverage(Graphics2D g){
		
	}	
	public void paintPoints(){
		
	}
	public void update(){
		
	}
	public void hideStudent(String studentName) {
		
	}
	public void showStudent(String studentName) {
		
	}
	public void hideTopic(String topic) {
		
	}
	public void showTopic(String topic) {
		
	}
	public void setIsFixated(boolean isFixated){
		this.isFixated = isFixated;
	}
}
