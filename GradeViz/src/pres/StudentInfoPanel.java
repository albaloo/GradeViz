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

import javax.swing.JLabel;
import javax.swing.JPanel;

import data.StudentRecord;

@SuppressWarnings("serial")
public class StudentInfoPanel extends JPanel {

	private final static int PANEL_INSETS = 20;
	
	private StudentRecord currentStudent;
	
	private JLabel pic;
	
	private JLabel name;
	
	private JLabel department;
	
	private JLabel totalGrade;

	private JLabel netid;
	
	
	
	public StudentInfoPanel() {
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

		g2.dispose();
	}
	
	public void showStudentInfo(){
		pic = new JLabel();
		String picLabel = "<html> <table cellpadding=0>" +
				"<tr><td><img src=file:res/student.gif" +
				"></td></tr></table></html>";
		//<td width=" + 3 + "><td>" + currentStudent.getName() + 
		//"</td>
		int y = PANEL_INSETS;
		pic.setText(picLabel);
		pic.setBounds(PANEL_INSETS, y, getWidth()-2*PANEL_INSETS, 2*PANEL_INSETS);
		this.add(pic);
	
		name = new JLabel();
		String nameLabel = "<html> <table cellpadding=0>" +
		"<tr><td>" + 
		"</td><td width=" + 3 + "><td>" + currentStudent.getFirstName() + " " + currentStudent.getLastName() +
		"</td></tr></table></html>";
		y += 3*PANEL_INSETS;
		name.setText(nameLabel);
		name.setBounds(PANEL_INSETS, y , getWidth()-2*PANEL_INSETS, 2*PANEL_INSETS);
		this.add(name);
	
		netid = new JLabel();
		String netidLabel = "<html> <table cellpadding=0>" +
		"<tr><td><b><i>" + "NetID: " +
		"</i></b></td><td width=" + 3 + "><td>" + currentStudent.getUserID() + 
		"</td></tr></table></html>";
		y += 3*PANEL_INSETS;
		netid.setText(netidLabel);
		netid.setBounds(PANEL_INSETS, y, getWidth()-2*PANEL_INSETS, 2*PANEL_INSETS);
		this.add(netid);
		
		department = new JLabel();
		String deptLabel = "<html> <table cellpadding=0>" +
		"<tr><td><b><i>" + "Department: " +
		"</i></b></td><td width=" + 3 + "><td>" + currentStudent.getDepartment() + 
		"</td></tr></table></html>";
		y += 3*PANEL_INSETS;
		department.setText(deptLabel);
		department.setBounds(PANEL_INSETS, y, getWidth()-2*PANEL_INSETS, 2*PANEL_INSETS);
		this.add(department);
		
		totalGrade = new JLabel();
		String gradeLabel = "<html> <table cellpadding=0>" +
		"<tr><td><b><i>" + "Total Grade: " +
		"</i></b></td><td width=" + 3 + "><td>" + currentStudent.findAverageGrade() + 
		"</td></tr></table></html>";
		y += 3*PANEL_INSETS;
		totalGrade.setText(gradeLabel);
		totalGrade.setBounds(PANEL_INSETS, y, getWidth()-2*PANEL_INSETS, 2*PANEL_INSETS);
		this.add(totalGrade);
		
		
	}
	
	public void hideStudentInfo() {
		this.remove(pic);
		this.remove(name);
		this.remove(netid);
		this.remove(department);
		this.remove(totalGrade);
	}
		
	public void setCurrentStudent(StudentRecord student){
		this.currentStudent = student;
	}
}
