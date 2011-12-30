/**
 * This file is licensed under the University of Illinois/NCSA Open Source License. See LICENSE.TXT for details.
 */
package data;
/**
 * @author Roshanak Zilouchian
 */
import java.util.ArrayList;

public class StudentRecord {
	private String firstName;
	private String lastName;
	private String userID;
	private String labSection;
	private ArrayList<Grade> grades = new ArrayList<Grade>();
	private int color;
	private String department;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String name) {
		this.firstName = name;
	}
	public String getUserID(){
		return userID;
	}
	public void setUserID(String userID){
		this.userID = userID;
	}
	public String getLabSection(){
		return labSection;
	}
	public void setLabSection(String labSection){
		this.labSection = labSection;
	}
	public ArrayList<Grade> getGrades() {
		return grades;
	}
	public void addGrade(Grade grade) {
		this.grades.add(grade);
	}
	public Grade getGrade(int index){
		return grades.get(index);
	}
	public int getColor(){
		return this.color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department){
		this.department = department;
	}
	public String getLastName(){
		return lastName;
	}
	public void setLastName(String familyName){
		this.lastName = familyName;
	}
	public int findAverageGrade(){
		int averageGrade = 0;
		for (Grade topic : grades) {
			averageGrade += topic.getGrade();
		}
		averageGrade = averageGrade / grades.size();
		return averageGrade;
	}
	public int findGradeInTopic(String topic) {
		for(Grade top: grades){
			if(top.getAssignment().equals(topic)){
				return top.getGrade();
			}
		}
		return -1;
	}
}
