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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import ch.rakudave.suggest.JSuggestField;

@SuppressWarnings("serial")
public class SearchBoxPanel extends JPanel implements ActionListener{

	private final static int PANEL_INSETS = 20;
		
	private JLabel firstName;
	
	private JLabel lastName;
	
	private JLabel netid;
	
	private MainWindow mainWindow;
	
	private JSuggestField searchFirst;

	private JSuggestField searchLast;

	private JSuggestField searchNetID;

	
	public SearchBoxPanel(MainWindow mainWindow) {
		this.setLayout(null);//new GridBagLayout());
		this.mainWindow = mainWindow; 
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
	
	public void initSearchBoxes(){
		//position: gridx, gridy - numberofCells:gridwidth, gridheight - resizewaigth: waightx, y 
		//this.getContentPane().add(scatterPlotView, new GridBagConstraints(0, 0, 3, 1, 1.0, 1.0,
		//		GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
		//				0, 0, 0, 0), 0, 0));
		
		firstName = new JLabel();
		String firstNameLabel = "<html> <table cellpadding=0>" +
		"<tr><td>" + 
		"</td><td width=" + 3 + "><td>" + "First Name" +
		"</td></tr></table></html>";
		//<td width=" + 3 + "><td>" + currentStudent.getName() + 
		//"</td>
		int y = PANEL_INSETS;
		firstName.setText(firstNameLabel);
		firstName.setBounds(PANEL_INSETS, y, 4*PANEL_INSETS, 2*PANEL_INSETS);
		this.add(firstName);
		//position: gridx, gridy - numberofCells:gridwidth, gridheight - resizewaigth: waightx, y 
		//this.add(firstName, new GridBagConstraints(0, 0, 2, 1, 0.3, 1.0,
		//		GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
		//				0, 0, 0, 0), 0, 0));
		
		searchFirst = new JSuggestField(mainWindow, mainWindow.getStudentFirstNames());
		searchFirst.setText("...");
		searchFirst.setBounds((3+3)*PANEL_INSETS, y+PANEL_INSETS/2, 4*PANEL_INSETS, PANEL_INSETS);
		this.add(searchFirst);
		searchFirst.addActionListener(this);
		//this.add(searchFirst, new GridBagConstraints(2, 0, 2, 1, 0.7, 1.0,
		//		GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
		//				0, 0, 0, 0), 0, 0));
		
		lastName = new JLabel();
		String lastNameLabel = "<html> <table cellpadding=0>" +
		"<tr><td>" + 
		"</td><td width=" + 3 + "><td>" + "Last Name" +
		"</td></tr></table></html>";
		y += 2*PANEL_INSETS;
		lastName.setText(lastNameLabel);
		lastName.setBounds(PANEL_INSETS, y, 4*PANEL_INSETS, 2*PANEL_INSETS);
		this.add(lastName);
	
		searchLast = new JSuggestField(mainWindow, mainWindow.getStudentLastNames());
		searchLast.setText("...");
		searchLast.setBounds((3+3)*PANEL_INSETS, y+PANEL_INSETS/2, 4*PANEL_INSETS, PANEL_INSETS);
		this.add(searchLast);
		searchLast.addActionListener(this);
		
		netid = new JLabel();
		String netidLabel = "<html> <table cellpadding=0>" +
		"<tr><td>" + 
		"</td><td width=" + 3 + "><td>" + "NetID" +
		"</td></tr></table></html>";
		y += 2*PANEL_INSETS;
		netid.setText(netidLabel);
		netid.setBounds(PANEL_INSETS, y, 4*PANEL_INSETS, 2*PANEL_INSETS);
		this.add(netid);
				
		searchNetID = new JSuggestField(mainWindow, mainWindow.getStudentNetIds());
		searchNetID.setText("...");
		searchNetID.setBounds((3+3)*PANEL_INSETS, y+PANEL_INSETS/2, 4*PANEL_INSETS, PANEL_INSETS);
		this.add(searchNetID);
		searchNetID.addActionListener(this);
	}

	String prevNetID;
	public void actionPerformed(ActionEvent evt) {
        String netID = searchNetID.getText();
        //String first = searchFirst.getText();
        //String last = searchLast.getText();
        
        if(!netID.equals("all") && netID != null && netID != "")
        	mainWindow.getScatterPlot().getScatterPlotGeneral().showSelectedStudent(netID);
        else{
        	System.out.println("here");
        	mainWindow.getScatterPlot().getScatterPlotGeneral().showAllStudents(prevNetID);
        }
        prevNetID = netID;
	}
}
