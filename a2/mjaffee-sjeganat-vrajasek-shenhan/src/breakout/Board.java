package breakout;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

// we could consider having a gameboard interface that defines functions like getCanvas() and getSize() 
public class Board {
	private JFrame frame;
	private JPanel panel;
	private static final int DEFAULT_WIDTH = 460;
	private static final int DEFAULT_HEIGHT = 410;
	private static final String DEFAULT_TITLE = "Breakout";
	private static final Color DEFAULT_BGCOLOR = Color.GREEN;
	public Board(){
		this.frame = new JFrame();
		this.panel = new JPanel();
		panel.setFocusable(true);
		panel.setBackground(Board.DEFAULT_BGCOLOR);
		panel.setDoubleBuffered(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(Board.DEFAULT_WIDTH, Board.DEFAULT_HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setTitle(Board.DEFAULT_TITLE);
		frame.setVisible(true);
		frame.add(this.panel);
	}
	public void setSize(int height, int width){
		this.frame.setSize(height, width);
	}
	public void setTitle(String title){
		frame.setTitle(title);
	}
	public JFrame getFrame(){
		return this.frame;
	}
	public Graphics getCanvas(){
		return this.panel.getGraphics();
	}
	public JPanel getPanel(){
		return this.panel;
	}
}
