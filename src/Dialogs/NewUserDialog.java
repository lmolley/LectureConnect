package Dialogs;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.*;

import javax.swing.*;

import GUI.MainMenuFrame;

public class NewUserDialog extends JDialog
{
	String username;
	String password;
	Integer ID;
	boolean cancelled;
	boolean student;
	
  public NewUserDialog(final JPanel mainFrame, String title)
  {
    //call JDialog constructor
    super();
    setLayout(new GridLayout(6,1));
    setResizable(true); 
    
    JButton okay_button;
    JButton cancel_button;
    final JTextField name_enter;
    final JPasswordField password_enter;
    final JPasswordField password_reenter;
    final JTextField id_enter;
    final JCheckBox student_chooser;
    final JCheckBox teacher_chooser;
    
    JPanel row1 = new JPanel();
    row1.add(new JLabel("Username: "));
    name_enter = new JTextField(20);
    row1.add(name_enter);
    
    JPanel idrow = new JPanel();
    idrow.add(new JLabel("UM ID #:"));
    id_enter = new JTextField(20);
    idrow.add(id_enter);
    
    JPanel row2 = new JPanel();
    row2.add(new JLabel("Password: "));
    password_enter = new JPasswordField(20);
    row2.add(password_enter);
    
    JPanel row3 = new JPanel();
    row3.add(new JLabel("Re-enter password: "));
    password_reenter = new JPasswordField(20);
    row3.add(password_reenter);
    
    JPanel type_select = new JPanel();
    type_select.add(new JLabel("Student"));
    student_chooser = new JCheckBox();
    student_chooser.setSelected(true);
    student = true;
    type_select.add(student_chooser);
    type_select.add(new JLabel("Teacher"));
    teacher_chooser = new JCheckBox();
    type_select.add(teacher_chooser);
    
    student_chooser.addActionListener(new ActionListener()
    {
    							public void actionPerformed(ActionEvent e)
    							{
    								teacher_chooser.setSelected(false);
    								student = true;
    							}
    });
    
    teacher_chooser.addActionListener(new ActionListener()
    {
    							public void actionPerformed(ActionEvent e)
    							{
    								student_chooser.setSelected(false);
    								student = false;
    							}
    }); 
    
    JPanel row4 = new JPanel();
    okay_button = new JButton("Finish");
    okay_button.addActionListener(new ActionListener()
    {
                  public void actionPerformed(ActionEvent e)
                  {
                    username = name_enter.getText();
                    char[] password_array = password_enter.getPassword();
                    password = String.copyValueOf(password_array);
                    ID = Integer.parseInt(id_enter.getText());
                    cancelled = false;
                    setVisible(false);
                  }
                  });
    
    cancel_button = new JButton("Cancel");
    cancel_button.addActionListener(new ActionListener()
    {
    							public void actionPerformed(ActionEvent e)
    							{
    								cancelled = true;
    								setVisible(false);
    							}
    });
    
    row4.add(cancel_button);
    row4.add(okay_button);
    
    add(row1);
    add(idrow);
    add(row2);
    add(row3);
    add(type_select);
    add(row4);
    
    pack();
    setVisible(true);
  }
    
   public String getName() 
   {
  	 return username;
   }
   public String getPassword()
   {
  	 return password;
   }
   public Integer getID()
   {
  	 return ID;
   }
   public boolean didCancel()
   {
  	 return cancelled;
   }
   public boolean isStudent()
   {
  	 return student;
   }
};
