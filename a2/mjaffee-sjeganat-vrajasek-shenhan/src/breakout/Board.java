package breakout;

import java.awt.Color;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;

// we could consider having a gameboard interface that defines functions like getCanvas() and getSize() 
public class Board {
	private JFrame frame;
	private GamePanel gamePanel;
	private static final int DEFAULT_WIDTH = 460;
	private static final int DEFAULT_HEIGHT = 410;
	private static final String DEFAULT_TITLE = "Breakout";
	private static final Color DEFAULT_BGCOLOR = Color.GREEN;
	public Board(){
		this.frame = new JFrame();
		this.gamePanel = new GamePanel();
		frame.setFocusable(true);
		gamePanel.setFocusable(true);
		gamePanel.setBackground(Board.DEFAULT_BGCOLOR);
		gamePanel.setDoubleBuffered(true);
		gamePanel.setSize(Board.DEFAULT_WIDTH, Board.DEFAULT_HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(Board.DEFAULT_WIDTH, Board.DEFAULT_HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setTitle(Board.DEFAULT_TITLE);
		frame.setVisible(true);
		frame.add(this.gamePanel);
	}
	public void setSize(int height, int width){
		this.frame.setSize(height, width);
		this.gamePanel.setSize(height, width);
	}
	public void setTitle(String title){
		frame.setTitle(title);
	}
	public JFrame getFrame(){
		return this.frame;
	}
	public Image getCanvas(){
		return this.gamePanel.getBufferedImage();
	}
	public JPanel getGamePanel(){
		return this.gamePanel;
	}
}
