package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import GUI.StudentMainGUI;
import GUI.TeacherMainGUI;
import Network.Client;
import Network.Server;
import Quiz.quizInit;

public class StudentMainListener implements ActionListener
{
	StudentMainGUI mainFrame;
	JPanel parent;
	Client myClient;
	
	public StudentMainListener(
																JPanel parent_in, 
																StudentMainGUI main_in,
																Client client_in
																)
	{
		super();
		mainFrame = main_in;
		parent = parent_in;
		myClient = client_in;
	}
	
	
	public void actionPerformed(ActionEvent e)
  {
		if(e.getSource() == mainFrame.save_lect_button)
		{
			
		}
		else if(e.getSource() == mainFrame.start_quiz_button)
		{
			String quiz = myClient.getQuizString();
			quizInit myQuiz = new quizInit(null, quiz);
		}
		else if(e.getSource() == mainFrame.confused_button)
		{
			
		}
		else if(e.getSource() == mainFrame.leave_class_button)
		{
			mainFrame.switchCardView("main menu");
		}
  }
}

