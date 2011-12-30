/**
 * This file is licensed under the University of Illinois/NCSA Open Source License. See LICENSE.TXT for details.
 */
package pres;
/**
 * @author Roshanak Zilouchian
 */
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import data.Assignment;
import data.StudentRecord;
import data.Grade;

@SuppressWarnings("serial")
public class ScatterPlotGeneral extends ScatterPlot {
	
	protected ArrayList<GradePoint> selectedPoints = new ArrayList<GradePoint>();

	private ArrayList<GradePoint> hiddenShrinkedPoints = new ArrayList<GradePoint>();

	private boolean areLabelsHidden = false;
	private boolean areIndicatorLinesHidden = false;
	private boolean isAverageHidden = false;

	GradePoint points[][];
	public ScatterPlotGeneral(ScatterPlotView parentComponent) {
		super(parentComponent);
		this.setLayout(null);
		points = new GradePoint[100][40];
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setColor(new Color(0xf8f5f4));
		g2.fillRect(0, 0, getWidth(), getHeight());
		g2.setStroke(new BasicStroke(3f));
		g2.setColor(new Color(0x354d94));
		g2.drawRoundRect(3, 3, getWidth()-6, getHeight()-6, 15, 15);
		g2.setStroke(new BasicStroke(1f));
		drawVerticalAxis(g2);
		drawHorizontalAxis(g2);

		if(averageCanBeShown && !isAverageHidden)
			drawAverage(g2);
		
		//drawIndicatorLinesForSelectedPoints(g2);
		drawSelectedStudents(g2);
		g2.dispose();
		
	}
	
	@Override
	protected void drawHorizontalAxis(Graphics2D g){
		int axisWidth = parentComponent.getMainForm().getAssignmentsWidth() + PANEL_INSETS + hRulerDistanceStep;//getWidth() - 2 * PANEL_INCENTS;
		int axisHeight = getHeight() - PANEL_INSETS;
		
		Font f2 = new Font ("sans-serif", Font.BOLD, 10);
	    g.setFont (f2);
		g.drawString("Topics", axisWidth/2-8, getHeight() - PANEL_INSETS + 49);
		
		g.setFont(f);
		g.setColor(Color.BLACK);
		g.drawLine(PANEL_INSETS, axisHeight, axisWidth, axisHeight);
		
		int x = PANEL_INSETS + hRulerDistanceStep;
		for (Assignment topic: parentComponent.getMainForm().getAssignment()){//int x = distanceStep; x < getWidth(); x += distanceStep) {
			g.drawLine(x, axisHeight, x, axisHeight - RULER_Height);
			g.rotate(Math.PI/2, x, getHeight() - PANEL_INSETS + 15);
			g.drawString("" + topic.getTopic(), x - 8, getHeight() - PANEL_INSETS + 15);
			g.rotate(-Math.PI/2, x, getHeight() - PANEL_INSETS + 15);
			x += topic.getWidth();
		}

		int[] xPoints = {axisWidth , axisWidth, axisWidth + 6};
		int[] yPoints = {axisHeight - 4, axisHeight + 4, axisHeight};
		
		g.fillPolygon(xPoints, yPoints, 3);
	}
	
	private void drawSelectedStudents(Graphics2D g2){
		int x = PANEL_INSETS + hRulerDistanceStep + 6 ;
		int y = 0;
		int prevX = -1;
		int prevY = -1;
		for(GradePoint point: selectedPoints){
			int grade =  point.getGrade();
			if(grade > 100){
				grade = 100;
				g2.setColor(new Color(180, 224,178));
			}else
				g2.setColor(Color.LIGHT_GRAY);
			if(grade == 0)
				grade = 1;
			y = getHeight() - PANEL_INSETS - grade * vRulerDistanceStep * 20 / 100;
			g2.setStroke(new BasicStroke(2f));
			g2.drawLine(x, y, x+point.getAssignment().getWidth(), y);
			if(prevX != -1){
				g2.setColor(Color.LIGHT_GRAY);
				g2.drawLine(prevX, prevY, x, y);
			}
			prevX = x+point.getAssignment().getWidth();
			prevY = y;
			x += point.getAssignment().getWidth();
		}
	}
	
	@Override
	protected void drawIndicatorLinesForSelectedPoints(Graphics2D g){
		for(int i = 0; i < selectedPoints.size(); i++){
			g.setColor(Color.black);
			Font f2 = new Font ("sans-serif", Font.BOLD, 11);
		    g.setFont (f2);
	
		    if(!areLabelsHidden)
		    	g.drawString(selectedPoints.get(i).getAssignment().getTopic(), selectedPoints.get(i).getX() + selectedPoints.get(i).getPointSize() + 3, selectedPoints.get(i).getY() + selectedPoints.get(i).getPointSize()/2);
			g.setFont(f);
			g.setColor(new Color(0x9998fe));
			Stroke drawingStroke = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{4}, 0);
			g.setStroke(drawingStroke);
			if(!areIndicatorLinesHidden){
				g.drawLine(selectedPoints.get(i).getX() + selectedPoints.get(i).getPointSize()/2, selectedPoints.get(i).getY() + selectedPoints.get(i).getPointSize()/2, selectedPoints.get(i).getX() + selectedPoints.get(i).getPointSize()/2, getHeight() - PANEL_INSETS);
				g.drawLine(selectedPoints.get(i).getX() + selectedPoints.get(i).getPointSize()/2, selectedPoints.get(i).getY() + selectedPoints.get(i).getPointSize()/2, PANEL_INSETS,selectedPoints.get(i).getY() + selectedPoints.get(i).getPointSize()/2);
			}
		}
	}

	@Override
	protected void drawAverage(Graphics2D g){
		int x = PANEL_INSETS + hRulerDistanceStep +2;
		int y = 0;
		int prevX = -1;
		int prevY = -1;
		for(Assignment topic: parentComponent.getMainForm().getAssignment()){
			y = getHeight() - PANEL_INSETS - topic.getAverage() * vRulerDistanceStep * 20 / 100;
			g.setStroke(new BasicStroke(2f));
			g.setColor(Color.DARK_GRAY);
			g.drawLine(x, y, x+topic.getWidth(), y);
			if(prevX != -1){
				g.drawLine(prevX, prevY, x, y);
			}
			prevX = x+topic.getWidth();
			prevY = y;
			x += topic.getWidth();
		}
	}
	
	@Override
	public void paintPoints() {		
		for(StudentRecord student: parentComponent.getMainForm().getStudentRecords()){
			Point location = new Point(0, 0);
			int prevWidth = 0;
			for(Grade grade: student.getGrades()){
				GradePoint gradePoint = new GradePoint(grade.getGrade(), student.getUserID(), grade.getAssignment(), parentComponent.getMainForm().getAverageGradeInAssignment(grade.getAssignment().getTopic()));
				location = findLocation(grade.getGrade(), location, gradePoint.getPointSize(), prevWidth);
				prevWidth = grade.getAssignment().getWidth();
				gradePoint.setBounds(location.x, location.y, gradePoint.getPointSize(), gradePoint.getPointSize());
				gradePoint.setToolTipText("<html><b>Student Name:</b> " + student.getFirstName() + " <br>" + "<b>Topic:</b> "
						+ grade.getAssignment() + " <br>" +"<b>Grade:</b> " + grade.getGrade() + "<br>" + "</html>");
				gradePoint.addMouseListener(new GradePointHover(gradePoint));
				this.add(gradePoint);
				allGradePoints.add(gradePoint);
			}
		}
	}
	
	protected Point findLocation(int average,Point prevLocation, int pointSize, int prevWidth) {
		int x = PANEL_INSETS + hRulerDistanceStep - pointSize / 2;
		if(average > 100)
			average = 100;
		int y = getHeight() - PANEL_INSETS - average * vRulerDistanceStep * 20 / 100;
		
		if(prevLocation.x >= x)
			x = prevLocation.x + prevWidth;
	
		return new Point(x, y);
	}

	public class GradePointHover implements MouseListener{
		private GradePoint currentGradePoint;
		public GradePointHover(GradePoint currentGradepoint) {
			this.currentGradePoint = currentGradepoint;
		}
		
		public void mouseClicked(MouseEvent mouseEvent) {
			if(isFixated == false){
				isFixated = true;
				hideOtherGradePoints(currentGradePoint);
			}
			else{
				isFixated = false;
				showOtherGradePoints(currentGradePoint);
			}
		}

		public void mouseEntered(MouseEvent mouseEvent) {
			String topic = currentGradePoint.getAssignment().getTopic();
			int grade = currentGradePoint.getGrade();
			for (GradePoint point : allGradePoints) {
				if(point.getAssignment().getTopic().equals(topic) && point.getGrade() == grade){
					
				}
			}
			showSelectedStudent(currentGradePoint.getStudentUserID());
		}
		
		public void mouseExited(MouseEvent mouseEvent) {
			showAllStudents(currentGradePoint.getStudentUserID());
		}
		

		public void mousePressed(MouseEvent mouseEvent) {
		}

		public void mouseReleased(MouseEvent mouseEvent) {
		}
	
	}

	public void showSelectedStudent(String userID){
		if(!isFixated){
			for (GradePoint point : allGradePoints) {
				if(!point.getStudentUserID().equals(userID)){
					point.makeTransparent();
					parentComponent.getMainForm().repaint();
				}else{
					selectedPoints.add(point);//new Point(point.getX()+point.getPointSize()/2, point.getY()+point.getPointSize()/2));
				}
			}
			averageCanBeShown = true;
			parentComponent.getMainForm().getStudentInfoPanel().setCurrentStudent(parentComponent.getMainForm().getStudentByUserID(userID));
			parentComponent.getMainForm().getStudentInfoPanel().showStudentInfo();
		}
		parentComponent.getMainForm().repaint();
	}

	public void showAllStudents(String userID){
		if(!isFixated){
			for (GradePoint point : allGradePoints) {
				if(!point.getStudentUserID().equals(userID)){
					point.makeOpaque();
					parentComponent.getMainForm().repaint();
				}
				selectedPoints = new ArrayList<GradePoint>();
			}
			averageCanBeShown = false;
			parentComponent.getMainForm().getStudentInfoPanel().hideStudentInfo();
			parentComponent.getMainForm().repaint();
		}
	}
	@Override
	public void update(){
		for (GradePoint point : allGradePoints) {
			point.setBounds(point.getX(), point.getY(), point.getPointSize(), point.getPointSize());
		}
	}
	
	public void hideOtherGradePoints(GradePoint gradePoint){
		ArrayList<GradePoint> tobeDeleted = new ArrayList<GradePoint>();
		
		for (GradePoint point : allGradePoints) {
			if(!point.getStudentUserID().equals(gradePoint.getStudentUserID())){
				tobeDeleted.add(point);
				hiddenShrinkedPoints.add(point);
				this.remove(point);
			}
		}
		
		for(GradePoint point : tobeDeleted)
			allGradePoints.remove(point);
	
		update();
		parentComponent.repaint();
		
	}
	
	public void showOtherGradePoints(GradePoint gradePoint){
		ArrayList<GradePoint> tobeDeleted = new ArrayList<GradePoint>();
		
		for (GradePoint point : hiddenShrinkedPoints) {
			if(!point.getStudentUserID().equals(gradePoint.getStudentUserID())){
				tobeDeleted.add(point);
				if(parentComponent.getMainForm().isTopicHidden(point.getAssignment().getTopic()) || parentComponent.getMainForm().isStudentHidden(point.getStudentUserID())){
					hiddenGradePoints.add(point);
					if(point.isShrinked()){
						point.makeOpaqueAndEnlarge();
					}
				}else{
					allGradePoints.add(point);
					point.setBounds(point.getX(), point.getY(), point.getPointSize(), point.getPointSize());
					this.add(point);
				}
			}
		}
		
		for(GradePoint point : tobeDeleted)
			hiddenShrinkedPoints.remove(point);
		
		update();
		parentComponent.repaint();
		
	}
	
	@Override
	public void hideStudent(String userID) {
		ArrayList<GradePoint> tobeDeleted = new ArrayList<GradePoint>();
		
		for (GradePoint point : allGradePoints) {
			if(point.getStudentUserID().equals(userID)){
				tobeDeleted.add(point);
				hiddenGradePoints.add(point);
				this.remove(point);
			}
		}
		
		for(GradePoint point : tobeDeleted)
			allGradePoints.remove(point);
	
		update();
		parentComponent.repaint();
	}
	
	@Override
	public void showStudent(String studentName) {
		ArrayList<GradePoint> tobeDeleted = new ArrayList<GradePoint>();
		
		for (GradePoint point : hiddenGradePoints) {
			if(point.getStudentUserID().equals(studentName) && !parentComponent.getMainForm().isTopicHidden(point.getAssignment().getTopic())){
				tobeDeleted.add(point);
				if(!isFixated){
					allGradePoints.add(point);
					point.setBounds(point.getX(), point.getY(), point.getPointSize(), point.getPointSize());
					this.add(point);
				}else{
					if(!point.isShrinked())
						point.makeTransparentAndShrink();
					hiddenShrinkedPoints.add(point);
				}
			}
		}
		
		for(GradePoint point : tobeDeleted)
			hiddenGradePoints.remove(point);
		
		update();
		parentComponent.repaint();
	}
	
	@Override
	public void hideTopic(String topic) {
		ArrayList<GradePoint> tobeDeleted = new ArrayList<GradePoint>();
		System.out.println("remove month: " + topic);
		for (GradePoint point : allGradePoints) {
			if(point.getAssignment().getTopic().equals(topic)){
				this.remove(point);
				tobeDeleted.add(point);
				hiddenGradePoints.add(point);
				System.out.println("remove month: " + topic);
			}
		}
		
		for(GradePoint point : tobeDeleted)
			allGradePoints.remove(point);
		
		update();
		parentComponent.repaint();
	}
	
	@Override
	public void showTopic(String topic) {
		ArrayList<GradePoint> tobeDeleted = new ArrayList<GradePoint>();
		
		for (GradePoint point : hiddenGradePoints) {
			if(point.getAssignment().getTopic().equals(topic) && !parentComponent.getMainForm().isStudentHidden(point.getStudentUserID())){
				tobeDeleted.add(point);
				allGradePoints.add(point);
				point.setBounds(point.getX(), point.getY(), point.getPointSize(), point.getPointSize());
				this.add(point);
			}
		}
		
		for(GradePoint point : tobeDeleted)
			hiddenGradePoints.remove(point);
		
		update();
		parentComponent.repaint();
	}

	public void setAreLabelsHidden(boolean areLabelsHidden) {
		this.areLabelsHidden = areLabelsHidden;
	}

	public void setAreIndicatorLinesHidden(boolean areIndicatorLinesHidden) {
		this.areIndicatorLinesHidden = areIndicatorLinesHidden;
	}
	
	public void setIsAverageHidden(boolean isAverageHidden){
		this.isAverageHidden = isAverageHidden;
	}
}
