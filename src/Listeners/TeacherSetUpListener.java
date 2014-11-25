package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import Database.databaseInit;
import Exceptions.EmptyTextFieldException;
import GUI.*;
import Network.Server;
import Network.acceptThreads;

import javax.swing.JButton;
import javax.swing.JPanel;


public class TeacherSetUpListener implements ActionListener
{
	TeacherSetUp mainFrame;
	JPanel parent;
	
	public TeacherSetUpListener(
																JPanel parent_in, 
																TeacherSetUp main_in
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

        mainFrame.setTextFieldValues();
        if(mainFrame.anyEmpty()) throw new EmptyTextFieldException("Fields cannot be empty!");
        
        new databaseInit(mainFrame.lecture_name);
       
        mainFrame.clearTextFieldValues();

        Server server = new Server();
        
        WaitingForStudents waitingScreen = new WaitingForStudents(parent, server.getIP(), server);
        parent.add(waitingScreen, "waitingStudents");
        mainFrame.switchCardView("waitingStudents"); 
        
       
        Thread acceptor = new acceptThreads(server, waitingScreen);
        acceptor.start(); 
    	}
			
    	catch(EmptyTextFieldException | NumberFormatException exc)
    	{
    		mainFrame.showErrorMessage(exc.getMessage());
    	}
  		catch (SQLException sqlEx)
  		{
  			mainFrame.showErrorMessage("Please choose a different lecture name.");
  		}
  		catch (ClassNotFoundException cnfEx)
  		{
  			System.out.println("Got a ClassNotFoundException!");
  		} 
		}
		else if(e.getSource() == mainFrame.cancel_button)
		{
			mainFrame.clearTextFieldValues();
			mainFrame.setVisible(false);
		}
  }

}
