/**
 * This file is licensed under the University of Illinois/NCSA Open Source License. See LICENSE.TXT for details.
 */
package pres;
/**
 * @author Roshanak Zilouchian
 */
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import data.Assignment;
import data.StudentRecord;

@SuppressWarnings("serial")
public class FilterWindow extends JFrame implements ItemListener, ActionListener{
	JPanel topPanel;
	JTabbedPane filterTab;
	
	JPanel users;
	JScrollPane usersWrapper;
	ArrayList<JCheckBox> userCheckBoxes;
	
	JPanel topics;
	JScrollPane topicsWrapper;
	ArrayList<JCheckBox> topicsCheckBoxes;
	
	JPanel advancedActivities;
	JScrollPane advacedActivitiesWrapper;
	ArrayList<JCheckBox> advancedActivitiesCheckBoxes;
	
	MainWindow mainForm;
	
	public FilterWindow(MainWindow mainForm){
		initComponents();
		this.mainForm = mainForm;
		this.setTitle("Filters");
	}
	
	private void initComponents(){
		topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		getContentPane().add(topPanel);
		
		filterTab = new JTabbedPane();
		
		users = new JPanel();
		users.setLayout(new GridBagLayout());
		usersWrapper = new JScrollPane();
		usersWrapper = new JScrollPane(users);
		usersWrapper.setPreferredSize(new Dimension(40 ,50));
		usersWrapper.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		usersWrapper.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		filterTab.addTab("Students", usersWrapper);
		userCheckBoxes = new ArrayList<JCheckBox>();
		
		topics = new JPanel();
		topics.setLayout(new GridBagLayout());
		topicsWrapper = new JScrollPane();
		topicsWrapper = new JScrollPane(topics);
		topicsWrapper.setPreferredSize(new Dimension(40 ,50));
		topicsWrapper.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		topicsWrapper.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		filterTab.addTab("Topics", topicsWrapper);
		topicsCheckBoxes = new ArrayList<JCheckBox>();
		
		advancedActivities = new JPanel();
		advancedActivities.setLayout(new GridBagLayout());
		advacedActivitiesWrapper = new JScrollPane();
		advacedActivitiesWrapper = new JScrollPane(advancedActivities);
		advacedActivitiesWrapper.setPreferredSize(new Dimension(40 ,50));
		advacedActivitiesWrapper.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		advacedActivitiesWrapper.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		filterTab.addTab("Advanced", advacedActivitiesWrapper);
		advancedActivitiesCheckBoxes = new ArrayList<JCheckBox>();
	
		topPanel.add(filterTab, BorderLayout.CENTER);
	}
	
	public void initTabs(){
		initUsersPage();
		initMonthsPage();
		initAdvacedActivitiesPage();		
	}
	
	private void initUsersPage(){

		JPanel buttons = new JPanel();
		buttons.setLayout(new GridBagLayout());
		JButton all = new JButton("Select All");
		JButton none = new JButton("Select None");
	
		buttons.add(all, new GridBagConstraints(0, 0, 1, 1, 0.5, 1.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
		all.addActionListener(this);
		all.setActionCommand("all-users");
		
		buttons.add(none, new GridBagConstraints(1, 0, 1, 1, 0.5, 1.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
		none.addActionListener(this);
		none.setActionCommand("none-users");
		
		int gridy = 0;
		users.add(buttons, new GridBagConstraints(0, gridy, 1, 1, 1.0, 1.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
		gridy++;		
			
		for (StudentRecord student : mainForm.getStudentRecords()) {
			JCheckBox box = new JCheckBox();
			String label = "<html> <table cellpadding=0>" +
					"<tr><td><img src=file:res/" + student.getFirstName() + ".gif" +
					"></td><td width=" + 3 + "><td>" + student.getFirstName() + 
					"</td></tr></table></html>";
			box.setText(label);
			users.add(box, new GridBagConstraints(0, gridy, 1, 1, 1.0, 1.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
							0, 0, 0, 0), 0, 0));
			box.setSelected(true);
			box.addItemListener(this);
			userCheckBoxes.add(box);
			gridy++;
		}
		
	}
	
	private void initMonthsPage(){
		JPanel buttons = new JPanel();
		buttons.setLayout(null);
		JButton all = new JButton("Select All");
		JButton none = new JButton("Select None");

		all.setBounds(2, 2, 135, 45);
		buttons.add(all);
		all.addActionListener(this);
		all.setActionCommand("all-months");
		
		none.setBounds((getWidth()-20)/2,2, 135, 45);
		buttons.add(none);
		none.addActionListener(this);
		none.setActionCommand("none-months");
		
		int gridy = 0;
		topics.add(buttons, new GridBagConstraints(0, gridy, 1, 1, 1.0, 1.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
		gridy++;		
		for (Assignment topic : mainForm.getAssignment()) {
			JCheckBox box = new JCheckBox();
			box.setText(topic.getTopic());
			topics.add(box, new GridBagConstraints(0, gridy, 1, 1, 1.0, 1.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
							0, 0, 0, 0), 0, 0));
			box.setSelected(true);
			box.addItemListener(this);
			topicsCheckBoxes.add(box);
			gridy++;
		}
		
	}
	
	private void initAdvacedActivitiesPage(){
		JPanel buttons = new JPanel();
		buttons.setLayout(null);
		JButton all = new JButton("Select All");
		JButton none = new JButton("Select None");
	
		all.setBounds(2, 2, 135, 45);
		buttons.add(all);
		all.addActionListener(this);
		all.setActionCommand("all-activities");
		
		none.setBounds((getWidth()-20)/2,2, 135, 45);
		buttons.add(none);
		none.addActionListener(this);
		none.setActionCommand("none-activities");
		
		int gridy = 0;
		advancedActivities.add(buttons, new GridBagConstraints(0, gridy, 1, 1, 1.0, 1.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
						2, 2, 2, 2), 0, 0));
		gridy++;		
	
		//Labels
		JCheckBox boxLabels = new JCheckBox();
		String labelLabels = "Show Topic Labels";
		boxLabels.setText(labelLabels);
		advancedActivities.add(boxLabels, new GridBagConstraints(0, gridy, 1, 1, 1.0, 1.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
						0, 0, 0, 0), 0, 0));
		boxLabels.setSelected(true);
		boxLabels.addItemListener(this);
		advancedActivitiesCheckBoxes.add(boxLabels);
		gridy++;
		
		//Lines
		JCheckBox boxLines = new JCheckBox();
		String labelLines = "Show Indicator Lines";
		boxLines.setText(labelLines);
		advancedActivities.add(boxLines, new GridBagConstraints(0, gridy, 1, 1, 1.0, 1.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
						0, 0, 0, 0), 0, 0));
		boxLines.setSelected(true);
		boxLines.addItemListener(this);
		advancedActivitiesCheckBoxes.add(boxLines);
		gridy++;
		
		//Average
		JCheckBox boxAverage = new JCheckBox();
		String labelAverage = "Show Average";
		boxAverage.setText(labelAverage);
		advancedActivities.add(boxAverage, new GridBagConstraints(0, gridy, 1, 1, 1.0, 1.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
						0, 0, 0, 0), 0, 0));
		boxAverage.setSelected(true);
		boxAverage.addItemListener(this);
		advancedActivitiesCheckBoxes.add(boxAverage);
	}

	public void actionPerformed(ActionEvent e) {
		if("all-users".equals(e.getActionCommand())){
			for (JCheckBox user : userCheckBoxes) {
				user.setSelected(true);
			}
		}else if("none-users".equals(e.getActionCommand())){
			for (JCheckBox user : userCheckBoxes) {
				user.setSelected(false);
			}
		}else if("all-months".equals(e.getActionCommand())){
			for (JCheckBox app : topicsCheckBoxes) {
				app.setSelected(true);
			}
		}else if("none-months".equals(e.getActionCommand())){
			for (JCheckBox app : topicsCheckBoxes) {
				app.setSelected(false);
			}	
		} else if("all-activities".equals(e.getActionCommand())){
			for (JCheckBox activity : advancedActivitiesCheckBoxes) {
				activity.setSelected(true);
			}
		}else if("none-activities".equals(e.getActionCommand())){
			for (JCheckBox activity : advancedActivitiesCheckBoxes) {
				activity.setSelected(false);
			}
		}
	}

	public void itemStateChanged(ItemEvent e){
		JCheckBox source = (JCheckBox) e.getItemSelectable();
		setStateUsers(source);
		setStateApps(source);
		setStateAdvancedActivities(source);
	}
	
	private void setStateUsers(JCheckBox source){
		for (JCheckBox box : userCheckBoxes) {
			if(source == box){
				int endIndex = box.getText().indexOf(".gif");
				int startIndex = box.getText().indexOf("res/") + 4;
				
				String studentName = box.getText().substring(startIndex, endIndex);
				if(!box.isSelected())
					mainForm.hideStudent(studentName);
				else
					mainForm.showStudent(studentName);
			}
		}
	}
	
	private void setStateApps(JCheckBox source){
		for (JCheckBox box : topicsCheckBoxes) {
			if(source == box){
				String topic = box.getText();
				if(!box.isSelected())
					mainForm.hideTopic(topic);
				else
					mainForm.showTopic(topic);

			}
		}		
	}

	private void setStateAdvancedActivities(JCheckBox source){
		for (JCheckBox box : advancedActivitiesCheckBoxes) {
			if(source == box){
				
				String activityName = box.getText();
				if(!box.isSelected() && activityName.equals("Show Topic Labels"))
					mainForm.hideLabels();
				else if(box.isSelected() && activityName.equals("Show Topic Labels"))
					mainForm.showLabels();

				if(!box.isSelected() && activityName.equals("Show Indicator Lines"))
					mainForm.hideIndicatorLines();
				else if(box.isSelected() && activityName.equals("Show Indicator Lines"))
					mainForm.showIndicatorLines();

				if(!box.isSelected() && activityName.equals("Show Average"))
					mainForm.hideAverage();
				else if(box.isSelected() && activityName.equals("Show Average"))
					mainForm.showAverage();
				
			}
		}	
	}
}
