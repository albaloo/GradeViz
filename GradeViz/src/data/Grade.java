/**
 * This file is licensed under the University of Illinois/NCSA Open Source License. See LICENSE.TXT for details.
 */
package data;
/**
 * @author Roshanak Zilouchian
 */
public class Grade {

	private Assignment assignment;
	private int grade = 0;
	private int color;
	
	public Grade(Assignment name, double grade) {
		super();
		this.assignment = name;
		this.grade = (int)grade;
	}
	public Assignment getAssignment() {
		return assignment;
	}
	public void setAssignment(Assignment name) {
		this.assignment = name;
	}
	public int getColor(){
		return this.color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public int getGrade(){
		return grade;
	}
	public void setGrade(int grade){
		this.grade = grade;
	}

}
