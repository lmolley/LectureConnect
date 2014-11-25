package GUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import Database.databaseInit;
import Listeners.StudentSetUpListener;

public class StudentSetUp extends JPanel
{
	JPanel parent;
	
	public String name;
	public String uniqname;
	public Integer ID;
	public String host;
  public JTextField name_enter;
  public JTextField id_enter;
  public JTextField uniqname_enter;
  public JTextField ip_enter;
  
	public JButton okay_button;
  public JButton cancel_button;
  
  StudentSetUpListener studentListener;
	
	public StudentSetUp(JPanel parent_in)
	{
		super();
		parent = parent_in;
		studentListener = new StudentSetUpListener(parent, this);
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50) );
		
   
    JPanel center = new JPanel();
    center.setLayout(new GridLayout(4, 2));
    
    center.add(new JLabel("Name:"));
    name_enter = new JTextField(20);
    center.add(name_enter);
    
    center.add(new JLabel("Uniqname:"));
    uniqname_enter = new JTextField(20);
    center.add(uniqname_enter);
    
    center.add(new JLabel("ID:"));
    id_enter = new JTextField(20);
    center.add(id_enter);
    
    center.add(new JLabel("Lecture Code:"));
    ip_enter = new JTextField(20);
    center.add(ip_enter);
    
    add(center, BorderLayout.CENTER);
    
    JPanel bottom = new JPanel();
    bottom.setLayout(new FlowLayout());
    
    /*try
    {
	    BufferedImage canc_img = ImageIO.read(new File("src/images/canc.png"));
			cancel_button = new JButton(new ImageIcon(canc_img));
			cancel_button.setBorder(null);
	    cancel_button.addActionListener(studentListener);  
	    bottom.add(cancel_button);
	    
	    bottom.add(Box.createRigidArea(new Dimension(20,0)));
	    
	    BufferedImage okay_img = ImageIO.read(new File("src/images/okay.png"));
			okay_button = new JButton(new ImageIcon(okay_img));
			okay_button.setBorder(null);
	    okay_button.addActionListener(studentListener);    
	    bottom.add(okay_button);
    }
		catch(IOException ioe)
		{
			System.out.println("Couldn't get button images");
		}*/
    
    cancel_button = new JButton("Cancel");
    cancel_button.addActionListener(studentListener);
    bottom.add(cancel_button);
    okay_button = new JButton("Okay");
    okay_button.addActionListener(studentListener);
    bottom.add(okay_button);
   
    add(bottom, BorderLayout.SOUTH);
    setVisible(true);
		
	}
	
	public void switchCardView(String name)
	{
		CardLayout c = (CardLayout)(parent.getLayout());
		c.show(parent,  name);
	}
	
	public boolean anyEmpty()
	{
		if(name_enter.getText().equals("")) return true;
		if(uniqname_enter.getText().equals("")) return true;
		if(id_enter.getText().equals("")) return true;
		return false;
	}
	
	public void setTextFieldValues()
	{
		name = name_enter.getText();
    uniqname = uniqname_enter.getText(); 
    ID = Integer.parseInt(id_enter.getText());
    host = ip_enter.getText();
	}
	
	public void clearTextFieldValues()
	{
		name_enter.setText("");
    id_enter.setText("");
    uniqname_enter.setText("");
    ip_enter.setText("");
	}
	
	public void showErrorMessage(String message)
	{
		JOptionPane.showMessageDialog(this,
        message,
        "Error",
        JOptionPane.ERROR_MESSAGE);
	}
}
