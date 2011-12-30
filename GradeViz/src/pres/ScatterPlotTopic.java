/**
 * This file is licensed under the University of Illinois/NCSA Open Source License. See LICENSE.TXT for details.
 */
package pres;
/**
 * @author Roshanak Zilouchian
 */
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class ScatterPlotTopic extends ScatterPlot {

	/*private String currentTopic;
	private String currentStudent;
	private GradePoint selectedPoint;
	private boolean isSelected = false;
	*/
	public ScatterPlotTopic(ScatterPlotView parentComponent) {
		super(parentComponent);
		this.setLayout(null);
	}

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

		g2.dispose();
		
	}

	//@Override
	/*protected void drawHorizontalAxis(Graphics2D g){
		int axisWidth = parentComponent.getMainForm().getFileReader().getStudents().size() * 2 * hRulerDistanceStep + PANEL_INSETS + hRulerDistanceStep;//getWidth() - 2 * PANEL_INCENTS;
		int axisHeight = getHeight() - PANEL_INSETS;
		
		Font f2 = new Font ("sans-serif", Font.BOLD, 10);
	    g.setFont (f2);
		g.drawString("Students", axisWidth/2-8, getHeight() - PANEL_INSETS + 29);
		
		g.setFont(f);
		g.setColor(Color.BLACK);
		g.drawLine(PANEL_INSETS, axisHeight, axisWidth, axisHeight);
		
		int x = PANEL_INSETS + hRulerDistanceStep;
		for (StudentRecord student: parentComponent.getMainForm().getFileReader().getStudents()){//int x = distanceStep; x < getWidth(); x += distanceStep) {
			g.drawLine(x, axisHeight, x, axisHeight - RULER_Height);
			//g.translate(10.0, 50.0);
            //g.rotate( 300 );
			g.drawString(student.getFirstName(), x - 6, getHeight() - PANEL_INSETS + 10);
			x += 2*hRulerDistanceStep;
		}

		int[] xPoints = {axisWidth , axisWidth, axisWidth + 6};
		int[] yPoints = {axisHeight - 4, axisHeight + 4, axisHeight};
		
		g.fillPolygon(xPoints, yPoints, 3);
	}
	
	protected void drawIndicatorLinesForAllPoints(Graphics2D g){
		int axisWidth = parentComponent.getMainForm().getFileReader().getStudents().size() * 2 * hRulerDistanceStep + PANEL_INSETS + hRulerDistanceStep;//getWidth() - 2 * PANEL_INCENTS;
		Font f2 = new Font ("sans-serif", Font.BOLD, 12);
	    g.setFont (f2);
		g.drawString(currentTopic, axisWidth-3*currentTopic.length()-PANEL_INSETS, PANEL_INSETS);//getHeight() - PANEL_INCENTS + 29);
		
		g.setFont(f);
		Stroke drawingStroke = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{4}, 0);
		g.setStroke(drawingStroke);
		g.setColor(Color.black);
		g.drawLine(selectedPoint.getX() + selectedPoint.getPointSize()/2, selectedPoint.getY() + selectedPoint.getPointSize()/2, selectedPoint.getX() + selectedPoint.getPointSize()/2, getHeight() - PANEL_INSETS);
		
		drawingStroke = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{4}, 0);	
		g.setStroke(drawingStroke);
		g.setColor(new Color(0x9998fe));
		for(GradePoint point: allGradePoints){
			if(!point.equals(selectedPoint))
				g.drawLine(point.getX() + point.getPointSize()/2, point.getY() + point.getPointSize()/2, point.getX() + point.getPointSize()/2, getHeight() - PANEL_INSETS);
		}
//			g.drawLine(selectedPoint.getX() + selectedPoint.getPointSize()/2, selectedPoint.getY() + selectedPoint.getPointSize()/2, PANEL_INCENTS,selectedPoint.getY() + selectedPoint.getPointSize()/2);
	}

	@Override
	protected void drawAverage(Graphics2D g){
		int x = PANEL_INSETS;
		int y = 0;
		int average = 0;
		int numTopics = 0;
		int axisWidth = parentComponent.getMainForm().getFileReader().getStudents().size() * 2 * hRulerDistanceStep + PANEL_INSETS + hRulerDistanceStep;
		for(StudentRecord student: parentComponent.getMainForm().getFileReader().getStudents()){
			int averageGradePerTopic = student.findAverageGradeInTopic(currentTopic);
			if(averageGradePerTopic >= 0){
				average += averageGradePerTopic;
				numTopics++;
			}
		}
		average = average / numTopics;
		y = getHeight() - PANEL_INSETS - average * vRulerDistanceStep * 20 / 100;
		g.drawLine(x, y, axisWidth, y);
	}
	
	@Override
	public void paintPoints() {		
		Point location = new Point(0, 0);
		for(StudentRecord student: parentComponent.getMainForm().getFileReader().getStudents()){
			for(Grade topic: student.getGrades()){
				if(topic.getName().equals(currentTopic)){
					int average = topic.findAverageGrade();
					GradePoint gradePoint = new GradePoint(average, student.getFirstName(), topic.getMonth(), topic.getName());
					location = findLocation(average, location, gradePoint.getPointSize());
					if(student.getFirstName().equals(currentStudent)){
						selectedPoint = gradePoint;
						gradePoint.highlight();
						location.x = location.x - 2;
					}
					else
						gradePoint.makeTransparent();

					gradePoint.setBounds(location.x, location.y, gradePoint.getPointSize(), gradePoint.getPointSize());
					gradePoint.setToolTipText("<html><b>Student Name:</b> " + student.getFirstName() + " <br>" + "<b>Topic:</b> "
						+ topic.getName() + " <br>" +"<b>Grade:</b> " + average + "<br>" + "<b>Month:</b> " + topic.getMonth() + "<br>" + "</html>");
					//gradePoint.addMouseListener(new GradePointHover(gradePoint));
					if(parentComponent.getMainForm().isStudentHidden(student.getFirstName()))
						hiddenGradePoints.add(gradePoint);
					else{
						this.add(gradePoint);
						allGradePoints.add(gradePoint);
					}
					if(student.getFirstName().equals(currentStudent))
						location.x = location.x + 2;
				}
			}
		}
	}
	
	protected Point findLocation(int average, Point prevLocation, int pointSize) {
		int x = 2 * hRulerDistanceStep + prevLocation.x;
		if(prevLocation.x == 0)
			x += PANEL_INSETS - hRulerDistanceStep - pointSize / 2;
		int y = getHeight() - PANEL_INSETS - average * vRulerDistanceStep * 20 / 100;
		return new Point(x, y);
	}
*/
	public class GradePointHover implements MouseListener{
//		private GradePoint currentGradePoint;

		public GradePointHover(GradePoint currentGradepoint) {
//			this.currentGradePoint = currentGradepoint;
		}
		
		public void mouseClicked(MouseEvent mouseEvent) {
			/*if(isFixated == false)
				isFixated = true;
			else
				isFixated = false;
				*/
		}

		public void mouseEntered(MouseEvent mouseEvent) {
			/*if(!isFixated){
				parentComponent.setInteractiveTreeViewPanel(InteractiveTreeView.contructTree("res/"+currentGradePoint.getStudentName()+"-data.xml", "name"));
				for (GradePoint point : allGradePoints) {
					if(!point.getStudentName().equals(currentGradePoint.getStudentName())){
						point.makeTransparent();
						point.setBounds(point.getX(), point.getY(), point.getWidth()-4, point.getHeight()-4);
						parentComponent.getMainForm().repaint();
					}else{
						//selectedPoints.add(point);//new Point(point.getX()+point.getPointSize()/2, point.getY()+point.getPointSize()/2));
					}
				}
				averageIsShown = true;
			}*/
		}

		public void mouseExited(MouseEvent mouseEvent) {
			/*if(!isFixated){
				for (GradePoint point : allGradePoints) {
					if(!point.getStudentName().equals(currentGradePoint.getStudentName())){
						point.makeOpaque();
						point.setBounds(point.getX(), point.getY(), point.getWidth()+4, point.getHeight()+4);
						parentComponent.getMainForm().repaint();
					}
					//selectedPoints = new ArrayList<GradePoint>();
				}
				averageIsShown = false;
			}*/
		}

		public void mousePressed(MouseEvent mouseEvent) {
		}

		public void mouseReleased(MouseEvent mouseEvent) {
		}
	
	}

	@Override
	public void update(){
		for (GradePoint point : allGradePoints) {
			point.setBounds(point.getX(), point.getY(), point.getPointSize(), point.getPointSize());
		}
	}
	
	public void removePrevPoints(){
		for (GradePoint point : allGradePoints) {
			this.remove(point);
		}
		allGradePoints.clear();
		hiddenGradePoints.clear();
	}
	
	@Override
	public void hideStudent(String studentName) {
		ArrayList<GradePoint> tobeDeleted = new ArrayList<GradePoint>();
		
		for (GradePoint point : allGradePoints) {
			if(point.getStudentUserID().equals(studentName)){
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
			if(point.getStudentUserID().equals(studentName)){
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

	/*public void setCurrentTopic(String topic) {
		currentTopic = topic;
	}
	
	public void setCurrentStudent(String student){
		currentStudent = student;
	}

	public void setIsSelected(boolean isSelected){
		this.isSelected = isSelected;
	}

	public boolean isSelected() {
		return isSelected;
	}*/
}
