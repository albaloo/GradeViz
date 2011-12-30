/**
 * This file is licensed under the University of Illinois/NCSA Open Source License. See LICENSE.TXT for details.
 */
package data;
/**
 * @author Roshanak Zilouchian
 */
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import au.com.bytecode.opencsv.CSVReader;


public class FileReader {

	int gradeBase = 0;
	ArrayList <Assignment> assignments = new ArrayList<Assignment>();
	
	public ArrayList<Assignment> getAssignment(){
		return assignments;
	}
	
	private static Pattern doublePattern = Pattern.compile("-?\\d+(\\.\\d*)?");

	public boolean isDouble(String string) {
	    return doublePattern.matcher(string).matches();
	}
	
	public boolean isInteger(String string) {
	    try {
	        Integer.valueOf(string);
	        return true;
	    } catch (NumberFormatException e) {
	        return false;
	    }
	}
	
	public ArrayList<StudentRecord> parseCVSFile(String filename) {
		ArrayList<StudentRecord> records = new ArrayList<StudentRecord>();
		try {
			CSVReader reader = new CSVReader(new java.io.FileReader(filename));
			 List<String[]> myEntries = reader.readAll();
			 ArrayList<String[]> entries = new ArrayList<String[]>();
			 entries.addAll(myEntries);
			 
			 ArrayList<String> topics = new ArrayList<String>();
			 for (int i=0; i < entries.get(0).length; i++) {
				 topics.add(entries.get(0)[i]);
				 if(i > 3)
					 assignments.add(new Assignment(entries.get(0)[i]));
			 }

			 for (int i = 1; i < entries.size(); i++) {
				StudentRecord sr = new StudentRecord();
				sr.setLastName(entries.get(i)[0]);
				sr.setFirstName(entries.get(i)[1]);
				sr.setUserID(entries.get(i)[2]);
				sr.setLabSection(entries.get(i)[3]);
				for (int j = 4; j < entries.get(i).length; j++) {
					if(isInteger(entries.get(i)[j]) || isDouble(entries.get(i)[j])){
						sr.addGrade(new Grade(findAssignment(topics.get(j)), new Double(entries.get(i)[j])));
						AddGradeToAssignment(topics.get(j), new Double(entries.get(i)[j]));
					}
					else
						sr.addGrade(new Grade(findAssignment(topics.get(j)), 0));
				}
				records.add(sr);
			 }
				for (int i = 0; i < assignments.size(); i++) {
					assignments.get(i).calculateAverage();
				}
			
		} catch (FileNotFoundException e) {
			System.out.println("Please enter a correct CSV file name/path.");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return records;
	}

	private Assignment findAssignment(String topic){
		for (int i = 0; i < assignments.size(); i++) {
			if(assignments.get(i).getTopic().equals(topic))
				return assignments.get(i);
		}
		return null;
	}
	private void AddGradeToAssignment(String topic, Double grade){
		for (int i = 0; i < assignments.size(); i++) {
			if(assignments.get(i).getTopic().equals(topic))
				assignments.get(i).addGrade(grade.intValue());
		}
	}
}