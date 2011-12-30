/**
 * This file is licensed under the University of Illinois/NCSA Open Source License. See LICENSE.TXT for details.
 */
package pres;
/**
 * @author Roshanak Zilouchian
 */
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class RunGradeVis {

	public void run(){
		MainWindow mainForm = new MainWindow(); 
		mainForm.setExtendedState(mainForm.getExtendedState());//| Frame.MAXIMIZED_BOTH );
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension scrnsize = toolkit.getScreenSize();
		mainForm.setSize(scrnsize.width, scrnsize.height);

        mainForm.setVisible(true);
		mainForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args){
		RunGradeVis runner = new RunGradeVis();
		runner.run();
		
	}
	
}
