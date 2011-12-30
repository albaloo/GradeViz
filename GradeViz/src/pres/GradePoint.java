/**
 * This file is licensed under the University of Illinois/NCSA Open Source License. See LICENSE.TXT for details.
 */
package pres;
/**
 * @author Roshanak Zilouchian
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;

import data.Assignment;



@SuppressWarnings("serial")
public class GradePoint extends JButton{
	private Color pointColor;
	private Color prevColor;
	private int shrink = 0;
	private static final int POINTDIAMETER = 4;
	private String UserID;
	private Assignment assignment;
	private int grade;
	
	public GradePoint(int grade, String studentName, Assignment topic, int average) {
		if(grade <= average/2)
			pointColor = new Color(250,5,5, 255);
		else if(grade <= average)
			pointColor = new Color(200,200,0, 255);
		else
			pointColor = new Color(50,205,50, 255);		
		this.UserID = studentName;
		this.assignment = topic;
		this.grade = grade; 
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setColor(pointColor);
		g2.fillRect(0, 0, POINTDIAMETER - shrink, POINTDIAMETER - shrink);	
	}
	
	public int getGrade(){
		return grade;
	}
	public String getStudentUserID(){
		return UserID;
	}

	public Assignment getAssignment(){
		return assignment;
	}
	
	public void makeTransparent() {
		prevColor = pointColor;
		pointColor = new Color((pointColor.getRed()), (pointColor.getGreen()), (pointColor.getBlue()), 20);
		this.setOpaque(false);
		repaint();
	}

	public void makeTransparentAndShrink() {
		prevColor = pointColor;
		pointColor = new Color((pointColor.getRed()+255)/2, (pointColor.getGreen()+255)/2, (pointColor.getBlue()+255)/2);
		this.setOpaque(false);
		shrink = 2;
		repaint();
	}

	public void makeOpaque(){
		pointColor = prevColor;//new Color(2*pointColor.getRed()-255, 2*pointColor.getGreen()-255, 2*pointColor.getBlue()-255);
		this.setOpaque(true);
		repaint();
	}
	
	public void makeOpaqueAndEnlarge(){
		pointColor = prevColor;//new Color(2*pointColor.getRed()-255, 2*pointColor.getGreen()-255, 2*pointColor.getBlue()-255);
		this.setOpaque(true);
		shrink = 0;
		repaint();
	}
	
	public int getPointSize(){
		return POINTDIAMETER - shrink;
	}

	public void highlight() {
		//pointColor = new Color(2*(pointColor.getRed())/3, 2*(pointColor.getGreen())/3, 2*(pointColor.getBlue())/3);
		shrink = -2;
		repaint();
	}
	
	public boolean isShrinked(){
		if(shrink != 0 )
			return true;
		else
			return false;
	}
}
