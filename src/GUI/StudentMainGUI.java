package GUI;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Listeners.StudentMainListener;
import Network.Client;

@SuppressWarnings("serial")
public class StudentMainGUI extends JPanel
{
	JPanel parent;
	Client myClient;
	public JButton save_lect_button;
	public JButton leave_class_button;
	public JButton confused_button;
	public JButton start_quiz_button;
	public JTextArea ta;
	public JTextField tf;
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
		
		JPanel bottom = new JPanel();
		bottom.setLayout(new BoxLayout(bottom, BoxLayout.PAGE_AXIS));
		
		JPanel lecture_view = new JPanel();
		
		JPanel chat_view = new JPanel(new BorderLayout());
		chat_view.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		tf = new JTextField("");
    ta = new JTextArea("");
    ta.setEditable(false);
    ta.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    tf.addActionListener(mainListener);
		chat_view.add("South", tf);
		chat_view.add("Center",ta);
		chat_view.setSize(100, 100);
		
		bottom.add(lecture_view);
		bottom.add(chat_view);
		
		add(options, BorderLayout.NORTH);
		add(bottom, BorderLayout.CENTER);
		setVisible(true);
		myClient.setGUI(this);
	}

	public void switchCardView(String name)
	{
		CardLayout c = (CardLayout)(parent.getLayout());
		c.show(parent,  name);
	}
}
