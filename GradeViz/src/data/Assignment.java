/**
 * This file is licensed under the University of Illinois/NCSA Open Source License. See LICENSE.TXT for details.
 */
package data;
/**
 * @author Roshanak Zilouchian
 */

public class Assignment {

	protected int hRulerDistanceStep = 40;
	String topic;
	int average = 0;
	int num = 0;
	
	public Assignment(String topic) {
		super();
		this.topic = topic;
	}
	public String getTopic(){
		return topic;
	}
	public int getAverage(){
		return average;
	}
	public void setAverage(int average){
		this.average = average;
	}
	public void calculateAverage(){
		average = average/num;
	}
	public void addGrade(int grade){
		average += grade;
		num++;
	}
	public int getWidth(){
		if(topic.contains("_EC")){
			return hRulerDistanceStep/4;
		}else if(topic.contains("MP"))
			return hRulerDistanceStep;
		else if (topic.contains("Lab"))
			return hRulerDistanceStep/2;
		else if(topic.contains("MT"))
			return hRulerDistanceStep*3/2;
		else 
			return hRulerDistanceStep;
	}
}
