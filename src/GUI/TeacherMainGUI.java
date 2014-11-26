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
import javax.swing.*;

import Listeners.TeacherMainListener;
import Network.Server;

public class TeacherMainGUI extends JPanel
{
	JPanel parent;
	public JButton load_quiz_button;
	public JButton start_quiz_button;
	public JButton load_lect_button;
	public JButton end_class_button;
	TeacherMainListener mainListener;
	Server server;
	
	public TeacherMainGUI(JPanel parent_in, Server server_in)
	{
		super();
		parent = parent_in;
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20) );
		setBackground(Color.WHITE);
		server = server_in;
		
		mainListener = new TeacherMainListener(parent, this, server);
		
		JPanel options = new JPanel();
		options.setLayout(new GridLayout(1, 4));
		options.setBackground(Color.WHITE);
		
		try
		{
			BufferedImage lect_img = ImageIO.read(new File("src/images/add_lect.png"));
			load_lect_button = new JButton(new ImageIcon(lect_img));
			load_lect_button.setBorder(null);
			load_lect_button.setToolTipText("Load Lecture");
			load_lect_button.addActionListener(mainListener);
			
			BufferedImage quiz_img = ImageIO.read(new File("src/images/add_quiz.png"));
			load_quiz_button = new JButton(new ImageIcon(quiz_img));
			load_quiz_button.setBorder(null);
			load_quiz_button.setToolTipText("Load Quiz");
			load_quiz_button.addActionListener(mainListener);
			
			BufferedImage start_img = ImageIO.read(new File("src/images/start_quiz.png"));
			start_quiz_button = new JButton(new ImageIcon(start_img));
			start_quiz_button.setBorder(null);
			start_quiz_button.setToolTipText("Start Quiz");
			start_quiz_button.addActionListener(mainListener);
			
			BufferedImage leave_img = ImageIO.read(new File("src/images/leave.png"));
			end_class_button = new JButton(new ImageIcon(leave_img));
			end_class_button.setBorder(null);
			end_class_button.setToolTipText("End Class");
			end_class_button.addActionListener(mainListener);

			
			options.add(load_lect_button);
			options.add(load_quiz_button);
			options.add(start_quiz_button);
			options.add(end_class_button);
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
