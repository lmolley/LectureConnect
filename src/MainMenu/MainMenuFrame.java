package MainMenu;

import javax.imageio.ImageIO;
import javax.swing.*;

import Listeners.MainMenuListener;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainMenuFrame extends JFrame{
	
	public JButton student_button;
	public JButton teacher_button;
	public JButton new_button;

	public MainMenuFrame()
	{
		super("Main Menu");
		setLayout(new BorderLayout());
		((JComponent) this.getContentPane()).setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40) );
		MainMenuListener myListener = new MainMenuListener(this);
		
		try{
		BufferedImage img = ImageIO.read(new File("src/images/logo.png"));
		JLabel background = new JLabel(new ImageIcon(img));
		add(background,BorderLayout.NORTH);
		
		JPanel buttons = new JPanel();
		BufferedImage student_img = ImageIO.read(new File("src/images/student_button.png"));
		student_button = new JButton(new ImageIcon(student_img));
		//student_button.setPreferredSize( new Dimension(50, 76));
		student_button.setBorder(null);
		student_button.addActionListener(myListener);
		buttons.add(student_button);
		
		buttons.add(Box.createRigidArea(new Dimension(20,0)));
		
		BufferedImage teacher_img = ImageIO.read(new File("src/images/teacher_button.png"));
		teacher_button = new JButton(new ImageIcon(teacher_img));
		//teacher_button.setPreferredSize( new Dimension(50, 76));
		teacher_button.setBorder(null);
		teacher_button.addActionListener(myListener);
		buttons.add(teacher_button);
		
		add(buttons, BorderLayout.CENTER);
		
		}
		catch(IOException ioe)
		{
			System.out.println("Couldn't get background image");
		}

		JLabel new_member = new JLabel("New to LectureConnect?   ");
		Font font = new_member.getFont();
		Font newFont = new Font(font.getFontName(), Font.BOLD, 20);
		new_member.setFont(newFont);
		new_member.setForeground(Color.BLUE);
		
		new_button = new JButton("Sign up here");
		font = new_button.getFont();
		Font underlined = new Font(font.getFontName(), Font.ITALIC, 20);
		new_button.setFont(underlined);
		new_button.setForeground(Color.BLUE);
		new_button.setBorder(null);
		new_button.addActionListener(myListener);
		
		JPanel new_panel = new JPanel();
		new_panel.add(new_member);
		new_panel.add(new_button);
		
		add(new_panel, BorderLayout.SOUTH);
		
		pack();
		setVisible(true);
		
	}
}
