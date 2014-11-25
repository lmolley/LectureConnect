package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import GUI.StudentSetUp;
import GUI.TeacherMainGUI;
import GUI.WaitingForStudents;
import Network.Server;

public class WaitingListener implements ActionListener
{
	WaitingForStudents mainFrame;
	JPanel parent;
	Server server;
	
	public WaitingListener(
																JPanel parent_in, 
																WaitingForStudents main_in,
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
		if(e.getSource() == mainFrame.start)
		{
			parent.add(new TeacherMainGUI(parent, server), "teacherMain");
			mainFrame.switchCardView("teacherMain");
		}
  }

}
