package Listeners;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import GUI.TeacherMainGUI;
import GUI.WaitingForStudents;
import Network.Server;

public class TeacherMainListener implements ActionListener
{
	TeacherMainGUI mainFrame;
	JPanel parent;
	Server server;
	
	public TeacherMainListener(
																JPanel parent_in, 
																TeacherMainGUI main_in,
																Server server_in
																)
	{
		super();
		mainFrame = main_in;
		parent = parent_in;
		server = server_in;
	}
	
	
	public void actionPerformed(ActionEvent e)
  {
		if(e.getSource() == mainFrame.load_lect_button)
		{
			
		}
		else if(e.getSource() == mainFrame.load_quiz_button)
		{
			server.loadQuizToThreads(null);
		}
		else if(e.getSource() == mainFrame.start_quiz_button)
		{
			server.pushExistingQuizToThreads();
		}
		else if(e.getSource() == mainFrame.end_class_button)
		{
			mainFrame.switchCardView("main menu");
		}
  }
}
