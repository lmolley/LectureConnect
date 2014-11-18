package Dialogs;
import java.awt.GridLayout;
import java.awt.event.*;

import javax.swing.*;

public class NewUserDialog extends JDialog
{
	String uniqname;
	char[] password;
	
  public NewUserDialog(final JFrame mainFrame, String title)
  {
    //call JDialog constructor
    super(mainFrame, title, true);
    setLayout(new GridLayout(4,1));
    setResizable(true); 
    
    JButton okay_button;
    JButton cancel_button;
    final JTextField name_enter;
    final JPasswordField password_enter;
    final JPasswordField password_reenter;
    
    JPanel row1 = new JPanel();
    row1.add(new JLabel("Uniquename: "));
    name_enter = new JTextField(20);
    row1.add(name_enter);
    
    JPanel row2 = new JPanel();
    row2.add(new JLabel("Password: "));
    password_enter = new JPasswordField(20);
    row2.add(password_enter);
    
    JPanel row3 = new JPanel();
    row3.add(new JLabel("Re-enter password: "));
    password_reenter = new JPasswordField(20);
    row3.add(password_reenter);
    
    JPanel row4 = new JPanel();
    okay_button = new JButton("Finish");
    okay_button.addActionListener(new ActionListener()
    {
                  public void actionPerformed(ActionEvent e)
                  {
                    uniqname = name_enter.getText();
                    password = password_enter.getPassword(); 
                    setVisible(false);
                  }
                  });
    
    cancel_button = new JButton("Cancel");
    cancel_button.addActionListener(new ActionListener()
    {
    							public void actionPerformed(ActionEvent e)
    							{
    								setVisible(false);
    							}
    });
    
    row4.add(cancel_button);
    row4.add(okay_button);
    
    add(row1);
    add(row2);
    add(row3);
    add(row4);
    
    pack();
    setVisible(true);
  }
    
   public String getName() 
   {
  	 return uniqname;
   }
   public char[] getPassword()
   {
  	 return password;
   }
};
