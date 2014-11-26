import GUI.MainMenuFrame;

import javax.swing.*;

import java.awt.*;


public class MainFile
{
	public static MainMenuFrame win;

  public static void main( String[] args )
  {
    win = new MainMenuFrame();
    win.setMinimumSize(new Dimension(400, 400));
    win.setLocation(0,0);
    win.pack();
    win.setVisible(true);
    win.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  }
}
