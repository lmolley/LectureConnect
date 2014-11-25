package GUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Listeners.StudentMainListener;
import Network.Client;

public class StudentMainGUI extends JPanel
{
	JPanel parent;
	Client myClient;
	public JButton save_lect_button;
	public JButton leave_class_button;
	public JButton confused_button;
	public JButton start_quiz_button;
	StudentMainListener mainListener;
	
	public StudentMainGUI(JPanel parent_in, Client client_in)
	{
		super();
		myClient = client_in;
		parent = parent_in;
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20) );
		setBackground(Color.WHITE);
		
		mainListener = new StudentMainListener(parent, this, myClient);
		
		JPanel options = new JPanel();
		options.setLayout(new GridLayout(1, 4));
		options.setBackground(Color.WHITE);
		
		try
		{
			BufferedImage lect_img = ImageIO.read(new File("src/images/lecture.png"));
			save_lect_button = new JButton(new ImageIcon(lect_img));
			save_lect_button.setBorder(null);
			save_lect_button.setToolTipText("Save Lecture");
			save_lect_button.addActionListener(mainListener);
			
			BufferedImage quiz_img = ImageIO.read(new File("src/images/quiz.png"));
			start_quiz_button = new JButton(new ImageIcon(quiz_img));
			start_quiz_button.setBorder(null);
			start_quiz_button.setToolTipText("Start Quiz");
			start_quiz_button.addActionListener(mainListener);
			
			BufferedImage confuse_img = ImageIO.read(new File("src/images/confused.png"));
			confused_button = new JButton(new ImageIcon(confuse_img));
			confused_button.setBorder(null);
			confused_button.setToolTipText("Confused? Alert Teacher");
			confused_button.addActionListener(mainListener);
			
			BufferedImage leave_img = ImageIO.read(new File("src/images/leave.png"));
			leave_class_button = new JButton(new ImageIcon(leave_img));
			leave_class_button.setBorder(null);
			leave_class_button.setToolTipText("Leave Lecture");
			leave_class_button.addActionListener(mainListener);
			
			options.add(save_lect_button);
			options.add(start_quiz_button);
			options.add(confused_button);
			options.add(leave_class_button);
		}
		catch(IOException ioe)
		{
			System.out.println("Couldn't get button images");
		}
		
		JPanel lecture_view = new JPanel();
		
		add(options, BorderLayout.NORTH);
		add(lecture_view, BorderLayout.CENTER);
		setVisible(true);
	}

	public void switchCardView(String name)
	{
		CardLayout c = (CardLayout)(parent.getLayout());
		c.show(parent,  name);
	}
}
