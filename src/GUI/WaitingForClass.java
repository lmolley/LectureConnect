package GUI;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Network.Client;

public class WaitingForClass extends JPanel
{
	JPanel parent;
	Client myClient;
	public JButton start;
	
	public WaitingForClass(JPanel parent_in, Client client_in)
	{
		super();
		myClient = client_in;
		parent = parent_in;
		add(new JLabel("Waiting for class to begin..."));
		start = new JButton("Enter class");
		add(start);
		start.addActionListener(new ActionListener(){

			@Override
      public void actionPerformed(ActionEvent e)
      {
				parent.add(new StudentMainGUI(parent, myClient), "studentMain");
				switchCardView("studentMain");      
      }
			
		});
		setVisible(true);
	}
	
	public void switchCardView(String name)
	{
		CardLayout c = (CardLayout)(parent.getLayout());
		c.show(parent,  name);
	}
}
