package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import Database.databaseInit;
import Exceptions.EmptyTextFieldException;
import GUI.*;
import Network.Client;

import javax.swing.JButton;
import javax.swing.JPanel;

public class StudentSetUpListener implements ActionListener
{
	StudentSetUp mainFrame;
	JPanel parent;
	
	public StudentSetUpListener(
																JPanel parent_in, 
																StudentSetUp main_in
																)
	{
		super();
		mainFrame = main_in;
		parent = parent_in;
	}

	public void actionPerformed(ActionEvent e)
  {
		if(e.getSource() == mainFrame.okay_button)
		{
			try
			{
				if(mainFrame.anyEmpty()) throw new EmptyTextFieldException("Fields cannot be empty!");
				mainFrame.setTextFieldValues();
				Client myClient = new Client(mainFrame.host, mainFrame.name, mainFrame.uniqname, mainFrame.ID);
				
				parent.add(new WaitingForClass(parent, myClient), "waitingClass");
				mainFrame.clearTextFieldValues();
				mainFrame.switchCardView("waitingClass");
				
				/*parent.add(new StudentMainGUI(parent), "studentMain");
				mainFrame.switchCardView("studentMain");*/
			}
			catch(EmptyTextFieldException err)
			{
				mainFrame.showErrorMessage(err.getMessage());
			}
			catch(NumberFormatException nfe)
			{
				mainFrame.showErrorMessage(nfe.getMessage() + " must be an integer value.");
			}
		}
		
		else if(e.getSource() == mainFrame.cancel_button)
		{
			mainFrame.clearTextFieldValues();
			mainFrame.setVisible(false);
		}
  }
};
