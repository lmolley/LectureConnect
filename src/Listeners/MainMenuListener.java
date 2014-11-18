package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Dialogs.NewUserDialog;

import javax.swing.JFrame;

import MainMenu.MainMenuFrame;

public class MainMenuListener implements ActionListener
{
	
	MainMenuFrame mainFrame;
	public MainMenuListener(MainMenuFrame main_in)
	{
		super();
		mainFrame = main_in;
	}

	public void actionPerformed(ActionEvent e)
  {

    if(e.getSource() == mainFrame.student_button)
    {
    	System.out.println("student");
    }
    else if(e.getSource() == mainFrame.teacher_button)
    {
    	System.out.println("teacher");
    }
    else if(e.getSource() == mainFrame.new_button)
    {
    	System.out.println("new user");
    	NewUserDialog new_user_popup = new NewUserDialog(mainFrame,"New User");
    }
  }
};
