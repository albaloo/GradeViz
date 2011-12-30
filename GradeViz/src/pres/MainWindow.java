/**
 * This file is licensed under the University of Illinois/NCSA Open Source License. See LICENSE.TXT for details.
 */
package pres;
/**
 * @author Roshanak Zilouchian
 */
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import data.Assignment;
import data.FileReader;
import data.StudentRecord;



@SuppressWarnings("serial")
public class MainWindow extends JFrame {
	
	private JMenuBar menuBar;

	private JMenu menuFile;

	private JMenuItem menuItem1;

	private JMenuItem menuItem2;
		
	private JMenu menuWindow;
		
	private JMenuItem menuItemFiltering;
	
	private ScatterPlotView scatterPlotView;
	
	private JFrame scatterPlotFrame;
		
	private FilterWindow filterWindow;
	
	private FileReader fileReader = new FileReader();
	
	private ArrayList<String> hiddenStudents = new ArrayList<String>();
	
	private ArrayList<String> hiddenTopics = new ArrayList<String>();
	
	private ArrayList<StudentRecord> studentRecords;
	
	private ArrayList<Assignment> assignments;
	
	private StudentInfoPanel studentInfoPanel;
	
	private SearchBoxPanel searchPanel;
	
	public MainWindow() {
		initComponents();
	}

	public FileReader getFileReader(){
		return fileReader;
	}
	
	private void menuItem2ActionPerformed(ActionEvent e) {
		this.dispose();
	}

	private void menuItemLoadGrades(ActionEvent e) {	
		filterWindow.initTabs();
		scatterPlotView.paintScatterPlot();
		repaint();
	}

	public ArrayList<StudentRecord> getStudentRecords(){
		return studentRecords;
	}
	public ArrayList<Assignment> getAssignment(){
		return assignments;
	}
	public int getAssignmentsWidth(){
		int result = 0;
		for (Assignment assign: assignments)
			result += assign.getWidth();
		
		return result;
	}
	public Vector<String> getStudentFirstNames(){
		Vector<String> result = new Vector<String>();
		for(int i = 0; i < studentRecords.size(); i++){
			result.add(studentRecords.get(i).getFirstName());
		}
		result.add("all");
		return result;
	}

	public Vector<String> getStudentLastNames(){
		Vector<String> result = new Vector<String>();
		for(int i = 0; i < studentRecords.size(); i++){
			result.add(studentRecords.get(i).getLastName());
		}
		result.add("all");
		return result;
	}
	
	public Vector<String> getStudentNetIds(){
		Vector<String> result = new Vector<String>();
		for(int i = 0; i < studentRecords.size(); i++){
			result.add(studentRecords.get(i).getUserID());
		}
		result.add("all");
		return result;
	}
	public void initSeachBox(){
		searchPanel.initSearchBoxes();
	}

	private void initComponents() {
		studentRecords = fileReader.parseCVSFile("res/exportedcourse.csv");
		assignments = fileReader.getAssignment();
		searchPanel = new SearchBoxPanel(this);
		studentInfoPanel = new StudentInfoPanel();
		menuBar = new JMenuBar();
		menuFile = new JMenu();
		menuItem1 = new JMenuItem();
		menuItem2 = new JMenuItem();
		menuWindow = new JMenu();
		menuItemFiltering = new JMenuItem();
	
		scatterPlotView = new ScatterPlotView(this);
		initSeachBox();
		scatterPlotFrame = new JFrame();
		
		
		setTitle("GradeVis");
		Container contentPane = getContentPane();
		contentPane.setLayout(new GridBagLayout());

		filterWindow = new FilterWindow(this);
		filterWindow.setSize(300, 500);
		filterWindow.setVisible(false);
		
				// ======== menuBar1 ========
		{

			// ======== menu1 ========
			{
				menuFile.setText("File");
				menuFile.setMnemonic(KeyEvent.VK_F);
				// ---- menuItem1 ----
				menuItem1.setText("Load Grades");
				menuItem1.setMnemonic(KeyEvent.VK_L);
				menuItem1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						menuItemLoadGrades(e);
					}
				});
				menuFile.add(menuItem1);

				// ---- menuItem2 ----
				menuItem2.setText("Exit");
				menuItem2.setMnemonic(KeyEvent.VK_X);
				menuItem2.setEnabled(false);
				menuItem2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						menuItem2ActionPerformed(e);
					}
				});
				menuFile.add(menuItem2);
			}
			menuBar.add(menuFile);

			// ======== menu3 ========
			{
				menuWindow.setText("Filters");
				menuWindow.setMnemonic(KeyEvent.VK_T);

				// ---- menuItem3 ----
				menuItemFiltering.setText("Open Filters Window");
				menuItemFiltering.setMnemonic(KeyEvent.VK_O);
				menuItemFiltering.setEnabled(true);
				menuItemFiltering.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						handleFilteringWindow();
					}
				});
				menuWindow.add(menuItemFiltering);
					 
				filterWindow.addWindowListener(new WindowListener(){
					public void windowClosing(WindowEvent e) {
					    handleFilteringWindow();
					}
					public void windowClosed(WindowEvent e) {
				    }
					public void windowOpened(WindowEvent e){	
					}
					public void windowIconified(WindowEvent e) {   
				    }
				    public void windowDeiconified(WindowEvent e) {   
				    }
				    public void windowActivated(WindowEvent e) {
				    }
				    public void windowDeactivated(WindowEvent e) {  
				    }
				});	
			
			}
			menuBar.add(menuWindow);
		
		}
		setJMenuBar(menuBar);
		
		//position: gridx, gridy - numberofCells:gridwidth, gridheight - resizewaigth: waightx, y 	
		this.getContentPane().add(scatterPlotView, new GridBagConstraints(0, 0, 1, 4, 1.0, 1.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
						0, 0, 0, 0), 0, 0));
		
		this.getContentPane().add(studentInfoPanel, new GridBagConstraints(1, 0, 1, 3, 0.2, 0.8,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
						0, 0, 0, 0), 0, 0));
		
		this.getContentPane().add(searchPanel, new GridBagConstraints(1, 3, 1, 1, 0.2, 0.2,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
						0, 0, 0, 0), 0, 0));
	
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
	    Dimension dimension = toolkit.getScreenSize();
	    
		final int initialWidth2 = new Double(dimension.getWidth()/2).intValue();
		final int initialHeight2 = 100;
		this.addComponentListener(new ComponentAdapter() {
		  public void componentResized(ComponentEvent event) {
			  setSize(
		      Math.max(initialWidth2, getWidth()),
		      Math.max(initialHeight2, getHeight()));
			  
		  }
		});

		pack();
		setLocationRelativeTo(getOwner());
	}
	

	public StudentInfoPanel getStudentInfoPanel(){
		return studentInfoPanel;
	}

	private void handleFilteringWindow(){
		if (menuItemFiltering.getText().equals("Close Filters Window")) {
			menuItemFiltering.setText("Open Filters Window");
			filterWindow.setVisible(false);
			repaint();
		} else {
			menuItemFiltering.setText("Close Filters Window");
			filterWindow.setVisible(true);
			repaint();
		}
	}
	
	public ScatterPlotView getScatterPlot() {
		return scatterPlotView;
	}

	
	public JFrame getInteractiveMapFrame() {
		return scatterPlotFrame;
	}

	public void setInteractiveMapFrame(JFrame interactiveMapFrame) {
		this.scatterPlotFrame = interactiveMapFrame;
	}

		public FilterWindow getFilterWindow() {
		return filterWindow;
	}

	public void setFilterWindow(FilterWindow filterWindow) {
		this.filterWindow = filterWindow;
	}

	public void hideStudent(String studentName) {
		hiddenStudents.add(studentName);
		scatterPlotView.hideStudent(studentName);
	}
	
	public void showStudent(String studentName) {
		hiddenStudents.remove(studentName);
		scatterPlotView.showStudent(studentName);	
	}
	
	public void hideTopic(String topic) {
		hiddenTopics.add(topic);
		scatterPlotView.hideTopic(topic);	
	}
	
	public void showTopic(String topic) {
		hiddenTopics.remove(topic);
		scatterPlotView.showTopic(topic);
	}

	public boolean isStudentHidden(String userID){
		for (String student : hiddenStudents) {
			if(student.equals(userID))
				return true;
		}		
		return false;
	}
	
	public boolean isTopicHidden(String topicName){
		for (String topic : hiddenTopics) {
			if(topicName.equals(topic))
				return true;
		}		
		return false;
	}

	public StudentRecord getStudentByUserID(String userID) {
		for(StudentRecord student: studentRecords){
			if(student.getUserID().equals(userID))
				return student;
		}
		return null;
	}
	
	public void hideLabels() {
		scatterPlotView.hideLabels();
		repaint();
	}

	public void showLabels() {
		scatterPlotView.showLabels();
		repaint();
	}

	public void hideIndicatorLines() {
		scatterPlotView.hideIndicatorLines();
		repaint();
	}

	public void showIndicatorLines() {
		scatterPlotView.showIndicatorLines();
		repaint();
	}

	public void hideAverage() {
		scatterPlotView.hideAverage();
		repaint();
	}
	
	public void showAverage() {
		scatterPlotView.showAverage();
		repaint();
	}

	public int getAverageGradeInAssignment(String name) {
		for (Assignment assignment:assignments) {
			if(assignment.getTopic().equals(name)){
				return assignment.getAverage();
			}
		}
		
		return 0;
	}

}
