package GUI;

import javax.swing.*;

import Database.databaseInit;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import Exceptions.*;
import Listeners.TeacherSetUpListener;
import Network.Server;

public class TeacherSetUp extends JPanel
{
	public String teacher_name;
	public String lecture_name;
	
	public JButton okay_button;
  public JButton cancel_button;
  
	public JTextField teacher_in;
	public JTextField lecture_in;
	
	TeacherSetUpListener teacherListener;
  
  JPanel parent;
	public TeacherSetUp(JPanel parent_in)
	{
		super();
		parent = parent_in;
		teacherListener = new TeacherSetUpListener(parent, this);
		setLayout(new BorderLayout());
		   
    //Set up GUI Layout
    //Input part of screen
    JPanel center = new JPanel();
    center.setLayout(new GridLayout(2, 2));
    
    center.add(new JLabel("Teacher Name:"));
    teacher_in = new JTextField(20);
    center.add(teacher_in);
    
    center.add(new JLabel("Lecture Name:"));
    lecture_in = new JTextField(20);
    center.add(lecture_in);
    
    add(center, BorderLayout.CENTER);
    
    //Buttons to proceed-- to be located at the bottom
    JPanel bottom = new JPanel();
    setLayout(new FlowLayout());
    
    okay_button = new JButton("Okay");
    okay_button.addActionListener(teacherListener);
    
    cancel_button = new JButton("Cancel");
    cancel_button.addActionListener(teacherListener);
    
    bottom.add(cancel_button);
    bottom.add(okay_button);
    add(bottom, BorderLayout.SOUTH);
		
		setVisible(true);		
	}
	
	public boolean anyEmpty()
	{
		if(teacher_name.equals("")) return true;
		if(lecture_name.equals("")) return true;
		return false;
	}
	
	public void showErrorMessage(String message)
	{
		JOptionPane.showMessageDialog(this,
        message,
        "Error",
        JOptionPane.ERROR_MESSAGE);
	}
	
	public void switchCardView(String name)
	{
		CardLayout c = (CardLayout)(parent.getLayout());
		c.show(parent,  name);
	}
	
	public void setTextFieldValues()
	{
		teacher_name = teacher_in.getText();
    lecture_name = lecture_in.getText();
	}
	
	public void clearTextFieldValues()
	{
		teacher_in.setText("");
    lecture_in.setText("");
	}
}
