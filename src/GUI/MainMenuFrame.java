package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;

import Listeners.MainMenuListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainMenuFrame extends JFrame
{
	
	public JButton student_button;
	public JButton teacher_button;
	public JPanel parent = new JPanel(new CardLayout());
	public JPanel mainFrame;
	MainMenuListener menuListener;

  public MainMenuFrame()
	{
		super("Lecture Connect");
		setContentPane(parent);

		JPanel mainFrame = new JPanel();	
		mainFrame.setLayout(new BorderLayout());
		mainFrame.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40) );
		mainFrame.setBackground(Color.WHITE);
		menuListener = new MainMenuListener(this, parent);
		
		try{
		BufferedImage img = ImageIO.read(new File("src/images/logo.png"));
		JLabel background = new JLabel(new ImageIcon(img));
		mainFrame.add(background,BorderLayout.NORTH);
		
		JPanel buttons = new JPanel();
		buttons.setBackground(Color.WHITE);
		BufferedImage student_img = ImageIO.read(new File("src/images/student_button.png"));
		student_button = new JButton(new ImageIcon(student_img));
		student_button.setBorder(null);
    student_button.addActionListener(menuListener);
		buttons.add(student_button);
		
		buttons.add(Box.createRigidArea(new Dimension(20,0)));
		
		BufferedImage teacher_img = ImageIO.read(new File("src/images/teacher_button.png"));
		teacher_button = new JButton(new ImageIcon(teacher_img));
		teacher_button.setBorder(null);
    teacher_button.addActionListener(menuListener);
		buttons.add(teacher_button);
		
		mainFrame.add(buttons, BorderLayout.CENTER);
		
		}
		catch(IOException ioe)
		{
			System.out.println("Couldn't get background image");
		}
		
		parent.add(mainFrame, "main menu");
		switchCardView("main menu");
		
		setVisible(true);
		
	}
	
	public void switchCardView(String name)
	{
		CardLayout c = (CardLayout)(parent.getLayout());
		c.show(parent,  name);
	}
}
