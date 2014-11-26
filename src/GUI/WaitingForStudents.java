package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Listeners.WaitingListener;
import Network.Server;

public class WaitingForStudents extends JPanel
{
	public String IP;
	public JPanel parent;
	public JButton start;
	public WaitingListener waitListener;
	public Server server;
	public JLabel num_connected;
	public WaitingForStudents(JPanel parent_in, String IP_in, Server server_in)
	{
		super();
		parent = parent_in;
		IP = IP_in;
		server = server_in;
		
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50) );
		waitListener = new WaitingListener(parent, this, server);
		
		JPanel center = new JPanel();
		center.setLayout(new GridLayout(2, 1));
		 
  //Set up bolded title at the top
    JLabel IP_view = new JLabel("IP: " + IP);
    Font fontIP = IP_view.getFont();
    // same font but bold
    Font boldFontIP = new Font(fontIP.getFontName(), Font.BOLD, 20);
    IP_view.setFont(boldFontIP);
    center.add(IP_view);
    
    num_connected = new JLabel("Number of Students Connected " + server.numConnected());
    center.add(num_connected);
    
    JPanel bottom = new JPanel();
		bottom.add(new JLabel("Waiting for Students..."));
		start = new JButton("Begin class");
		start.addActionListener(waitListener);
		bottom.add(start);
		
		add(center, BorderLayout.CENTER);
		add(bottom, BorderLayout.SOUTH);
		
	}
	
	
	public void switchCardView(String name)
	{
		CardLayout c = (CardLayout)(parent.getLayout());
		c.show(parent,  name);
	}
	
	public void updateNumConnected()
	{
		num_connected.setText("Number of Students Connected " + server.numConnected());
		System.out.println(server.numConnected());
	}
	
}
