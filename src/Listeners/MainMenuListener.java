package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import GUI.*;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MainMenuListener implements ActionListener
{
	
	MainMenuFrame mainFrame;
	JPanel parent;
	public MainMenuListener(MainMenuFrame main_in,
														JPanel parent_in)
	{
		super();
		mainFrame = main_in;
		parent = parent_in;
	}

	public void actionPerformed(ActionEvent e)
  {

    if(e.getSource() == mainFrame.student_button)
    {
    	parent.add(new StudentSetUp(parent), "student");
    	mainFrame.switchCardView("student");
    	
    }
    else if(e.getSource() == mainFrame.teacher_button)
    {
    	parent.add(new TeacherSetUp(parent), "teacher");
    	mainFrame.switchCardView("teacher");
    	
    }

  }
};
